package ru.itis.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itis.dto.RegistrationForm;
import ru.itis.exception.EmailNotFoundException;
import ru.itis.exception.UserAlreadyExistsException;

public interface AuthService extends UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void register(RegistrationForm form) throws UserAlreadyExistsException;

}
