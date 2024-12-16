package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mainImage;

    @Column(columnDefinition = "TEXT")    
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)    
    private Integer initialQuantity;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier; 

    /**** One To Many ****/
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;

    // Constructors    
	public Product() {
		super();
	}

    // Getters and setters 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getInitialQuantity() {
		return initialQuantity;
	}

	public void setInitialQuantity(Integer initialQuantity) {
		this.initialQuantity = initialQuantity;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	} 

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", mainImage=" + mainImage + ", description=" + description + ", price="
				+ price + ", initialQuantity=" + initialQuantity + ", quantity=" + quantity + ", supplier=" + supplier
				+ ", images=" + images + ", id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	} 

}
