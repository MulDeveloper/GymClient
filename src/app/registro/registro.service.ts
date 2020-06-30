import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cliente } from '../modelos/cliente';
import { of, Observable } from 'rxjs';
import { Tarifa } from '../modelos/tarifa';

@Injectable({
  providedIn: 'root'
})
export class RegistroService {

  private endPointAlta: string = 'http://localhost:8080/api/cliente';
  private endPointListaTarifas: string = 'http://localhost:8080/api/tarifas';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});


  constructor(private http: HttpClient) { }

  altaCliente(cliente: Cliente): Observable<Cliente>{
      return this.http.post<Cliente>(this.endPointAlta, cliente, {headers: this.httpHeaders});
  }

  getTarifas(): Observable<Tarifa[]>{
    return this.http.get<Tarifa[]>(this.endPointListaTarifas);
  }



}
