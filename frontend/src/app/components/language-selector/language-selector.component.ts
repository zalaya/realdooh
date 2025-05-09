import { Component, OnInit } from '@angular/core';
import { CommonModule }       from '@angular/common';
import { MatMenuModule }      from '@angular/material/menu';
import { MatButtonModule }    from '@angular/material/button';
import { MatIconModule }      from '@angular/material/icon';
import { TranslateModule, TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-language-selector',
  standalone: true,
  imports: [
    CommonModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    TranslateModule
  ],
  templateUrl: './language-selector.component.html',
  styleUrls: ['./language-selector.component.scss']
})
export class LanguageSelectorComponent implements OnInit {
  currentLang!: string;

  constructor(private translate: TranslateService) {}

  ngOnInit() {
    this.currentLang = (this.translate.currentLang || this.translate.getDefaultLang()).toUpperCase();
  }
  
  switch(lang: string) {
    this.translate.use(lang);
    this.currentLang = lang.toUpperCase();
  }
}
