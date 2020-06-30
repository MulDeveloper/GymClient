/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.Modelos.FormularioContacto;


public interface ServiceEmail {
    public void enviaEmailDatosAcceso(String username, String password, String to);
    public void enviaEmailModificacion(String username, String password, String to);
    public void enviaEmailContacto(FormularioContacto form);
}
