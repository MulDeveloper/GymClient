import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { of, Observable } from 'rxjs';
import { Tarifa } from '../modelos/tarifa';
import { Cliente } from '../modelos/cliente';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  private endPointGetCliente: string = 'http://localhost:8080/api/cliente/get';
  private endPointModificaCliente: string = 'http://localhost:8080/api/cliente/modifica';
  private endPointModificaAcceso: string = 'http://localhost:8080/api/cliente/modificaAcceso';
  private endPointGetTarifa: string = 'http://localhost:8080/api/cliente/get/tarifa';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient,private router: Router) { }

  getUsuario(nomusu: string): Observable<Cliente>{
    var params = new HttpParams();
    params = params.append('nomusu',nomusu);
    params = params.append('token',localStorage.getItem('auth_token'));
    return this.http.get<Cliente>(this.endPointGetCliente, {params:params});
  }

  getTarifa(idMat: Number):Observable<Number>{
    return this.http.get<Number>(`${this.endPointGetTarifa}/${idMat}`);
  }

  actualizaUsuario(cliente: Cliente):Observable<Cliente>{
    var params = new HttpParams();
    params = params.append('token',localStorage.getItem('auth_token'));
    return this.http.post<Cliente>(this.endPointModificaCliente, cliente, {headers: this.httpHeaders, params:params});
  }

  modificaAcceso(nueva:string, idcliente: string):void{
    var params = new HttpParams();
    params = params.append('nuevapass',nueva);
    params = params.append('idcliente',idcliente);
    params = params.append('token',localStorage.getItem('auth_token'));
    this.http.post<any>(this.endPointModificaAcceso, null, {params: params})
    .subscribe((resp: any) => {
          if (resp === 1){
            swal("Correcto", `Credenciales actualizadas con exito`, 'success');
            this.router.navigate(['/clases']);
          }});
  }

}
