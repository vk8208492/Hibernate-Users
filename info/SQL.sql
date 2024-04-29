

CREATE DATABASE Hidermate;



CREATE TABLE IF NOT EXISTS contacts
( id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  email VARCHAR(128) NOT NULL,
  PRIMARY KEY (id)
);

- Порівняння HQL та SQL

-- HQL
INSERT INTO Users (name, email) VALUES (:name, :email)
-- SQL
INSERT INTO users (name, email) VALUES (:name, :email)


-- HQL
FROM User
-- SQL
SELECT * FROM users


-- HQL
UPDATE Users SET name = :name WHERE id = :id
-- SQL
UPDATE users SET email = :email WHERE id = :id


-- HQL
DELETE FROM User WHERE id = :id
-- SQL
DELETE FROM users WHERE id = :id;

