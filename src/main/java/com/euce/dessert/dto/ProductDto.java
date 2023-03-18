package com.euce.dessert.dto;

import com.euce.dessert.model.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private String uniqueProductIdentificationCode;
    private String name;
    private String model;
    private Double price;
    private String color;
    private String size;
    private String material;
    private int stockQuantity;
    private String brand;
    private String category;
}
