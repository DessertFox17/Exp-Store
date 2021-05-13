package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InCartProductsDto {
    private int quantity;
    private ShowProductinPurDto product;
}
