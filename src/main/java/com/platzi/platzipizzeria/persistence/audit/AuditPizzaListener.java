package com.platzi.platzipizzeria.persistence.audit;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;


public class AuditPizzaListener {
    private PizzaEntity currentValue;

    // Se triggerea después de una persistencia o un update
    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue.toString());
        System.out.println("NEW VALUE: " + entity.toString());
    }

    // Se triggerea antes de eliminar en la bd
    @PreRemove
    public void onPreDelete(PizzaEntity entity) {
        System.out.println(entity.toString());
    }

    // Se ejecuta después de que haga el select para cargar la info en el java Entity
    @PostLoad
    public void postLoad(PizzaEntity entity) {
        System.out.println("POST LOAD");
        // Esto que está comentado NO está bien porque no se está clonando
        // sino que se está pasando la referencia al objeto original, trayendo problemas más tarde
        // this.currentValue = entity;
        this.currentValue = SerializationUtils.clone(entity);
    }
}
