package com.platzi.platzipizzeria.service;

import com.platzi.platzipizzeria.persistence.entity.OrderEntity;
import com.platzi.platzipizzeria.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderEntity> getAll() {
        return this.orderRepository.findAll();
    }
}
