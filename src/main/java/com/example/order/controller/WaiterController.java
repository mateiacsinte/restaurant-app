package com.example.order.controller;

import com.example.order.dtos.WaiterDTO;
import com.example.order.service.WaiterService;
import com.example.order.exceptions.InvalidBodyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WaiterController {

    WaiterService waiterService;

    public WaiterController(WaiterService waiterService){
        this.waiterService = waiterService;
    }
    @GetMapping("/waiters")
    public List<WaiterDTO> getOrders(){
        return waiterService.getWaiters();
    }
    @PostMapping("/waiters")
    public WaiterDTO addOrder(@RequestBody WaiterDTO waiter) throws InvalidBodyException {
        return waiterService.createWaiter(waiter);
    }
}
