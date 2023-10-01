package com.platzi.platzipizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /* ESTO ES POR SI QUEREMOS QUE TODOS LOS REQUESTS ESTEN AUTORIZADOS
        httpSecurity.authorizeHttpRequests()
                .anyRequest()
                .permitAll();*/

        httpSecurity.authorizeHttpRequests()
                .anyRequest()
                .authenticated() // Con estas dos ultimas estamos diciendo que TODA petición debe estar autenticada
                .and()
                .httpBasic(); // y que tiene que estar autenticada con http basic
                // ( base 64 para user:pass donde la pass es la que genera para cada sesion spring security )
        return httpSecurity.build();
    }
}
