import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LojaService {

  rootURL = '/cnab';

  constructor(private https: HttpClient) { }

  getLojas(): Observable<HttpEvent<{}>> {

    const newRequest = new HttpRequest('GET', this.rootURL + '/api/lojas', {
      reportProgress: true,
      responseType: 'json'
    });

    return this.https.request(newRequest);
  }
}
