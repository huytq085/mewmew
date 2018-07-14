import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService, ApiService } from './services';

@NgModule({
  imports: [
    CommonModule,
  ],
  declarations: [],
  providers:[UserService, ApiService]
})
export class CoreModule { }
