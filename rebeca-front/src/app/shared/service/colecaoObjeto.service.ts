import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {ColecaoObjetoModel} from '../model/colecaoObjeto.model';
import {ColecaoAtributosModel} from '../model/colecaoAtributos.model';

@Injectable({
  providedIn: 'root'
})
export class ColecaoObjetoService {

  apiUrl = 'http://localhost:8081/dataset/colecao/objetos/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(
    private httpClient: HttpClient
  ) { }

  public getColecaoObjeto(): Observable<ColecaoObjetoModel> {
    return this.httpClient.get<ColecaoObjetoModel>(this.apiUrl);
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
