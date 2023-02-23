import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Router} from "@angular/router";
import jwt_decode from 'jwt-decode';
import {AccessTokenResponse, DecodedToken} from './AuthorizationTypes';
import {randomBytes} from 'crypto';
import * as cryptojs from "crypto";
import base64url from 'base64url';
import {Utils} from "../utils";
import {AppConfigService} from "../app-config.service";
import {ACCESS_TOKEN_REF, AUTHORIZATION_CODE, CODE_CHALLENGE_METHOD, RESPONSE_TYPE} from "./authorization-config";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  private readonly authorizationEndpoint = '/oauth2/authorize';
  protected authority: string | undefined
  protected clientId: string | undefined
  protected scope: string[] | undefined

  constructor(
    private http: HttpClient,
    private router: Router,
    private utils: Utils,
    private appConfig: AppConfigService
  ) {}

  ngOnInit(): void {
    this.authority = this.appConfig.getAuthority()
    this.clientId = this.appConfig.getClientId()
    this.scope = this.ensureContainsScopeOpenId(this.appConfig.getScope())
  }

  async startAuthentication(): Promise<any> {
    this.ensureConfigLoaded()
    const scope = this.scope?.join(' ') ?? 'openid';
    const clientId = this.clientId ?? 'default-frontend-client-id-change-me';
    const redirectUri = this.utils.removeQueryParamsFromUrl(this.utils.getCurrentUrl());

    const params = {
      response_type: RESPONSE_TYPE,
      client_id: clientId,
      redirect_uri: redirectUri,
      scope: scope,
      state: this.getState(),
      nonce: this.getNonce(),
      code_challenge_method: CODE_CHALLENGE_METHOD,
      code_challenge: this.getCodeChallenge(),
    };

    const queryString = Object.entries(params)
      .map(([key, value]) => `${key}=${encodeURIComponent(value)}`)
      .join("&");

    const authUrl = encodeURI(this.authority + this.authorizationEndpoint + "?" + queryString);


    // Redirect to authorization request URL
    this.utils.setDestinationUrlTo(authUrl)
  }

  protected setRemainingConfigItems() {
    this.getCodeVerifier()
    this.getCodeChallengeSalt()
    this.getCodeChallenge()
    this.getState()
    this.getNonce()
  }

  login(code: string, state: string): Observable<any> {
    const redirectUri = window.location.href;
    return this.http.post(`${this.authority}/${this.authorizationEndpoint}`, {
      code,
      state,
      redirect_uri: redirectUri
    });
  }

  logout(): void {
    localStorage.removeItem(ACCESS_TOKEN_REF);
    localStorage.removeItem('expires_at');
    this.router.navigate(['/']);
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem(ACCESS_TOKEN_REF);
    return !!token;
  }

  async getToken(code: string, request: HttpRequest<any> | null): Promise<any> {
    this.ensureConfigLoaded();
    const url = `${this.authority}/oauth2/token`
    const redirectUri = this.utils.removeQueryParamsFromUrl(this.utils.getCurrentUrl());

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
    });
    const params = new HttpParams()
      .set('grant_type', AUTHORIZATION_CODE)
      .set('redirect_uri', redirectUri)
      .set('code_verifier', this.getCodeVerifier())
      .set('code', code)
      .set('client_id', this.clientId!);

      // @ts-ignore
    const response: Object = await this.http.post(url, params.toString(), {headers}).toPromise();
    const tokenResponse = response as AccessTokenResponse
    const accessToken = tokenResponse[ACCESS_TOKEN_REF];
    const expiresIn = tokenResponse['expires_in'];
    const expirationDate = new Date(Date.now() + expiresIn * 1000);
    localStorage.setItem(ACCESS_TOKEN_REF, accessToken);
    localStorage.setItem('expires_at', expirationDate.toISOString());

    if (request) {
      this.http.request(request)
    }
  }

  private ensureConfigLoaded() {
    if (!(this.authority && this.clientId && this.scope)) {
      this.authority = this.appConfig.getAuthority()
      this.clientId = this.appConfig.getClientId()
      this.scope = this.ensureContainsScopeOpenId(this.appConfig.getScope())
    }
  }

  checkToken(): boolean {
    const token = this.getAccessToken();
    if (!token) {
      return false;
    }

    const decodedToken = jwt_decode(token) as DecodedToken;
    const expirationDate = new Date(0);
    expirationDate.setUTCSeconds(decodedToken.exp);

    return expirationDate >= new Date();
  }

  getAccessToken(): string | null {
    return localStorage.getItem(ACCESS_TOKEN_REF);
  }

  getCodeVerifier(): string {
    return this.getValueFromStorage('codeVerifier', () => this.generateCodeVerifier());
  }

  getCodeChallenge(): string {
    const codeVerifier = this.getCodeVerifier();
    return this.getValueFromStorage('codeChallenge', () => this.generateCodeChallenge(codeVerifier));
  }

  getCodeChallengeSalt(): string {
    return this.getValueFromStorage('codeChallengeSalt', () => this.generateCodeChallengeSalt(256));
  }

  getState(): string {
    return this.getValueFromStorage('state', () => this.generateState());
  }

  getNonce(): string {
    return this.getValueFromStorage('nonce', () => this.generateNonce());
  }

  getRedirectUri() {
    return window.location.href;
  }

  private getValueFromStorage(key: string, generateValue: () => string): string {
    const storedValue = localStorage.getItem(key);
    if (storedValue) {
      return storedValue;
    } else {
      const generatedValue = generateValue();
      localStorage.setItem(key, generatedValue);
      return generatedValue;
    }
  }

  private generateCodeVerifier(): string {
    const bytes = randomBytes(64);
    return AuthorizationService.base64URLEncode(bytes);
  }

  private static base64URLEncode(buffer: Buffer): string {
    return buffer.toString('base64')
      .replace(/\+/g, '-')
      .replace(/\//g, '_')
      .replace(/=/g, '');
  }

  private generateCodeChallenge(verifier: string): string {
    const digest = cryptojs.createHash("sha256")
      .update(verifier)
      .digest("base64");

    return base64url.fromBase64(digest);
  }

  protected generateCodeChallengeSalt(length: number): string {
    const charset = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-._~';
    const randomValues = new Uint8Array(length);
    crypto.getRandomValues(randomValues);
    return Array.from(randomValues)
      .map((value) => charset[value % charset.length])
      .join('');
  }

  private generateState(): string {
    // Generate a random string for the state parameter, a security measure to prevent CSRF (Cross-Site Request Forgery) attacks
    return Math.random().toString(36).substring(2, 15) +
      Math.random().toString(36).substring(2, 15);
  }

  private generateNonce(): string {
    // Generate a random string for the nonce parameter to prevent replay attacks
    return Math.random().toString(36).substring(2, 15) +
      Math.random().toString(36).substring(2, 15);
  }

  private ensureContainsScopeOpenId(scopes: string[]): string[] {
    if (scopes.includes('openid')) {
      return scopes
    }
    scopes.push('openid')
    return scopes
  }
}
