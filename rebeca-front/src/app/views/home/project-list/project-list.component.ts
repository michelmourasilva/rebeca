import { Component, OnInit } from '@angular/core';
import {ProjetoService} from '../../../shared/service/projeto.service';
import {ProjetoModel} from '../../../shared/model/projeto.model';
import {ProjetoFormDialogComponent} from '../projeto-form-dialog/projeto-form-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {any} from 'codelyzer/util/function';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projetoLista: any = [];

  constructor(
    public projetoService: ProjetoService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.getProjetos();
  }

  // tslint:disable-next-line:typedef
  getProjetos(){
    this.projetoService.getProjetos().subscribe(
      data => {
        // console.log(typeof data);
        // console.log(typeof this.projetoLista.length);
        // console.log(this.projetoLista.length);
        this.projetoLista = data;
      }
    );
  }

  deleteProjeto(idProjeto: number): void {
    console.log('deletando projeto', idProjeto) ;
    this.projetoService.deleteProjeto(idProjeto);
    window.location.reload();
  }


  openProjeto(idProjeto: number, tipo: string): void {
    const dialogRef = this.dialog.open(ProjetoFormDialogComponent, {
      minWidth: '400px',
      data: {idProjeto, tipo}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
