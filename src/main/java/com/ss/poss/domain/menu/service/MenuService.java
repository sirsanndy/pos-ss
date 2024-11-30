package com.ss.poss.domain.menu.service;

import com.ss.poss.application.port.in.menu.CreateMenuUseCase;
import com.ss.poss.application.port.in.menu.GetListMenuUseCase;
import com.ss.poss.application.port.in.menu.GetMenuUseCase;
import com.ss.poss.domain.menu.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuService implements CreateMenuUseCase, GetListMenuUseCase, GetMenuUseCase {
    @Override
    public Menu createMenu(Menu menu) {
        return null;
    }

    @Override
    public List<Menu> getListMenu() {
        return List.of();
    }

    @Override
    public Menu getMenuById(UUID menuId) {
        return null;
    }
}
