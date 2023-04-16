package com.bymdev.grocerystore.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Builder
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
    private Integer id;

    @NotNull
    @Min(1)
    @Column(name = "quantity")
    private int quantity;


    @NotNull
    @Valid
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Field(type = FieldType.Object)
    private Product product;
}
