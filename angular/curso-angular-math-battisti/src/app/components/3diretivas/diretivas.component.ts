import { Component } from '@angular/core';

@Component({
  selector: 'app-diretivas',
  templateUrl: './diretivas.component.html',
  styleUrls: ['./diretivas.component.css']
})
export class DiretivasComponent {

  tamanho: number = 40;
  coloracao: string = "blue";
  tipoDaFonte: string = "cosmic sans";
  crasses: object = ['blue-title', 'smoll-title'];

  size: number = 10;
  color: string = 'red';
  font: string = 'Tahoma';

  classes = ["green-title", "small-title", "underline"];

  // classUnique: string = 'underline';

}
