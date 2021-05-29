import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {EndPointModel} from '../model/endPoint.model';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DatasetService {


  constructor(
    private httpClient: HttpClient
  ) { }

  apiUrl = 'http://localhost:8081/dataset/';

  public getData(noProjeto: string, noModulo: string): Observable<any>{
    // tslint:disable-next-line:variable-name
    const _url = `${this.apiUrl}${noProjeto}/${noModulo}`;
    const retorno = this.httpClient.get<any>(_url);
    console.log('>>> url:', _url);
    return retorno;
  }

  // tslint:disable-next-line:typedef
  downloadFile(data, header, filename= 'data') {

    const csvData = this.ConvertToCSV(data, header);
    const blob = new Blob(['\ufeff' + csvData], { type: 'text/csv;charset=utf-8;' });
    const dwldLink = document.createElement('a');
    const url = URL.createObjectURL(blob);

    const isSafariBrowser = navigator.userAgent.indexOf('Safari') !== -1 && navigator.userAgent.indexOf('Chrome') === -1;
    if (isSafariBrowser) {  // if Safari open in new window to save file with random filename.
      dwldLink.setAttribute('target', '_blank');
    }
    dwldLink.setAttribute('href', url);
    dwldLink.setAttribute('download', filename + '.csv');
    dwldLink.style.visibility = 'hidden';
    document.body.appendChild(dwldLink);
    dwldLink.click();
    document.body.removeChild(dwldLink);
  }

  // tslint:disable-next-line:typedef
  ConvertToCSV(objArray, headerList) {
    const array = typeof objArray !== 'object' ? JSON.parse(objArray) : objArray;
    let str = '';
    let row = '"Numero_registro",';

    // tslint:disable-next-line:forin
    for (const index in headerList) {
      row += '"' + headerList[index] + '",';
    }
    row = row.slice(0, -1);
    str += row + '\r\n';
    for (let i = 0; i < array.length; i++) {
      let line = (i + 1) + '';
      for (const index in headerList) {
        const head = headerList[index];

        if (typeof (array[i][head]) !== 'number'){
          line += ',"' + array[i][head] + '"';
        } else {
          line += ',' + array[i][head];
        }
      }
      str += line + '\r\n';
    }
    return str;
  }



}
