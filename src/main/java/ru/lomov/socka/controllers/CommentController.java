package ru.lomov.socka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lomov.socka.entities.Comment;
import ru.lomov.socka.services.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {


    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody String content, @RequestParam Long userId) {
        return ResponseEntity.ok(commentService.updateComment(id, content, userId));
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Comment> likeComment(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(commentService.likeComment(id, userId));
    }

    @PostMapping("/unlike/{id}")
    public ResponseEntity<Comment> unLikeComment(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(commentService.unLikeComment(id, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @RequestParam Long userId) {
        commentService.deleteComment(id, userId);
        return ResponseEntity.ok().build();
    }
}