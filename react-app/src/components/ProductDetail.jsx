import { PropTypes } from "prop-types";

export const ProductDetail = ({ handlerSelected, handlerRemove, product = {} }) => {
    return (
        <tr>
            <td>{product.name}</td>
            <td>{product.description}</td>
            <td>{product.price}</td>
            <td>
                <button onClick={() => handlerSelected(product)}>
                    update
                </button>
            </td>
            <td>
                <button onClick={() => handlerRemove(product.name)}>
                    remove
                </button>
            </td>
        </tr>
    )
}

ProductDetail.propTypes = {
    product: PropTypes.object.isRequired,
    handlerRemove: PropTypes.func.isRequired,
    handlerSelected: PropTypes.func.isRequired
}
