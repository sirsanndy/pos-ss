package com.ss.poss.domain.menu.service;

import com.ss.poss.application.port.in.menu.CreateMenuUseCase;
import com.ss.poss.application.port.in.menu.GetListMenuUseCase;
import com.ss.poss.application.port.in.menu.GetMenuUseCase;
import com.ss.poss.application.port.in.menu.WebhookMenuUseCase;
import com.ss.poss.application.port.in.menucategory.GetMenuCategoryUseCase;
import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.domain.menu.model.MenuWebhook;
import com.ss.poss.domain.menucategory.exception.MenuCategoryNotFoundException;
import com.ss.poss.infrastructure.adapter.out.persistence.menu.MenuPersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuService implements CreateMenuUseCase, GetListMenuUseCase, GetMenuUseCase, WebhookMenuUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(MenuService.class);
    private final MenuPersistenceAdapter menuPersistenceAdapter;
    private final GetMenuCategoryUseCase getMenuCategoryUseCase;

    public MenuService(MenuPersistenceAdapter menuPersistenceAdapter, GetMenuCategoryUseCase getMenuCategoryUseCase) {
        this.menuPersistenceAdapter = menuPersistenceAdapter;
        this.getMenuCategoryUseCase = getMenuCategoryUseCase;
    }

    @Override
    public Menu createMenu(Menu menu) {
        LOG.info("Create or Update menu: {} started", menu.menuId());
        var menuCategoryId = menu.menuCategoryId();
        Optional.ofNullable(getMenuCategoryUseCase.getMenuCategoryById(menu.menuId()))
                .orElseThrow(() -> new MenuCategoryNotFoundException(String.format("Menu Category with ID %s does not exist.", menuCategoryId)));

        menu = menuPersistenceAdapter.saveMenu(menu);
        LOG.info("Create or Update menu: {} finished", menu.menuId());
        return menu;
    }

    @Override
    public List<Menu> createMenus(List<Menu> menus) {
        LOG.info("Create menus for {} started", menus.size());
        menus = menuPersistenceAdapter.createMenus(menus);
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
        LOG.info("GET MENU SERVICE BY ID: {}", menuId);
        return menuPersistenceAdapter.getMenuById(menuId).orElse(null);
    }

    @Override
    public void send(MenuWebhook menuWebhook) {
        LOG.info("SEND MENU WEBHOOK SERVICE: {}", menuWebhook.event());
    }
}
