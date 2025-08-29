package com.ss.poss.application.port.in.orderdetail;

import com.ss.poss.domain.order.model.Order;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class CreateOrderDetailUseCaseTest extends OrderDetailUseCasesTest {
    private final CreateOrderDetailUseCase createOrderDetailUseCase = orderDetailService;
    private static final Order order = Instancio.create(Order.class);
    @Test
    public void createOrderDetail() {
        given(orderDetailPersistenceAdapter.saveOrderDetail(orderDetail)).willReturn(orderDetail);
        var result = createOrderDetailUseCase.createOrderDetail(orderDetail);
        assertAll(()-> {
            Mockito.verify(orderDetailPersistenceAdapter).saveOrderDetail(orderDetail);
            assertNotNull(result);
            assertEquals(orderDetail, result);
            assertEquals(orderDetail.orderId(), result.orderId());
            assertEquals(orderDetail.menuId(), result.menuId());
        });
    }

    @Test
    public void createOrderDetails(){
        given(orderDetailPersistenceAdapter.saveOrderDetails(order)).willReturn(orderDetailList);
        var result = createOrderDetailUseCase.createOrderDetails(order);
        assertAll(()-> {
            Mockito.verify(orderDetailPersistenceAdapter).saveOrderDetails(order);
            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(orderDetailList.size(), result.size());
            for(var orderDetailTest : result){
                assertNotNull(orderDetailTest);
                assertTrue(orderDetailList.contains(orderDetailTest));
            }
        });
    }
}
