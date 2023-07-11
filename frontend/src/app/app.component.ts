import { Component } from '@angular/core';
import {MessageService} from "primeng/api";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [MessageService]
})
export class AppComponent {
  title = 'UnB Avaliações';

  constructor(private titleService: Title) {
    titleService.setTitle("UnB Avaliações");
  }

}
