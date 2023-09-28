package com.platzi.platzipizzeria.persistence.repository;

import com.platzi.platzipizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
}
