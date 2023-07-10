import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {InputDropdownItem} from "../../models/InputDropdownItem";

@Component({
  selector: 'app-input-dropdown',
  templateUrl: './input-dropdown.component.html',
  styleUrls: ['./input-dropdown.component.css']
})
export class InputDropdownComponent {

  @Input() formName!: string;
  @Input() formGroup!: FormGroup;
  @Input() type: string = 'text';
  @Input() label!: string;
  @Input() itens!: InputDropdownItem[];

  @Output() onSelect = new EventEmitter<any>();

  onChange(event: any) {
    this.onSelect.emit(event.value)
  }

  isSelecionado(item: InputDropdownItem): boolean {
    return this.formGroup.controls[this.formName].value == item.valor;
  }

}
