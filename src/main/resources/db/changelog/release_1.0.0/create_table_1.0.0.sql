--liquibase formatted sql

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- Order -> OrderItem -> Product <- Category

--changeset Ihor:1680873560
create table category
(
    category_id   uuid not null,
    category_name varchar(255),
    primary key (category_id)
);

--changeset Ihor:1680873547
create table product
(
    product_id             uuid,
    price          DECIMAL(10, 2),
    sku            VARCHAR(50),
    product_name   VARCHAR(255),
    category_id_fk uuid constraint products_to_category references category (category_id),
    primary key (product_id)

);

--changeset Ihor:1680873557
create table client_order
(
    order_id     uuid not null,
    total_amount DECIMAL(10, 2),
    primary key (order_id),
    order_date date not null default current_date
);

--changeset Ihor:1680873553
create table order_item
(
    product_id  uuid primary key unique constraint item_to_order references product (product_id),
    quantity    integer,
    order_id_fk uuid constraint items_to_order references client_order (order_id)
);
















