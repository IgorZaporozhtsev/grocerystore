--liquibase formatted sql


-- Order -> OrderItem -> Product <- Category

--changeset Ihor:1680420393
-- alter table if exists order_item
-- add constraint items_to_order
--     foreign key (order_id_fk)
--         references client_order;



--changeset Ihor:1680420559
-- alter table if exists product
--     add constraint products_to_category
--         foreign key (category_id_fk)
--             references category;


