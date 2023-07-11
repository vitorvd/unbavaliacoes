import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Estudante} from "../../models/Estudante";
import {EstudanteService} from "../../services/estudante.service";
import {MessageService} from "primeng/api";
import {catchError, throwError} from "rxjs";

@Component({
  selector: 'cadastro-estudante',
  templateUrl: './cadastro-estudante.component.html',
  styleUrls: ['./cadastro-estudante.component.css'],
})
export class CadastroEstudanteComponent implements OnInit{

  formCadastro!: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router,
              private messageService: MessageService, private service: EstudanteService) {}

  ngOnInit(): void {
    this.formCadastro = this.formBuilder.group({
      nome: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      matricula: ['', [Validators.required]],
      curso: ['', [Validators.required]],
      senha: ['', [Validators.required]],
    })
  }

  onSubmit(){
    if(this.formCadastro.valid) {
      const estudante: Estudante = new Estudante();
      estudante.nome = this.formCadastro.value.nome
      estudante.email = this.formCadastro.value.email
      estudante.matricula = this.formCadastro.value.matricula
      estudante.curso = this.formCadastro.value.curso
      estudante.senha = this.formCadastro.value.senha
      this.service.criarEstudante(estudante)
        .pipe(catchError(error => {
          this.messageService.add({ severity: 'error', summary: 'Erro', detail: error.error });
          return throwError(error.error)
        }))
        .subscribe(value => {
          this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: value });
          this.router.navigateByUrl("/auth");
      });
    }
  }

}
