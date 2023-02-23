import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from "@angular/material/button";
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from "@angular/material/table";
import {CustomerProfileModule} from './customer-profile/customer-profile.module';
import { UserProfileComponent } from './user-profile/user-profile.component';
import {AuthorizationService} from './authorization/authorization.service';
import {AuthInterceptor} from './authorization/authInterceptor';
import {UserProfileService} from "./user-profile/user-profile.service";
import {UserProfileModule} from "./user-profile/user-profile.module";


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
    AuthorizationService,
    UserProfileService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
  bootstrap: [HomeComponent]
})
export class AppModule {
}
