package com.experimentality.Store.persistence.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer usId;

    @Column(name = "role_id")
    private Integer roId;

    @Column(name = "u_name")
    private String name;

    @Column(name = "u_regdate")
    private LocalDateTime regDate;

    @Column(name = "u_address")
    private String address;

    @Column(name = "u_phonenumbr")
    private Long phoneNumber;

    @Column(name = "u_email")
    private String email;

    @Column(name = "u_password")
    private String password;

/*    @ManyToOne
    @JoinColumn(name = "role_id", updatable = false, insertable = false)
    private RoleEntity role;

    @OneToMany(mappedBy = "user")
    private List<PurchaseEntity> purchases;*/
}
