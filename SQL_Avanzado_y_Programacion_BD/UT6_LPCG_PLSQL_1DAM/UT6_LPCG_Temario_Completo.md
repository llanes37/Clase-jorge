# UT6 - LPCG: Lenguaje de Programación. Construcción de Guiones (PL/SQL)

> **Asignatura:** Bases de Datos — **Curso:** 1º DAM
> **Nivel:** Examen de 1 DAM (fundamentos de PL/SQL Oracle)
> **Bloques:** Instrucciones de control · Subprogramas · Cursores · Triggers · Paquetes

---

## Índice

1. [Introducción a PL/SQL](#1-introducción-a-plsql)
2. [Estructura de un bloque PL/SQL](#2-estructura-de-un-bloque-plsql)
3. [Variables y tipos de datos](#3-variables-y-tipos-de-datos)
4. [UT6.2 — Instrucciones de control](#ut62--instrucciones-de-control)
5. [UT6.3 — Subprogramas (Procedimientos y Funciones)](#ut63--subprogramas-procedimientos-y-funciones)
6. [UT6.4 — Cursores](#ut64--cursores)
7. [UT6.5 — Triggers (Disparadores)](#ut65--triggers-disparadores)
8. [UT6.6 — Paquetes](#ut66--paquetes)
9. [Errores típicos en exámenes](#9-errores-típicos-en-exámenes)
10. [Resumen de comandos clave](#10-resumen-de-comandos-clave)

---

## 1. Introducción a PL/SQL

**PL/SQL** (*Procedural Language extension to SQL*) es el lenguaje de programación procedimental de Oracle. Extiende SQL con:

- Variables
- Estructuras de control (IF, LOOP, WHILE, FOR)
- Procedimientos y funciones
- Manejo de errores (EXCEPTION)
- Cursores para recorrer resultados

### ¿Para qué sirve?

SQL por sí solo es **declarativo**: dices QUÉ quieres, no CÓMO. PL/SQL añade la parte **procedimental**: bucles, condicionales, lógica.

```
SQL puro:        SELECT * FROM alumnos WHERE nota >= 5;
PL/SQL:          Bucle que recorra alumnos y para cada uno haga algo distinto según su nota.
```

### Ventajas frente a hacerlo en Java/otro lenguaje

- **Velocidad:** se ejecuta dentro del motor de BD (sin viajes cliente↔servidor).
- **Seguridad:** lógica encapsulada en la BD.
- **Reutilización:** procedimientos y paquetes compartidos.
- **Integridad:** triggers que se disparan automáticamente.

---

## 2. Estructura de un bloque PL/SQL

Todo bloque PL/SQL tiene **tres secciones** (las dos primeras son opcionales):

```sql
DECLARE
    -- Sección de declaración (opcional)
    -- Variables, constantes, cursores, excepciones
BEGIN
    -- Sección ejecutable (OBLIGATORIA)
    -- Sentencias SQL y PL/SQL
EXCEPTION
    -- Sección de manejo de errores (opcional)
    -- Capturar y tratar excepciones
END;
/
```

> **`/` final:** indica a SQL*Plus / SQL Developer que ejecute el bloque. Es la "tecla enter" del PL/SQL.

### Tipos de bloque

| Tipo | Características | Ejemplo |
|------|----------------|---------|
| **Anónimo** | No tiene nombre, no se guarda en la BD | `BEGIN ... END;` directo |
| **Nombrado** | Se almacena en la BD con un nombre | Procedimientos, funciones, paquetes, triggers |

### Mostrar resultados por pantalla

Antes de empezar, en SQL*Plus o SQL Developer hay que activar la salida:

```sql
SET SERVEROUTPUT ON;
```

Y para imprimir usamos:

```sql
DBMS_OUTPUT.PUT_LINE('Hola mundo');
```

---

## 3. Variables y tipos de datos

### Declaración

```sql
DECLARE
    v_nombre   VARCHAR2(50);          -- Sin valor inicial → NULL
    v_edad     NUMBER(3) := 18;       -- Con valor inicial
    v_fecha    DATE := SYSDATE;       -- Fecha del sistema
    c_iva      CONSTANT NUMBER := 0.21;  -- Constante (no se puede modificar)
BEGIN
    v_nombre := 'Ana';
    DBMS_OUTPUT.PUT_LINE(v_nombre || ' tiene ' || v_edad || ' años');
END;
/
```

### Tipos básicos

| Tipo | Uso | Ejemplo |
|------|-----|---------|
| `NUMBER(p,s)` | Números | `NUMBER(5,2)` → hasta 999.99 |
| `VARCHAR2(n)` | Texto variable | `VARCHAR2(50)` |
| `CHAR(n)` | Texto fijo | `CHAR(10)` siempre 10 chars |
| `DATE` | Fecha y hora | `SYSDATE` |
| `BOOLEAN` | Verdadero/Falso | `TRUE`, `FALSE`, `NULL` |
| `CLOB` | Texto grande | Documentos |

### Tipos anclados (muy importante para examen)

Permiten que la variable tome el tipo de una columna o fila de la BD. Si la BD cambia, el código sigue funcionando.

```sql
DECLARE
    v_nombre   alumnos.nombre%TYPE;        -- Mismo tipo que la columna "nombre"
    v_alumno   alumnos%ROWTYPE;            -- Una fila entera de la tabla "alumnos"
BEGIN
    SELECT * INTO v_alumno
    FROM alumnos
    WHERE id = 1;

    DBMS_OUTPUT.PUT_LINE(v_alumno.nombre);
END;
/
```

> **Truco de examen:** usa siempre `%TYPE` y `%ROWTYPE`. Si el profesor cambia el tamaño de una columna y tú habías puesto `VARCHAR2(50)` a fuego, tu código rompe.

---

## UT6.2 — Instrucciones de control

Permiten alterar el flujo de ejecución: tomar decisiones o repetir código.

### 4.1 IF - THEN - ELSE

```sql
-- Forma básica
IF nota >= 5 THEN
    DBMS_OUTPUT.PUT_LINE('Aprobado');
END IF;

-- Con alternativa
IF nota >= 5 THEN
    DBMS_OUTPUT.PUT_LINE('Aprobado');
ELSE
    DBMS_OUTPUT.PUT_LINE('Suspenso');
END IF;

-- Con varias alternativas
IF nota >= 9 THEN
    DBMS_OUTPUT.PUT_LINE('Sobresaliente');
ELSIF nota >= 7 THEN                      -- ¡OJO! ELSIF (sin E intermedia)
    DBMS_OUTPUT.PUT_LINE('Notable');
ELSIF nota >= 5 THEN
    DBMS_OUTPUT.PUT_LINE('Aprobado');
ELSE
    DBMS_OUTPUT.PUT_LINE('Suspenso');
END IF;
```

> **Pregunta típica de examen:** ¿Cuál es la sintaxis correcta? `ELSIF` (no `ELSE IF`, no `ELIF`).

### 4.2 CASE

Dos formas: **simple** (comparación de igualdad) y **buscado** (condiciones libres).

```sql
-- CASE simple
CASE v_dia
    WHEN 1 THEN DBMS_OUTPUT.PUT_LINE('Lunes');
    WHEN 2 THEN DBMS_OUTPUT.PUT_LINE('Martes');
    ELSE        DBMS_OUTPUT.PUT_LINE('Otro día');
END CASE;

-- CASE buscado (más potente)
CASE
    WHEN nota >= 9 THEN v_calif := 'Sobresaliente';
    WHEN nota >= 7 THEN v_calif := 'Notable';
    WHEN nota >= 5 THEN v_calif := 'Aprobado';
    ELSE              v_calif := 'Suspenso';
END CASE;

-- CASE como expresión (devuelve valor)
v_calif := CASE
              WHEN nota >= 5 THEN 'Aprobado'
              ELSE 'Suspenso'
           END;
```

### 4.3 Bucle LOOP básico

Bucle infinito hasta encontrar un `EXIT`.

```sql
DECLARE
    i NUMBER := 1;
BEGIN
    LOOP
        DBMS_OUTPUT.PUT_LINE('Iteración ' || i);
        i := i + 1;
        EXIT WHEN i > 5;        -- Salida condicional
    END LOOP;
END;
/
```

### 4.4 Bucle WHILE

Se ejecuta MIENTRAS la condición sea verdadera. Se evalúa ANTES de entrar.

```sql
DECLARE
    i NUMBER := 1;
BEGIN
    WHILE i <= 5 LOOP
        DBMS_OUTPUT.PUT_LINE(i);
        i := i + 1;
    END LOOP;
END;
/
```

### 4.5 Bucle FOR numérico

Itera entre dos valores. La variable de control se declara automáticamente.

```sql
BEGIN
    FOR i IN 1..5 LOOP                  -- De 1 a 5 (ambos incluidos)
        DBMS_OUTPUT.PUT_LINE(i);
    END LOOP;

    FOR j IN REVERSE 1..5 LOOP          -- De 5 a 1
        DBMS_OUTPUT.PUT_LINE(j);
    END LOOP;
END;
/
```

> **Pregunta típica:** ¿Hay que declarar `i`? **NO**, el FOR la crea sola. Y solo existe DENTRO del bucle.

### 4.6 EXIT y CONTINUE

```sql
FOR i IN 1..10 LOOP
    IF i = 5 THEN
        EXIT;                            -- Sale del bucle
    END IF;
    IF MOD(i, 2) = 0 THEN
        CONTINUE;                        -- Salta a la siguiente iteración
    END IF;
    DBMS_OUTPUT.PUT_LINE(i);
END LOOP;
```

---

## UT6.3 — Subprogramas (Procedimientos y Funciones)

Bloques **nombrados** que se guardan en la BD y se pueden invocar varias veces.

### Diferencias clave

| Procedimiento | Función |
|---------------|---------|
| **NO** devuelve valor | **SÍ** devuelve un valor (`RETURN`) |
| Se invoca como sentencia: `EXEC proc(args);` | Se invoca dentro de una expresión: `v := func(args);` |
| Puede tener parámetros `IN`, `OUT`, `IN OUT` | Normalmente solo `IN` |
| No se usa en SELECT | **Sí** se puede usar en SELECT |

### 5.1 Procedimientos

#### Sintaxis

```sql
CREATE OR REPLACE PROCEDURE nombre_proc
    (param1 IN  tipo,
     param2 OUT tipo,
     param3 IN OUT tipo)
IS
    -- Declaraciones locales
BEGIN
    -- Cuerpo
EXCEPTION
    -- Manejo de errores
END nombre_proc;
/
```

#### Tipos de parámetros

| Modo | Significado | Visualización |
|------|-------------|----------------|
| `IN` | Entrada (solo lectura). **Por defecto.** | El procedimiento lo lee |
| `OUT` | Salida. Devuelve un valor al llamador. | El procedimiento lo escribe |
| `IN OUT` | Entra con un valor y sale con otro | Lectura y escritura |

#### Ejemplo: procedimiento que sube el sueldo

```sql
CREATE OR REPLACE PROCEDURE subir_sueldo
    (p_id_empleado IN NUMBER,
     p_porcentaje  IN NUMBER)
IS
BEGIN
    UPDATE empleados
       SET salario = salario * (1 + p_porcentaje/100)
     WHERE id = p_id_empleado;

    DBMS_OUTPUT.PUT_LINE('Sueldo actualizado');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- Invocación
EXEC subir_sueldo(101, 10);
-- O bien
BEGIN
    subir_sueldo(101, 10);
END;
/
```

#### Ejemplo con parámetro OUT

```sql
CREATE OR REPLACE PROCEDURE obtener_nombre
    (p_id     IN  NUMBER,
     p_nombre OUT VARCHAR2)
IS
BEGIN
    SELECT nombre INTO p_nombre
    FROM empleados
    WHERE id = p_id;
END;
/

-- Invocación
DECLARE
    v_nombre VARCHAR2(50);
BEGIN
    obtener_nombre(101, v_nombre);
    DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_nombre);
END;
/
```

### 5.2 Funciones

#### Sintaxis

```sql
CREATE OR REPLACE FUNCTION nombre_func
    (param1 tipo, param2 tipo)
RETURN tipo_devuelto
IS
    -- Declaraciones
BEGIN
    -- Cuerpo
    RETURN valor;          -- OBLIGATORIO
END nombre_func;
/
```

#### Ejemplo: función que calcula el IVA

```sql
CREATE OR REPLACE FUNCTION calcular_iva(p_precio NUMBER)
RETURN NUMBER
IS
    v_iva NUMBER;
BEGIN
    v_iva := p_precio * 0.21;
    RETURN v_iva;
END;
/

-- Usos posibles:
SELECT producto, precio, calcular_iva(precio) AS iva
  FROM productos;

DECLARE
    v_total NUMBER;
BEGIN
    v_total := 100 + calcular_iva(100);
    DBMS_OUTPUT.PUT_LINE('Total con IVA: ' || v_total);
END;
/
```

> **Trampa de examen:** Una función SIEMPRE debe tener `RETURN`. Si hay un camino del código que no lo tiene → error de compilación.

---

## UT6.4 — Cursores

Un **cursor** es un puntero a un área de memoria que guarda el resultado de una consulta. Permite **procesar fila a fila**.

### ¿Cuándo se usan?

Cuando un `SELECT ... INTO` devuelve **más de una fila** (en ese caso `INTO` falla con `TOO_MANY_ROWS`). El cursor permite recorrerlas una a una.

### Tipos de cursores

| Tipo | Descripción |
|------|-------------|
| **Implícito** | Lo crea Oracle automáticamente para cada DML/SELECT INTO |
| **Explícito** | Lo declara y maneja el programador |
| **Parametrizado** | Explícito que recibe parámetros |

### 6.1 Cursor implícito

Lo gestiona Oracle solo. Podemos consultar sus atributos:

```sql
BEGIN
    UPDATE empleados SET salario = salario * 1.1
     WHERE departamento = 'IT';

    DBMS_OUTPUT.PUT_LINE('Filas actualizadas: ' || SQL%ROWCOUNT);

    IF SQL%FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Sí se actualizó algo');
    END IF;
END;
/
```

| Atributo | Significado |
|----------|-------------|
| `SQL%FOUND` | TRUE si la última sentencia afectó alguna fila |
| `SQL%NOTFOUND` | TRUE si NO afectó ninguna |
| `SQL%ROWCOUNT` | Nº de filas afectadas |
| `SQL%ISOPEN` | Para cursores implícitos siempre es FALSE |

### 6.2 Cursor explícito (las 4 fases)

Los **cuatro pasos** que hay que memorizar para el examen:

1. **DECLARE** — Declarar el cursor (sin ejecutarlo aún)
2. **OPEN** — Abrir (ejecuta la consulta, no recupera filas)
3. **FETCH** — Recuperar una fila en variables
4. **CLOSE** — Cerrar y liberar recursos

```sql
DECLARE
    -- 1. DECLARACIÓN
    CURSOR c_empleados IS
        SELECT id, nombre, salario
          FROM empleados
         WHERE departamento = 'IT';

    -- Variables para recoger los datos
    v_id      empleados.id%TYPE;
    v_nombre  empleados.nombre%TYPE;
    v_salario empleados.salario%TYPE;
BEGIN
    -- 2. APERTURA
    OPEN c_empleados;

    -- 3. RECUPERACIÓN (con bucle)
    LOOP
        FETCH c_empleados INTO v_id, v_nombre, v_salario;
        EXIT WHEN c_empleados%NOTFOUND;       -- Salir cuando no haya más

        DBMS_OUTPUT.PUT_LINE(v_id || ' - ' || v_nombre);
    END LOOP;

    -- 4. CIERRE
    CLOSE c_empleados;
END;
/
```

### 6.3 Atributos del cursor explícito

| Atributo | Significado |
|----------|-------------|
| `c_nombre%FOUND` | TRUE si el último FETCH trajo fila |
| `c_nombre%NOTFOUND` | TRUE si el último FETCH NO trajo fila |
| `c_nombre%ROWCOUNT` | Nº de filas recuperadas hasta ahora |
| `c_nombre%ISOPEN` | TRUE si el cursor está abierto |

> **Pregunta clásica:** ¿Dónde colocar `EXIT WHEN c%NOTFOUND;`? **JUSTO DESPUÉS del FETCH**. Si lo pones antes, sales antes de procesar la última fila.

### 6.4 Cursor FOR (forma simplificada)

Forma compacta. Oracle hace el OPEN, FETCH, CLOSE y crea la variable automáticamente.

```sql
BEGIN
    FOR reg IN (SELECT id, nombre, salario
                  FROM empleados
                 WHERE departamento = 'IT')
    LOOP
        DBMS_OUTPUT.PUT_LINE(reg.id || ' - ' || reg.nombre);
    END LOOP;
END;
/
```

> **Recomendación:** salvo que el examen exija las 4 fases explícitas, usa SIEMPRE el `FOR`. Es más limpio y más difícil de equivocarse.

### 6.5 Cursor parametrizado

Recibe parámetros, como un procedimiento.

```sql
DECLARE
    CURSOR c_emp_por_depto(p_depto VARCHAR2) IS
        SELECT id, nombre
          FROM empleados
         WHERE departamento = p_depto;
BEGIN
    FOR reg IN c_emp_por_depto('IT') LOOP
        DBMS_OUTPUT.PUT_LINE(reg.nombre);
    END LOOP;

    FOR reg IN c_emp_por_depto('VENTAS') LOOP
        DBMS_OUTPUT.PUT_LINE(reg.nombre);
    END LOOP;
END;
/
```

---

## UT6.5 — Triggers (Disparadores)

Un **trigger** es un bloque PL/SQL que se ejecuta **automáticamente** cuando ocurre un evento sobre una tabla (INSERT, UPDATE, DELETE).

### Tipos de triggers

#### Por el momento de disparo

- **BEFORE:** se ejecuta ANTES de la operación. Sirve para validar o modificar datos antes de guardar.
- **AFTER:** se ejecuta DESPUÉS. Sirve para auditoría, registros, etc.

#### Por el ámbito

- **ROW** (`FOR EACH ROW`): se dispara una vez **por cada fila** afectada.
- **STATEMENT** (sin `FOR EACH ROW`): se dispara una vez **por sentencia**, aunque afecte a 100 filas.

#### Por el evento

- `INSERT`
- `UPDATE` (puede ser `UPDATE OF columna`)
- `DELETE`

### 7.1 Sintaxis general

```sql
CREATE OR REPLACE TRIGGER nombre_trigger
    {BEFORE | AFTER | INSTEAD OF}
    {INSERT | UPDATE | DELETE} [OR ...]
    ON nombre_tabla
    [FOR EACH ROW]
    [WHEN (condicion)]
DECLARE
    -- Declaraciones (opcional)
BEGIN
    -- Cuerpo
END;
/
```

### 7.2 :NEW y :OLD (FUNDAMENTAL para examen)

Solo disponibles en triggers `FOR EACH ROW`:

| Operación | `:OLD` | `:NEW` |
|-----------|--------|--------|
| INSERT | NULL | Valor que se va a insertar |
| UPDATE | Valor anterior | Valor nuevo |
| DELETE | Valor a eliminar | NULL |

### 7.3 Ejemplo: validar antes de insertar

```sql
CREATE OR REPLACE TRIGGER tr_validar_salario
    BEFORE INSERT OR UPDATE ON empleados
    FOR EACH ROW
BEGIN
    IF :NEW.salario < 1000 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Salario mínimo: 1000€');
    END IF;
END;
/
```

### 7.4 Ejemplo: auditoría tras modificación

```sql
CREATE OR REPLACE TRIGGER tr_auditar_cambios
    AFTER UPDATE ON empleados
    FOR EACH ROW
BEGIN
    INSERT INTO auditoria(id_empleado, salario_antiguo, salario_nuevo, fecha)
    VALUES (:OLD.id, :OLD.salario, :NEW.salario, SYSDATE);
END;
/
```

### 7.5 Predicados condicionales: INSERTING / UPDATING / DELETING

Permiten que UN trigger responda a varios eventos a la vez:

```sql
CREATE OR REPLACE TRIGGER tr_log
    AFTER INSERT OR UPDATE OR DELETE ON empleados
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO log VALUES (:NEW.id, 'INSERT', SYSDATE);
    ELSIF UPDATING THEN
        INSERT INTO log VALUES (:NEW.id, 'UPDATE', SYSDATE);
    ELSIF DELETING THEN
        INSERT INTO log VALUES (:OLD.id, 'DELETE', SYSDATE);
    END IF;
END;
/
```

### 7.6 Borrar y deshabilitar triggers

```sql
-- Eliminar el trigger
DROP TRIGGER tr_validar_salario;

-- Desactivar (sin borrarlo)
ALTER TRIGGER tr_validar_salario DISABLE;

-- Volver a activar
ALTER TRIGGER tr_validar_salario ENABLE;
```

### Errores típicos con triggers

1. **Usar :NEW en un DELETE** → es NULL, error lógico.
2. **Usar :OLD en un INSERT** → es NULL, error lógico.
3. **Olvidar `FOR EACH ROW`** → no podrás usar :NEW ni :OLD.
4. **Mutating table error:** intentar leer/modificar la misma tabla dentro del trigger.

---

## UT6.6 — Paquetes

Un **paquete** (package) es una agrupación de procedimientos, funciones, variables y cursores relacionados, encapsulados como una unidad.

### Ventajas

- **Modularidad:** todo lo relacionado, junto.
- **Encapsulación:** distingue entre PÚBLICO (lo de la especificación) y PRIVADO (solo en el cuerpo).
- **Rendimiento:** se carga en memoria entero al primer uso.
- **Sobrecarga:** mismo nombre con parámetros distintos.

### Estructura: ESPECIFICACIÓN + CUERPO

Un paquete tiene SIEMPRE dos partes:

#### A) Especificación (la "interfaz pública")

Es lo que ven los usuarios del paquete. Declara QUÉ hay, no CÓMO se hace.

```sql
CREATE OR REPLACE PACKAGE pkg_empleados IS

    -- Constantes/variables públicas
    c_salario_minimo CONSTANT NUMBER := 1000;

    -- Procedimientos públicos
    PROCEDURE contratar(p_nombre VARCHAR2, p_salario NUMBER);
    PROCEDURE despedir(p_id NUMBER);

    -- Funciones públicas
    FUNCTION salario_medio RETURN NUMBER;
    FUNCTION contar_empleados(p_depto VARCHAR2) RETURN NUMBER;

END pkg_empleados;
/
```

#### B) Cuerpo (la "implementación")

Aquí va el código real de cada elemento declarado en la especificación, además de elementos PRIVADOS (que solo se usan dentro del paquete).

```sql
CREATE OR REPLACE PACKAGE BODY pkg_empleados IS

    -- Función privada (no aparece en la especificación)
    FUNCTION existe_empleado(p_id NUMBER) RETURN BOOLEAN IS
        v_cont NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_cont FROM empleados WHERE id = p_id;
        RETURN v_cont > 0;
    END existe_empleado;

    -- Implementación pública
    PROCEDURE contratar(p_nombre VARCHAR2, p_salario NUMBER) IS
    BEGIN
        IF p_salario < c_salario_minimo THEN
            RAISE_APPLICATION_ERROR(-20001, 'Salario por debajo del mínimo');
        END IF;

        INSERT INTO empleados(nombre, salario) VALUES (p_nombre, p_salario);
    END contratar;

    PROCEDURE despedir(p_id NUMBER) IS
    BEGIN
        IF NOT existe_empleado(p_id) THEN
            RAISE_APPLICATION_ERROR(-20002, 'Empleado no existe');
        END IF;
        DELETE FROM empleados WHERE id = p_id;
    END despedir;

    FUNCTION salario_medio RETURN NUMBER IS
        v_media NUMBER;
    BEGIN
        SELECT AVG(salario) INTO v_media FROM empleados;
        RETURN v_media;
    END salario_medio;

    FUNCTION contar_empleados(p_depto VARCHAR2) RETURN NUMBER IS
        v_cont NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_cont FROM empleados WHERE departamento = p_depto;
        RETURN v_cont;
    END contar_empleados;

END pkg_empleados;
/
```

### Cómo usar el paquete

Se accede con la notación `paquete.elemento`:

```sql
BEGIN
    pkg_empleados.contratar('Lucía', 1500);
    DBMS_OUTPUT.PUT_LINE('Media: ' || pkg_empleados.salario_medio);
    DBMS_OUTPUT.PUT_LINE('IT: '    || pkg_empleados.contar_empleados('IT'));
END;
/
```

### Lo que sí y lo que no va en cada parte

| Elemento | Especificación | Cuerpo |
|----------|:--------------:|:------:|
| Declarar variables públicas | ✅ | ❌ (van declaradas arriba en especif.) |
| Declarar variables privadas | ❌ | ✅ |
| Cabecera de procedimientos públicos | ✅ | También (con `IS BEGIN ... END`) |
| Cuerpo (lógica) de procedimientos | ❌ | ✅ |
| Procedimientos privados | ❌ | ✅ |

> **Trampa típica:** Si declaras un procedimiento en la especificación pero NO lo implementas en el cuerpo → error de compilación.

---

## 9. Errores típicos en exámenes

### Errores de sintaxis frecuentes

| Error | Forma correcta |
|-------|----------------|
| `ELSE IF` | `ELSIF` |
| Olvidar `END IF;` | Siempre cerrar con `END IF;` |
| Olvidar `END LOOP;` | Siempre cerrar con `END LOOP;` |
| Olvidar `;` al final de cada sentencia | Cada sentencia acaba en `;` |
| `RETURN` sin valor en función | Las funciones DEBEN devolver valor |
| Falta `/` al final del bloque | Es lo que ejecuta el bloque |
| `IF a = NULL` | Usar `IF a IS NULL` |

### Errores semánticos

- Usar `SELECT ... INTO` esperando 1 fila y que devuelva más → `TOO_MANY_ROWS`.
- Usar `SELECT ... INTO` y que no devuelva nada → `NO_DATA_FOUND`.
- Modificar la tabla del trigger en el propio trigger → `mutating table`.
- Olvidar `CLOSE` del cursor (memory leak).
- Poner `EXIT WHEN c%NOTFOUND` antes del FETCH (sale antes de procesar).

### Excepciones predefinidas más comunes

```sql
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontró ningún registro');
    WHEN TOO_MANY_ROWS THEN
        DBMS_OUTPUT.PUT_LINE('Más de una fila devuelta');
    WHEN ZERO_DIVIDE THEN
        DBMS_OUTPUT.PUT_LINE('División por cero');
    WHEN VALUE_ERROR THEN
        DBMS_OUTPUT.PUT_LINE('Error de conversión de tipos');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
```

---

## 10. Resumen de comandos clave

### Para examen, no te olvides de:

```sql
-- Activar salida por pantalla
SET SERVEROUTPUT ON;

-- Bloque anónimo mínimo
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hola');
END;
/

-- Procedimiento
CREATE OR REPLACE PROCEDURE p_nombre(p IN tipo) IS
BEGIN
    -- ...
END;
/

-- Función
CREATE OR REPLACE FUNCTION f_nombre(p tipo) RETURN tipo IS
BEGIN
    RETURN valor;
END;
/

-- Cursor explícito (4 fases)
DECLARE
    CURSOR c IS SELECT ...;
    v reg%ROWTYPE;
BEGIN
    OPEN c;
    LOOP
        FETCH c INTO v;
        EXIT WHEN c%NOTFOUND;
        -- procesar
    END LOOP;
    CLOSE c;
END;
/

-- Cursor FOR (versión corta)
BEGIN
    FOR r IN (SELECT ...) LOOP
        -- procesar r.campo
    END LOOP;
END;
/

-- Trigger
CREATE OR REPLACE TRIGGER tr_nombre
    BEFORE INSERT OR UPDATE ON tabla
    FOR EACH ROW
BEGIN
    -- usar :NEW.campo y :OLD.campo
END;
/

-- Paquete: especificación
CREATE OR REPLACE PACKAGE pkg_nombre IS
    PROCEDURE p1(p tipo);
    FUNCTION f1(p tipo) RETURN tipo;
END pkg_nombre;
/

-- Paquete: cuerpo
CREATE OR REPLACE PACKAGE BODY pkg_nombre IS
    PROCEDURE p1(p tipo) IS BEGIN ... END p1;
    FUNCTION  f1(p tipo) RETURN tipo IS BEGIN RETURN ...; END f1;
END pkg_nombre;
/

-- Ver errores de compilación
SHOW ERRORS;
```

---

## Tabla resumen para repaso rápido

| Concepto | Para qué sirve | Palabra clave |
|----------|----------------|---------------|
| Variables | Almacenar datos temporales | `DECLARE`, `:=` |
| Condicional | Tomar decisiones | `IF...ELSIF...ELSE`, `CASE` |
| Bucle infinito | Repetir hasta EXIT | `LOOP...END LOOP` |
| Bucle while | Repetir mientras... | `WHILE...LOOP` |
| Bucle for | Repetir N veces | `FOR i IN 1..N LOOP` |
| Procedimiento | Acción sin valor | `CREATE PROCEDURE` |
| Función | Cálculo con valor de retorno | `CREATE FUNCTION ... RETURN` |
| Cursor | Recorrer varias filas | `CURSOR ... OPEN/FETCH/CLOSE` |
| Trigger | Acción automática ante evento | `CREATE TRIGGER ... ON ...` |
| Paquete | Agrupar elementos relacionados | `CREATE PACKAGE` + `BODY` |

---

**¡Suerte en el examen!** Repasa el archivo `UT6_LPCG_Ejemplos.sql` adjunto donde tienes todos estos ejemplos para ejecutar.
