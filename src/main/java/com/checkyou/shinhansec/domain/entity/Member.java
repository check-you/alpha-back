package com.checkyou.shinhansec.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="member")
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class) // 추가
public class Member implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "password", length = 255)
    @NotNull
    private String password;

    @Column(name = "phone_number", length = 25)
    @NotNull
    private String phoneNumber;

    @Column(name = "email", length = 70, unique = true)
    @NotNull
    private String email;

    @Column(name = "name", length = 30)
    @NotNull
    private String name;

    @Column(name = "role", length = 10)
    @NotNull
    private String role;

    @Column(name="email_auth", columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    @NotNull
    private Boolean emailAuth;

    @Column(name = "created_at")
    @NotNull
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @NotNull
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()-> "ROLE_USER");
        return collectors;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Builder
    public Member(String email, String phoneNumber, String password, Boolean emailAuth, String name){
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.emailAuth = emailAuth;
        this.name = name;
        this.role = "USER";
    }

    public void emailVerifiedSuccess() {
        this.emailAuth = true;
    }
}
