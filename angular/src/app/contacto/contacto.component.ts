import { Component, OnInit } from '@angular/core';
import {Formulario} from '../modelos/formulario';
import {ContactoService} from './contacto.service';

@Component({
  selector: 'app-contacto',
  templateUrl: './contacto.component.html'
})
export class ContactoComponent implements OnInit {

  public form: Formulario = new Formulario();

  constructor(private service: ContactoService) { }

  ngOnInit(): void {
  }

  enviarFormulario(){
    //hacemos la peticion al service que hace la peticion al backend enviandole el objeto formulario
    this.service.enviaEmail(this.form);
  }

}
