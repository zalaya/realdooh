import { Component } from '@angular/core';
import { CommonModule }    from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { TranslateModule } from '@ngx-translate/core';
import { RouterModule }    from '@angular/router';

@Component({
  selector: 'app-alternative-access',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    TranslateModule,
    RouterModule
  ],
  templateUrl: './alternative-access.component.html',
  styleUrls: ['./alternative-access.component.scss']
})
export class AlternativeAccessComponent {}
