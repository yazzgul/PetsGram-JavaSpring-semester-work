package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.model.Post;
import ru.itis.model.Role;
import ru.itis.model.User;
import ru.itis.repository.UserRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.deleteById(user.getId());
            log.info("Deleted user with email{}", user.getEmail());
        }
    }

    public void changeUserRole(Long id) {
        User user = getUserById(id);
        if (user.getRole().equals(Role.ROLE_USER)) {
            user.setRole(Role.ROLE_ADMIN);
        }
        else {
            user.setRole(Role.ROLE_USER);
        }
        userRepository.save(user);
    }

}
