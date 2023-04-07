--liquibase formatted sql

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--changeset Ihor:1680873560
create table category
(
    category_id uuid primary key,
    category_name varchar(255)
);

--changeset Ihor:1680873547
create table client_order
(
    order_id uuid primary key,
    total_amount DECIMAL(10,2)
);

--changeset Ihor:1680873557
create table product
(
    product_id    uuid primary key,
    price         DECIMAL(10,2),
    sku           VARCHAR(50) UNIQUE,
    product_name VARCHAR(255),
    category_id_fk uuid constraint products_to_category references category (category_id)
--     constraint product_to_item foreign key(product_id) references order_item(item_id)
);

--changeset Ihor:1680873553
create table order_item
(
    item_id     uuid primary key,
    quantity    integer,
    product_id_fk uuid constraint item_to_product references product (product_id),
    order_id_fk uuid constraint items_to_order references client_order (order_id)
);








