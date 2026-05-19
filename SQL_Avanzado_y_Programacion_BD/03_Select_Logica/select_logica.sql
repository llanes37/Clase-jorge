-- #############################################
-- 🧩 BLOQUE 03: CONSULTAS Y LÓGICA EN SQL
-- #############################################

-- ! Este script contiene ejemplos de SELECT con lógica: filtros, comparaciones,
-- ! condicionales y agrupaciones. Ejecútalo paso a paso en MySQL Workbench.

-- -----------------------------------------------------
-- 1️⃣ SELECT BÁSICO: Todas las columnas
-- -----------------------------------------------------
-- * Obtenemos todas las filas y columnas de empleados.
SELECT *
FROM empleados;
-- * Útil para verificar la estructura y contenido de la tabla.


-- -----------------------------------------------------
-- 2️⃣ USO DE ALIAS: Renombrar tablas y columnas
-- -----------------------------------------------------
-- * Alias de tabla (e) y alias de columna (sueldo).
SELECT e.id        AS empleado_id,
       e.nombre    AS nombre_empleado,
       e.salario   AS sueldo
FROM empleados AS e;


-- -----------------------------------------------------
-- 3️⃣ FILTRADO CON WHERE
-- -----------------------------------------------------
-- ! Consultar empleados que ganan más de 1500.
SELECT nombre, salario
FROM empleados
WHERE salario > 1500;

-- ? Prueba a cambiar el valor para ver distintos resultados.


-- -----------------------------------------------------
-- 4️⃣ BUSQUEDAS CON LIKE
-- -----------------------------------------------------
-- * Buscar clientes con email de dominio "gmail.com".
SELECT nombre, email
FROM clientes
WHERE email LIKE '%@gmail.com';

-- TODO: Intenta buscar nombres que empiecen por 'A':
-- SELECT nombre FROM clientes WHERE nombre LIKE 'A%';


-- -----------------------------------------------------
-- 5️⃣ FILTRADO CON IN
-- -----------------------------------------------------
-- * Obtener empleados de departamentos 1 (Ventas) o 2 (IT).
SELECT 
  nombre, 
  departamento_id
FROM empleados
WHERE 
  departamento_id = 1                          -- Filtramos solo el departamento 1
  AND nombre LIKE 'A%';                        -- Nombre que comienza por 'A'

-- ⚠️ Si tu collation distingue mayúsculas/minúsculas y quieres incluir 'a' minúscula:
-- AND LOWER(nombre) LIKE 'a%';

-- ? Modifica la lista de IDs para practicar con otros departamentos.


-- -----------------------------------------------------
-- 6️⃣ RANGO DE FECHAS CON BETWEEN
-- -----------------------------------------------------
-- * Empleados dados de alta entre 2021 y 2022.
SELECT nombre, fecha_alta
FROM empleados
WHERE fecha_alta BETWEEN '2021-01-01' AND '2022-12-31';


-- -----------------------------------------------------
-- 7️⃣ CONDICIONES CON CASE (IF / ELSE)
-- -----------------------------------------------------
-- ! Asignar un nivel salarial según el salario.
SELECT nombre,
       CASE
         WHEN salario < 1000 THEN 'Bajo'
         WHEN salario BETWEEN 1000 AND 2000 THEN 'Medio'
         ELSE 'Alto'
       END AS nivel_salarial
FROM empleados;

-- * Ideal para simulaciones de procedimientos con condicionales.


-- -----------------------------------------------------
-- 8️⃣ AGRUPACIÓN Y AGREGADOS (GROUP BY)
-- -----------------------------------------------------
-- * Contar empleados y promedio salarial por departamento.
SELECT departamento_id,
       COUNT(*)    AS total_empleados,
       AVG(salario) AS salario_medio
FROM empleados
GROUP BY departamento_id;

-- TODO: Añade HAVING para filtrar departamentos con más de 2 empleados.


-- -----------------------------------------------------
-- 🔗 Consulta: Unir empleados con nombres de sus departamentos
SELECT 
  e.nombre      AS empleado,         -- Selecciona el nombre de la tabla 'empleados' y lo etiqueta como 'empleado'
  d.nombre      AS departamento      -- Selecciona el nombre de la tabla 'departamentos' y lo etiqueta como 'departamento'
FROM empleados AS e                   -- Fuente principal: tabla 'empleados' con alias 'e'
LEFT JOIN departamentos AS d          -- LEFT JOIN para incluir todos los empleados, incluso sin departamento
  ON e.departamento_id = d.id;        -- Condición de unión: el campo 'departamento_id' de 'empleados' debe coincidir con 'id' de 'departamentos'

-- 🔗 Consulta: Empleados con departamento válido (INNER JOIN)
SELECT 
  e.nombre      AS empleado,         -- Nombre del empleado
  d.nombre      AS departamento      -- Nombre del departamento
FROM empleados AS e                   -- Tabla principal
INNER JOIN departamentos AS d         -- INNER JOIN para excluir empleados sin departamento
  ON e.departamento_id = d.id;        -- Solo filas donde exista coincidencia en ambas tablas


-- #############################################
-- ✅ FIN BLOQUE 03: SELECT Y LÓGICA
-- #############################################
-- #############################################
-- 🧩 BLOQUE 03: EJERCICIOS SELECT Y LÓGICA
-- #############################################

-- 1️⃣ Listar empleados bien pagados
--    Muestra el nombre y salario de todos los empleados que cobran más de 1500 €, 
--    ordenados de mayor a menor salario.

-- 2️⃣ Buscar clientes de Gmail
--    Devuelve nombre y email de los clientes cuyo correo sea de dominio '@gmail.com'.

-- 3️⃣ Empleados de 'Ventas' con 'A'
--    Selecciona el nombre de los empleados que pertenezcan al departamento Ventas (id=1)
--    y cuyo nombre empiece por la letra 'A'.

-- 4️⃣ Etiqueta de antigüedad
--    Muestra nombre, fecha_alta y una etiqueta de antigüedad:
--      - 'Nuevo' si lleva < 1 año
--      - 'Intermedio' si lleva entre 1 y 3 años
--      - 'Veterano' si lleva > 3 años
--    Usa CASE y la función CURDATE(), ordena por fecha_alta ascendente.

-- 5️⃣ Salario medio por departamento
--    Para cada departamento_id muestra:
--      - total_empleados (COUNT)
--      - salario_medio (AVG)
--    Incluye solo aquellos departamentos con al menos 2 empleados (HAVING).

-- 6️⃣ Empleado + Departamento
--    Devuelve la lista de todos los empleados junto con el nombre de su departamento.
--    Incluye también los empleados que no tienen departamento asignado (LEFT JOIN).
