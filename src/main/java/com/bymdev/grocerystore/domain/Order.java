package com.bymdev.grocerystore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "client_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @OneToMany
    @JoinColumn(name = "order_id_fk")
    private List<OrderItem> orderItems;

}
