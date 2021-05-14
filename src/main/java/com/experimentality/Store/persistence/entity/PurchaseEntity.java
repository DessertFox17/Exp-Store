package com.experimentality.Store.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class PurchaseEntity {

    //----------------TABLE COLUMNS-------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Purchase_id")
    private Integer puId;

    @Column(name = "status_id")
    private Integer stId;

    @Column(name = "user_id")
    private Integer usId;

    @Column(name = "p_date")
    private LocalDateTime date;

    @Column(name = "p_paymeth")
    private String payMethod;

    @Column(name = "p_comment")
    private String comment;

    //----------------RELATIONSHIPS--------------------------

    @ManyToOne
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private StatusEntity status;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    private UserEntity user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "purchase", cascade = {CascadeType.ALL})
    private List<ProductPurchaseEntity> products;

}
