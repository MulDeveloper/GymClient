/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.Modelos.FormularioContacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ServiceEmailImpl implements ServiceEmail{
    
    @Autowired
    public JavaMailSender emailSender;
    
    private static final String EMAIL_CORP = "contact@muldev.dev";

    @Async
    @Override
    public void enviaEmailDatosAcceso(String username, String password, String to) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Datos acceso GYM STRENGHT");

        msg.setText("Felicidades ahora formas parte de nuestro gimnasio \n"+
        "Tus datos de acceso al area de cliente son: \n"+
        "Usuario:" + username + " \n"+
        "Password:" + password + " \n"+
        "Cualquier duda ponte en contacto con nosotros");
        emailSender.send(msg);

        System.out.println("Enviando..");
    }

    @Async
    @Override
    public void enviaEmailModificacion(String username, String password, String to) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Modificacion contraseña GYM STRENGHT");

        msg.setText("Se han modificado las credenciales de acceso \n"+
        "Tus nuevos datos de acceso al area de cliente son: \n"+
        "Usuario:" + username + " \n"+
        "Password:" + password + " \n"+
        "Cualquier duda ponte en contacto con nosotros");
        emailSender.send(msg);

        System.out.println("Enviando..");
    }

    @Async
    @Override
    public void enviaEmailContacto(FormularioContacto form) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(EMAIL_CORP);
        msg.setSubject("Contacto desde la web:"+form.getAsunto());

        msg.setText(form.getNombre() + " ha enviado un formulario de contacto desde la web"+"\n"+
        "Telefono:" + form.getNumeroTelefono() + " \n"+
        "Mensaje:" + form.getMensaje());
        emailSender.send(msg);

        
    }
    
}
