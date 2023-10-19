import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CursosComponent } from './cursos/cursos.component';

//Aqui é onde vamos adicionar os components desse módulo

const routes: Routes = [
  { path: '', component: CursosComponent } //path: '' indica que é para a própria pasta(módulo) cursos
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CursosRoutingModule { }
