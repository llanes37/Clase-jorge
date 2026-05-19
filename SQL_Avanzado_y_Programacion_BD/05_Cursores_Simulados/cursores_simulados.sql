-- #############################################################
-- 🔁 BLOQUE 05: CURSORES SIMULADOS — TEORÍA, CÓDIGO Y EJERCICIOS
-- #############################################################

/*
📘 TEORÍA DE CURSORES

1. ¿Qué es un cursor?
   - Mecanismo que permite procesar fila a fila los resultados de una consulta.
   - Se usa cuando necesitas lógica por registro (actualizaciones, validaciones…).

2. Simulación en SQLite / SQL puro:
   - No hay cursores nativos: usamos SELECT con ORDER BY, LIMIT y CASE.
   - El procesamiento fila a fila se realiza externamente (Java: while(rs.next()), Python: for row in cursor).

3. Cursores reales en MySQL:
   - Definidos dentro de procedimientos con DECLARE CURSOR, OPEN, FETCH y handlers.
   - Permiten lógica imperativa y bucles controlados (LOOP).

4. ¿Cuándo usar cursores?
   - Cálculos complejos por registro.
   - Transformaciones paso a paso.
   - Integridad de datos en procesos ETL pequeños.
*/
-- 1️⃣ Declaración del cursor y variables de control

-- DECLARE cursor_nombre CURSOR FOR
--   SELECT id, nombre, salario FROM empleados ORDER BY id;
--   ↑ Define un cursor llamado `cursor_nombre` que recorrerá el resultado
--     de la consulta especificada, fila a fila.

-- DECLARE CONTINUE HANDLER FOR NOT FOUND
--   SET done = TRUE;
--   ↑ Crea un “handler” que, cuando FETCH no encuentre más filas,
--     establece la variable `done = TRUE` en lugar de abortar el SP.

-- -------------------------------------------------------------
-- 2️⃣ Abrir el cursor

-- OPEN cursor_nombre;
-- ↑ Inicia el cursor; lo sitúa en la primera fila del conjunto de resultados.
--     A partir de este momento, podemos hacer FETCH para leer datos.

-- -------------------------------------------------------------
-- 3️⃣ Bucle de lectura con REPEAT…UNTIL

-- REPEAT
--   FETCH cursor_nombre INTO var_id, var_nom, var_salario;
--   -- Lee la siguiente fila del cursor:
--   --   - Si hay datos, los almacena en las variables var_id, var_nom, var_salario.
--   --   - Si no hay más filas, el handler marca `done = TRUE`.

--   IF NOT done THEN
--     -- Aquí va la lógica que quieres aplicar a cada fila:
--     -- por ejemplo, sumar, validar o actualizar.
--     UPDATE empleados
--       SET salario = var_salario * 1.05
--     WHERE id = var_id;
--   END IF;

-- UNTIL done END REPEAT;
-- ↑ Repite el FETCH y la lógica interna hasta que `done = TRUE`,
--     es decir, hasta que no queden filas por procesar.

-- -------------------------------------------------------------
-- 4️⃣ Cerrar el cursor

-- CLOSE cursor_nombre;
-- ↑ Finaliza el uso del cursor y libera los recursos asociados.

-- -------------------------------------------------------------
-- 1️⃣ SELECT ORDENADO COMO CURSOR SIMULADO
-- -------------------------------------------------------------
-- * Obtenemos empleados ordenados por salario descendente.
SELECT id, nombre, salario
FROM empleados
ORDER BY salario DESC;

-- 🔽 EN LA APLICACIÓN (MySQL Workbench):
-- 1. Selecciona solo esta consulta (líneas 2 a 4).
-- 2. Pulsa Ctrl + Enter para ejecutarla y ver los resultados en la tabla inferior.
-- 3. Observa cómo aparecen ordenados de mayor a menor salario.
-- 4. Explica que esto simula la lectura "fila a fila" que haría un cursor:
--    - La primera fila sería la que leería primero el cursor.
-- 5. Puedes repetirlo luego con ORDER BY ASC para mostrar lo contrario.


-- -------------------------------------------------------------
-- 2️⃣ TOP-N (LIMIT) COMO FETCH MÚLTIPLE
-- -------------------------------------------------------------
-- * Recuperar solo los 3 empleados mejor pagados.
SELECT id, nombre, salario
FROM empleados
ORDER BY salario DESC
LIMIT 3;
-- ✅ Simula FETCH FIRST 3 ROWS del cursor.

