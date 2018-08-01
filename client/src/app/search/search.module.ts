import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchComponent } from './search.component';
import { SearchRoutingModule } from './search-routing.module';
import { SearchListComponent } from './search-list.component';
import { SharedModule } from '../shared';

@NgModule({
  imports: [
    CommonModule,
    SearchRoutingModule,
    SharedModule
  ],
  declarations: [
    SearchComponent,
    SearchListComponent
  ]
})
export class SearchModule { }
