import { Injectable } from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";

export class CustomerProfile {
  constructor(
    public id: string | null,
    public firstName: string | null,
    public lastName: string | null,
    public email: string | null
    ) {
  }

  valid(): boolean {
    return (this.email !== null && this.email !== '')
  }
}

interface CustomerProfileDTO {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
}

@Injectable()
export class CustomerProfileService {

  private baseUrl = '/api/customer-profiles/';

  constructor(private http: HttpClient) {
  }

  getCustomerProfiles(): Observable<CustomerProfile[]> {
    return this.http.get<CustomerProfileDTO[]>(this.baseUrl)
      .pipe(map(dtos => {
        return dtos.map(dto => new CustomerProfile(dto.id, dto.firstName, dto.lastName, dto.email))
      }));
  }

  createCustomerProfile(customerProfile: CustomerProfile) {
    this.http.post(
      this.baseUrl,
      {
        id: customerProfile.id,
        firstName: customerProfile.firstName,
        lastName: customerProfile.lastName,
        email: customerProfile.email
      }
    ).subscribe();
  }
}
