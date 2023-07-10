import {Component} from '@angular/core';
import {EstudanteService} from "../../services/estudante.service";

@Component({
  selector: 'menu-principal',
  templateUrl: './menu-principal.component.html',
  styleUrls: ['./menu-principal.component.css']
})
export class MenuPrincipalComponent {

  constructor(public estudanteService: EstudanteService) {
  }

  logout() {
    localStorage.removeItem("ESTUDANTE")
    localStorage.removeItem("MATRICULA")
  }

}
