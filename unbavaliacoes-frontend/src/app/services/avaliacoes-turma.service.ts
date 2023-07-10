import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, ReplaySubject} from "rxjs";
import {environment} from "../../environments/env";
import {AvaliacaoTurma} from "../models/AvaliacaoTurma";
import {Turma} from "../models/Turma";

@Injectable({
  providedIn: 'root'
})
export class AvaliacoesTurmaService {

  turmaAdicionarAvaliacao: ReplaySubject<Turma> = new ReplaySubject<Turma>();
  avaliacaoEdicao$: ReplaySubject<AvaliacaoTurma> = new ReplaySubject<AvaliacaoTurma>();

  constructor(private http: HttpClient) { }

  public criarAvaliacaoDaTurma(avaliacao: AvaliacaoTurma): Observable<any> {
    return this.http.post(`${environment.API}/avaliacoes-turma`, avaliacao,{ responseType: 'text' })
  }

  public editarAvaliacaoDaTurma(avaliacao: AvaliacaoTurma): Observable<any> {
    return this.http.put(`${environment.API}/avaliacoes-turma`, avaliacao,{ responseType: 'text' });
  }

  public listarAvaliacoesDaTurma(turmaId: number): Observable<any> {
    return this.http.get(`${environment.API}/avaliacoes-turma/turma/${turmaId}`)
  }

  public deletarAvaliacao(avaliacaoId: number): Observable<any> {
    return this.http.delete(`${environment.API}/avaliacoes-turma/${avaliacaoId}`,{ responseType: 'text' })
  }


}
