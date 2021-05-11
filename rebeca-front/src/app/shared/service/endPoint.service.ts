import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {ResponsePageableModel} from '../model/responsePageable.model';
import {ProjetoModel} from '../model/projeto.model';
import { retry, catchError } from 'rxjs/operators';
import {ConfiguracaoModel} from '../model/configuracao.model';
import {EndPointModel} from '../model/endPoint.model';

@Injectable({
  providedIn: 'root'
})
export class EndPointService {

  apiUrl = 'http://localhost:8081/endpoints/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(
    private httpClient: HttpClient
  ) { }

  public getEndPointbyProjeto(dsProjeto: string): Observable<EndPointModel>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}${dsProjeto}`;
    return this.httpClient.get<EndPointModel>(_url);
  }
}
