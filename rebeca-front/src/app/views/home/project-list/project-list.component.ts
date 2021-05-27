import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ProjetoService} from '../../../shared/service/projeto.service';
import {ProjetoModel} from '../../../shared/model/projeto.model';
import {ProjetoFormDialogComponent} from '../projeto-form-dialog/projeto-form-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {any} from 'codelyzer/util/function';
import {ConfiguracaoFormDialogComponent} from '../configuracao-form-dialog/configuracao-form-dialog.component';
import {MensagemDialogComponent} from '../mensagem-dialog/mensagem-dialog.component';
import {EndpointDialogComponent} from '../endpoint-dialog/endpoint-dialog.component';
import {ConfiguracaoService} from '../../../shared/service/configuracao.service';
import {ConfiguracaoModel} from '../../../shared/model/configuracao.model';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projetoLista: any = [];
  resultado: any = [];
  length: number;

  constructor(
    public projetoService: ProjetoService,
    public configuracaoService: ConfiguracaoService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.getProjetos();
  }

  // tslint:disable-next-line:typedef
  getProjetos(){
    this.projetoService.getProjetos().subscribe(
      data => {
        for (const i in data){
          data[i].configuracoes = [];
          this.configuracaoService.getConfiguracaobyProjeto(data[i].idProjeto).subscribe(
            (result: any) => {

              if (result){
                for (const results in result) {
                  data[i].configuracoes.push(result[results].idConfiguracao);
                }
              }
            });
        }
        this.projetoLista = data;
        console.log(data);
      }
    );
  }


  deleteProjeto(idProjeto: number, noProjeto: string, tipo: string, qtdConfiguracao: number): void {

    if (qtdConfiguracao > 0) {
      tipo = 'delete_negado';
      const dialogRef = this.dialog.open(MensagemDialogComponent, {
        panelClass: 'popup',
        minWidth: '200px',
        minHeight: '200px',
        data: {idProjeto, noProjeto, tipo}
      });
    } else {
      const dialogRef = this.dialog.open(MensagemDialogComponent, {
        panelClass: 'popup',
        minWidth: '200px',
        minHeight: '200px',
        data: {idProjeto, noProjeto, tipo}
      });
    }
  }

  openConfiguracao(idProjeto: number, noProjeto: string): void {
  const dialogRef = this.dialog.open(ConfiguracaoFormDialogComponent, {
    panelClass: 'popup',
    width: 'fixed',
    height: 'fixed',
    minWidth: '1500px',
    maxWidth: '1500px',
    minHeight: '785px',
    maxHeight: '785px',
    data: {idProjeto, noProjeto},
    disableClose: true
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
