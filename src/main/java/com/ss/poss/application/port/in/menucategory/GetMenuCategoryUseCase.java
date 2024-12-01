package com.ss.poss.application.port.in.menucategory;

import com.ss.poss.domain.menucategory.model.MenuCategory;

import java.util.UUID;

public interface GetMenuCategoryUseCase {
    MenuCategory getMenuCategoryById(UUID menuCategoryId);
}
