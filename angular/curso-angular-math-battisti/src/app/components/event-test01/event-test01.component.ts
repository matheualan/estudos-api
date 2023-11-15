import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-event-test01',
  templateUrl: './event-test01.component.html',
  styleUrls: ['./event-test01.component.css']
})
export class EventTest01Component {

  @Input() nomeMae: string = '';
  @Input() nomePai!: string;


  numero: number = 0;

  onChangeNumber(): void {
    this.numero = Math.floor(Math.random() * 20);
    // this.numero = Math.random() + 10;
  }

}
