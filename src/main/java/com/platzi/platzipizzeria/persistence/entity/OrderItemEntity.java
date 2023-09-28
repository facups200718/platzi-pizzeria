package com.platzi.platzipizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {
    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, columnDefinition = "Decimal(2,1)")
    private Double quantity;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    // @JsonIgnore es innecesario si implementamos los DTOs.
    // Cuando obtenemos una OrderEntity, se intenta crear el objeto order de OrderItemEntity.
    // Esto es debido a las annotations @ManyToOne, @OneToMany, @JoinColumn...
    // No podemos obtener la información de la List OrderItemEntity llamada "items", la cual forma parte de OrderEntity,
    // si en OrderItemEntity se está intentando instanciar la OrderEntity ("order"). Es un loop.
    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", insertable = false, updatable = false)
    @JsonIgnore
    private OrderEntity order;

    // Con insertable = false y updatable = false estamos indicando que no queremos que se creen registros en la tabla pizza ni que se actualicen automaticamente.
    @OneToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", insertable = false, updatable = false)
    private PizzaEntity pizza;
}
