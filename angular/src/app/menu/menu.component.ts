import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Cliente } from '../modelos/cliente';
import { PerfilService } from '../perfil/perfil.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {

  public nomusu: string;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.nomusu = localStorage.getItem('nomusu');
  }

  salir():void{
    localStorage.removeItem('auth_token');
    localStorage.removeItem('nomusu');
    location.replace('/');
  }


}
