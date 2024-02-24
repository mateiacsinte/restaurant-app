package com.example.order.controller;

import com.example.order.dtos.OrderDTO;
import com.example.order.exceptions.EntityNotFoundException;
import com.example.order.service.OrderService;
import com.example.order.exceptions.InvalidBodyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDTO> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("/orders")
    public OrderDTO addOrder(@RequestBody OrderDTO order) throws EntityNotFoundException {
        return orderService.createOrder(order);
    }

    @DeleteMapping("/orders/{orderId}")
    public OrderDTO deleteUser(@PathVariable Integer orderId) throws EntityNotFoundException {
        return orderService.deleteOrder(orderId);
    }
}
