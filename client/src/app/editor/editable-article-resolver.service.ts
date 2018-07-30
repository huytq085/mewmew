import { ProfilesService } from './../core/services/profiles.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

import { Article, ArticlesService, UserService } from '../core';
import { catchError, map } from 'rxjs/operators';

@Injectable()
export class EditableArticleResolver implements Resolve<Article> {
  constructor(
    private articlesService: ArticlesService,
    private router: Router,
    private userService: UserService,
    private profileService: ProfilesService
  ) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any> {

    return this.articlesService.get(route.params['id'])
      .pipe(
        map(
          article => {
            this.userService.getUserById(article.author.id).subscribe(
              data => {
                article.author = this.profileService.user2Profile(data);
                if (this.userService.getCurrentUser().username === article.author.username) {
                  console.log('return ne')
                  return article;
                } else {
                  this.router.navigateByUrl('/');
                }
              }
            )

          }
        ),
        catchError((err) => this.router.navigateByUrl('/'))
      );
  }
}
