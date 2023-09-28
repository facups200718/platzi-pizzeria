package com.platzi.platzipizzeria.service;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import com.platzi.platzipizzeria.persistence.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@RequiredArgsConstructor
@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    public List<PizzaEntity> getAll() {
        return this.pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAvailable() {
        return this.pizzaRepository.findAllByAvailableOrderByPrice();
    }

    public PizzaEntity getByName(String name) { return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name); }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public PizzaEntity get(Integer idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public Boolean exists(Integer idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

    public void delete(Integer idPizza) { this.pizzaRepository.deleteById(idPizza); }
}
