import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';

const LOGIN_ROUTE = "auth/login";

export const routes: Routes = [
  { path: LOGIN_ROUTE, component: LoginPageComponent },
  { path: '', redirectTo: LOGIN_ROUTE, pathMatch: 'full' },
  { path: '**', redirectTo: LOGIN_ROUTE }
];
