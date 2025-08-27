package com.ss.poss.application.port.in.order;

import com.ss.poss.domain.order.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class GetListOrderUseCaseTest extends OrderUseCasesTest {
    private final GetListOrderUseCase getListOrderUseCase = orderService;

     @Test
     void getListOrder() {
         given(orderPersistenceAdapter.getAllOrders()).willReturn(orders);
         List<Order> result = getListOrderUseCase.getAllOrder();
         assertAll(()-> {
             assertNotNull(result);
             assertFalse(result.isEmpty());
             assertEquals(orders.size(), result.size());
             Mockito.verify(orderPersistenceAdapter, Mockito.times(1)).getAllOrders();
             for (Order orderTest : result) {
                 assertNotNull(orderTest);
                 assertTrue(orders.contains(orderTest));
             }
         });
     }
}
