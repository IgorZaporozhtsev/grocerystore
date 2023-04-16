package com.bymdev.grocerystore.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Entity
@Builder
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
    private Integer id;

    @NotNull
    @Min(3)
    private BigDecimal price;

    private String sku;

    @NotBlank
    @Size(min = 2)
    @Column(name = "product_name")
    @Field(type = FieldType.Text, name = "productName")
    private String productName;
}
