import {render} from "@testing-library/react";
import {MemoryRouter} from "react-router-dom";
import CreateCustomerProfile from "./CreateCustomerProfilePage";

describe("CreateCustomerProfilePage", () => {

  it("upon entering the page it should render the header and form", () => {

    const PageComponent = render(<MemoryRouter><CreateCustomerProfile /></MemoryRouter>);

    const header = PageComponent.getByRole("banner")
    const form = PageComponent.getByRole("form")

    expect(header).toBeTruthy();
    expect(header).toHaveTextContent("Create Customer Profile");

    expect(form).toBeTruthy();
  });
});