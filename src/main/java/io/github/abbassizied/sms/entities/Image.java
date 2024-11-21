package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Boolean isMain;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Additional relationships for other entities can be added as needed

    // Getters and setters
}

