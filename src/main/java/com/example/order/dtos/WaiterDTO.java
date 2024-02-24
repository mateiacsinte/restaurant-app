package com.example.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WaiterDTO {

    private Integer waiterId;
    private String waiterName;
}
