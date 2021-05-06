import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {ResponsePageableModel} from '../model/responsePageable.model';
import {ProjetoModel} from '../model/projeto.model';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProjetoService {

  apiUrl = 'http://localhost:8081/projetos/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(
    private httpClient: HttpClient
  ) { }

  public getProjetos(): Observable<ProjetoModel> {
    return this.httpClient.get<ProjetoModel>(this.apiUrl);
  }

  public postProjeto(projeto: any): Observable<ProjetoModel>{
    // @ts-ignore
    return this.httpClient.post<any>(this.apiUrl, projeto, this.httpOptions).subscribe(() => console.log('Projeto cadastrado'));
  }

  public getProjeto(idProjeto: number): Observable<ProjetoModel>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}${idProjeto}`;
    return this.httpClient.get<ProjetoModel>(_url);
  }

  public updateProjeto(idProjeto: number, projeto: any): Observable<ProjetoModel>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}${idProjeto}`;
    // @ts-ignore
    return this.httpClient.put<any>(_url, projeto, this.httpOptions).subscribe(() => console.log('Projeto alterado'));
  }

  public deleteProjeto(idProjeto: number): Observable<ProjetoModel>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}${idProjeto}`;
    console.log(_url, idProjeto);
    // @ts-ignore
    return this.httpClient.delete(_url, this.httpOptions).subscribe(() => console.log('Projeto deletado'));
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
