package com.platzi.platzipizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .crsf().disable()
                .cors().and()
                // .requestMatchers(HttpMethod.GET, "/api/*").permitAll()
                // Con esto decimos que NO NECESITAMOS AUTORIZACIÓN, OJO
                // Cuando tenemos un solo asterísco estamos indicando que queremos que se permita hasta solo un nivel después del slash
                // ---------------------------------------------------------------------------------------------------------------------
                // .requestMatchers(HttpMethod.GET, "/api/**").permitAll()  Acá estamos diciendo que queremos TODO el arbol de slashes
                .requestMatchers(HttpMethod.GET, "/api/pizzas/**").permitAll() // Acá estamos diciendo que permitimos todas las ramas hijas de pizza
                .requestMatchers(HttpMethod.PUT).denyAll() // Acá decimos que no permitimos requests con verbos PUT. Esto devuelve un 403.
                .anyRequest()
                .authenticated() // Con estas dos ultimas estamos diciendo que TODA petición debe estar autenticada
                .and()
                .httpBasic(); // y que tiene que estar autenticada con http basic
                // ( base 64 para user:pass donde la pass es la que genera para cada sesion spring security )
        return httpSecurity.build();
    }
}
