package com.ss.poss.application.port.in.menucategory;

import com.ss.poss.domain.menucategory.model.MenuCategory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class CreateMenuCategoryUseCaseTest extends MenuCategoryUseCasesTest {
    private final CreateMenuCategoryUseCase createMenuCategoryUseCase = menuCategoryService;

    @Test
    public void createMenuCategory() {
        given(menuCategoryPersistenceAdapter.saveMenuCategory(menuCategory)).willReturn(menuCategory);
        MenuCategory result = createMenuCategoryUseCase.createMenuCategory(menuCategory);
        assertAll(()-> {
            Mockito.verify(menuCategoryPersistenceAdapter, Mockito.times(1)).saveMenuCategory(menuCategory);
            assertNotNull(result);
            assertEquals(result, menuCategory);
            assertEquals(result.menuCategoryId(), menuCategory.menuCategoryId());
        });
    }
}
