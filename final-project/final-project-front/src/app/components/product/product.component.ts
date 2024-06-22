import { Component } from '@angular/core';
import { CrudNavbarComponent } from '../crud/crud-navbar/crud-navbar.component';
import { CrudCreateComponent } from '../crud/crud-create/crud-create.component';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CrudNavbarComponent, CrudCreateComponent],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {

}
