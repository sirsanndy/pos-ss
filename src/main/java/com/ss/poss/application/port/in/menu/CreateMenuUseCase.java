package com.ss.poss.application.port.in.menu;

import com.ss.poss.domain.menu.model.Menu;

import java.util.List;

public interface CreateMenuUseCase {
    Menu createMenu(Menu menu);
    List<Menu> createMenus(List<Menu> menus);
}
