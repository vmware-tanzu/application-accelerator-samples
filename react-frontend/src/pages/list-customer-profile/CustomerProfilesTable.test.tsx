import {queryAllByRole, render} from "@testing-library/react";
import CustomerProfilesTable from "./CustomerProfilesTable";
import {CustomerProfile} from "../../api/CustomerProfileDomain";

describe("CustomerProfilesTable", () => {

  it("should render empty data set", () => {
    const CustomerProfilesTableComponent = render(
      <CustomerProfilesTable profiles={[]}/>
    );

    const table = CustomerProfilesTableComponent.queryByRole("table")
    expect(table).toBeTruthy()

    const rows = queryAllByRole(table!, "row")
    expect(rows).toHaveLength(1)

    verifyHeader(rows);
  });

  it("should render provided data set", () => {
    const profile = new CustomerProfile("id", "Joe", "Doe", "joe.doe@example.com")
    const CustomerProfilesTableComponent = render(
      <CustomerProfilesTable profiles={[profile]}/>
    );

    const table = CustomerProfilesTableComponent.queryByRole("table")
    expect(table).toBeTruthy()

    const rows = queryAllByRole(table!, "row")
    expect(rows).toHaveLength(2)

    verifyHeader(rows);

    const cells = rows[1].children
    expect(cells).toHaveLength(4)
    expect(cells[0]).toContainHTML("id")
    expect(cells[1]).toContainHTML("Joe")
    expect(cells[2]).toContainHTML("Doe")
    expect(cells[3]).toContainHTML("joe.doe@example.com")
  });

  function verifyHeader(rows: HTMLElement[]) {
    const headers = rows[0].children
    expect(headers).toHaveLength(4)
    expect(headers[0]).toContainHTML("#")
    expect(headers[1]).toContainHTML("First Name")
    expect(headers[2]).toContainHTML("Last Name")
    expect(headers[3]).toContainHTML("Email")
  }
});
