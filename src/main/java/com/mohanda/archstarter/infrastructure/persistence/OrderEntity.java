package com.mohanda.archstarter.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Entite JPA : detail d'infrastructure. Le domaine ne connait pas cette
   * classe -- c'est OrderJpaRepositoryAdapter qui fait la conversion.
   */
@Entity
  @Table(name = "orders")
  public class OrderEntity {

    @Id
        private String id;
        private String customerId;
        private BigDecimal amount;
        private String status;

    protected OrderEntity() {
              // requis par JPA
    }

    public OrderEntity(String id, String customerId, BigDecimal amount, String status) {
              this.id = id;
              this.customerId = customerId;
              this.amount = amount;
              this.status = status;
    }

    public String getId() { return id; }
        public String getCustomerId() { return customerId; }
        public BigDecimal getAmount() { return amount; }
        public String getStatus() { return status; }
  }
