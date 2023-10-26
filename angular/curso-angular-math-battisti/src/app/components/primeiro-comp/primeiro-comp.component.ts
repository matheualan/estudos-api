import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-primeiro-comp',
  templateUrl: './primeiro-comp.component.html',
  styleUrls: ['./primeiro-comp.component.css']
})
export class PrimeiroCompComponent implements OnInit {

  firstName: string = "Matheus";
  lastName = "Alan";
  age: number = 28;
  job: string = "Desenvolvedor Fullstack";
  hobbies: Array<string> = ["Jogar", " Estudar", " Cultivar"];
  car = {
    name: "Honda Civic Touring",
    color: "Preto",
    year: 2024
  };

  constructor() { }

  ngOnInit(): void {
      
  }

}
