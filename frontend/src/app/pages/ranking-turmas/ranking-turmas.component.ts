import {Component, OnInit} from '@angular/core';
import {TurmasService} from "../../services/turmas.service";
import {TurmaRanking} from "../../models/TurmaRanking";

@Component({
  selector: 'app-ranking-turmas',
  templateUrl: './ranking-turmas.component.html',
  styleUrls: ['./ranking-turmas.component.css']
})
export class RankingTurmasComponent implements OnInit {

  turmas!: TurmaRanking[]

  constructor(private service: TurmasService) {

  }

  ngOnInit() {
    this.service.getRankingTurmas().subscribe((value: TurmaRanking[]) => {
      this.turmas = value;
    })
  }

}
