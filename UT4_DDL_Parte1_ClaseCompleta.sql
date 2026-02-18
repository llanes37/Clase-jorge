-- * ===============================================================
-- * UT4 - SQL DDL PARTE 1 (Clase Completa)
-- * Basado en: "UT 4 - ELSQL -- DDL - Parte 1.pdf"
-- * Motor objetivo: ORACLE SQL
-- * ===============================================================
-- * ENUNCIADO DIDACTICO
-- * Vamos a practicar DDL para crear estructura de base de datos:
-- * 1) CREATE TABLE
-- * 2) Restricciones: PK, FK, NN, UK, CK, DEFAULT
-- * 3) Integridad referencial: ON DELETE CASCADE / SET NULL
-- * 4) Consulta de metadatos: USER_TABLES, USER_CONSTRAINTS, USER_CONS_COLUMNS
-- * 5) Mini tareas tipo examen (personas/provincias + empleados/departamentos)
-- *
-- ! IMPORTANTE
-- ! DDL modifica metadatos y normalmente hace COMMIT implicito.
-- ! Ejecuta en esquema de practica, no en produccion.
-- * ===============================================================

-- * ---------------------------------------------------------------
-- * BLOQUE 0 - LIMPIEZA (opcional y segura para repetir la clase)
-- * ---------------------------------------------------------------
BEGIN EXECUTE IMMEDIATE 'DROP TABLE templeados CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE tdepartamentos CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE tpersonas CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE tprovincias CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/

-- * ---------------------------------------------------------------
-- * BLOQUE 1 - TABLAS BASE (TAREA 2 del PDF)
-- * ---------------------------------------------------------------
-- * Tabla maestra: TPROVINCIAS
-- * Regla de nombres sugerida en el PDF: TAB_COL_TIPO (PK/FK/NN/UK/CK)
CREATE TABLE tprovincias (
    cd_prov   NUMBER(2)
        CONSTRAINT prv_cdpro_pk PRIMARY KEY,
    nom_prov  VARCHAR2(30)
        CONSTRAINT prv_nom_nn NOT NULL
        CONSTRAINT prv_nom_uk UNIQUE
);

-- * Tabla detalle: TPERSONAS
-- * FK a provincias con ON DELETE SET NULL
-- ? Si borramos una provincia, la persona no se borra; su cd_prov pasa a NULL.
CREATE TABLE tpersonas (
    cd_pers    NUMBER(6)
        CONSTRAINT per_cdpk_pk PRIMARY KEY,
    dni        VARCHAR2(9)
        CONSTRAINT per_dni_nn NOT NULL
        CONSTRAINT per_dni_uk UNIQUE,
    nombre     VARCHAR2(60)
        CONSTRAINT per_nom_nn NOT NULL,
    direccion  VARCHAR2(120),
    poblacion  VARCHAR2(60),
    cd_prov    NUMBER(2),
    CONSTRAINT per_prv_fk FOREIGN KEY (cd_prov)
        REFERENCES tprovincias (cd_prov)
        ON DELETE SET NULL
);

-- * ---------------------------------------------------------------
-- * BLOQUE 2 - TABLAS EMPLEADOS/DEPARTAMENTOS (TAREA 3 del PDF)
-- * ---------------------------------------------------------------
CREATE TABLE tdepartamentos (
    cd_dpto   NUMBER(4)
        CONSTRAINT dpt_cddp_pk PRIMARY KEY,
    nom_dpto  VARCHAR2(40)
        CONSTRAINT dpt_nom_nn NOT NULL,
    ciudad    VARCHAR2(20)
        CONSTRAINT dpt_ciu_ck CHECK (ciudad IN ('ALMERIA', 'SORIA', 'VALLADOLID'))
);

