package com.ss.poss.application.port.in.menucategory;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class GetMenuCategoryUseCaseTest extends MenuCategoryUseCasesTest {
    private final GetMenuCategoryUseCase getMenuCategoryUseCase = menuCategoryService;

    @Test
    public void getMenuCategory() {
        UUID id = menuCategory.menuCategoryId();
        given(menuCategoryPersistenceAdapter.getMenuCategoryById(id)).willReturn(menuCategory);
        var result = getMenuCategoryUseCase.getMenuCategoryById(id);

        assertAll(()->{
            Mockito.verify(menuCategoryPersistenceAdapter, Mockito.times(1)).getMenuCategoryById(id);
            assertNotNull(result);
            assertEquals(result, menuCategory);
            assertEquals(result.menuCategoryId(), menuCategory.menuCategoryId());
        });
    }
}
