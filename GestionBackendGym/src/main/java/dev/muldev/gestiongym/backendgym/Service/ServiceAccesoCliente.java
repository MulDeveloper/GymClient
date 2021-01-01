
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.Modelos.ClientLoginEntity;


public interface ServiceAccesoCliente {
    public ClientLoginEntity buscaPorNomUsu(String nomusu);
    public void alta(ClientLoginEntity a);
}
