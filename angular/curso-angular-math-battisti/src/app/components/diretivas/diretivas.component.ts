import { Component } from '@angular/core';

@Component({
  selector: 'app-diretivas',
  templateUrl: './diretivas.component.html',
  styleUrls: ['./diretivas.component.css']
})
export class DiretivasComponent {

  size: number = 10;
  color: string = 'red';
  font: string = 'Tahoma';

  classes = ["green-title", "small-title"];

  classUnique: string = 'underline';

}
