-- admin account (login: admin, password: admin)

insert into users ("username", "user_password", "user_role", "block_flag")
values ('admin', '$2a$12$8EAzaRK1KL7oTtU7fOhg8eVFFUuo/F4EyVTX/j/yCb0GT3YigBrdO', 'ADMIN', false);


-- regular user account (login: user, password: user)

insert into users ("username", "user_password", "user_role", "block_flag")
values ('user', '$2a$12$Qj9H08nwotFoytST5nlEAuAeVauy4/ilK.sdogKUXYN/KpSaN4CFa', 'USER', false);


-- author_book join table


insert into author_book("author_id", "book_id")
values (23, 1);
insert into author_book("author_id", "book_id")
values (22, 2);
insert into author_book("author_id", "book_id")
values (21, 3);
insert into author_book("author_id", "book_id")
values (20, 3);
insert into author_book("author_id", "book_id")
values (19, 3);
insert into author_book("author_id", "book_id")
values (18, 3);
insert into author_book("author_id", "book_id")
values (17, 4);
insert into author_book("author_id", "book_id")
values (46, 5);
insert into author_book("author_id", "book_id")
values (15, 6);
insert into author_book("author_id", "book_id")
values (16, 7);
insert into author_book("author_id", "book_id")
values (15, 8);
insert into author_book("author_id", "book_id")
values (15, 9);
insert into author_book("author_id", "book_id")
values (14, 10);
insert into author_book("author_id", "book_id")
values (13, 10);
insert into author_book("author_id", "book_id")
values (10, 11);
insert into author_book("author_id", "book_id")
values (9, 12);
insert into author_book("author_id", "book_id")
values (47, 13);
insert into author_book("author_id", "book_id")
values (12, 14);
insert into author_book("author_id", "book_id")
values (11, 15);
insert into author_book("author_id", "book_id")
values (8, 16);
insert into author_book("author_id", "book_id")
values (32, 16);
insert into author_book("author_id", "book_id")
values (7, 17);
insert into author_book("author_id", "book_id")
values (6, 18);
insert into author_book("author_id", "book_id")
values (5, 19);
insert into author_book("author_id", "book_id")
values (4, 20);
insert into author_book("author_id", "book_id")
values (1, 21);
insert into author_book("author_id", "book_id")
values (3, 22);
insert into author_book("author_id", "book_id")
values (2, 23);
insert into author_book("author_id", "book_id")
values (1, 24);
insert into author_book("author_id", "book_id")
values (1, 25);
insert into author_book("book_id", "author_id")
values (26, 24);
insert into author_book("book_id", "author_id")
values (27, 24);
insert into author_book("book_id", "author_id")
values (28, 25);
insert into author_book("book_id", "author_id")
values (29, 26);
insert into author_book("book_id", "author_id")
values (30, 26);
insert into author_book("book_id", "author_id")
values (31, 1);
insert into author_book("book_id", "author_id")
values (45, 1);
insert into author_book("book_id", "author_id")
values (32, 27);
insert into author_book("book_id", "author_id")
values (33, 28);
insert into author_book("book_id", "author_id")
values (33, 29);
insert into author_book("book_id", "author_id")
values (34, 28);
insert into author_book("book_id", "author_id")
values (35, 31);
insert into author_book("book_id", "author_id")
values (36, 30);
insert into author_book("book_id", "author_id")
values (37, 34);
insert into author_book("book_id", "author_id")
values (38, 36);
insert into author_book("book_id", "author_id")
values (39, 37);
insert into author_book("book_id", "author_id")
values (40, 38);
insert into author_book("book_id", "author_id")
values (41, 39);
insert into author_book("book_id", "author_id")
values (41, 40);
insert into author_book("book_id", "author_id")
values (42, 41);
insert into author_book("book_id", "author_id")
values (43, 42);
insert into author_book("book_id", "author_id")
values (44, 42);
insert into author_book("book_id", "author_id")
values (45, 42);
insert into author_book("book_id", "author_id")
values (46, 42);
insert into author_book("book_id", "author_id")
values (47, 43);
insert into author_book("book_id", "author_id")
values (48, 44);
insert into author_book("book_id", "author_id")
values (49, 45);
insert into author_book("book_id", "author_id")
values (50, 45);


-- genre_book join table


