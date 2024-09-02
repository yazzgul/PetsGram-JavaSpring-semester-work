package ru.itis.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.RegistrationForm;
import ru.itis.exception.UserAlreadyExistsException;
import ru.itis.model.Role;
import ru.itis.model.User;
import ru.itis.repository.UserRepository;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.AuthService;

@Slf4j
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegistrationForm form) throws UserAlreadyExistsException {

        if (userRepository.existsUserByEmail(form.getEmail())) {
            throw new UserAlreadyExistsException("User with the email: " + form.getEmail() + " already exists");
        }

        Role role = form.getEmail().equals("admin@admin.com") ? Role.ROLE_ADMIN : Role.ROLE_USER;

        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .passwordHash(passwordEncoder.encode(form.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Not exists user with the email: " + email));

        log.info("found user with email {}", user.getEmail());
        return new UserDetailsImpl(user);

    }

}
