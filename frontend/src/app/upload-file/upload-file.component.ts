import { Component, OnInit } from '@angular/core';

import { FormGroup, FormControl, Validators} from '@angular/forms';
import { UploadFileServiceService } from './service/upload-file-service.service';
import { HttpResponse, HttpEventType } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  selectedFile = null;
  // @ts-ignore: Object is possibly 'null'
  selectedFiles: FileList;
  // @ts-ignore: Object is possibly 'null'
  currentFileUpload: File;

  myForm = new FormGroup({
    file: new FormControl('', [Validators.required])
  });

  constructor(private uploadService: UploadFileServiceService, private router: Router) {
    
   }

  ngOnInit(): void {
  }

  get form(){
    return this.myForm.controls;
  }

  submit() {
    this.upload();
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
  }

  private upload() {
   
    // @ts-ignore: Object is possibly 'null'
    this.currentFileUpload = this.selectedFiles.item(0);

    this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {

      if(event instanceof HttpResponse ) {
        this.router.navigate(['/lojas'])
      }
      // @ts-ignore: Object is possibly 'null'
      this.selectedFiles = undefined;
       // @ts-ignore: Object is possibly 'null'
      this.currentFileUpload = undefined;
     }
    );
  }

  limpar() {
    this.myForm.reset();
  }

}
