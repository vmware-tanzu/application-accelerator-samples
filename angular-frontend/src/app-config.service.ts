import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {firstValueFrom} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

  private appConfig: any;

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

    return this.appConfig.authority
  }

  getClientId(): string {
    if (!this.appConfig) {
      throw Error('Config file not loaded!');
    }

    return this.appConfig.clientId
  }

  getScope(): string[] {
    if (!this.appConfig) {
      throw Error('Config file not loaded!');
    }

    return this.appConfig.scope
  }
}
