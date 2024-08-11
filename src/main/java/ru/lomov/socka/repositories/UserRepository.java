package ru.lomov.socka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lomov.socka.entities.AppUser;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findByEmail(String email);

    @Query("SELECT u FROM AppUser u WHERE u.username LIKE %:username%")
    List<AppUser> searchByUsername(@Param("username") String username);

    @Query("SELECT u FROM AppUser u WHERE u.id NOT IN :ids")
    List<AppUser> findUsersNotInList(@Param("ids") List<Long> ids);
}