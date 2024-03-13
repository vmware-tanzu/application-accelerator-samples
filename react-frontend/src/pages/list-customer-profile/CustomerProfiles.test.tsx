import {render} from "@testing-library/react";
import CustomerProfilesTable from "./CustomerProfilesTable";
import CustomerProfiles from "./CustomerProfiles";
import { useFetchCustomerProfiles } from "./fetchCustomerProfiles";
import { CustomerProfile } from "../../api/CustomerProfileDomain";

// Solves TypeScript Errors
const mockedUseFetchCustomerProfiles = useFetchCustomerProfiles as jest.Mock<any>;

// Mock the module
vitest.mock("./fetchCustomerProfiles");

describe("CustomerProfiles", () => {

  beforeEach(() => {
    mockedUseFetchCustomerProfiles.mockImplementation(() => ({ isLoading: true }));
  });

  afterEach(() => {
    vitest.clearAllMocks();
  });

  it("given the data is loading it should render loading", () => {
    const { getByText } = render(<CustomerProfiles />);

    expect(getByText(/Loading.../i)).toBeVisible();
  });

  it("given an error has occurred while retrieving customer profiles, it should displays error message", () => {
    mockedUseFetchCustomerProfiles.mockImplementation(() => ({
      isLoading: false,
      isError: true
    }));
    const { getByText, queryByText } = render(<CustomerProfiles />);

    expect(queryByText(/Loading.../i)).toBeFalsy();
    expect(getByText(/Error:/i)).toBeVisible();
  });

  it("given customer profiles has been retrieved, it should display those", () => {
    mockedUseFetchCustomerProfiles.mockImplementation(() => ({
      isLoading: false,
      isError: false,
      data: [new CustomerProfile("id", "Joe", "Doe", "joe.doe@example.com")]
    }));
    const { getByText, queryByText } = render(<CustomerProfiles />);

    expect(queryByText(/Loading.../i)).toBeFalsy();
    expect(getByText(/joe.doe@example.com/i)).toBeVisible();
  });
});
