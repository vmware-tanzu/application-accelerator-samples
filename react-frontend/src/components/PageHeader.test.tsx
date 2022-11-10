import {render} from "@testing-library/react";
import PageHeader from "./PageHeader";

describe("PageHeader", () => {

  it("should render passed text", () => {
    const PageHeaderComponent = render(
      <PageHeader text="My Page Header"/>
    );

    const headerLabel = PageHeaderComponent.queryByRole("heading")
    expect(headerLabel).toBeTruthy()
    expect(headerLabel).toContainHTML("My Page Header")
    expect(headerLabel).toHaveClass("page-header")
  });
});
