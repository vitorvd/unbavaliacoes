import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuPrincipalComponent } from './shared/menu-principal/menu-principal.component';
import { CadastroEstudanteComponent } from './pages/cadastro-estudante/cadastro-estudante.component';
import { InputCustomComponent } from './shared/input-custom/input-custom.component';
import {ReactiveFormsModule} from "@angular/forms";
import { ButtonCustomComponent } from './shared/button-custom/button-custom.component';
import {ToastModule} from "primeng/toast";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import { LoginComponent } from './pages/login/login.component';
import { ListagemTurmasComponent } from './pages/listagem-turmas/listagem-turmas.component';
import { CadastroTurmaComponent } from './pages/cadastro-turma/cadastro-turma.component';
import { InputDropdownComponent } from './shared/input-dropdown/input-dropdown.component';
import {PaginatorModule} from "primeng/paginator";
import { ListagemAvaliacoesTurmaComponent } from './pages/listagem-avaliacoes-turma/listagem-avaliacoes-turma.component';
import { CadastroAvaliacaoTurmaComponent } from './pages/cadastro-avaliacao-turma/cadastro-avaliacao-turma.component';
import { ListagemProfessoresComponent } from './pages/listagem-professores/listagem-professores.component';
import { CadastroProfessorComponent } from './pages/cadastro-professor/cadastro-professor.component';
import { CadastroAvaliacaoProfessorComponent } from './pages/cadastro-avaliacao-professor/cadastro-avaliacao-professor.component';
import { ListagemAvaliacaoProfessorComponent } from './pages/listagem-avaliacao-professor/listagem-avaliacao-professor.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuPrincipalComponent,
    CadastroEstudanteComponent,
    InputCustomComponent,
    ButtonCustomComponent,
    LoginComponent,
    ListagemTurmasComponent,
    CadastroTurmaComponent,
    InputDropdownComponent,
    ListagemAvaliacoesTurmaComponent,
    CadastroAvaliacaoTurmaComponent,
    ListagemProfessoresComponent,
    CadastroProfessorComponent,
    CadastroAvaliacaoProfessorComponent,
    ListagemAvaliacaoProfessorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    ToastModule,
    PaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
