package com.swidx.userservice.redis;

import lombok.Getter;
import org.springframework.data.annotation.Id; // 이걸 쓰시오
import org.springframework.data.redis.core.RedisHash;

// 이거 쓰면 ..requires to have an explicit id field. Did you forget to provide one using @Id? 오류
// import javax.persistence.Id;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 10L) // 10L for testing
public class Token {
    @Id
    private String refreshToken;

    public Token(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
