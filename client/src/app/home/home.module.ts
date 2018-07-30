import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { ArticleModule } from '../article/article.module';
import { SharedModule } from '../shared';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  declarations: [HomeComponent]
})
export class HomeModule { }
