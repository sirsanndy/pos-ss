package com.ss.poss.application.port.in.menu;

import com.ss.poss.domain.menu.model.Menu;

import java.util.UUID;

public interface GetMenuUseCase {
    Menu getMenuById(UUID menuId);
}
