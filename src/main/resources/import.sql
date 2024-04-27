---- Insertar los datos del producto 1
--INSERT INTO products (id, name, price, description, gender, rating) VALUES(1, 'Jersey', 4.00, 'es un jersey y es una mejor cosa', 'MEN', 4.5);
--INSERT INTO image_url (id, url, product_id) VALUES (1, 'https://kaleoscollection.com/fotos/monturas/82225100/originals/82225100-21.jpg?v=1647857721', 1);
--INSERT INTO image_url (id, url, product_id) VALUES (2, 'https://cdn.aboutstatic.com/file/images/275b2bcc4aad109e646acd85eef62569.jpg?quality=75&height=800&width=600', 1);
--INSERT INTO image_url (id, url, product_id) VALUES (3, 'https://cdn.aboutstatic.com/file/images/71f2e4b0db56c2bdeb508897fb719136.jpg?brightness=0.96&quality=75&trim=1&height=800&width=600', 1);
--INSERT INTO image_url (id, url, product_id) VALUES (4, 'https://cdn.aboutstatic.com/file/images/3a356163ab4e318884b3f0dc664150a2.jpg?quality=75&height=800&width=600', 1);
--INSERT INTO image_url (id, url, product_id) VALUES (5, 'https://cdn.aboutstatic.com/file/images/69b8a228153362abb6b8d07dee34d592.jpg?quality=75&height=800&width=600', 1);
--
--
--
--INSERT INTO products (id, name, price, description, gender, rating) VALUES(2, 'Shirt', 20.00, 'es una camisa y es una mejor cosa', 'MEN', 3.0);
--INSERT INTO image_url (id, url, product_id) VALUES (7, 'https://kaleoscollection.com/fotos/monturas/82225100/originals/82225100-21.jpg?v=1647857721', 2);
--INSERT INTO image_url (id, url, product_id) VALUES (8, 'https://cdn.aboutstatic.com/file/images/275b2bcc4aad109e646acd85eef62569.jpg?quality=75&height=800&width=600', 2);
--INSERT INTO image_url (id, url, product_id) VALUES (9, 'https://cdn.aboutstatic.com/file/images/71f2e4b0db56c2bdeb508897fb719136.jpg?brightness=0.96&quality=75&trim=1&height=800&width=600', 2);
--INSERT INTO image_url (id, url, product_id) VALUES (10, 'https://cdn.aboutstatic.com/file/images/3a356163ab4e318884b3f0dc664150a2.jpg?quality=75&height=800&width=600', 2);
--INSERT INTO image_url (id, url, product_id) VALUES (11, 'https://cdn.aboutstatic.com/file/images/69b8a228153362abb6b8d07dee34d592.jpg?quality=75&height=800&width=600', 2);
--
--
--INSERT INTO products (id, name, price, description, gender, rating) VALUES(3, 'Amirecana', 20.00, 'es una camisa y es una mejor cosa', 'MEN', 3.0);
--INSERT INTO image_url (id, url, product_id) VALUES (12, 'https://m.media-amazon.com/images/I/71tP01VnXsL._AC_SX679_.jpg', 3);
--INSERT INTO image_url (id, url, product_id) VALUES (13, 'https://m.media-amazon.com/images/I/81mw7wIxZ2L._AC_SX679_.jpg', 3);
--INSERT INTO image_url (id, url, product_id) VALUES (14, 'https://m.media-amazon.com/images/I/71tP01VnXsL._AC_SX679_.jpg', 3);
--INSERT INTO image_url (id, url, product_id) VALUES (15, 'https://m.media-amazon.com/images/I/61psfqTFCML._AC_SX679_.jpg', 3);
--INSERT INTO image_url (id, url, product_id) VALUES (16, 'https://m.media-amazon.com/images/I/61ZuIzYRvTL._AC_SX679_.jpg', 3);
--
--
--INSERT INTO products (id, name, price, description, gender, rating) VALUES(4, 'Amirecana', 20.00, 'Material: 75% poliéster, 20% algodón, 5% elastano, forro: 100% poliéster. Una mezcla de tres telas de primera calidad hace que nuestros blazers sean extra resistentes a las arrugas, suaves, cómodos y transpirables. Diseño: cuello de solapa, 1 botón, 2 bolsillos frontales con solapa, 1 bolsillo interior, bolsillo en el pecho para decoración.', 'MEN', 3.0);
--INSERT INTO image_url (id, url, product_id) VALUES (17, 'https://m.media-amazon.com/images/I/71Dq8+GXcvL._AC_SX679_.jpg', 4);
--INSERT INTO image_url (id, url, product_id) VALUES (18, 'https://m.media-amazon.com/images/I/51IsB1c6n-L._AC_SX679_.jpg', 4);
--INSERT INTO image_url (id, url, product_id) VALUES (19, 'https://m.media-amazon.com/images/I/81RmTFFy-iL._AC_SX679_.jpg', 4);
--INSERT INTO image_url (id, url, product_id) VALUES (20, 'https://m.media-amazon.com/images/I/61psfqTFCML._AC_SX679_.jpg', 4);
--INSERT INTO image_url (id, url, product_id) VALUES (21, 'https://m.media-amazon.com/images/I/61ZuIzYRvTL._AC_SX679_.jpg', 4);
--
INSERT INTO colors (id, name) VALUES (1,'Rojo');
INSERT INTO colors (id, name) VALUES (2,'Verde');
INSERT INTO colors (id, name) VALUES (3,'Azul');
INSERT INTO colors (id, name) VALUES (4,'Blanco');
INSERT INTO colors (id, name) VALUES (5,'Negro');
INSERT INTO colors (id, name) VALUES (6,'Rosa');
INSERT INTO colors (id, name) VALUES (7,'Gris');
INSERT INTO colors (id, name) VALUES (8,'Amarillo');
INSERT INTO colors (id, name) VALUES (9,'Marron');
INSERT INTO colors (id, name) VALUES (10,'Azul suave');

