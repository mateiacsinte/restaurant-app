package com.example.order.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders") // 'order' is a reserved keyword in SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Integer tableNumber;
    private Float orderValue;
    private String orderDetails;
    private Boolean isDone;

    private Date dateCreated;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> orderProducts;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "waiter_id")
    private Waiter orderWaiter;

}
