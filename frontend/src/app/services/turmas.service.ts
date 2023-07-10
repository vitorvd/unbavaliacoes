import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, ReplaySubject} from "rxjs";
import {environment} from "../../environments/env";
import {Turma} from "../models/Turma";

@Injectable({
  providedIn: 'root'
})
export class TurmasService {

  turmaEdicao$: ReplaySubject<Turma> = new ReplaySubject<Turma>();

  turmaListagemAvaliacao$: ReplaySubject<Turma> = new ReplaySubject<Turma>();

  constructor(private http: HttpClient) { }

  public criarTurma(turma: Turma): Observable<any> {
    return this.http.post(`${environment.API}/turma`, turma,{ responseType: 'text' });
  }

  public editarTurma(turma: Turma): Observable<any> {
    return this.http.put(`${environment.API}/turma`, turma,{ responseType: 'text' });
  }

  public getTodasTurmas(): Observable<any> {
    return this.http.get(`${environment.API}/turma`);
  }

  public getRankingTurmas(): Observable<any> {
    return this.http.get(`${environment.API}/turma/ranking`);
  }

  public deletarTurma(turmaId: number): Observable<any> {
    return this.http.delete(`${environment.API}/turma/${turmaId}`, { responseType: 'text' });
  }

}
