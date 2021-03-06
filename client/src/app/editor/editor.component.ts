import { UserService } from './../core/services/user.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Article, ArticlesService, ProfilesService } from '../core';

@Component({
  selector: 'app-editor-page',
  templateUrl: './editor.component.html'
})
export class EditorComponent implements OnInit {
  article: Article = {} as Article;
  articleForm: FormGroup;
  tagField = new FormControl();
  errors: string;
  isSubmitting = false;

  constructor(
    private userService: UserService,
    private articlesService: ArticlesService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private profileService: ProfilesService
  ) {
    // use the FormBuilder to create a form group
    this.articleForm = this.fb.group({
      subject: ['', Validators.required],
      content: ['', Validators.required]
    });

    // Initialized tagList as empty array
    this.article.tagList = [];

    // Optional: subscribe to value changes on the form
    // this.articleForm.valueChanges.subscribe(value => this.updateArticle(value));
  }

  ngOnInit() {
    // If there's an article prefetched, load it
    this.route.data.subscribe((data: { article: Article }) => {
      if (data.article) {
        this.article = data.article;
        this.articleForm.patchValue(data.article);
      } else {
        // TODO: Do not need get article again, bescause it already exists from previous route
        let articleId = this.route.snapshot.params['id'];
        if (typeof articleId != 'undefined')
        this.articlesService.get(articleId, this.userService.getCurrentUser().id).subscribe(
          data => {
            this.article = data;
            this.articleForm.patchValue(data);
          }
        )
      }
    });
  }

  addTag() {
    // retrieve tag control
    const tag = this.tagField.value;
    // only add tag if it does not exist yet
    if (this.article.tagList.indexOf(tag) < 0) {
      this.article.tagList.push(tag);
    }
    // clear the input
    this.tagField.reset('');
  }

  removeTag(tagName: string) {
    this.article.tagList = this.article.tagList.filter(tag => tag !== tagName);
  }

  submitForm() {
    this.isSubmitting = true;

    // Set userId for article
    this.article.author = this.profileService.user2Profile(this.userService.getCurrentUser());

    // Set default category = 1 for testing
    this.article.categoryId = 1;
    

    // update the model
    this.updateArticle(this.articleForm.value);

    // post the changes
    this.articlesService.save(this.article).subscribe(
      article => {
        this.router.navigateByUrl('/article/' + article.id)
      }
      ,
      err => {
        this.errors = err;
        this.isSubmitting = false;
      }
    );
  }

  updateArticle(values: Object) {
    Object.assign(this.article, values);
  }
}
