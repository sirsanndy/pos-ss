package com.ss.poss.domain.menucategory.service;

import com.ss.poss.application.port.in.menucategory.CreateMenuCategoryUseCase;
import com.ss.poss.application.port.in.menucategory.GetListMenuCategoryUseCase;
import com.ss.poss.application.port.in.menucategory.GetMenuCategoryUseCase;
import com.ss.poss.domain.menucategory.model.MenuCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuCategoryService implements CreateMenuCategoryUseCase, GetMenuCategoryUseCase, GetListMenuCategoryUseCase {
    private static Logger LOG = LoggerFactory.getLogger(MenuCategoryService.class);


    @Override
    public MenuCategory createListMenuCategory(MenuCategory menuCategory) {
        return null;
    }

    @Override
    public List<MenuCategory> createListMenuCategory(List<MenuCategory> menuCategoryList) {
        return List.of();
    }

    @Override
    public List<MenuCategory> getListMenuCategory() {
        return List.of();
    }

    @Override
    public MenuCategory getMenuCategory(UUID menuCategoryId) {
        return null;
    }
}
