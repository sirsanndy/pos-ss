package com.ss.poss.infrastructure.adapter.out.persistence.menucategory;

import com.ss.poss.application.port.out.menucategory.MenuCategoryOutputPort;
import com.ss.poss.domain.menucategory.mapper.MenuCategoryMapper;
import com.ss.poss.domain.menucategory.model.MenuCategory;
import com.ss.poss.infrastructure.adapter.config.Adapter;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuCategoryEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Adapter
public class MenuCategoryPersistenceAdapter implements MenuCategoryOutputPort {
    private static final Logger LOG = LoggerFactory.getLogger(MenuCategoryPersistenceAdapter.class);

    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuCategoryMapper menuCategoryMapper;

    public MenuCategoryPersistenceAdapter(MenuCategoryRepository menuCategoryRepository, MenuCategoryMapper menuCategoryMapper) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuCategoryMapper = menuCategoryMapper;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, RuntimeException.class})
    public MenuCategory saveMenuCategory(MenuCategory menuCategory) {
        LOG.info("Save menu category: {} started", menuCategory.getMenuCategoryId());
        MenuCategoryEntity menuCategoryEntity = menuCategoryMapper.toMenuCategoryEntity(menuCategory);
        menuCategoryRepository.save(menuCategoryEntity);
        menuCategory.setMenuCategoryId(menuCategoryEntity.getMenuCategoryId());
        LOG.info("Save menu category: {} finished", menuCategory.getMenuCategoryId());
        return menuCategory;
    }

    @Override
    public List<MenuCategory> saveMenuCategories(List<MenuCategory> menuCategories) {
        LOG.info("Save menu category list to DB: {} started", menuCategories.size());
        List<MenuCategoryEntity> menuCategoryEntityList = menuCategories.stream().map(menuCategoryMapper::toMenuCategoryEntity).toList();
        menuCategoryRepository.saveAll(menuCategoryEntityList);
        menuCategories = menuCategoryEntityList.stream().map(menuCategoryMapper::toMenuCategory).toList();
        LOG.info("Save menu category list to DB: {} finished", menuCategories.size());
        return menuCategories;
    }

    @Override
    public MenuCategory getMenuCategoryById(UUID menuCategoryId) {
        LOG.info("Get menu category: {} started", menuCategoryId);
        MenuCategory menuCategory = menuCategoryRepository.findById(menuCategoryId).map(menuCategoryMapper::toMenuCategory)
                .orElse(null);
        LOG.info("Get menu category: {} finished", menuCategoryId);
        return menuCategory;
    }

    @Override
    public List<MenuCategory> getMenuCategories() {
        LOG.info("Get all menu category: started");
        List<MenuCategory> menuCategories = menuCategoryRepository.findAllOrderByCreatedAtDesc()
                .stream()
                .map(menuCategoryMapper::toMenuCategory)
                .toList();
        LOG.info("Get all menu category: finished");
        return menuCategories;
    }
}
