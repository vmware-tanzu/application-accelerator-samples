import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {firstValueFrom, Observable} from 'rxjs';
import {Router} from "@angular/router";
import jwt_decode from 'jwt-decode';
import {DecodedToken, HttpRequestParamConfig} from './AuthorizationTypes';
import {enc, SHA256} from 'crypto-js';
import {randomBytes} from 'crypto';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  protected codeVerifier: string | undefined;
  protected codeChallengeSalt: string | undefined;
  private readonly authorizationEndpoint = '/oauth2/authorize';
  private readonly codeChallengeMethod = 'S256';
  private readonly responseType = 'code';
  private readonly authorizationCode = 'authorization_code';
  private readonly accessTokenRef = 'access_token';
  protected config: HttpRequestParamConfig = {
    authorizationEndpoint: this.authorizationEndpoint,
    codeChallengeMethod: this.codeChallengeMethod,
    responseType: this.responseType,
  };

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {
  }

  async startAuthentication(): Promise<any> {
    if (this.config.authority && this.config.clientId && this.config.redirectUri && this.config.scope) {
      this.redirectToAuthentication();
    } else {
      await this.getConfig()

      this.setRemainingConfigItems()
      this.redirectToAuthentication()
    }
  }

  protected redirectToAuthentication() {
    const scope = this.config.scope?.join(' ') ?? '';
    const clientId = this.config.clientId ?? 'default-frontend-client-id-change-me';
    const redirectUri = window.location.href;

    // Construct authorization request URL
    const params = new URLSearchParams({
      response_type: this.responseType,
      client_id: clientId,
      redirect_uri: redirectUri,
      scope: scope,
      state: this.config.state!,
      nonce: this.config.nonce!,
      code_challenge_method: this.codeChallengeMethod,
      code_challenge: this.config.codeChallenge!,
    });

    const authUrl = this.config.authority + this.config.authorizationEndpoint! + '?' + params.toString();

    // Redirect to authorization request URL
    window.location.href = authUrl;
  }

  protected setRemainingConfigItems() {
    this.codeVerifier ??= this.generateCodeVerifier();
    this.codeChallengeSalt ??= this.generateCodeChallengeSalt(256);
    this.config.codeChallenge ??= this.generateCodeChallenge(this.codeVerifier);
    this.config.state ??= this.generateState();
    this.config.nonce ??= this.generateNonce();
  }

  login(code: string, state: string): Observable<any> {
    const redirectUri = window.location.href;
    return this.http.post(`${this.config.authority}/${this.config.authorizationEndpoint}`, { code, state, redirect_uri: redirectUri });
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

  async getToken(code: string, codeVerifier: string, redirectUri: string): Promise<any> {
    if (!(this.config.authority && this.config.clientId && this.config.redirectUri && this.config.scope)) {
      await this.getConfig()
    }
    const url = `${this.config.authority}/oauth2/token`
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    });
    const body = new HttpParams()
      .set('grant_type', this.authorizationCode)
      .set('redirect_uri', redirectUri)
      .set('code_verifier', codeVerifier)
      .set('code', code);

    if (this.config.clientId != null) {
      body.set('client_id', this.config.clientId)
    }

    return firstValueFrom(
      this.http.post(url, body.toString(), { headers })
    ).then((response: any) => {
        const accessToken = response[this.accessTokenRef];
        const expiresIn = response['expires_in'];
        const expirationDate = new Date(Date.now() + expiresIn * 1000);
        localStorage.setItem(this.accessTokenRef, accessToken);
        localStorage.setItem('expires_at', expirationDate.toISOString());
      }
    );
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

  private generateCodeVerifier(): string {
    const bytes = randomBytes(128);
    return this.base64URLEncode(bytes);
  }

  private base64URLEncode(buffer: Buffer): string {
    return buffer.toString('base64')
      .replace(/\+/g, '-')
      .replace(/\//g, '_')
      .replace(/=/g, '');
  }

  private generateCodeChallenge(verifier: string): string {
    // Use the SHA-256 algorithm to generate a code challenge
    const challenge = SHA256(verifier + this.codeChallengeSalt).toString(enc.Base64);
    return challenge.replace(/\+/g, '-').replace(/\//g, '_').replace(/=/g, '');
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

  private ensureContainsScopeOpenId(config: string[]): string[] {
    if (config.includes('openid')) {
      return config
    }
    config.push('openid')
    return config
  }

  protected async getConfig(): Promise<any> {
    const conf = await firstValueFrom(this.http.get<any>('assets/auth.conf.json'))
    this.config.authority = conf.authority
    this.config.clientId = conf.clientId
    // this.config.postLogoutRedirectUri = conf.postLogoutRedirectUri
    this.config.redirectUri = conf.redirectUri
    this.config.scope = this.ensureContainsScopeOpenId(conf.scope)
    this.setRemainingConfigItems()
  }

  getCodeVerifier(): string {
    return this.codeVerifier!
  }

  getRedirectUri() {
    return window.location.href;
  }

  getState(): string {
    return this.config.state!
  }
}
