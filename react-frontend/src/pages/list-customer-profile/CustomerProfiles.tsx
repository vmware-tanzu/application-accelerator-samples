import React from "react";
import PageHeader from "../../components/PageHeader";
import CustomerProfilesTable from "./CustomerProfilesTable";
import { useFetchCustomerProfiles } from "./fetchCustomerProfiles";

function CustomerProfiles() {

  const { isLoading, isError, data } = useFetchCustomerProfiles()

  if (isLoading) {
    return <span>Loading ...</span>
  }

  if (isError) {
    return <span>Error: </span>
  }

  return (
    <>
      <PageHeader text={"Customer Profiles"} />
      <main>
        <CustomerProfilesTable profiles={data} />
      </main>
    </>
  )
}

export default CustomerProfiles;
