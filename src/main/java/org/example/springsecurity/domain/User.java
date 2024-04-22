package org.example.springsecurity.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MEMBER")
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

    @Builder
    public User(String userid, String password, String usernm) {
        this.userid = userid;
        this.password = password;
        this.usernm = usernm;
    }
}
