import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ResponsePageableModel} from '../model/responsePageable.model';
import {ProjetoModel} from '../model/projeto.model';

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

  public postProjetos(projeto: any): Observable<ProjetoModel>{
    return this.httpClient.post<any>(this.apiUrl, projeto, this.httpOptions);
  }

  public getProjeto(idProjeto: number): Observable<ProjetoModel>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}/${idProjeto}`;
    return this.httpClient.get<ProjetoModel>(this.apiUrl + '/' + idProjeto);
  }

  public updateProjeto(idProjeto: number, projeto: any): Observable<ProjetoModel>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}/${idProjeto}`;
    return this.httpClient.put<any>(this.apiUrl, projeto, this.httpOptions);
  }

  public deleteProjeto(idProjeto: number): Observable<ProjetoModel>{
    return this.httpClient.delete<any>(this.apiUrl + '/' + idProjeto);
  }

}
