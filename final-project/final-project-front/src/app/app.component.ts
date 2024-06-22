import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { MenuCategoryComponent } from './components/menu-category/menu-category.component';
import { NavbarComponent } from "./components/navbar/navbar.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterLink,
        RouterOutlet,
        MenuCategoryComponent, NavbarComponent]
})
export class AppComponent {
  title = 'final-project-front';
}
