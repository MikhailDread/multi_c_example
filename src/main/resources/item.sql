INSERT INTO item (id, name) VALUES (1, 'Телевизор');
INSERT INTO item (id, name) VALUES (2, 'Смартфон');
INSERT INTO item (id, name) VALUES (3, 'Соковыжималка');
INSERT INTO item (id, name) VALUES (4, 'Наушники');
INSERT INTO item (id, name) VALUES (5, 'Клавиатура');

INSERT INTO users (id, name, last_name, age, count, amount, purchase_date)
VALUES (1, 'Иван', 'Иванов', 25, 4, 1000.00, '2024-01-28');
INSERT INTO users (id, name, last_name, age, count, amount, purchase_date)
VALUES (2, 'Тестовый', 'Юзер', 18, 2, 1024450.00, '2024-01-28');
INSERT INTO users (id, name, last_name, age, count, amount, purchase_date)
VALUES (3, 'Важный', 'Девелопер', 33, 5, 14340.00, '2023-01-28');
INSERT INTO users (id, name, last_name, age, count, amount, purchase_date)
VALUES (4, 'Дмитрий', 'Иванов', 18, 2, 12220.00, '2023-12-18');
INSERT INTO users (id, name, last_name, age, count, amount, purchase_date)
VALUES (5, 'Иван', 'Смирнов', 40, 1, 23314.00, '2023-10-11');

INSERT INTO users_items (user_id, item_id) VALUES
(1, 1),
(1, 2),
(1, 4),
(1, 5);
INSERT INTO users_items (user_id, item_id) VALUES
(2, 4),
(2, 5);
INSERT INTO users_items (user_id, item_id) VALUES
(3, 1),
(3, 3),
(3, 4);
INSERT INTO users_items (user_id, item_id) VALUES
(4, 1),
(4, 5);
INSERT INTO users_items (user_id, item_id) VALUES
(5, 1);