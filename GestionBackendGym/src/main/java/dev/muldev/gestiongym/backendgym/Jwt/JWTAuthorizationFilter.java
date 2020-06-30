
package dev.muldev.gestiongym.backendgym.Jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
    
    private static final String KEY = "HHKKLLOO997JJ";
    
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        
        if (!requiresAuth(header)){
            chain.doFilter(request, response);
            return;
        }
        
        //validamos el token
        boolean valido;
        Claims token = null;
        try{
            token = Jwts.parser()
                    .setSigningKey(KEY.getBytes())
                    .parseClaimsJws(header.replace("Bearer ", "")).getBody();
            
            valido = true;
        }
        catch(JwtException | IllegalArgumentException e){
            //e.printStackTrace();
            valido = false;
        }
        
        UsernamePasswordAuthenticationToken aut = null;
        
        
        if(valido){
            //damos acceso al recurso
            String username = token.getSubject();
            Object roles = token.get("roles");
            
            Collection<? extends GrantedAuthority> autho = Arrays.asList(new ObjectMapper()
                    .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedMixin.class)
                    .readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
            
            aut = new UsernamePasswordAuthenticationToken(username, null, autho);
            
        }
        
        //asignamos el objeto al contexto
        SecurityContextHolder.getContext().setAuthentication(aut);
        chain.doFilter(request, response);
        
    }
    
    protected boolean requiresAuth(String header){
        if (header == null || !header.startsWith("Bearer ")){
            return false;
        }
        return true;
    }
    
    
    
}
