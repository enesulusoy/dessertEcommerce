package com.euce.dessert.service;

import com.euce.dessert.dto.BrandDto;
import com.euce.dessert.model.Brand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BrandService {
    List<Brand> getBrands();

    Brand saveBrand(BrandDto brandDto);

    Brand getBrand(Long id);

    Brand updateBrand(Long id, BrandDto brandDto);

    void deleteBrand(Long id);
}
