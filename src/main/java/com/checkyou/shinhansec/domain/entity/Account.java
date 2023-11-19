package com.checkyou.shinhansec.domain.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class) // 추가
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    @Column(name = "bank", length = 20)
    @NotNull
    private String bank;

    @Column(name = "account", length = 20)
    @NotNull
    private String account;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "member_id")
    private Member member;

}
