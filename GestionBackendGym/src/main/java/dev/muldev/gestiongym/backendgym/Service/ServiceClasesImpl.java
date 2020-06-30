/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.DAO.DAOClases;
import dev.muldev.gestiongym.backendgym.Modelos.ClasesGym;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClasesImpl implements ServiceClases{
    
    @Autowired
    private DAOClases dao;
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ClasesGym> listaClases() {
        return dao.findAll();
    }
    
    @Override
    public List<ClasesGym> listaClasesSemana(Date desde, Date hasta){
        try{
            Query q = em.createNamedQuery("ClasesGym.findByFechaConcreta").setParameter("desde", desde)
                   .setParameter("hasta", hasta);
            List<ClasesGym> clases = q.getResultList();
            Collections.sort(clases, new Comparator<ClasesGym>() {
                public int compare(ClasesGym o1, ClasesGym o2) {
                    return o1.getFechaClase().compareTo(o2.getFechaClase());
                }
                });
            
            return clases;
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public ClasesGym getOne(int id) {
        return dao.getOne(id);
    }

    @Override
    public void actualizaCliente(ClasesGym clase) {
        dao.save(clase);
    }
    
    
    
    
}
