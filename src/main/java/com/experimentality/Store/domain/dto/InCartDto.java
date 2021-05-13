package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class InCartDto {
    private List<InCartProductsDto> products;
}
