import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {ConfiguracaoService} from '../../../shared/service/configuracao.service';
import {PageEvent} from '@angular/material/paginator';
import {DOCUMENT} from '@angular/common';
import {environment} from '../../../../environments/environment';
import {EndPointService} from '../../../shared/service/endPoint.service';

@Component({
  selector: 'app-endpoint-dialog',
  templateUrl: './endpoint-dialog.component.html',
  styleUrls: ['./endpoint-dialog.component.css']
})
export class EndpointDialogComponent implements OnInit {

  endPointsLista: any = [];
  resultado: any = [];

  length: number;
  pageSize = 5;
  pageSizeOptions: number[] = [1, 2, 5];
  pageEvent: PageEvent;

  // localURL = this.document.location.origin;
  localURL = environment.urlbaseBackend;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    @Inject(DOCUMENT) private document: Document,
    private rest: EndPointService,
  ) {
  }

  ngOnInit(): void {
    this.getConfiguracoesbyProjeto(this.data.noProjeto);
  }


  // tslint:disable-next-line:typedef
  getData( event?: PageEvent) {
    console.log(event);
    this.endPointsLista = this.resultado.slice(event.pageIndex * event.pageSize,
      event.pageIndex * event.pageSize + event.pageSize);
    console.log(this.endPointsLista);
    return event;
  }

  // tslint:disable-next-line:typedef
  getConfiguracoesbyProjeto(noProjeto: string){
    this.rest.getEndPointbyProjeto(noProjeto).subscribe(
      data => {
        this.resultado = data;
        this.length = this.resultado.length;
        this.endPointsLista = this.resultado.slice(0, this.pageSize);
      }
    );
  }

  openURL(url): void{
    console.log(url);
    window.open(url);
  }



}
