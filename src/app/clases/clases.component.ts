import { Component, OnInit } from '@angular/core';
import { Clase } from '../modelos/clase';
import { ClasesService } from './clases.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ClasesDia } from '../modelos/clasesdia';
import { of, Observable } from 'rxjs';
import swal from 'sweetalert2';

@Component({
  selector: 'app-clases',
  templateUrl: './clases.component.html'
})
export class ClasesComponent implements OnInit {

  clasesSemana:  Map<string, Clase[]>;

  private nomusu: string;
  private isLogueado: boolean;
  private res: Observable<Boolean>;


  constructor(private service: ClasesService,
    private router : Router,
    private activatedRoute : ActivatedRoute) { }

  ngOnInit(): void {
    this.service.getClases().subscribe(
      //asignamos
      clasesSemana => this.clasesSemana = clasesSemana
    );

    this.nomusu = localStorage.getItem('nomusu');
    if (this.nomusu != null){
      //esta logueado, podemos reservar clases
      this.isLogueado = true;
    }
    else{
      //no esta logueado, si clicamos lo mandamos al login
      this.isLogueado = false;
    }


  }

  public getDia(key: number): string{
    switch (key) {
      case 1:
        return "LUNES";
        break;

      case 1:
        return "MARTES";
        break;

      case 3:
        return "MIERCOLES";
        break;

      case 4:
        return "JUEVES";
        break;

      case 5:
          return "VIERNES";
      break;

      case 6:
        return "SABADO";
        break;

      case 7:
        return "DOMINGO";
        break;

      default:
        break;
    }
  }

  public reservaClase(id: string, nomclase: string, hora:string):void{

    if (this.nomusu === null || this.nomusu === ""){
      swal(
        'Error',
        'No estas logueado',
        'error'
      )
    }
    else{
      swal({
        title: 'Quieres reservar '+ nomclase +' a las ' + hora + '?',
        text: "",
        type: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, reservar'
      }).then((result) => {
        if (result.value) {
          //llamamos al servicio para hacer la peticion POST al Backend
          this.service.reservaClase(this.nomusu,id);
        }
      })

    }
  }





}
