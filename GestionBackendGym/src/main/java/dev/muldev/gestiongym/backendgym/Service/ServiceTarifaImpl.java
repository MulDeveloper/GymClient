/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.DAO.DAOTarifa;
import dev.muldev.gestiongym.backendgym.Modelos.PricesEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTarifaImpl implements ServiceTarifa{
    
    @Autowired
    private DAOTarifa dao;

    @Override
    public List<PricesEntity> listaTarifas() {
        return dao.findAll();
    }

    @Override
    public PricesEntity obtenerPorId(int id) {
        return dao.getOne(id);
    }
    
}
