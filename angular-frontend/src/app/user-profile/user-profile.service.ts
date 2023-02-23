import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import {AuthorizationService} from "../authorization/authorization.service";
import {AuthenticationUtilities} from "../authorization/authentication-utilities";

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {
  private jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private authService: AuthorizationService, private authenticationUtilities: AuthenticationUtilities) {}

  public async getSub(): Promise<string | undefined> {
    const token = this.authService.getAccessToken();
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken?.sub;
    } else {
      await this.checkAccessToken();
      return undefined;
    }
  }

  public async getEmail(): Promise<string | undefined> {
    const token = this.authService.getAccessToken();
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken?.email;
    } else {
      await this.checkAccessToken();
      return undefined;
    }
  }

  private async checkAccessToken(): Promise<void> {
    if (!this.authService.isAuthenticated()) {
      if (!this.authenticationUtilities.thereIsAnAuthorizationCode()) {
        await this.authService.startAuthentication();
      }
      await this.authenticationUtilities.exchangeAuthCodeForToken();
    }
  }
}
