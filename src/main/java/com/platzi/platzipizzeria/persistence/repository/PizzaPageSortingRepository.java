package com.platzi.platzipizzeria.persistence.repository;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPageSortingRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {
}
