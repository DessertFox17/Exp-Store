package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class NewProductPurchaseDto {
    @NotNull
    @Min(value = 1)
    private int prId;
    @NotNull
    @Min(value = 1)
    private int quantity;
}
