package ru.itis.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.itis.services.AuthService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    static final String[] PERMIT = {"/res/**", "/sign-up", "/images/**"};

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PERMIT).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .usernameParameter("email")
                                .loginPage("/sign-in")
                                .defaultSuccessUrl("/posts")
                                .failureUrl("/sign-in")
//                                .failureUrl("/sign-in?error=true")
                                .permitAll()
                )
                .logout(form ->
                        form
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/sign-in")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")


                )
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
