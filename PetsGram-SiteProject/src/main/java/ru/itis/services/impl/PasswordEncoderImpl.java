package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderImpl implements PasswordEncoder {

    @Autowired
    private final BCryptPasswordEncoder encoder;

    @Override
    public String encode(CharSequence rawPassword) {
        return encoder.encode(rawPassword);
    }

//    @Override
//    public String encode(CharSequence rawPassword) {
//        return String.valueOf(rawPassword);
//    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return rawPassword.equals(encodedPassword);
        return encoder.matches(rawPassword, encodedPassword);
    }
}