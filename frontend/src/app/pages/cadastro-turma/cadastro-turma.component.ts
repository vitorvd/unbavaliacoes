import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {EstudanteService} from "../../services/estudante.service";
import {InputDropdownItem} from "../../models/InputDropdownItem";
import {DisciplinaProfessorService} from "../../services/disciplina-professor.service";
import {catchError, Subscription, throwError} from "rxjs";
import {DisciplinaProfessor} from "../../models/DisciplinaProfessor";
import {Professor} from "../../models/Professor";
import {Disciplina} from "../../models/Disciplina";
import {TurmasService} from "../../services/turmas.service";
import {Turma} from "../../models/Turma";

@Component({
  selector: 'app-cadastro-turma',
  templateUrl: './cadastro-turma.component.html',
  styleUrls: ['./cadastro-turma.component.css']
})
export class CadastroTurmaComponent implements OnInit, OnDestroy {

  formCadsatroTurma!: FormGroup;

  disciplinaProfessors: DisciplinaProfessor[] = [];

  dropdownDisciplina: InputDropdownItem[] = [];
  dropdownProfessor: InputDropdownItem[] = [];

  turmaEdicaoSubscription!: Subscription;

  isViewEdicao: boolean = false;

  constructor(private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private messageService: MessageService,
              private service: TurmasService,
              private serviceDisciplinaProfessores: DisciplinaProfessorService) {}


  ngOnInit(): void {
    this.serviceDisciplinaProfessores.getDisciplinasProfessores().pipe(catchError(error => {
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: JSON.stringify(error.error) });
      return throwError(error.error)
    })).subscribe(value => {
      this.disciplinaProfessors = value;
      this.disciplinaProfessors.forEach((disciplinaProfessor: DisciplinaProfessor) => {
        if (!this.dropdownDisciplina.some(item => item.valor === disciplinaProfessor.disciplina.id)) {
          this.dropdownDisciplina.push({
            valor: disciplinaProfessor.disciplina.id,
            texto: disciplinaProfessor.disciplina.nome
          });
        }
        this.dropdownProfessor.push({valor: disciplinaProfessor.professor.id, texto: disciplinaProfessor.professor.nome})
      })
    })

    this.formCadsatroTurma = this.formBuilder.group({
      id: [''],
      periodo: ['', [Validators.required]],
      disciplina: ['', [Validators.required]],
      professor: ['', [Validators.required]],
    })

    this.turmaEdicaoSubscription = this.service.turmaEdicao$.subscribe((value: Turma) => {
      this.formCadsatroTurma.controls['id'].setValue(value.id);
      this.formCadsatroTurma.controls['periodo'].setValue(value.periodo);
      this.formCadsatroTurma.controls['disciplina'].setValue(value.disciplina.id);
      this.formCadsatroTurma.controls['professor'].setValue(value.professor.id);

      this.isViewEdicao = true;
    })
  }

  ngOnDestroy() {
    this.turmaEdicaoSubscription.unsubscribe();
  }

  onSelectDisciplina(valor: any) {
    this.dropdownProfessor = []
    this.disciplinaProfessors.forEach(dp => {
      if(dp.disciplina.id == valor){

        this.dropdownProfessor.push({valor: dp.professor.id, texto: dp.professor.nome})

      }
    })

    this.formCadsatroTurma.get('professor')?.enable()
  }

  onSubmit(){
    if(this.formCadsatroTurma.invalid){
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Os campos: Periodo, Disciplina e Professor são obrigatórios.' });
      return
    }

    const turma: Turma = new Turma();
    if(this.isViewEdicao)
      turma.id = Number(this.formCadsatroTurma.value.id)

    turma.periodo = this.formCadsatroTurma.value.periodo
    turma.disciplina.id = Number(this.formCadsatroTurma.value.disciplina)
    turma.professor.id = Number(this.formCadsatroTurma.value.professor)

    if(this.isViewEdicao){
      this.service.editarTurma(turma).subscribe(value => {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: value });
        this.router.navigateByUrl("/turmas")
        this.isViewEdicao = false;
        this.formCadsatroTurma.reset()
      })
    }else{
      this.service.criarTurma(turma).subscribe(value => {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: value });
        this.router.navigateByUrl("/turmas")
      })
    }
  }

  getTextoBotao(): string {
    return this.isViewEdicao ? "Editar" : "Cadastrar"
  }

}
