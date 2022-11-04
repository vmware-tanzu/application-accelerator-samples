import React, {useState} from "react";
import { CustomerProfile } from "../../api/CustomerProfileDomain";
import './CreateCustomerProfilePage.css';
import ErrorMessage from "../../components/ErrorMessage";

export type CreateCustomerProfileFormProps = {
  onSubmit: (customerProfile: CustomerProfile) => void;
  error: string
}

function CreateCustomerProfileForm(props: CreateCustomerProfileFormProps) {

  const [profile, setProfile] = useState<CustomerProfile>(new CustomerProfile(null, null, null, null))
  const [profileInvalid, setProfileInvalid] = useState<boolean>(!profile.valid())

  function handleFirstNameChange(event: React.FormEvent<HTMLInputElement>) {
    profile.firstName = event.currentTarget.value
    setProfile(profile)
  }

  function handleLastNameChange(event: React.FormEvent<HTMLInputElement>) {
    profile.lastName = event.currentTarget.value
    setProfile(profile)
  }

  function handleEmailChange(event: React.FormEvent<HTMLInputElement>) {
    profile.email = event.currentTarget.value
    setProfileInvalid(!profile.valid())
    setProfile(profile)
  }

  async function handleSubmit(event: React.SyntheticEvent) {
    event.preventDefault();
    props.onSubmit(profile)
  }

  return (
    <form name="create-customer-profile" onSubmit={handleSubmit}>
      <ErrorMessage error={props.error} />
      <div className="row">
        <div className="label">
          <label htmlFor="firstName">First Name</label>
        </div>
        <input className="input" name="firstName" id="firstName" type="text" onChange={handleFirstNameChange} placeholder="John" data-testid="first-name"/>
      </div>

      <div className="row">
        <label className="label" htmlFor="lastName">Last Name</label>
        <input className="input" name="lastName" id="lastName" type="text" onChange={handleLastNameChange} placeholder="Smith" data-testid="last-name"/>
      </div>

      <div className="row">
        <label className="label" htmlFor="email">E-mail</label>
        <input className="input" name="email" id="email" type="text" onChange={handleEmailChange} placeholder="john.smith@example.com" data-testid="email"/>

        {profileInvalid &&
          <span className="error">Email must be provided!</span>
        }
      </div>

      <div className="row">
        <input type="submit" value="Create" disabled={profileInvalid} className="button"/>
      </div>
    </form>
  )
}

export default CreateCustomerProfileForm;
