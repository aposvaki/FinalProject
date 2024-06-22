import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MenuCategories } from 'src/app/shared/interfaces/menu-categories';

@Component({
  selector: 'app-menu-category',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './menu-category.component.html',
  styleUrl: './menu-category.component.css'
})
export class MenuCategoryComponent {
menu: MenuCategories[] = [
  {text: 'Edit-Products', routerLink: 'product'},
  {text: 'Edit-Users', routerLink: 'edit-users'},
  {text: 'Edit-Orders', routerLink: 'edit-orders'},
  {text: 'Laptos', routerLink: 'laptops'},
  {text: 'All-in-One', routerLink: 'all-in-one'},
  {text: 'CPUs', routerLink: 'cpus'},
  {text: 'SSDs', routerLink: 'ssds'}
]
}
