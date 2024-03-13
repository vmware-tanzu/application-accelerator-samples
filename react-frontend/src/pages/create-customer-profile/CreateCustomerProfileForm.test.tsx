import {render} from "@testing-library/react";
import userEvent from '@testing-library/user-event';
import CreateCustomerProfileForm from "./CreateCustomerProfileForm";
import { CustomerProfile } from "../../api/CustomerProfileDomain";

const onSubmitFunction = vitest.fn()

describe("CreateCustomerProfileForm", () => {

  let firstNameElement: HTMLElement | null;
  let lastNameElement: HTMLElement | null;
  let emailElement: HTMLElement | null;
  let submitButton: HTMLElement | null;

  const setup = () => {
    const Form = render(<CreateCustomerProfileForm onSubmit={onSubmitFunction} error="" />);

    firstNameElement = Form.queryByTestId("first-name");
    lastNameElement = Form.queryByTestId("last-name");
    emailElement = Form.queryByTestId("email");

    submitButton = Form.getByRole("button");
  }

  afterEach(() => {
    vitest.clearAllMocks();
  });

  it("it should render the form", () => {
    setup();

    expect(firstNameElement).toBeTruthy();

    expect(lastNameElement).toBeTruthy();

    expect(emailElement).toBeTruthy();

    expect(submitButton).toBeTruthy();
  });

  it("upon submitting the form after filling in the value it should delegate the creation towards the onSubmit function", async() => {
    setup();

    await userType(firstNameElement!, "John");
    await userType(lastNameElement!, "Doe");
    await userType(emailElement!, "john.doe@example.com");

    expect(firstNameElement).toHaveValue("John");

    await userClick(submitButton!);
    expect(onSubmitFunction).toHaveBeenCalledWith(new CustomerProfile(null, "John", "Doe", "john.doe@example.com"));
  })

  it("upon not filling in the e-mail the submit button needs to be disabled", async() => {
    setup();

    expect(submitButton).toBeDisabled()

    await userType(firstNameElement!, "John");
    await userType(lastNameElement!, "Doe");
    await userType(emailElement!, "john.doe@example.com");

    expect(submitButton).toBeEnabled()

    await userType(emailElement!, "");

    expect(submitButton).toBeDisabled()
  })

  async function userType(element: HTMLElement, value: string) {
    const user = userEvent.setup();
    await user.clear(element);
    if (value !== "") {
      await user.type(element, value);
    }
  }

  async function userClick(element: HTMLElement) {
    const user = userEvent.setup();
    await user.click(element);
  }
});