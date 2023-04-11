package com.bymdev.grocerystore.elasticsearch;

import com.bymdev.grocerystore.domain.Order;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderElasticSearchRepository extends ElasticsearchRepository<Order, Integer> {

    @Query("{ \"nested\": { \"path\": \"orderItems\", \"query\": { \"match\": { \"orderItems.product.productName\": \"?0\" } } } }")
    List<Order> findByProductName(String productName);

    @Query("{ \"nested\": { \"path\": \"orderItems\", \"query\": { \"match\": { \"orderItems.color\": \"?0\" } } } }")
    List<Order> findByColor(String color);

}
