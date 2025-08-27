package com.ss.poss.application.port.in.menucategory;

import com.ss.poss.domain.menucategory.model.MenuCategory;
import com.ss.poss.domain.menucategory.service.MenuCategoryService;
import com.ss.poss.infrastructure.adapter.out.persistence.menucategory.MenuCategoryPersistenceAdapter;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
public class MenuCategoryUseCasesTest {
    protected static MenuCategory menuCategory;
    protected static List<MenuCategory> menuCategoryList;
    protected final MenuCategoryPersistenceAdapter menuCategoryPersistenceAdapter = Mockito.mock(MenuCategoryPersistenceAdapter.class);
    protected final MenuCategoryService menuCategoryService = new MenuCategoryService(menuCategoryPersistenceAdapter);

    @BeforeAll
    public static void setUp() {
        menuCategory = Instancio.create(MenuCategory.class);
        menuCategoryList = Instancio.ofList(MenuCategory.class).size(5).create();
    }
}
