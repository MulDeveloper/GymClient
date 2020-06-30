import { Injectable } from '@angular/core';
import { Formulario } from '../modelos/formulario';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { of, Observable } from 'rxjs';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ContactoService {

  private endPointContacto: string = 'http://localhost:8080/api/contacto';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) { }

  enviaEmail(formulario: Formulario): void{
      this.http.post<Formulario>(this.endPointContacto, formulario, {headers: this.httpHeaders}).subscribe((resp: any) => {
        if(resp){
          //se ha enviado correctamente
          swal(
            'Correcto',
            'Mensaje enviado correctamente',
            'success'
          )
        }
        else{
          swal(
            'Error',
            'Mensaje no enviado',
            'error'
          )
        }

      });
  }

}
