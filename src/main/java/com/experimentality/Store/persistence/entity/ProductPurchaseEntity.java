package com.experimentality.Store.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_purchase")
public class ProductPurchaseEntity {

    //----------------TABLE COLUMNS---------------------------

    @EmbeddedId
    private ProductPurchasePKEntity pId = new ProductPurchasePKEntity();

    @Column(name = "pp_quantity")
    private Integer quantity;

    @Column(name = "pp_totprcntdiscont")
    private Integer totPrcntDisc;

    @Column(name = "pp_totprodscost")
    private Double totProdsCost;

    @Column(name = "pp_purchasecost")
    private Double purchaseCost;

    //----------------RELATIONSHIPS---------------------------

    @ManyToOne
    @MapsId("prId")
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity product;

    @ManyToOne
    @MapsId("puId")
    @JoinColumn(name = "purchase_id", insertable = false, updatable = false)
    private PurchaseEntity purchase;
}
