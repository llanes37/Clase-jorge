-- #############################################################
-- ⚙️ BLOQUE 04: PROCEDIMIENTOS SIMULADOS — TEORÍA, CÓDIGO Y EJERCICIOS
-- #############################################################

-- #############################################################
-- 📘 TEORÍA AMPLIADA DE PROCEDIMIENTOS ALMACENADOS Y SIMULADOS
-- #############################################################

-- ! ¿Qué es un Procedimiento Almacenado?
-- * Conjunto de sentencias SQL agrupadas bajo un nombre único.
-- * Se invoca con CALL, simplificando el uso repetido de lógica compleja.
-- * Ejemplo real: GenerarFactura(cliente_id, fecha) en un ERP:

--   DELIMITER $$
--   CREATE PROCEDURE GenerarFactura(
--     IN p_cliente INT,
--     IN p_fecha DATE,
--     OUT p_factura_id INT
--   )
--   BEGIN
--     -- Inserta cabecera en facturas
--     INSERT INTO facturas(cliente_id, fecha) VALUES(p_cliente, p_fecha);
--     -- Captura ID generado
--     SET p_factura_id = LAST_INSERT_ID();
--     -- Aquí iría un cursor para añadir detalles de línea...
--   END $$
--   DELIMITER ;

-- * Ventajas:
--   - ✅ Mantenibilidad: cambias la lógica en un solo sitio.
--   - 🔒 Seguridad: controlas permisos a nivel de SP.
--   - 🚀 Rendimiento: el servidor compila y optimiza el plan de ejecución.
--   - 📊 Monitorización: puedes auditar llamadas y uso.

-- ! Simulación en SQLite / SQL “PLANO”
-- * SQLite y entornos sin SP usan:
--   - SELECT con CASE para condicionales.
--   - Transacciones BEGIN/COMMIT para agrupar pasos.
--   - Lógica de control en la capa de aplicación (Java, Python).

-- * Ejemplo de validación de stock:
--   BEGIN;
--     SELECT CASE
--              WHEN stock < 1 THEN RAISE(ABORT,'Sin stock')
--              ELSE 1
--            END
--     FROM productos WHERE id = 10;
--     UPDATE productos SET stock = stock - 1 WHERE id = 10;
--   COMMIT;

-- ! Cuándo usar Procedimientos
-- * 🌀 Operaciones repetitivas (cálculos, validaciones).
-- * 🔄 Flujos de negocio complejos (IF…ELSE, bucles).
-- * 📱 Interfaces limpias: las apps solo necesitan CALL, no DML directo.
-- * 📈 Integridad: encapsulas reglas críticas (ej. amortizaciones, comisiones).

-- ! Patrones en proyectos reales
-- * **ERP / CRM**: GenerarComisiones(ventas_periodo), CerrarMesContable().
-- * **E-commerce**: ProcesarCarrito(user_id), AplicarDescuentos(promo_code).
-- * **Finanzas**: CalcularInteresesDiarios(cuenta_id), RegistrarMovimiento().
-- * **Reservas**: AsignarHabitacion(reserva_id), ConfirmarDisponibilidad().

-- ! Buenas prácticas
-- * 📛 Nombres claros: usa prefijos `sp_` o `usp_`.  
-- * 📝 Documenta parámetros IN/OUT y valores de retorno.  
-- * ⚠️ Maneja errores con SIGNAL/THROW para mensajes usuarios.  
-- * 🚫 Evita SP demasiado largos; divide en subprocedimientos.  
-- * 🔄 Versionado: guarda el DDL en control de código (Git, SVN).

-- ! Integración con aplicaciones
-- * Java:
--   CallableStatement cs = conn.prepareCall("{CALL GenerarFactura(?,?,?)}");
--   cs.setInt(1, clienteId);
--   cs.setDate(2, java.sql.Date.valueOf(fecha));
--   cs.registerOutParameter(3, Types.INTEGER);
--   cs.execute();
--   int facturaId = cs.getInt(3);

-- * Python (pseudocódigo):
--   cursor.callproc('GenerarFactura', [cliente_id, fecha]);
--   factura_id = cursor.fetchone()[0];

