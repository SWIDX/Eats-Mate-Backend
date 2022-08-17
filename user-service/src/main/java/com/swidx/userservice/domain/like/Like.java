package com.swidx.userservice.domain.like;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Like {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID like_id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long place_id;

    // WIP..
}
