package org.example.springsecurity.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.springsecurity.enumeration.EncryptionAlgolithm;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    @Id
    @Column(name = "userid")
    private String userid;

    @Column(name = "password")
    private String password;

    @Column(name = "usernm")
    private String usernm;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgolithm algolithm;

    @OneToMany(mappedBy = "user")
    private List<Authority> authorities = new ArrayList<>();

    @Builder
    public User(String userid, String password, String usernm) {
        this.userid = userid;
        this.password = password;
        this.usernm = usernm;
    }
}
