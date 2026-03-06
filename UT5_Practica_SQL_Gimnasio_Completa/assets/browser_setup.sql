-- * ===============================================================
-- * SIMULADOR WEB - BASE LOCAL SQLITE
-- * ===============================================================

-- *INFO: Este script crea una copia compatible con SQLite para el HTML.
-- *INFO: Se mantiene el mismo modelo logico para que el alumno practique los
-- *INFO: SELECT y JOIN del ejercicio aunque no tenga Oracle abierto.
DROP TABLE IF EXISTS trecibos_g;
DROP TABLE IF EXISTS talu_activ_demand;
DROP TABLE IF EXISTS talu_activ;
DROP TABLE IF EXISTS tactividades_g;
DROP TABLE IF EXISTS tprofesores_g;
DROP TABLE IF EXISTS talumnos_g;

CREATE TABLE talumnos_g (
  idalumno INTEGER PRIMARY KEY,
  nombre TEXT NOT NULL,
  apellidos TEXT NOT NULL,
  telefono TEXT NOT NULL,
  activo TEXT NOT NULL DEFAULT 's'
);

CREATE TABLE tprofesores_g (
  idprofesor INTEGER PRIMARY KEY,
  nombre TEXT NOT NULL,
  apellidos TEXT NOT NULL
);

CREATE TABLE tactividades_g (
  idactividad INTEGER PRIMARY KEY,
  descripcion TEXT NOT NULL,
  idprofesor INTEGER NOT NULL,
  activa TEXT NOT NULL DEFAULT 's'
);

CREATE TABLE talu_activ (
  idalumno INTEGER NOT NULL,
  idactividad INTEGER NOT NULL,
  fecha TEXT NOT NULL,
  PRIMARY KEY (idalumno, idactividad, fecha)
);

CREATE TABLE talu_activ_demand (
  idalumno INTEGER NOT NULL,
  idactividad INTEGER NOT NULL,
  PRIMARY KEY (idalumno, idactividad)
);

CREATE TABLE trecibos_g (
  idalumno INTEGER NOT NULL,
  femision TEXT NOT NULL,
  fpago TEXT NOT NULL,
  importe REAL NOT NULL,
  PRIMARY KEY (idalumno, femision)
);

-- !IMPORTANT: Los datos son los mismos de la practica Oracle para que el HTML
-- !IMPORTANT: sirva como apoyo real y no como una demo desconectada del tema.
INSERT INTO talumnos_g VALUES (1, 'LUISA', 'NUNEZ GIL', '956333444', 's');
INSERT INTO talumnos_g VALUES (2, 'ANGEL', 'PEREZ CARRASCO', '628123456', 's');
INSERT INTO talumnos_g VALUES (3, 'CLARA', 'DE LA TORRE HUERTAS', '956080808', 's');
INSERT INTO talumnos_g VALUES (4, 'DAVID', 'OJEDA LOPEZ', '985678987', 's');
INSERT INTO talumnos_g VALUES (5, 'LORENZO', 'SANCHEZ PEREZ', '986124378', 's');
INSERT INTO talumnos_g VALUES (6, 'RITA', 'LOPEZ VERDUGO', '956122112', 's');
INSERT INTO talumnos_g VALUES (7, 'FRANCISCO JAVIER', 'RUIZ JIMENEZ', '647910234', 's');
INSERT INTO talumnos_g VALUES (8, 'RAFAEL', 'PENA SOLA', '956080041', 's');

INSERT INTO tprofesores_g VALUES (1, 'ROSA', 'FERNANDEZ BENITEZ');
INSERT INTO tprofesores_g VALUES (2, 'LUIS', 'GARCIA RODRIGUEZ');
INSERT INTO tprofesores_g VALUES (3, 'FELIPE', 'GOMEZ JAVALOYES');

INSERT INTO tactividades_g VALUES (1, 'HALTEROFILIA', 1, 's');
INSERT INTO tactividades_g VALUES (2, 'CICLO INDOOR', 2, 's');
INSERT INTO tactividades_g VALUES (3, 'AEROBIC', 1, 's');
INSERT INTO tactividades_g VALUES (4, 'GIMNASIA', 3, 's');
INSERT INTO tactividades_g VALUES (5, 'JUST PUMP', 2, 'n');
INSERT INTO tactividades_g VALUES (6, 'JUDO', 1, 'n');

INSERT INTO talu_activ VALUES (3, 1, '2022-01-01');
INSERT INTO talu_activ VALUES (5, 1, '2022-01-01');
INSERT INTO talu_activ VALUES (6, 1, '2022-01-01');
INSERT INTO talu_activ VALUES (7, 1, '2022-01-01');
INSERT INTO talu_activ VALUES (1, 2, '2022-03-01');
INSERT INTO talu_activ VALUES (3, 2, '2022-03-01');
INSERT INTO talu_activ VALUES (5, 2, '2022-03-01');
INSERT INTO talu_activ VALUES (3, 3, '2022-02-01');
INSERT INTO talu_activ VALUES (5, 3, '2022-02-01');
INSERT INTO talu_activ VALUES (6, 3, '2022-02-01');
INSERT INTO talu_activ VALUES (1, 4, '2022-04-01');
INSERT INTO talu_activ VALUES (3, 4, '2022-04-01');
INSERT INTO talu_activ VALUES (4, 4, '2022-04-01');
INSERT INTO talu_activ VALUES (5, 4, '2022-04-01');
INSERT INTO talu_activ VALUES (6, 4, '2022-04-01');
INSERT INTO talu_activ VALUES (7, 4, '2022-04-01');

INSERT INTO talu_activ_demand VALUES (5, 2);
INSERT INTO talu_activ_demand VALUES (3, 4);
INSERT INTO talu_activ_demand VALUES (6, 4);
INSERT INTO talu_activ_demand VALUES (6, 6);
INSERT INTO talu_activ_demand VALUES (5, 4);
