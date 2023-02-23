import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { AuthorizationService } from './authorization.service';


describe('AuthorizationService', () => {
  let service: TestAuthorizationService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [ TestAuthorizationService ]
    });
    service = TestBed.inject(TestAuthorizationService);
    httpMock = TestBed.inject(HttpTestingController);
  });


  describe('start authentication', () => {
    it('should redirect to authentication if config is complete', async () => {
      spyOn(service, 'redirectToAuthentication');
      spyOn(service, 'getConfig');

      // Set up a complete config
      service['config'] = {
        authority: 'https://example.com',
        clientId: 'my-client-id',
        redirectUri: 'https://my-app.com/callback',
        scope: ['openid', 'profile'],
        state: 'my-state',
        nonce: 'my-nonce',
        codeChallengeMethod: 'S256',
        codeChallenge: 'my-code-challenge',
        responseType: 'code',
        authorizationEndpoint: '/oauth2/authorize'
      };

      await service.startAuthentication();
      expect(service.redirectToAuthentication).toHaveBeenCalled();
      expect(service.getConfig).not.toHaveBeenCalled();
    });

    it('should get config and redirect to authentication if config is incomplete', async () => {
      spyOn(service, 'redirectToAuthentication');
      spyOn(service, 'getConfig').and.returnValue(Promise.resolve());

      // Set up an incomplete config
      service['config'] = {
        clientId: 'my-client-id',
        redirectUri: 'https://my-app.com/callback',
        scope: ['openid', 'profile']
      };

      await service.startAuthentication();
      expect(service.redirectToAuthentication).toHaveBeenCalled();
      expect(service.getConfig).toHaveBeenCalled();
    });
  })

  describe('token', () => {
    it('should return false if token has expired', () => {
      // Generate a JWT token that expires in an hour
      const expirationTime = Math.floor(Date.now() / 1000) + 1
      let token = generateToken(expirationTime);

      // Store the token in local storage
      spyOn(localStorage, 'getItem').and.returnValue(token);
      // Wait for 2 seconds to ensure the token has expired
      setTimeout(() => {
        expect(service.checkToken()).toBe(false);
      }, 2000);
    });

    it('should return true if token has not expired', () => {
      // Generate a JWT token that expires in an hour
      const expirationTime = Math.floor(Date.now() / 1000) + 3600;
      const token = generateToken(expirationTime);

      // Store the token in local storage
      spyOn(localStorage, 'getItem').and.returnValue(token);

      // Check that the token is valid
      expect(service.checkToken()).toBe(true);
    });

    it('should return false if no token is found', () => {
      spyOn(localStorage, 'getItem').and.returnValue(null);
      expect(service.checkToken()).toBe(false);
    });
  })

  describe('setting the configs', () => {
    beforeEach(() => {
      service.config = {}
      service.config.clientId = 'my-client-id'
      service.config.redirectUri = 'https://my-app.com/callback'
      service.config.scope = ['openid', 'profile']
    })

    it('should set the remaining config items if not already set', () => {
      // Call the setRemainingConfigItems() function
      service.setRemainingConfigItems();

      // Check that the values that were not already set have been set correctly
      expect(service.codeVerifier).toBeTruthy();
      expect(service.codeChallengeSalt).toBeTruthy();
      expect(service.config.codeChallenge).toBeTruthy();
      expect(service.config.state).toBeTruthy();
      expect(service.config.nonce).toBeTruthy();
    });

    it('should not change the config values that are already set', () => {
      // Set some initial values to the config object
      service.codeVerifier = 'abc123';
      service.codeChallengeSalt = 'xyz456';
      service.config.codeChallenge = '123abc';
      service.config.state = '456xyz';
      service.config.nonce = '789qwe';

      // Call the setRemainingConfigItems() function
      service.setRemainingConfigItems();

      // Check that the values that were already set have not changed
      expect(service.codeVerifier).toEqual('abc123');
      expect(service.codeChallengeSalt).toEqual('xyz456');
      expect(service.config.codeChallenge).toEqual('123abc');
      expect(service.config.state).toEqual('456xyz');
      expect(service.config.nonce).toEqual('789qwe');
    });
  })

  describe('generating functions', () => {
    describe('generateCodeChallengeSalt', () => {
      it('should generate a random string of the specified length', () => {
        const salt = service.generateCodeChallengeSalt(16);
        expect(salt.length).toBe(16);
        expect(typeof salt).toBe('string');
      });

      it('should generate different salts on successive calls', () => {
        const salt1 = service.generateCodeChallengeSalt(16);
        const salt2 = service.generateCodeChallengeSalt(16);
        expect(salt1).not.toBe(salt2);
      });
    });
  })

  class TestAuthorizationService extends AuthorizationService {
    override config = super.config
    override codeVerifier = super.codeVerifier
    override codeChallengeSalt = super.codeChallengeSalt

    override getConfig(): any {
      return super.getConfig();
    }

    override redirectToAuthentication(): any {
      return super.redirectToAuthentication()
    }

    override setRemainingConfigItems(): any {
      return super.setRemainingConfigItems()
    }

    override generateCodeChallengeSalt(length: number): any {
      return super.generateCodeChallengeSalt(length)
    }
  }


  const generateToken = (expirationTime: number) => {
    const header = {alg: 'HS256', typ: 'JWT'};
    const payload = {sub: '123', exp: expirationTime};
    const secret = 'test-secret';
    const encodedHeader = btoa(JSON.stringify(header));
    const encodedPayload = btoa(JSON.stringify(payload));
    const signature = btoa(`${encodedHeader}.${encodedPayload}.${secret}`);
    const token = `${encodedHeader}.${encodedPayload}.${signature}`;
    return token;
  };
})
