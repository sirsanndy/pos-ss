package com.ss.poss.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "MENU")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid"
    )
    @Column(name = "MENU_ID")
    private UUID menuId;

    @Column(name = "MENU_NAME", nullable = false)
    private String name;

    @Column(name = "MENU_DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "PRICE")
    private BigDecimal price;
}
