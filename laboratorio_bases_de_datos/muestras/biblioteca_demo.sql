PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS prestamos;
DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS autores;
DROP TABLE IF EXISTS categorias;
DROP TABLE IF EXISTS socios;

CREATE TABLE socios (
  id_socio INTEGER PRIMARY KEY,
  nombre TEXT NOT NULL,
  ciudad TEXT,
  email TEXT UNIQUE,
  fecha_alta TEXT NOT NULL
);

CREATE TABLE categorias (
  id_categoria INTEGER PRIMARY KEY,
  nombre TEXT NOT NULL UNIQUE,
  zona TEXT
);

CREATE TABLE autores (
  id_autor INTEGER PRIMARY KEY,
  nombre TEXT NOT NULL,
  pais TEXT,
  anio_nacimiento INTEGER
);

CREATE TABLE libros (
  id_libro INTEGER PRIMARY KEY,
  titulo TEXT NOT NULL,
  id_autor INTEGER NOT NULL,
  id_categoria INTEGER NOT NULL,
  paginas INTEGER,
  anio_publicacion INTEGER,
  disponible INTEGER NOT NULL DEFAULT 1,
  FOREIGN KEY (id_autor) REFERENCES autores(id_autor),
  FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

CREATE TABLE prestamos (
  id_prestamo INTEGER PRIMARY KEY,
  id_socio INTEGER NOT NULL,
  id_libro INTEGER NOT NULL,
  fecha_prestamo TEXT NOT NULL,
  fecha_devolucion TEXT,
  estado TEXT NOT NULL,
  FOREIGN KEY (id_socio) REFERENCES socios(id_socio),
  FOREIGN KEY (id_libro) REFERENCES libros(id_libro)
);

INSERT INTO socios (id_socio, nombre, ciudad, email, fecha_alta) VALUES
  (1, 'Ana Martin', 'Madrid', 'ana@correo.com', '2025-01-10'),
  (2, 'Luis Gomez', 'Sevilla', 'luis@correo.com', '2025-01-12'),
  (3, 'Sara Nunez', 'Valencia', 'sara@correo.com', '2025-02-01'),
  (4, 'Mario Ruiz', 'Bilbao', 'mario@correo.com', '2025-02-15'),
  (5, 'Claudia Perez', 'Madrid', 'claudia@correo.com', '2025-03-03'),
  (6, 'Diego Leon', 'Malaga', 'diego@correo.com', '2025-03-08'),
  (7, 'Elena Castro', 'Sevilla', 'elena@correo.com', '2025-03-20'),
  (8, 'Pablo Serra', 'Valencia', NULL, '2025-03-28');

INSERT INTO categorias (id_categoria, nombre, zona) VALUES
  (1, 'Tecnologia', 'A1'),
  (2, 'Novela', 'B2'),
  (3, 'Historia', 'C1'),
  (4, 'Negocio', 'A3');

INSERT INTO autores (id_autor, nombre, pais, anio_nacimiento) VALUES
  (1, 'Clara Soto', 'Espana', 1985),
  (2, 'Miguel Vera', 'Espana', 1978),
  (3, 'Laura Gil', 'Argentina', 1990),
  (4, 'Pedro Sancho', 'Mexico', 1982),
  (5, 'Eva Lago', 'Espana', 1975),
  (6, 'Ramon Ibarra', 'Chile', 1969);

INSERT INTO libros (id_libro, titulo, id_autor, id_categoria, paginas, anio_publicacion, disponible) VALUES
  (1, 'SQL para todos', 1, 1, 210, 2022, 1),
  (2, 'Bases de datos reales', 2, 1, 328, 2021, 0),
  (3, 'El mapa del tiempo', 3, 2, 415, 2019, 0),
  (4, 'Analisis en una tarde', 4, 4, 180, 2020, 0),
  (5, 'Cocina con datos', 5, 4, 240, 2018, 1),
  (6, 'Historia minima del sur', 6, 3, 290, 2017, 1),
  (7, 'Consultas sin miedo', 1, 1, 156, 2023, 1),
  (8, 'La ciudad sumergida', 3, 2, 502, 2024, 1),
  (9, 'Metricas para equipos', 4, 4, 198, 2022, 1),
  (10, 'Atlas de imperios', 6, 3, 610, 2016, 1);

INSERT INTO prestamos (id_prestamo, id_socio, id_libro, fecha_prestamo, fecha_devolucion, estado) VALUES
  (1, 1, 2, '2025-03-01', '2025-03-16', 'devuelto'),
  (2, 1, 3, '2025-03-20', NULL, 'abierto'),
  (3, 2, 1, '2025-03-04', '2025-03-10', 'devuelto'),
  (4, 3, 4, '2025-03-18', NULL, 'abierto'),
  (5, 4, 5, '2025-03-21', '2025-03-29', 'devuelto'),
  (6, 5, 6, '2025-03-24', '2025-03-31', 'devuelto'),
  (7, 7, 2, '2025-04-01', NULL, 'abierto'),
  (8, 8, 8, '2025-04-02', NULL, 'abierto');
