package com.ss.poss.application.port.in.menucategory;

import com.ss.poss.domain.menucategory.model.MenuCategory;

import java.util.List;

public interface CreateMenuCategoryUseCase {
    MenuCategory createListMenuCategory(MenuCategory menuCategory);
    List<MenuCategory> createListMenuCategory(List<MenuCategory> menuCategoryList);
}
