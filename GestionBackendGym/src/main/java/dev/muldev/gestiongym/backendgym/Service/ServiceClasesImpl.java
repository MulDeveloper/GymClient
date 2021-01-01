/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.DAO.DAOClases;
import dev.muldev.gestiongym.backendgym.Modelos.GymClassEntity;
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
    public List<GymClassEntity> listaClases() {
        return dao.findAll();
    }
    
    @Override
    public List<GymClassEntity> listaClasesSemana(Date desde, Date hasta){
        try{
            Query q = em.createNamedQuery("GymClassEntity.findByFechaConcreta").setParameter("desde", desde)
                   .setParameter("hasta", hasta);
            List<GymClassEntity> clases = q.getResultList();
            Collections.sort(clases, new Comparator<GymClassEntity>() {
                public int compare(GymClassEntity o1, GymClassEntity o2) {
                    return o1.getDateClass().compareTo(o2.getDateClass());
                }
                });
            
            return clases;
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public GymClassEntity getOne(int id) {
        return dao.getOne(id);
    }

    @Override
    public void actualizaCliente(GymClassEntity clase) {
        dao.save(clase);
    }
    
    
    
    
}