-- #############################################################
-- 🚀 ¡Listo para reemplazar y utilizar en tu material de clase!
-- #############################################################


-- -------------------------------------------------------------
-- 1️⃣ Simulación con CASE (procedimiento implícito)
-- -------------------------------------------------------------
-- ! OBJETIVO:
-- * Mostrar cómo implementar lógica condicional (IF/ELSE) dentro de una consulta SQL
-- * Comprender la evaluación de expresiones CASE por fila, sin necesidad de crear un SP

-- * CONSULTA PRINCIPAL:
SELECT 
  nombre,
  CASE
    WHEN salario < 1000 THEN 'Bajo'                 -- Salarios menores a 1000 → etiqueta 'Bajo'
    WHEN salario BETWEEN 1000 AND 2000 THEN 'Medio' -- Salarios entre 1000 y 2000 → etiqueta 'Medio'
    ELSE 'Alto'                                     -- Salarios superiores a 2000 → etiqueta 'Alto'
  END AS nivel_salario
FROM empleados;
-- ✅ SALIDA ESPERADA: una lista de empleados con una nueva columna 'nivel_salario' indicando su rango

-- 🔽 EJECUCIÓN EN MYSQL WORKBENCH:
-- 1. Abre tu archivo .sql en una pestaña del SQL Editor.
-- 2. Selecciona únicamente el bloque SELECT (desde “SELECT nombre” hasta “FROM empleados;”).
-- 3. Pulsa Ctrl+Enter (Windows/Linux) o ⌘+Enter (macOS) para ejecutar solo esa parte.
-- 4. Observa en el panel “Result Grid” los resultados y verifica la columna 'nivel_salario'.
-- 5. Explica en clase:
--    - Cada fila se evalúa en orden: la primera condición WHEN cumplida detiene la evaluación.
--    - WITHOUT ELSE, las filas que no cumplan quedarían con NULL; por eso incluimos ALWAYS ELSE.

-- 🧪 TAREAS DE PRÁCTICA:
-- • Modifica el primer WHEN a “salario <= 1200” y vuelve a ejecutar para ver el cambio.  
-- • Añade una línea:
--     WHEN salario > 3000 THEN 'Muy Alto'
--   justo antes de ELSE, y comprueba cómo aparece esa etiqueta.  
-- • Añade un ORDER BY nivel_salario DESC al final para agrupar los rangos.  


-- -------------------------------------------------------------
-- 2️⃣ Procedimiento Almacenado: ObtenerNivelSalario
-- -------------------------------------------------------------
-- 📌 OBJETIVO:
--    Encapsular en un SP la lógica de cálculo del nivel salarial,
--    para poder reutilizarla con un simple CALL.

DELIMITER $$

-- 1. Definición del SP sin parámetros
CREATE PROCEDURE ObtenerNivelSalario()
BEGIN
  -- 2. Sentencia que obtiene nombre y nivel según el salario
  SELECT 
    nombre,                                      -- Nombre del empleado
    CASE
      WHEN salario < 1000 THEN 'Bajo'            -- Si salario < 1000 → 'Bajo'
      WHEN salario BETWEEN 1000 AND 2000 THEN 'Medio' -- Si 1000 ≤ salario ≤ 2000 → 'Medio'
      ELSE 'Alto'                                -- Si salario > 2000 → 'Alto'
    END AS nivel_salario                         -- Alias de la columna calculada
  FROM empleados;                                -- Tabla origen
END $$

DELIMITER ;

-- 🔽 EJECUCIÓN EN MYSQL WORKBENCH:
-- 1. Selecciona todo el bloque (DELIMITER $$ … END $$).
-- 2. Pulsa Ctrl+Shift+Enter (Cmd+Shift+Enter en macOS) para crear el SP.
-- 3. Verifica en Navigator → Stored Procedures que aparece 'ObtenerNivelSalario'.
-- 4. Abre una nueva pestaña y escribe: CALL ObtenerNivelSalario();
-- 5. Pulsa Ctrl+Enter para ejecutarlo y observa el resultado en el Result Grid.

