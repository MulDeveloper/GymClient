import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { of, Observable} from 'rxjs';
import { Clase } from '../modelos/clase';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ClasesService {

  private endPointClases: string = 'http://localhost:8080/api/clases';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient,private router: Router) { }

  getClases(): Observable<Map<string, Clase[]>>{
    return this.http.get<Map<string, Clase[]>>(this.endPointClases);
  }

  reservaClase(nomusu: string, idclase:string): void{
    var params = new HttpParams();
    params = params.append('nomusu',nomusu);
    params = params.append('id',idclase);
    params = params.append('token',localStorage.getItem('auth_token'));
    this.http.post(this.endPointClases, null,{params: params})
    .subscribe((resp: any) => {
      if(resp){
        swal(
          'Confirmado',
          'Has reservado una clase',
          'success'
        )
      }
      else{
        swal(
          'Error',
          'Ya habias reservado esta clase',
          'error'
        )
      }
    });

  }





}
