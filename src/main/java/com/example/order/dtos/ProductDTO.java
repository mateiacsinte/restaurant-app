package com.example.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Integer productId;
    private String productName;
    private String productObservations;
    private Float productPrice;
    private Integer productQuantity;
    private Integer productCategory;
}
