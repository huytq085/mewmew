import { Profile } from './profile.model';

export class Article {
  id: number;
  slug: string;
  subject: string;
  description: string;
  content: string;
  tagList: string[];
  dateAdded: string;
  updatedAt: string;
  favorited: boolean;
  favoritesCount: number;
  author: Profile;
  view: number;
  categoryId: number;
  image: string;
}
