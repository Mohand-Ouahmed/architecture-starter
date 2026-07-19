package com.mohanda.archstarter.domain.port;

import com.mohanda.archstarter.domain.model.Order;

import java.util.Optional;

/**
 * Port sortant : le domaine decrit ce dont il a besoin, sans savoir comment
 * c'est implemente (JPA, Mongo, en memoire...). L'implementation vit dans
 * infrastructure.persistence.
 */
public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(String id);
}
