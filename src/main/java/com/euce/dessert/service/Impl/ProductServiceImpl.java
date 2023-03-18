package com.euce.dessert.service.Impl;

import com.euce.dessert.dto.ProductDto;
import com.euce.dessert.exception.RecordAlreadyExistsException;
import com.euce.dessert.model.Brand;
import com.euce.dessert.model.Category;
import com.euce.dessert.model.Product;
import com.euce.dessert.repository.BrandRepository;
import com.euce.dessert.repository.CategoryRepository;
import com.euce.dessert.repository.ProductRepository;
import com.euce.dessert.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(ProductDto productDto) {
        List<Brand> brandList = brandRepository.findByName(productDto.getCategory());
        List<Category> categoryList = categoryRepository.findByName(productDto.getCategory());
        if (brandList.isEmpty() || categoryList.isEmpty()) {
            throw new ResourceNotFoundException("No Such Brand or Category Exists");
        }
        if (!productRepository.findByName(productDto.getName()).isEmpty()) {
            throw new RecordAlreadyExistsException("There is already a product with this name");
        }

        Product product = Product.builder()
                .uniqueProductIdentificationCode(productDto.getUniqueProductIdentificationCode())
                .name(productDto.getName())
                .model(productDto.getModel())
                .price(productDto.getPrice())
                .color(productDto.getColor())
                .size(productDto.getSize())
                .material(productDto.getMaterial())
                .stockQuantity(productDto.getStockQuantity())
                .brand(brandList.get(0))
                .category(categoryList.get(0))
                .build();

        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such Product Exists")
        );
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such Product Exists")
        );

        List<Brand> brandList = brandRepository.findByName(productDto.getBrand());
        List<Category> categoryList = categoryRepository.findByName(productDto.getCategory());

        product.setName(productDto.getName());
        product.setModel(productDto.getModel());
        product.setPrice(productDto.getPrice());
        product.setColor(productDto.getColor());
        product.setSize(productDto.getSize());
        product.setMaterial(productDto.getMaterial());
        product.setBrand(brandList.get(0));
        product.setCategory(categoryList.get(0));

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such Product Exists")
        );
        productRepository.delete(product);
    }
}
