-- UT4 - SQL (DDL en Oracle) - SOLUCIONES
-- Autor: Joaquin Rodriguez
-- Incluye: creacion, constraints, alter, indices, vistas, sinonimos, reto final.

--------------------------------------------------------------------------------
-- 0) LIMPIEZA (reinicio seguro)
--------------------------------------------------------------------------------
BEGIN
  EXECUTE IMMEDIATE 'DROP VIEW V_EMP_PROY';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
  EXECUTE IMMEDIATE 'DROP VIEW V_EMP_DEPT';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE ASIGNACION CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE PROYECTO CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE EMP CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE DEPT CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

--------------------------------------------------------------------------------
-- 1) DEPT
--------------------------------------------------------------------------------
CREATE TABLE DEPT (
  DEPTNO   NUMBER(2)   CONSTRAINT DEPT_PK PRIMARY KEY,
  DNOMBRE  VARCHAR2(30) CONSTRAINT DEPT_DNOMBRE_NN NOT NULL,
  LOC      VARCHAR2(30) DEFAULT 'MADRID',
  ACTIVO   CHAR(1) DEFAULT 'S' CONSTRAINT DEPT_ACTIVO_CK CHECK (ACTIVO IN ('S','N'))
);

--------------------------------------------------------------------------------
-- 2) EMP
--------------------------------------------------------------------------------
CREATE TABLE EMP (
  EMPNO       NUMBER(6)    CONSTRAINT EMP_PK PRIMARY KEY,
  APELLIDOS   VARCHAR2(50) CONSTRAINT EMP_APELLIDOS_NN NOT NULL,
  SALARIO     NUMBER(8,2) DEFAULT 1200 CONSTRAINT EMP_SALARIO_CK CHECK (SALARIO >= 0),
  FECHA_ALTA  DATE DEFAULT SYSDATE CONSTRAINT EMP_FECHA_ALTA_NN NOT NULL,
  DEPTNO      NUMBER(2),
  CONSTRAINT EMP_DEPT_FK FOREIGN KEY (DEPTNO) REFERENCES DEPT(DEPTNO) ON DELETE SET NULL
);

--------------------------------------------------------------------------------
-- 3) DATOS DE PRUEBA
--------------------------------------------------------------------------------
INSERT INTO DEPT (DEPTNO, DNOMBRE, LOC, ACTIVO) VALUES (10, 'VENTAS', 'SEVILLA', 'S');
INSERT INTO DEPT (DEPTNO, DNOMBRE) VALUES (20, 'IT');

INSERT INTO EMP (EMPNO, APELLIDOS, SALARIO, DEPTNO) VALUES (1001, 'GARCIA', 1500, 10);
INSERT INTO EMP (EMPNO, APELLIDOS, SALARIO, DEPTNO) VALUES (1002, 'PEREZ', 1200, 20);
INSERT INTO EMP (EMPNO, APELLIDOS, DEPTNO) VALUES (1003, 'LOPEZ', 10);

-- Errores controlados (dejar comentados; sirven para demostrar)
-- INSERT INTO EMP (EMPNO, APELLIDOS, SALARIO, DEPTNO) VALUES (2001, 'NEGATIVO', -10, 10); -- CHECK
-- INSERT INTO EMP (EMPNO, APELLIDOS, SALARIO, DEPTNO) VALUES (2002, 'SIN_DEPT', 1000, 99); -- FK
-- INSERT INTO EMP (EMPNO, SALARIO, DEPTNO) VALUES (2003, 1000, 10); -- NOT NULL

--------------------------------------------------------------------------------
-- 4) ALTER TABLE
--------------------------------------------------------------------------------
-- 4.1) ADD
ALTER TABLE EMP ADD (EMAIL VARCHAR2(120));

-- 4.2) UNIQUE (permite NULL)
ALTER TABLE EMP ADD CONSTRAINT EMP_EMAIL_UQ UNIQUE (EMAIL);

-- 4.3) CHECK adicional
ALTER TABLE EMP ADD CONSTRAINT EMP_SALARIO_MAX_CK CHECK (SALARIO <= 6000);

-- 4.4) RENAME COLUMN
ALTER TABLE EMP RENAME COLUMN APELLIDOS TO APELLIDO;

