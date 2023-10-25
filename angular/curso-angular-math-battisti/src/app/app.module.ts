import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PrimeiroCompComponent } from './components/primeiro-comp/primeiro-comp.component';
import { SegundoCompComponent } from './components/segundo-comp/segundo-comp.component';

@NgModule({
  declarations: [
    AppComponent,
    PrimeiroCompComponent,
    SegundoCompComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
