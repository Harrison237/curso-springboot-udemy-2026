import { useEffect, useState } from "react";

const initialDataForm = {
    id: 0,
    name: '',
    description: '',
    price: ''
}
export const ProductForm = ({ handlerAdd, productSelected }) => {

    const [form, setForm] = useState(initialDataForm);

    const { id, name, description, price } = form;

    useEffect(() => {
        setForm(productSelected)
    }, [productSelected])
    return (
        <form onSubmit={(event) => {
            event.preventDefault();

            if (!name || !description || !price) {
                alert("Debe completar los datos del formulario");
                return;
            }

            handlerAdd(form)
            setForm(initialDataForm);
        }}>
            <div>
                <input placeholder="Name"
                    className="form-control my-3 w-75"
                    name="name"
                    value={name}
                    onChange={(event) => setForm({ ...form, name: event.target.value })} />
            </div>
            <div>
                <input placeholder="Description"
                    className="form-control my-3 w-75"
                    name="description"
                    value={description}
                    onChange={(event) => setForm({ ...form, description: event.target.value })} />
            </div>
            <div>
                <input placeholder="Price"
                    className="form-control my-3 w-75"
                    name="price"
                    value={price}
                    type="number"
                    onChange={(event) => setForm({ ...form, price: event.target.value })} />
            </div>
            <div>
                <button type="submit" className="btn btn-primary">
                    {id > 0 ? 'Update' : 'Save'}
                </button>
            </div>
        </form>
    );
}