package com.ss.poss.infrastructure.adapter.in.web;

import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/webhook/{orderId}")
    public ResponseEntity<Order> getOrderWebhook(@PathVariable UUID orderId){
        try {
            Order order = orderService.getOrderById(orderId);
            return ResponseEntity.ok(order);
        } catch (Exception e){
            LOG.error("ERROR WHEN GET ORDER WEBHOOK REQUEST : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<Order> submit(@RequestBody Order order){
        try {
            order = orderService.createOrder(order);
            return ResponseEntity.ok(order);
        } catch (Exception e){
            LOG.error("ERROR WHEN CREATE ORDER REQUEST : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
