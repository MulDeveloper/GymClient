/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.DAO.DAORoles;
import dev.muldev.gestiongym.backendgym.Modelos.GymRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRolesImpl implements ServiceRoles{
    
    @Autowired
    private DAORoles dao;

    @Override
    public void alta(GymRoles r) {
        dao.save(r);
    }
    
}
