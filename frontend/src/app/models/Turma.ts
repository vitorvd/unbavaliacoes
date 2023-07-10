import {Professor} from "./Professor";
import {Disciplina} from "./Disciplina";

export class Turma {
  id!: number;
  periodo!: string;
  professor: Professor = new Professor();
  disciplina: Disciplina = new Disciplina();
}
