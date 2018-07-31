import { Profile } from './profile.model';

export class Article {
  id: number;
  slug: string;
  subject: string;
  description: string;
  content: string;
  tagList: string[];
  createdAt: string;
  updatedAt: string;
  favorited: boolean;
  favoritesCount: number;
  author: Profile;
  view: number;
  categoryId: number;
  image: string;
}
