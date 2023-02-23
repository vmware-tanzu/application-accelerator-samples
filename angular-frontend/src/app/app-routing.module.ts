import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateCustomerProfileComponent } from "./customer-profile/create-customer-profile.component";
import { ListCustomerProfilesComponent } from "./customer-profile/list-customer-profiles.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";
import { HomeComponent } from "./home.component";

const routes: Routes = [
  { path: 'customer-profiles/list', component: ListCustomerProfilesComponent },
  { path: 'customer-profiles/create', component: CreateCustomerProfileComponent },
  { path: 'user-profile', component: UserProfileComponent },
  { path: '', redirectTo: '/customer-profiles/list', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
