import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {firstValueFrom} from "rxjs";
import {AppConfig} from "./configTypes";

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

  private appConfig?: AppConfig;

  constructor(private http: HttpClient) { }

  loadAppConfig(): Promise<any> {
    return firstValueFrom(this.http.get('assets/auth.conf.json'))
      .then(data => {
        this.appConfig = data;
      });
  }

  getAuthority(): string {
    if (!this.appConfig) {
      throw Error('Config file not loaded!');
    }

    if (!this.appConfig.authority) {
      throw Error('Authority not provided');
    }
    return this.appConfig.authority
  }

  getClientId(): string {
    if (!this.appConfig) {
      throw Error('Config file not loaded!');
    }

    if (!this.appConfig.clientId) {
      throw Error('ClientId not provided');
    }
    return this.appConfig.clientId
  }

  getScope(): string[] {
    if (!this.appConfig) {
      throw Error('Config file not loaded!');
    }

    if (!this.appConfig.scope) {
      throw Error('Scope not provided');
    }
    return this.appConfig.scope
  }
}
