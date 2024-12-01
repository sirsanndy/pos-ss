package com.ss.poss.infrastructure.adapter.out.persistence.menu;

import com.ss.poss.domain.menu.mapper.MenuMapperImpl;
import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ActiveProfiles("test")
class MenuPersistenceAdapterTest {
    private final MenuRepository menuRepository = Mockito.mock(MenuRepository.class);
    private final MenuMapperImpl menuMapper = new MenuMapperImpl();
    private final MenuPersistenceAdapter menuPersistenceAdapter = new MenuPersistenceAdapter(menuRepository, menuMapper);
    private static Menu menu;
    private static MenuEntity menuEntity;

    @BeforeAll
    static void setUp() {
        menu = Instancio.create(Menu.class);
        menuEntity = Instancio.create(MenuEntity.class);
    }

    @Test
    void saveMenu() {
        menu.setMenuId(null);
        given(menuRepository.findByMenuIdLocked(any(UUID.class))).willReturn(Optional.of(menuEntity));
        given(menuRepository.save(any(MenuEntity.class))).willReturn(menuEntity);
        Menu objTest = menuPersistenceAdapter.saveMenu(menu);
        assertNotNull(objTest);
        assertNotNull(objTest.getMenuId());
        assertEquals(menuEntity.getMenuId(), objTest.getMenuId());
    }

    @Test
    void createMenus() {
    }

    @Test
    void getMenuById() {
        given(menuRepository.findById(any(UUID.class))).willReturn(Optional.of(menuEntity));
        Optional<Menu> objTest = menuPersistenceAdapter.getMenuById(menu.getMenuId());
        assertNotNull(objTest);
        assertTrue(objTest.isPresent());
        Menu objMenuTest = objTest.get();
        assertNotNull(objMenuTest.getMenuId());
        assertEquals(menuEntity.getMenuId(), objMenuTest.getMenuId());
    }

    @Test
    void getMenuList() {
    }
}