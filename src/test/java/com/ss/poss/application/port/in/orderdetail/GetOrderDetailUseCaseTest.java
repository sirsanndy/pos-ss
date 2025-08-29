package com.ss.poss.application.port.in.orderdetail;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class GetOrderDetailUseCaseTest extends OrderDetailUseCasesTest {
    private final GetOrderDetailUseCase getOrderDetailUseCase = orderDetailService;

    @Test
    public void getOrderDetail() {
        UUID orderId = orderDetail.orderId();
        given(orderDetailPersistenceAdapter.getOrderDetailById(orderId)).willReturn(Optional.of(orderDetail));
        var result = getOrderDetailUseCase.getOrderDetailById(orderId);
        assertAll(()-> {
            Mockito.verify(orderDetailPersistenceAdapter).getOrderDetailById(orderId);
            assertNotNull(result);
            assertNotNull(result.orderId());
            assertEquals(orderDetail.orderId(), result.orderId());
            assertEquals(orderDetail.menuId(), result.menuId());
        });
    }
}
