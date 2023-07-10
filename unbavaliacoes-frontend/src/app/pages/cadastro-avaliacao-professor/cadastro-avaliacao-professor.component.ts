import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {catchError, Subscription, throwError} from "rxjs";
import {AvaliacaoTurma} from "../../models/AvaliacaoTurma";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {AvaliacoesProfessorService} from "../../services/avaliacoes-professor.service";
import {Professor} from "../../models/Professor";

@Component({
  selector: 'app-cadastro-avaliacao-professor',
  templateUrl: './cadastro-avaliacao-professor.component.html',
  styleUrls: ['./cadastro-avaliacao-professor.component.css']
})
export class CadastroAvaliacaoProfessorComponent implements OnInit, OnDestroy {

  formCadastroAvaliacao!: FormGroup;

  avaliacaoEdicaoSubscription!: Subscription;
  professorAdicionarAvaliacaoSubscription!: Subscription;

  isViewEdicao: boolean = false;

  avaliacao: AvaliacaoTurma = new AvaliacaoTurma();

  constructor(private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private messageService: MessageService,
              private service: AvaliacoesProfessorService) {}


  ngOnInit(): void {
    this.professorAdicionarAvaliacaoSubscription = this.service.professorAdicionarAvaliacao.subscribe((value: Professor) => {
      this.avaliacao.idProfessor = value.id

      const matricula = localStorage.getItem("MATRICULA")
      this.avaliacao.matriculaEstudante = matricula !== null ? matricula : 'default';
    })

    this.formCadastroAvaliacao = this.formBuilder.group({
      avaliacao: ['', [Validators.required]],
      nota: ['', [Validators.required]],
    })

    this.avaliacaoEdicaoSubscription = this.service.avaliacaoEdicao$.subscribe((value: AvaliacaoTurma) => {
      this.avaliacao = value

      this.formCadastroAvaliacao.controls['avaliacao'].setValue(this.avaliacao.avaliacao);
      this.formCadastroAvaliacao.controls['nota'].setValue(this.avaliacao.nota);

      this.isViewEdicao = true;
    })
  }

  ngOnDestroy() {
    this.avaliacaoEdicaoSubscription.unsubscribe();
    this.professorAdicionarAvaliacaoSubscription.unsubscribe();
  }

  onSubmit(){
    if(this.formCadastroAvaliacao.invalid){
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Os campos: Avaliação e Nota são obrigatórios.' });
      return
    }

    this.avaliacao.avaliacao = this.formCadastroAvaliacao.value.avaliacao

    const notaNumber = Number(this.formCadastroAvaliacao.value.nota);
    this.avaliacao.nota = notaNumber > 5 ? 5 : notaNumber < 0 ? 0 : notaNumber; //Valores minimo: 0 e máximo: 5

    if(this.isViewEdicao){
      this.service.editarAvaliacaoDoProfessor(this.avaliacao).subscribe(value => {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: value });
        this.router.navigateByUrl("/professores")
        this.isViewEdicao = false;
        this.avaliacaoEdicaoSubscription.unsubscribe();
        this.formCadastroAvaliacao.reset()
      })
    }else{
      this.service.criarAvaliacaoDoProfessor(this.avaliacao).pipe(catchError(error => {
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: error.error });
        return throwError(error.error)
      })).subscribe(value => {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: value });
        this.professorAdicionarAvaliacaoSubscription.unsubscribe();
        this.router.navigateByUrl("/professores")
      })
    }
  }

  getTextoBotao(): string {
    return this.isViewEdicao ? "Editar" : "Cadastrar"
  }

}
