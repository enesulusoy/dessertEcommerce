package com.euce.dessert.service;

import com.euce.dessert.dto.ProductDto;
import com.euce.dessert.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveProduct(ProductDto productDto);

    Product updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);
}
