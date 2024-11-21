package io.github.abbassizied.sms.services;

import java.util.List;

import io.github.abbassizied.sms.entities.Product;

public interface ProductService {

    List<Product> listProducts();

    Product getProductById(long id);

    Product saveProduct(Product product);

    void deleteProductById(long id);
}
