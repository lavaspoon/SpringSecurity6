package org.example.springsecurity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "T_AUTHORITY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
}
