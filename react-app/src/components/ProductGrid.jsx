import { PropTypes } from "prop-types";
import { ProductDetail } from "./ProductDetail";

export const ProductGrid = ({ products = [], handlerRemove, handlerSelected }) => {
    return (
        <table>
            <thead>
                <tr>
                    <th>name</th>
                    <th>description</th>
                    <th>price</th>
                    <th>update</th>
                    <th>remove</th>
                </tr>
            </thead>
            <tbody>
                {products.map(p => {
                    return <ProductDetail handlerSelected={handlerSelected} handlerRemove={handlerRemove} product={p} key={p.name} />
                })}
            </tbody>
        </table>
    )
}

ProductGrid.propTypes = {
    products: PropTypes.array.isRequired,
    handlerRemove: PropTypes.func.isRequired,
    handlerSelected: PropTypes.func.isRequired
}
