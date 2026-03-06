-- * ===============================================================
-- * PRACTICA COMPLETA SQL - GIMNASIO
-- * BLOQUE 5: VERIFICACIONES
-- * ===============================================================

-- !IMPORTANT: Este archivo sirve para comprobar rapido si el montaje esta
-- !IMPORTANT: correcto. Es muy util despues de ejecutar creacion y carga.
-- !IMPORTANT: Asi el alumno detecta enseguida si fallo algun INSERT.

-- *INFO: Comprobacion de conteos esperados.
SELECT COUNT(*) AS total_alumnos FROM talumnos_g;
SELECT COUNT(*) AS total_profesores FROM tprofesores_g;
SELECT COUNT(*) AS total_actividades FROM tactividades_g;
SELECT COUNT(*) AS total_clases FROM talu_activ;
SELECT COUNT(*) AS total_demandas FROM talu_activ_demand;
SELECT COUNT(*) AS total_recibos FROM trecibos_g;

-- *INFO: Comprobacion de relaciones basicas.
SELECT
  a.descripcion,
  p.nombre || ' ' || p.apellidos AS profesor
FROM tactividades_g a
JOIN tprofesores_g p
  ON p.idprofesor = a.idprofesor
ORDER BY a.idactividad;

-- *INFO: Comprobacion del alumno sin clases para practicar LEFT JOIN.
SELECT
  al.idalumno,
  al.nombre,
  al.apellidos
FROM talumnos_g al
LEFT JOIN talu_activ ta
  ON ta.idalumno = al.idalumno
WHERE ta.idalumno IS NULL;

-- TODO: Resultado esperado orientativo:
-- TODO: 8 alumnos, 3 profesores, 6 actividades, 16 clases, 5 demandas y 0 recibos.
