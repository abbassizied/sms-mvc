package io.github.abbassizied.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.abbassizied.sms.entities.Product;
import io.github.abbassizied.sms.exceptions.ProductNotFoundException;
import io.github.abbassizied.sms.exceptions.SupplierNotFoundException;
import io.github.abbassizied.sms.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Invalid product Id:" + id));
    }

    @Override
    public Product saveProduct(Product product) {
    	try {
    	    return productRepository.save(product); // Save the product and return it
    	} catch (Exception e) {
    	    // Log the error to understand the issue
    	    System.err.println("Error saving product: " + e.getMessage());
    	    e.printStackTrace();
    	    // Rethrow the exception or handle it
    	    throw new RuntimeException("Failed to save the product due to an unexpected error", e);
    	}

    }

	@Override
	public void deleteProductById(long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new SupplierNotFoundException("Invalid supplier Id:" + id)); 
		productRepository.delete(product);
	}

}