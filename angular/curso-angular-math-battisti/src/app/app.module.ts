import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PrimeiroCompComponent } from './components/1primeiro-comp/primeiro-comp.component';
import { ParentDataComponent } from './components/2parent-data/parent-data.component';
import { DiretivasComponent } from './components/3diretivas/diretivas.component';
import { IfRenderComponent } from './components/4if-render/if-render.component';
import { EventsComponent } from './components/5events/events.component';
import { EmitterComponent } from './components/6.1emitter/emitter.component';
import { ChangeNumberComponent } from './components/6.2change-number/change-number.component';
import { EventTest01Component } from './components/event-test01/event-test01.component';
import { EventTest02Component } from './components/event-test02/event-test02.component';

@NgModule({
  declarations: [
    AppComponent,
    PrimeiroCompComponent,
    ParentDataComponent,
    DiretivasComponent,
    IfRenderComponent,
    EventsComponent,
    EmitterComponent,
    ChangeNumberComponent,
    EventTest01Component,
    EventTest02Component
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