CREATE TABLE templeados (
    emp_no      NUMBER(6)
        CONSTRAINT emp_empn_pk PRIMARY KEY,
    dni         VARCHAR2(9)
        CONSTRAINT emp_dni_nn NOT NULL
        CONSTRAINT emp_dni_uk UNIQUE,
    nombre      VARCHAR2(60)
        CONSTRAINT emp_nom_nn NOT NULL,
    telefono    VARCHAR2(15)
        CONSTRAINT emp_tel_uk UNIQUE,
    f_nac       DATE
        CONSTRAINT emp_fna_nn NOT NULL,
    casado      CHAR(1)
        CONSTRAINT emp_cas_ck CHECK (casado IN ('S', 'N')),
    sueldo      NUMBER(9,2) DEFAULT 3000.50
        CONSTRAINT emp_sue_ck CHECK (sueldo >= 0),
    dept_no     NUMBER(4),
    CONSTRAINT emp_dpt_fk FOREIGN KEY (dept_no)
        REFERENCES tdepartamentos (cd_dpto)
);

-- * ---------------------------------------------------------------
-- * BLOQUE 3 - CARGA DE DATOS VALIDOS
-- * ---------------------------------------------------------------
INSERT INTO tprovincias (cd_prov, nom_prov) VALUES (4, 'ALMERIA');
INSERT INTO tprovincias (cd_prov, nom_prov) VALUES (42, 'SORIA');
INSERT INTO tprovincias (cd_prov, nom_prov) VALUES (47, 'VALLADOLID');

INSERT INTO tpersonas (cd_pers, dni, nombre, direccion, poblacion, cd_prov)
VALUES (1001, '12345678A', 'ANA PEREZ', 'C/ MAYOR 1', 'ALMERIA', 4);

INSERT INTO tpersonas (cd_pers, dni, nombre, direccion, poblacion, cd_prov)
VALUES (1002, '23456789B', 'LUIS DIAZ', 'C/ REAL 9', 'SORIA', 42);

INSERT INTO tdepartamentos (cd_dpto, nom_dpto, ciudad)
VALUES (10, 'INFORMATICA', 'ALMERIA');

INSERT INTO tdepartamentos (cd_dpto, nom_dpto, ciudad)
VALUES (20, 'VENTAS', 'SORIA');

INSERT INTO templeados (emp_no, dni, nombre, telefono, f_nac, casado, dept_no)
VALUES (7369, '34567890C', 'MARTA LOPEZ', '600111222', DATE '1998-02-10', 'N', 10);

INSERT INTO templeados (emp_no, dni, nombre, telefono, f_nac, casado, sueldo, dept_no)
VALUES (7499, '45678901D', 'PEDRO GIL', '600222333', DATE '1995-07-25', 'S', 3500.00, 20);

COMMIT;

-- * ---------------------------------------------------------------
-- * BLOQUE 4 - PRUEBAS GUIADAS (deja errores esperados comentados)
-- * ---------------------------------------------------------------
-- TODO Alumno: Descomenta una a una y explica el error que aparece.

-- ! Error PK duplicada (TPROVINCIAS)
-- INSERT INTO tprovincias (cd_prov, nom_prov) VALUES (4, 'ALMERIA 2');

-- ! Error UK duplicada (telefono)
-- INSERT INTO templeados (emp_no, dni, nombre, telefono, f_nac, casado, dept_no)
-- VALUES (7521, '56789012E', 'PILAR RUIZ', '600111222', DATE '1990-01-01', 'N', 10);

-- ! Error CHECK ciudad no permitida
-- INSERT INTO tdepartamentos (cd_dpto, nom_dpto, ciudad)
-- VALUES (30, 'RRHH', 'MADRID');

-- ! Error CHECK casado solo S/N
-- INSERT INTO templeados (emp_no, dni, nombre, telefono, f_nac, casado, dept_no)
-- VALUES (7654, '67890123F', 'RAUL SANZ', '600333444', DATE '1992-03-12', 'X', 10);

-- ! Error NOT NULL en f_nac
-- INSERT INTO templeados (emp_no, dni, nombre, telefono, f_nac, casado, dept_no)
-- VALUES (7788, '78901234G', 'LAURA VERA', '600444555', NULL, 'S', 10);

