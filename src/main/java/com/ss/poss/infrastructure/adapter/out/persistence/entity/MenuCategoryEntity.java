package com.ss.poss.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "MENU_CATEGORY")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid"
    )
    @Column(name = "MENU_CATEGORY_ID")
    private UUID menuCategoryId;

    @Column(name = "MENU_CATEGORY_NAME")
    private String menuCategoryName;

    @Column(name = "MENU_CATEGORY_DESC")
    private String menuCategoryDesc;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.REMOVE)
    private List<MenuEntity> listMenu;
}
