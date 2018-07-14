import { SharedModule } from './../shared/shared.module';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthComponent } from './auth.component';
import { ModuleWithProviders } from '@angular/compiler/src/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const authRoutes: ModuleWithProviders = RouterModule.forChild([
  {
    path: 'login',
    component: AuthComponent
  },
  {
    path: 'register',
    component: AuthComponent
  }
])

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    authRoutes
  ],
  declarations: [AuthComponent]
})
export class AuthModule { }