-- -------------------------------------------------------------
-- 3️⃣ PROCESAMIENTO CON CONDICIONES POR FILA
-- -------------------------------------------------------------
-- * Mostrar estado salarial con mensajes personalizados.
SELECT id,
       nombre,
       CASE
         WHEN salario < 1000 THEN CONCAT('❗ ', nombre, ' tiene salario bajo (', salario, ')')
         ELSE CONCAT('✅ ', nombre, ' - OK (', salario, ')')
       END AS estado_salario
FROM empleados
ORDER BY salario ASC;
-- ✅ Simula FETCH + lógica IF/ELSE interna en cada iteración.

-- -------------------------------------------------------------
-- 4️⃣ CURSOR REAL DENTRO DE PROCEDIMIENTO (MySQL) – EXPLICACIÓN LÍNEA A LÍNEA
-- -------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE ProcesarEmpleados()
BEGIN
  -- 1. Declarar variable de control para detectar fin de datos
  DECLARE fin INT DEFAULT FALSE;
  
  -- 2. Declarar variables para almacenar cada columna de la fila leída
  DECLARE emp_id  INT;         -- Guardará el valor de la columna 'id'
  DECLARE emp_nom VARCHAR(100);-- Guardará el valor de la columna 'nombre'
  DECLARE emp_sal DECIMAL(10,2); -- Guardará el valor de la columna 'salario'
  
  -- 3. Definir el cursor que recorrerá todas las filas de empleados, ordenadas por id
  DECLARE cursor_emp CURSOR FOR
    SELECT id, nombre, salario
      FROM empleados
     ORDER BY id;
  
  -- 4. Definir un handler que, cuando FETCH no encuentre más filas, marque fin = TRUE
  DECLARE CONTINUE HANDLER FOR NOT FOUND
    SET fin = TRUE;
  
  -- 5. Abrir el cursor para iniciar la lectura
  OPEN cursor_emp;
  
  -- 6. Iniciar un bucle que se repetirá hasta que fin = TRUE
  bucle: LOOP
    -- 6.1. Leer la siguiente fila del cursor y volcar sus valores en las variables
    FETCH cursor_emp INTO emp_id, emp_nom, emp_sal;
    
    -- 6.2. Si ya no hay más filas (fin = TRUE), salir del bucle
    IF fin THEN
      LEAVE bucle;
    END IF;
    
    -- 6.3. Lógica de procesamiento por cada fila leída:
    --      en este ejemplo, aumentamos el salario un 5% usando el viejo valor
    UPDATE empleados
       SET salario = emp_sal * 1.05
     WHERE id = emp_id;
    
    -- 6.4. Al terminar, el LOOP vuelve a hacer FETCH de la siguiente fila
  END LOOP;
  
  -- 7. Cerrar el cursor y liberar recursos
  CLOSE cursor_emp;
END $$

DELIMITER ;

-- ✅ Para ejecutar: CALL ProcesarEmpleados();

-- -------------------------------------------------------------
-- 5️⃣ VERIFICACIÓN POST-PROCESO
-- -------------------------------------------------------------
-- * Comprobar que todos los salarios se incrementaron correctamente.
SELECT id, nombre, salario
FROM empleados
ORDER BY id;

-- #############################################################
-- 🧪 EJERCICIOS PROPUESTOS
-- #############################################################

-- 1️⃣ Ajuste inverso:
--    • Modifica el procedimiento ProcesarEmpleados para disminuir
--      en vez de aumentar un 5% los salarios de los empleados.
--    • Ejecuta y verifica con SELECT.

-- 2️⃣ Cursor para clientes:
--    • Crea un procedimiento ProcesarClientes que recorra
--      SELECT id, nombre, email FROM clientes.
--    • Dentro del bucle, actualiza un campo ficticio o registra en log.

-- 3️⃣ Simulación externa:
--    • Escribe un pseudocódigo Java:
--        while(rs.next()) {
--          // imprime id y nombre
--        }
--      para el SELECT ORDER BY salario.

-- 4️⃣ Cursor anidado:
--    • Dentro de un procedimiento, usa dos cursores:
--        – Uno sobre departamentos: SELECT id FROM departamentos;
--        – Dentro del bucle, otro SELECT COUNT(*) FROM empleados WHERE departamento_id = depto_id;
--      • Actualiza una tabla estadística con esos conteos.

-- 5️⃣ Tarea avanzada:
--    • Usa un handler para SIGNAL SQLSTATE y envía un error
--      si en algún FETCH el salario es NULO o negativo.
