import { ModuleWithProviders, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProfileArticlesComponent } from './profile-articles.component';
import { ProfileComponent } from './profile.component';
import { ProfileFavoritesComponent } from './profile-favorites.component';
import { ProfileResolver } from './profile-resolver.service';
import { SharedModule } from '../shared';
import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileGalleryComponent } from './profile-gallery.component';
import { ProfileFriendsComponent } from './profile-friends.component';
import { SearchModule } from '../search/search.module';

@NgModule({
  imports: [
    SharedModule,
    ProfileRoutingModule,
    SearchModule
  ],
  declarations: [
    ProfileArticlesComponent,
    ProfileComponent,
    ProfileFavoritesComponent,
    ProfileGalleryComponent,
    ProfileFriendsComponent,
  ],
  providers: [
    ProfileResolver
  ]
})
export class ProfileModule {}
