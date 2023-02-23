import {TestBed} from '@angular/core/testing';
import {ActivatedRoute} from '@angular/router';
import {AuthorizationService} from './authorization.service';
import {AuthenticationUtilities} from './authentication-utilities';

describe('AuthenticationUtilities', () => {
  let authenticationUtilities: AuthenticationUtilities;
  let activatedRouteSpy: jasmine.SpyObj<ActivatedRoute>;
  let authServiceSpy: jasmine.SpyObj<AuthorizationService>;

  beforeEach(() => {
    authServiceSpy = jasmine.createSpyObj(
      'AuthorizationService',
      [
        'getToken',
        'getCodeVerifier',
        'getRedirectUri']);

    const routeSpy = jasmine.createSpyObj('ActivatedRoute', [], {
      snapshot: {
        queryParams: {
          code: 'abc123',
          state: 'test'
        }
      }
    });

    TestBed.configureTestingModule({
      providers: [
        AuthenticationUtilities,
        { provide: AuthorizationService, useValue: authServiceSpy },
        { provide: ActivatedRoute, useValue: routeSpy }
      ]
    });

    authenticationUtilities = TestBed.inject(AuthenticationUtilities);
    activatedRouteSpy = TestBed.inject(ActivatedRoute) as jasmine.SpyObj<ActivatedRoute>;
  });

  it('should create', () => {
    expect(authenticationUtilities).toBeTruthy();
  });

  describe('exchangeAuthCodeForToken', () => {
    it('should call authService.getToken with correct arguments', async () => {
      const expectedCodeVerifier = 'a-code-verifier';
      const expectedRedirectUri = 'the-redirect-uri';
      authServiceSpy.getCodeVerifier.and.returnValue(expectedCodeVerifier)
      authServiceSpy.getRedirectUri.and.returnValue(expectedRedirectUri)

      await authenticationUtilities.exchangeAuthCodeForToken();

      expect(authServiceSpy.getToken).toHaveBeenCalledWith('abc123', expectedCodeVerifier, expectedRedirectUri);
    });
  });

  describe('thereIsAnAuthorizationCode', () => {
    it('should return true if there is an authorization code in the snapshot', () => {
      expect(authenticationUtilities.thereIsAnAuthorizationCode()).toBeTrue();
    });

    it('should return false if there is no authorization code in the snapshot', () => {
      activatedRouteSpy.snapshot.queryParams = {};

      expect(authenticationUtilities.thereIsAnAuthorizationCode()).toBeFalse();
    });
  });
});
