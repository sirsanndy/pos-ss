package com.ss.poss.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
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

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "MENU_CATEGORY_ID")
    private MenuCategoryEntity menuCategory;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private List<OrderDetailEntity> listOrderDetail;
}
