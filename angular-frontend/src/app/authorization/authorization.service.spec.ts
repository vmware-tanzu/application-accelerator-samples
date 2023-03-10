import {AuthorizationService} from './authorization.service';
import {Utils} from "../utils";
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {Router} from "@angular/router";
import {of} from "rxjs";
import {AppConfigService} from "../app-config.service";
import anything = jasmine.anything;


describe('AuthorizationService', () => {
  let service: AuthorizationService;
  let httpMock: jasmine.SpyObj<HttpClient>;
  let utils: jasmine.SpyObj<Utils>;
  let appConfigService: jasmine.SpyObj<AppConfigService>;
  let router: jasmine.SpyObj<Router>

  beforeEach(() => {
    utils = jasmine.createSpyObj('Utils', ['getCurrentUrl', 'setDestinationUrlTo', 'removeQueryParamsFromUrl'])
    appConfigService = jasmine.createSpyObj('AppConfigService', ['getAuthority', 'getClientId', 'getScope'])
    httpMock = jasmine.createSpyObj('HttpClient', ['post', 'request'])
    router = jasmine.createSpyObj('Router', ['navigate'])

    service = new AuthorizationService(httpMock, router, utils, appConfigService)
  });

  describe('start authentication', () => {
    it('should redirect to authentication if config is complete', async () => {
      utils.getCurrentUrl.and.returnValue('http://www.example.com')
      utils.removeQueryParamsFromUrl.and.returnValue('http://www.example.com/')

      spyOn(service, 'getState').and.returnValue('a-state')
      spyOn(service, 'getNonce').and.returnValue('a-nonce')
      spyOn(service, 'getCodeChallenge').and.returnValue('a-code-challenge')

      appConfigService.getAuthority.and.returnValue('http://authorizationserver:9000')
      appConfigService.getClientId.and.returnValue('angular-frontend')
      appConfigService.getScope.and.returnValue(['openid', 'email'])

      const authorizationServerWithEndpoint = 'http://authorizationserver:9000/oauth2/authorize';
      const responseTypeCode = 'response_type=code';
      const clientId = 'client_id=angular-frontend';
      const redirectUri = 'redirect_uri=http%3A%2F%2Fwww.example.com%2F';
      const scopes = 'scope=openid%20email';
      const state = 'state=a-state';
      const nonce = 'nonce=a-nonce';
      const codeChallengeMethod = 'code_challenge_method=S256';
      const codeChallenge = 'code_challenge=a-code-challenge';

      const expectedUrl = authorizationServerWithEndpoint
        + '?' + responseTypeCode
        + '&' + clientId
        + '&' + redirectUri
        + '&' + scopes
        + '&' + state
        + '&' + nonce
        + '&' + codeChallengeMethod
        + '&' + codeChallenge

      expect(service).toBeDefined()

      await service.startAuthentication();


      expect(utils.setDestinationUrlTo).toHaveBeenCalledWith(expectedUrl);
    });

  })

  describe('token', () => {
    it('checkToken should return false if token has expired', () => {
      // Generate a JWT token that expires in an hour
      const expirationTime = Math.floor(Date.now() / 1000) + 1
      let token = generateTestToken(expirationTime);

      // Store the token in local storage
      spyOn(localStorage, 'getItem').and.returnValue(token);
      // Wait for 2 seconds to ensure the token has expired
      setTimeout(() => {
        expect(service.checkToken()).toBe(false);
      }, 2000);
    });

    it('checkToken should return true if token has not expired', () => {
      // Generate a JWT token that expires in an hour
      const expirationTime = Math.floor(Date.now() / 1000) + 3600;
      const token = generateTestToken(expirationTime);

      // Store the token in local storage
      spyOn(localStorage, 'getItem').and.returnValue(token);

      // Check that the token is valid
      expect(service.checkToken()).toBe(true);
    });

    it('checkToken should return false if no token is found', () => {
      spyOn(localStorage, 'getItem').and.returnValue(null);
      expect(service.checkToken()).toBe(false);
    });

    describe('getToken', () => {
      const redirectUri = 'http://www.example.com/';
      const codeVerifier = 'a-code-verifier';
      const code = 'code'

      const expectedUrl = 'http://localhost.identity.team:9000/oauth2/token'
      const expectedParams = new HttpParams()
        .set('grant_type', 'authorization_code')
        .set('redirect_uri', redirectUri)
        .set('code_verifier', codeVerifier)
        .set('code', code)
        .set('client_id', 'angular-frontend');

      beforeEach(() => {
        appConfigService.getAuthority.and.returnValue('http://localhost.identity.team:9000')
        appConfigService.getClientId.and.returnValue('angular-frontend')
        appConfigService.getScope.and.returnValue(['openid', 'email'])

        utils.getCurrentUrl.and.returnValue('http://www.example.com')
        utils.removeQueryParamsFromUrl.and.returnValue(redirectUri)
        const tokenObservable = of({
          access_token: 'an-access-token',
          expires_in: 123
        });
        httpMock.post.and.returnValue(tokenObservable)

        spyOn(service, 'getCodeVerifier').and.returnValue(codeVerifier)
        localStorage.removeItem('accessToken')
      })

      it('should call the authority to fetch the token', async () => {
        await service.getToken(code, null)

        expect(httpMock.post).toHaveBeenCalledWith(expectedUrl, expectedParams.toString(), anything())
      });

      it('should store the access token to local storage', async () => {
        spyOn(localStorage, 'setItem')

        await service.getToken(code, null)
        expect(localStorage.setItem).toHaveBeenCalledWith('access_token', 'an-access-token')
      });

      it('should call through to the original request if there is one', async () => {
        const mockHttpRequest: HttpRequest<any> = jasmine.createSpyObj('HttpRequest<any>', ['clone'])
        await service.getToken(code, mockHttpRequest)

        // @ts-ignore
        expect(httpMock.request).toHaveBeenCalledWith(mockHttpRequest)
      });
    })
  })

  describe('generating functions', () => {
    describe('codeChallengeSalt', () => {
      beforeEach(() => {
        spyOn(localStorage, 'setItem').and.callThrough()
        spyOn(localStorage, 'getItem').and.callThrough()

        localStorage.removeItem('codeChallengeSalt')
      })

      it('should generate a random string of a specified length', () => {
        const salt = service.getCodeChallengeSalt();
        expect(salt.length).toBe(256);
        expect(typeof salt).toBe('string');
        expect(localStorage.setItem).toHaveBeenCalledWith('codeChallengeSalt', salt)
      });

      it('should not generate different salts on successive calls', () => {
        const salt1 = service.getCodeChallengeSalt();
        const salt2 = service.getCodeChallengeSalt();
        expect(salt1).toBe(salt2);
      });
    });

    describe('getCodeVerifier', () => {
      beforeEach(() => {
        spyOn(localStorage, 'setItem').and.callThrough()
        spyOn(localStorage, 'getItem').and.callThrough()

        localStorage.removeItem('codeVerifier')
      })

      it('should generate a base64 URL-encoded string', () => {
        const codeVerifier = service.getCodeVerifier();
        expect(codeVerifier).toMatch(/^[A-Za-z0-9-_]+$/);
        expect(localStorage.setItem).toHaveBeenCalledWith('codeVerifier', codeVerifier)
      });

      it('should generate the same code verifier on successive calls', () => {
        const codeVerifier1 = service.getCodeVerifier();
        const codeVerifier2 = service.getCodeVerifier();
        expect(codeVerifier1).toEqual(codeVerifier2);
      });
    })

    describe('getCodeChallenge', () => {
      const codeVerifier = 'some-random-string';

      beforeEach(() => {
        spyOn(localStorage, 'setItem').and.callThrough()
        spyOn(localStorage, 'getItem').and.callThrough()

        spyOn(service, 'getCodeVerifier').and.returnValue(codeVerifier)
        localStorage.removeItem('codeChallenge')
      })

      it('should generate code challenge from code verifier', () => {
        const codeChallenge = service.getCodeChallenge();
        expect(codeChallenge).toBeDefined();
        expect(codeChallenge).not.toEqual('');
        expect(localStorage.setItem).toHaveBeenCalledWith('codeChallenge', codeChallenge)
      });

      it('should generate the same code challenge on successive calls', () => {
        const codeChallenge1 = service.getCodeChallenge();
        const codeChallenge2 = service.getCodeChallenge();
        expect(codeChallenge1).toEqual(codeChallenge2);
      });
    })
  })


  const generateTestToken = (expirationTime: number) => {
    const header = {alg: 'HS256', typ: 'JWT'};
    const payload = {sub: '123', exp: expirationTime};
    const secret = 'test-secret';
    const encodedHeader = btoa(JSON.stringify(header));
    const encodedPayload = btoa(JSON.stringify(payload));
    const signature = btoa(`${encodedHeader}.${encodedPayload}.${secret}`);
    return `${encodedHeader}.${encodedPayload}.${signature}`;
  };
})
