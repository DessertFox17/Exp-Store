package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class ShowProductDto {
    private int prId;
    private String name;
    private String description;
    private double price;
    private double discountPrice;
    private int discountPrct;
    private List<ImageDto> images;
}
