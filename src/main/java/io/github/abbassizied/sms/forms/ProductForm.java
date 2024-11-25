package io.github.abbassizied.sms.forms;

import jakarta.validation.constraints.*;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.github.abbassizied.sms.entities.Supplier;
import io.github.abbassizied.sms.forms.validations.ValidMultipartFile;

public class ProductForm {
    private Long id;

    // Name: Required, between 2 and 50 characters
    @NotBlank(message = "{product.name.notBlank}", groups = { OnCreate.class, OnUpdate.class })
    @Size(min = 2, max = 50, message = "{product.name.size}", groups = { OnCreate.class, OnUpdate.class })
    private String name;
    
    // Logo URL: Required, must be a valid File Type
    @ValidMultipartFile(groups = OnCreate.class)
    private MultipartFile mainImage;
    
    // Description: Required, must not exceed 255 characters
    @Size(max = 255, message = "{product.description.size}", groups = { OnCreate.class, OnUpdate.class })
    private String description;

    // Price: Required, must be positive
    @NotNull(message = "{product.price.notNull}", groups = { OnCreate.class, OnUpdate.class })
    @DecimalMin(value = "0.0", inclusive = false, message = "{product.price.positive}", groups = { OnCreate.class, OnUpdate.class })
    private Double price;

    // Quantity: Optional, but if provided must be non-negative
    @Min(value = 0, message = "{product.quantity.nonNegative}", groups = { OnCreate.class, OnUpdate.class })
    private Integer quantity;

    // Reorder Level: Optional, but if provided must be non-negative
    @Min(value = 0, message = "{product.reorderLevel.nonNegative}")
    private Integer reorderLevel;

    // Supplier: Required
    @NotNull(message = "{product.supplier.notNull}")    
    private Supplier supplier;

    // Image URLs: Required
    @ValidMultipartFile(groups = OnCreate.class)
    private List<MultipartFile> images;

    // Constructors

    /**
     * Default constructor for form binding.
     */
    public ProductForm() {}
  
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public MultipartFile getMainImage() {
		return mainImage;
	}

	public void setMainImage(MultipartFile mainImage) {
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

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    // Getter for 'supplier'
    public Supplier getSupplier() {
        return supplier;
    }

    // Setter for 'supplier'
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}