import {CustomerProfile} from "./CustomerProfileDomain";

describe('CustomerProfile', () => {

  it('valid if email is provided', () => {
    const profile = new CustomerProfile(null, null, null, 'joe.doe@examle.com')
    expect(profile.valid()).toEqual(true)
  });

  it('invalid if email is not provided', () => {
    const profile = new CustomerProfile('id', 'Joe', 'Doe', null)
    expect(profile.valid()).toEqual(false)
  });
});
