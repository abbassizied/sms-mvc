package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase extends BaseEntity { 
	
    @Column(nullable = false)
    private LocalDate purchaseDate;

    @Column(nullable = false)
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> purchaseItems;

    // Default constructor
	public Purchase() {
		super();
	}

    // Getters and setters
}
