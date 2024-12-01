package com.ss.poss.domain.menucategory.service;

import com.ss.poss.application.port.in.menucategory.CreateMenuCategoryUseCase;
import com.ss.poss.application.port.in.menucategory.GetListMenuCategoryUseCase;
import com.ss.poss.application.port.in.menucategory.GetMenuCategoryUseCase;
import com.ss.poss.domain.menucategory.model.MenuCategory;
import com.ss.poss.infrastructure.adapter.out.persistence.menucategory.MenuCategoryPersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuCategoryService implements CreateMenuCategoryUseCase, GetMenuCategoryUseCase, GetListMenuCategoryUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(MenuCategoryService.class);

    private final MenuCategoryPersistenceAdapter menuCategoryPersistenceAdapter;

    public MenuCategoryService(MenuCategoryPersistenceAdapter menuCategoryPersistenceAdapter) {
        this.menuCategoryPersistenceAdapter = menuCategoryPersistenceAdapter;
    }

    @Override
    public MenuCategory createMenuCategory(MenuCategory menuCategory) {
        LOG.info("Create menu category started");
        menuCategory = menuCategoryPersistenceAdapter.saveMenuCategory(menuCategory);
        LOG.info("Create menu category finished");
        return menuCategory;
    }

    @Override
    public List<MenuCategory> createListMenuCategory(List<MenuCategory> menuCategoryList) {
        LOG.info("Create list menu category started");
        menuCategoryList = menuCategoryPersistenceAdapter.saveMenuCategories(menuCategoryList);
        LOG.info("Create list menu category finished");
        return menuCategoryList;
    }

    @Override
    public List<MenuCategory> getListMenuCategory() {
        LOG.info("Get list menu category started");
        List<MenuCategory> menuCategoryList = menuCategoryPersistenceAdapter.getMenuCategories();
        LOG.info("Get list menu category finished");
        return menuCategoryList;
    }

    @Override
    public MenuCategory getMenuCategory(UUID menuCategoryId) {
        LOG.info("Get menu category by id {} started", menuCategoryId);
        MenuCategory menuCategory = menuCategoryPersistenceAdapter.getMenuCategoryById(menuCategoryId);
        LOG.info("get menu category by id {} finished", menuCategoryId);
        return menuCategory;
    }
}
