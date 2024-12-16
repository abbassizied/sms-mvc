package io.github.abbassizied.sms.forms;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.github.abbassizied.sms.entities.Supplier;
import jakarta.validation.constraints.*;

public class ProductForm {

    private Long id;

    // Name: Required, between 2 and 50 characters
    @NotBlank(message = "{product.name.notBlank}")
    @Size(min = 2, max = 50, message = "{product.name.size}")
    private String name;

    private MultipartFile mainImage;

    // Description: Optional, but limited length
    @Size(min=10, max = 2000, message = "{product.description.size}")
    private String description;

    // Price: Required, must be positive, with max value
    @NotNull(message = "{product.price.notNull}")
    @DecimalMin(value = "0.01", inclusive = true, message = "{product.price.positive}")
    @DecimalMax(value = "100000.00", inclusive = true, message = "{product.price.max}")
    private Double price;

    // Initial Quantity: Required, must be positive
    @NotNull(message = "{product.initialQuantity.notNull}")
    @Min(value = 0, message = "{product.initialQuantity.nonNegative}")
    private Integer initialQuantity;

    // Supplier: Required
    @NotNull(message = "{product.supplier.notNull}")
    private Supplier supplier;

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

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ProductForm [id=" + id + ", name=" + name + ", mainImage=" + mainImage + ", description=" + description
                + ", price=" + price + ", initialQuantity=" + initialQuantity + ", supplier=" + supplier + ", images="
                + images + "]";
    }
}
