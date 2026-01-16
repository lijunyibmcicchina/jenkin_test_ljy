import { Routes } from '@angular/router';
import { Login } from './component/login/login';
import { Register } from './components/register/register';
import { ServiceTypeList } from './components/service-type-list/service-type-list';

export const routes: Routes = [
  {
    path: 'login',
    component: Login, //http://localhost:4200/login
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
  {
    path: 'register',
    component: Register,
  },
  {
    path: 'service-types',
    component: ServiceTypeList,
  },
];
