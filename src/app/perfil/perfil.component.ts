import { Component, OnInit,AfterViewInit } from '@angular/core';
import { PerfilService } from './perfil.service';
import { Cliente } from '../modelos/cliente';
import { of, Observable } from 'rxjs';
import { Tarifa } from '../modelos/tarifa';
import { RegistroService } from '../registro/registro.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls:['../registro/registro.css']
})
export class PerfilComponent implements OnInit, AfterViewInit {

  private nomusu:string;
  public cliente: Cliente;
  public tarifas: Tarifa[] = [];
  public tarifa: Number;
  public pass: string;

  constructor(private service: PerfilService, private serviceTarifas: RegistroService,private router: Router) { }

  ngOnInit(): void {
    this.nomusu = localStorage.getItem('nomusu');

    if (this.nomusu === null || this.nomusu === ""){
      this.router.navigate(['/']);
    }

    this.serviceTarifas.getTarifas().subscribe(
      //asignamos
      tarifas => this.tarifas = tarifas
    );

    this.service.getUsuario(this.nomusu).subscribe(
       //asignamos el valor
       (cliente) =>  this.cliente = cliente
    );

  }

  ngAfterViewInit():void{
    this.service.getTarifa(this.cliente.idMatricula).subscribe(
       //asignamos el valor
       (tarifa) =>  this.tarifa = tarifa
    );
  }


  public modificar(): void{
    this.service.actualizaUsuario(this.cliente).subscribe();
    swal(
      'Actualizado',
      'Actualizado correctamente',
      'success'
    )

  }

  public modificarPass(){
    this.service.modificaAcceso(this.pass, this.cliente.idcliente.toString());
  }

}
