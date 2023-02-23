import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {AuthorizationService} from './authorization.service';
import {AuthenticationUtilities} from './authentication-utilities';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthorizationService, private authenticationUtilities: AuthenticationUtilities) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.url.includes('/oauth2/token') || request.url.includes('/oauth2/authorize')) {
      return next.handle(request);
    }

    const token = this.authService.getAccessToken();
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    if (!this.authenticationUtilities.thereIsAnAuthorizationCode()) {
      this.authService.startAuthentication();
    }

    return next.handle(request).pipe(
      catchError(async (error: HttpErrorResponse) => {
        if (error.status === 401) {
          this.authenticationUtilities.exchangeAuthCodeForToken(request);
          throw error;
        }
        throw error;
      }),
      retry(1),
    );
  }
}
