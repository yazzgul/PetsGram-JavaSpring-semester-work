package ru.itis.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.RegistrationForm;
import ru.itis.exception.UserAlreadyExistsException;
import ru.itis.services.AuthService;

@AllArgsConstructor
@Controller
public class AuthorizationController {
    private final AuthService authService;

    @GetMapping("/sign-in")
    public String getSignInView() {
        return "sign_in_view";
    }

    @GetMapping("/sign-up")
    public String getSignUpView() {
        return "sign_up_view";
    }

    @PostMapping("/sign-up")
    public String doSignUp(@ModelAttribute RegistrationForm form) throws UserAlreadyExistsException {
        authService.register(form);
        return "redirect:/test";
    }

}
