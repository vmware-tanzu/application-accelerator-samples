import {MatchersV3, PactV3} from "@pact-foundation/pact";
import path from "path";
import {CustomerProfileService} from "./CustomerProfileService";
import {CustomerProfile} from "./CustomerProfileDomain";

const provider = new PactV3({
  dir: path.resolve(process.cwd(), 'pacts'),
  consumer: 'CustomerProfileConsumer',
  provider: 'CustomerProfileProvider'
});

const customerProfile = { id: "1", firstName: "Joe", lastName: "Doe", email: "joe.doe@example.com" };

describe('Customer API Client', () => {

  describe('GET /api/customer-profiles', () => {

    it('returns an HTTP 200 and a list of customer profiles', async() => {
      provider
        .given('There is a list of customer profiles')
        .uponReceiving('a request for all customer profiles')
        .withRequest({
          method: 'GET',
          path: '/api/customer-profiles/',
          headers: {Accept: 'application/json'},
        })
        .willRespondWith({
          status: 200,
          headers: {'Content-Type': 'application/json'},
          body: MatchersV3.eachLike(customerProfile),
        });

      return await provider.executeTest((mockserver) => {
        const customerProfileService = new CustomerProfileService(mockserver.url)
        return customerProfileService.getCustomerProfiles()
          .then(response => expect(response).toEqual([customerProfile]))
      });
    });
  });

  describe('POST /api/customer-profiles', () => {

    it('returns an HTTP 201 and a created customer profile', async() => {
      provider
        // .given('There is a list of customer profiles')
        .uponReceiving('a request to create a new customer profile')
        .withRequest({
          method: 'POST',
          path: '/api/customer-profiles/',
          headers: {'Accept': 'application/json'},
          body: MatchersV3.like({
            'firstName':'Joe',
            'lastName': 'Doe',
            'email': 'joe.doe@example.com'
          }),
          contentType: 'application/json'
        })
        .willRespondWith({
          status: 201,
          headers: {
            'Content-Type': 'application/json',
            'Location': MatchersV3.url(['/api/customer-profiles/', MatchersV3.uuid()])
          },
          body: MatchersV3.like(customerProfile),
        });

      return await provider.executeTest((mockserver) => {
        const customerProfileService = new CustomerProfileService(mockserver.url)
        return customerProfileService.createCustomerProfile(new CustomerProfile(null, 'Joe', 'Doe', 'joe.doe@example.com'))
          .then(response => expect(response).toEqual(customerProfile))
      });
    });
  });
});
