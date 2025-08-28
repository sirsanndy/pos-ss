package com.ss.poss.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "MENU_CATEGORY")
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

    public UUID getMenuCategoryId() {
        return menuCategoryId;
    }

    public void setMenuCategoryId(UUID menuCategoryId) {
        this.menuCategoryId = menuCategoryId;
    }

    public String getMenuCategoryName() {
        return menuCategoryName;
    }

    public void setMenuCategoryName(String menuCategoryName) {
        this.menuCategoryName = menuCategoryName;
    }

    public String getMenuCategoryDesc() {
        return menuCategoryDesc;
    }

    public void setMenuCategoryDesc(String menuCategoryDesc) {
        this.menuCategoryDesc = menuCategoryDesc;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<MenuEntity> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<MenuEntity> listMenu) {
        this.listMenu = listMenu;
    }
}
