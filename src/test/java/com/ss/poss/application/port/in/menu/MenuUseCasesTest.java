package com.ss.poss.application.port.in.menu;

import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.domain.menu.model.MenuWebhook;
import com.ss.poss.domain.menu.service.MenuService;
import com.ss.poss.infrastructure.adapter.out.persistence.menu.MenuPersistenceAdapter;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
public class MenuUseCasesTest {
    protected static Menu menu;
    protected static List<Menu> menus;
    protected final MenuPersistenceAdapter menuPersistenceAdapter = Mockito.mock(MenuPersistenceAdapter.class);
    protected final MenuService menuService = new MenuService(menuPersistenceAdapter);
    protected static MenuWebhook menuWebhook;

    @BeforeAll
    static void setUp() {
        menu = Instancio.create(Menu.class);
        menus = Instancio.ofList(Menu.class).size(5).create();
        menuWebhook = Instancio.create(MenuWebhook.class);
    }
}
