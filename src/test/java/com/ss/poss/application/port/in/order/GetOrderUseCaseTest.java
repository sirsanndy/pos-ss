package com.ss.poss.application.port.in.order;

import com.ss.poss.domain.order.model.Order;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

public class GetOrderUseCaseTest extends OrderUseCasesTest{
    private final GetOrderUseCase getOrderUseCase = orderService;

    @Test
    void  getOrder() {
        given(orderPersistenceAdapter.getOrderById(order.getOrderId())).willReturn(Optional.of(order));
        Order result = getOrderUseCase.getOrderById(order.getOrderId());
        assertNotNull(result);
        assertEquals(order, result);
        assertEquals(order.getOrderId(), result.getOrderId());
    }
}
