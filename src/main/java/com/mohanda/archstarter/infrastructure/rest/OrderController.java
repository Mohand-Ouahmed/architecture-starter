package com.mohanda.archstarter.infrastructure.rest;

import com.mohanda.archstarter.application.OrderService;
import com.mohanda.archstarter.domain.model.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
  @RequestMapping("/api/orders")
  public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
              this.orderService = orderService;
    }

    @PostMapping
        public OrderResponse create(@RequestBody CreateOrderRequest request) {
                  Order order = orderService.createOrder(request.customerId(), request.amount());
                  return OrderResponse.from(order);
        }

    @PostMapping("/{id}/confirm")
        public OrderResponse confirm(@PathVariable String id) {
                  Order order = orderService.confirmOrder(id);
                  return OrderResponse.from(order);
        }

    public record CreateOrderRequest(String customerId, BigDecimal amount) {}

    public record OrderResponse(String id, String customerId, BigDecimal amount, String status) {
              static OrderResponse from(Order order) {
                            return new OrderResponse(order.getId(), order.getCustomerId(), order.getAmount(), order.getStatus().name());
    }
  }
}
