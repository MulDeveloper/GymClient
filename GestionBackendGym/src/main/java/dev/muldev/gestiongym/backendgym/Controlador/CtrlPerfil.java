/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Controlador;

import dev.muldev.gestiongym.backendgym.Modelos.AccesoClientes;
import dev.muldev.gestiongym.backendgym.Modelos.ClientesGym;
import dev.muldev.gestiongym.backendgym.Modelos.MatriculasGym;
import dev.muldev.gestiongym.backendgym.Service.ServiceAccesoCliente;
import dev.muldev.gestiongym.backendgym.Service.ServiceCliente;
import dev.muldev.gestiongym.backendgym.Service.ServiceEmail;
import dev.muldev.gestiongym.backendgym.Service.ServiceMatriculas;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin("http://localhost:4200") //no funciona
public class CtrlPerfil {
    
    @Autowired
    private ServiceCliente service;

    @Autowired
    private ServiceMatriculas serviceMatriculas;
    
    @Autowired
    private ServiceAccesoCliente serviceAcceso;
    
    @Autowired
    private BCryptPasswordEncoder bCrypt;
    
    @Autowired
    private ServiceEmail emailService;
    
    @GetMapping("/get")
    public ClientesGym get(@RequestParam(name="nomusu") String nomusu,
            @RequestParam(name="token") String token){
        
        //le pasamos el nomusu y el token, si es correcto devolvemos los datos
        
        String tknUsername = decodeJWT(token);
        
        if (nomusu.equals(tknUsername)){
            //es el usuario correcto
            return service.buscaPorUsername(nomusu);
        }
        else{
            return null;
        }
        
        
    }
    
    
    //decodificamos el token JWT para comprobar si es el usuario correcto
    public String decodeJWT(String tkn){
        try{
            String[] split_string = tkn.split("\\.");
            String base64EncodedHeader = split_string[0];
            String base64EncodedBody = split_string[1];
            String base64EncodedSignature = split_string[2];

            Base64 base64Url = new Base64(true);

            String body = new String(base64Url.decode(base64EncodedBody));
            String[] parts = body.split(":");

            String username = parts[1].replace("\"", "").replace("}", "");

            return username;
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    
    @GetMapping("/get/tarifa/{idMat}")
    public int getTarifa(@PathVariable int idMat){
        //devolvemos el id de tarifa que tiene ese usuario
        MatriculasGym mat = serviceMatriculas.get(idMat);
        return mat.getIdtarifa();
        
    }
    
    @PostMapping("/modifica")
    public ClientesGym modificar(@RequestBody ClientesGym c, @RequestParam(name="token") String tkn){
        
        String username = decodeJWT(tkn);
        
        if(c.getApellidoCliente().equals("")){
            return null;
        }
        
        if(c.getEmailCliente().equals("")){
            return null;
        }
        
        if(c.getNifCliente().equals("")){
            return null;
        }
        
        if(c.getDireccionCliente().equals("")){
            return null;
        }
        
        if(c.getFechaNacimiento() == null){
            return null;
        }
        
        if(c.getTelCliente() == null){
            return null;
        }
        

        if (c.getNifCliente().equals(username)){
            //tenemos acceso a modificar el usuario
            return service.alta(c);
        }
        else{
            return null;
        }
        
    }
    
    @PostMapping("/modificaAcceso")
    public int modificarAcceso(@RequestParam(name="nuevapass") String pass,
            @RequestParam(name="idcliente") String idcliente, @RequestParam(name="token") String token){
        
        //comprobamos que el token es el correcto
        
        String username = decodeJWT(token);
        
        //modificamos el acceso 
        
        int id = -1;
        
        try{
            id = Integer.parseInt(idcliente);
        }catch(NumberFormatException e){System.out.println("Error");}
        
        if (id != -1){
            AccesoClientes acceso = serviceAcceso.getByClienteId(id);
            
            if (username.equals(acceso.getUsername())){
                //es correcto, podemos modificar el acceso
                //encriptamos password
                acceso.setPassword(bCrypt.encode(pass));
                serviceAcceso.alta(acceso);

                //enviamos nuevo email con las nuevas claves
                ClientesGym c = service.getById(id);
                emailService.enviaEmailModificacion(c.getNifCliente(), pass, c.getEmailCliente());
                return 1;
            }
            else{
                return -1;
            }
           
            
        }
        
        
        return -1;
    }
    
}
