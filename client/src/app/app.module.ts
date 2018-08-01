import { EditorModule } from './editor/editor.module';
import { SettingsModule } from './settings/settings.module';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppMaterialModule } from './app-material/app-material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';

import { HeaderComponent } from './shared/layout/header.component'
import { FooterComponent } from './shared/layout/footer.component'
import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { HomeModule } from './home/home.module';
import { AuthModule } from './auth/auth.module';
import { HttpModule } from '@angular/http';
import { ProfileModule } from './profile/profile.module';
import { ProfileRoutingComponent } from './profile/profile-routing.component';
import { SearchModule } from './search/search.module';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ProfileRoutingComponent,

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    AppRoutingModule,
    CoreModule,
    SharedModule,
    HomeModule,
    AuthModule,
    HttpModule,
    HttpClientModule,
    ProfileModule,
    SettingsModule,
    EditorModule,
    SearchModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
