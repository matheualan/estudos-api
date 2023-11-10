import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-component-test1',
  templateUrl: './component-test1.component.html',
  styleUrls: ['./component-test1.component.css']
})
export class ComponentTest1Component {

  nome: string = 'Matheus Alan';

  @Input() firstName: string = '';
  @Input() lastName!: string;
  @Input() age!: number;
  @Input() job!: string;

}
