import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from './../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LojaService {

  constructor(private https: HttpClient) { }

  getLojas(): Observable<HttpEvent<{}>> {

    const newRequest = new HttpRequest('GET', environment.apiUrl + '/api/lojas', {
      reportProgress: true,
      responseType: 'json'
    });

    return this.https.request(newRequest);
  }
}
