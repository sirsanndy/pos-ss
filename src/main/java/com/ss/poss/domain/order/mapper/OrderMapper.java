package com.ss.poss.domain.order.mapper;

import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.orderdetail.model.OrderDetail;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderDetailEntity;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface OrderMapper {
    OrderEntity toEntity(Order order);

    @Mapping(target = "listItem", qualifiedByName = "orderDetailEntityToDto")
    Order toOrder(OrderEntity orderEntity);

    @Mapping(target = "order", ignore = true)
    OrderDetailEntity toEntity(OrderDetail orderDetail);

    @Mapping(target = "order", ignore = true)
    @Named("orderDetailEntityToDto")
    OrderDetail toOrderDetail(OrderDetailEntity orderDetailEntity);
}
