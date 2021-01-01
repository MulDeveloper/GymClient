/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.Modelos.ClientEntity;

/**
 *
 * @author bunn3
 */
public interface ServiceCliente {
    
    public ClientEntity alta(ClientEntity c);
    
    public ClientEntity buscaPorUsername(String username);
    
    public ClientEntity getById(int id);
    
}
