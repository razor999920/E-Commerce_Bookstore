import React, { FC } from "react"
import { RouteComponentProps } from "@reach/router"

interface ProductDetailsProps extends RouteComponentProps {
  id?: string
}

const ProductDetails: FC<ProductDetailsProps> = props => {
  return (
    <div>
      Product details for: {props.id}
    </div>
  )
}

export default ProductDetails
