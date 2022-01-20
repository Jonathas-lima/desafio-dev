import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from './../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UploadFileServiceService {

  constructor(private https: HttpClient) { }

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const data: FormData = new FormData();

    data.append('file', file);
    const newRequest = new HttpRequest('POST', environment.apiUrl + '/api/upload', data, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.https.request(newRequest);
  }
}
