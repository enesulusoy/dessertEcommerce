package com.euce.dessert.service;

import com.euce.dessert.model.Product;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ProductService {

    public List<Product> getProducts();

    public Product saveProduct(Product product);

    public Product getProduct(Long id) throws ResourceNotFoundException;

    public Product updateProduct(Long id, Product product) throws ResourceNotFoundException;

    public void deleteProduct(Long id) throws ResourceNotFoundException;
}
