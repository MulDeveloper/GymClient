/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Controlador;

import dev.muldev.gestiongym.backendgym.Modelos.*;
import dev.muldev.gestiongym.backendgym.Security.GeneraPass;
import dev.muldev.gestiongym.backendgym.Service.ServiceAccesoCliente;
import dev.muldev.gestiongym.backendgym.Service.ServiceCliente;
import dev.muldev.gestiongym.backendgym.Service.ServiceMatriculas;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CtrlCliente {
    
    @Autowired
    private ServiceCliente service;
    
    @Autowired
    private ServiceMatriculas serviceMatriculas;
    
    @Autowired
    private BCryptPasswordEncoder bCrypt;
    
    @Autowired
    private ServiceAccesoCliente serviceAcceso;

    
 
    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientEntity alta(@RequestBody ClientEntity c){
        
        //recibimos el id de tarifa en lugar del id de matricula, creamos una matricula para
        //el cliente con esa tarifa asignada
        //el cliente tendra el status por defecto inactivo 
        
        MembershipEntity matricula = new MembershipEntity();
        
        matricula.setIdtarifa(c.getIdMatricula());
        

        LocalDateTime d = java.time.LocalDateTime.now();
        Date fecha = convertToDateViaSqlTimestamp(d);
        matricula.setFechaAlta(fecha);
        
        System.out.println(matricula.getFechaAlta());

        //guardamos el cliente con valor de matricula 0
        c.setIdMatricula(0);
        ClientEntity cliente = service.alta(c);
        
        matricula.setIdcliente(cliente.getIdcliente());
        
        //al retornar la matricula obtenemos su id y se lo asignamos al cliente
        MembershipEntity temporal = serviceMatriculas.altaMatricula(matricula);
        
        cliente.setIdMatricula(temporal.getIdmatricula());
        
        service.alta(cliente);
        
        //generamos el acceso al cliente y credenciales mandamos email
        
        ClientLoginEntity ac = new ClientLoginEntity();
        
        ac.setId(UUID.randomUUID());
        ac.setUsername(cliente.getNifCliente());
        
        //creamos password personalizada
        
        //dos letras aleatorias y fechanac (DDMMYY)
        SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
        String fechaNac = format.format(c.getFechaNacimiento());
        String pass = new GeneraPass().creaPass(fechaNac);

        //encriptamos password
        ac.setPassword(bCrypt.encode(pass));
        serviceAcceso.alta(ac);
        return cliente;
    }
    
    public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    
}
