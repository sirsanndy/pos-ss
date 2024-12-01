package com.ss.poss.application.port.in.order;

import com.ss.poss.domain.order.model.OrderWebhook;

public interface WebhookOrderUserCase {
    void send(OrderWebhook orderWebhook);
}
