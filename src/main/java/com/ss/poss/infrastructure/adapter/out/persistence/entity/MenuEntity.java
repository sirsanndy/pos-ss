package com.ss.poss.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "MENU")
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

    public UUID getMenuId() {
        return menuId;
    }

    public void setMenuId(UUID menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public MenuCategoryEntity getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategoryEntity menuCategory) {
        this.menuCategory = menuCategory;
    }

    public List<OrderDetailEntity> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetailEntity> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }
}
