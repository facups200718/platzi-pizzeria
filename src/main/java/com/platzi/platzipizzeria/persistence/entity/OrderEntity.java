package com.platzi.platzipizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double total;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;

    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;

    // @JsonIgnore también puede servir para no traer info que no es de nuestro interés.
    // Un ejemplo que viene al caso, para qué queremos toda la info del customer si ya tenemos el id?
    @OneToOne(fetch = FetchType.LAZY) // Con esto indicamos que no se recupere la info en este caso, lo cual sería aún más eficiente que el @JsonIgnore.
    @JsonIgnore
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER) // Con EAGER indicamos que SÍ queremos que se recupere la info. y que se muestre.
    @OrderBy("price DESC")
    private List<OrderItemEntity> items;
}
