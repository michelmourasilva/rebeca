import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ProjetoService} from '../../../shared/service/projeto.service';
import {ProjetoModel} from '../../../shared/model/projeto.model';
import {ProjetoFormDialogComponent} from '../projeto-form-dialog/projeto-form-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {any} from 'codelyzer/util/function';
import {ConfiguracaoFormDialogComponent} from '../configuracao-form-dialog/configuracao-form-dialog.component';
import {MensagemDialogComponent} from '../mensagem-dialog/mensagem-dialog.component';
import {EndpointDialogComponent} from '../endpoint-dialog/endpoint-dialog.component';

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

  deleteProjeto(idProjeto: number, noProjeto: string, tipo: string): void {

    const dialogRef = this.dialog.open(MensagemDialogComponent, {
      panelClass: 'popup',
      minWidth: '200px',
      minHeight: '200px',
      data: {idProjeto, noProjeto, tipo}
    });
    console.log('deletando projeto', idProjeto) ;
  }

  openConfiguracao(idProjeto: number, noProjeto: string): void {
  const dialogRef = this.dialog.open(ConfiguracaoFormDialogComponent, {
    panelClass: 'popup',
    minWidth: '1500px',
    minHeight: '600px',
    data: {idProjeto, noProjeto}
  });

  dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openProjeto(idProjeto: number, tipo: string, noProjeto: string): void {
    const dialogRef = this.dialog.open(ProjetoFormDialogComponent, {
      panelClass: 'popup',
      minWidth: '400px',
      data: {idProjeto, tipo, noProjeto}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openURL(url): void{
    window.open(url);
  }

  openEndpoints(idProjeto: number,  noProjeto: string): void {
    const dialogRef = this.dialog.open(EndpointDialogComponent, {
      panelClass: 'popup',
      minWidth: '800px',
      minHeight: '400px',
      data: {idProjeto, noProjeto}
    });
  }
}
