PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS lineas_pedido;
DROP TABLE IF EXISTS pedidos;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS categorias_producto;
DROP TABLE IF EXISTS clientes;

CREATE TABLE clientes (
  id_cliente INTEGER PRIMARY KEY,
  nombre TEXT NOT NULL,
  ciudad TEXT,
  email TEXT UNIQUE,
  fecha_registro TEXT NOT NULL
);

CREATE TABLE categorias_producto (
  id_categoria INTEGER PRIMARY KEY,
  nombre TEXT NOT NULL UNIQUE
);

CREATE TABLE productos (
  id_producto INTEGER PRIMARY KEY,
  nombre TEXT NOT NULL,
  id_categoria INTEGER NOT NULL,
  precio REAL NOT NULL,
  stock INTEGER NOT NULL,
  activo INTEGER NOT NULL DEFAULT 1,
  FOREIGN KEY (id_categoria) REFERENCES categorias_producto(id_categoria)
);

CREATE TABLE pedidos (
  id_pedido INTEGER PRIMARY KEY,
  id_cliente INTEGER NOT NULL,
  fecha_pedido TEXT NOT NULL,
  estado TEXT NOT NULL,
  FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

CREATE TABLE lineas_pedido (
  id_linea INTEGER PRIMARY KEY,
  id_pedido INTEGER NOT NULL,
  id_producto INTEGER NOT NULL,
  cantidad INTEGER NOT NULL,
  precio_unitario REAL NOT NULL,
  FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
  FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

INSERT INTO clientes (id_cliente, nombre, ciudad, email, fecha_registro) VALUES
  (1, 'Nuria Lopez', 'Madrid', 'nuria@tienda.com', '2025-01-04'),
  (2, 'Carlos Vega', 'Sevilla', 'carlos@tienda.com', '2025-01-11'),
  (3, 'Lucia Torres', 'Valencia', 'lucia@tienda.com', '2025-01-25'),
  (4, 'Sergio Mena', 'Bilbao', 'sergio@tienda.com', '2025-02-08'),
  (5, 'Alba Reyes', 'Madrid', NULL, '2025-02-18');

INSERT INTO categorias_producto (id_categoria, nombre) VALUES
  (1, 'Perifericos'),
  (2, 'Pantallas'),
  (3, 'Mobiliario'),
  (4, 'Almacenamiento');

INSERT INTO productos (id_producto, nombre, id_categoria, precio, stock, activo) VALUES
  (1, 'Teclado mecanico', 1, 79.90, 12, 1),
  (2, 'Raton inalambrico', 1, 24.50, 30, 1),
  (3, 'Monitor 27', 2, 189.00, 8, 1),
  (4, 'Silla ergonomica', 3, 210.00, 4, 1),
  (5, 'SSD 1TB', 4, 95.00, 15, 1),
  (6, 'Monitor ultrawide', 2, 399.00, 2, 0),
  (7, 'Hub USB-C', 1, 34.90, 20, 1);

INSERT INTO pedidos (id_pedido, id_cliente, fecha_pedido, estado) VALUES
  (1, 1, '2025-03-01', 'entregado'),
  (2, 2, '2025-03-03', 'preparacion'),
  (3, 1, '2025-03-10', 'entregado'),
  (4, 3, '2025-03-11', 'enviado'),
  (5, 5, '2025-03-15', 'cancelado');

INSERT INTO lineas_pedido (id_linea, id_pedido, id_producto, cantidad, precio_unitario) VALUES
  (1, 1, 1, 1, 79.90),
  (2, 1, 2, 2, 24.50),
  (3, 2, 3, 1, 189.00),
  (4, 3, 5, 1, 95.00),
  (5, 3, 7, 2, 34.90),
  (6, 4, 4, 1, 210.00),
  (7, 4, 2, 1, 24.50),
  (8, 5, 6, 1, 399.00);
