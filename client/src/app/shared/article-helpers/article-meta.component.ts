import { Component, Input, OnInit } from '@angular/core';

import { Article, ArticlesService } from '../../core';

@Component({
  selector: 'app-article-meta',
  templateUrl: './article-meta.component.html'
})
export class ArticleMetaComponent implements OnInit{
  @Input() article: Article;
  isLoad = false;
  constructor(
    private articlesService: ArticlesService
  ) {}
  ngOnInit(){
    console.log('article meta')
    console.log(this.article)
    if (typeof this.article != 'undefined'){
      this.isLoad = true;
    }
    this.articlesService.isFavorite(this.article.id).subscribe(
      res => {
        this.article.favorited = res;
      }
    )
  }
}
