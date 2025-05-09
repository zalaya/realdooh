import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

  constructor(translate: TranslateService) {
    translate.addLangs(['es', 'en', 'fr', 'pt']);
    translate.setDefaultLang('es');

    const browser = translate.getBrowserLang();

    translate.use(browser?.match(/es|en|fr|pt/) ? browser! : 'es');
  }

}
