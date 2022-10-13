import { Component } from '@angular/core';
import {CustomerProfile, CustomerProfileService} from "./customer-profile.service";
import {Router} from "@angular/router";

@Component({
  selector: 'create-customer-profile',
  templateUrl: './create-customer-profile.component.html',
  styleUrls: ['./create-customer-profile.component.css']
})
export class CreateCustomerProfileComponent {

  customerProfile: CustomerProfile = new CustomerProfile('', '', '', '');

  constructor(
    private router: Router,
    private customerProfileService: CustomerProfileService
  ) {
  }

  onSubmit() {
    this.customerProfileService.createCustomerProfile(this.customerProfile)
    this.router.navigate(['/customer-profiles/list']);
  }
}
