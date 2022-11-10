import { Outlet } from "react-router-dom";
import Navigation from './components/Navigation';

export default function Layout() {
    return (
        <>
            <Navigation links={
                [
                    {uri: "customer-profiles/list", label: "List all"},
                    {uri: "customer-profiles/create", label: "Create"},
                ]
            }/>
          <main>
            <Outlet />
          </main>
        </>
    );
}