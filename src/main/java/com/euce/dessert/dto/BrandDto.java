package com.euce.dessert.dto;

import com.euce.dessert.model.constant.NotificationType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
public class BrandDto {
    private String name;
    private String email;
    private String phone;
    private String description;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
}
