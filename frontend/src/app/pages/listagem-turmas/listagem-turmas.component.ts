import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {TurmasService} from "../../services/turmas.service";
import {Turma} from "../../models/Turma";
import {catchError, throwError} from "rxjs";
import {EstudanteService} from "../../services/estudante.service";

@Component({
  selector: 'app-listagem-turmas',
  templateUrl: './listagem-turmas.component.html',
  styleUrls: ['./listagem-turmas.component.css']
})
export class ListagemTurmasComponent implements OnInit {

  turmas!: Turma[];

  constructor(private router: Router, private messageService: MessageService,
              public estudanteService: EstudanteService,
              private service: TurmasService) {
  }

  ngOnInit() {
    this.service.getTodasTurmas().subscribe(value => {
      this.turmas = value;
    })
  }

  editarTurma(turma: Turma) {
    this.service.turmaEdicao$.next(turma);
    this.router.navigateByUrl("/turmas/editar")
  }

  deletarTurma(turmaId: number) {
    this.service.deletarTurma(turmaId).pipe(catchError(error => {
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: error.error });
      return throwError(error.error)
    }))
    .subscribe(value => {
      this.turmas = this.turmas.filter(turma => turma.id != turmaId)
      this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: value });
    });
  }

  abrirTelaCriarTurma(){
    this.router.navigateByUrl("/turmas/cadastrar")
  }

  abrirTelaDeAvaliacoesDaTurma(turma: Turma) {
    this.service.turmaListagemAvaliacao$.next(turma)
    this.router.navigateByUrl("/turmas/avaliacoes")
  }

}


