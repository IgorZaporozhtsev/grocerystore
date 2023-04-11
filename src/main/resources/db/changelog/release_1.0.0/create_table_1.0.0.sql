--liquibase formatted sql

--changeset Ihor:1681212558
create sequence if not exists client_order_seq start with 1 increment by 50;
--changeset Ihor:1681212564
create sequence  if not exists category_seq start with 1 increment by 50;
--changeset Ihor:1681212569
create sequence  if not exists product_seq start with 1 increment by 50;
--changeset Ihor:1681227395
create sequence if not exists  order_item_seq start with 1 increment by 50;

--changeset Ihor:1680873560
create table category
(
    category_id  integer  not null,
    category_name varchar(255),
    primary key (category_id)
);

--changeset Ihor:1680873547
create table product
(
    product_id     integer,
    price          DECIMAL(10, 2),
    sku            VARCHAR(50),
    product_name   VARCHAR(255),
    category_id_fk integer constraint products_to_category references category (category_id),
    primary key (product_id)

);

--changeset Ihor:1680873557
create table client_order
(
    order_id     integer not null,
    title varchar(255),
    total_amount DECIMAL(10, 2),
    primary key (order_id),
    order_date date not null default current_date
);

--changeset Ihor:1680873553
create table order_item
(
    product_id  integer primary key unique constraint item_to_order references product (product_id),
    quantity    integer,
    order_id_fk integer constraint items_to_order references client_order (order_id)
);





