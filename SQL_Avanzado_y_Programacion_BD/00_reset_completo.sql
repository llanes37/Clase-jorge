-- #############################################################
-- Script: 00_reset_completo.sql
-- Objetivo: borrar y recrear todas las tablas con datos de práctica
-- Motor: MySQL 8  (ejecutar en repaso_bd)
-- Uso: clic derecho en VS Code → Run on active connection
-- #############################################################

USE repaso_bd;

-- *** BORRAR en orden inverso a las FK ***
DROP TABLE IF EXISTS empleados_log;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS empleados;
DROP TABLE IF EXISTS departamentos;

-- *** RECREAR TABLAS ***

CREATE TABLE departamentos (
    id     INT          AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE empleados (
    id              INT            AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(100)   NOT NULL,
    salario         DECIMAL(10,2)  CHECK (salario >= 0),
    fecha_alta      DATE           NOT NULL,
    departamento_id INT,
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id)
);

CREATE TABLE clientes (
    id     INT          AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email  VARCHAR(150) UNIQUE
);

-- Tabla de auditoría usada en el trigger de UT6
CREATE TABLE empleados_log (
    log_id       INT           AUTO_INCREMENT PRIMARY KEY,
    empleado_id  INT,
    salario_old  DECIMAL(10,2),
    salario_new  DECIMAL(10,2),
    fecha_cambio DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- *** INSERTAR DATOS ***

INSERT INTO departamentos (nombre) VALUES
('Ventas'),
('IT'),
('RRHH');

INSERT INTO empleados (nombre, salario, fecha_alta, departamento_id) VALUES
('Ana López',      1200.50, '2023-02-15', 1),
('Luis Martínez',  1800.00, '2022-11-05', 2),
('Carmen Pérez',    950.75, '2021-06-30', 3),
('Pedro Gómez',    2000.00, '2020-01-20', 1),
('Laura Sánchez',  1500.25, '2023-07-01', 2),
('Miguel Torres',   800.00, '2024-01-10', 3),
('Sofía Ramírez',  2200.00, '2019-05-15', 2),
('Raúl Herrera',   1100.00, '2022-03-22', 1);

INSERT INTO clientes (nombre, email) VALUES
('Carlos Fernández', 'carlos.fernandez@example.com'),
('Marta Ruiz',       'marta.ruiz@example.com'),
('Javier Ortiz',     'javier.ortiz@example.com'),
('Lucía Díaz',       'lucia.diaz@example.com'),
('Elena Castro',     'elena.castro@gmail.com'),
('Tomás Ibáñez',     'tomas.ibanez@empresa.es');

-- *** VERIFICACIÓN ***
SELECT 'departamentos=' , COUNT(*) FROM departamentos;
SELECT 'empleados='     , COUNT(*) FROM empleados;
SELECT 'clientes='      , COUNT(*) FROM clientes;
SELECT '✓ Reset completado correctamente' AS estado;
