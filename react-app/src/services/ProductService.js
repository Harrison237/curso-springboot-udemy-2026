import axios from "axios";

const initProducts = [
    {
        id: 1,
        name: 'Monitor Samsung 65',
        price: 500,
        description: 'El monitor es increíble!'
    },
    {
        id: 2,
        name: 'IPhone 14',
        price: 500,
        description: 'El teléfono es basura!'
    }
];

const baseUrl = 'http://localhost:8080/products';

export const listProduct = () => {
    return initProducts;
}

export const findAll = async () => {
    try {
        const response = await axios.get(baseUrl);
        return response;
    } catch (error) {
        console.log(error);
        return null;
    }
}

export const create = async ({ name, description, price }) => {
    try {
        const response = await axios.post(baseUrl, {
            name,
            description,
            price
        });
        return response;
    } catch (error) {
        console.log(error);
        return undefined;
    }
}

export const update = async ({ id, name, description, price }) => {
    try {
        const response = await axios.put(`${baseUrl}/${id}`, {
            name,
            description,
            price
        });
        return response;
    } catch (error) {
        console.log(error);
        return undefined;
    }
}

export const remove = async (id) => {
    try {
        await axios.delete(`${baseUrl}/${id}`);
    } catch (error) {
        console.log(error);
    }
}
