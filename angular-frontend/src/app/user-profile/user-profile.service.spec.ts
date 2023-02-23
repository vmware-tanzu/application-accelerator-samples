import {TestBed} from '@angular/core/testing';
import {UserProfileService} from './user-profile.service';
import {AuthorizationService} from '../authorization/authorization.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import * as base64 from 'base64-js';
import {AuthenticationUtilities} from "../authorization/authentication-utilities";
import {ActivatedRoute} from "@angular/router";

describe('UserProfileService', () => {
  let service: UserProfileService;
  let authService: jasmine.SpyObj<AuthorizationService>;
  let authUtilities: jasmine.SpyObj<AuthenticationUtilities>;
  let activatedRoute: jasmine.SpyObj<ActivatedRoute>;
  const expectedSub = '123456789';
  const expectedEmail = 'john@doe.com';


  beforeEach(() => {
    authService = jasmine.createSpyObj('AuthorizationService', ['getAccessToken', 'startAuthentication']);
    authUtilities = jasmine.createSpyObj('AuthenticationUtilities', ['exchangeAuthCodeForToken', 'thereIsAnAuthorizationCode']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        {provide: AuthorizationService, useValue: authService},
        {provide: AuthenticationUtilities, useValue: authUtilities},
        UserProfileService,
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              queryParams: {
                code: 'mock-auth-code'
              }
            }
          }
        }
      ]
    });
    service = TestBed.inject(UserProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should parse the access token and return the sub', async () => {
    const mockAccessToken = generateToken(expectedSub, expectedEmail);

    authService.getAccessToken.and.returnValue(mockAccessToken)

    const actualSub = await service.getSub();
    expect(actualSub).toEqual(expectedSub);
    expect(authService.getAccessToken).toHaveBeenCalled();
  });

  it('should parse the access token and return the email', async () => {
    const mockAccessToken = generateToken(expectedSub, expectedEmail);

    authService.getAccessToken.and.returnValue(mockAccessToken)
    const actualEmail = await service.getEmail();
    expect(actualEmail).toEqual(expectedEmail);
    expect(authService.getAccessToken).toHaveBeenCalled();
  });

  const generateToken = (sub: string, email: string) => {
    const header = {alg: 'HS256', typ: 'JWT'};
    const expirationTime = Math.floor(Date.now() / 1000) + 3600;
    const payload = {sub: sub, email: email, exp: expirationTime};
    const secret = 'test-secret';
    const encodedHeader = base64.fromByteArray(new TextEncoder().encode(JSON.stringify(header)));
    const encodedPayload = base64.fromByteArray(new TextEncoder().encode(JSON.stringify(payload)));
    const signature = base64.fromByteArray(new TextEncoder().encode(`${encodedHeader}.${encodedPayload}.${secret}`));
    return `${encodedHeader}.${encodedPayload}.${signature}`;
  };
});
