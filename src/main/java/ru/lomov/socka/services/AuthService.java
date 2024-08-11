package ru.lomov.socka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lomov.socka.entities.AppUser;
import ru.lomov.socka.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class AuthService {


    private final UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder;

    public AppUser register(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public AppUser changePassword(Long userId, String oldPassword, String newPassword) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    public AppUser login(String email, String password) {
        AppUser user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return user;
    }


}
