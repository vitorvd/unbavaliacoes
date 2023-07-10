import { Injectable } from '@angular/core';
import {Observable, ReplaySubject} from "rxjs";
import {Turma} from "../models/Turma";
import {AvaliacaoTurma} from "../models/AvaliacaoTurma";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/env";
import {Professor} from "../models/Professor";

@Injectable({
  providedIn: 'root'
})
export class AvaliacoesProfessorService {

  professorAdicionarAvaliacao: ReplaySubject<Professor> = new ReplaySubject<Professor>();
  avaliacaoEdicao$: ReplaySubject<AvaliacaoTurma> = new ReplaySubject<AvaliacaoTurma>();

  constructor(private http: HttpClient) { }

  public criarAvaliacaoDoProfessor(avaliacao: AvaliacaoTurma): Observable<any> {
    return this.http.post(`${environment.API}/avaliacoes-professor`, avaliacao,{ responseType: 'text' })
  }

  public editarAvaliacaoDoProfessor(avaliacao: AvaliacaoTurma): Observable<any> {
    return this.http.put(`${environment.API}/avaliacoes-professor`, avaliacao,{ responseType: 'text' });
  }

  public listarAvaliacoesDoProfessor(professorId: number): Observable<any> {
    return this.http.get(`${environment.API}/avaliacoes-professor/professor/${professorId}`)
  }

  public deletarAvaliacao(avaliacaoId: number): Observable<any> {
    return this.http.delete(`${environment.API}/avaliacoes-professor/${avaliacaoId}`,{ responseType: 'text' })
  }

}
