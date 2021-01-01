/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.muldev.gestiongym.backendgym.Service.ServiceAccesoCliente;
import dev.muldev.gestiongym.backendgym.Service.ServiceCliente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter{
    
    private AuthenticationManager authManager;
    
    private static final String KEY = "HHKKLLOO997JJ";
    
    @Autowired
    private ServiceCliente serviceCliente;
    
    @Autowired
    private ServiceAccesoCliente serviceAcceso;
    
    public JWTAuthFilter(AuthenticationManager authManager) {
        this.authManager = authManager;
    }
    
    

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String pass = obtainPassword(request);
         
        
        if (username == null){
            username = "";
        }
        
        if (pass == null){
            pass = "";
        }
        
        username = username.trim();
        
        if(username != null && pass != null){
            logger.info("Username y pass:" + username + " : " + pass);
        }
        
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,pass);
        
        
        
        return authManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        
        //creamos token JWT
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .signWith(SignatureAlgorithm.HS512, KEY.getBytes())
                .compact();


        //usamos el prefijo Bearer para pasar al cliente
        response.addHeader("Authorization", "Bearer " + token);
        
        Map <String,Object> respuesta = new HashMap<String, Object>();
        respuesta.put("token", token);
        respuesta.put("nomusu", authResult.getName());
        respuesta.put("mensaje", "Login correcto");
        respuesta.put("estado", 1);

        
        //convertimos respuesta a JSON
        response.getWriter().write(new ObjectMapper().writeValueAsString(respuesta));
        response.setStatus(200);
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        Map <String,Object> respuesta = new HashMap<String, Object>();
        respuesta.put("mensaje", "Error en las credenciales");
        respuesta.put("error", failed.getMessage());
        respuesta.put("estado", 0);
        
        //convertimos respuesta a JSON
        response.getWriter().write(new ObjectMapper().writeValueAsString(respuesta));
        response.setStatus(401);
        response.setContentType("application/json");
    }
    
    
    
}
