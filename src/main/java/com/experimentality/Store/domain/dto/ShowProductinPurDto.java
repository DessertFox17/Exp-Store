package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowProductinPurDto {
    private int prId;
    private String name;
    private double discountPrice;
    private String frontImage;
    private String backImage;
}
