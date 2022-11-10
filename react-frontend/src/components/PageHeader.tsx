import React from "react"
import "./PageHeader.css"

export type PageHeaderProps = {
  text: string;
}

function PageHeader({
  text,
}: PageHeaderProps) {
  return (
    <header>
      <h1 className="page-header">
        {text}
      </h1>
    </header>
  )
}

export default PageHeader
