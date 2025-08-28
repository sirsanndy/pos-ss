package com.ss.poss.infrastructure.adapter.out.persistence.orderdetail;

import com.ss.poss.application.port.out.orderdetail.OrderDetailOutputPort;
import com.ss.poss.domain.menu.exception.MenuNotFoundException;
import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.orderdetail.mapper.OrderDetailMapper;
import com.ss.poss.domain.orderdetail.model.OrderDetail;
import com.ss.poss.infrastructure.adapter.config.Adapter;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.MenuEntity;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderDetailEntity;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.ss.poss.infrastructure.adapter.out.persistence.menu.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final MenuRepository menuRepository;

    public OrderDetailPersistenceAdapter(OrderDetailRepository orderDetailRepository, OrderDetailMapper orderDetailMapper, MenuRepository menuRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
        this.menuRepository = menuRepository;
    }

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        LOG.info("SAVE ORDER DETAIL IN PERSISTENCE LAYER TO DB STARTED");
        OrderDetailEntity orderDetailEntity = orderDetailMapper.toEntity(orderDetail);
        orderDetailRepository.save(orderDetailEntity);
        LOG.info("SAVE ORDER DETAIL IN PERSISTENCE LAYER TO DB FINISHED");
        return orderDetail;
    }

    @Override
    public Optional<OrderDetail> getOrderDetailById(UUID id) {
        LOG.info("GET ORDER DETAIL IN PERSISTENCE LAYER FROM DB BY ID {}", id);
        OrderDetailEntity orderDetailEntity = orderDetailRepository.findById(id).orElse(null);
        if (Objects.nonNull(orderDetailEntity)) {
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
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public List<OrderDetail> saveOrderDetails(Order order) {
        LOG.info("SAVE ORDER DETAIL IN PERSISTENCE LAYER FROM DB STARTED");
        List<OrderDetailEntity> orderDetailEntityList = order.getListItem().stream().map(obj -> {
            Optional<OrderDetailEntity> existOrderDetail = orderDetailRepository.findByOrderIdAndMenuId(order.getOrderId(), obj.menuId());

            OrderDetailEntity orderDetailEntity = orderDetailMapper.toEntity(obj);
            MenuEntity menuEntity = menuRepository.findByMenuIdLocked(obj.menuId())
                    .filter(menu -> menu.getStock() > 0)
                    .orElseThrow(() -> new MenuNotFoundException(String.format("MENU WITH ID %s IS NOT FOUND OR STOCK IS EMPTY", obj.menuId())));
            menuEntity.setStock(menuEntity.getStock() - (existOrderDetail.map(detailEntity -> obj.quantity() - detailEntity.getQuantity())
                    .orElseGet(obj::quantity)));
            orderDetailEntity.setMenu(menuEntity);
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(order.getOrderId());
            orderEntity.setTotalPrice(order.getTotalPrice());
            menuRepository.save(menuEntity);
            orderDetailEntity.setOrder(orderEntity);
            return orderDetailEntity;
        }).toList();
        orderDetailRepository.saveAll(orderDetailEntityList);
        LOG.info("SAVE ORDER DETAIL IN PERSISTENCE LAYER FROM DB FINISHED");
        return order.getListItem();
    }
}
