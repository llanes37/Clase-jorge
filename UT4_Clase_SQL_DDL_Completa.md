# UT4 - Clase Completa de **SQL (DDL en Oracle)** (Markdown Docente)
**Autor:** Joaquin Rodriguez  
**Nivel:** Iniciacion / Grado medio (BBDD)  
**Modo de uso:** Explica + demuestra + ejercita (ideal en Oracle SQL Developer)

> Este documento esta basado en tus PDFs `UT 4 - ELSQL -- DDL - Parte 1/2` y esta pensado para dar una clase **muy practica**: primero base conceptual, luego DDL "de verdad" con **constraints**, `ALTER`, `DROP/TRUNCATE`, `INDEX`, `VIEW` y `SYNONYM`.

---

## Indice
- [Como leer este documento](#como-leer-este-documento)
- [0) Preparacion rapida (entorno y normas)](#0-preparacion-rapida-entorno-y-normas)
- [1) SQL en 3 frases (DML / DDL / DCL)](#1-sql-en-3-frases-dml--ddl--dcl)
- [2) Conceptos clave: usuario, esquema, objetos](#2-conceptos-clave-usuario-esquema-objetos)
- [3) DDL: CREATE TABLE (estructura)](#3-ddl-create-table-estructura)
- [4) Constraints (la parte importante)](#4-constraints-la-parte-importante)
- [5) ALTER TABLE (evolucionar el modelo)](#5-alter-table-evolucionar-el-modelo)
- [6) DROP / TRUNCATE / RENAME](#6-drop--truncate--rename)
- [7) Indices](#7-indices)
- [8) Vistas](#8-vistas)
- [9) Sinonimos](#9-sinonimos)
- [10) Mini-proyecto (modelo EMP/DEPT)](#10-mini-proyecto-modelo-empdept)
- [11) Material de practica (scripts)](#11-material-de-practica-scripts)

---

## Como leer este documento
Convencion **Better Comments** (para que el alumnado "vea" que es importante):

- **! Importante** -> idea clave / error tipico.  
- **? Nota** -> contexto util / matiz.  
- **\* Ejemplo** -> bloques copiables.  
- **TODO Alumno** -> ejercicios concretos.  
- **Solucion sugerida** -> una respuesta valida (para el profe o correccion).

---

## 0) Preparacion rapida (entorno y normas)
**Objetivo didactico:** poder ejecutar DDL sin friccion.

**Entorno recomendado**
- Oracle SQL Developer (o SQL*Plus).
- Un usuario con permisos para crear objetos (al menos `CREATE TABLE`, `CREATE VIEW`, `CREATE SEQUENCE` si se usa, etc.).

**Normas de clase (muy utiles)**
- **! Importante:** DDL **no se "deshace"** como un `ROLLBACK` normal (depende de herramientas, pero en general: cuidado).  
- Trabaja con scripts: ejecuta por bloques y **lee el error** (te ensena que constraint se ha roto).  
- Nombres consistentes: `T_...` o nombres simples (`DEPT`, `EMP`) y constraints con sufijo (`_PK`, `_FK`, `_CK`, `_UQ`, `_NN`).  

---

## 1) SQL en 3 frases (DML / DDL / DCL)
**Objetivo didactico:** ubicar cada sentencia en su "familia".

- **DML** (Data Manipulation Language): datos dentro de tablas.  
  - `SELECT`, `INSERT`, `UPDATE`, `DELETE`
- **DDL** (Data Definition Language): estructura/objetos de BD (metadatos).  
  - `CREATE`, `ALTER`, `DROP`, `TRUNCATE`, `RENAME`
- **DCL + TCL** (segun temario): seguridad y transacciones.  
  - `GRANT`, `REVOKE`, `COMMIT`, `ROLLBACK`

**! Importante:** hoy nos centramos en **DDL**, pero veras que DDL y DML se necesitan mutuamente para comprobar constraints.

---

## 2) Conceptos clave: usuario, esquema, objetos
**Objetivo didactico:** entender "donde vive" una tabla.

- **Usuario**: cuenta con permisos en la BD.
- **Esquema**: conjunto de objetos propiedad de un usuario (en Oracle, suele coincidir con el nombre del usuario).
- **Objetos tipicos**: tablas, vistas, indices, sinonimos, secuencias...

**? Nota:** en Oracle es muy comun referenciar objetos como `ESQUEMA.TABLA` si trabajas con varios usuarios.

---

## 3) DDL: CREATE TABLE (estructura)
**Objetivo didactico:** saber crear una tabla correcta desde el minuto 1.

### Plantilla minima
```sql
CREATE TABLE NOMBRE_TABLA (
  COLUMNA1  TIPO_DATO,
  COLUMNA2  TIPO_DATO
);
```

### Tipos de datos (los mas usados en iniciacion)
- `NUMBER(p[,s])` -> numeros (p = precision, s = decimales)
- `VARCHAR2(n)` -> texto variable
- `CHAR(n)` -> texto fijo (codigos cortos)
- `DATE` -> fecha/hora (segun configuracion)

**! Importante:** el tipo de dato ayuda pero no valida reglas de negocio. Para eso estan los **constraints**.

---

## 4) Constraints (la parte importante)
**Objetivo didactico:** garantizar calidad de datos desde la estructura.

### Tipos de constraints (lo que cae en examen)
- `NOT NULL` (NN): el campo debe tener valor.
- `UNIQUE` (UQ): valores no repetidos (permite `NULL` salvo combinacion con NN).
- `PRIMARY KEY` (PK): identifica fila (equivale a `UNIQUE + NOT NULL`).
- `FOREIGN KEY` (FK): relacion entre tablas (integridad referencial).
- `CHECK` (CK): regla de validacion.
- `DEFAULT`: valor por defecto si no se inserta.

### Constraint a nivel de columna vs. a nivel de tabla
**Regla practica**
- Si es simple y afecta a **una** columna -> suele ir junto a la columna.
- Si es compuesta (varias columnas) -> suele ir **al final** (constraint de tabla).

**\* Ejemplo (PK simple + NN + DEFAULT + CHECK)**
```sql
CREATE TABLE DEPT (
  DEPTNO   NUMBER(2) CONSTRAINT DEPT_PK PRIMARY KEY,
  DNOMBRE  VARCHAR2(30) CONSTRAINT DEPT_DNOMBRE_NN NOT NULL,
  LOC      VARCHAR2(30) DEFAULT 'MADRID',
  ACTIVO   CHAR(1) DEFAULT 'S' CONSTRAINT DEPT_ACTIVO_CK CHECK (ACTIVO IN ('S','N'))
);
```

**\* Ejemplo (PK compuesta + FK con ON DELETE)**
```sql
CREATE TABLE MATRICULA (
  ID_ALUMNO   NUMBER(6) NOT NULL,
  ID_ASIGNAT  NUMBER(6) NOT NULL,
  FECHA       DATE DEFAULT SYSDATE NOT NULL,
  CONSTRAINT MATRICULA_PK PRIMARY KEY (ID_ALUMNO, ID_ASIGNAT),
  CONSTRAINT MATRICULA_ALUM_FK FOREIGN KEY (ID_ALUMNO) REFERENCES ALUMNO(ID_ALUMNO) ON DELETE CASCADE,
  CONSTRAINT MATRICULA_ASIG_FK FOREIGN KEY (ID_ASIGNAT) REFERENCES ASIGNATURA(ID_ASIGNAT)
);
```

**! Importante (errores tipicos)**
- Crear la FK cuando la columna referenciada **no es PK/UNIQUE** en la tabla padre.
- Intentar poner `NOT NULL` en una columna con datos nulos existentes.
- Confundir `UNIQUE` con `PRIMARY KEY` (PK no permite NULL y solo 1 por tabla).

---

## 5) ALTER TABLE (evolucionar el modelo)
**Objetivo didactico:** modificar estructura con control.

### Operaciones tipicas
- Anadir columna(s): `ALTER TABLE ... ADD (...)`
- Modificar tipo / NN: `ALTER TABLE ... MODIFY (...)`
- Borrar columna: `ALTER TABLE ... DROP COLUMN ...`
- Anadir/Eliminar constraints: `ALTER TABLE ... ADD CONSTRAINT ...` / `DROP CONSTRAINT ...`
- Activar/Desactivar constraints: `ENABLE` / `DISABLE`
- Renombrar columna: `RENAME COLUMN ... TO ...`

**\* Ejemplo**
```sql
ALTER TABLE EMP ADD (EMAIL VARCHAR2(80));
ALTER TABLE EMP MODIFY (EMAIL VARCHAR2(120));
ALTER TABLE EMP RENAME COLUMN APELLIDO TO APELLIDOS;
```

**! Importante:** `MODIFY ... NOT NULL` solo funciona si no existen filas con `NULL` en esa columna.

---

## 6) DROP / TRUNCATE / RENAME
**Objetivo didactico:** distinguir "borrar datos" vs "borrar la tabla".

- `DROP TABLE` -> elimina **la tabla** (estructura) + objetos dependientes (indices) y deja vistas/sinonimos "rotos".
- `TRUNCATE TABLE` -> elimina **todas las filas** pero mantiene estructura (es rapido).
- `RENAME` -> renombra un objeto.

**\* Ejemplo**
```sql
TRUNCATE TABLE EMP;
DROP TABLE EMP CASCADE CONSTRAINTS;
RENAME EMP TO EMPLEADOS;
```

**! Importante:** `DROP TABLE ... CASCADE CONSTRAINTS` es clave cuando hay FKs apuntando a esa tabla.

---

## 7) Indices
**Objetivo didactico:** entender que son y cuando crearlos.

**Idea rapida**
- Un indice acelera busquedas/joins a costa de mas coste en `INSERT/UPDATE/DELETE`.
- Oracle crea indice automaticamente para `PRIMARY KEY` (y a veces para `UNIQUE` segun configuracion), pero no para FKs.

**\* Ejemplo**
```sql
CREATE INDEX EMP_APELLIDOS_IX ON EMP (APELLIDOS);
DROP INDEX EMP_APELLIDOS_IX;
```

**TODO Alumno**
- Crea un indice sobre una columna que uses mucho para buscar (por ejemplo `EMAIL` o `DEPTNO`).
- Explica en 2 lineas por que no se indexa "todo".

---

## 8) Vistas
**Objetivo didactico:** crear una "tabla virtual" para simplificar consultas.

**\* Ejemplo**
```sql
CREATE OR REPLACE VIEW V_EMP_DEPT AS
SELECT e.EMPNO, e.APELLIDOS, e.SALARIO, d.DNOMBRE, d.LOC
FROM EMP e
JOIN DEPT d ON d.DEPTNO = e.DEPTNO;
```

**? Nota:** `OR REPLACE` permite re-crear la vista sin borrarla primero.

---

## 9) Sinonimos
**Objetivo didactico:** crear un alias para un objeto (util con esquemas).

**\* Ejemplo**
```sql
CREATE SYNONYM EMP FOR TU_ESQUEMA.EMP;
DROP SYNONYM EMP;
```

**! Importante:** los sinonimos no "copian" nada, solo apuntan. Si renombramos/borramos la tabla, el sinonimo queda invalido.

---

## 10) Mini-proyecto (modelo EMP/DEPT)
**Objetivo didactico:** practicar DDL completo de un mini-sistema.

**Resultado esperado**
- Tablas `DEPT` y `EMP` con constraints bien nombradas.
- Inserts de prueba (DML) para verificar que las reglas funcionan.
- Una vista `V_EMP_DEPT`.
- Un indice "razonable".

**TODO Alumno**
1. Crea `DEPT` con: PK, `DNOMBRE NOT NULL`, `LOC DEFAULT`.
2. Crea `EMP` con: PK, `APELLIDOS NOT NULL`, `SALARIO CHECK`, `DEPTNO FK`.
3. Inserta 2 departamentos y 4 empleados (uno debe fallar por constraint).
4. Crea la vista y prueba `SELECT * FROM V_EMP_DEPT`.

---

## 11) Material de practica (scripts)
En esta carpeta tienes (creados para esta UT):
- `UT4_Ejercicios_SQL_DDL_Oracle.sql` -> enunciados + huecos + pistas.
- `UT4_Soluciones_SQL_DDL_Oracle.sql` -> soluciones completas (para correccion).

**Como usarlos**
- En SQL Developer: abre el `.sql`, selecciona un bloque y ejecuta (Ctrl+Enter o F5 segun configuracion).
- Repite el patron: **leer error -> entender constraint -> corregir**.
