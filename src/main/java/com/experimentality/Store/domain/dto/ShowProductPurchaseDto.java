package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowProductPurchaseDto {
    //private int prId;
    private int quantity;
    private double purchaseCost;
    private ShowProductDto product;
}
