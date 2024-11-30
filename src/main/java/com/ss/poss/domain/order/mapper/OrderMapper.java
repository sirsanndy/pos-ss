package com.ss.poss.domain.order.mapper;

import com.ss.poss.domain.order.model.Order;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toEntity(Order order);
    Order toOrder(OrderEntity orderEntity);
}
