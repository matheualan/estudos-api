import { Component } from '@angular/core';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent {

  show: boolean = false;

  showMessage(): void {
    this.show = !this.show; //toggle: inversão dos valor da variável
    console.log('O botão foi cliclado');
  }

}
