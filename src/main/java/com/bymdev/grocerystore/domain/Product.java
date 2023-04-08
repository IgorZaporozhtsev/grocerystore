package com.bymdev.grocerystore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    UUID productId;
    @Column(name = "price")
    BigDecimal price;
    @Column(name = "sku")
    String sku;
    @Column(name = "product_name")
    String productName;
}
