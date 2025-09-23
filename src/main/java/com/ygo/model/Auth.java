package com.ygo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "auth")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String codePassword;
    private LocalDateTime registrationDate;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(columnDefinition="LONGTEXT")
    private String jwt;

}