insert into genre_book("genre_id", "book_id")
values (1, 1);
insert into genre_book("genre_id", "book_id")
values (1, 2);
insert into genre_book("genre_id", "book_id")
values (12, 2);
insert into genre_book("genre_id", "book_id")
values (1, 3);
insert into genre_book("genre_id", "book_id")
values (10, 3);
insert into genre_book("genre_id", "book_id")
values (1, 4);
insert into genre_book("genre_id", "book_id")
values (1, 5);
insert into genre_book("genre_id", "book_id")
values (11, 5);
insert into genre_book("genre_id", "book_id")
values (2, 6);
insert into genre_book("genre_id", "book_id")
values (2, 7);
insert into genre_book("genre_id", "book_id")
values (2, 8);
insert into genre_book("genre_id", "book_id")
values (2, 9);
insert into genre_book("genre_id", "book_id")
values (3, 10);
insert into genre_book("genre_id", "book_id")
values (4, 11);
insert into genre_book("genre_id", "book_id")
values (12, 11);
insert into genre_book("genre_id", "book_id")
values (4, 12);
insert into genre_book("genre_id", "book_id")
values (4, 13);
insert into genre_book("genre_id", "book_id")
values (4, 14);
insert into genre_book("genre_id", "book_id")
values (4, 15);
insert into genre_book("genre_id", "book_id")
values (12, 15);
insert into genre_book("genre_id", "book_id")
values (5, 16);
insert into genre_book("genre_id", "book_id")
values (5, 17);
insert into genre_book("genre_id", "book_id")
values (14, 17);
insert into genre_book("genre_id", "book_id")
values (5, 18);
insert into genre_book("genre_id", "book_id")
values (5, 19);
insert into genre_book("genre_id", "book_id")
values (5, 20);
insert into genre_book("genre_id", "book_id")
values (5, 21);
insert into genre_book("genre_id", "book_id")
values (5, 22);
insert into genre_book("genre_id", "book_id")
values (5, 23);
insert into genre_book("genre_id", "book_id")
values (5, 24);
insert into genre_book("genre_id", "book_id")
values (5, 25);
insert into genre_book("genre_id", "book_id")
values (9, 25);
insert into genre_book("genre_id", "book_id")
values (3, 26);
insert into genre_book("genre_id", "book_id")
values (14, 26);
insert into genre_book("genre_id", "book_id")
values (15, 26);
insert into genre_book("genre_id", "book_id")
values (3, 27);
insert into genre_book("genre_id", "book_id")
values (6, 28);
insert into genre_book("genre_id", "book_id")
values (6, 29);
insert into genre_book("genre_id", "book_id")
values (6, 30);
insert into genre_book("genre_id", "book_id")
values (5, 31);
insert into genre_book("genre_id", "book_id")
values (11, 31);
insert into genre_book("genre_id", "book_id")
values (6, 32);
insert into genre_book("genre_id", "book_id")
values (7, 33);
insert into genre_book("genre_id", "book_id")
values (7, 34);
insert into genre_book("genre_id", "book_id")
values (14, 34);
insert into genre_book("genre_id", "book_id")
values (3, 35);
insert into genre_book("genre_id", "book_id")
values (7, 36);
insert into genre_book("genre_id", "book_id")
values (3, 37);
insert into genre_book("genre_id", "book_id")
values (15, 37);
insert into genre_book("genre_id", "book_id")
values (7, 38);
insert into genre_book("genre_id", "book_id")
values (9, 38);
insert into genre_book("genre_id", "book_id")
values (14, 38);
insert into genre_book("genre_id", "book_id")
values (3, 39);
insert into genre_book("genre_id", "book_id")
values (11, 39);
insert into genre_book("genre_id", "book_id")
values (7, 40);
insert into genre_book("genre_id", "book_id")
values (3, 41);
insert into genre_book("genre_id", "book_id")
values (7, 42);
insert into genre_book("genre_id", "book_id")
values (3, 43);
insert into genre_book("genre_id", "book_id")
values (15, 43);
insert into genre_book("genre_id", "book_id")
values (3, 44);
insert into genre_book("genre_id", "book_id")
values (15, 44);
insert into genre_book("genre_id", "book_id")
values (5, 45);
insert into genre_book("genre_id", "book_id")
values (9, 45);
insert into genre_book("genre_id", "book_id")
values (13, 45);
insert into genre_book("genre_id", "book_id")
values (3, 46);
insert into genre_book("genre_id", "book_id")
values (7, 47);
insert into genre_book("genre_id", "book_id")
values (3, 48);
insert into genre_book("genre_id", "book_id")
values (8, 49);
insert into genre_book("genre_id", "book_id")
values (8, 50);
insert into genre_book("genre_id", "book_id")
values (14, 50);

