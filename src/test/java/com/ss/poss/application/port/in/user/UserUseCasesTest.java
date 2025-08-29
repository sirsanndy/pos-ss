package com.ss.poss.application.port.in.user;

import com.ss.poss.domain.user.model.User;
import com.ss.poss.domain.user.service.UserService;
import com.ss.poss.infrastructure.adapter.out.persistence.user.UserPersistenceAdapter;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

public class UserUseCasesTest {
    protected static User user;
    protected UserPersistenceAdapter userPersistenceAdapter = Mockito.mock(UserPersistenceAdapter.class);
    protected UserService userService = new UserService(userPersistenceAdapter);

    @BeforeAll
    public static void setUp() {
        user = Instancio.create(User.class);
    }
}
