package ru.lomov.socka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.lomov.socka.entities.AppUser;
import ru.lomov.socka.entities.Post;
import ru.lomov.socka.repositories.CommentRepository;
import ru.lomov.socka.repositories.PostRepository;
import ru.lomov.socka.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {


    private final PostRepository postRepository;


    private final CommentRepository commentRepository;


    private final UserRepository userRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPosts(List<Long> userIds) {
        return postRepository.findPostsByUserIds(userIds);
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    public Post likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.getLikes().add(userId);
        return postRepository.save(post);
    }

    public Post unLikePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.getLikes().remove(userId);
        return postRepository.save(post);
    }

    public List<Post> getUserPosts(Long userId) {
        return postRepository.findAllByUser(userId);
    }

    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> getPostDiscover(List<Long> userIds, int num) {
        PageRequest pageRequest = PageRequest.of(0, num);
        return postRepository.findDiscoverPosts(userIds, pageRequest);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post reportPost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.getReports().add(userId);
        return postRepository.save(post);
    }

    public AppUser savePost(Long userId, Long postId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getSaved().add(postId);
        return userRepository.save(user);
    }

    public AppUser unSavePost(Long userId, Long postId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getSaved().remove(postId);
        return userRepository.save(user);
    }

    public List<Post> getSavePost(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return postRepository.findAllById(user.getSaved());
    }
}
