import {Component, OnDestroy, OnInit} from '@angular/core';
import {catchError, Subscription, throwError} from "rxjs";
import {AvaliacaoTurma} from "../../models/AvaliacaoTurma";
import {Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {EstudanteService} from "../../services/estudante.service";
import {AvaliacoesProfessorService} from "../../services/avaliacoes-professor.service";
import {Professor} from "../../models/Professor";
import {ProfessorService} from "../../services/professor.service";

@Component({
  selector: 'app-listagem-avaliacao-professor',
  templateUrl: './listagem-avaliacao-professor.component.html',
  styleUrls: ['./listagem-avaliacao-professor.component.css']
})
export class ListagemAvaliacaoProfessorComponent implements OnInit, OnDestroy {

  professorSubscription!: Subscription;

  professor!: Professor;
  avaliacoes: AvaliacaoTurma[] = []

  constructor(private router: Router,
              private messageService: MessageService,
              public estudanteService: EstudanteService,
              private professorSerice: ProfessorService,
              private service: AvaliacoesProfessorService) {
  }

  ngOnInit() {
    this.professorSubscription = this.professorSerice.professorListagemAvaliacao$.subscribe(value => {
      this.professor = value;
      this.service.listarAvaliacoesDoProfessor(this.professor.id).subscribe(avaliacoes => {
        this.avaliacoes = avaliacoes;
      })
    })
  }

  ngOnDestroy() {
    this.professorSubscription.unsubscribe();
  }

  abrirTelaCadastroAvaliacao() {
    this.service.professorAdicionarAvaliacao.next(this.professor);
    this.router.navigateByUrl("/professores/avaliacoes/cadastrar")
  }

  abrirTelaEdicaoAvaliacao(avalicao: AvaliacaoTurma) {
    this.service.avaliacaoEdicao$.next(avalicao);
    this.router.navigateByUrl("/professores/avaliacoes/editar")
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
