-- * ===============================================================
-- * PRACTICA COMPLETA SQL - GIMNASIO
-- * BLOQUE 2: CARGA DE DATOS
-- * Motor objetivo: ORACLE SQL
-- * ===============================================================

-- !IMPORTANT: Se usan fechas con TO_DATE para evitar errores por configuracion
-- !IMPORTANT: regional del ordenador. Es un detalle didactico importante
-- !IMPORTANT: porque muchas practicas fallan por el formato de fecha.

-- *INFO: Primero se insertan las tablas "padre". Asi evitamos el error tipico
-- *INFO: de intentar insertar filas hijas con claves foraneas que aun no
-- *INFO: existen en la tabla referenciada.
INSERT INTO talumnos_g (idalumno, apellidos, nombre, telefono, activo)
VALUES (1, 'NUNEZ GIL', 'LUISA', '956333444', 's');
INSERT INTO talumnos_g (idalumno, apellidos, nombre, telefono, activo)
VALUES (2, 'PEREZ CARRASCO', 'ANGEL', '628123456', 's');
INSERT INTO talumnos_g (idalumno, apellidos, nombre, telefono, activo)
VALUES (3, 'DE LA TORRE HUERTAS', 'CLARA', '956080808', 's');
INSERT INTO talumnos_g (idalumno, apellidos, nombre, telefono, activo)
VALUES (4, 'OJEDA LOPEZ', 'DAVID', '985678987', 's');
INSERT INTO talumnos_g (idalumno, apellidos, nombre, telefono, activo)
VALUES (5, 'SANCHEZ PEREZ', 'LORENZO', '986124378', 's');
INSERT INTO talumnos_g (idalumno, apellidos, nombre, telefono, activo)
VALUES (6, 'LOPEZ VERDUGO', 'RITA', '956122112', 's');
INSERT INTO talumnos_g (idalumno, apellidos, nombre, telefono, activo)
VALUES (7, 'RUIZ JIMENEZ', 'FRANCISCO JAVIER', '647910234', 's');
INSERT INTO talumnos_g (idalumno, apellidos, nombre, telefono, activo)
VALUES (8, 'PENA SOLA', 'RAFAEL', '956080041', 's');

-- *INFO: Profesores del gimnasio. Aqui hay pocos datos porque lo importante es
-- *INFO: que el alumno vea bien la relacion 1:N entre profesor y actividad.
INSERT INTO tprofesores_g (idprofesor, apellidos, nombre)
VALUES (1, 'FERNANDEZ BENITEZ', 'ROSA');
INSERT INTO tprofesores_g (idprofesor, apellidos, nombre)
VALUES (2, 'GARCIA RODRIGUEZ', 'LUIS');
INSERT INTO tprofesores_g (idprofesor, apellidos, nombre)
VALUES (3, 'GOMEZ JAVALOYES', 'FELIPE');

-- !IMPORTANT: Actividades activas e inactivas. Esto se deja asi a proposito
-- !IMPORTANT: para poder hacer despues consultas con WHERE, COUNT y filtros
-- !IMPORTANT: por estado sin tener que inventar datos nuevos en clase.
INSERT INTO tactividades_g (idactividad, descripcion, idprofesor, activa)
VALUES (1, 'HALTEROFILIA', 1, 's');
INSERT INTO tactividades_g (idactividad, descripcion, idprofesor, activa)
VALUES (2, 'CICLO INDOOR', 2, 's');
INSERT INTO tactividades_g (idactividad, descripcion, idprofesor, activa)
VALUES (3, 'AEROBIC', 1, 's');
INSERT INTO tactividades_g (idactividad, descripcion, idprofesor, activa)
VALUES (4, 'GIMNASIA', 3, 's');
INSERT INTO tactividades_g (idactividad, descripcion, idprofesor, activa)
VALUES (5, 'JUST PUMP', 2, 'n');
INSERT INTO tactividades_g (idactividad, descripcion, idprofesor, activa)
VALUES (6, 'JUDO', 1, 'n');

-- *INFO: Relaciones alumno-actividad con fecha de inicio. El caso limite aqui
-- *INFO: es repetir la misma combinacion de alumno, actividad y fecha, lo que
-- *INFO: la clave primaria compuesta evita automaticamente.
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (1, 3, TO_DATE('01/01/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (1, 5, TO_DATE('01/01/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (1, 6, TO_DATE('01/01/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (1, 7, TO_DATE('01/01/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (2, 1, TO_DATE('01/03/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (2, 3, TO_DATE('01/03/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (2, 5, TO_DATE('01/03/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (3, 3, TO_DATE('01/02/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (3, 5, TO_DATE('01/02/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (3, 6, TO_DATE('01/02/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (4, 1, TO_DATE('01/04/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (4, 3, TO_DATE('01/04/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (4, 4, TO_DATE('01/04/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (4, 5, TO_DATE('01/04/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (4, 6, TO_DATE('01/04/2022', 'DD/MM/YYYY'));
INSERT INTO talu_activ (idactividad, idalumno, fecha)
VALUES (4, 7, TO_DATE('01/04/2022', 'DD/MM/YYYY'));

-- *INFO: Demandas de actividades. Este bloque se deja tal cual venia porque
-- *INFO: permite comparar demanda frente a matricula real, una idea muy buena
-- *INFO: para que el alumno aprenda LEFT JOIN y diferencias entre tablas.
INSERT INTO talu_activ_demand (idactividad, idalumno)
VALUES (2, 5);
INSERT INTO talu_activ_demand (idactividad, idalumno)
VALUES (4, 3);
INSERT INTO talu_activ_demand (idactividad, idalumno)
VALUES (4, 6);
INSERT INTO talu_activ_demand (idactividad, idalumno)
VALUES (6, 6);
INSERT INTO talu_activ_demand (idactividad, idalumno)
VALUES (4, 5);

-- ?QUESTION: En el material base no aparecen inserciones para TRECIBOS_G.
-- ?QUESTION: Se deja sin datos para no inventar requisitos y para poder usar
-- ?QUESTION: esta tabla como ampliacion optativa del alumno.

COMMIT;

-- TODO: Comprueba rapidamente que las tablas tienen las filas esperadas.
SELECT COUNT(*) AS total_alumnos FROM talumnos_g;
SELECT COUNT(*) AS total_profesores FROM tprofesores_g;
SELECT COUNT(*) AS total_actividades FROM tactividades_g;
SELECT COUNT(*) AS total_relaciones FROM talu_activ;
SELECT COUNT(*) AS total_demandas FROM talu_activ_demand;
