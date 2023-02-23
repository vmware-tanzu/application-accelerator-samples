import {HttpErrorResponse, HttpHandler, HttpHeaders, HttpRequest, HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {TestBed} from '@angular/core/testing';
import {lastValueFrom, Observable, of, throwError} from 'rxjs';
import {AuthInterceptor} from './authInterceptor';
import {AuthorizationService} from './authorization.service';
import {AuthenticationUtilities} from "./authentication-utilities";

describe('AuthInterceptor', () => {
  let interceptor: AuthInterceptor;
  let authService: jasmine.SpyObj<AuthorizationService>;
  let http: HttpTestingController;
  let authenticationUtilities: jasmine.SpyObj<AuthenticationUtilities>;

  beforeEach(() => {
    authService = jasmine.createSpyObj('AuthorizationService', ['getAccessToken', 'startAuthentication']);
    authenticationUtilities = jasmine.createSpyObj('AuthenticationUtilities', [
      'thereIsAnAuthorizationCode',
      'exchangeAuthCodeForToken',
    ]);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        AuthInterceptor,
        { provide: AuthorizationService, useValue: authService },
        { provide: AuthenticationUtilities, useValue: authenticationUtilities },
      ],
    });

    interceptor = TestBed.inject(AuthInterceptor);
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    http.verify();
  });

  it('should add Authorization header to requests when token is available', (done) => {
    const mockRequest = new HttpRequest('GET', '/api/test');
    const mockResponse = { success: true };
    authService.getAccessToken.and.returnValue('mock-token');

    interceptor.intercept(mockRequest as any, {
      handle: (req: HttpRequest<any>) => {
        expect(req.headers.get('Authorization')).toBe('Bearer mock-token');
        return new Observable<HttpResponse<any>>((observer) => {
          observer.next(new HttpResponse({ body: mockResponse }));
          observer.complete();
        });
      },
    } as any).subscribe((event) => {
      if (event instanceof HttpResponse) {
        expect(event.body).toEqual(mockResponse);
        done();
      }
    });
  });

  it('should not add Authorization header to authentication requests', (done) => {
    const mockRequest = new HttpRequest('OPTIONS', '/api/authorize');

    const mockResponse = { success: true };
    authService.getAccessToken.and.returnValue(null);

    interceptor.intercept(mockRequest as any, {
      handle: (req: HttpRequest<any>) => {
        expect(req.headers.get('Authorization')).toBeNull();
        return new Observable<HttpResponse<any>>((observer) => {
          observer.next(new HttpResponse({ body: mockResponse }));
          observer.complete();
        });
      },
    } as any).subscribe((event) => {
      if (event instanceof HttpResponse) {
        expect(event.body).toEqual(mockResponse);
        done();
      }
    });
  });
})
