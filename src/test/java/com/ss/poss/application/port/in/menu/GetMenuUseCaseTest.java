package com.ss.poss.application.port.in.menu;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class GetMenuUseCaseTest extends MenuUseCasesTest {
    private final GetMenuUseCase getMenuUseCase = menuService;

    @Test
    void getMenu() {
        Mockito.when(menuPersistenceAdapter.getMenuById(menu.menuId())).thenReturn(java.util.Optional.of(menu));
        var result = getMenuUseCase.getMenuById(menu.menuId());
        assertAll(()-> {
            Mockito.verify(menuPersistenceAdapter, Mockito.times(1)).getMenuById(menu.menuId());
            assertNotNull(result);
            assertEquals(menu, result);
            assertEquals(menu.menuId(), result.menuId());
        });
    }
}