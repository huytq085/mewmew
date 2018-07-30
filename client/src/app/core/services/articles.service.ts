import { UserService } from './user.service';
import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ApiService } from './api.service';
import { Article, ArticleListConfig } from '../models';
import { map } from 'rxjs/operators';

@Injectable()
export class ArticlesService {
  constructor(
    private apiService: ApiService,
    private userService: UserService
  ) { }

  query(config: ArticleListConfig): Observable<Article[]> {
    console.log('Da vao query')
    // Convert any filters over to Angular's URLSearchParams
    const params = {};

    Object.keys(config.filters)
      .forEach((key) => {
        params[key] = config.filters[key];
      });

    return this.apiService
      .get(
        '/articles' + ((config.type === 'feed') ? ('/feed/' + this.userService.getCurrentUser().id) : '/global/feed/' ),
        new HttpParams({ fromObject: params })
      );
  }

  get(id): Observable<Article> {
    return this.apiService.get('/articles/' + id)
      .pipe(map(data => data));
  }

  destroy(id) {
    return this.apiService.delete('/articles/' + id);
  }

  save(article): Observable<Article> {
    // If we're updating an existing article
    if (article.slug) {
      return this.apiService.put('/articles/' + article.slug, { article: article })
        .pipe(map(data => data.article));

      // Otherwise, create a new article
    } else {
      console.log('// Otherwise, create a new article')
      return this.apiService.post('/articles/', article)
        .pipe(
          map(article => article));
    }
  }

  favorite(articleId: number): Observable<Article> {
    return this.apiService.post('/articles/' + articleId + '/like', this.userService.getCurrentUser());
  }

  unfavorite(articleId: number): Observable<Article> {
    return this.apiService.post('/articles/' + articleId + '/unlike',this.userService.getCurrentUser());
  }

  isFavorite(articleId: number): Observable<boolean>{
    return this.apiService.get('/articles/' + articleId + '/isfavorite/' + this.userService.getCurrentUser().id);
  }


}
