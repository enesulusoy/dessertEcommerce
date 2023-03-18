package com.euce.dessert.repository;

import com.euce.dessert.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Boolean existsByName(String name);
    List<Brand> findByName(String name);
}
