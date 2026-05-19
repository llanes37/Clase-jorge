-- #############################################################
-- 🚨 BLOQUE 06: TRIGGERS — TEORÍA, CÓDIGO Y EJERCICIOS
-- #############################################################

/*
📘 TEORÍA DE TRIGGERS

1. ¿Qué es un trigger?
   - Un trigger (disparador) es un bloque de código SQL que se ejecuta
     automáticamente ante eventos DML (INSERT, UPDATE, DELETE).

2. Tipos de triggers:
   - BEFORE INSERT / UPDATE / DELETE: se ejecutan antes de la operación.
   - AFTER  INSERT / UPDATE / DELETE: se ejecutan después de la operación.
   - FOR EACH ROW: se aplican a cada fila afectada.

3. Usos habituales:
   - Validaciones automáticas (ej. impedir borrados o inserciones inválidas).
   - Auditoría de cambios (registrar historial en tabla de log).
   - Asignación de valores por defecto complejos.
   - Mantenimiento de integridad derivada.

4. Sintaxis MySQL:
   DELIMITER $$
   CREATE TRIGGER nombre_trigger
     {BEFORE|AFTER} {INSERT|UPDATE|DELETE} ON tabla
     FOR EACH ROW
   BEGIN
     -- lógica SQL
   END $$
   DELIMITER ;
*/

-- -------------------------------------------------------------
-- 1️⃣ Trigger BEFORE DELETE en CLIENTES
-- -------------------------------------------------------------
-- Objetivo: impedir borrados directos de clientes
DELIMITER $$
CREATE TRIGGER trg_prevent_delete_cliente
BEFORE DELETE ON clientes
FOR EACH ROW
BEGIN
  SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = '⚠️ No está permitido borrar clientes directamente';
END $$
DELIMITER ;
-- ✅ Prueba con: DELETE FROM clientes WHERE id = 1;  -- Debe fallar

-- -------------------------------------------------------------
-- 2️⃣ Trigger BEFORE INSERT en EMPLEADOS
-- -------------------------------------------------------------
-- Objetivo: asignar departamento por defecto si no se indica
DELIMITER $$
CREATE TRIGGER trg_default_depto_empleado
BEFORE INSERT ON empleados
FOR EACH ROW
BEGIN
  IF NEW.departamento_id IS NULL THEN
    SET NEW.departamento_id = 1;  -- Por defecto 'Ventas'
  END IF;
END $$
DELIMITER ;
-- ✅ Prueba con: INSERT INTO empleados(nombre, salario, fecha_alta) 
--VALUES('Test',1000,'2025-01-01');  -- depto_id quedará =1

-- -------------------------------------------------------------
-- 3️⃣ Trigger BEFORE INSERT en DEPARTAMENTOS
-- -------------------------------------------------------------
-- Objetivo: evitar nombres de departamento duplicados (sensible a mayúsculas)
DELIMITER $$
CREATE TRIGGER trg_unique_depto_name
BEFORE INSERT ON departamentos
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1 FROM departamentos
    WHERE LOWER(nombre) = LOWER(NEW.nombre)
  ) THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = '⚠️ Ya existe un departamento con ese nombre';
  END IF;
END $$
DELIMITER ;
-- ✅ Prueba con: INSERT INTO departamentos(nombre) VALUES('IT');  -- Debe fallar si ya existe

-- -------------------------------------------------------------
-- 4️⃣ Trigger AFTER UPDATE en EMPLEADOS para auditoría
-- -------------------------------------------------------------
-- Objetivo: registrar cambios de salario en tabla de log
CREATE TABLE IF NOT EXISTS empleados_log (
  log_id INT AUTO_INCREMENT PRIMARY KEY,
  emp_id INT,
  old_salario DECIMAL(10,2),
  new_salario DECIMAL(10,2),
  changed_at DATETIME DEFAULT NOW()
) ENGINE=InnoDB;

DELIMITER $$
CREATE TRIGGER trg_audit_update_salario
AFTER UPDATE ON empleados
FOR EACH ROW
BEGIN
  IF OLD.salario <> NEW.salario THEN
    INSERT INTO empleados_log(emp_id, old_salario, new_salario)
    VALUES (OLD.id, OLD.salario, NEW.salario);
  END IF;
END $$
DELIMITER ;
-- ✅ Prueba con: UPDATE empleados SET salario = salario * 1.10 WHERE id = 2;
--    Luego: SELECT * FROM empleados_log;

-- -------------------------------------------------------------
-- 5️⃣ Trigger BEFORE UPDATE en CLIENTES
-- -------------------------------------------------------------
-- Objetivo: asegurar que el email no quede nulo ni vacío
DELIMITER $$
CREATE TRIGGER trg_not_null_email_cliente
BEFORE UPDATE ON clientes
FOR EACH ROW
BEGIN
  IF NEW.email IS NULL OR NEW.email = '' THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = '⚠️ El email no puede quedar vacío';
  END IF;
END $$
DELIMITER ;
-- ✅ Prueba con: UPDATE clientes SET email = '' WHERE id = 3;  -- Debe fallar

-- #############################################################
-- 🧪 EJERCICIOS PROPUESTOS
-- #############################################################

-- 1️⃣ Prevent DELETE con condición:
--    • Crea un trigger trg_prevent_delete_empleado
--      que impida borrar empleados con salario > 2000.
--    • Prueba con DELETE y verifica el error.

-- 2️⃣ Audit INSERT en CLIENTES:
--    • Crea tabla clientes_log(id, cli_id, nombre, inserted_at).
--    • Trigger AFTER INSERT ON clientes que registre cada inserción.

-- 3️⃣ Default fecha en RESERVAS:
--    • Imagina tabla reservas(fecha_reserva DATE).
--    • Crea trigger BEFORE INSERT que si no se pasa fecha, ponga CURDATE().

-- 4️⃣ Auditoría de departamentos:
--    • Crea tabla deptos_log(depto_id, old_name, new_name, changed_at).
--    • Trigger AFTER UPDATE ON departamentos que inserte cambios de nombre.

-- 5️⃣ Ejercicio avanzado:
--    • Dentro de un trigger BEFORE UPDATE en empleados,
--      comprueba si NEW.salario < OLD.salario y, en tal caso,
--      SIGNAL un error 'No se permite reducción de salario'.

-- ▶️ Ejecuta triggers y registra cada prueba con SELECT para validar su funcionamiento.
