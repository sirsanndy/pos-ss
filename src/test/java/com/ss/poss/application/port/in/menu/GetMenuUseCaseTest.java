package com.ss.poss.application.port.in.menu;

import com.ss.poss.domain.menu.model.Menu;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class GetMenuUseCaseTest extends MenuUseCasesTest {
    private final GetMenuUseCase getMenuUseCase = menuService;

    @Test
    void getMenu() {
        Mockito.when(menuPersistenceAdapter.getMenuById(menu.getMenuId())).thenReturn(java.util.Optional.of(menu));
        Menu result = getMenuUseCase.getMenuById(menu.getMenuId());
        assertAll(()-> {
            Mockito.verify(menuPersistenceAdapter, Mockito.times(1)).getMenuById(menu.getMenuId());
            assertNotNull(result);
            assertEquals(menu, result);
            assertEquals(menu.getMenuId(), result.getMenuId());
        });
    }
}