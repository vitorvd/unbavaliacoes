import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {EstudanteService} from "../../services/estudante.service";
import {Estudante} from "../../models/Estudante";
import {catchError, throwError} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formLogin!: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router,
              private messageService: MessageService, private service: EstudanteService) {
  }

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      matricula: ['', [Validators.required]],
      senha: ['', [Validators.required]],
    })
  }

  onSubmit() {
    if (this.formLogin.valid) {
      const estudante: Estudante = new Estudante();
      estudante.matricula = this.formLogin.value.matricula
      estudante.senha = this.formLogin.value.senha
      this.service.autenticar(estudante).pipe(catchError(error => {
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: JSON.stringify(error.error) });
        return throwError(error.error)
      })).subscribe(value => {
        localStorage.setItem("ESTUDANTE", JSON.stringify(value))
        localStorage.setItem("MATRICULA", value.matricula)
        this.router.navigateByUrl("/turmas")
      })
    }else{
      this.messageService.add({ severity: 'error', summary: 'Campos obrigatórios', detail: 'Os campos: matricula e senha são obrigatórios.' });
    }
  }

}


