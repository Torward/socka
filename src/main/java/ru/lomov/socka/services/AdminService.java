package ru.lomov.socka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lomov.socka.entities.Post;
import ru.lomov.socka.repositories.CommentRepository;
import ru.lomov.socka.repositories.PostRepository;
import ru.lomov.socka.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class AdminService {


    private final UserRepository userRepository;


    private final PostRepository postRepository;


    private final CommentRepository commentRepository;

    public long getTotalUsers() {
        return userRepository.count();
    }

    public long getTotalPosts() {
        return postRepository.count();
    }

    public long getTotalComments() {
        return commentRepository.count();
    }

    public long getTotalLikes() {
        return postRepository.findAll().stream().mapToLong(post -> post.getLikes().size()).sum();
    }

    public long getTotalSpamPosts() {
        return postRepository.findAll().stream().filter(post -> post.getReports().size() > 2).count();
    }

    public List<Post> getSpamPosts() {
        return postRepository.findAll().stream().filter(post -> post.getReports().size() > 1).collect(Collectors.toList());
    }

    public void deleteSpamPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.getComments().forEach(commentRepository::deleteById);
        postRepository.delete(post);
    }
}