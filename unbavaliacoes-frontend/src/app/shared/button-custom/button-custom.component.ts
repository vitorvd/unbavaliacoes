import {Component, Input} from '@angular/core';

@Component({
  selector: 'button-custom',
  templateUrl: './button-custom.component.html',
  styleUrls: ['./button-custom.component.css']
})
export class ButtonCustomComponent {

  @Input() texto = "Botão";

}
