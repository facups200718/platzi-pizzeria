package com.platzi.platzipizzeria.web.controller;

import com.platzi.platzipizzeria.persistence.entity.CustomerEntity;
import com.platzi.platzipizzeria.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> findByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(this.customerService.findByPhone(phone));
    }
}
