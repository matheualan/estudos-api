import { Component } from '@angular/core';

@Component({
  selector: 'app-emitter',
  templateUrl: './emitter.component.html',
  styleUrls: ['./emitter.component.css']
})
export class EmitterComponent {

  myNumber: number = 0;

  onChangeNumber(): void {
    console.log('Função onChangeNumber deu certo!!!');
    this.myNumber = Math.floor(Math.random() * 10);
  }

}
