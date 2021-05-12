package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class NewProductsDto {
    @Min(value = 1)
    @NotNull
    private int scId;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @DecimalMin(value = "0.1")
    @NotNull
    private double price;
    @Min(value = 1)
    @NotNull
    private int discountPrct;
    @NotNull
    private String frontImage;
    @NotNull
    private String backImage;
}
