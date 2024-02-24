package com.example.order.repository;

import com.example.order.entities.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaiterRepository extends JpaRepository<Waiter, Integer> {
}

