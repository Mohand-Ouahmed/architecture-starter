package com.mohanda.archstarter.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Objet metier pur : aucune annotation Spring ni JPA.
   * Le domaine ne connait aucun detail d'infrastructure.
   */
public class Order {

    private final String id;
      private final String customerId;
      private final BigDecimal amount;
      private OrderStatus status;

    public Order(String id, String customerId, BigDecimal amount) {
              if (amount.signum() <= 0) {
                            throw new IllegalArgumentException("Le montant d'une commande doit etre positif");
              }
              this.id = Objects.requireNonNull(id);
              this.customerId = Objects.requireNonNull(customerId);
              this.amount = amount;
              this.status = OrderStatus.CREATED;
    }

    public void confirm() {
              if (status != OrderStatus.CREATED) {
                            throw new IllegalStateException("Seule une commande CREATED peut etre confirmee");
              }
              this.status = OrderStatus.CONFIRMED;
    }

    public String getId() { return id; }
      public String getCustomerId() { return customerId; }
      public BigDecimal getAmount() { return amount; }
      public OrderStatus getStatus() { return status; }
}
