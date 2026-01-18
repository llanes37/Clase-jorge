# UT4 - SQL (DDL en Oracle) - Resumen para estudiar

## 1) SQL en 30 segundos
- DML: datos dentro de tablas -> `SELECT`, `INSERT`, `UPDATE`, `DELETE`
- DDL: estructura/objetos -> `CREATE`, `ALTER`, `DROP`, `TRUNCATE`, `RENAME`
- DCL/TCL: permisos + transacciones -> `GRANT`, `REVOKE`, `COMMIT`, `ROLLBACK`

Idea clave: DDL define las reglas; DML demuestra si las reglas funcionan (inserts que pasan/fallan).

## 2) Conceptos clave (Oracle)
- Usuario: cuenta con permisos.
- Esquema: conjunto de objetos de un usuario (tablas, vistas, indices...).
- Objetos: TABLE, VIEW, INDEX, SYNONYM.

## 3) CREATE TABLE (plantillas)

### 3.1) Plantilla minima
```sql
CREATE TABLE TABLA (
  COL1 TIPO,
  COL2 TIPO
);
```

### 3.2) Tabla con constraints tipicas
```sql
CREATE TABLE DEPT (
  DEPTNO  NUMBER(2)   CONSTRAINT DEPT_PK PRIMARY KEY,
  DNOMBRE VARCHAR2(30) CONSTRAINT DEPT_DNOMBRE_NN NOT NULL,
  LOC     VARCHAR2(30) DEFAULT 'MADRID',
  ACTIVO  CHAR(1) DEFAULT 'S' CONSTRAINT DEPT_ACTIVO_CK CHECK (ACTIVO IN ('S','N'))
);
```

## 4) Constraints (lo mas importante)

### Tipos y que hacen
- NOT NULL (NN): obliga a valor (no permite NULL).
- UNIQUE (UQ): no repetidos (ojo: suele permitir NULL).
- PRIMARY KEY (PK): identifica fila (equivale a UNIQUE + NOT NULL). Solo 1 PK por tabla.
- FOREIGN KEY (FK): relaciona tablas. Requiere que la columna referenciada sea PK o UQ.
- CHECK (CK): valida reglas (`IN`, rangos, etc.).
- DEFAULT: valor por defecto si no se indica en el INSERT.

### Columna vs tabla
- Constraint de columna: afecta a una columna (NN, CK simple, PK simple).
- Constraint de tabla: compuesta o mas clara al final (PK/FK compuestas).

### FK y ON DELETE (muy preguntado)
- `ON DELETE CASCADE`: si borro el padre, borro los hijos automaticamente.
- `ON DELETE SET NULL`: si borro el padre, la FK del hijo pasa a NULL (la columna FK no puede ser NOT NULL).

## 5) ALTER TABLE (evolucionar el modelo)

### Operaciones tipicas
```sql
ALTER TABLE T ADD (NUEVA_COL VARCHAR2(50));
ALTER TABLE T MODIFY (NUEVA_COL VARCHAR2(120));
ALTER TABLE T RENAME COLUMN VIEJA TO NUEVA;
ALTER TABLE T DROP COLUMN NUEVA_COL;
```

### Constraints con ALTER
```sql
ALTER TABLE T ADD CONSTRAINT T_EMAIL_UQ UNIQUE (EMAIL);
ALTER TABLE T ADD CONSTRAINT T_SAL_CK CHECK (SALARIO >= 0);
ALTER TABLE T DROP CONSTRAINT T_EMAIL_UQ;
ALTER TABLE T DISABLE CONSTRAINT T_SAL_CK;
ALTER TABLE T ENABLE CONSTRAINT T_SAL_CK;
```

Error tipico: `MODIFY ... NOT NULL` falla si existen filas con NULL.

## 6) DROP vs TRUNCATE vs RENAME
- DROP TABLE: elimina la tabla (estructura). Puede requerir `CASCADE CONSTRAINTS`.
- TRUNCATE TABLE: elimina TODAS las filas, mantiene estructura (rapido).
- RENAME: renombra un objeto.

## 7) Indices / Vistas / Sinonimos

### Indices
```sql
CREATE INDEX EMP_APELLIDO_IX ON EMP (APELLIDO);
DROP INDEX EMP_APELLIDO_IX;
```
Idea clave: acelera lecturas, pero encarece escrituras. No se indexa "todo".

### Vistas
```sql
CREATE OR REPLACE VIEW V_EMP_DEPT AS
SELECT e.EMPNO, e.APELLIDO, d.DNOMBRE
FROM EMP e
JOIN DEPT d ON d.DEPTNO = e.DEPTNO;
```

### Sinonimos
```sql
CREATE SYNONYM EMP_ALIAS FOR EMP;
DROP SYNONYM EMP_ALIAS;
```

## 8) Lista de errores tipicos (y como pensar)
- ORA-02291 (FK): insertas hijo con padre inexistente -> crea padre primero o corrige DEPTNO.
- ORA-01400 (NN): intentas insertar NULL en NOT NULL -> faltan datos.
- ORA-00001 (UQ/PK): valor repetido -> cambia dato o revisa clave.
- CHECK falla: regla de negocio no se cumple -> revisa rango/IN.

## 9) Mini-checklist para examen
1) Se crear tabla: tipos + constraints con nombre.
2) Se distinguir PK vs UQ vs NN.
3) Se crear FK correcta y entiendo ON DELETE.
4) Se hacer ALTER (add/modify/drop/rename + add/drop constraint).
5) Se explicar DROP vs TRUNCATE.
6) Se crear un INDEX y justificar.
7) Se crear una VIEW.
8) Se crear un SYNONYM (si procede).

