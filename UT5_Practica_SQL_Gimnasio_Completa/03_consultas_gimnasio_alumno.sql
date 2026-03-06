-- * ===============================================================
-- * PRACTICA COMPLETA SQL - GIMNASIO
-- * BLOQUE 3: CONSULTAS PARA EL ALUMNO
-- * ===============================================================

-- !IMPORTANT: Este archivo esta pensado para que el alumno escriba aqui sus
-- !IMPORTANT: SELECT paso a paso. Se incluyen pistas porque la idea es que
-- !IMPORTANT: aprenda a construir consultas, no solo a copiar soluciones.

-- *INFO: EJERCICIO 1
-- *INFO: Muestra todos los alumnos ordenados alfabeticamente por apellidos.
-- *INFO: Error tipico que evita: olvidar ORDER BY y pensar que la BD "ya sale
-- *INFO: ordenada" por defecto.
SELECT
  idalumno,
  apellidos,
  nombre,
  telefono,
  activo
FROM talumnos_g
ORDER BY apellidos, nombre;

-- *INFO: EJERCICIO 2
-- *INFO: Saca solo las actividades que estan inactivas.
-- *INFO: Pista: usa WHERE sobre la columna activa.


-- *INFO: EJERCICIO 3
-- *INFO: Lista el nombre de cada actividad junto con el profesor que la imparte.
-- *INFO: Pista: hay que unir tactividades_g con tprofesores_g.


-- *INFO: EJERCICIO 4
-- *INFO: Muestra los alumnos que reciben la actividad HALTEROFILIA.
-- *INFO: Pista: hacen falta tres tablas si quieres el nombre del alumno.


-- !IMPORTANT: EJERCICIO 5
-- !IMPORTANT: Saca cada alumno con la actividad que realiza y la fecha.
-- !IMPORTANT: El error tipico aqui es no poner alias y perderse en los JOIN.


-- *INFO: EJERCICIO 6
-- *INFO: Cuenta cuantas actividades tiene asignadas cada profesor.
-- *INFO: Pista: usa COUNT, GROUP BY y un JOIN previo.


-- *INFO: EJERCICIO 7
-- *INFO: Muestra cuantas veces aparece cada actividad en talu_activ.
-- *INFO: Pista: agrupa por descripcion de la actividad.


-- *INFO: EJERCICIO 8
-- *INFO: Saca los alumnos que han demandado alguna actividad inactiva.
-- *INFO: Pista: mira la relacion entre demanda y actividad.


-- *INFO: EJERCICIO 9
-- *INFO: Busca los alumnos que no aparecen en talu_activ.
-- *INFO: Pista: necesitas LEFT JOIN o NOT EXISTS.


-- *INFO: EJERCICIO 10
-- *INFO: Muestra los alumnos que demandan una actividad y ademas ya la reciben.
-- *INFO: Caso limite: si comparas mal las claves, te salen cruces falsos.


-- TODO: EJERCICIO 11
-- TODO: Muestra los profesores que imparten al menos una actividad inactiva.


-- TODO: EJERCICIO 12
-- TODO: Lista las actividades demandadas, indicando si estan activas o no.


-- TODO: EJERCICIO 13
-- TODO: Saca el alumno o alumnos que mas actividades realizan.


-- ?QUESTION: EJERCICIO 14
-- ?QUESTION: Si tuvieramos datos en trecibos_g, que consulta harias para ver
-- ?QUESTION: el total pagado por alumno? Escribela aunque ahora no devuelva filas.
