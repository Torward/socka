package ru.lomov.socka.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lomov.socka.entities.Comment;
import ru.lomov.socka.entities.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user IN :userIds ORDER BY p.createdAt DESC")
    List<Post> findPostsByUserIds(@Param("userIds") List<Long> userIds);

    @Query(value = "SELECT p FROM Post p WHERE p.user NOT IN :userIds ORDER BY RAND()",
            countQuery = "SELECT COUNT(p) FROM Post p WHERE p.user NOT IN :userIds")
    List<Post> findDiscoverPosts(List<Long> userIds, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findAllByUser(@Param("userId")Long userId);

}

