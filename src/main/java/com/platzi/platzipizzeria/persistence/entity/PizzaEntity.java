
package com.platzi.platzipizzeria.persistence.entity;

import com.platzi.platzipizzeria.persistence.audit.AuditPizzaListener;
import com.platzi.platzipizzeria.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


@Entity
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
// Son los distintos listeners que triggerean métodos que hacen cosas de auditoría
// Tenemos uno por default y el que creamos nosotros en este caso
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
public class PizzaEntity extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "id_pizza", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean available;

    // Esto es para auditoría

    /*@Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;*/

    @Override
    public String toString() {
        return "PizzaEntity{" +
                "idPizza=" + idPizza +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", available=" + available +
                '}';
    }
}
