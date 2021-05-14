package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class InCartDto {
    private int puId;
    private List<InCartProductsDto> products;
}
