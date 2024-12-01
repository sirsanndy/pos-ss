package com.ss.poss.infrastructure.adapter.in.web;

import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.order.model.OrderWebhook;
import com.ss.poss.domain.order.service.OrderService;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<Order> getOrderWebhook(@RequestBody OrderWebhook orderWebhook){
        try {
            orderService.send(orderWebhook);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            LOG.error("ERROR WHEN GET ORDER WEBHOOK REQUEST : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<Order> submit(@RequestBody @NotNull Order order){
        LOG.info("SUBMIT ORDER REQUEST : {} STARTED", order.getOrderId());
        try {
            order = orderService.createOrder(order);
            return ResponseEntity.ok(order);
        } catch (Exception e){
            LOG.error("ERROR WHEN SUBMIT ORDER REQUEST : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        } finally {
            LOG.info("SUBMIT ORDER REQUEST : {} FINISHED", order.getOrderId());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody @NotNull Order order){
        LOG.info("UPDATE ORDER REQUEST : {} STARTED", order.getOrderId());
        try {
            order = orderService.createOrder(order);
            return ResponseEntity.ok(order);
        } catch (Exception e){
            LOG.error("ERROR WHEN CREATE ORDER REQUEST : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        } finally {
            LOG.info("UPDATE ORDER REQUEST : {} FINISHED", order.getOrderId());
        }
    }
}
