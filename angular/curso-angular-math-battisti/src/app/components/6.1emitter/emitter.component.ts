import { Component } from '@angular/core';

@Component({
  selector: 'app-emitter',
  templateUrl: './emitter.component.html',
  styleUrls: ['./emitter.component.css']
})
export class EmitterComponent {

  numeroAtual: number = 0;

  onNumero(): void {
    this.numeroAtual = Math.floor(Math.random() * 10 + 1);
  }

}
