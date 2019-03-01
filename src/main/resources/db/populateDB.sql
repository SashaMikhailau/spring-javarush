DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;
SELECT setval('meals_id_seq',1);

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories)  VALUES
(100000,TIMESTAMP '2019-02-26 10:00','Завтрак',500),
(100000,TIMESTAMP '2019-02-26 13:00','Обед',400),
(100000,TIMESTAMP '2019-02-26 19:00','Ужин',1100),
(100000,TIMESTAMP '2019-02-27 10:00','Завтрак',500),
(100000,TIMESTAMP '2019-02-27 13:00','Обед',500),
(100000,TIMESTAMP '2019-02-27 19:00','Ужин',1100),
(100001,TIMESTAMP '2019-02-25 10:00','Завтрак',1000),
(100001,TIMESTAMP '2019-02-25 13:00','Обед',1000);
