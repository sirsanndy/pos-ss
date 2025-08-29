package com.ss.poss.application.port.in.menu;

import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.domain.menucategory.model.MenuCategory;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class CreateMenuUseCaseTest extends MenuUseCasesTest {
    private final CreateMenuUseCase createMenuUseCase = menuService;

    @Test
    void createMenu() {
        final var menuCategory = Instancio.create(MenuCategory.class);
        given(getMenuCategoryUseCase.getMenuCategoryById(menu.menuCategoryId())).willReturn(menuCategory);
        given(menuPersistenceAdapter.saveMenu(menu)).willReturn(menu);
        var result = createMenuUseCase.createMenu(menu);
        assertAll(()->{
            Mockito.verify(menuPersistenceAdapter, Mockito.times(1)).saveMenu(menu);
            Mockito.verify(getMenuCategoryUseCase, Mockito.times(1)).getMenuCategoryById(menu.menuCategoryId());
            assertNotNull(result);
            assertEquals(menu, result);
            assertEquals(menu.menuId(), result.menuId());
        });
    }

    @Test
    void createMenus() {
        given(menuPersistenceAdapter.createMenus(menus)).willReturn(menus);
        List<Menu> result = createMenuUseCase.createMenus(menus);
        assertAll(()-> {
            Mockito.verify(menuPersistenceAdapter, Mockito.times(1)).createMenus(menus);
            assertNotNull(result);
            assertFalse(result.isEmpty());
            for (Menu menuTest : result) {
                assertNotNull(menuTest.menuId());
                assertTrue(menus.contains(menuTest));
            }
        });
    }
}
