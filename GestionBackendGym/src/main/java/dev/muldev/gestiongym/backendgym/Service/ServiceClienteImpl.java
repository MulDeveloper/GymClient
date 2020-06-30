/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.DAO.DAOCliente;
import dev.muldev.gestiongym.backendgym.Modelos.AccesoClientes;
import dev.muldev.gestiongym.backendgym.Modelos.ClientesGym;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClienteImpl implements ServiceCliente{
    
    @Autowired
    private DAOCliente dao;
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public ClientesGym alta(ClientesGym c) {
        return dao.save(c);
    }

    @Override
    public ClientesGym buscaPorUsername(String username) {
        try{
            Query q = em.createNamedQuery("ClientesGym.findByNifCliente")
                    .setParameter("nifCliente", username);
            ClientesGym a = (ClientesGym) q.getSingleResult();
            return a;
            
        }
        catch(Exception e){
            System.out.println("No encontrado");
            return null;
        }
    }

    @Override
    public ClientesGym getById(int id) {
        return dao.getOne(id);
    }

    
}
