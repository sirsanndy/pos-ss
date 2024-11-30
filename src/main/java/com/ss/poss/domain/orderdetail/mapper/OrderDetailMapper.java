package com.ss.poss.domain.orderdetail.mapper;

import com.ss.poss.domain.orderdetail.model.OrderDetail;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderDetailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailEntity toEntity(OrderDetail orderDetail);
    OrderDetail toOrderDetail(OrderDetailEntity entity);
}
