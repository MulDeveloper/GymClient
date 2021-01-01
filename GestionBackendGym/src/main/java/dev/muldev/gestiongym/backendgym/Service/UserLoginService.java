package dev.muldev.gestiongym.backendgym.Service;



import java.util.ArrayList;
import java.util.List;

import dev.muldev.gestiongym.backendgym.Modelos.ClientLoginEntity;
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
        ClientLoginEntity uv = service.buscaPorNomUsu(string);
        
        if(uv == null){
            throw new UsernameNotFoundException("Usuario no existe");
        }
        
        List <GrantedAuthority> roles = new ArrayList();
        

        return new User(uv.getUsername(), uv.getPassword(),true, true, true, true, roles);
        
    }
    
    
    
    
    
}
