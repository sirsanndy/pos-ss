package com.ss.poss.infrastructure.adapter.in.web;

import com.ss.poss.application.port.in.order.CreateOrderUseCase;
import com.ss.poss.application.port.in.order.GetListOrderUseCase;
import com.ss.poss.application.port.in.order.GetOrderUseCase;
import com.ss.poss.application.port.in.order.WebhookOrderUserCase;
import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.order.model.OrderWebhook;
import com.ss.poss.domain.order.service.OrderService;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);
    private final WebhookOrderUserCase webhookOrderUserCase;
    private final GetListOrderUseCase getListOrderUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    public OrderController(WebhookOrderUserCase webhookOrderUserCase, GetListOrderUseCase getListOrderUseCase,
                           CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase) {
        this.webhookOrderUserCase = webhookOrderUserCase;
        this.getListOrderUseCase = getListOrderUseCase;
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
    }

    @PostMapping("/webhook")
    public ResponseEntity<Order> getOrderWebhook(@RequestBody OrderWebhook orderWebhook){
        try {
            webhookOrderUserCase.send(orderWebhook);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            LOG.error("ERROR WHEN GET ORDER WEBHOOK REQUEST : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/get/{orderId}")
    public ResponseEntity<Order> get(@PathVariable UUID orderId){
        LOG.info("GET ORDER REQUEST : {} STARTED", orderId);
        try {
            Order order = getOrderUseCase.getOrderById(orderId);
            return ResponseEntity.ok(order);
        } catch (Exception e){
            LOG.error("ERROR WHEN GET ORDER REQUEST : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        } finally {
            LOG.info("GET ORDER REQUEST : {} FINISHED", orderId);
        }
    }

    @GetMapping("/get-list")
    public ResponseEntity<List<Order>> getListOrder(){
        LOG.info("GET LIST ORDER REQUEST STARTED");
        try {
            List<Order> orderList = getListOrderUseCase.getAllOrder();
            return ResponseEntity.ok(orderList);
        } catch (Exception e){
            LOG.error("ERROR WHEN GET LIST ORDER REQUEST : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        } finally {
            LOG.info("GET LIST ORDER REQUEST FINISHED");
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<Order> submit(@RequestBody @NotNull Order order){
        LOG.info("SUBMIT ORDER REQUEST : {} STARTED", order.getOrderId());
        try {
            order = createOrderUseCase.createOrder(order);
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
            order = createOrderUseCase.createOrder(order);
            return ResponseEntity.ok(order);
        } catch (Exception e){
            LOG.error("ERROR WHEN CREATE ORDER REQUEST : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        } finally {
            LOG.info("UPDATE ORDER REQUEST : {} FINISHED", order.getOrderId());
        }
    }
}
