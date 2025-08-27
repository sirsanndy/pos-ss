package com.ss.poss.application.port.in.menucategory;

import com.ss.poss.domain.menucategory.model.MenuCategory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class GetListMenuCategoryUseCaseTest extends MenuCategoryUseCasesTest {
    private final GetListMenuCategoryUseCase getListMenuCategoryUseCase = menuCategoryService;

    @Test
    public void getListMenuCategory() {
        given(menuCategoryPersistenceAdapter.getMenuCategories()).willReturn(menuCategoryList);
        List<MenuCategory> result = getListMenuCategoryUseCase.getListMenuCategory();
        assertAll(()-> {
            Mockito.verify(menuCategoryPersistenceAdapter, Mockito.times(1)).getMenuCategories();
            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertAll(() -> {
                for(MenuCategory menuCategory : result) {
                    assertNotNull(menuCategory);
                    assertTrue(menuCategoryList.contains(menuCategory));
                }
            });
        });
    }
}
