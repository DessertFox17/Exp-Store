package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DynamicFilterDto {
    private String name;
    private double price;
    private double discountPrice;
    private int discountPrct;
    private String frontImage;
    private String backImage;
    private Integer searchCounter;
}
