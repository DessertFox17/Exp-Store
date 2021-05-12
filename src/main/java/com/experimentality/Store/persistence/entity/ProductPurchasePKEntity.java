package com.experimentality.Store.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductPurchasePKEntity implements Serializable {

    //---------------- FOREING KEYS ------------------------------

    @Column(name = "product_id")
    private Integer prId;

    @Column(name = "purchase_id")
    private Integer puId;
}
