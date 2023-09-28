package com.platzi.platzipizzeria.persistence.repository;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;
import java.util.*;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableOrderByPrice();
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    //Esta query method dice "che hay alguna descripción que contenga este str, sin importar el casing?"
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    //Esta query method dice lo contrario a lo de arriba xd
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    int countByVeganTrue();
}
