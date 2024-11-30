package com.ss.poss.domain.orderdetail.mapper;

import com.ss.poss.domain.order.mapper.OrderMapper;
import com.ss.poss.domain.orderdetail.model.OrderDetail;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderDetailEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface OrderDetailMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class );

    @Mapping(target = "order", ignore = true)
    OrderDetailEntity toEntity(OrderDetail orderDetail);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "menu", ignore = true)
    OrderDetail toOrderDetail(OrderDetailEntity entity);
}
