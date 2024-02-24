package com.example.order.service;

import com.example.order.dtos.WaiterDTO;
import com.example.order.entities.Waiter;
import com.example.order.repository.WaiterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaiterService {

    private final WaiterRepository waiterRepository;

    public WaiterService(WaiterRepository waiterRepository){
        this.waiterRepository = waiterRepository;
    }

    public WaiterDTO createWaiter(WaiterDTO waiterDTO) {
        Waiter waiter = new Waiter();
        waiter.setWaiterName(waiterDTO.getWaiterName());
        Waiter persistedWaiter = waiterRepository.save(waiter);
        return new WaiterDTO(persistedWaiter.waiterId,persistedWaiter.getWaiterName());
    }

    public List<WaiterDTO> getWaiters(){
        List<Waiter> waiters = waiterRepository.findAll();
        return waiters.stream()
                .map(w -> new WaiterDTO(w.waiterId,w.getWaiterName()))
                .collect(Collectors.toList());
    }
}
