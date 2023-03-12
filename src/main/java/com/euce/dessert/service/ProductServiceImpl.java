package com.euce.dessert.service;

import com.euce.dessert.model.Product;
import com.euce.dessert.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) throws ResourceNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public Product updateProduct(Long id, Product productDto) throws ResourceNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());

        product.setName(productDto.getName());
        product.setModel(productDto.getModel());
        product.setPrice(productDto.getPrice());
        product.setColor(productDto.getColor());
        product.setSize(productDto.getSize());
        product.setMaterial(productDto.getMaterial());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        productRepository.delete(product);
    }
}
