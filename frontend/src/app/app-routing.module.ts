import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CadastroEstudanteComponent} from "./pages/cadastro-estudante/cadastro-estudante.component";
import {LoginComponent} from "./pages/login/login.component";
import {ListagemTurmasComponent} from "./pages/listagem-turmas/listagem-turmas.component";
import {CadastroTurmaComponent} from "./pages/cadastro-turma/cadastro-turma.component";
import {ListagemAvaliacoesTurmaComponent} from "./pages/listagem-avaliacoes-turma/listagem-avaliacoes-turma.component";
import {CadastroAvaliacaoTurmaComponent} from "./pages/cadastro-avaliacao-turma/cadastro-avaliacao-turma.component";
import {ListagemProfessoresComponent} from "./pages/listagem-professores/listagem-professores.component";
import {CadastroProfessorComponent} from "./pages/cadastro-professor/cadastro-professor.component";
import {
  ListagemAvaliacaoProfessorComponent
} from "./pages/listagem-avaliacao-professor/listagem-avaliacao-professor.component";
import {
  CadastroAvaliacaoProfessorComponent
} from "./pages/cadastro-avaliacao-professor/cadastro-avaliacao-professor.component";

const routes: Routes = [
  {path: "auth", component: LoginComponent},
  {path: "cadastrar", component: CadastroEstudanteComponent},
  {path: "turmas", component: ListagemTurmasComponent},
  {path: "turmas/cadastrar", component: CadastroTurmaComponent},
  {path: "turmas/editar", component: CadastroTurmaComponent},
  {path: "turmas/avaliacoes", component: ListagemAvaliacoesTurmaComponent},
  {path: "turmas/avaliacoes/cadastrar", component: CadastroAvaliacaoTurmaComponent},
  {path: "turmas/avaliacoes/editar", component: CadastroAvaliacaoTurmaComponent},
  {path: "professores", component: ListagemProfessoresComponent},
  {path: "professores/cadastrar", component: CadastroProfessorComponent},
  {path: "professores/editar", component: CadastroProfessorComponent},
  {path: "professores/avaliacoes", component: ListagemAvaliacaoProfessorComponent},
  {path: "professores/avaliacoes/cadastrar", component: CadastroAvaliacaoProfessorComponent},
  {path: "professores/avaliacoes/editar", component: CadastroAvaliacaoProfessorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
