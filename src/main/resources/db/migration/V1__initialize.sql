create table customers (
    id                      bigserial,
    name                    varchar(255) not null,
    primary key (id)
);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int
);

create table orders (
    id                      bigserial primary key,
    customer_id             bigint references customers(id),
    price                   int
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price                   int,
    price_per_product       int,
    quantity                int
);

insert into customers (name)
values
('Bob'),
('John'),
('Jack');

insert into products (title, price)
values
('Bread1', 21),
('Bread2', 22),
('Bread3', 23),
('Bread4', 24),
('Bread5', 25),
('Bread6', 26),
('Bread7', 27),
('Bread8', 28),
('Bread9', 29),
('Bread10', 31),
('Bread11', 32),
('Bread12', 33),
('Bread13', 34),
('Bread14', 35),
('Bread15', 36),
('Bread16', 37),
('Bread17', 38),
('Bread18', 39),
('Bread19', 40);

create table student  (
                             id                         bigserial primary key,
                             name                       varchar(255),
                             age                        int
);

insert into student (name, age)
values
('Ivanov', 21),
('Stepanov', 22),
('Petrov', 23),
('Marcov', 24),
('Simonov', 25),
('Grena', 26),
('Job', 27),
('Jon', 28),
('Den', 29);