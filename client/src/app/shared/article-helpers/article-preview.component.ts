import { Component, Input, OnInit } from '@angular/core';

import { Article } from '../../core';

@Component({
  selector: 'app-article-preview',
  templateUrl: './article-preview.component.html'
})
export class ArticlePreviewComponent implements OnInit {
  
  @Input() article: Article;

  ngOnInit(): void {
    // shorten content to 150 characters
    // Set count for testing
    this.article.favoritesCount = 1;
    this.article.content = this.article.content.substring(0,150);
  }

  onToggleFavorite(favorited: boolean) {
    this.article['favorited'] = favorited;

    if (favorited) {
      this.article['favoritesCount']++;
    } else {
      this.article['favoritesCount']--;
    }
  }
}
