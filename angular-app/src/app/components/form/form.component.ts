import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product } from '../../models/product';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'product-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit {
  private readonly initialProduct: Product = {
    id: Number.NaN,
    name: '',
    description: '',
    price: Number.NaN
  }

  @Input() public product!: Product;

  @Output() newProductEvent = new EventEmitter<Product>();

  ngOnInit(): void {
    this.clean((undefined as any));
  }

  protected onSubmit(productForm: NgForm): void {
    if (productForm.valid) {
      this.newProductEvent.emit(this.product);
      console.log(this.product)
    }

    this.resetForm(productForm);
  }

  protected clean(productForm: NgForm): void {
    this.product = { ...this.initialProduct };

    if (productForm)
      this.resetForm(productForm);
  }

  protected isNaN(evaluate: number): boolean {
    return Number.isNaN(evaluate) || evaluate === null || evaluate === undefined;
  }

  private resetForm(form: NgForm) {
    form.reset();
    form.resetForm();
  }
}
