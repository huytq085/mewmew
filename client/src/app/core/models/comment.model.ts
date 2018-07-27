import { Profile } from './profile.model';

export interface Comment {
  id: number;
  articleId: number;
  content: string;
  dateAdded: string;
  author: Profile;
}
