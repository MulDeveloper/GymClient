import { Component, OnInit } from '@angular/core';
import { RegistroService } from './registro.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Cliente } from '../modelos/cliente';
import { Tarifa } from '../modelos/tarifa';
import swal from 'sweetalert2';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls:['./registro.css']
})
export class RegistroComponent implements OnInit {


  public tarifas: Tarifa[] = [];

  public cliente: Cliente = new Cliente();

  constructor(private service: RegistroService,
    private router : Router,
    private activatedRoute : ActivatedRoute
  ) { }

  ngOnInit(): void {
    //populamos la lista de tarifas
    this.service.getTarifas().subscribe(
      //asignamos
      tarifas => this.tarifas = tarifas
    );
  }

  public create(): void{
    this.service.altaCliente(this.cliente).subscribe(
      response => {
        this.router.navigate(['/home'])
        swal("Nuevo cliente", `Cliente ${response.nombreCliente} creado correctamente`, 'success')
      }
    )}

}
