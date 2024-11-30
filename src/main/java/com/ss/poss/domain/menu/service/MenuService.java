package com.ss.poss.domain.menu.service;

import com.ss.poss.application.port.in.menu.CreateMenuUseCase;
import com.ss.poss.application.port.in.menu.GetListMenuUseCase;
import com.ss.poss.application.port.in.menu.GetMenuUseCase;
import com.ss.poss.domain.menu.mapper.MenuMapper;
import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import com.ss.poss.infrastructure.adapter.out.persistence.menu.MenuPersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuService implements CreateMenuUseCase, GetListMenuUseCase, GetMenuUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(MenuService.class);
    private final MenuPersistenceAdapter menuPersistenceAdapter;
    private final MenuMapper menuMapper;

    public MenuService(MenuPersistenceAdapter menuPersistenceAdapter, MenuMapper menuMapper) {
        this.menuPersistenceAdapter = menuPersistenceAdapter;
        this.menuMapper = menuMapper;
    }

    @Override
    public Menu createMenu(Menu menu) {
        return null;
    }

    @Override
    public List<Menu> createMenus(List<Menu> menus) {
        LOG.info("Create menus for {} started", menus.size());
        menus = menuPersistenceAdapter.createMenus(menus);;
        LOG.info("Create menus for {} finished", menus.size());
        return menus;
    }

    @Override
    public List<Menu> getListMenu() {
        LOG.info("GET LIST OF MENU SERVICE");
        return menuPersistenceAdapter.getMenuList();
    }

    @Override
    public Menu getMenuById(UUID menuId) {
        return menuPersistenceAdapter.getMenuById(menuId).orElse(null);
    }
}
