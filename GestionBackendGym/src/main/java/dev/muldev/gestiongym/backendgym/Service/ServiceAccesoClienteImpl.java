
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.DAO.DAOAccesoCliente;
import dev.muldev.gestiongym.backendgym.Modelos.AccesoClientes;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccesoClienteImpl implements ServiceAccesoCliente{
    
    @Autowired
    private DAOAccesoCliente dao;
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public AccesoClientes buscaPorNomUsu(String nomusu) {
        try{
            Query q = em.createNamedQuery("AccesoClientes.findByUsername")
                    .setParameter("username", nomusu);
            AccesoClientes u = (AccesoClientes) q.getSingleResult();
            return u;
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public void alta(AccesoClientes a) {
        dao.save(a);
    }

    @Override
    public AccesoClientes getByClienteId(int id) {
        //obtenemos el acceso de ese cliente
        try{
            Query q = em.createNamedQuery("AccesoClientes.findByIdcliente")
                    .setParameter("idcliente", id);
            AccesoClientes u = (AccesoClientes) q.getSingleResult();
            return u;
        }
        catch(NoResultException e){
            return null;
        }
    }
    
}
