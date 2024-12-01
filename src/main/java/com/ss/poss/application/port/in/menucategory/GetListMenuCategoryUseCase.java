package com.ss.poss.application.port.in.menucategory;

import com.ss.poss.domain.menucategory.model.MenuCategory;

import java.util.List;

public interface GetListMenuCategoryUseCase {
    List<MenuCategory> getListMenuCategory();
}
