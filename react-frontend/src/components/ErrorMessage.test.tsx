import {render} from "@testing-library/react";
import ErrorMessage from "./ErrorMessage";

describe("ErrorMessage", () => {
  it("Given no error message, it is not shown", () => {

    const ErrorMessageComponent = render(<ErrorMessage error="" />)
    const errorMessageElement = ErrorMessageComponent.queryByTestId("error-message")

    expect(errorMessageElement).toBeNull()
  })

  it("Given an error message, it is shown", () => {

    const ErrorMessageComponent = render(<ErrorMessage error="some error message" />)
    const errorMessageElement = ErrorMessageComponent.queryByTestId("error-message")

    expect(errorMessageElement).toBeTruthy()
    expect(errorMessageElement).toHaveTextContent("some error message")
  })
});