import React from "react"

const ErrorFallback: React.FC = () => {
  return (
    <div style={{ display: "flex", alignItems: "center" }}>
      There has been an error. Check console for more details.
    </div>
  )
}

export default ErrorFallback
