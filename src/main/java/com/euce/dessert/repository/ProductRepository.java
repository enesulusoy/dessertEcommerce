package com.euce.dessert.repository;

import com.euce.dessert.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStockQuantity(int stockQuantity);
    List<Product> findByName(String name);
    List<Product> findByBrandId(Long brandId);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByBrandIdAndCategoryId(Long brandId, Long categoryId);
}
