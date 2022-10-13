import { Component, OnInit } from '@angular/core';
import {CustomerProfile, CustomerProfileService} from "./customer-profile.service";
import {catchError, Observable, of, startWith} from "rxjs";

@Component({
  selector: 'list-customer-profiles',
  templateUrl: './list-customer-profiles.component.html',
  styleUrls: ['./list-customer-profiles.component.css']
})
export class ListCustomerProfilesComponent implements OnInit {

  columnsToDisplay = ['id', 'firstName', 'lastName', 'email'];
  dataSource: Observable<CustomerProfile[]> = of([]);

  couldNotRetrieve: Boolean = false;

  constructor(private customerProfileService: CustomerProfileService) {

  }

  ngOnInit(): void {
    this.dataSource =
      this.customerProfileService.getCustomerProfiles()
        .pipe(
          startWith([]),
          catchError(err => {
            // Wait a turn because couldNotRetrieve already set once this turn
            setTimeout(() => this.couldNotRetrieve = true);
            return [];
          })
        );
  }
}
