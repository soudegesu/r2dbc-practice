\c testdb;

DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  id INT PRIMARY KEY,
  name VARCHAR(255)
);

INSERT INTO customer (id, name) values (1, 'Jone');
INSERT INTO customer (id, name) values (2, 'Mike');
INSERT INTO customer (id, name) values (3, 'Ken');
