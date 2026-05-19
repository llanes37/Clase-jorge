-- #############################################
-- ➕ BLOQUE 02: INSERCIÓN DE DATOS (MySQL Workbench)
-- #############################################

--! Este script puebla las tablas creadas en 01_Tablas.
--! Está preparado para ejecutarse en MySQL Workbench tal cual.
--! Utiliza transacciones para agrupar múltiples INSERTs y asegurar integridad.

-- -----------------------------------------------------
-- 1️⃣ Insertar DEPARTAMENTOS
-- -----------------------------------------------------
-- * Creamos 3 departamentos básicos: Ventas, IT y RRHH.
-- * AUTO_INCREMENT en la tabla departamentos garantiza IDs únicos.
START TRANSACTION;
INSERT INTO departamentos (nombre) VALUES
  ('Ventas'),
  ('IT'),
  ('RRHH');
COMMIT;
/* ✅ Comprueba que hay 3 filas:
   SELECT * FROM departamentos;
*/

-- -----------------------------------------------------
-- 2️⃣ Insertar EMPLEADOS respetando departamento_id
-- -----------------------------------------------------
-- * Cada empleado recibe un departamento existente (1,2 o 3).
-- * Se utiliza el formato 'YYYY-MM-DD' para fechas en MySQL.
START TRANSACTION;
INSERT INTO empleados (nombre, salario, fecha_alta, departamento_id) VALUES
  ('Ana López',      1200.50, '2023-02-15', 1),  -- Ventas
  ('Luis Martínez',  1800.00, '2022-11-05', 2),  -- IT
  ('Carmen Pérez',    950.75, '2021-06-30', 3),  -- RRHH
  ('Pedro Gómez',    2000.00, '2020-01-20', 1),  -- Ventas
  ('Laura Sánchez',  1500.25, '2023-07-01', 2);  -- IT
COMMIT;
/* ✅ Verifica con:
   SELECT id, nombre, salario, fecha_alta, departamento_id
     FROM empleados
     ORDER BY id;
*/

-- -----------------------------------------------------
-- 3️⃣ Insertar CLIENTES con EMAIL único
-- -----------------------------------------------------
-- * La columna email tiene restricción UNIQUE en la tabla clientes.
-- * Si intentas duplicar un email, MySQL lanzará un error y revertirá la transacción.
START TRANSACTION;
INSERT INTO clientes (nombre, email) VALUES
  ('Carlos Fernández', 'carlos.fernandez@example.com'),
  ('Marta Ruiz',       'marta.ruiz@example.com'),
  ('Javier Ortiz',     'javier.ortiz@example.com'),
  ('Lucía Díaz',       'lucia.diaz@example.com');
COMMIT;
/* ✅ Comprueba con:
   SELECT id, nombre, email FROM clientes;
*/

-- -----------------------------------------------------
-- 4️⃣ Prueba de validación: INSERT con email duplicado
-- -----------------------------------------------------
-- ? Descomenta la siguiente línea para ver cómo MySQL aborta la transacción
-- TODO: Ejecuta y observa el error de duplicado de clave única
-- INSERT INTO clientes (nombre, email) VALUES ('Test Duplicado', 'marta.ruiz@example.com');

-- -----------------------------------------------------
-- 5️⃣ Verificación final de datos
-- -----------------------------------------------------
-- * Ejecuta estas consultas para ver el contenido de las tablas:
SELECT * FROM departamentos;
SELECT * FROM empleados;
SELECT * FROM clientes;
