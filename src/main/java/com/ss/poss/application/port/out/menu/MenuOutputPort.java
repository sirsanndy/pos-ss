package com.ss.poss.application.port.out.menu;

import com.ss.poss.domain.menu.model.Menu;

import java.util.Optional;
import java.util.UUID;

public interface MenuOutputPort {
    Menu saveMenu(Menu menu);
    Optional<Menu> getMenuById(UUID id);
}
