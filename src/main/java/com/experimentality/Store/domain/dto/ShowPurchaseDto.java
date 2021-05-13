package com.experimentality.Store.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ShowPurchaseDto {
    private int puId;
    private LocalDateTime date;
    private String payMethod;
    private String comment;
    private StatusDto status;
    private List<ShowProductPurchaseDto> products;
}
