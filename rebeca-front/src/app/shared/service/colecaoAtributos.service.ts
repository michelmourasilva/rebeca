import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {ColecaoAtributosModel} from '../model/colecaoAtributos.model';

@Injectable({
  providedIn: 'root'
})
export class ColecaoAtributosService {

  apiUrl = 'http://localhost:8081/dataset/colecao/atributos/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(
    private httpClient: HttpClient
  ) { }

  public getColecaoAtributos(noObjeto: string): Observable<ColecaoAtributosModel>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}${noObjeto}`;
    return this.httpClient.get<ColecaoAtributosModel>(_url);
  }
}
