package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image extends BaseEntity { 

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Additional relationships for other entities can be added as needed
    
    // Constructors
	public Image() {
		super();
	}
 
	public Image(String url) {
		super();
		this.url = url;
	}
	
    // Getters and setters  
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", url=" + url + ", product_id=" + product.getId() + "]";
	}
	
}

