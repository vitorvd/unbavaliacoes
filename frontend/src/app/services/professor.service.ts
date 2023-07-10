import { Injectable } from '@angular/core';
import {Observable, ReplaySubject} from "rxjs";
import {Professor} from "../models/Professor";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/env";

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {

  professorEdicao$: ReplaySubject<Professor> = new ReplaySubject<Professor>();

  professorListagemAvaliacao$: ReplaySubject<Professor> = new ReplaySubject<Professor>();

  constructor(private http: HttpClient) { }

  public criarProfessor(professor: Professor): Observable<any> {
    return this.http.post(`${environment.API}/professor`, professor,{ responseType: 'text' });
  }

  public editarProfessor(professor: Professor): Observable<any> {
    return this.http.put(`${environment.API}/professor`, professor,{ responseType: 'text' });
  }

  public getTodosProfessores(): Observable<any> {
    return this.http.get(`${environment.API}/professor`);
  }

  public deletarProfessor(professorId: number): Observable<any> {
    return this.http.delete(`${environment.API}/professor/${professorId}`, { responseType: 'text' });
  }

}
