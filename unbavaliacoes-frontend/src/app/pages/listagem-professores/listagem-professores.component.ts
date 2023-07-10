import {Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Subscription, throwError} from "rxjs";
import {Turma} from "../../models/Turma";
import {AvaliacaoTurma} from "../../models/AvaliacaoTurma";
import {Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {EstudanteService} from "../../services/estudante.service";
import {TurmasService} from "../../services/turmas.service";
import {AvaliacoesTurmaService} from "../../services/avaliacoes-turma.service";
import {ProfessorService} from "../../services/professor.service";
import {Professor} from "../../models/Professor";

@Component({
  selector: 'app-listagem-professores',
  templateUrl: './listagem-professores.component.html',
  styleUrls: ['./listagem-professores.component.css']
})
export class ListagemProfessoresComponent implements OnInit {

  professores!: Professor[];

  constructor(private router: Router, private messageService: MessageService,
              public estudanteService: EstudanteService,
              private service: ProfessorService) {
  }

  ngOnInit() {
    this.service.getTodosProfessores().subscribe(value => {
      this.professores = value;
    })
  }

  editarProfessor(professor: Professor) {
    this.service.professorEdicao$.next(professor);
    this.router.navigateByUrl("/professores/editar")
  }

  deletarProfessor(professorId: number) {
    this.service.deletarProfessor(professorId).pipe(catchError(error => {
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: error.error });
      return throwError(error.error)
    }))
      .subscribe(value => {
        this.professores = this.professores.filter(prof => prof.id != professorId)
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: value });
      });
  }

  abrirTelaCriarProfessor(){
    this.router.navigateByUrl("/professores/cadastrar")
  }

  abrirTelaDeAvaliacoesDoProfessor(professor: Professor) {
    this.service.professorListagemAvaliacao$.next(professor)
    this.router.navigateByUrl("/professores/avaliacoes")
  }

}
