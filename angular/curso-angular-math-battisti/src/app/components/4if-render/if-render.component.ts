import { Component } from '@angular/core';

@Component({
  selector: 'app-if-render',
  templateUrl: './if-render.component.html',
  styleUrls: ['./if-render.component.css']
})
export class IfRenderComponent {

  // usado no *ngIf fornecendo a condição para exibir ou não a tag desejada
  boleano: boolean = true;

  name: string = 'Matheus';

  idade: string = '28';

}
