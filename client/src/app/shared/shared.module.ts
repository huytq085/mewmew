import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListErrorsComponent } from './list-errors.component';

@NgModule({
  imports: [
    CommonModule
  ],
  exports: [
    ListErrorsComponent
  ],
  declarations: [ListErrorsComponent]
})
export class SharedModule { }
