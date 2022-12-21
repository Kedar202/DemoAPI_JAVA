package com.productdemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ProductData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productCode;
    private String productName;
    private Double productMRP;
    private Double productPrice;
    private Long productCategoryId;
    private String productTargetGRP;
    private String productDescription;
    private Character currentlyAvailable;

}
