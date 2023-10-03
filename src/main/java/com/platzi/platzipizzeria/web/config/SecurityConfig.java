package com.platzi.platzipizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                // ---------------------------------------------------------------------------------------------------------------------
                // .requestMatchers(HttpMethod.GET, "/api/pizzas/**").permitAll()  Acá estamos diciendo que permitimos todas las ramas hijas de pizza
                .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole("ADMIN") // Ver metodo memoryUsers
                // .requestMatchers(HttpMethod.PUT).denyAll()  Acá decimos que no permitimos requests con verbos PUT. Esto devuelve un 403.
                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .requestMatchers("/api/orders/**").hasRole("ADMIN") // Para poder consumir los endpoints de orders, tenes que ser admin B-)
                .anyRequest()
                .authenticated() // Con estas dos ultimas estamos diciendo que TODA petición debe estar autenticada
                .and()
                .httpBasic(); // y que tiene que estar autenticada con http basic
                // ( base 64 para user:pass donde la pass es la que genera para cada sesion spring security )
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService memoryUsers() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer123"))
                .roles("CUSTOMER")
                .build();
        return new InMemoryUserDetailsManager(admin, customer);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder;
    }
}
