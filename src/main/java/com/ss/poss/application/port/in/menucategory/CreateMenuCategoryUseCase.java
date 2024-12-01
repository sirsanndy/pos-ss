package com.ss.poss.application.port.in.menucategory;

import com.ss.poss.domain.menucategory.model.MenuCategory;

import java.util.List;

public interface CreateMenuCategoryUseCase {
    MenuCategory createMenuCategory(MenuCategory menuCategory);
    List<MenuCategory> createMenuCategory(List<MenuCategory> menuCategoryList);
}
