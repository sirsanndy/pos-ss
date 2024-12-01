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

    @Column(name = "MENU_CATEGORY_DESCRIPTION")
    private String menuCategoryDescription;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MENU_ID")
    private List<MenuEntity> listMenu;
}
