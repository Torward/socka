package ru.lomov.socka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lomov.socka.entities.Comment;
import ru.lomov.socka.entities.Post;
import ru.lomov.socka.repositories.CommentRepository;
import ru.lomov.socka.repositories.PostRepository;
@RequiredArgsConstructor
@Service
public class CommentService {


    private final CommentRepository commentRepository;


    private final PostRepository postRepository;

    public Comment createComment(Comment comment) {
        Post post = postRepository.findById(comment.getPost().getId()).orElseThrow(() -> new RuntimeException("Post not found"));
        if (comment.getReply() != null) {
            commentRepository.findById(comment.getReply()).orElseThrow(() -> new RuntimeException("Comment not found"));
        }
        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment.getId());
        postRepository.save(post);
        return savedComment;
    }

    public Comment updateComment(Long id, String content, Long userId) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        if (!comment.getUser().equals(userId)) {
            throw new RuntimeException("You are not authorized to update this comment");
        }
        comment.setContent(content);
        return commentRepository.save(comment);
    }

    public Comment likeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        if (comment.getLikes().contains(userId)) {
            throw new RuntimeException("You have already liked this comment");
        }
        comment.getLikes().add(userId);
        return commentRepository.save(comment);
    }

    public Comment unLikeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.getLikes().remove(userId);
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        if (!comment.getUser().equals(userId) && !comment.getPostUserId().equals(userId)) {
            throw new RuntimeException("You are not authorized to delete this comment");
        }
        Post post = postRepository.findById(comment.getPost().getId()).orElseThrow(() -> new RuntimeException("Post not found"));
        post.getComments().remove(commentId);
        postRepository.save(post);
        commentRepository.delete(comment);
    }
}