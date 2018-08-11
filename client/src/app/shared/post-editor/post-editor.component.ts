import { Component, OnInit, ElementRef, ViewChild, Renderer } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Article, ArticlesService, UserService, ProfilesService } from '../../core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post-editor',
  templateUrl: './post-editor.component.html',
  styleUrls: ['./post-editor.component.css']
})
export class PostEditorComponent implements OnInit {
  isSubmitting: boolean = false;
  urlImage: string;
  postForm: FormGroup;
  article: Article =  {} as Article;
  errors: string;
  isAuthenticated: boolean;

  constructor(
    private fb: FormBuilder,
    private articlesService: ArticlesService,
    private router: Router,
    private profileService: ProfilesService,
    private userService: UserService
  ) { 
    this.postForm = this.fb.group({
      content: ['', Validators.required],
      image: ''
    })
  }
  ngOnInit() {
    this.userService.isAuthenticated.subscribe(
      (authenticated) => {
        this.isAuthenticated = authenticated;
      }
    );
  }

  onFileChange(event){
    let reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.postForm.controls['image'].setValue(reader.result.split(',')[1]);
        this.urlImage = reader.result;
      }

    }
  }

  submitForm(){
    this.isSubmitting = true;
    // set value article
    this.updateArticle(this.postForm.value);
    // Set default category = 1 for testing
    this.article.categoryId = 1;
    this.article.author = this.profileService.user2Profile(this.userService.getCurrentUser());
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
