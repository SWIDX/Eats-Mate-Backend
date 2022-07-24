package com.swidx.userservice.redis;

import lombok.Getter;
import org.springframework.data.annotation.Id; // 이걸 쓰시오
import org.springframework.data.redis.core.RedisHash;

// 이거 쓰면 ..requires to have an explicit id field. Did you forget to provide one using @Id? 오류
// import javax.persistence.Id;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 180L) // 180L == 180s for testing
public class Token {
    @Id
    private String refreshToken;

    private String id;

    public Token(String refreshToken, String id) {
        this.refreshToken = refreshToken;
        this.id = id;
    }
}
