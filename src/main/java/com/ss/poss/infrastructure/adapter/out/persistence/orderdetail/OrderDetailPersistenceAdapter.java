package com.ss.poss.infrastructure.adapter.out.persistence.orderdetail;

import com.ss.poss.application.port.out.orderdetail.OrderDetailOutputPort;
import com.ss.poss.domain.orderdetail.mapper.OrderDetailMapper;
import com.ss.poss.domain.orderdetail.model.OrderDetail;
import com.ss.poss.infrastructure.adapter.config.Adapter;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderDetailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Adapter
public class OrderDetailPersistenceAdapter implements OrderDetailOutputPort {
    private static final Logger LOG = LoggerFactory.getLogger(OrderDetailPersistenceAdapter.class);
    
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailPersistenceAdapter(OrderDetailRepository orderDetailRepository, OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        LOG.info("SAVE ORDER DETAIL IN PERSISTENCE LAYER TO DB STARTED");
        OrderDetailEntity orderDetailEntity = orderDetailMapper.toEntity(orderDetail);
        orderDetailRepository.save(orderDetailEntity);
        orderDetail.setOrderDetailId(orderDetailEntity.getOrderDetailId());
        LOG.info("SAVE ORDER DETAIL IN PERSISTENCE LAYER TO DB FINISHED");
        return orderDetail;
    }

    @Override
    public Optional<OrderDetail> getOrderDetailById(UUID id) {
        LOG.info("GET ORDER DETAIL IN PERSISTENCE LAYER FROM DB BY ID {}", id);
        OrderDetailEntity orderDetailEntity = orderDetailRepository.findById(id).orElse(null);
        if(Objects.nonNull(orderDetailEntity)){
            return Optional.of(orderDetailMapper.toOrderDetail(orderDetailEntity));
        }
        return Optional.empty();
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(UUID orderId) {
        LOG.info("GET LIST OF ORDER DETAIL IN PERSISTENCE LAYER FROM DB BY ID : {}", orderId);
        List<OrderDetailEntity> orderDetailEntityList = orderDetailRepository.findByOrder_OrderId(orderId);
        return orderDetailEntityList.stream().map(orderDetailMapper::toOrderDetail).toList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class, RuntimeException.class})
    public List<OrderDetail> saveOrderDetails(List<OrderDetail> orderDetailList) {
        LOG.info("SAVE ORDER DETAIL IN PERSISTENCE LAYER FROM DB STARTED");
        List<OrderDetailEntity> orderDetailEntityList = orderDetailList.stream().map(orderDetailMapper::toEntity).toList();
        orderDetailRepository.saveAll(orderDetailEntityList);
        LOG.info("SAVE ORDER DETAIL IN PERSISTENCE LAYER FROM DB FINISHED");
        return orderDetailList;
    }
}
