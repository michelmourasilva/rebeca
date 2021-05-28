import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ConfiguracaoModel} from '../model/configuracao.model';
import {FiltroModel} from '../model/filtro.model';
import {ProjetoModel} from '../model/projeto.model';

@Injectable({
  providedIn: 'root'
})
export class FiltroService {

  apiUrl = 'http://localhost:8081/filtros/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(
    private httpClient: HttpClient
  ) {
  }

  public postFiltro(filtro: any): Observable<FiltroModel>{
    // @ts-ignore
    return this.httpClient.post<any>(this.apiUrl, filtro, this.httpOptions).subscribe(() => console.log('Filtro cadastrado'));
  }

  public deleteFiltro(idFiltro: number): Observable<FiltroModel>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}${idFiltro}`;
    // @ts-ignore
    return this.httpClient.delete(_url, this.httpOptions).subscribe(() => console.log('FIltro deletado'));
  }

}
