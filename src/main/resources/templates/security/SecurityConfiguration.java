//package com.KickOffEsporte.KickOffEsporte.infra.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
//import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception{
//        MvcRequestMatcher.Builder mvcMatherBuilder = new MvcRequestMatcher.Builder(introspector);
//
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session.
//                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(mvcMatherBuilder.pattern("/css/**"),
//                                mvcMatherBuilder.pattern("/img/**"),
//                                mvcMatherBuilder.pattern("/javascript/**"),
//                                mvcMatherBuilder.pattern("/auth/login")).permitAll()
//                        .requestMatchers(mvcMatherBuilder.pattern("/testandoOLogin")).permitAll()
//                        .requestMatchers(mvcMatherBuilder.pattern("/cadastrar/admin")).permitAll()
//                        .requestMatchers(mvcMatherBuilder.pattern("/cadastrar/estoquista")).permitAll()
//                        .requestMatchers(mvcMatherBuilder.pattern("/listaUsuarios")).permitAll()
//                        .requestMatchers(mvcMatherBuilder.pattern("/cadastro")).hasRole("ADMIN")
//                        .requestMatchers(mvcMatherBuilder.pattern("/telaPrincipal")).hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(formLoginConfigurer -> {
//                    formLoginConfigurer
//                            .loginPage("/login")
//                            .successForwardUrl("/listaUsuarios")
//                            .failureUrl("/login?error=true")
//                            .permitAll();
//                })
//                .build();
//}
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//            return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//}
