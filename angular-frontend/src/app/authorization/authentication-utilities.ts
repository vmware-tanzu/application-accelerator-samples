import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthorizationService } from './authorization.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationUtilities {
  constructor(private route: ActivatedRoute, private authService: AuthorizationService) {}

  async exchangeAuthCodeForToken(): Promise<void> {
    const authCode = this.route.snapshot.queryParams['code'];

    await this.authService.getToken(authCode, this.authService.getCodeVerifier(), this.authService.getRedirectUri());
  }

  thereIsAnAuthorizationCode(): boolean {
    return this.route.snapshot.queryParams['code'] != undefined;
  }
}
