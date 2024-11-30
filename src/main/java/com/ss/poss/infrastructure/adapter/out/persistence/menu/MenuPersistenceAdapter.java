package com.ss.poss.infrastructure.adapter.out.persistence.menu;

import com.ss.poss.application.port.out.menu.MenuOutputPort;
import com.ss.poss.domain.menu.model.Menu;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MenuPersistenceAdapter implements MenuOutputPort {
    private final MenuRepository menuRepository;

    public MenuPersistenceAdapter(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu saveMenu(Menu menu) {

        return null;
    }

    @Override
    public Optional<Menu> getMenuById(UUID id) {
        return Optional.empty();
    }
}
