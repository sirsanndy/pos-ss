package com.ss.poss.infrastructure.adapter.out.persistence.menu;

import com.ss.poss.domain.menu.mapper.MenuMapperImpl;
import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.domain.menucategory.mapper.MenuCategoryMapper;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuCategoryEntity;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
class MenuPersistenceAdapterTest {
    private static Menu menu;
    private static MenuEntity menuEntity;
    private static MenuCategoryEntity menuCategoryEntity;
    private final MenuRepository menuRepository = Mockito.mock(MenuRepository.class);
    private final MenuCategoryMapper menuCategoryMapper = Mockito.mock(MenuCategoryMapper.class);
    private final MenuMapperImpl menuMapper = new MenuMapperImpl(menuCategoryMapper);
    private final MenuPersistenceAdapter menuPersistenceAdapter = new MenuPersistenceAdapter(menuRepository, menuMapper);

    @BeforeAll
    static void setUp() {
        menu = Instancio.create(Menu.class);
        menuEntity = Instancio.create(MenuEntity.class);
        menuCategoryEntity = Instancio.create(MenuCategoryEntity.class);
        menuCategoryEntity.setMenuCategoryId(menu.menuCategoryId());
    }

    @Test
    void saveMenu() {
        given(menuRepository.findByMenuIdLocked(any(UUID.class))).willReturn(Optional.of(menuEntity));
        given(menuRepository.save(any(MenuEntity.class))).willReturn(menuEntity);
        given(menuCategoryMapper.map(any(UUID.class))).willReturn(menuCategoryEntity);
        Menu objTest = menuPersistenceAdapter.saveMenu(menu);
        assertNotNull(objTest);
        assertNotNull(objTest.menuId());
        assertEquals(menuEntity.getMenuId(), objTest.menuId());
    }

    @Test
    void createMenus() {
    }

    @Test
    void getMenuById() {
        given(menuRepository.findById(any(UUID.class))).willReturn(Optional.of(menuEntity));
        Optional<Menu> objTest = menuPersistenceAdapter.getMenuById(menu.menuId());
        assertNotNull(objTest);
        assertTrue(objTest.isPresent());
        Menu objMenuTest = objTest.get();
        assertNotNull(objMenuTest.menuId());
        assertEquals(menuEntity.getMenuId(), objMenuTest.menuId());
    }

    @Test
    void getMenuList() {
    }
}