package com.ss.poss.application.port.in.menu;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

public class WebhookMenuUseCaseTest extends MenuUseCasesTest {
    private final WebhookMenuUseCase webhookMenuUseCase = menuService;

    @Test
    void sendMenuToWebhook() {
        webhookMenuUseCase.send(menuWebhook);
    }
}