-- * ---------------------------------------------------------------
-- * BLOQUE 5 - ON DELETE SET NULL (demostracion)
-- * ---------------------------------------------------------------
-- * Antes de borrar provincia 42:
SELECT cd_pers, nombre, cd_prov FROM tpersonas ORDER BY cd_pers;

-- * Borramos SORIA en tabla maestra:
DELETE FROM tprovincias WHERE cd_prov = 42;
COMMIT;

-- * Despues del borrado: persona asociada queda con cd_prov = NULL.
SELECT cd_pers, nombre, cd_prov FROM tpersonas ORDER BY cd_pers;

-- * ---------------------------------------------------------------
-- * BLOQUE 6 - CONSULTA DE METADATOS (vistas del usuario)
-- * ---------------------------------------------------------------
-- * Ver tablas del esquema actual
SELECT table_name FROM user_tables ORDER BY table_name;

-- * Ver constraints de nuestras tablas
SELECT constraint_name, table_name, constraint_type, status
FROM user_constraints
WHERE table_name IN ('TPROVINCIAS', 'TPERSONAS', 'TDEPARTAMENTOS', 'TEMPLEADOS')
ORDER BY table_name, constraint_name;

-- * Ver columnas afectadas por constraints
SELECT constraint_name, table_name, column_name, position
FROM user_cons_columns
WHERE table_name IN ('TPROVINCIAS', 'TPERSONAS', 'TDEPARTAMENTOS', 'TEMPLEADOS')
ORDER BY table_name, constraint_name, position;

-- * ---------------------------------------------------------------
-- * BLOQUE 7 - EXTRA: EJEMPLO ON DELETE CASCADE (mini demo)
-- * ---------------------------------------------------------------
BEGIN EXECUTE IMMEDIATE 'DROP TABLE tlineas_pedido CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE tpedidos CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
/

CREATE TABLE tpedidos (
    id_pedido NUMBER(6) CONSTRAINT ped_id_pk PRIMARY KEY,
    fecha_ped DATE CONSTRAINT ped_fec_nn NOT NULL
);

CREATE TABLE tlineas_pedido (
    id_linea  NUMBER(8) CONSTRAINT lin_id_pk PRIMARY KEY,
    id_pedido NUMBER(6) CONSTRAINT lin_ped_nn NOT NULL,
    concepto  VARCHAR2(80) CONSTRAINT lin_con_nn NOT NULL,
    CONSTRAINT lin_ped_fk FOREIGN KEY (id_pedido)
        REFERENCES tpedidos (id_pedido)
        ON DELETE CASCADE
);

INSERT INTO tpedidos (id_pedido, fecha_ped) VALUES (1, SYSDATE);
INSERT INTO tlineas_pedido (id_linea, id_pedido, concepto) VALUES (10001, 1, 'PORTATIL');
INSERT INTO tlineas_pedido (id_linea, id_pedido, concepto) VALUES (10002, 1, 'RATON');
COMMIT;

-- * Al borrar el pedido maestro, se borran sus lineas automaticamente.
DELETE FROM tpedidos WHERE id_pedido = 1;
COMMIT;

-- * Comprobar que no quedan lineas del pedido 1
SELECT * FROM tlineas_pedido;

-- * ---------------------------------------------------------------
-- * CIERRE / GUIA DE REPASO
-- * ---------------------------------------------------------------
-- * DDL visto hoy:
-- * - CREATE TABLE
-- * - CONSTRAINT con nombre
-- * - PK / FK / NOT NULL / UNIQUE / CHECK / DEFAULT
-- * - ON DELETE SET NULL y ON DELETE CASCADE
-- * - USER_TABLES / USER_CONSTRAINTS / USER_CONS_COLUMNS
-- *
-- TODO Alumno (entrega final):
-- 1) Crear tabla tproyectos con PK y UK.
-- 2) Relacionarla con templeados con FK.
-- 3) Poner CHECK al estado del proyecto ('A','C').
-- 4) Mostrar constraints con una consulta a diccionario.
--
-- ✅ Solucion sugerida (esqueleto):
-- CREATE TABLE tproyectos (...);
-- ALTER TABLE ... ADD CONSTRAINT ...;