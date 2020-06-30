
package dev.muldev.gestiongym.backendgym.Service;

import dev.muldev.gestiongym.backendgym.Modelos.AccesoClientes;


public interface ServiceAccesoCliente {
    public AccesoClientes buscaPorNomUsu(String nomusu);
    public void alta(AccesoClientes a);
    public AccesoClientes getByClienteId(int id);
}
