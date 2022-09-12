/*

    Engine: Postgresql
    Version: 1.0
    Description: Initial database structure and data

*/

-- create database book_store_db;

create table authors
(
    author_id  serial primary key,
    first_name varchar,
    last_name  varchar,
    patronymic varchar,
    birthday   date
);


create table publishers
(
    publisher_id   serial primary key,
    publisher_name varchar
);


create table books
(
    book_id         serial primary key,
    price           double precision,
    publisher_id    int,
    title           varchar,
    number_of_pages int,
    release_year    int,
    book_quantity   int,
    constraint fk_publisher
        foreign key (publisher_id)
            references publishers (publisher_id)
);


create table author_book
(
    author_id int,
    book_id   int,
    constraint fk_author
        foreign key (author_id)
            references authors (author_id),
    constraint fk_book
        foreign key (book_id)
            references books (book_id)
);


create table genres
(
    genre_id   serial primary key,
    genre_name varchar
);


create table genre_book
(
    genre_id int,
    book_id  int,
    constraint fk_genre
        foreign key (genre_id)
            references genres (genre_id),
    constraint fk_book
        foreign key (book_id)
            references books (book_id)
);


create table users
(
    user_id       serial primary key,
    username      varchar not null unique,
    user_password varchar,
    user_role     varchar,
    block_flag    boolean
);


create table orders
(
    order_id     serial primary key,
    user_id      int,
    order_status varchar,
    order_time   timestamp,
    constraint fk_user
        foreign key (user_id)
            references users (user_id)
);


create table order_book
(
    order_id            int,
    book_id             int,
    ordered_book_amount int,
    constraint fk_order
        foreign key (order_id)
            references orders (order_id),
    constraint fk_book
        foreign key (book_id)
            references books (book_id)
);

/*
    Data
*/


-- publishers


insert into publishers("publisher_name")
values ('АСТ');
insert into publishers("publisher_name")
values ('Neoclassic');
insert into publishers("publisher_name")
values ('Fanzon');
insert into publishers("publisher_name")
values ('Эксмо');
insert into publishers("publisher_name")
values ('Лениздат');
insert into publishers("publisher_name")
values ('Азбука');
insert into publishers("publisher_name")
values ('Corpus');
insert into publishers("publisher_name")
values ('Individuum');
insert into publishers("publisher_name")
values ('ARTSCP');
insert into publishers("publisher_name")
values ('ADAPTEC');


-- books

INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('1984', 2070, 3, 2014, 20, 320);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Метаморфозы, или Золотой осел', 1680, 1, 2018, 20, 608);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Античная комедия', 1390, 7, 2020, 20, 608);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Все рушится', 2350, 4, 2020, 20, 224);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Блеск и нищета куртизанок', 1250, 2, 2021, 20, 544);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Испытания Аполлона. Гробница тирана', 2650, 8, 2020, 20, 480);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Хроники хищных городов', 5999, 9, 2019, 20, 192);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Магнус Чейз и боги Асгарда. Девять из Девяти Миров. Люди против магов', 2650, 5, 2019, 20, 192);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Испытания Аполлона. Тёмное пророчество', 4260, 6, 2018, 20, 448);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Реальная магия. Практический курс развития сверхспособностей', 4020, 3, 2017, 20, 240);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Божественная Комедия', 1720, 1, 2016, 20, 800);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('А напоследок я скажу...', 1250, 2, 2020, 20, 320);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Цветы зла', 1390, 4, 2020, 20, 352);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Вид с холма', 4750, 7, 2016, 20, 176);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Энеида', 1350, 7, 2021, 20, 352);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Последняя академия Элизабет Чарльстон', 4150, 9, 2019, 20, 360);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Автостопом по Галактике', 2850, 9, 2017, 20, 240);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Метро 2033: Кочевник', 5250, 10, 2019, 20, 320);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Раны. Земля монстров', 6150, 10, 2021, 20, 640);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Возрождение времени', 7280, 8, 2020, 20, 384);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Хребты безумия', 3850, 5, 2016, 20, 480);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Книги крови. Запретное', 4650, 3, 2020, 20, 736);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Сага о Мерлине. Т. 1. Потерянные годы', 2950, 3, 2019, 20, 416);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Затаившийся страх', 3450, 6, 2016, 20, 512);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Зов Ктулху', 1650, 6, 2020, 20, 416);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Алая королева (#1)', 4100, 1, 2019, 20, 544);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Клетка короля (#3)', 3650, 2, 2020, 20, 640);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Шоу обреченных', 4100, 3, 2017, 20, 470);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Выхода нет', 4100, 4, 2019, 20, 530);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Поле зрения', 3880, 5, 2019, 20, 368);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Некрономикон', 1630, 2, 2020, 20, 416);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Селфи', 1990, 7, 2020, 20, 544);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Что, если это мы', 3520, 8, 2020, 20, 448);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Саймон и программа Homo sapiens', 4400, 9, 2019, 20, 368);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Я сгораю', 999, 10, 2017, 20, 320);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Родина', 6350, 9, 2019, 20, 730);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Мы правим ночью', 999, 8, 2019, 20, 480);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Челюсти', 1990, 7, 2020, 20, 320);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Зигги Стардаст и я', 3250, 6, 2020, 20, 416);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Рыбы', 1990, 5, 2020, 20, 320);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Небо без звезд. Кн. 1.', 2160, 4, 2019, 20, 592);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Последствия', 2550, 3, 2019, 20, 416);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Кошки ходят поперёк', 4260, 2, 2018, 20, 512);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Краткая история Тьмы', 1000, 1, 2018, 20, 233);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Мгла над Инсмутом', 1140, 6, 2016, 20, 352);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Пепел Анны', 2600, 2, 2019, 20, 470);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Советские вожди шутят. От Ленина до Горбачева', 4260, 3, 2018, 20, 470);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Все звёзды и клыки (#1)', 2990, 4, 2020, 20, 480);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Приключения Шерлока Холмса. Возвращение Шерлока Холмса', 1980, 5, 2018, 20, 672);


INSERT INTO books("title", "price", "publisher_id", "release_year", "book_quantity", "number_of_pages")
VALUES ('Приключения Шерлока Холмса.', 3450, 6, 2020, 20, 480);


-- authors


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Говард', 'Лавкрафт', 'Филипс', '1890-08-20');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Томас', 'Баррон', 'Арчибальд', '1952-03-26');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Клайв', 'Баркер', '', '1952-03-26');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Баошу', '', '', '1980-01-03');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Натан', 'Баллингруд', '', '1970-12-31');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Сергей', 'Алексеев', 'Владимирович', '1972-08-04');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Дуглас', 'Адамс', 'Ноэль', '1952-03-11');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Ёрш', 'Ника', '', '1988-03-01');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Изабелла', 'Ахмадулина', 'Ахатовна', '1937-04-10');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Данте', 'Алигьери', 'Дельи', '1265-05-13');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Вергилий', 'Публий', 'Марон', '1000-10-15');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Иосиф', 'Бродский', 'Александрович', '1940-04-24');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Ксандр', 'Вайлд', '', '1979-08-14');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Элли', 'Вайлд', '', '1983-10-01');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Рик', 'Риордан', '', '1964-06-05');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Леветт', 'Джордан', '', '1964-12-08');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Чинуа', 'Ачебе', '', '1930-11-16');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Аристофан', '', '', '0446-10-01');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Менандр', '', '', '0342-11-30');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Тит', 'Плавт', 'Макций', '0254-06-24');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Публий', 'Афр', 'Теренций', '0185-02-18');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Апулей', '', '', '0124-09-23');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Джордж', 'Оруэлл', 'Артур', '1903-06-25');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Виктория', 'Авеярд', '', '1990-07-27');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Павел', 'Агалаков', 'Никонович', '27-07-1901');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Стэнли', 'Адамс', 'Тейлор', '09-05-1922');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Юсси', 'Генри', 'Адлер-Ольсен', '09-05-1922');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Бекки', 'Алберталли', '', '17-11-1982 ');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Адам', 'Сильвера', '', '25-04-1962');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Фернандо', 'Арамбуру', '', '12-04-1959');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Марсия ', 'Андес ', '', '21-08-1990');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Диана', 'Соул', '', '1990-09-20');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Дженнифер', 'Арментроут', 'Линн', '11-07-1980');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Клэр', 'Бартлетт', 'Элиза', '18-10-1956');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Вивиан', 'Барц', '', '22-10-1985');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Питер', 'Бенчли', 'Брэдфорд', '08-05-1940');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Джеймс', 'Брендон', '', '11-07-1989');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Мелисса', 'Бродер', '', '06-05-1972');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Джессика', 'Броуди', '', '17-04-1979');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Джоан', 'Рэнделл', '', '06-02-1978');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Ридиан', 'Брук', '', '12-04-1964');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Эдуард', 'Веркин', 'Николаевич', '17-04-1975');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Михаил', 'Вострышев', 'Иванович', '14-03-1950');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Аделин', 'Грейс', '', '12-07-1991');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Артур', 'Дойл', 'Конан', '22-05-1859');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Оноре де', 'Бальзак', '', '1799-05-20');


INSERT INTO authors ("first_name", "last_name", "patronymic", "birthday")
VALUES ('Шарль', 'Бодлер', 'Пьер', '1821-04-09');


-- genres


insert into genres ("genre_name")
values ('Всемирная классика');
insert into genres ("genre_name")
values ('Детская литература');
insert into genres ("genre_name")
values ('Young adult');
insert into genres ("genre_name")
values ('Поэзия');
insert into genres ("genre_name")
values ('Фантастика');
insert into genres ("genre_name")
values ('Остросюжетная литература');
insert into genres ("genre_name")
values ('Современная мировая проза');
insert into genres ("genre_name")
values ('Детектив');
insert into genres ("genre_name")
values ('Ужасы');
insert into genres ("genre_name")
values ('Драма');
insert into genres ("genre_name")
values ('Художественный вымысел');
insert into genres ("genre_name")
values ('Эпос');
insert into genres ("genre_name")
values ('Новелла');
insert into genres ("genre_name")
values ('Роман');
insert into genres ("genre_name")
values ('Фэнтези');









