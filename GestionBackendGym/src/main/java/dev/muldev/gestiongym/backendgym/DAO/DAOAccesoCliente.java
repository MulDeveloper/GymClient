
package dev.muldev.gestiongym.backendgym.DAO;

import dev.muldev.gestiongym.backendgym.Modelos.ClientLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DAOAccesoCliente extends JpaRepository<ClientLoginEntity, Integer>{
    
}
