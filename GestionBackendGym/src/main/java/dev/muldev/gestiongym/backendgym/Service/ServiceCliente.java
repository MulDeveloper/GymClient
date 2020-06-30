/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.Modelos.ClientesGym;

/**
 *
 * @author bunn3
 */
public interface ServiceCliente {
    
    public ClientesGym alta(ClientesGym c);
    
    public ClientesGym buscaPorUsername(String username);
    
    public ClientesGym getById(int id);
    
}
