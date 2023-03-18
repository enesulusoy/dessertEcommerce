package com.euce.dessert.controller;

import com.euce.dessert.dto.BrandDto;
import com.euce.dessert.model.Brand;
import com.euce.dessert.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brandList = brandService.getBrands();
        return ResponseEntity.ok(brandList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandsById(@PathVariable Long id) {
        Brand brand = brandService.getBrand(id);
        return ResponseEntity.ok(brand);
    }

    @PostMapping
    public ResponseEntity<String> createBrand(@Valid @RequestBody BrandDto brandDto) {
        Brand brand =  brandService.saveBrand(brandDto);
        return ResponseEntity.ok("Brand Created Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandDto brandDto) {
        Brand brand = brandService.updateBrand(id, brandDto);
        return ResponseEntity.ok("Brand Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok("Brand Deleted Successfully");
    }
}
