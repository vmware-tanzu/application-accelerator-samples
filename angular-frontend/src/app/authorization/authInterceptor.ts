import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Injectable} from "@angular/core";
import {AuthorizationService} from "./authorization.service";
import {AuthenticationUtilities} from "./authentication-utilities";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthorizationService, private authenticationUtilities: AuthenticationUtilities) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getAccessToken();
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(request).pipe(
      catchError(async (error: HttpErrorResponse) => {
        if (error.status === 401) {
          if (this.authenticationUtilities.thereIsAnAuthorizationCode()) {
            await this.authenticationUtilities.exchangeAuthCodeForToken();
          } else {
            let authenticationPromise = await this.authService.startAuthentication();
            await this.authenticationUtilities.exchangeAuthCodeForToken();
            return authenticationPromise
          }
        }
        return throwError(error);
      })
    );
  }
}
