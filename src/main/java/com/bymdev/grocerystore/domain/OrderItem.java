package com.bymdev.grocerystore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem implements Serializable {

    @Id
    @Column(name = "product_id")
    UUID itemId;

    @Column(name = "quantity")
    int quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @MapsId
    Product product;
}
