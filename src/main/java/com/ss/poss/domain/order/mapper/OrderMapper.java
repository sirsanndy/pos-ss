package com.ss.poss.domain.order.mapper;

import com.ss.poss.domain.order.model.Order;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class );

    OrderEntity toEntity(Order order);
    Order toOrder(OrderEntity orderEntity);
}
