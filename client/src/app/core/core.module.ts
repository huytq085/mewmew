import { ProfilesService } from './services/profiles.service';
import { CommentsService } from './services/comment.service';
import { ArticlesService } from './services/articles.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { UserService, ApiService, AuthGuard } from './services';
import { HttpTokenInterceptor } from '.';

@NgModule({
  imports: [
    CommonModule,
  ],
  declarations: [],
  providers:[
    { provide: HTTP_INTERCEPTORS, useClass: HttpTokenInterceptor, multi: true },
    UserService,
    ApiService,
    ArticlesService,
    CommentsService,
    ProfilesService,
    AuthGuard

  ]
})
export class CoreModule { }
