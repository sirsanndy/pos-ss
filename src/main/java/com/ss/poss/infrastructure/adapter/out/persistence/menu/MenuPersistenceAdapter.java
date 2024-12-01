package com.ss.poss.infrastructure.adapter.out.persistence.menu;

import com.ss.poss.application.port.out.menu.MenuOutputPort;
import com.ss.poss.domain.menu.mapper.MenuMapper;
import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.infrastructure.adapter.config.Adapter;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Adapter
@Transactional(readOnly = true)
public class MenuPersistenceAdapter implements MenuOutputPort {
    private static final Logger LOG = LoggerFactory.getLogger(MenuPersistenceAdapter.class);

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    public MenuPersistenceAdapter(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Menu saveMenu(Menu menu) {
        LOG.info("SAVE MENU IN PERSISTENCE LAYER TO DB STARTED");
        MenuEntity menuEntity;
        if(Objects.nonNull(menu.getMenuId())) {
            menuEntity = menuRepository.findByMenuIdLocked(menu.getMenuId())
                    .orElse(new MenuEntity());
            menuEntity.setDescription(menu.getDescription());
            menuEntity.setName(menu.getName());
            menuEntity.setStock(menu.getStock());
            menuEntity.setPrice(menu.getPrice());
        } else {
            menuEntity = menuMapper.toEntity(menu);
        }
        menuEntity = menuRepository.save(menuEntity);
        menu.setMenuId(menuEntity.getMenuId());
        LOG.info("SAVE MENU IN PERSISTENCE LAYER TO DB FINISHED");
        return menu;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public List<Menu> createMenus(List<Menu> menus) {
        LOG.info("SAVE LIST OF MENU IN PERSISTENCE LAYER TO DB STARTED");
        List<MenuEntity> menuEntityList = menus.stream().map(menuMapper::toEntity)
                .toList();
        menuRepository.saveAll(menuEntityList);
        menus = menuEntityList.stream()
                        .map(menuMapper::toMenu).toList();
        LOG.info("SAVE LIST OF MENU IN PERSISTENCE LAYER TO DB FINISHED");
        return menus;
    }

    @Override
    public Optional<Menu> getMenuById(UUID id) {
        LOG.info("GET MENU IN PERSISTENCE LAYER FROM DB");
        MenuEntity menuEntity = menuRepository.findById(id).orElse(null);
        if(Objects.nonNull(menuEntity)){
            return Optional.of(menuMapper.toMenu(menuEntity));
        }
        return Optional.empty();
    }

    @Override
    public List<Menu> getMenuList() {
        LOG.info("GET LIST OF MENU IN PERSISTENCE LAYER FROM DB");
        return menuRepository.findAllByOrderByCreatedAtDesc().stream().map(menuMapper::toMenu).toList();
    }
}
