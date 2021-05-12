package com.experimentality.Store.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subcategory")
public class SubcategoryEntity {

    //----------------TABLE COLUMNS-------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private Integer scId;

    @Column(name = "category_id")
    private Integer catId;

    @Column(name = "s_name")
    private String name;

    @Column(name = "s_descript")
    private String description;

    @Column(name = "s_status")
    private Boolean active;

    @Column(name = "s_image")
    private String image;

    //----------------RELATIONSHIPS--------------------------

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "subcategory")
    private List<ProductEntity> products;
}
