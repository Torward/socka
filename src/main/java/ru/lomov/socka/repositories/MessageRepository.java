package ru.lomov.socka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lomov.socka.entities.Message;

import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE (m.sender = :userId AND m.recipient = :recipientId) OR (m.sender = :recipientId AND m.recipient = :userId)")
    List<Message> findAllBySenderAndRecipient(@Param("userId") Long userId, @Param("recipientId") Long recipientId);
}