-- 🧪 TAREAS DE PRÁCTICA:
-- • Modifica el SP para recibir un parámetro p_max DECIMAL y solo listar
--   empleados con salario ≤ p_max.
-- • Crea un SP análogo llamado ListarClientes que devuelva id y nombre
--   de clientes de la tabla clientes.


-- -------------------------------------------------------------
-- 3️⃣ Procedimiento con Parámetro: BuscarEmpleado
-- -------------------------------------------------------------
DELIMITER $$
CREATE PROCEDURE BuscarEmpleado(IN empNombre VARCHAR(100))
BEGIN
  -- Busca empleados cuyo nombre contenga el parámetro
  SELECT id, nombre, salario
  FROM empleados
  WHERE nombre LIKE CONCAT('%', empNombre, '%');
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL BuscarEmpleado('Ana');

-- -------------------------------------------------------------
-- 4️⃣ Procedimiento de Inserción: AgregarCliente
-- -------------------------------------------------------------
DELIMITER $$
CREATE PROCEDURE AgregarCliente(IN cliNombre VARCHAR(100), IN cliEmail VARCHAR(100))
BEGIN
  -- Inserta un cliente nuevo; email debe ser único
  INSERT INTO clientes (nombre, email)
  VALUES (cliNombre, cliEmail);
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL AgregarCliente('Prueba', 'prueba@example.com');

-- -------------------------------------------------------------
-- 5️⃣ Eliminación Condicional: EliminarEmpleadoPorSalario
-- -------------------------------------------------------------
DELIMITER $$
CREATE PROCEDURE EliminarEmpleadoPorSalario(IN minSalario DECIMAL(10,2))
BEGIN
  -- Elimina empleados con salario menor al parámetro
  DELETE FROM empleados
  WHERE salario < minSalario;
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL EliminarEmpleadoPorSalario(1000.00);

-- -------------------------------------------------------------
-- 6️⃣ Limpieza de Procedimientos (opcional)
-- -------------------------------------------------------------
-- DROP PROCEDURE IF EXISTS ObtenerNivelSalario;
-- DROP PROCEDURE IF EXISTS BuscarEmpleado;
-- DROP PROCEDURE IF EXISTS AgregarCliente;
-- DROP PROCEDURE IF EXISTS EliminarEmpleadoPorSalario;

-- #############################################################
-- 🧪 EJERCICIOS PROPUESTOS
-- #############################################################

-- 1️⃣ Simulación con CASE:
--    • Copia el primer bloque de SELECT y modifica los rangos:
--      - salario < 1000   → 'Junior'
--      - 1000–3000         → 'Senior'
--      - >3000             → 'Lead'
--    • Ejecuta y comprueba los resultados.

-- 2️⃣ SP básico sin parámetros:
--    • Crea y prueba un SP llamado ListarClientes:
--         - Devuelva id y nombre de todos los clientes.
--    • Invoca con: CALL ListarClientes();

-- 3️⃣ SP con parámetro:
--    • Crea un SP FiltrarClientes por dominio de email:
--         - Parámetro: dominio VARCHAR(100)
--         - WHERE email LIKE CONCAT('%', dominio)
--    • Prueba con: CALL FiltrarClientes('example.com');

-- 4️⃣ SP de inserción:
--    • Crea un SP AgregarDepartamento:
--         - Parámetro: nombre VARCHAR(100)
--         - INSERT INTO departamentos(nombre)
--    • Gestiona duplicados: ¿qué ocurre si el nombre ya existe?

-- 5️⃣ SP de borrado condicional:
--    • Crea un SP EliminarClientesAntiguos:
--         - Parámetro: fecha_limite DATE
--         - DELETE FROM clientes WHERE fecha_alta < fecha_limite
--    • Invoca con: CALL EliminarClientesAntiguos('2022-01-01');

-- 6️⃣ Ejercicio avanzado:
--    • Dentro de un procedimiento, usa IF…ELSE para:
--         - Si no hay registros que cumplan la condición, SIGNAL un error.
--         - Si existen, realiza la operación y retorna un mensaje de éxito.

-- #############################################################
-- ✅ FIN BLOQUE 04: PROCEDIMIENTOS SIMULADOS
-- #############################################################
