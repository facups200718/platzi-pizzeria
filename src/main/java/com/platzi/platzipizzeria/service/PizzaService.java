package com.platzi.platzipizzeria.service;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PizzaService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PizzaEntity> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM pizz WHEN available = 1",
                new BeanPropertyRowMapper<>(PizzaEntity.class));
    }
}
