package com.opgg.opggapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Pw", nullable = false, length = 100)
    private String pw;

    @Column(name = "UserName", nullable = false, length = 30, unique = true)
    private String username;

    @Column(name = "NickName", nullable = false, unique = true)
    private String nickname;

    @Column(name = "Email", nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "CreateDate")
    private LocalDateTime createDate;
}