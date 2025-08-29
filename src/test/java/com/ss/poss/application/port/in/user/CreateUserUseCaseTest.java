package com.ss.poss.application.port.in.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class CreateUserUseCaseTest extends UserUseCasesTest{
    private final CreateUserUseCase createUserUseCase = userService;

    @Test
    public void createUser() {
        given(userPersistenceAdapter.saveUser(user)).willReturn(user);
        var result = createUserUseCase.saveUser(user);
        assertAll(() -> {
            Mockito.verify(userPersistenceAdapter, Mockito.times(1)).saveUser(user);
            assertNotNull(result);
            assertEquals(user, result);
            assertEquals(user.userId(), result.userId());
        });
    }
}
