package com.ss.poss.application.port.in.order;

import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.order.service.OrderService;
import com.ss.poss.infrastructure.adapter.out.persistence.order.OrderPersistenceAdapter;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
public class OrderUseCasesTest {
    protected static Order order;
    protected static List<Order> orders;
    protected final OrderPersistenceAdapter orderPersistenceAdapter = Mockito.mock(OrderPersistenceAdapter.class);
    protected final OrderService orderService = new OrderService(orderPersistenceAdapter);

    @BeforeAll
    static void setUp() {
        order = Instancio.create(Order.class);
        orders = Instancio.ofList(Order.class).size(5).create();
    }
}
