-- --------------------------------------------------
-- Limpieza previa
-- --------------------------------------------------
DROP TABLE IF EXISTS herramienta;
DROP TABLE IF EXISTS planta;
DROP TABLE IF EXISTS pedido_items;
DROP TABLE IF EXISTS linea_pedido;
DROP TABLE IF EXISTS pedido;
DROP TABLE IF EXISTS producto;

-- --------------------------------------------------
-- Tabla base: producto
-- --------------------------------------------------
CREATE TABLE producto (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    precio DOUBLE,
    stock INT,
    imagen_url VARCHAR(255)
);

-- --------------------------------------------------
-- Tabla hija: planta (1:1 con producto)
-- --------------------------------------------------
CREATE TABLE planta (
    id BIGINT PRIMARY KEY,
    clima_recomendado VARCHAR(255),
    CONSTRAINT FK_planta_producto FOREIGN KEY (id) REFERENCES producto(id)
);

-- --------------------------------------------------
-- Tabla hija: herramienta (1:1 con producto)
-- --------------------------------------------------
CREATE TABLE herramienta (
    id BIGINT PRIMARY KEY,
    material VARCHAR(255),
    CONSTRAINT FK_herramienta_producto FOREIGN KEY (id) REFERENCES producto(id)
);

-- --------------------------------------------------
-- Ejemplo tablas pedidos si las usas
-- --------------------------------------------------
CREATE TABLE pedido (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    total DOUBLE NOT NULL,
    usuario_id BIGINT,
    estado VARCHAR(255)
);

CREATE TABLE linea_pedido (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cantidad INT NOT NULL,
    producto_id BIGINT,
    CONSTRAINT FK_linea_pedido_producto FOREIGN KEY (producto_id) REFERENCES producto(id)
);

CREATE TABLE pedido_items (
    pedido_id BIGINT NOT NULL,
    items_id BIGINT NOT NULL,
    CONSTRAINT PK_pedido_items PRIMARY KEY (pedido_id, items_id),
    CONSTRAINT FK_pedido_items_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    CONSTRAINT FK_pedido_items_linea FOREIGN KEY (items_id) REFERENCES linea_pedido(id)
);

