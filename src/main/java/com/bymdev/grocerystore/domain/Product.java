package com.bymdev.grocerystore.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Document(indexName = "product")
@Table(name = "product")
public class Product {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field(type = FieldType.Integer)
    @Column(name = "product_id")
    Integer id;

    BigDecimal price;

    String sku;

    @Column(name = "product_name")
    @Field(type = FieldType.Text, name = "productName")
    String productName;
}
