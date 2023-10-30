import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  firstName: string = "Joaquim";
  lastName: string = "Alvares";

  userObject = {
    email: 'math@random.com',
    cell: '9999-1122'
  }

  title = 'curso-angular-math-battisti';
}
