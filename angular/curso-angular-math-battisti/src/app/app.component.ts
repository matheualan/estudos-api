import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  // primeiro-comp
  objetivoFinal: string = "Concluir este curso de Angular e me tornar fullstack!";
  // primeiro-comp

  // component-test1
  primeiroNome: string = 'Matios';
  ultimoNome: string = 'Vinhera';
  idade: number = 25;
  job: string = 'Programador';
  // component-test1

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
