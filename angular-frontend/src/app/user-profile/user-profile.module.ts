import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {UserProfileComponent} from "./user-profile.component";
import {UserProfileService} from "./user-profile.service";

@NgModule({
  declarations: [
    UserProfileComponent
  ],
  exports: [
    UserProfileComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
  ],
  providers: [
    UserProfileService
  ]
})
export class UserProfileModule { }
