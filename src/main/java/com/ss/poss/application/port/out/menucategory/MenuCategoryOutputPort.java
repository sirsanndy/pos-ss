package com.ss.poss.application.port.out.menucategory;

import com.ss.poss.domain.menucategory.model.MenuCategory;

import java.util.List;
import java.util.UUID;

public interface MenuCategoryOutputPort {
    MenuCategory saveMenuCategory(MenuCategory menuCategory);
    List<MenuCategory> saveMenuCategories(List<MenuCategory> menuCategories);
    MenuCategory getMenuCategoryById(UUID menuCategoryId);
    List<MenuCategory> getMenuCategories();
}
