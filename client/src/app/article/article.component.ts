import { ProfilesService } from './../core/services/profiles.service';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import {
  Article,
  ArticlesService,
  Comment,
  CommentsService,
  User,
  UserService,
  Profile
} from '../core';

@Component({
  selector: 'app-article-page',
  templateUrl: './article.component.html'
})
export class ArticleComponent implements OnInit {
  article: Article;
  currentUser: User;
  canModify: boolean;
  comments: Comment[] = new Array();
  commentControl = new FormControl();
  commentFormErrors = '';
  isSubmitting = false;
  isDeleting = false;
  hasArticle = false;
  comment: Comment = {} as Comment;

  constructor(
    private route: ActivatedRoute,
    private articlesService: ArticlesService,
    private commentsService: CommentsService,
    private router: Router,
    private userService: UserService,
    private profileService: ProfilesService
  ) { }

  ngOnInit() {
    // Retreive the prefetched article
    // this.route.data.subscribe(
    //   (data: Article) => {
    //     this.article = data;

    //     // Load the comments on this article
    //     // this.populateComments();
    //   }
    // );
    let articleId = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
    this.comment.articleId = parseInt(articleId);
    this.articlesService.get(articleId).subscribe(
      data => {
        this.article = data;
        // TODO: Create likeCount from spring server to load all info of article
        
        // TODO: Get number of like from server
        this.article.favoritesCount = 1;
        // Load the current user's data
        this.currentUser = this.userService.getCurrentUser();
        this.comment.author = this.profileService.user2Profile(this.currentUser);
        this.canModify = (this.currentUser.id === this.article.author.id);
        // TODO: Get author from another service
        this.userService.getUserById(this.article.author.id).subscribe(
          data => {
            this.article.author = this.profileService.user2Profile(data);
            this.profileService.isFollowing(this.article.author.id)
          .subscribe(
            res => {
              this.article.author.following = res;
            }
          )
          }
        )
        console.log('can modify: ' + this.canModify)
        this.populateComments();
      }
    )



  }

  onToggleFavorite(favorited: boolean) {
    this.article.favorited = favorited;

    if (favorited) {
      this.article.favoritesCount++;
    } else {
      this.article.favoritesCount--;
    }
  }

  onToggleFollowing(following: boolean) {
    this.article.author.following = following;
  }

  deleteArticle() {
    this.isDeleting = true;

    this.articlesService.destroy(this.article.id)
      .subscribe(
        success => {
          this.router.navigateByUrl('/');
        }
      );
  }

  populateComments() {
    this.commentsService.getAll(this.article.id)
      .subscribe(comments => this.comments = comments);
  }

  addComment() {
    // Clone new object
    this.comment = Object.assign({}, this.comment);
    this.isSubmitting = true;
    this.comment.content = this.commentControl.value;
    this.commentsService
      .add(this.comment)
      .subscribe(
        res => {
          if (res == 1){
            console.log(this.comments)
            
            this.comments.unshift(this.comment);
            this.commentControl.reset('');
            this.isSubmitting = false;
          }
          
        },
        errors => {
          this.isSubmitting = false;
          this.commentFormErrors = errors;
        }
      );
  }

  // onDeleteComment(comment) {
  //   this.commentsService.destroy(comment.id, this.article.slug)
  //     .subscribe(
  //       success => {
  //         this.comments = this.comments.filter((item) => item !== comment);
  //       }
  //     );
  // }


}
