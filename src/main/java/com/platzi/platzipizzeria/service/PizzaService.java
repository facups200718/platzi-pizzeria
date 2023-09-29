package com.platzi.platzipizzeria.service;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import com.platzi.platzipizzeria.persistence.repository.PizzaPageSortingRepository;
import com.platzi.platzipizzeria.persistence.repository.PizzaRepository;
import com.platzi.platzipizzeria.service.dto.UpdatePizzaPriceDto;
import com.platzi.platzipizzeria.service.exception.EmailApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPageSortingRepository pizzaPageSortingRepository;

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPageSortingRepository.findAll(pageRequest);
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        System.out.println(this.pizzaRepository.countByVeganTrue());
        Pageable pageRequest = PageRequest.of(page, elements, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return this.pizzaPageSortingRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("La pizza no existe"));
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(Double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
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

    // Propagation.MANDATORY lo que hace es que si no hay transacción, que se arroje excepción (por lo que entendi)
    @Transactional(noRollbackFor = EmailApiException.class, propagation = Propagation.MANDATORY)
    public void updatePrice(UpdatePizzaPriceDto dto) {
        this.pizzaRepository.updatePrice(dto.getPizzaId(), dto.getNewPrice());
        this.sendEmail();
    }

    private void sendEmail() {
        throw new EmailApiException();
    }

    public void delete(Integer idPizza) { this.pizzaRepository.deleteById(idPizza); }
}
