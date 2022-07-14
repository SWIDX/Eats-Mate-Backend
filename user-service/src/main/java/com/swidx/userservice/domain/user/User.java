package com.swidx.userservice.domain.user;

import com.swidx.userservice.dto.KakaoUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String profileImageUrl;

    public User(KakaoUserDto userinfo) {
        this.id = userinfo.getId();
        this.name = userinfo.getName();
        this.email = userinfo.getEmail();
        this.profileImageUrl = userinfo.getProfileImageUrl();
    }

    public void update(KakaoUserDto userinfo) {
        this.name = userinfo.getName();
        this.profileImageUrl = userinfo.getProfileImageUrl();
    }

}
