package com.KickOofEsports.KickOffEsports.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception{
        MvcRequestMatcher.Builder mvcMatherBuilder = new MvcRequestMatcher.Builder(introspector);


        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers(mvcMatherBuilder.pattern("/css/**"),
                                mvcMatherBuilder.pattern("/img/**"),
                                mvcMatherBuilder.pattern("/javascript/**"),
                                mvcMatherBuilder.pattern("/login"),
                                mvcMatherBuilder.pattern("/auth/login")).permitAll()
                        .requestMatchers(mvcMatherBuilder.pattern("/cadastro"),
                                mvcMatherBuilder.pattern("/cadastrar"),
                                mvcMatherBuilder.pattern("/editar/**"),
                                mvcMatherBuilder.pattern("/editarUsuario/**")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLoginConfigurer -> {
                    formLoginConfigurer
                            .loginPage("/login")
                            .loginProcessingUrl("/auth/logar")
                            .successForwardUrl("/listaDeUsuario");
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
