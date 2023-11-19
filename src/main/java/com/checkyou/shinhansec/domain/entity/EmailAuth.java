package com.checkyou.shinhansec.domain.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "email_auth")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailAuth {
    private static final Long MAX_EXPIRE_TIME = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 60)
    @NotNull
    private String email;

    @Column(name = "auth_token")
    @NotNull
    private String authToken;

    @Column(name = "expired", columnDefinition = "TINYINT(1)")
    @NotNull
    private Boolean expired;

    @Column(name = "expired_date")
    @NotNull
    private LocalDateTime expireDate;

    @Builder
    public EmailAuth(String email, String authToken, Boolean expired) {
        this.email = email;
        this.authToken = authToken;
        this.expired = expired;
        this.expireDate = LocalDateTime.now().plusMinutes(MAX_EXPIRE_TIME);
    }

    public void useToken() {
        this.expired = true;
    }
}
