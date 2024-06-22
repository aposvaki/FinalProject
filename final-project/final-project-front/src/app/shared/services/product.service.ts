import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Product } from '../interfaces/product';
import { Observable } from 'rxjs';

const PRODUCT_API_URL = 'http://localhost:8080/api/products';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  http: HttpClient = inject(HttpClient);

  getAllProducts() {
    return this.http.get<Product[]>(`${PRODUCT_API_URL}/?productName=`, {
      headers: {
        Accept: 'application/json'
      }
    }
  )
  }

  addProduct(product: Product) {
    return this.http.post<{ msg: string }>(`${PRODUCT_API_URL}/add`, product);
  }

  deleteProduct(id: string) {
    return this.http.delete<{msg: string}>(`${PRODUCT_API_URL}/${id}`);
  }

  getProductById(id: string) {
    return this.http.get<{product: Product}>(`${PRODUCT_API_URL}/${id}`)
  }

  updateProduct(product: Product) {
    return this.http.put<{msg: string}>(`${PRODUCT_API_URL}/${product.id}`, product);
  }
}
