package com.mohanda.archstarter.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataOrderRepository extends JpaRepository<OrderEntity, String> {
}
