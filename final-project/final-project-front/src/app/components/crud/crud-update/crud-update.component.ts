import { Component, OnInit, inject } from '@angular/core';
import { CrudNavbarComponent } from '../crud-navbar/crud-navbar.component';
import { Product } from 'src/app/shared/interfaces/product';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ProductService } from 'src/app/shared/services/product.service';

@Component({
  selector: 'app-crud-update',
  standalone: true,
  imports: [CommonModule,
    CrudNavbarComponent,
    MatCardModule, 
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule, 
  ReactiveFormsModule],
  templateUrl: './crud-update.component.html',
  styleUrl: './crud-update.component.css'
})
export class CrudUpdateComponent {

  searchForm: FormGroup;
  updateForm: FormGroup;
  products: Product[] = [];
  productForms: { [key: string]: FormGroup } = {};

  productService = inject(ProductService);
  fb = inject(FormBuilder);

  product: any;

  ngOnInit() {
    this.searchForm = this.fb.group({
      searchId: ['', [Validators.required, Validators.pattern('^[0-9]*$')]]
    });

    this.updateForm = this.fb.group({
      productName: ['', Validators.required],
      shortDesc: ['', Validators.required]
    });

    this.refreshProducts();
  }

  refreshProducts() {
    this.productService.getAllProducts().subscribe((data: Product[]) => {
      this.products = data;
      this.products.forEach(product => {
        this.productForms[product.id] = this.fb.group({
          productName: [product.productName, Validators.required],
          shortDesc: [product.shortDesc, Validators.required]
        });
      });
    });
  }

  onSubmit(value: any) {
    if (this.searchForm.invalid) {
      alert('Invalid input');
      return;
    }
    console.log(value);
    const productId = this.searchForm.value.searchId;

    this.productService.getProductById(productId).subscribe({
      next: (respose) => {
        console.log('product found');
        this.product = respose;
        this.updateForm.patchValue({
          productName: this.product.productName,
          shortDesc: this.product.shortDesc
        })
      },
      error: () => {
        console.log("Can't find that product");
        this.product = null;
      }
    })
  }

  updateProduct() {
    if (this.updateForm.invalid) {
      alert('Invalid form data');
      return;
    }

    const updatedProduct: Product = {
      ...this.product,
      ...this.updateForm.value
    };

    this.productService.updateProduct(updatedProduct).subscribe({
      next: (response) => {
        alert(`product with id: ${updatedProduct.id} updated succesfully`);
        console.log(`product with id: ${updatedProduct.id} updated succesfully`)
        this.refreshProducts();
        this.product = null;  // Hide the update form
      },
      error: () => {
        console.error('Failed to update product');
      }
    });
  }

  getFormGroup(productId: string): FormGroup {
    return this.productForms[productId];
  
  }
  //updating the product from the list
  updateProductInList(productId: string): void {
    const formGroup = this.getFormGroup(productId);
    const updatedProduct: Product = {
      ...this.products.find(p => p.id === productId),
      ...formGroup.value
    };

    this.productService.updateProduct(updatedProduct).subscribe({
      next: () => {
        alert(`product with id: ${productId} updated succesfully`);
        this.refreshProducts();
      },
      error: () => {
        console.error('Failed to update product');
      }
    });
  }
}
