package ru.lomov.socka.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lomov.socka.entities.Post;
import ru.lomov.socka.services.AdminService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {


    private final AdminService adminService;

    @GetMapping("/totalUsers")
    public ResponseEntity<Long> getTotalUsers() {
        return ResponseEntity.ok(adminService.getTotalUsers());
    }

    @GetMapping("/totalPosts")
    public ResponseEntity<Long> getTotalPosts() {
        return ResponseEntity.ok(adminService.getTotalPosts());
    }

    @GetMapping("/totalComments")
    public ResponseEntity<Long> getTotalComments() {
        return ResponseEntity.ok(adminService.getTotalComments());
    }

    @GetMapping("/totalLikes")
    public ResponseEntity<Long> getTotalLikes() {
        return ResponseEntity.ok(adminService.getTotalLikes());
    }

    @GetMapping("/totalSpamPosts")
    public ResponseEntity<Long> getTotalSpamPosts() {
        return ResponseEntity.ok(adminService.getTotalSpamPosts());
    }

    @GetMapping("/spamPosts")
    public ResponseEntity<List<Post>> getSpamPosts() {
        return ResponseEntity.ok(adminService.getSpamPosts());
    }

    @DeleteMapping("/deleteSpamPost/{id}")
    public ResponseEntity<String> deleteSpamPost(@PathVariable Long id) {
        adminService.deleteSpamPost(id);
        return ResponseEntity.ok("Post deleted successfully.");
    }
}