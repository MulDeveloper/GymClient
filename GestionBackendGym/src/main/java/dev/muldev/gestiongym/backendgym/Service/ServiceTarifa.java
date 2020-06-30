/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.Modelos.TarifasGym;
import java.util.List;

/**
 *
 * @author bunn3
 */
public interface ServiceTarifa {
    public List <TarifasGym> listaTarifas();
    
    public TarifasGym obtenerPorId(int id);
}
