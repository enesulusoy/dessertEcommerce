package com.euce.dessert.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private String name;
    private String subCategory;
    private String description;
}
