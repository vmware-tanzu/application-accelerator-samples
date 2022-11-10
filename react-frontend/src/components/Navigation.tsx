import React from "react"
import { Link } from "react-router-dom"
import "./Navigation.css"

export type NavigationLink = {
    uri: string,
    label: string
}

export type NavigationProps = {
    links: NavigationLink[];
}

function Navigation({ links }: NavigationProps) {

    return (
        <nav className="topnav">
            {links.map((link, index) => <Link to={link.uri} key={index}>{link.label}</Link>)}
        </nav>
    )
}

export default Navigation