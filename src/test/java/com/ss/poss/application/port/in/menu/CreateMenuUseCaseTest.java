package com.ss.poss.application.port.in.menu;

import com.ss.poss.domain.menu.model.Menu;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
class CreateMenuUseCaseTest extends MenuUseCasesTest {
    private final CreateMenuUseCase createMenuUseCase = menuService;

    @Test
    void createMenu() {
        given(menuPersistenceAdapter.saveMenu(menu)).willReturn(menu);
        Menu result = createMenuUseCase.createMenu(menu);
        Mockito.verify(menuPersistenceAdapter, Mockito.times(1)).saveMenu(menu);

        assertNotNull(result);
        assertEquals(menu, result);
        assertEquals(menu.getMenuId(), result.getMenuId());
    }

    @Test
    void createMenus() {
        given(menuPersistenceAdapter.createMenus(menus)).willReturn(menus);
        List<Menu> result = createMenuUseCase.createMenus(menus);
        Mockito.verify(menuPersistenceAdapter, Mockito.times(1)).createMenus(menus);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        for (Menu menuTest : result) {
            assertNotNull(menuTest.getMenuId());
            assertTrue(menus.contains(menuTest));
        }
    }
}
