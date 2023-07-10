import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {catchError, min, Subscription, throwError} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {AvaliacoesTurmaService} from "../../services/avaliacoes-turma.service";
import {AvaliacaoTurma} from "../../models/AvaliacaoTurma";
import {Turma} from "../../models/Turma";

@Component({
  selector: 'app-cadastro-avaliacao-turma',
  templateUrl: './cadastro-avaliacao-turma.component.html',
  styleUrls: ['./cadastro-avaliacao-turma.component.css']
})
export class CadastroAvaliacaoTurmaComponent implements OnInit, OnDestroy {

  formCadsatroAvaliacao!: FormGroup;

  avaliacaoEdicaoSubscription!: Subscription;
  turmaAdicionarAvaliacaoSubscription!: Subscription;

  isViewEdicao: boolean = false;

  avaliacao: AvaliacaoTurma = new AvaliacaoTurma();

  constructor(private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private messageService: MessageService,
              private service: AvaliacoesTurmaService) {}


  ngOnInit(): void {
    this.turmaAdicionarAvaliacaoSubscription = this.service.turmaAdicionarAvaliacao.subscribe((value: Turma) => {
      this.avaliacao.idTurma = value.id

      const matricula = localStorage.getItem("MATRICULA")
      this.avaliacao.matriculaEstudante = matricula !== null ? matricula : 'default';
    })

    this.formCadsatroAvaliacao = this.formBuilder.group({
      avaliacao: ['', [Validators.required]],
      nota: ['', [Validators.required]],
    })

    this.avaliacaoEdicaoSubscription = this.service.avaliacaoEdicao$.subscribe((value: AvaliacaoTurma) => {
      this.avaliacao = value
      this.formCadsatroAvaliacao.controls['avaliacao'].setValue(this.avaliacao.avaliacao);
      this.formCadsatroAvaliacao.controls['nota'].setValue(this.avaliacao.nota);

      this.isViewEdicao = true;
    })
  }

  ngOnDestroy() {
    this.avaliacaoEdicaoSubscription.unsubscribe();
    this.turmaAdicionarAvaliacaoSubscription.unsubscribe();
  }

  onSubmit(){
    if(this.formCadsatroAvaliacao.invalid){
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Os campos: Avaliação e Nota são obrigatórios.' });
      return
    }

    this.avaliacao.avaliacao = this.formCadsatroAvaliacao.value.avaliacao

    const notaNumber = Number(this.formCadsatroAvaliacao.value.nota);
    this.avaliacao.nota = notaNumber > 5 ? 5 : notaNumber < 0 ? 0 : notaNumber; //Valores minimo: 0 e máximo: 5

    if(this.isViewEdicao){
      this.service.editarAvaliacaoDaTurma(this.avaliacao).subscribe(value => {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: value });
        this.router.navigateByUrl("/turmas")
        this.isViewEdicao = false;
        this.service.avaliacaoEdicao$.unsubscribe()
        this.avaliacaoEdicaoSubscription.unsubscribe();
        this.formCadsatroAvaliacao.reset()
      })
    }else{
      this.service.criarAvaliacaoDaTurma(this.avaliacao).pipe(catchError(error => {
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: error.error });
        return throwError(error.error)
      })).subscribe(value => {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: value });
        this.turmaAdicionarAvaliacaoSubscription.unsubscribe();
        this.router.navigateByUrl("/turmas")
      })
    }
  }

  getTextoBotao(): string {
    return this.isViewEdicao ? "Editar" : "Cadastrar"
  }

  toNumber(valor: any): number {
    return Number(valor)
  }

}
