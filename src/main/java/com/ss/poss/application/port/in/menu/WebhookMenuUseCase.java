package com.ss.poss.application.port.in.menu;

import com.ss.poss.domain.menu.model.MenuWebhook;

public interface WebhookMenuUseCase {
    void send(MenuWebhook menuWebhook);
}
