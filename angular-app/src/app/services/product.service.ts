import { inject, Injectable } from '@angular/core';
import { Product } from '../models/product';
import { map, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private readonly http: HttpClient = inject(HttpClient);
  private readonly baseUrl: string = 'http://localhost:8080/products';

  private readonly products: Array<Product> = [
    {
      id: 1,
      name: 'Mesa comedor',
      description: 'Excelente mesa para el comedor',
      price: 700
    },
    {
      id: 2,
      name: 'Teclado mecánico',
      description: 'Excelente teclado para escribir',
      price: 500
    }
  ]

  constructor() { }

  public findAll(): Observable<Array<Product>> {
    // return of(this.products);
    return this.http.get<Array<Product>>(this.baseUrl).pipe(
      map((received: any) => (received._embedded.products) as Array<Product>)
    );
  }

  public create(product: Product): Observable<Product> {
    return this.http.post<Product>(this.baseUrl, { ...product });
  }

  public update(product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.baseUrl}/${product.id}`, { ...product });
  }

  public remove(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
