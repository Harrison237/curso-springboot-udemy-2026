import { PropTypes } from "prop-types";
import { useEffect, useState } from "react";
import { create, findAll, remove, update } from "../services/ProductService";
import { ProductGrid } from "./ProductGrid";
import { ProductForm } from "./ProductForm";

export const ProductApp = ({ title }) => {
    const [products, setProducts] = useState([]);

    const getProducts = async () => {
        const result = await findAll();
        console.log(result)
        setProducts(result.data._embedded.products);
    }

    useEffect(() => {
        getProducts();
    }, [])

    const [productSelected, setProductSelected] = useState({
        id: 0,
        name: '',
        description: '',
        price: ''
    });

    const handlerAddProduct = async (product) => {
        console.log(product)

        if (product.id > 0) {
            const response = await update(product);
            setProducts(products.map(p => p.id === response.data.id ? { ...response.data } : p))
        }
        else {
            const response = await create(product);
            setProducts([...products, { ...response.data }])
        }
    }
    const handlerRemoveProduct = async (id) => {
        console.log(id)
        await remove(id);
        setProducts(products.filter(p => p.id !== id))
    }
    const handlerProductSelected = (product) => {
        setProductSelected({ ...product })
    }

    return (
        <>
            <div className="container my-4">
                <h2>{title}</h2>
                <div className="row">
                    <div className="col">
                        <ProductForm handlerAdd={handlerAddProduct} productSelected={productSelected} />
                    </div>
                    <div className="col">
                        {
                            products.length !== 0 ? <ProductGrid products={products} handlerRemove={handlerRemoveProduct} handlerSelected={handlerProductSelected} />
                                : <div className="alert alert-warning">No hay productos en el sistema!</div>
                        }

                    </div>
                </div>
            </div>
        </>
    )
};
ProductApp.propType = {
    title: PropTypes.string.isRequired
}