package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class NewPurchaseDto {
    private int puId;
    @NotNull
    @Min(value = 1)
    private int stId;
    @NotNull
    @Min(value = 1)
    private int usId;
    @NotNull
    private String payMethod;
    @NotNull
    private String comment;
    @NotEmpty
    private List<NewProductPurchaseDto> products;

}
