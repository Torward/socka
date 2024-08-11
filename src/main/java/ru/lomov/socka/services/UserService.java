package ru.lomov.socka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lomov.socka.entities.AppUser;
import ru.lomov.socka.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;

    public List<AppUser> searchUser(String username) {
        return userRepository.searchByUsername(username);
    }

    public Optional<AppUser> getUser(Long id) {
        return userRepository.findById(id);
    }

    public AppUser updateUser(AppUser user) {
        return userRepository.save(user);
    }

    public AppUser follow(Long userId, Long followerId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getFollowers().add(followerId);
        return userRepository.save(user);
    }

    public AppUser unfollow(Long userId, Long followerId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getFollowers().remove(followerId);
        return userRepository.save(user);
    }

    public List<AppUser> suggestionsUser(List<Long> ids, int num) {
        List<AppUser> users = userRepository.findUsersNotInList(ids);
        if (users.size() > num) {
            return users.subList(0, num);
        }
        return users;
    }
}