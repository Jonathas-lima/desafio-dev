import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { LojaService } from './service/loja.service';

@Component({
  selector: 'app-lista-lojas',
  templateUrl: './lista-lojas.component.html',
  styleUrls: ['./lista-lojas.component.css']
})
export class ListaLojasComponent implements OnInit {

  constructor(private lojaService: LojaService) { }

  lojas: any;

  ngOnInit(): void {

    this.lojaService.getLojas().subscribe(event => {

      if (event instanceof HttpResponse) {
        this.lojas = event.body;
      }

    });

  }
}
