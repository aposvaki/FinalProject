import { Component, inject } from '@angular/core';
import { CrudNavbarComponent } from '../crud-navbar/crud-navbar.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { ProductService } from 'src/app/shared/services/product.service';
import { Product } from 'src/app/shared/interfaces/product';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-crud-read',
  standalone: true,
  imports: [
    CommonModule,
    CrudNavbarComponent,
    MatCardModule, 
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule, 
  ReactiveFormsModule],
  templateUrl: './crud-read.component.html',
  styleUrl: './crud-read.component.css'
})
export class CrudReadComponent {

  form = new FormGroup({
    searchId: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$")])
  });
  productWithId: any;

  get searchId() {
    return this.form.get('searchId');
  }

  productService = inject(ProductService);

  products: Product[] = [];

  ngOnInit(): void {
    this.refreshProduct();
  }

  refreshProduct() {
    this.productService.getAllProducts().subscribe((data: Product[]) => {
      this.products = data;
      data.forEach(product => {
        console.log(product.productName);
        console.log(product.shortDesc);
    })
      
    });
    
  }

  onSubmit(value: any){

    if (this.form.invalid) {
      alert(`Try again invalid input`);
      return;
    }
    console.log(value);
    const productId = this.form.value.searchId;

    this.productService.getProductById(productId).subscribe({
      next: (respose) => {
        console.log('product found');
        this.productWithId = respose;

      },
      error: () => {
        console.log("Can't find that product")
      }
    })
  }
}
