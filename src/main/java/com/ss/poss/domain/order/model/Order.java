package com.ss.poss.domain.order.model;

import com.ss.poss.domain.menu.model.Menu;
import lombok.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private UUID id;
    private String orderStatus;
    private BigDecimal totalPrice;
    private Collection<Menu> listItem;
}
