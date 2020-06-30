/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Controlador;

import dev.muldev.gestiongym.backendgym.Modelos.AccesoClientes;
import dev.muldev.gestiongym.backendgym.Modelos.ClientesGym;
import dev.muldev.gestiongym.backendgym.Modelos.GymRoles;
import dev.muldev.gestiongym.backendgym.Modelos.MatriculasGym;
import dev.muldev.gestiongym.backendgym.Modelos.TarifasGym;
import dev.muldev.gestiongym.backendgym.Security.GeneraPass;
import dev.muldev.gestiongym.backendgym.Service.ServiceAccesoCliente;
import dev.muldev.gestiongym.backendgym.Service.ServiceCliente;
import dev.muldev.gestiongym.backendgym.Service.ServiceEmail;
import dev.muldev.gestiongym.backendgym.Service.ServiceMatriculas;
import dev.muldev.gestiongym.backendgym.Service.ServiceRoles;
import dev.muldev.gestiongym.backendgym.Service.ServiceTarifa;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @Autowired
    private ServiceRoles serviceRoles;
    
    @Autowired
    private ServiceEmail serviceEmail;
    
 
    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientesGym alta(@RequestBody ClientesGym c){
        
        //recibimos el id de tarifa en lugar del id de matricula, creamos una matricula para
        //el cliente con esa tarifa asignada
        //el cliente tendra el status por defecto inactivo 
        
        MatriculasGym matricula = new MatriculasGym();
        
        matricula.setIdtarifa(c.getIdMatricula());
        matricula.setEstado(false);
        
        
        //fecha de alta en este momento
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();
        Date date = new Date(dtf.format(now));  
        matricula.setFechaAlta(date);

        //guardamos el cliente con valor de matricula 0
        c.setIdMatricula(0);
        ClientesGym cliente = service.alta(c);
        
        matricula.setIdcliente(cliente.getIdcliente());
        
        //al retornar la matricula obtenemos su id y se lo asignamos al cliente
        MatriculasGym temporal = serviceMatriculas.altaMatricula(matricula);
        
        cliente.setIdMatricula(temporal.getIdmatricula());
        
        service.alta(cliente);
        
        //generamos el acceso al cliente y credenciales mandamos email
        
        AccesoClientes ac = new AccesoClientes();
        
        ac.setIdcliente(cliente.getIdcliente());
        ac.setUsername(cliente.getNifCliente());
        
        //creamos password personalizada
        
        //dos letras aleatorias y fechanac (DDMMYY)
        SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
        String fecha = format.format(c.getFechaNacimiento());
        String pass = new GeneraPass().creaPass(fecha);
        System.out.println(pass);
        
        
        //encriptamos password
        ac.setPassword(bCrypt.encode(pass));
        //se inserta con roles nulos, se busca usuario y se crea rol correspondiente
        serviceAcceso.alta(ac);
        actualizaRolUsuario(cliente.getNifCliente(), "USER");
        
        //enviamos email con los datos al usuario
        
        serviceEmail.enviaEmailDatosAcceso(cliente.getNifCliente(), pass, cliente.getEmailCliente());
        

        return cliente;
    }
    
    private void actualizaRolUsuario(String username, String rolSeleccionado){
        AccesoClientes u = serviceAcceso.buscaPorNomUsu(username);
        if (rolSeleccionado.equals("USER")){
            GymRoles rol = new GymRoles("USER",u);
            serviceRoles.alta(rol);
        }
        else{
            System.out.println("Error en el rol");
        }
        
    }
    
}
