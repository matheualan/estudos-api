import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // event-test01
  maeNome: string = 'Maria Anunciada';
  paiNome: string = 'Francisco Moacy';
  // event-test01
  
  // primeiro-comp
  objetivoFinal: string = "Concluir este curso de Angular e me tornar fullstack!";
  // primeiro-comp

  // parent-data
  firstName: string = "Joaquim";
  lastName: string = "Alvares";
  age: number = 28;

  userObject = {
    email: 'math@random.com',
    cell: '9999-1122'
  }
  // parent-data

  title = 'curso-angular-math-battisti';
}
