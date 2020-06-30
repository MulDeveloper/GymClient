import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { Router, ActivatedRoute } from '@angular/router';
import { of, Observable } from 'rxjs';
import { Cliente } from '../modelos/cliente';
import swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  username:string='';
  password:string='';
  public cliente: Cliente;

  constructor(private service: LoginService,
    private router : Router,
    private activatedRoute : ActivatedRoute) { }

  ngOnInit(): void {
  }

  public login(){
      this.service.log(this.username, this.password);
  }




}
