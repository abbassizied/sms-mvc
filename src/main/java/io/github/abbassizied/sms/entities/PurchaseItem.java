package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "purchase_items")
public class PurchaseItem extends BaseEntity { 
    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Getters and setters
}
