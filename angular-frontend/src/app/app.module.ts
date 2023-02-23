import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from "@angular/material/button";
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CustomerProfileModule} from './customer-profile/customer-profile.module';
import {AuthorizationService} from './authorization/authorization.service';
import {AuthInterceptor} from './authorization/authInterceptor';
import {UserProfileService} from "./user-profile/user-profile.service";
import {UserProfileModule} from "./user-profile/user-profile.module";
import {AppConfigService} from "./app-config.service";


@NgModule({
  declarations: [
    HomeComponent,
    TopBarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CustomerProfileModule,
    UserProfileModule,
    MatToolbarModule,
    MatButtonModule,
  ],
  providers: [
    // StartSSOProviders
    {
      provide: APP_INITIALIZER,
      multi: true,
      deps: [AppConfigService],
      useFactory: (appConfigService: AppConfigService) => {
        return () => {
          //Make sure to return a promise!
          return appConfigService.loadAppConfig();
        };
      }
    },
    AuthorizationService,
    UserProfileService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    // EndSSOProviders
  ],
  bootstrap: [HomeComponent]
})
export class AppModule {
}
