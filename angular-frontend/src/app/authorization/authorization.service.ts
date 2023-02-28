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
import {AppConfigService} from "../../app-config.service";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  private readonly authorizationEndpoint = '/oauth2/authorize';
  private readonly codeChallengeMethod = 'S256';
  private readonly responseType = 'code';
  private readonly authorizationCode = 'authorization_code';
  private readonly accessTokenRef = 'access_token';
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

    // Construct authorization request URL
    const params = new URLSearchParams({
      response_type: this.responseType,
      client_id: clientId,
      redirect_uri: redirectUri,
      scope: scope,
      state: this.getState(),
      nonce: this.getNonce(),
      code_challenge_method: this.codeChallengeMethod,
      code_challenge: this.getCodeChallenge(),
    });

    const authUrl = this.authority + this.authorizationEndpoint + '?' + params.toString();

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
    localStorage.removeItem(this.accessTokenRef);
    localStorage.removeItem('expires_at');
    this.router.navigate(['/']);
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem(this.accessTokenRef);
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
      .set('grant_type', this.authorizationCode)
      .set('redirect_uri', redirectUri)
      .set('code_verifier', this.getCodeVerifier())
      .set('code', code)
      .set('client_id', this.clientId!);

      // @ts-ignore
    const response: Object = await this.http.post(url, params.toString(), {headers}).toPromise();
    const tokenResponse = response as AccessTokenResponse
    const accessToken = tokenResponse[this.accessTokenRef];
    const expiresIn = tokenResponse['expires_in'];
    const expirationDate = new Date(Date.now() + expiresIn * 1000);
    localStorage.setItem(this.accessTokenRef, accessToken);
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
    return localStorage.getItem(this.accessTokenRef);
  }

  // protected getConfig(): void {
  //   (async () => {
  //    const conf = await firstValueFrom(this.http.get<any>('assets/auth.conf.json'))
  //   this.authority = conf.authority
  //   // this.authority = "http://localhost.identity.team:9000"; //conf.authority
  //   // this.clientId = "angular-frontend"; //conf.clientId
  //   this.clientId = conf.clientId
  //   // this.scope = this.ensureContainsScopeOpenId(["openid", "profile", "email", "message.read", "message.write"])
  //   this.scope = this.ensureContainsScopeOpenId(conf.scope)
  //   this.setRemainingConfigItems()
  //   })();
  // }

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