--INSERT INTO product_color (product_id, color_id) VALUES (1,1)
--INSERT INTO product_color (product_id, color_id) VALUES (1,2)
--INSERT INTO product_color (product_id, color_id) VALUES (1,3)
--INSERT INTO product_color (product_id, color_id) VALUES (1,4)
--INSERT INTO product_color (product_id, color_id) VALUES (1,5)
--
INSERT INTO clothing_sizes (id, clothing_size) VALUES (1, 'S');
INSERT INTO clothing_sizes (id, clothing_size) VALUES (2, 'M');
INSERT INTO clothing_sizes (id, clothing_size) VALUES (3, 'L');
INSERT INTO clothing_sizes (id, clothing_size) VALUES (4, 'XL');
INSERT INTO clothing_sizes (id, clothing_size) VALUES (5, 'XXL');

--INSERT INTO product_size (product_id, size_id) VALUES (1,1)
--INSERT INTO product_size (product_id, size_id) VALUES (1,2)
--INSERT INTO product_size (product_id, size_id) VALUES (1,3)
--INSERT INTO product_size (product_id, size_id) VALUES (1,4)
--INSERT INTO product_size (product_id, size_id) VALUES (1,5)
--
--
--INSERT INTO product_size (product_id, size_id) VALUES (2,1)
--INSERT INTO product_size (product_id, size_id) VALUES (2,2)
--INSERT INTO product_size (product_id, size_id) VALUES (2,3)
--INSERT INTO product_size (product_id, size_id) VALUES (2,4)
--
--INSERT INTO product_size (product_id, size_id) VALUES (3,1)
--INSERT INTO product_size (product_id, size_id) VALUES (3,2)
--INSERT INTO product_size (product_id, size_id) VALUES (3,3)
--INSERT INTO product_size (product_id, size_id) VALUES (3,4)
--INSERT INTO product_size (product_id, size_id) VALUES (3,5)
--
--INSERT INTO product_size (product_id, size_id) VALUES (4,1)
--INSERT INTO product_size (product_id, size_id) VALUES (4,2)
--INSERT INTO product_size (product_id, size_id) VALUES (4,3)
----
---- Insertar algunos productos generales
--INSERT INTO products (name, brand, rating, price, description, gender, created_at, stock, product_type,type)VALUES ('Zapatos deportivos', 'Nike', 4, 99.99, 'Zapatos deportivos de alta calidad', 'WOMEN', NOW(), 50,'shoes','SNEAKERS');
--
-- Insertar tallas de zapatos
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_35');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_36');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_37');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_38');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_39');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_40');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_41');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_42');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_43');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_44');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_45');
INSERT INTO shoes_sizes (shoe_size) VALUES ('SIZE_46');
--
-- Relacionar tallas de zapatos con productos
--INSERT INTO product_shoes_sizes (product_id, shoe_size_id) VALUES (1,1 );
--INSERT INTO product_shoes_sizes (product_id, shoe_size_id) VALUES  (1, 2);
--INSERT INTO product_shoes_sizes (product_id, shoe_size_id) VALUES  (1, 3);
--INSERT INTO product_shoes_sizes (product_id, shoe_size_id) VALUES (1, 4);
--INSERT INTO product_shoes_sizes (product_id, shoe_size_id) VALUES (1, 5);
--INSERT INTO product_shoes_sizes (product_id, shoe_size_id) VALUES (1, 6);
--INSERT INTO product_shoes_sizes (product_id, shoe_size_id) VALUES (1, 7);
--INSERT INTO product_shoes_sizes (product_id, shoe_size_id) VALUES (1, 8);
--
--
---- Relacionar colores con productos
--INSERT INTO product_color (product_id, color_id)VALUES (1, 1);
--INSERT INTO product_color (product_id, color_id)VALUES (1, 3);
--INSERT INTO product_color (product_id, color_id)VALUES (1, 5);
--
