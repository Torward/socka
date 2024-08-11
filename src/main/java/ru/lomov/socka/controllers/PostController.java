package ru.lomov.socka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lomov.socka.entities.AppUser;
import ru.lomov.socka.entities.Post;
import ru.lomov.socka.services.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {


    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(@RequestParam List<Long> userIds) {
        return ResponseEntity.ok(postService.getPosts(userIds));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        return ResponseEntity.ok(postService.updatePost(post));
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Post> likePost(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(postService.likePost(id, userId));
    }

    @PostMapping("/unlike/{id}")
    public ResponseEntity<Post> unLikePost(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(postService.unLikePost(id, userId));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getUserPosts(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Optional<Post> post = postService.getPost(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(400).body(null));
    }

    @GetMapping("/discover")
    public ResponseEntity<List<Post>> getPostDiscover(@RequestParam List<Long> userIds, @RequestParam(defaultValue = "8") int num) {
        return ResponseEntity.ok(postService.getPostDiscover(userIds, num));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/report/{id}")
    public ResponseEntity<Post> reportPost(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(postService.reportPost(id, userId));
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<AppUser> savePost(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(postService.savePost(userId, id));
    }

    @PostMapping("/unsave/{id}")
    public ResponseEntity<AppUser> unSavePost(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(postService.unSavePost(userId, id));
    }

    @GetMapping("/saved")
    public ResponseEntity<List<Post>> getSavePost(@RequestParam Long userId) {
        return ResponseEntity.ok(postService.getSavePost(userId));
    }
}