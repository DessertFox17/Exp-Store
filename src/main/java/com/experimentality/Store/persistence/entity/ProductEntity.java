package com.experimentality.Store.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {

    //----------------TABLE COLUMNS-------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer prId;

    @Column(name = "subcategory_id")
    private Integer scId;

    @Column(name = "pr_name")
    private String name;

    @Column(name = "pr_descript")
    private String description;

    @Column(name = "pr_price")
    private Double price;

    @Column(name = "pr_discountprice")
    private Double discountPrice;

    @Column(name = "pr_pcnt_discount")
    private Integer discountPrct;

    @Column(name = "pr_front_image")
    private String frontImage;

    @Column(name = "pr_back_image")
    private String backImage;

    @Column(name = "pr_searchcount")
    private Integer searchCounter;

    @Column(name = "pr_status")
    private Boolean active;

    //----------------RELATIONSHIPS--------------------------

    @ManyToOne
    @JoinColumn(name = "subcategory_id", insertable = false, updatable = false)
    private SubcategoryEntity subcategory;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "product")
    private List<ImageEntity> images;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "product")
    private List<ProductPurchaseEntity> purchases;
}
