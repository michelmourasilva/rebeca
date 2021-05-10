import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ProjetoFormDialogComponent} from './projeto-form-dialog/projeto-form-dialog.component';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(public dialog: MatDialog, private sanitizer: DomSanitizer, private http: HttpClient) {}
  logoHtml: SafeHtml;

  ngOnInit(): void {
    this.http.get('/assets/images/Rebeca.svg', { responseType: 'text' })
      .subscribe(logo => {
        console.log(logo);
        this.logoHtml = this.sanitizer.bypassSecurityTrustHtml(logo);
      });
  }

  addProjeto(): void {
    const tipo = 'new';
    const idProjeto = 0;

    const dialogRef = this.dialog.open(ProjetoFormDialogComponent, {
      minWidth: '400px',
      panelClass: 'popup',
      data: {idProjeto, tipo}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }



}
