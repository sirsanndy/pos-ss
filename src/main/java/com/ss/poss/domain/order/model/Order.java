package com.ss.poss.domain.order.model;

import com.ss.poss.domain.orderdetail.model.OrderDetail;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record Order (
        UUID orderId,
        OrderStatus orderStatus,
        BigDecimal totalPrice,
        List<OrderDetail> listItem
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -3761931517638975180L;
}
