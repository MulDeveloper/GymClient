
package dev.muldev.gestiongym.backendgym.Controlador;

import dev.muldev.gestiongym.backendgym.Modelos.PricesEntity;
import dev.muldev.gestiongym.backendgym.Service.ServiceTarifa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CtrlTarifas {
    
    @Autowired
    private ServiceTarifa service;
    
    @GetMapping("/tarifas")
    public List <PricesEntity> listar(){
        
        return service.listaTarifas();
    }
    
    
    
    
}
