
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.DAO.DAOMatricula;
import dev.muldev.gestiongym.backendgym.Modelos.MembershipEntity;
import dev.muldev.gestiongym.backendgym.Service.ServiceMatriculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceMatriculasImpl implements ServiceMatriculas{
   
    @Autowired
    private DAOMatricula dao;

    @Override
    public MembershipEntity altaMatricula(MembershipEntity m) {
        return dao.save(m);
    }

    @Override
    public MembershipEntity get(int id) {
        return dao.getOne(id);
    }
    
}
