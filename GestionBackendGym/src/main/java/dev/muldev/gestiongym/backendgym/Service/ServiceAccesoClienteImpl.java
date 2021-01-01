
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.DAO.DAOAccesoCliente;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dev.muldev.gestiongym.backendgym.Modelos.ClientLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccesoClienteImpl implements ServiceAccesoCliente{
    
    @Autowired
    private DAOAccesoCliente dao;
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public ClientLoginEntity buscaPorNomUsu(String nomusu) {
        try{
            Query q = em.createNamedQuery("ClientLogin.findByUsername")
                    .setParameter("username", nomusu);
            ClientLoginEntity u = (ClientLoginEntity) q.getSingleResult();
            return u;
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public void alta(ClientLoginEntity a) {
        dao.save(a);
    }

    
}
