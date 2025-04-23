package com.ead.authuser.configs.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

//    private final UserDetailsService userDetailsService;
//
//    private final AuthenticationEntryPoint authenticationEntryPoint;

    private static final String[] AUTH_WHITELIST = {
//            "/auth/**"
            "/ead-authuser/auth/*"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(AUTH_WHITELIST).permitAll()
//                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(AUTH_WHITELIST).permitAll()
//                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(authenticationEntryPoint));

        return http.build();
    }

//    @Autowired
//    public void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("123456"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
