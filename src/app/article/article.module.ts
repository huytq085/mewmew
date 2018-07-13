import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticleComponent } from './article.component';
import { ArticleCommentComponent } from './article-comment.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [ArticleComponent, ArticleCommentComponent]
})
export class ArticleModule { }
