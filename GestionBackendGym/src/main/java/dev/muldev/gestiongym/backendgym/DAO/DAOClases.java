/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.DAO;

import dev.muldev.gestiongym.backendgym.Modelos.ClasesGym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DAOClases extends JpaRepository<ClasesGym, Integer>{
    
}
