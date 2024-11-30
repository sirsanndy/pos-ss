package com.ss.poss.domain.order.model;

import com.ss.poss.domain.orderdetail.model.OrderDetail;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID orderId;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private Collection<OrderDetail> listItem;
}
