package ru.lomov.socka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lomov.socka.entities.Notify;

import java.util.List;

@Repository
public interface NotifyRepository extends JpaRepository<Notify, Long> {

    @Query("SELECT n FROM Notify n WHERE :userId MEMBER OF n.recipients")
    List<Notify> findAllByRecipientsContaining(@Param("userId") Long userId);

    void deleteAllByRecipientsContaining(Long userId);
}