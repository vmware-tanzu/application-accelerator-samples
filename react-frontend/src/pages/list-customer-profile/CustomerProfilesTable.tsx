import React from "react";
import {CustomerProfile} from "../../api/CustomerProfileDomain";
import "./CustomerProfilesTable.css"

export type CustomerProfilesProps = {
  profiles: CustomerProfile[] | undefined;
}

function CustomerProfilesTable({
  profiles,
}: CustomerProfilesProps) {
  return (
    <table className="profiles-table">
      <thead>
        <tr>
          <th>#</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email</th>
        </tr>
      </thead>
      <tbody>
      {profiles && profiles.map((profile) => {
        return (
          <tr key={profile.id}>
            <td>{profile.id}</td>
            <td>{profile.firstName}</td>
            <td>{profile.lastName}</td>
            <td>{profile.email}</td>
          </tr>
        )}
      )}
      </tbody>
    </table>
  )
}

export default CustomerProfilesTable
