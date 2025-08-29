package com.ss.poss.application.port.in.order;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

public class CreateOrderUseCaseTest extends OrderUseCasesTest{
    private final CreateOrderUseCase createOrderUseCase = orderService;

    @Test
    public void createOrder() throws IOException {
        given(orderPersistenceAdapter.saveOrder(order)).willReturn(order);
        doNothing().when(orderWebSocketHandler).broadcastOrder(Mockito.anyString());
        var result = createOrderUseCase.createOrder(order);
        assertAll(()-> {
            Mockito.verify(orderPersistenceAdapter, Mockito.times(1)).saveOrder(order);
            Mockito.verify(orderWebSocketHandler, Mockito.times(1)).broadcastOrder(Mockito.anyString());
            assertNotNull(result);
            assertEquals(order, result);
            assertEquals(order.orderId(), result.orderId());
        });
    }
}
