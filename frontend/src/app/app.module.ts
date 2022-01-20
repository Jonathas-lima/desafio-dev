import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';


import { AppComponent } from './app.component';
import { UploadFileComponent } from './upload-file/upload-file.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ListaLojasComponent } from './lista-lojas/lista-lojas.component';

import { LOCALE_ID } from '@angular/core';

@NgModule({
  declarations: [
    AppComponent,
    UploadFileComponent,
    LoginComponent,
    ListaLojasComponent
  ],

  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {path: 'upload-file', component: UploadFileComponent}, 
      {path: 'login', component: LoginComponent},
      {path: 'lojas', component: ListaLojasComponent},
      {path: '', redirectTo: '/upload-file', pathMatch: 'full'},
      {path: '**', component: PageNotFoundComponent}
      ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
