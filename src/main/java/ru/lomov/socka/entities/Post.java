package ru.lomov.socka.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.List;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String content;

    @ElementCollection
    private List<String> images;

    @ElementCollection
    private List<Long> likes; // Список ID пользователей, которые лайкнули пост

    @ElementCollection
    private List<Long> comments; // Список ID комментариев

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ElementCollection
    private List<Long> reports; // Список ID пользователей, которые отрепортили пост

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

}