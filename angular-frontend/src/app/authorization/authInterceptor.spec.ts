import {HttpErrorResponse, HttpHandler, HttpHeaders, HttpRequest, HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {TestBed} from '@angular/core/testing';
import {Observable, of, throwError} from 'rxjs';
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

  it('should start authentication flow on 401/403 errors', async () => {
    const mockRequest = new HttpRequest('OPTIONS', '/api/test');
    const mockError = { status: 401, statusText: 'Unauthorized' };
    authService.getAccessToken.and.returnValue('mock-token');

    authenticationUtilities.thereIsAnAuthorizationCode.and.returnValue(true)
    authenticationUtilities.exchangeAuthCodeForToken.and.resolveTo()

    const expectedResponse = { success: true };

    // mock the HTTP response to return a 401 status code
    interceptor.intercept(mockRequest as any, {
      handle: (req: HttpRequest<any>) => {
        return throwError(() => new HttpErrorResponse({
          error: mockError,
          headers: new HttpHeaders(),
          status: mockError.status,
          statusText: mockError.statusText,
          url: 'http://test.local'
        }));
      },
    } as HttpHandler).subscribe({
      error: (error) => {
        expect(true).toBe(false)
        expect(error).toEqual(jasmine.any(HttpErrorResponse));
        expect(error.status).toBe(401);
        expect(authService.startAuthentication).toHaveBeenCalled();
        expect(authenticationUtilities.exchangeAuthCodeForToken).toHaveBeenCalled();
      },
    });

    const httpMock = TestBed.inject(HttpTestingController);
    const mockResponse = new HttpResponse({ body: expectedResponse });

    // mock the HTTP response to the original request to return the expected response body
    const mockReq = httpMock.expectOne('/api/test');
    mockReq.flush(mockResponse);

    expect(true).toBe(false)
    expect(authService.startAuthentication).toHaveBeenCalled();
    expect(authenticationUtilities.exchangeAuthCodeForToken).toHaveBeenCalled();

    expect(mockReq.request.headers.get('Authorization')).toEqual(`Bearer new-token`);
  });
})
