package com.ss.poss.application.port.in.orderdetail;

import com.ss.poss.domain.orderdetail.model.OrderDetail;
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
        UUID orderId = orderDetail.getOrderId();
        given(orderDetailPersistenceAdapter.getOrderDetailById(orderId)).willReturn(Optional.of(orderDetail));
        OrderDetail result = getOrderDetailUseCase.getOrderDetailById(orderId);
        assertAll(()-> {
            Mockito.verify(orderDetailPersistenceAdapter).getOrderDetailById(orderId);
            assertNotNull(result);
            assertNotNull(result.getOrderId());
            assertEquals(orderDetail.getOrderId(), result.getOrderId());
            assertEquals(orderDetail.getMenuId(), result.getMenuId());
        });
    }
}
