package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "discounts")
public class Discount extends BaseEntity { 

    @Column(nullable = false)
    private Double discountPercentage;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    // Default constructor 
	public Discount() {
		super();
	}
    
    // Getters and setters 
	public Double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Discount [id=" + id + ", discountPercentage=" + discountPercentage + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}       
	
}
