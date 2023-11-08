import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-parent-data',
  templateUrl: './parent-data.component.html',
  styleUrls: ['./parent-data.component.css']
})
export class ParentDataComponent {

  @Input() firstName: string = "";
  @Input() lastName: string = "";
  @Input() idade!: number;
// a exclamação é uma sintaxe do TS para afirmar que o valor da variável não será nulo assim podendo inicializar-la
  @Input() data!: { email: string, cell: string };
  @Input() titulo: string = "";

}
