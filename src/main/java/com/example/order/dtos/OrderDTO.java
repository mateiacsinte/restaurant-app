package com.example.order.dtos;

import com.example.order.entities.Order;
import com.example.order.entities.Product;
import com.example.order.entities.Waiter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {

    public OrderDTO(Order order){
        this.orderId = order.getOrderId();
        this.tableNumber = order.getTableNumber();
        this.orderValue = order.getOrderValue();
        this.orderDetails = order.getOrderDetails();
        this.isDone = order.getIsDone();
        this.dateCreated = order.getDateCreated();
        this.orderProducts = order.getOrderProducts().stream().map(
                Product::getProductId
        ).collect(Collectors.toList());
        this.orderWaiterId = order.getOrderWaiter().getWaiterId();
    }

    private Integer orderId;
    private Integer tableNumber;
    private Float orderValue;
    private String orderDetails;
    private Boolean isDone;

    private Date dateCreated;

    private List<Integer> orderProducts;

    private Integer orderWaiterId;
}
