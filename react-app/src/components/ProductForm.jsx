import { useEffect, useState } from "react";

const initialDataForm = {
    name: '',
    description: '',
    price: ''
}
export const ProductForm = ({ handlerAdd, productSelected }) => {

    const [form, setForm] = useState(initialDataForm);

    const { name, description, price } = form;

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
                    style={{ 'marginBottom': '4px' }}
                    name="name"
                    value={name}
                    onChange={(event) => setForm({ ...form, name: event.target.value })} />
            </div>
            <div>
                <input placeholder="Description"
                    style={{ 'marginBottom': '4px' }}
                    name="description"
                    value={description}
                    onChange={(event) => setForm({ ...form, description: event.target.value })} />
            </div>
            <div>
                <input placeholder="Price"
                    style={{ 'marginBottom': '4px' }}
                    name="price"
                    value={price}
                    type="number"
                    onChange={(event) => setForm({ ...form, price: event.target.value })} />
            </div>
            <div>
                <button type="submit">Create</button>
            </div>
        </form>
    );
}