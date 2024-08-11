package ru.lomov.socka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lomov.socka.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
