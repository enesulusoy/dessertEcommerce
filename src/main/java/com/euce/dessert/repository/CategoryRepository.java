package com.euce.dessert.repository;

import com.euce.dessert.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByName(String name);
    List<Category> findByName(String name);
}