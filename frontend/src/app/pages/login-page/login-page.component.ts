import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { AlternativeAccessComponent } from '../../components/alternative-access/alternative-access.component';
import { LanguageSelectorComponent } from '../../components/language-selector/language-selector.component';
import { LoginFormComponent } from '../../components/login-form/login-form.component';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    LoginFormComponent,
    AlternativeAccessComponent,
    LanguageSelectorComponent
  ],
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent { }
