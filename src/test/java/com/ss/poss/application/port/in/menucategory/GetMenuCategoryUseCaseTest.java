package com.ss.poss.application.port.in.menucategory;

import com.ss.poss.domain.menucategory.model.MenuCategory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class GetMenuCategoryUseCaseTest extends MenuCategoryUseCasesTest {
    private final GetMenuCategoryUseCase getMenuCategoryUseCase = menuCategoryService;

    @Test
    public void getMenuCategory() {
        UUID id = menuCategory.getMenuCategoryId();
        given(menuCategoryPersistenceAdapter.getMenuCategoryById(id)).willReturn(menuCategory);
        MenuCategory result = getMenuCategoryUseCase.getMenuCategoryById(id);

        assertAll(()->{
            Mockito.verify(menuCategoryPersistenceAdapter, Mockito.times(1)).getMenuCategoryById(id);
            assertNotNull(result);
            assertEquals(result, menuCategory);
            assertEquals(result.getMenuCategoryId(), menuCategory.getMenuCategoryId());
        });
    }
}
