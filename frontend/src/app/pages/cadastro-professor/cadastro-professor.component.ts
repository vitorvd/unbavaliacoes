import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {InputDropdownItem} from "../../models/InputDropdownItem";
import {catchError, Subscription, throwError} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {DisciplinaProfessorService} from "../../services/disciplina-professor.service";
import {Disciplina} from "../../models/Disciplina";
import {ProfessorService} from "../../services/professor.service";
import {Professor} from "../../models/Professor";

@Component({
  selector: 'app-cadastro-professor',
  templateUrl: './cadastro-professor.component.html',
  styleUrls: ['./cadastro-professor.component.css']
})
export class CadastroProfessorComponent implements OnInit, OnDestroy {

  formCadastroProfessor!: FormGroup;

  disciplinas: Disciplina[] = [];
  departamentos: any[] = []

  dropdownDisciplina: InputDropdownItem[] = [];
  dropdownDepartamento: InputDropdownItem[] = [];

  professorEdicaoSubscription!: Subscription;

  isViewEdicao: boolean = false;

  professor: Professor = new Professor();

  constructor(private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private messageService: MessageService,
              private disciplinaProfessorService: DisciplinaProfessorService,
              private service: ProfessorService) {
  }


  ngOnInit(): void {
    //Obter disciplinas
    this.disciplinaProfessorService.getDisciplinas().pipe(catchError(error => {
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: JSON.stringify(error.error) });
      return throwError(error.error)
    })).subscribe(value => {
      value.forEach((disciplina: Disciplina) => {
        this.dropdownDisciplina.push({valor: disciplina.id, texto: disciplina.nome});
      })
    })

    //Obter departamentos
    this.disciplinaProfessorService.getDepartamentos().pipe(catchError(error => {
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: JSON.stringify(error.error) });
      return throwError(error.error)
    })).subscribe(value => {
      value.forEach((departamento: any) => {
        this.dropdownDepartamento.push({valor: departamento.id, texto: departamento.nome});
      })
    })

    this.formCadastroProfessor = this.formBuilder.group({
      nome: ['', [Validators.required]],
      disciplina: ['', [Validators.required]],
      departamento: ['', [Validators.required]],
    })

    this.professorEdicaoSubscription = this.service.professorEdicao$.subscribe((value: Professor) => {
      this.professor = value;
      console.log(`Professor: ${JSON.stringify(this.professor)}`)
      this.formCadastroProfessor.controls['nome'].setValue(value.nome);
      this.formCadastroProfessor.controls['disciplina'].setValue(value.disciplinaId);
      this.formCadastroProfessor.controls['departamento'].setValue(value.departamentoId);

      this.isViewEdicao = true;
    })
  }

  ngOnDestroy() {
    this.professorEdicaoSubscription.unsubscribe();
  }

  onSubmit() {
    if (this.formCadastroProfessor.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Erro',
        detail: 'Os campos: Nome, Disciplina e Departamento são obrigatórios.'
      });
      return
    }

    this.professor.nome = this.formCadastroProfessor.value.nome
    this.professor.disciplinaId = Number(this.formCadastroProfessor.value.disciplina)
    this.professor.departamentoId = Number(this.formCadastroProfessor.value.departamento)

    if (this.isViewEdicao) {
      console.log(`Professor: ${JSON.stringify(this.professor)}`)
      this.service.editarProfessor(this.professor).subscribe(value => {
        this.messageService.add({severity: 'success', summary: 'Sucesso', detail: value});
        this.router.navigateByUrl("/professores")
        this.isViewEdicao = false;
        this.formCadastroProfessor.reset()
      })
    } else {
      this.service.criarProfessor(this.professor).subscribe(value => {
        this.messageService.add({severity: 'success', summary: 'Sucesso', detail: value});
        this.router.navigateByUrl("/professores")
      })
    }
  }

  getTextoBotao(): string {
    return this.isViewEdicao ? "Editar" : "Cadastrar"
  }
}


