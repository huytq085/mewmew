import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ApiService } from './api.service';
import { Comment } from '../models';
import { map } from 'rxjs/operators';


@Injectable()
export class CommentsService {
  constructor (
    private apiService: ApiService
  ) {}

  add(comment: Comment): Observable<number> {
    return this.apiService
    .post(
      `/articles/comments`,
      comment
    );
  }

  getAll(id): Observable<Comment[]> {
    return this.apiService.get(`/articles/${id}/comments`)
      // .pipe(map(data => data.comments));
      .pipe();
  }

  destroy(commentId, articleSlug) {
    return this.apiService
           .delete(`/articles/${articleSlug}/comments/${commentId}`);
  }

}
