-- * ===============================================================
-- * PRACTICA COMPLETA SQL - GIMNASIO
-- * BLOQUE 1: CREACION DE TABLAS
-- * Motor objetivo: ORACLE SQL
-- * ===============================================================

-- !IMPORTANT: Este bloque borra primero las tablas si ya existen para que
-- !IMPORTANT: el alumno pueda repetir la practica desde cero sin errores de
-- !IMPORTANT: "tabla ya existente", que es uno de los fallos mas tipicos.
BEGIN EXECUTE IMMEDIATE 'DROP TABLE trecibos_g CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE talu_activ_demand CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE talu_activ CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE tactividades_g CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE tprofesores_g CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE talumnos_g CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/

-- *INFO: Tabla principal de alumnos. Guardamos un indicador de activo porque
-- *INFO: despues sera util para filtros y para explicar el uso de DEFAULT.
-- *INFO: El error que evitamos aqui es olvidar la clave primaria de la tabla.
CREATE TABLE talumnos_g (
  idalumno   NUMBER(3)      NOT NULL,
  nombre     VARCHAR2(30)   NOT NULL,
  apellidos  VARCHAR2(50)   NOT NULL,
  telefono   VARCHAR2(9)    NOT NULL,
  activo     VARCHAR2(1)    DEFAULT 's' NOT NULL,
  CONSTRAINT pk_idalumno_g PRIMARY KEY (idalumno),
  CONSTRAINT ck_alumno_activo_g CHECK (activo IN ('s', 'n'))
);

-- *INFO: Tabla de profesores. Se deja separada para poder practicar JOIN entre
-- *INFO: actividades y profesores, que es una relacion muy facil de entender.
-- *INFO: Un error tipico es intentar guardar el nombre del profesor en texto
-- *INFO: dentro de actividades en lugar de relacionar tablas con una FK.
CREATE TABLE tprofesores_g (
  idprofesor NUMBER(3)     NOT NULL,
  nombre     VARCHAR2(30)  NOT NULL,
  apellidos  VARCHAR2(50)  NOT NULL,
  CONSTRAINT pk_idprofesor_g PRIMARY KEY (idprofesor)
);

-- !IMPORTANT: Cada actividad pertenece a un profesor. Aqui se trabaja bien la
-- !IMPORTANT: integridad referencial porque el idprofesor debe existir antes.
-- !IMPORTANT: Tambien se fuerza un valor valido en la columna activa.
CREATE TABLE tactividades_g (
  idactividad NUMBER(3)     NOT NULL,
  descripcion VARCHAR2(20)  NOT NULL,
  idprofesor  NUMBER(3)     NOT NULL,
  activa      VARCHAR2(1)   DEFAULT 's' NOT NULL,
  CONSTRAINT pk_idactividad_g PRIMARY KEY (idactividad),
  CONSTRAINT fk_actividad_profesor_g FOREIGN KEY (idprofesor)
    REFERENCES tprofesores_g (idprofesor),
  CONSTRAINT ck_actividad_activa_g CHECK (activa IN ('s', 'n'))
);

-- *INFO: Tabla intermedia N:M entre alumnos y actividades realizadas.
-- *INFO: La clave primaria compuesta evita duplicar la misma actividad para el
-- *INFO: mismo alumno en la misma fecha, que es justo el problema que se busca
-- *INFO: prevenir cuando se ensena una tabla de relacion.
CREATE TABLE talu_activ (
  idalumno    NUMBER(3)  NOT NULL,
  idactividad NUMBER(3)  NOT NULL,
  fecha       DATE       NOT NULL,
  CONSTRAINT pk_talu_activ_g PRIMARY KEY (idalumno, idactividad, fecha),
  CONSTRAINT fk_talu_activ_alumno_g FOREIGN KEY (idalumno)
    REFERENCES talumnos_g (idalumno),
  CONSTRAINT fk_talu_activ_actividad_g FOREIGN KEY (idactividad)
    REFERENCES tactividades_g (idactividad)
);

-- *INFO: Tabla de actividades demandadas. Sirve para comparar lo que el alumno
-- *INFO: pide con lo que realmente cursa, muy util para ejercicios de JOIN y
-- *INFO: para detectar casos como "demanda una actividad pero no la recibe".
CREATE TABLE talu_activ_demand (
  idalumno    NUMBER(3)  NOT NULL,
  idactividad NUMBER(3)  NOT NULL,
  CONSTRAINT pk_talu_activ_demand_g PRIMARY KEY (idalumno, idactividad),
  CONSTRAINT fk_talu_demand_alumno_g FOREIGN KEY (idalumno)
    REFERENCES talumnos_g (idalumno),
  CONSTRAINT fk_talu_demand_actividad_g FOREIGN KEY (idactividad)
    REFERENCES tactividades_g (idactividad)
);

-- ?QUESTION: En el material recibido solo aparecia la estructura de recibos y
-- ?QUESTION: no venian inserciones. Se crea la tabla para respetar el modelo,
-- ?QUESTION: pero los datos quedan como ampliacion voluntaria para el alumno.
CREATE TABLE trecibos_g (
  idalumno NUMBER(3)  NOT NULL,
  femision DATE       NOT NULL,
  fpago    DATE       NOT NULL,
  importe  NUMBER(6)  NOT NULL,
  CONSTRAINT pk_trecibos_g PRIMARY KEY (idalumno, femision),
  CONSTRAINT fk_trecibos_alumno_g FOREIGN KEY (idalumno)
    REFERENCES talumnos_g (idalumno)
);

-- TODO: Ejecuta estas consultas al final y comprueba que las 6 tablas existen.
SELECT table_name
FROM user_tables
WHERE table_name IN (
  'TALUMNOS_G',
  'TPROFESORES_G',
  'TACTIVIDADES_G',
  'TALU_ACTIV',
  'TALU_ACTIV_DEMAND',
  'TRECIBOS_G'
)
ORDER BY table_name;
