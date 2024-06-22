import { Component, inject } from '@angular/core';
import { CrudNavbarComponent } from '../crud-navbar/crud-navbar.component';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Product } from 'src/app/shared/interfaces/product';
import { ProductService } from 'src/app/shared/services/product.service';

@Component({
  selector: 'app-crud-delete',
  standalone: true,
  imports: [
    CommonModule,
    CrudNavbarComponent,
    MatCardModule, 
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule, 
  ReactiveFormsModule],
  templateUrl: './crud-delete.component.html',
  styleUrl: './crud-delete.component.css'
})
export class CrudDeleteComponent {

  form = new FormGroup({
    searchId: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$")])
  });
  productWithId: any;

  get searchId() {
    return this.form.get('searchId');
  }

  productService = inject(ProductService);

  products: Product[] = [];

  ngOnInit() {
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

  

  deletePr(value: any){
    console.log(`are you sure you want to delete the product with id: ${value}`);
    
    const productId = value;
    const userResponse = window.confirm(`You sure you wanna delete the product with id: ${value}`);
    if (!userResponse) {
      alert("Deletion canceled");
      return;
    }
    this.productService.deleteProduct(productId).subscribe({
      next: (respose) => {
        alert(`product with id: ${productId} has been deleted` );
        this.productWithId = respose;

      },
      error: () => {
        console.log("something went wrong with deleteion")
      }
    })
  }
}
