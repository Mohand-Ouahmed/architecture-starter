package com.mohanda.archstarter.infrastructure.persistence;

import com.mohanda.archstarter.domain.model.Order;
import com.mohanda.archstarter.domain.model.OrderStatus;
import com.mohanda.archstarter.domain.port.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Adaptateur : implemente le port du domaine (OrderRepository) en s'appuyant
   * sur Spring Data JPA. C'est la seule classe qui fait le pont entre le
   * domaine et la persistance.
   */
@Repository
  public class OrderJpaRepositoryAdapter implements OrderRepository {

    private final SpringDataOrderRepository springDataOrderRepository;

    public OrderJpaRepositoryAdapter(SpringDataOrderRepository springDataOrderRepository) {
              this.springDataOrderRepository = springDataOrderRepository;
    }

    @Override
        public Order save(Order order) {
                  OrderEntity entity = new OrderEntity(
                                    order.getId(), order.getCustomerId(), order.getAmount(), order.getStatus().name());
                  springDataOrderRepository.save(entity);
                  return order;
        }

    @Override
        public Optional<Order> findById(String id) {
                  return springDataOrderRepository.findById(id).map(entity -> {
                                Order order = new Order(entity.getId(), entity.getCustomerId(), entity.getAmount());
                                if (OrderStatus.valueOf(entity.getStatus()) == OrderStatus.CONFIRMED) {
                                                  order.confirm();
                                }
                                return order;
                  });
        }
  }
