import {Component, OnDestroy, OnInit} from '@angular/core';
import {Turma} from "../../models/Turma";
import {Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {TurmasService} from "../../services/turmas.service";
import {AvaliacoesTurmaService} from "../../services/avaliacoes-turma.service";
import {AvaliacaoTurma} from "../../models/AvaliacaoTurma";
import {catchError, Subscription, throwError} from "rxjs";
import {EstudanteService} from "../../services/estudante.service";

@Component({
  selector: 'app-listagem-avaliacoes-turma',
  templateUrl: './listagem-avaliacoes-turma.component.html',
  styleUrls: ['./listagem-avaliacoes-turma.component.css']
})
export class ListagemAvaliacoesTurmaComponent implements OnInit, OnDestroy {

  turmaSubscription!: Subscription;

  turma!: Turma;
  avaliacoes: AvaliacaoTurma[] = []

  constructor(private router: Router,
              private messageService: MessageService,
              public estudanteService: EstudanteService,
              private turmaSerice: TurmasService,
              private service: AvaliacoesTurmaService) {
  }

  ngOnInit() {
    this.turmaSubscription = this.turmaSerice.turmaListagemAvaliacao$.subscribe(value => {
      this.turma = value;
      this.service.listarAvaliacoesDaTurma(this.turma.id).subscribe(avaliacoes => {
        this.avaliacoes = avaliacoes;
      })
    })
  }

  ngOnDestroy() {
    this.turmaSubscription.unsubscribe();
  }

  abrirTelaCadastroAvaliacao() {
    this.service.turmaAdicionarAvaliacao.next(this.turma);
    this.router.navigateByUrl("/turmas/avaliacoes/cadastrar")
  }

  abrirTelaEdicaoAvaliacao(avalicao: AvaliacaoTurma) {
    this.service.avaliacaoEdicao$.next(avalicao);
    this.router.navigateByUrl("/turmas/avaliacoes/editar")
  }

  deletarAvaliacao(avaliacaoId: number) {
    this.service.deletarAvaliacao(avaliacaoId).pipe(catchError(error => {
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: error.error });
      return throwError(error.error)
    })).subscribe(value => {
      this.avaliacoes = this.avaliacoes.filter(av => av.id != avaliacaoId)
      this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Avaliação deletada com sucesso' });
    })
  }

  isDonoAvaliacao(matricula: string): boolean {
    return this.estudanteService.getEstudanteLogado().administrador || this.estudanteService.getEstudanteLogado().matricula == matricula;
  }

}
