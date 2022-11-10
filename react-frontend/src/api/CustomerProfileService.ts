import {CustomerProfile} from "./CustomerProfileDomain";
import axios from "axios";

interface CustomerProfileDTO {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
}

export class CustomerProfileService {

  constructor(private baseUrl: string = '') {
  }

  getCustomerProfiles(): Promise<CustomerProfile[]> {
    const url = `${this.baseUrl}/api/customer-profiles/`
    return axios.get<CustomerProfileDTO[]>(
      url,
      {
        headers: {'Accept': 'application/json'}
      }
    ).then(response => {
      return response.data.map(dto => new CustomerProfile(dto.id, dto.firstName, dto.lastName, dto.email))
    })
  }

  createCustomerProfile(profile: CustomerProfile): Promise<CustomerProfile> {
    const url = `${this.baseUrl}/api/customer-profiles/`
    return axios.post<CustomerProfileDTO>(
      url,
      {
        'firstName': profile.firstName,
        'lastName': profile.lastName,
        'email': profile.email
      },
      {
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
      }
    ).then(response => {
      const dto = response.data
      return new CustomerProfile(dto.id, dto.firstName, dto.lastName, dto.email)
    })
  }
}
