package com.ss.poss.application.port.in.order;

import org.junit.jupiter.api.Test;

public class DeleteOrderUseCaseTest extends OrderUseCasesTest{
    private final DeleteOrderUseCase deleteOrderUseCase = orderService;

    @Test
    void deleteOrder() {
        deleteOrderUseCase.deleteOrder(order);
    }
}
