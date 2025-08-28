package com.ss.poss.application.port.in.orderdetail;

import com.ss.poss.domain.orderdetail.model.OrderDetail;
import com.ss.poss.domain.orderdetail.service.OrderDetailService;
import com.ss.poss.infrastructure.adapter.out.persistence.orderdetail.OrderDetailPersistenceAdapter;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
public class OrderDetailUseCasesTest {
    protected static OrderDetail orderDetail;
    protected static List<OrderDetail> orderDetailList;
    protected OrderDetailPersistenceAdapter orderDetailPersistenceAdapter = Mockito.mock(OrderDetailPersistenceAdapter.class);
    protected OrderDetailService orderDetailService = new OrderDetailService(orderDetailPersistenceAdapter);

    @BeforeAll
    public static void setUp() {
        orderDetail = Instancio.create(OrderDetail.class);
        orderDetailList = Instancio.ofList(OrderDetail.class).size(5).create();
    }

}
