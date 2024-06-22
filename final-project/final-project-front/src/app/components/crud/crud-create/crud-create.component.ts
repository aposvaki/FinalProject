import { Component, inject } from '@angular/core';
import { CrudNavbarComponent } from '../crud-navbar/crud-navbar.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { ProductService } from 'src/app/shared/services/product.service';
import { Product } from 'src/app/shared/interfaces/product';

@Component({
  selector: 'app-crud-create',
  standalone: true,
  imports: [CrudNavbarComponent, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './crud-create.component.html',
  styleUrl: './crud-create.component.css'
})
export class CrudCreateComponent {
  productService = inject(ProductService);

  form = new FormGroup({
      productName: new FormControl('', Validators.required),
      shortDesc: new FormControl('', Validators.required)
    }
  );
  registrationStatus: {success: boolean; message: string} = {
    success: false,
    message: 'Not attempted yet',
  };

  onSubmit(value: any) {
    console.log(value);
    const product = this.form.value as Product;
    this.productService.addProduct(product).subscribe({
      next: (response) => {
        alert(`${product.productName} Added`);
        this.registrationStatus = {
          success:true, message: response.msg
        };
      },
      error: (response) => {
        const message = response.error.msg;
        console.log('Error in adding product ', message);
        this.registrationStatus = { success:false, message}
      }
    })
  }

}
