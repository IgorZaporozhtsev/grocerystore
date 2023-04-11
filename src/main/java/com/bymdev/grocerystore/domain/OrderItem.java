package com.bymdev.grocerystore.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Document(indexName = "item")
@Table(name = "order_item")
public class OrderItem{

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    @Field(type = FieldType.Integer)
    Integer id;

    @Column(name = "quantity")
    int quantity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @MapsId
    @Field(type = FieldType.Object)
    Product product;
}
