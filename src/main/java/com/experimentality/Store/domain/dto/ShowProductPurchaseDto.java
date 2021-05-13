package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowProductPurchaseDto {
    private int quantity;
    private double purchaseCost;
    private ShowProductinPurDto product;
}
