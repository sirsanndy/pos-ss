package com.ss.poss.application.port.in.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class GetUserUseCaseTest extends UserUseCasesTest{
    private final GetUserUseCase getUserUseCase = userService;

    @Test
    public void getUserById() {
        given(userPersistenceAdapter.getUserById(user.userId())).willReturn(user);
        var result = getUserUseCase.getUserById(user.userId());
        assertAll(() -> {
            Mockito.verify(userPersistenceAdapter, Mockito.times(1)).getUserById(user.userId());
            assertNotNull(result);
            assertEquals(user, result);
            assertEquals(user.userId(), result.userId());
        });
    }

    @Test
    public void getUserByUsername() {
        given(userPersistenceAdapter.getUserByUsername(user.username())).willReturn(user);
        var result = getUserUseCase.getUserByUsername(user.username());
        assertAll(() -> {
            Mockito.verify(userPersistenceAdapter, Mockito.times(1)).getUserByUsername(user.username());
            assertNotNull(result);
            assertEquals(user, result);
            assertEquals(user.userId(), result.userId());
        });
    }
}
