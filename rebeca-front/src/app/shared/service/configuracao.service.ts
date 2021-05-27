import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {ResponsePageableModel} from '../model/responsePageable.model';
import {ProjetoModel} from '../model/projeto.model';
import { retry, catchError } from 'rxjs/operators';
import {ConfiguracaoModel} from '../model/configuracao.model';

@Injectable({
  providedIn: 'root'
})
export class ConfiguracaoService {

  apiUrl = 'http://localhost:8081/configuracoes/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(
    private httpClient: HttpClient
  ) { }

  public getConfiguracoes(): Observable<ConfiguracaoModel> {
    return this.httpClient.get<ConfiguracaoModel>(this.apiUrl);
  }

  public getConfiguracaobyId(idConfiguracao: number): Observable<ConfiguracaoModel> {
    const _url = `${this.apiUrl}${idConfiguracao}`;
    return this.httpClient.get<ConfiguracaoModel>(_url);
  }


  public getConfiguracaobyProjeto(idProjeto: number): Observable<ConfiguracaoModel>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}projeto/${idProjeto}`;
    return this.httpClient.get<ConfiguracaoModel>(_url);
  }

  public postConfiguracao(configuracao: any): Observable<any>{
    // @ts-ignore
    return this.httpClient.post<any>(this.apiUrl, configuracao, this.httpOptions);
  }

  public deleteConfiguracao(idConfiguracao: number): Observable<ConfiguracaoModel>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}${idConfiguracao}`;
    console.log(_url, idConfiguracao);
    // @ts-ignore
    return this.httpClient.delete(_url, this.httpOptions).subscribe(() => console.log('Configuracao deletada'));
  }

  // tslint:disable-next-line:typedef
  httpError(error) {
    let msg = '';
    if (error.error instanceof ErrorEvent) {
      // client side error
      msg = error.error.message;
    } else {
      // server side error
      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(msg);
    return throwError(msg);
  }

}
