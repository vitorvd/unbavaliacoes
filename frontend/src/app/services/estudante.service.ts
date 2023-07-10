import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Estudante} from "../models/Estudante";
import {Observable} from "rxjs";
import {environment} from "../../environments/env";

@Injectable({
  providedIn: 'root'
})
export class EstudanteService {

  constructor(private http: HttpClient) { }

  public autenticar(estudante: Estudante): Observable<any> {
    return this.http.post(`${environment.API}/estudante/auth`, estudante);
  }

  public criarEstudante(estudante: Estudante): Observable<any> {
    return this.http.post(`${environment.API}/estudante`, estudante, { responseType: 'text' });
  }

  public edtiarEstudante(estudante: Estudante): Observable<any> {
    return this.http.put(`${environment.API}/estudante/${estudante.matricula}`, estudante);
  }

  public getEstudante(matricula: string): Observable<any> {
    return this.http.get(`${environment.API}/estudante/${matricula}`);
  }

  public getEstudanteLogado(): Estudante {
    const estudanteString = localStorage.getItem("ESTUDANTE")
    const estudante: Estudante = estudanteString !== null ? JSON.parse(estudanteString) : new Estudante();
    return estudante;
  }

  public isAutenticado(): boolean {
    const matricula = localStorage.getItem("MATRICULA")
    return matricula !== null
  }

  public isAdministrador(): boolean {
    const estudanteString = localStorage.getItem("ESTUDANTE")
    const estudante: Estudante = estudanteString !== null ? JSON.parse(estudanteString) : new Estudante();
    return estudante.administrador
  }

}
