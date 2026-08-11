import { Component, inject, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import { FormComponent } from '../form/form.component';

@Component({
  selector: 'app-product',
  imports: [FormComponent],
  standalone: true,
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit {
  private readonly service: ProductService = inject(ProductService);

  protected products: Array<Product> = [];
  protected productSelected: Product = new Product();

  ngOnInit(): void {
    this.service.findAll().subscribe(products => this.products = products);
  }

  protected handleProduct(product: Product): void {
    if (product.id > 0) {
      this.updateProduct(product)
    } else {
      this.addProduct(product);
    }

    this.productSelected = new Product();
  }

  protected onUpdateProduct(productRow: Product): void {
    this.productSelected = { ...productRow };
  }

  protected onRemoveProduct(id: number): void {
    this.service.remove(id).subscribe(() => this.products = this.products.filter(p => p.id !== id));
  }

  private addProduct(product: Product): void {
    this.service.create(product).subscribe(newProduct => this.products = [...this.products, { ...newProduct }]);
  }

  private updateProduct(product: Product): void {
    this.service.update(product).subscribe(updatedProduct => this.products = this.products.map(p => p.id == updatedProduct.id ? { ...updatedProduct } : p));
  }
}
