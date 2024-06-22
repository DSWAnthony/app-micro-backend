package com.app.exa.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FavoriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.ALL})
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity bookEntity;

    private LocalDateTime date;
}
