import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {ConfiguracaoService} from '../../../shared/service/configuracao.service';
import {PageEvent} from '@angular/material/paginator';
import {DOCUMENT, formatDate} from '@angular/common';
import {environment} from '../../../../environments/environment';
import {EndPointService} from '../../../shared/service/endPoint.service';
import {DatasetService} from '../../../shared/service/dataset.service';


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
    private endPointService: EndPointService,
    private dataSetService: DatasetService,

  ) {
  }

  ngOnInit(): void {
    this.getConfiguracoesbyProjeto(this.data.noProjeto);
  }

  getDataset(noProjeto: string, dsProjeto: string): void{
    const jsonData =  this.dataSetService.getData(noProjeto, dsProjeto).subscribe(
      data => {

        const header = [];

        console.log(data);
        for (const i in data.metadados){
          header.push(data.metadados[i].nomeatributo);
          //header.push(['"', data.metadados[i].nomeatributo, '"'].join(''));
        }

        const date = new Date();

        const dataformatada = formatDate(date, 'yyyy_MM_dd', 'en-US');

        const nomearquivo = [dataformatada, noProjeto, dsProjeto].join('_');
        this.dataSetService.downloadFile(data.dados, header, nomearquivo);

      }
    );

  }

  refBibliografic(noProjeto: string, noModulo: string, localURL: string, endPoint: string): string{

    const date = new Date();

    const dataformatada = formatDate(date, 'yyyy-MM-dd', 'en-US');

    const retorno = [noProjeto, noModulo, localURL, endPoint, dataformatada].join(',');
    return retorno;
  }

  getData( event?: PageEvent): PageEvent {
    console.log(event);
    this.endPointsLista = this.resultado.slice(event.pageIndex * event.pageSize,
      event.pageIndex * event.pageSize + event.pageSize);
    console.log(this.endPointsLista);
    return event;
  }

  getConfiguracoesbyProjeto(noProjeto: string): void {
    this.endPointService.getEndPointbyProjeto(noProjeto).subscribe(
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
