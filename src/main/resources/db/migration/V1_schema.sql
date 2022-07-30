create database book_store_db;

create table if not exists Authors
(
    author_id  bigserial primary key,
    first_name varchar,
    last_name  varchar,
    patronymic varchar,
    birthday   date
);

create table if not exists Books
(
    book_id         bigserial primary key,
    price           float,
    publisher_id    int,
    constraint fk_books_publishers
        foreign key (publisher_id)
            references Publishers (publisher_id),
    title           varchar,
    number_of_pages integer,
    release_year    integer
);

create table if not exists Publishers
(
    publisher_id   bigserial primary key,
    publisher_name varchar
);

create table if not exists author_book
(
    author_id integer,
    constraint fk_authors_books
        foreign key (author_id)
            references authors (author_id),
    book_id   integer,
    constraint fk_books_authors
        foreign key (book_id)
            references books (book_id)
);


-- insert into authors values (0, 'Leo', 'Tolstoy', 'Nikolayevich', '1828-09-09');
-- insert into authors values (1, 'Fyodor', 'Dostoevsky', 'Mikhailovich', '1821-11-11');
-- insert into authors values (2, 'Aleksandr', 'Solzhenitsyn', 'Isayevich', '1918-12-11');