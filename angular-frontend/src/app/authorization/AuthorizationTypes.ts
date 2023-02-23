export interface DecodedToken {
  iss: string;
  sub: string;
  aud: string[];
  exp: number;
  iat: number;
  nonce: string;
  auth_time: number;
  acr: string;
  amr: string[];
  azp: string;
  scope: string;
  email?: string;
}

export interface HttpRequestParamConfig {
  authority?: string,
  clientId?: string,
  postLogoutRedirectUri?: string,
  redirectUri?: string,
  codeChallengeMethod?: string,
  scope?: string[],
  responseType?: string,
  state?: string,
  nonce?: string,
  codeChallenge?: string,
  authorizationEndpoint?: string,
}
