import { Component } from '@angular/core';

@Component({
  selector: 'app-emitter',
  templateUrl: './emitter.component.html',
  styleUrls: ['./emitter.component.css']
})
export class EmitterComponent {

  myNumber: number = 0;
  numeroAtual: number = 1;

  onChangeNumber(): void {
    console.log('Função onChangeNumber() funcionando corretamente.');
    this.myNumber = Math.floor(Math.random() * 10);
  }

  onTrocarNumero(): void {
    this.numeroAtual = Math.floor(Math.random() * 10 + 1);
  }

}
