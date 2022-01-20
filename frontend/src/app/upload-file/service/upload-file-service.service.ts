import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadFileServiceService {

  constructor(private https: HttpClient) { }

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const data: FormData = new FormData();
    
    data.append('file', file);
    const newRequest = new HttpRequest('POST', 'http://localhost:8080/api/upload', data, {
      reportProgress: true,
      responseType: 'json'
    });
    
    return this.https.request(newRequest);
  }
}
