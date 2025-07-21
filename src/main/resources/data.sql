-- ---------------------------
-- 1. PLANTAS (producto + planta)
-- ---------------------------

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (1, 'Helecho Boston', 'Planta de interior de fácil cuidado.', 15.5, 100, 'https://dejardines.com/wp-content/uploads/lo-importante-s-es-e-helecho-1080x600.jpg');

INSERT INTO planta (id, clima_recomendado)
VALUES (1, 'Templado');

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (2, 'Cactus Barril', 'Cactus resistente, ideal para clima seco.', 8.0, 200, 'https://jardinpostal.com/wp-content/uploads/2020/06/Echinocactus-grusonii-asiento-de-la-suegra-jardin-postal-comprar-online-cactus-suculentas-cuidados-tama%C3%B1o-500x750.jpg');

INSERT INTO planta (id, clima_recomendado)
VALUES (2, 'Seco');

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (3, 'Orquídea Phalaenopsis', 'Flor exótica de interior.', 25.0, 50, 'https://www.hola.com/horizon/square/7a0eb87ba394-adobestock436265223.jpg?im=Resize=(360),type=downsize');

INSERT INTO planta (id, clima_recomendado)
VALUES (3, 'Húmedo');

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (4, 'Bonsái Ficus', 'Mini árbol para interiores.', 40.0, 30, 'https://floreriamundofloral.com/wp-content/uploads/2022/12/Bonsai-Ficus-Benjamina.jpg');

INSERT INTO planta (id, clima_recomendado)
VALUES (4, 'Templado');

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (5, 'Suculenta Echeveria', 'Suculenta decorativa.', 5.0, 150, 'https://mundomagnoliophyta.com/wp-content/uploads/2020/04/Echeveria-Laui-Suculentas-Vivero-Magnoliophyta-web-930x620.jpg');

INSERT INTO planta (id, clima_recomendado)
VALUES (5, 'Seco');

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (6, 'Poto', 'Planta colgante muy resistente.', 12.0, 120, 'https://www.hola.com/horizon/landscape/84dcb6a2e38c-adobestock370846208.jpg?im=Resize=(960),type=downsize');

INSERT INTO planta (id, clima_recomendado)
VALUES (6, 'Interior');

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (7, 'Ficus Lyrata', 'Planta grande para interior.', 55.0, 25, 'https://www.decorsteals.com/cdn/shop/files/Ficus-Lyrata-600x600-1.jpg?crop=center&height=600&v=1711648475&width=600');

INSERT INTO planta (id, clima_recomendado)
VALUES (7, 'Interior');

-- ---------------------------
-- 2. HERRAMIENTAS (producto + herramienta)
-- ---------------------------

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (8, 'Pala de Mano', 'Pala pequeña para trasplantar.', 10.0, 50, 'https://plasticosdetectables.com/wp-content/uploads/2018/02/Pala-plana-de-acero-inoxidable.jpg');

INSERT INTO herramienta (id, material)
VALUES (8, 'Acero');

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (9, 'Rastrillo', 'Rastrillo para jardinería.', 8.0, 40, 'https://grupocasalima.com/wp-content/uploads/2022/08/venta-de-rastrillo-curvo-14-dientes-casa-lima.jpg');

INSERT INTO herramienta (id, material)
VALUES (9, 'Aluminio');

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (10, 'Tijeras de Podar', 'Tijeras para poda ligera.', 15.0, 30, 'https://promart.vteximg.com.br/arquivos/ids/572469-1000-1000/125451.jpg?v=637401983798800000');

INSERT INTO herramienta (id, material)
VALUES (10, 'Acero Inoxidable');

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (11, 'Guantes de Jardín', 'Guantes resistentes.', 5.0, 70, 'https://promart.vteximg.com.br/arquivos/ids/5384116-1000-1000/1000209807.jpg?v=637854639168930000');

INSERT INTO herramienta (id, material)
VALUES (11, 'Cuero');

INSERT INTO producto (id, nombre, descripcion, precio, stock, imagen_url)
VALUES (12, 'Regadera Metálica', 'Regadera para interior/exterior.', 20.0, 35, 'https://http2.mlstatic.com/D_NQ_NP_2X_808413-MPE73249074570_122023-F-regadera-de-metal-de-1-galon-plantas-de-exterior-rega.webp');

INSERT INTO herramienta (id, material)
VALUES (12, 'Hierro Galvanizado');

INSERT INTO hibernate_sequences (sequence_name, next_val) VALUES ('default', 13)
ON DUPLICATE KEY UPDATE next_val = 13;

