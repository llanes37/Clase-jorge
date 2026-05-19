-- ####################################################
-- 🔧 REPASO BÁSICO: CREAR TABLAS E INSERTAR DATOS
--    Compatible con MySQL Workbench
-- ####################################################

-- 🚫 Eliminar tablas si existen (orden: hijo → padre)
DROP TABLE IF EXISTS empleados;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS departamentos;

-- 🏢 Crear tabla DEPARTAMENTOS
CREATE TABLE departamentos (
  id INT AUTO_INCREMENT PRIMARY KEY,  -- ID único
  nombre VARCHAR(100) NOT NULL        -- Nombre del departamento
) ENGINE=InnoDB;

-- 🧍 Crear tabla EMPLEADOS
CREATE TABLE empleados (
  id INT AUTO_INCREMENT PRIMARY KEY,       -- ID único
  nombre VARCHAR(100) NOT NULL,            -- Nombre del empleado
  salario DECIMAL(10,2) NOT NULL CHECK (salario >= 0),  -- Salario positivo
  fecha_alta DATE NOT NULL,                -- Fecha de alta
  departamento_id INT,                     -- Relación con departamentos
  FOREIGN KEY (departamento_id)
    REFERENCES departamentos(id)
    ON DELETE SET NULL
) ENGINE=InnoDB;

-- 👥 Crear tabla CLIENTES
CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,     -- ID único
  nombre VARCHAR(100) NOT NULL,          -- Nombre del cliente
  email VARCHAR(150) NOT NULL UNIQUE     -- Email único
) ENGINE=InnoDB;

-- ####################################################
-- ➕ INSERTAR DATOS DE EJEMPLO
-- ####################################################

-- 1️⃣ Insertar DEPARTAMENTOS
START TRANSACTION;
INSERT INTO departamentos (nombre) VALUES
  ('Ventas'),
  ('IT'),
  ('RRHH');
COMMIT;

-- 2️⃣ Insertar EMPLEADOS
START TRANSACTION;
INSERT INTO empleados (nombre, salario, fecha_alta, departamento_id) VALUES
  ('Ana López',     1200.50, '2023-02-15', 1),
  ('Luis Martínez', 1800.00, '2022-11-05', 2),
  ('Carmen Pérez',   950.75, '2021-06-30', 3),
  ('Pedro Gómez',   2000.00, '2020-01-20', 1),
  ('Laura Sánchez', 1500.25, '2023-07-01', 2);
COMMIT;

-- 3️⃣ Insertar CLIENTES
START TRANSACTION;
INSERT INTO clientes (nombre, email) VALUES
  ('Carlos Fernández', 'carlos.fernandez@example.com'),
  ('Marta Ruiz',       'marta.ruiz@example.com'),
  ('Javier Ortiz',     'javier.ortiz@example.com'),
  ('Lucía Díaz',       'lucia.diaz@example.com');
COMMIT;

-- ####################################################
-- ▶️ Verificación de datos
-- ####################################################

SELECT 'DEPARTAMENTOS' AS tabla, * FROM departamentos;
SELECT 'EMPLEADOS'    AS tabla, * FROM empleados;
SELECT 'CLIENTES'     AS tabla, * FROM clientes;
