package dev.muldev.gestiongym.backendgym.Service;


import dev.muldev.gestiongym.backendgym.Modelos.AccesoClientes;
import dev.muldev.gestiongym.backendgym.Modelos.GymRoles;
import dev.muldev.gestiongym.backendgym.Service.ServiceAccesoCliente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userLoginService")
public class UserLoginService implements UserDetailsService{
    
    @Autowired
    private ServiceAccesoCliente service;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        AccesoClientes uv = service.buscaPorNomUsu(string);
        
        if(uv == null){
            throw new UsernameNotFoundException("Usuario no existe");
        }
        
        List <GrantedAuthority> roles = new ArrayList();
        
        for (GymRoles r: uv.getGymRolesList()){
            //registramos el rol
            roles.add(new SimpleGrantedAuthority(r.getAuthority()));
        }
        
        if(roles.isEmpty()){
            throw new UsernameNotFoundException("No tienes permisos");
        }
        
        return new User(uv.getUsername(), uv.getPassword(),true, true, true, true, roles);
        
    }
    
    
    
    
    
}
