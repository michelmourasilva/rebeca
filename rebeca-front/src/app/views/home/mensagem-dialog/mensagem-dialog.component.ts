import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ProjetoService} from '../../../shared/service/projeto.service';
import {ConfiguracaoService} from '../../../shared/service/configuracao.service';

@Component({
  selector: 'app-mensagem-dialog',
  templateUrl: './mensagem-dialog.component.html',
  styleUrls: ['./mensagem-dialog.component.css']
})
export class MensagemDialogComponent implements OnInit {

  constructor(
    public projetoService: ProjetoService,
    public configuracaoService: ConfiguracaoService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<MensagemDialogComponent>,
  ) { }

  ngOnInit(): void {
  }

  deletaProjeto(idProjeto: number, noProjeto: string): void{
    console.log('deletando projeto', idProjeto) ;
    this.projetoService.deleteProjeto(idProjeto);

    const tipo = 'delete_projeto_sucesso';

    const dialogRef = this.dialog.open(MensagemDialogComponent, {
      panelClass: 'popup',
      minWidth: '200px',
      minHeight: '200px',
      data: {idProjeto, noProjeto, tipo}
    });
    console.log('deletando projeto', idProjeto) ;

  }


  deletaConfiguracao(idConfiguracao: number, noConfiguracao: string): void{
    this.configuracaoService.deleteConfiguracao(idConfiguracao);
    const tipo = 'delete_configuracao_sucesso';
    const dialogRef = this.dialog.open(MensagemDialogComponent, {
      panelClass: 'popup',
      minWidth: '200px',
      minHeight: '200px',
      data: {idConfiguracao, noConfiguracao, tipo}
    });
    console.log('deletando projeto', idConfiguracao) ;
  }

  fechar(): void {
    this.dialogRef.close();
    window.location.reload();
  }
}
