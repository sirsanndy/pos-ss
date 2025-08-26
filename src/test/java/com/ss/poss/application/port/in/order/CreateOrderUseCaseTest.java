package com.ss.poss.application.port.in.order;

import com.ss.poss.domain.order.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

public class CreateOrderUseCaseTest extends OrderUseCasesTest{
    private final CreateOrderUseCase createOrderUseCase = orderService;

    @Test
    public void createOrder() {
        given(orderPersistenceAdapter.saveOrder(order)).willReturn(order);
        Order result = createOrderUseCase.createOrder(order);

        Mockito.verify(orderPersistenceAdapter, Mockito.times(1)).saveOrder(order);
        assertNotNull(result);
        assertEquals(order, result);
        assertEquals(order.getOrderId(), result.getOrderId());
    }
}
