/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.Modelos.ClasesGym;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bunn3
 */
public interface ServiceClases {
    public List<ClasesGym> listaClases();
    public List<ClasesGym> listaClasesSemana(Date desde, Date hasta);
    public ClasesGym getOne(int id);
    public void actualizaCliente(ClasesGym clase);
}
