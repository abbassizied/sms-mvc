package io.github.abbassizied.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.abbassizied.sms.entities.Product;
import io.github.abbassizied.sms.exceptions.ProductNotFoundException;
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
        return productRepository.save(product);
    }

	@Override
	public void deleteProductById(long id) {
		// ---------------
	}

}