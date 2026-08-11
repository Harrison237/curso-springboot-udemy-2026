import { PropTypes } from "prop-types";

export const ProductDetail = ({ handlerSelected, handlerRemove, product = {} }) => {
    return (
        <tr>
            <td>{product.name}</td>
            <td>{product.description}</td>
            <td>{product.price}</td>
            <td>
                <button className="btn btn-secondary btn-sm" onClick={() => handlerSelected(product)}>
                    update
                </button>
            </td>
            <td>
                <button className="btn btn-danger btn-sm" onClick={() => handlerRemove(product.id)}>
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
