package com.bymdev.grocerystore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "item_id")
    UUID itemId;

    @Column(name = "quantity")
    int quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    Product product;
}
