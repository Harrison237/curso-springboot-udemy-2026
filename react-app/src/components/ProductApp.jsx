import { PropTypes } from "prop-types";
import { useState } from "react";
import { listProduct } from "../services/ProductService";
import { ProductGrid } from "./ProductGrid";
import { ProductForm } from "./ProductForm";

export const ProductApp = ({ title }) => {
    const [products, setProducts] = useState(() => listProduct());

    const [productSelected, setProductSelected] = useState({
        name: '',
        description: '',
        price: NaN
    });

    const handlerAddProduct = (product) => {
        console.log(product)
        setProducts([...products, { ...product }])
    }
    const handlerRemoveProduct = (name) => {
        console.log(name)
        setProducts(products.filter(p => p.name !== name))
    }
    const handlerProductSelected = (product) => {
        setProductSelected({ ...product })
    }

    return (
        <>
            <div>
                <h1>{title}</h1>
                <div>
                    <ProductForm handlerAdd={handlerAddProduct} productSelected={productSelected} />
                </div>
                <div>
                    <ProductGrid products={products} handlerRemove={handlerRemoveProduct} handlerSelected={handlerProductSelected} />
                </div>
            </div>
        </>
    )
};
ProductApp.propType = {
    title: PropTypes.string.isRequired
}