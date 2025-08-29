package com.ss.poss.application.port.in.order;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atMost;

public class GetOrderUseCaseTest extends OrderUseCasesTest{
    private final GetOrderUseCase getOrderUseCase = orderService;

    @Test
    void  getOrder() {
        given(orderPersistenceAdapter.getOrderById(order.orderId())).willReturn(Optional.of(order));
        var result = getOrderUseCase.getOrderById(order.orderId());
        assertAll(()-> {
            Mockito.verify(orderPersistenceAdapter, atMost(1)).getOrderById(order.orderId());
            assertNotNull(result);
            assertEquals(order, result);
            assertEquals(order.orderId(), result.orderId());
        });
    }
}
