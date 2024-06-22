import { Routes } from '@angular/router';
import { ProductComponent } from './components/product/product.component';
import { CrudCreateComponent } from './components/crud/crud-create/crud-create.component';
import { CrudReadComponent } from './components/crud/crud-read/crud-read.component';
import { CrudUpdateComponent } from './components/crud/crud-update/crud-update.component';
import { CrudDeleteComponent } from './components/crud/crud-delete/crud-delete.component';

export const routes: Routes = [
    {path: 'product', component: ProductComponent},
    {path:'product/add', component: CrudCreateComponent},
    {path:'product/read', component: CrudReadComponent},
    {path:'product/update', component: CrudUpdateComponent},
    {path:'product/delete', component: CrudDeleteComponent}
];
