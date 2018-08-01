import { Component, OnInit } from '@angular/core';
import { Article, ArticleListConfig, ArticlesService } from '../core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile-gallery',
  templateUrl: './profile-gallery.component.html',
  styleUrls: ['./profile-gallery.component.css']
})
export class ProfileGalleryComponent implements OnInit {

  articles: Article[] = new Array();

  articlesConfig: ArticleListConfig = {
    type: 'all',
    filters: {}
  };
  
  constructor(
    private route: ActivatedRoute,
    private articlesService: ArticlesService
  ) { }

  ngOnInit() {
    this.articlesConfig.filters = {
      author: this.route.parent.snapshot.params['username'],
      limit: 10,
      offset: 0
    }
    console.log('profile gallery');
    console.log(this.articlesConfig);
    this.articlesService.query(this.articlesConfig)
    .subscribe(data => {
      // this.loading = false;
      this.articles = data;
      console.log(this.articles)

      // Used from http://www.jstips.co/en/create-range-0...n-easily-using-one-line/
      // this.totalPages = Array.from(new Array(Math.ceil(data.length / this.limit)), (val, index) => index + 1);
    });
  }

}
