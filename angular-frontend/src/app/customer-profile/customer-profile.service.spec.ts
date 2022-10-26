import {CustomerProfile, CustomerProfileService} from "./customer-profile.service";
import {HttpClient} from "@angular/common/http";
import {of} from "rxjs";

describe('CustomerProfileService', () => {
  let subject: CustomerProfileService;
  let httpClient: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    httpClient = jasmine.createSpyObj('HttpClient', ['get', 'post']);
    subject = new CustomerProfileService(httpClient)
  });

  it('#getCustomerProfiles should return stubbed value from a spy', () => {
    const customerProfiles = [
      {id: 'piet-01', firstName: 'Piet', lastName: 'Hein', email: 'piet.hein@gmail.com'}
    ];
    httpClient.get.and.returnValue(of(customerProfiles));

    subject.getCustomerProfiles().subscribe({
      next: result => {
        expect(result)
          .withContext('service returned the retrieved values')
          .toEqual([new CustomerProfile('piet-01', 'Piet', 'Hein', 'piet.hein@gmail.com')]);
      },
      error: fail
    });

    expect(httpClient.get).toHaveBeenCalledOnceWith('/api/customer-profiles/')
  });

  it('#createCustomerProfile should call HttpClient', () => {

    httpClient.post.and.returnValue(of());

    subject.createCustomerProfile(new CustomerProfile('', 'John', 'Smith', 'john.smith@example.com'))

    expect(httpClient.post)
      .toHaveBeenCalledOnceWith(
        '/api/customer-profiles/',
        {id:'', firstName:'John', lastName: 'Smith', email:'john.smith@example.com'})
  });
});
