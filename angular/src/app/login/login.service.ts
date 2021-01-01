import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { of, Observable } from 'rxjs';
import { Cliente } from '../modelos/cliente';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private endPointLogin: string = 'http://localhost:8080/login';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Headers': 'Content-Type'});


  constructor(private http: HttpClient,private router: Router){ }

  log(username:string, pass:string){
    var params = new HttpParams();
    params = params.append('username',username);
    params = params.append('password',pass);

      this.http.post(this.endPointLogin, null, {params: params})
      .subscribe((resp: any) => {
          if (resp.estado === 1){
            //el login es correcto, redirige a clases
            localStorage.setItem('auth_token', resp.token);
            localStorage.setItem('nomusu', resp.nomusu);
            location.replace('/clases');
          }
        },
        (error: HttpErrorResponse) => {
          swal(
            'Error',
            'Error en las credenciales',
            'error'
          )
        }


      );



    }






}
