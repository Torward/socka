package ru.lomov.socka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lomov.socka.entities.AppUser;
import ru.lomov.socka.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<AppUser>> searchUser(@RequestParam String username) {
        return ResponseEntity.ok(userService.searchUser(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUser(@PathVariable Long id) {
        Optional<AppUser> user = userService.getUser(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody AppUser user) {
        userService.updateUser(user);
        return ResponseEntity.ok("Profile updated successfully.");
    }

    @PostMapping("/follow/{id}")
    public ResponseEntity<AppUser> follow(@PathVariable Long id, @RequestParam Long followerId) {
        return ResponseEntity.ok(userService.follow(id, followerId));
    }

    @PostMapping("/unfollow/{id}")
    public ResponseEntity<AppUser> unfollow(@PathVariable Long id, @RequestParam Long followerId) {
        return ResponseEntity.ok(userService.unfollow(id, followerId));
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<AppUser>> suggestionsUser(@RequestParam List<Long> ids, @RequestParam(defaultValue = "10") int num) {
        return ResponseEntity.ok(userService.suggestionsUser(ids, num));
    }
}