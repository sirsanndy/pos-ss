package com.ss.poss.domain.orderdetail.model;

import com.ss.poss.domain.menu.model.Menu;
import com.ss.poss.domain.order.model.Order;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private UUID id;
    private Order order;
    private Menu menu;
    private Integer quantity;
    private BigDecimal totalPrice;
}
