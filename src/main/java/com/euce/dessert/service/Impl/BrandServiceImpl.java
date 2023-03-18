package com.euce.dessert.service.Impl;

import com.euce.dessert.dto.BrandDto;
import com.euce.dessert.exception.RecordAlreadyExistsException;
import com.euce.dessert.model.Brand;
import com.euce.dessert.repository.BrandRepository;
import com.euce.dessert.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand saveBrand(BrandDto brandDto) {
        if (brandRepository.existsByName(brandDto.getName())) {
            throw new RecordAlreadyExistsException("There is already a brand with this name");
        }

        Brand brand = Brand.builder()
                .name(brandDto.getName())
                .email(brandDto.getEmail())
                .phone(brandDto.getPhone())
                .description(brandDto.getDescription())
                .notificationType(brandDto.getNotificationType())
                .build();

        return brandRepository.save(brand);
    }

    @Override
    public Brand getBrand(Long id) {
        return brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such Brand Exists")
        );
    }

    @Override
    public Brand updateBrand(Long id, BrandDto brandDto) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such Brand Exists")
        );

        if (brandRepository.existsByName(brandDto.getName())) {
            throw new RecordAlreadyExistsException("There is already a brand with this name");
        }

        brand.setName(brandDto.getName());
        brand.setEmail(brandDto.getEmail());
        brand.setPhone(brandDto.getPhone());
        brand.setDescription(brandDto.getDescription());
        brand.setNotificationType(brandDto.getNotificationType());

        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such Brand Exists")
        );
        brandRepository.delete(brand);
    }
}