-- 4.5) DROP COLUMN (si quieres eliminar EMAIL)
-- ! Importante: al borrar la columna se elimina su constraint asociada.
ALTER TABLE EMP DROP COLUMN EMAIL;

--------------------------------------------------------------------------------
-- 5) TRUNCATE vs DROP (demostracion)
--------------------------------------------------------------------------------
-- TRUNCATE borra filas pero mantiene estructura; no es "deshacible" con ROLLBACK tipico.
-- TRUNCATE TABLE EMP;
-- ROLLBACK;

--------------------------------------------------------------------------------
-- 6) INDICES
--------------------------------------------------------------------------------
CREATE INDEX EMP_APELLIDO_IX ON EMP (APELLIDO);

--------------------------------------------------------------------------------
-- 7) VISTA EMP+DEPT
--------------------------------------------------------------------------------
CREATE OR REPLACE VIEW V_EMP_DEPT AS
SELECT
  e.EMPNO,
  e.APELLIDO,
  e.SALARIO,
  e.FECHA_ALTA,
  e.DEPTNO,
  d.DNOMBRE,
  d.LOC
FROM EMP e
JOIN DEPT d ON d.DEPTNO = e.DEPTNO;

--------------------------------------------------------------------------------
-- 8) SINONIMOS
--------------------------------------------------------------------------------
-- Ejemplo de sinonimo local
CREATE SYNONYM EMP_ALIAS FOR EMP;
-- SELECT * FROM EMP_ALIAS;
DROP SYNONYM EMP_ALIAS;

--------------------------------------------------------------------------------
-- 9) RETO FINAL: PROYECTO + ASIGNACION (N:M)
--------------------------------------------------------------------------------
CREATE TABLE PROYECTO (
  PROY_ID      NUMBER(6)    CONSTRAINT PROYECTO_PK PRIMARY KEY,
  NOMBRE       VARCHAR2(80) CONSTRAINT PROYECTO_NOMBRE_NN NOT NULL,
  PRESUPUESTO  NUMBER(12,2) DEFAULT 0 CONSTRAINT PROYECTO_PRESUP_CK CHECK (PRESUPUESTO >= 0),
  CONSTRAINT PROYECTO_NOMBRE_UQ UNIQUE (NOMBRE)
);

CREATE TABLE ASIGNACION (
  EMPNO   NUMBER(6) NOT NULL,
  PROY_ID NUMBER(6) NOT NULL,
  HORAS   NUMBER(3) NOT NULL,
  CONSTRAINT ASIGNACION_PK PRIMARY KEY (EMPNO, PROY_ID),
  CONSTRAINT ASIG_HORAS_CK CHECK (HORAS BETWEEN 1 AND 200),
  -- Decision: si borramos un proyecto, se borran sus asignaciones (CASCADE en PROY)
  CONSTRAINT ASIG_PROY_FK FOREIGN KEY (PROY_ID) REFERENCES PROYECTO(PROY_ID) ON DELETE CASCADE,
  -- Si borramos un empleado, tambien se borran sus asignaciones (CASCADE en EMP)
  CONSTRAINT ASIG_EMP_FK FOREIGN KEY (EMPNO) REFERENCES EMP(EMPNO) ON DELETE CASCADE
);

-- Datos de prueba
INSERT INTO PROYECTO (PROY_ID, NOMBRE, PRESUPUESTO) VALUES (501, 'ERP', 25000);
INSERT INTO PROYECTO (PROY_ID, NOMBRE, PRESUPUESTO) VALUES (502, 'WEB', 9000);

INSERT INTO ASIGNACION (EMPNO, PROY_ID, HORAS) VALUES (1001, 501, 80);
INSERT INTO ASIGNACION (EMPNO, PROY_ID, HORAS) VALUES (1002, 501, 40);
INSERT INTO ASIGNACION (EMPNO, PROY_ID, HORAS) VALUES (1003, 502, 60);

-- Vista final
CREATE OR REPLACE VIEW V_EMP_PROY AS
SELECT
  e.EMPNO,
  e.APELLIDO,
  p.PROY_ID,
  p.NOMBRE AS PROYECTO,
  a.HORAS
FROM ASIGNACION a
JOIN EMP e ON e.EMPNO = a.EMPNO
JOIN PROYECTO p ON p.PROY_ID = a.PROY_ID;

COMMIT;
