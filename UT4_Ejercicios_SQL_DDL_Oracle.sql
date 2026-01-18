-- UT4 - SQL (DDL en Oracle) - EJERCICIOS GUIADOS
-- Autor: Joaquin Rodriguez
-- Modo: enunciado + huecos + pistas (Better Comments)
--
-- Recomendacion: ejecuta por BLOQUES (no todo de golpe).
-- Si algo falla: lee el error, identifica la constraint y corrige.

--------------------------------------------------------------------------------
-- 0) LIMPIEZA (opcional)
--------------------------------------------------------------------------------
-- ! Importante: borra en orden inverso de dependencias (hijos -> padres)
-- TODO Alumno: descomenta y ejecuta SOLO si necesitas reiniciar.
/*
DROP VIEW V_EMP_DEPT;
DROP TABLE EMP CASCADE CONSTRAINTS;
DROP TABLE DEPT CASCADE CONSTRAINTS;
*/

--------------------------------------------------------------------------------
-- 1) CREAR TABLA PADRE: DEPT
--------------------------------------------------------------------------------
-- Objetivo: crear departamentos con PK y reglas basicas
--
-- Requisitos:
-- - DEPTNO: NUMBER(2) PK
-- - DNOMBRE: VARCHAR2(30) NOT NULL
-- - LOC: VARCHAR2(30) DEFAULT 'MADRID'
-- - ACTIVO: CHAR(1) DEFAULT 'S' con CHECK (ACTIVO IN ('S','N'))
--
-- ! Importante: nombra constraints con sufijos: _PK, _NN, _CK

-- TODO Alumno: completa los CONSTRAINT y ejecuta
CREATE TABLE DEPT (
  DEPTNO   NUMBER(2)  /* CONSTRAINT ... PRIMARY KEY */,
  DNOMBRE  VARCHAR2(30) /* CONSTRAINT ... NOT NULL */,
  LOC      VARCHAR2(30) DEFAULT 'MADRID',
  ACTIVO   CHAR(1) DEFAULT 'S' /* CONSTRAINT ... CHECK (...) */
);

--------------------------------------------------------------------------------
-- 2) CREAR TABLA HIJA: EMP
--------------------------------------------------------------------------------
-- Objetivo: empleados con reglas de negocio + relacion con DEPT
--
-- Requisitos:
-- - EMPNO: NUMBER(6) PK
-- - APELLIDOS: VARCHAR2(50) NOT NULL
-- - SALARIO: NUMBER(8,2) DEFAULT 1200 con CHECK (SALARIO >= 0)
-- - FECHA_ALTA: DATE DEFAULT SYSDATE NOT NULL
-- - DEPTNO: NUMBER(2) FK -> DEPT(DEPTNO)
--   - ON DELETE SET NULL (si se borra un depto, el empleado queda sin depto)
--
-- ? Nota: ON DELETE SET NULL exige que la columna FK NO sea NOT NULL.

-- TODO Alumno: completa constraints (PK, NN, CK, FK) y ejecuta
CREATE TABLE EMP (
  EMPNO       NUMBER(6)   /* CONSTRAINT ... PRIMARY KEY */,
  APELLIDOS   VARCHAR2(50) /* CONSTRAINT ... NOT NULL */,
  SALARIO     NUMBER(8,2) DEFAULT 1200 /* CONSTRAINT ... CHECK (...) */,
  FECHA_ALTA  DATE DEFAULT SYSDATE /* CONSTRAINT ... NOT NULL */,
  DEPTNO      NUMBER(2),
  /* CONSTRAINT ... FOREIGN KEY (DEPTNO) REFERENCES DEPT(DEPTNO) ON DELETE SET NULL */
);

--------------------------------------------------------------------------------
-- 3) INSERTS DE PRUEBA (para comprobar constraints)
--------------------------------------------------------------------------------
-- ! Importante: el DDL se "comprueba" con DML.

-- Inserta 2 departamentos
INSERT INTO DEPT (DEPTNO, DNOMBRE, LOC, ACTIVO) VALUES (10, 'VENTAS', 'SEVILLA', 'S');
INSERT INTO DEPT (DEPTNO, DNOMBRE) VALUES (20, 'IT'); -- LOC por defecto

