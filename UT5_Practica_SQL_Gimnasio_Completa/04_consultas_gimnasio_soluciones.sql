-- * ===============================================================
-- * PRACTICA COMPLETA SQL - GIMNASIO
-- * BLOQUE 4: SOLUCIONES DE CONSULTAS
-- * ===============================================================

-- *INFO: SOLUCION 1
-- *INFO: Orden alfabetico por apellidos y nombre. Se hace asi porque es la
-- *INFO: forma mas clara de ver que ORDER BY puede usar varias columnas.
SELECT
  idalumno,
  apellidos,
  nombre,
  telefono,
  activo
FROM talumnos_g
ORDER BY apellidos, nombre;

-- *INFO: SOLUCION 2
-- *INFO: Filtrado simple por el estado de la actividad. Se usa el valor 'n'
-- *INFO: porque asi se practica un WHERE basico sobre una tabla sencilla.
SELECT
  idactividad,
  descripcion,
  idprofesor,
  activa
FROM tactividades_g
WHERE activa = 'n'
ORDER BY descripcion;

-- *INFO: SOLUCION 3
-- *INFO: JOIN entre actividades y profesores. El alias evita escribir nombres
-- *INFO: largos y reduce errores de ambiguedad cuando se repiten columnas.
SELECT
  a.descripcion AS actividad,
  p.nombre || ' ' || p.apellidos AS profesor
FROM tactividades_g a
JOIN tprofesores_g p
  ON p.idprofesor = a.idprofesor
ORDER BY a.descripcion;

-- !IMPORTANT: SOLUCION 4
-- !IMPORTANT: Se unen tres tablas porque la tabla intermedia solo guarda ids.
-- !IMPORTANT: Si saltas la tabla N:M, no puedes recuperar al alumno correcto.
SELECT
  al.nombre,
  al.apellidos
FROM talumnos_g al
JOIN talu_activ ta
  ON ta.idalumno = al.idalumno
JOIN tactividades_g ac
  ON ac.idactividad = ta.idactividad
WHERE ac.descripcion = 'HALTEROFILIA'
ORDER BY al.apellidos, al.nombre;

-- *INFO: SOLUCION 5
-- *INFO: Consulta combinada con fecha. Se ordena primero por fecha y luego por
-- *INFO: alumno para que el resultado se lea mejor en clase.
SELECT
  al.nombre,
  al.apellidos,
  ac.descripcion AS actividad,
  ta.fecha
FROM talumnos_g al
JOIN talu_activ ta
  ON ta.idalumno = al.idalumno
JOIN tactividades_g ac
  ON ac.idactividad = ta.idactividad
ORDER BY ta.fecha, al.apellidos, al.nombre;

-- *INFO: SOLUCION 6
-- *INFO: COUNT con GROUP BY por profesor. Un error tipico es agrupar solo por
-- *INFO: el id y luego querer mostrar nombre y apellidos sin incluirlos.
SELECT
  p.idprofesor,
  p.nombre,
  p.apellidos,
  COUNT(*) AS total_actividades
FROM tprofesores_g p
JOIN tactividades_g a
  ON a.idprofesor = p.idprofesor
GROUP BY p.idprofesor, p.nombre, p.apellidos
ORDER BY total_actividades DESC, p.apellidos;

-- *INFO: SOLUCION 7
-- *INFO: Numero de alumnos por actividad recibida. COUNT(*) cuenta las filas de
-- *INFO: la tabla de relacion, que en este caso son matriculas reales.
SELECT
  a.descripcion,
  COUNT(*) AS total_alumnos
FROM tactividades_g a
JOIN talu_activ ta
  ON ta.idactividad = a.idactividad
GROUP BY a.descripcion
ORDER BY total_alumnos DESC, a.descripcion;

-- !IMPORTANT: SOLUCION 8
-- !IMPORTANT: Esta consulta mezcla demanda e inactividad, asi que el WHERE se
-- !IMPORTANT: pone sobre la tabla de actividades despues del JOIN.
SELECT
  al.nombre,
  al.apellidos,
  ac.descripcion
FROM talumnos_g al
JOIN talu_activ_demand td
  ON td.idalumno = al.idalumno
JOIN tactividades_g ac
  ON ac.idactividad = td.idactividad
WHERE ac.activa = 'n'
ORDER BY al.apellidos, ac.descripcion;

-- *INFO: SOLUCION 9
-- *INFO: LEFT JOIN para detectar alumnos sin clases. El truco es buscar NULL
-- *INFO: en la tabla derecha, no en la tabla de alumnos.
SELECT
  al.idalumno,
  al.nombre,
  al.apellidos
FROM talumnos_g al
LEFT JOIN talu_activ ta
  ON ta.idalumno = al.idalumno
WHERE ta.idalumno IS NULL
ORDER BY al.apellidos, al.nombre;

-- *INFO: SOLUCION 10
-- *INFO: Comparamos demanda y clase real por alumno y actividad a la vez. Ese
-- *INFO: doble cruce evita unir actividades distintas del mismo alumno.
SELECT
  al.nombre,
  al.apellidos,
  ac.descripcion
FROM talumnos_g al
JOIN talu_activ_demand td
  ON td.idalumno = al.idalumno
JOIN talu_activ ta
  ON ta.idalumno = td.idalumno
 AND ta.idactividad = td.idactividad
JOIN tactividades_g ac
  ON ac.idactividad = td.idactividad
ORDER BY al.apellidos, ac.descripcion;

-- TODO: SOLUCION 11
-- TODO: Profesores con actividades inactivas.
SELECT DISTINCT
  p.idprofesor,
  p.nombre,
  p.apellidos
FROM tprofesores_g p
JOIN tactividades_g a
  ON a.idprofesor = p.idprofesor
WHERE a.activa = 'n'
ORDER BY p.apellidos, p.nombre;

-- TODO: SOLUCION 12
-- TODO: Actividades demandadas con su estado.
SELECT
  al.nombre,
  al.apellidos,
  ac.descripcion,
  ac.activa
FROM talumnos_g al
JOIN talu_activ_demand td
  ON td.idalumno = al.idalumno
JOIN tactividades_g ac
  ON ac.idactividad = td.idactividad
ORDER BY al.apellidos, ac.descripcion;

-- TODO: SOLUCION 13
-- TODO: Alumno o alumnos que mas actividades realizan.
SELECT
  nombre,
  apellidos,
  total_actividades
FROM (
  SELECT
    al.nombre,
    al.apellidos,
    COUNT(*) AS total_actividades,
    DENSE_RANK() OVER (ORDER BY COUNT(*) DESC) AS posicion
  FROM talumnos_g al
  JOIN talu_activ ta
    ON ta.idalumno = al.idalumno
  GROUP BY al.nombre, al.apellidos
)
WHERE posicion = 1
ORDER BY apellidos, nombre;

-- ?QUESTION: SOLUCION 14
-- ?QUESTION: Consulta preparada para el dia en que se inserten recibos.
SELECT
  al.idalumno,
  al.nombre,
  al.apellidos,
  SUM(r.importe) AS total_pagado
FROM talumnos_g al
JOIN trecibos_g r
  ON r.idalumno = al.idalumno
GROUP BY al.idalumno, al.nombre, al.apellidos
ORDER BY total_pagado DESC, al.apellidos;
