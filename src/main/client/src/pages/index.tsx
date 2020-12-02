import React from "react"
import { ErrorBoundary } from "react-error-boundary"
import { Route, Switch, withRouter } from "react-router-dom"
import ErrorFallback from "./common/Error/ErrorFallback"
import { Home } from "./Home"
import { NotFound } from "./common/NotFound"

const Routes: React.FC = () => {
  const errorHandler = (e: Error) => console.log(e)

  return (
    <ErrorBoundary onError={errorHandler} FallbackComponent={ErrorFallback}>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route component={NotFound} />
      </Switch>
    </ErrorBoundary>
  )
}

export const Pages = withRouter(Routes)
