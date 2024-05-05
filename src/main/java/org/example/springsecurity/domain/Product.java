package org.example.springsecurity.domain;

import jakarta.persistence.*;
import org.example.springsecurity.enumeration.Currency;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
