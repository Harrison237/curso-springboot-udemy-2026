import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
// import './index.css'
// eslint-disable-next-line no-unused-vars
import { App } from './App.jsx'
import { ProductApp } from './components/ProductApp.jsx';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ProductApp title={'Lista de productos!'} />
  </StrictMode>,
)
