
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.DAO.DAOMatricula;
import dev.muldev.gestiongym.backendgym.Modelos.MatriculasGym;
import dev.muldev.gestiongym.backendgym.Service.ServiceMatriculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceMatriculasImpl implements ServiceMatriculas{
   
    @Autowired
    private DAOMatricula dao;

    @Override
    public MatriculasGym altaMatricula(MatriculasGym m) {
        return dao.save(m);
    }

    @Override
    public MatriculasGym get(int id) {
        return dao.getOne(id);
    }
    
}
