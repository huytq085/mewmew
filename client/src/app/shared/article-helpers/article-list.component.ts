import { UserService } from './../../core/services/user.service';
import { Component, Input, Output, EventEmitter } from '@angular/core';

import { Article, ArticleListConfig, ArticlesService } from '../../core';
@Component({
  selector: 'app-article-list',
  styleUrls: ['article-list.component.css'],
  templateUrl: './article-list.component.html'
})
export class ArticleListComponent {
  constructor(
    private articlesService: ArticlesService,
    private userService: UserService
  ) { }

  @Input() limit: number;
  @Input()
  set config(config: ArticleListConfig) {
    if (config) {
      this.query = config;
      this.currentPage = 1;
      this.runQuery();
    }
  }
  @Output() type: EventEmitter<any> = new EventEmitter();

  query: ArticleListConfig;
  results: Article[];
  loading = false;
  currentPage = 1;
  totalPages: Array<number> = [1];

  setPageTo(pageNumber) {
    this.currentPage = pageNumber;
    this.runQuery();
  }

  runQuery() {
    this.loading = true;
    this.results = [];

    if (this.limit) {
      this.query.filters.limit = this.limit;
      this.query.filters.offset = (this.limit * (this.currentPage - 1));
    }
    // FIXME: Khi co token dang nhap, global tai bat dong bo override len feed hien tai
    this.userService.currentUser.subscribe(
      data => {
        this.articlesService.query(this.query, data.id)
          .subscribe(data => {
            this.loading = false;
            this.results = data;

            // Used from http://www.jstips.co/en/create-range-0...n-easily-using-one-line/
            this.totalPages = Array.from(new Array(Math.ceil(data.length / this.limit)), (val, index) => index + 1);
          });
      }
    )

  }
}
