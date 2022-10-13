import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from "@angular/common/http";
import { ListCustomerProfilesComponent } from "./list-customer-profiles.component";
import { MatTableModule } from "@angular/material/table";
import {CreateCustomerProfileComponent} from "./create-customer-profile.component";
import {CustomerProfileService} from "./customer-profile.service";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    CreateCustomerProfileComponent,
    ListCustomerProfilesComponent
  ],
  exports: [
    ListCustomerProfilesComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    CustomerProfileService
  ]
})
export class CustomerProfileModule { }
