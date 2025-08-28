package com.ss.poss.domain.orderdetail.model;

import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.domain.order.model.Order;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record OrderDetail (
        UUID orderId,
        UUID menuId,
        Order order,
        Menu menu,
        Integer quantity,
        BigDecimal totalPrice
) implements Serializable {
    @Serial
    private static final  long serialVersionUID = -4138918901403855697L;
}