-- Inserta 3 empleados correctos
INSERT INTO EMP (EMPNO, APELLIDOS, SALARIO, DEPTNO) VALUES (1001, 'GARCIA', 1500, 10);
INSERT INTO EMP (EMPNO, APELLIDOS, SALARIO, DEPTNO) VALUES (1002, 'PEREZ', 1200, 20);
INSERT INTO EMP (EMPNO, APELLIDOS, DEPTNO) VALUES (1003, 'LOPEZ', 10); -- SALARIO por defecto

-- TODO Alumno: provoca un error controlado (y explica por que)
-- 1) Inserta un empleado con SALARIO negativo (debe fallar por CHECK)
-- 2) Inserta un empleado con DEPTNO inexistente (debe fallar por FK)
-- 3) Inserta un empleado sin APELLIDOS (debe fallar por NOT NULL)

--------------------------------------------------------------------------------
-- 4) ALTER TABLE: EVOLUCION DEL MODELO
--------------------------------------------------------------------------------
-- Objetivo: practicar ADD/MODIFY/DROP COLUMN + constraints

-- 4.1) ADD: nueva columna EMAIL (sin constraint)
ALTER TABLE EMP ADD (EMAIL VARCHAR2(120));

-- 4.2) UQ: EMAIL debe ser unico (pero permitimos NULL)
-- TODO Alumno: anade un UNIQUE sobre EMAIL (con nombre de constraint)
-- Pista: ALTER TABLE EMP ADD CONSTRAINT ... UNIQUE (EMAIL);

-- 4.3) CHECK: SALARIO maximo (regla ejemplo)
-- TODO Alumno: anade CHECK (SALARIO <= 6000)

-- 4.4) RENAME COLUMN: APELLIDOS -> APELLIDO
-- TODO Alumno: renombra la columna

-- 4.5) DROP COLUMN: elimina EMAIL (solo si ya has practicado el UNIQUE)
-- TODO Alumno: borra la columna EMAIL

--------------------------------------------------------------------------------
-- 5) TRUNCATE vs DROP
--------------------------------------------------------------------------------
-- TODO Alumno: explica la diferencia en 2-3 lineas y prueba:
-- - TRUNCATE TABLE EMP;   (borra filas, mantiene estructura)
-- - ROLLBACK;             (vuelve? por que?)
-- - luego inserta de nuevo 1 fila para comprobar que la tabla existe

--------------------------------------------------------------------------------
-- 6) INDICES
--------------------------------------------------------------------------------
-- Objetivo: crear indice para busquedas por APELLIDO
-- TODO Alumno: crea un indice sobre APELLIDO (o APELLIDOS si aun no has renombrado)
-- Pista: CREATE INDEX ... ON EMP (APELLIDO);

--------------------------------------------------------------------------------
-- 7) VISTAS
--------------------------------------------------------------------------------
-- Objetivo: simplificar joins EMP + DEPT
-- TODO Alumno: crea una vista V_EMP_DEPT con columnas:
-- EMPNO, APELLIDO, SALARIO, FECHA_ALTA, DEPTNO, DNOMBRE, LOC
-- Pista: CREATE OR REPLACE VIEW V_EMP_DEPT AS SELECT ...

--------------------------------------------------------------------------------
-- 8) SINONIMOS (nivel extra / si hay 2 esquemas)
--------------------------------------------------------------------------------
-- Caso A (mismo esquema): un sinonimo no aporta mucho, pero se practica.
-- Caso B (2 esquemas): crea un sinonimo para apuntar a ESQUEMA.EMP
--
-- TODO Alumno:
-- 1) Crea un sinonimo EMP_ALIAS para EMP
-- 2) Haz un SELECT desde el sinonimo
-- 3) Borra el sinonimo

--------------------------------------------------------------------------------
-- 9) RETO FINAL (potente)
--------------------------------------------------------------------------------
-- Disena una tabla PROYECTO y una tabla ASIGNACION (relacion N:M con EMP).
--
-- Requisitos minimos:
-- - PROYECTO(PROY_ID PK, NOMBRE NOT NULL UNIQUE, PRESUPUESTO CHECK >= 0)
-- - ASIGNACION(EMPNO FK, PROY_ID FK, HORAS CHECK (HORAS BETWEEN 1 AND 200))
-- - PK compuesta en ASIGNACION(EMPNO, PROY_ID)
-- - ON DELETE CASCADE en al menos una FK (decide cual y justifica)
--
-- TODO Alumno:
-- 1) Crea las tablas con constraints nombradas.
-- 2) Inserta datos de prueba.
-- 3) Crea una vista V_EMP_PROY con EMP + PROY + HORAS.
