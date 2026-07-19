package com.mohanda.archstarter.application;

import com.mohanda.archstarter.domain.model.Order;
import com.mohanda.archstarter.domain.port.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Cas d'usage : orchestre le domaine via les ports. Ne depend que de
   * "domain", jamais de "infrastructure".
   */
@Service
  public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
              this.orderRepository = orderRepository;
    }

    public Order createOrder(String customerId, BigDecimal amount) {
              Order order = new Order(UUID.randomUUID().toString(), customerId, amount);
              return orderRepository.save(order);
    }

    public Order confirmOrder(String orderId) {
              Order order = orderRepository.findById(orderId)
                                .orElseThrow(() -> new IllegalArgumentException("Commande introuvable : " + orderId));
              order.confirm();
              return orderRepository.save(order);
    }
  }
