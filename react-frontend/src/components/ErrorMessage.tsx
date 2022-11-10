import React from "react"

export type ErrorMessageProps = {
    error: string;
}

function ErrorMessage({ error }: ErrorMessageProps) {
    const hasError = error.length > 0
    if (hasError) {
        return (
          <div data-testid="error-message"><span className="error">{error}</span></div>
        )
    }
    return null
}

export default ErrorMessage