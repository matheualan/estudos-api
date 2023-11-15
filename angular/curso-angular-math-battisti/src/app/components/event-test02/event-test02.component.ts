import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-event-test02',
  templateUrl: './event-test02.component.html',
  styleUrls: ['./event-test02.component.css']
})
export class EventTest02Component {

  @Input() valor: number = 0;

  @Output() changeNumber: EventEmitter<any> = new EventEmitter();

  handleClick(): void {
    console.log('método funcionando');
    this.changeNumber.emit();
  }

  incrementa(): void {
    this.valor++;
  }

  decrementa(): void {
    this.valor--;
  }

}
