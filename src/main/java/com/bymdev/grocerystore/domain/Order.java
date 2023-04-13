package com.bymdev.grocerystore.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(indexName = "order")
@Table(name = "client_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    @Field(type = FieldType.Integer)
    Integer id;

    @Field(type = FieldType.Text, name = "title")
    @Column(name = "title")
    String title;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;


    @NotEmpty
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id_fk")
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "order_date")
    @Field(type = FieldType.Date)
    private LocalDate orderDate = LocalDate.now();

    public void addOrderItems(List<OrderItem> items) {
        this.getOrderItems().clear();
        this.getOrderItems().addAll(items);
    }

}



