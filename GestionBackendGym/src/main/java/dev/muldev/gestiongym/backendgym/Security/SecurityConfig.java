/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Security;

import dev.muldev.gestiongym.backendgym.Jwt.JWTAuthFilter;
import dev.muldev.gestiongym.backendgym.Service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserLoginService userLogin;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userLogin).passwordEncoder(encoder);
        
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().and()
                .authorizeRequests().antMatchers("/api/clases/**",
                        "/api/cliente/**","/api/tarifas/**",
                        "/login","/api/contacto/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilter(new JWTAuthFilter(authenticationManager()))
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        

    }
    
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");  // TODO: lock down before deploying
        config.addAllowedHeader("*");
        config.addExposedHeader(HttpHeaders.AUTHORIZATION);
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    

    
    
    
    
}
