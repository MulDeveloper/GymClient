/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Controlador;

import dev.muldev.gestiongym.backendgym.Modelos.FormularioContacto;
import dev.muldev.gestiongym.backendgym.Service.ServiceEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/contacto")
public class CtrlContacto {
    
    @Autowired
    private ServiceEmail service;
    
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean enviaEmail(@RequestBody FormularioContacto form){
        //logica de envio de email interno
        if (form.getMensaje() != "" && form.getMensaje() != null){
            //enviamos el email
            service.enviaEmailContacto(form);
            return true;
        }
        return false;
    }
}
