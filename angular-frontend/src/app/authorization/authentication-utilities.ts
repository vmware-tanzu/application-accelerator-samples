import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthorizationService } from './authorization.service';
import {HttpRequest} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationUtilities {
  constructor(private route: ActivatedRoute, private authService: AuthorizationService) {}

  async exchangeAuthCodeForToken(request: HttpRequest<any> | null): Promise<void> {
    const authCode = this.route.snapshot.queryParams['code'];

    await this.authService.getToken(authCode, request);
  }

  thereIsAnAuthorizationCode(): boolean {
    return this.route.snapshot.queryParams['code'] != undefined;
  }
}
