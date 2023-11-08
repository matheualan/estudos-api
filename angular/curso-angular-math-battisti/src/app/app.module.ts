import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PrimeiroCompComponent } from './components/1primeiro-comp/primeiro-comp.component';
import { ParentDataComponent } from './components/2parent-data/parent-data.component';
import { DiretivasComponent } from './components/3diretivas/diretivas.component';
import { IfRenderComponent } from './components/4if-render/if-render.component';

@NgModule({
  declarations: [
    AppComponent,
    PrimeiroCompComponent,
    ParentDataComponent,
    DiretivasComponent,
    IfRenderComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
