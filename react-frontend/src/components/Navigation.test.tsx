import {render} from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";
import Navigation from "./Navigation";

describe("Navigation", () => {

    it("should render navigation menu", () => {
        const Component = render(
            <MemoryRouter>
            <Navigation links={
                [
                    {uri: "/home", label: "Home"},
                ]
            }
            />
            </MemoryRouter>
        );

        const navElement = Component.queryByRole("navigation");
        expect(navElement).toBeTruthy();

        const links = Component.queryAllByRole("link");
        expect(links).toHaveLength(1)

        const link = links[0]
        expect(link).toContainHTML("href=\"/home\"")
        expect(link).toContainHTML("Home")
    });
});