import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  // dados primeiro-comp
  objetivoFinal: string = "Concluir este curso de Angular e me tornar fullstack!";
  // dados primeiro-comp

  primeiroNome: string = 'Matios';
  ultimoNome: string = 'Vinhera';

  // dados parent-data
  firstName: string = "Joaquim";
  lastName: string = "Alvares";
  age: number = 28;

  userObject = {
    email: 'math@random.com',
    cell: '9999-1122'
  }
  // dados parent-data

  title = 'curso-angular-math-battisti';
}
