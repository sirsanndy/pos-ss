package com.ss.poss.infrastructure.adapter.out.persistence.menu;

import com.ss.poss.application.port.out.menu.MenuOutputPort;
import com.ss.poss.domain.menu.mapper.MenuMapper;
import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.infrastructure.adapter.config.Adapter;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Adapter
public class MenuPersistenceAdapter implements MenuOutputPort {
    private static final Logger LOG = LoggerFactory.getLogger(MenuPersistenceAdapter.class);

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    public MenuPersistenceAdapter(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    public Menu saveMenu(Menu menu) {
        LOG.info("SAVE MENU IN PERSISTENCE LAYER TO DB STARTED");
        MenuEntity menuEntity = menuMapper.toEntity(menu);
        menuRepository.save(menuEntity);
        menu.setId(menuEntity.getMenuId());
        LOG.info("SAVE MENU IN PERSISTENCE LAYER TO DB FINISHED");
        return menu;
    }

    @Override
    public Optional<Menu> getMenuById(UUID id) {
        LOG.info("GET MENU IN PERSISTENCE LAYER FROM DB");
        MenuEntity menuEntity = menuRepository.findByMenuId(id).orElse(null);
        if(Objects.nonNull(menuEntity)){
            return Optional.of(menuMapper.toMenu(menuEntity));
        }
        return Optional.empty();
    }
}
