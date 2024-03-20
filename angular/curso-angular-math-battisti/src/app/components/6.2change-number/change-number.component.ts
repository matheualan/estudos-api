import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-change-number',
  templateUrl: './change-number.component.html',
  styleUrls: ['./change-number.component.css']
})
export class ChangeNumberComponent {

  @Output() numero: EventEmitter<any> = new EventEmitter();

  alterarNumero(): void {
    this.numero.emit();
  }

}
