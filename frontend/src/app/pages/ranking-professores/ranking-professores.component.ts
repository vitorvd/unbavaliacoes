import {Component, OnInit} from '@angular/core';
import {ProfessorRanking} from "../../models/ProfessorRanking";
import {ProfessorService} from "../../services/professor.service";

@Component({
  selector: 'app-ranking-professores',
  templateUrl: './ranking-professores.component.html',
  styleUrls: ['./ranking-professores.component.css']
})
export class RankingProfessoresComponent implements OnInit {

  professores!: ProfessorRanking[]

  constructor(private service: ProfessorService) {

  }

  ngOnInit() {
    this.service.getRankingProfessores().subscribe((value: ProfessorRanking[]) => {
      this.professores = value;
    })
  }

}
