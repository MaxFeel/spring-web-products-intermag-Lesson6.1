create table categories
(
    id              bigserial primary key,
    title           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into categories (title) values ('Техника');

create table if not exists products (
    id bigserial    primary key,
    title           varchar(255),
    price           int,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
    );
insert into products (title, price)
values
('Телефон',5000),
('Ноутбук',50000),
('Монитор',30000),
('Клавиатура',2000),
('Мышь',1000),
('Колонка',15000),
('Принтер',20000),
('Телевизор',30000);

create table orders
(
    id              bigserial primary key,
    username        varchar(255),
    total_price     int,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table orders_items
(
    id                      bigserial primary key,
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    price_per_product       int,
    quantity                int,
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);




