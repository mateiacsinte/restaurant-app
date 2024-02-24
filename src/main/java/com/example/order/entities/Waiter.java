package com.example.order.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="waiter")
public class Waiter {

    @GeneratedValue
    @Id
    public Integer waiterId;

    public String waiterName;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();
}
