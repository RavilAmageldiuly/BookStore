create database book_store_db
    with owner postgres;


create table if not exists author_book
(
    author_id integer
        constraint author_book_author_fk
            references authors,
    book_id   integer
        constraint author_book_book_fk
            references books
);

create table authors
(
    author_id  integer default nextval('authors_id_seq'::regclass) not null
        constraint authors_pk
            primary key,
    first_name varchar,
    last_name  varchar,
    patronymic varchar,
    birthday   date
);

create table books
(
    book_id         serial
        constraint books_pk
            primary key,
    price           double precision,
    publisher_id    integer
        constraint books_publisher_fk
            references publishers
            on update restrict on delete restrict,
    title           varchar,
    number_of_pages integer,
    release_year    integer,
    book_quantity   integer
);

create table genre_book
(
    genre_id integer
        constraint genre_book_genre_fk
            references genres,
    book_id  integer
        constraint genre_book_book_fk
            references books
);

create table genres
(
    genre_id   serial
        constraint genres_pk
            primary key,
    genre_name varchar
);

create table order_book
(
    order_id            integer
        constraint order_book_order_fk
            references orders,
    book_id             integer
        constraint order_book_book_fk
            references books,
    ordered_book_amount integer
);

create table orders
(
    order_id     serial
        constraint orders_pk
            primary key,
    user_id      integer
        constraint orders_users_fk
            references users,
    order_status varchar,
    order_time   timestamp
);

create table publishers
(
    publisher_id   integer default nextval('publisher_publisher_id_seq'::regclass) not null
        constraint publisher_pk
            primary key,
    publisher_name varchar
);

create table users
(
    user_id       integer default nextval('user_user_id_seq'::regclass) not null
        constraint user_pk
            primary key,
    username      varchar                                               not null,
    user_password varchar                                               not null,
    user_role     varchar                                               not null,
    block_flag    boolean                                               not null
);

create unique index user_user_login_uindex
    on users (username);

insert into authors values (0, 'Leo', 'Tolstoy', 'Nikolayevich', '1828-09-09');
insert into authors values (1, 'Fyodor', 'Dostoevsky', 'Mikhailovich', '1821-11-11');
insert into authors values (2, 'Aleksandr', 'Solzhenitsyn', 'Isayevich', '1918-12-11');

