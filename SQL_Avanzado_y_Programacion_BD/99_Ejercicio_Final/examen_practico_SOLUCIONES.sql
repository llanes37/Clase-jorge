-- #############################################################
-- SOLUCIONES EXAMEN PRÁCTICO — Repaso BBDD SQL Completo
-- Referencia para el profesor. No distribuir al alumno.
-- Motor: MySQL 8 | BD: repaso_bd
-- Ejecutar 00_reset_completo.sql antes de corregir
-- #############################################################

USE repaso_bd;

-- =============================================================
-- SECCIÓN 1: SELECT y LÓGICA (30 puntos)
-- =============================================================

-- 1. Nombre y nivel_salario (10 puntos)
SELECT nombre,
       salario,
       CASE
           WHEN salario < 1200             THEN 'Bajo'
           WHEN salario BETWEEN 1200 AND 1800 THEN 'Medio'
           ELSE 'Alto'
       END AS nivel_salario
FROM empleados
ORDER BY salario DESC;

-- 2. Clientes con email @example.com (10 puntos)
SELECT nombre, email
FROM clientes
WHERE email LIKE '%@example.com'
ORDER BY nombre;

-- 3. Empleados por departamento con más de 1 empleado (10 puntos)
SELECT d.nombre        AS departamento,
       COUNT(e.id)     AS total_empleados
FROM empleados e
JOIN departamentos d ON e.departamento_id = d.id
GROUP BY e.departamento_id, d.nombre
HAVING COUNT(e.id) > 1
ORDER BY total_empleados DESC;

-- =============================================================
-- SECCIÓN 2: PROCEDIMIENTOS ALMACENADOS (25 puntos)
-- =============================================================

-- 1. ListarEmpleadosRangoSalario (12 puntos)
DROP PROCEDURE IF EXISTS ListarEmpleadosRangoSalario;
DELIMITER $$
CREATE PROCEDURE ListarEmpleadosRangoSalario(
    IN min_sal DECIMAL(10,2),
    IN max_sal DECIMAL(10,2)
)
BEGIN
    IF min_sal > max_sal THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El salario mínimo no puede ser mayor que el máximo';
    END IF;

    SELECT id, nombre, salario
    FROM empleados
    WHERE salario BETWEEN min_sal AND max_sal
    ORDER BY salario DESC;
END $$
DELIMITER ;

-- Prueba:
CALL ListarEmpleadosRangoSalario(1000, 1800);


-- 2. InsertarDepartamento (13 puntos)
DROP PROCEDURE IF EXISTS InsertarDepartamento;
DELIMITER $$
CREATE PROCEDURE InsertarDepartamento(IN nombre_depto VARCHAR(100))
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe
    FROM departamentos
    WHERE LOWER(nombre) = LOWER(nombre_depto);

    IF existe > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ya existe un departamento con ese nombre';
    END IF;

    INSERT INTO departamentos (nombre) VALUES (nombre_depto);
    SELECT LAST_INSERT_ID() AS nuevo_id, nombre_depto AS nombre;
END $$
DELIMITER ;

-- Prueba éxito:
CALL InsertarDepartamento('Marketing');
-- Prueba error (debe fallar):
-- CALL InsertarDepartamento('Ventas');


-- =============================================================
-- SECCIÓN 3: CURSORES (20 puntos)
-- =============================================================

-- 1. ProcesarClientes con cursor (10 puntos)
-- Añadimos columna estado si no existe (necesaria para el ejercicio)
ALTER TABLE clientes ADD COLUMN IF NOT EXISTS estado VARCHAR(20) DEFAULT 'PENDIENTE';

DROP PROCEDURE IF EXISTS ProcesarClientes;
DELIMITER $$
CREATE PROCEDURE ProcesarClientes()
BEGIN
    DECLARE done     INT          DEFAULT 0;
    DECLARE v_id     INT;
    DECLARE v_nombre VARCHAR(100);
    DECLARE v_email  VARCHAR(150);

    DECLARE cur CURSOR FOR
        SELECT id, nombre, email FROM clientes ORDER BY id;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cur;

    bucle: LOOP
        FETCH cur INTO v_id, v_nombre, v_email;
        IF done THEN LEAVE bucle; END IF;

        UPDATE clientes SET estado = 'OK' WHERE id = v_id;
    END LOOP;

    CLOSE cur;

    SELECT id, nombre, email, estado FROM clientes;
END $$
DELIMITER ;

-- Prueba:
CALL ProcesarClientes();


-- 2. Pseudocódigo Java equivalente (10 puntos — respuesta documental)
/*
String sql = "SELECT id, nombre FROM empleados ORDER BY id";
PreparedStatement ps = conn.prepareStatement(sql);
ResultSet rs = ps.executeQuery();

while (rs.next()) {
    int    id     = rs.getInt("id");
    String nombre = rs.getString("nombre");
    System.out.println(id + " - " + nombre);
}

rs.close();
ps.close();
*/


-- =============================================================
-- SECCIÓN 4: TRIGGERS (25 puntos)
-- =============================================================

-- 1. trg_prevent_delete_cliente (12 puntos)
DROP TRIGGER IF EXISTS trg_prevent_delete_cliente;
DELIMITER $$
CREATE TRIGGER trg_prevent_delete_cliente
    BEFORE DELETE ON clientes
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No está permitido borrar clientes directamente';
END $$
DELIMITER ;

-- Prueba (debe fallar):
-- DELETE FROM clientes WHERE id = 1;


-- 2. trg_audit_update_salario (13 puntos)
DROP TRIGGER IF EXISTS trg_audit_update_salario;
DELIMITER $$
CREATE TRIGGER trg_audit_update_salario
    AFTER UPDATE ON empleados
    FOR EACH ROW
BEGIN
    IF OLD.salario <> NEW.salario THEN
        INSERT INTO empleados_log (empleado_id, salario_old, salario_new)
        VALUES (OLD.id, OLD.salario, NEW.salario);
    END IF;
END $$
DELIMITER ;

-- Prueba:
UPDATE empleados SET salario = 2500 WHERE id = 1;
SELECT * FROM empleados_log;
