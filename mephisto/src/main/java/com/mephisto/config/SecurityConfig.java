package com.mephisto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the application.
 *
 * @author Prashant Singh
 *         on February 24, 2025.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

        /**
         * Configures the security filter chain.
         *
         * @param http the HttpSecurity to modify
         * @return the SecurityFilterChain
         * @throws Exception if an error occurs
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/", "/api/register", "/api/users",
                                                                "/api/users/{username}",
                                                                "/api/users/{username}/addFriend")
                                                .permitAll()
                                                .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")
                                                .requestMatchers("/api.admin/**").hasRole("ADMIN")
                                                .anyRequest().denyAll()) // Block any other requests
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .permitAll())
                                .logout(logout -> logout
                                                .permitAll())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                                .maximumSessions(1)
                                                .maxSessionsPreventsLogin(true)
                                                .expiredUrl("/login?expired=true"));

                return http.build();
        }

        /**
         * Provides a password encoder bean.
         *
         * @return the PasswordEncoder
         */
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        /**
         * Defines an in-memory user details service.
         *
         * @return the UserDetailsService bean
         */
        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
                UserDetails user = User.withUsername("user")
                                .password(passwordEncoder.encode("password"))
                                .roles("USER")
                                .build();

                UserDetails admin = User.withUsername("admin")
                                .password(passwordEncoder.encode("admin"))
                                .roles("ADMIN")
                                .build();

                return new InMemoryUserDetailsManager(user, admin);
        }
}
