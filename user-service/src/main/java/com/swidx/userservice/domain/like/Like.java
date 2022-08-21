package com.swidx.userservice.domain.like;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Table(name = "user_like")
@Entity
public class Like {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID likeId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long placeId;

    public Like(String email, Long placeId) {
        this.email = email;
        this.placeId = placeId;
    }
}
