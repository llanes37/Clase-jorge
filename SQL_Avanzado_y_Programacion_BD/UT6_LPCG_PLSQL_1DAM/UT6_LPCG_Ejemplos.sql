--==============================================================================
--* UT6 - LPCG: Lenguaje de Programación. Construcción de Guiones (PL/SQL)
--* Curso:    1º DAM - Bases de Datos
--* Nivel:    Examen de 1 DAM
--* Archivo:  Ejemplos prácticos completos
--==============================================================================
--? Convención de comentarios (Better Comments):
--*   --*  Información destacada / Importante
--!   --!  Atención / Cuidado / Errores comunes
--?   --?  Pregunta / Explicación teórica
--    --   Comentario normal
--TODO:    Tarea pendiente para el alumno
--==============================================================================


--==============================================================================
--* PREPARACIÓN INICIAL - Activar la salida por pantalla
--==============================================================================

--! IMPORTANTE: ejecuta esta línea ANTES de cualquier bloque para ver los
--! mensajes de DBMS_OUTPUT.PUT_LINE. Si no, los println no aparecen.
SET SERVEROUTPUT ON;


--==============================================================================
--* TABLAS DE EJEMPLO - Las usaremos durante todo el archivo
--==============================================================================

--? Limpiamos por si existen (para poder ejecutar el script varias veces)
DROP TABLE auditoria_empleados CASCADE CONSTRAINTS;
DROP TABLE empleados            CASCADE CONSTRAINTS;
DROP TABLE departamentos        CASCADE CONSTRAINTS;

--* Tabla DEPARTAMENTOS
CREATE TABLE departamentos (
    id        NUMBER(3)     PRIMARY KEY,
    nombre    VARCHAR2(50)  NOT NULL
);

--* Tabla EMPLEADOS
CREATE TABLE empleados (
    id            NUMBER(5)     PRIMARY KEY,
    nombre        VARCHAR2(50)  NOT NULL,
    salario       NUMBER(8,2),
    id_depto      NUMBER(3)     REFERENCES departamentos(id),
    fecha_alta    DATE          DEFAULT SYSDATE
);

--* Tabla AUDITORÍA (la usaremos en triggers)
CREATE TABLE auditoria_empleados (
    id            NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_empleado   NUMBER,
    accion        VARCHAR2(10),    --! Valores: INSERT, UPDATE, DELETE
    salario_old   NUMBER(8,2),
    salario_new   NUMBER(8,2),
    fecha         DATE
);

--? Insertamos algunos datos de prueba
INSERT INTO departamentos VALUES (10, 'IT');
INSERT INTO departamentos VALUES (20, 'VENTAS');
INSERT INTO departamentos VALUES (30, 'RRHH');

INSERT INTO empleados VALUES (1, 'Ana García',    2500, 10, SYSDATE);
INSERT INTO empleados VALUES (2, 'Luis Martín',   1800, 10, SYSDATE);
INSERT INTO empleados VALUES (3, 'María López',   3000, 20, SYSDATE);
INSERT INTO empleados VALUES (4, 'Pedro Ruiz',    1500, 20, SYSDATE);
INSERT INTO empleados VALUES (5, 'Sara Núñez',    2200, 30, SYSDATE);

COMMIT;



--==============================================================================
--==============================================================================
--*                     UT6.1 - ESTRUCTURA BÁSICA Y VARIABLES
--==============================================================================
--==============================================================================


--------------------------------------------------------------------------------
--* EJEMPLO 1.1 - Bloque anónimo más simple
--------------------------------------------------------------------------------
BEGIN
    DBMS_OUTPUT.PUT_LINE('¡Hola mundo desde PL/SQL!');
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 1.2 - Bloque con sección DECLARE
--------------------------------------------------------------------------------
DECLARE
    --? Sintaxis: nombre TIPO [:= valor_inicial];
    v_nombre   VARCHAR2(50) := 'Joaquín';
    v_edad     NUMBER(3)    := 18;
    v_fecha    DATE         := SYSDATE;
    c_iva      CONSTANT NUMBER := 0.21;   --! CONSTANT = no se puede modificar
BEGIN
    --? Concatenación con ||
    DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_nombre);
    DBMS_OUTPUT.PUT_LINE('Edad:   ' || v_edad);
    DBMS_OUTPUT.PUT_LINE('Fecha:  ' || TO_CHAR(v_fecha, 'DD/MM/YYYY'));
    DBMS_OUTPUT.PUT_LINE('IVA:    ' || (c_iva * 100) || '%');
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 1.3 - Tipos anclados %TYPE y %ROWTYPE (IMPORTANTE PARA EXAMEN)
--------------------------------------------------------------------------------
DECLARE
    --* %TYPE: misma definición que la columna
    v_nombre   empleados.nombre%TYPE;

    --* %ROWTYPE: una fila entera de la tabla (registro)
    v_empleado empleados%ROWTYPE;
BEGIN
    --? SELECT INTO mete el resultado en variables
    SELECT nombre INTO v_nombre
      FROM empleados
     WHERE id = 1;

    DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_nombre);

    --? Para meter toda la fila usamos SELECT * y %ROWTYPE
    SELECT * INTO v_empleado
      FROM empleados
     WHERE id = 1;

    --? Accedemos a cada campo con la notación var.campo
    DBMS_OUTPUT.PUT_LINE('Empleado: ' || v_empleado.nombre
                       || ' - Salario: ' || v_empleado.salario);
END;
/

--TODO: ALUMNO → Modifica el ejemplo para mostrar el empleado con id = 3



--==============================================================================
--==============================================================================
--*                  UT6.2 - INSTRUCCIONES DE CONTROL
--==============================================================================
--==============================================================================


--------------------------------------------------------------------------------
--* EJEMPLO 2.1 - IF / ELSIF / ELSE
--------------------------------------------------------------------------------
DECLARE
    v_nota NUMBER := 7.5;
BEGIN
    --! OJO: ELSIF (no ELSE IF, no ELIF)
    IF v_nota >= 9 THEN
        DBMS_OUTPUT.PUT_LINE('Sobresaliente');
    ELSIF v_nota >= 7 THEN
        DBMS_OUTPUT.PUT_LINE('Notable');
    ELSIF v_nota >= 5 THEN
        DBMS_OUTPUT.PUT_LINE('Aprobado');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Suspenso');
    END IF;
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 2.2 - CASE en dos formas (simple y buscado)
--------------------------------------------------------------------------------
DECLARE
    v_dia      NUMBER := 3;
    v_nota     NUMBER := 6;
    v_calif    VARCHAR2(20);
BEGIN
    --? CASE SIMPLE: compara igualdad
    CASE v_dia
        WHEN 1 THEN DBMS_OUTPUT.PUT_LINE('Lunes');
        WHEN 2 THEN DBMS_OUTPUT.PUT_LINE('Martes');
        WHEN 3 THEN DBMS_OUTPUT.PUT_LINE('Miércoles');
        ELSE        DBMS_OUTPUT.PUT_LINE('Otro día');
    END CASE;

    --? CASE BUSCADO: condiciones libres (más potente)
    CASE
        WHEN v_nota >= 9 THEN v_calif := 'Sobresaliente';
        WHEN v_nota >= 7 THEN v_calif := 'Notable';
        WHEN v_nota >= 5 THEN v_calif := 'Aprobado';
        ELSE                  v_calif := 'Suspenso';
    END CASE;

    DBMS_OUTPUT.PUT_LINE('Calificación: ' || v_calif);
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 2.3 - Bucle LOOP básico (con EXIT WHEN)
--------------------------------------------------------------------------------
DECLARE
    i NUMBER := 1;
BEGIN
    --! Sin EXIT esto sería un bucle infinito
    LOOP
        DBMS_OUTPUT.PUT_LINE('Iteración: ' || i);
        i := i + 1;
        EXIT WHEN i > 5;   --? Salir cuando se cumpla la condición
    END LOOP;
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 2.4 - Bucle WHILE (se evalúa ANTES de entrar)
--------------------------------------------------------------------------------
DECLARE
    v_contador NUMBER := 1;
    v_suma     NUMBER := 0;
BEGIN
    --? Suma los números del 1 al 10
    WHILE v_contador <= 10 LOOP
        v_suma     := v_suma + v_contador;
        v_contador := v_contador + 1;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('Suma 1..10 = ' || v_suma);
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 2.5 - Bucle FOR numérico
--------------------------------------------------------------------------------
BEGIN
    --! La variable de control (i) NO hay que declararla, la crea el FOR
    --! Y solo existe DENTRO del bucle
    FOR i IN 1..5 LOOP
        DBMS_OUTPUT.PUT_LINE('Subiendo: ' || i);
    END LOOP;

    --? REVERSE para ir hacia atrás
    FOR i IN REVERSE 1..5 LOOP
        DBMS_OUTPUT.PUT_LINE('Bajando: ' || i);
    END LOOP;
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 2.6 - EXIT y CONTINUE dentro de un bucle
--------------------------------------------------------------------------------
BEGIN
    FOR i IN 1..10 LOOP
        IF i = 8 THEN
            EXIT;            --? Sale del bucle por completo
        END IF;

        IF MOD(i, 2) = 0 THEN
            CONTINUE;        --? Salta a la siguiente iteración (no imprime)
        END IF;

        DBMS_OUTPUT.PUT_LINE('Impar: ' || i);
    END LOOP;
END;
/


--TODO: ALUMNO → Crea un bloque que use un FOR para imprimir la tabla
--TODO:          de multiplicar del 7 (del 7x1 hasta el 7x10)



--==============================================================================
--==============================================================================
--*                  UT6.3 - SUBPROGRAMAS (PROCEDIMIENTOS Y FUNCIONES)
--==============================================================================
--==============================================================================


--------------------------------------------------------------------------------
--* EJEMPLO 3.1 - Procedimiento simple (solo parámetro IN)
--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE saludar(p_nombre IN VARCHAR2)
IS
    --? Variables locales del procedimiento (si las necesitamos)
    v_mensaje VARCHAR2(100);
BEGIN
    v_mensaje := 'Hola ' || p_nombre || ', bienvenido al curso';
    DBMS_OUTPUT.PUT_LINE(v_mensaje);
END;
/

--? Invocación de un procedimiento (forma 1: EXEC)
EXEC saludar('María');

--? Invocación de un procedimiento (forma 2: dentro de un bloque)
BEGIN
    saludar('Carlos');
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 3.2 - Procedimiento con parámetro OUT (devuelve valor por parámetro)
--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE obtener_salario(
    p_id      IN  NUMBER,
    p_salario OUT NUMBER       --! El OUT permite "devolver" valores
)
IS
BEGIN
    SELECT salario INTO p_salario
      FROM empleados
     WHERE id = p_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_salario := 0;
        DBMS_OUTPUT.PUT_LINE('No existe el empleado ' || p_id);
END;
/

--? Para usar un OUT necesitamos una variable donde recibir el valor
DECLARE
    v_sal NUMBER;
BEGIN
    obtener_salario(1, v_sal);
    DBMS_OUTPUT.PUT_LINE('Salario del empleado 1: ' || v_sal);
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 3.3 - Procedimiento que modifica datos (UPDATE)
--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE subir_sueldo(
    p_id_empleado IN NUMBER,
    p_porcentaje  IN NUMBER
)
IS
BEGIN
    UPDATE empleados
       SET salario = salario * (1 + p_porcentaje / 100)
     WHERE id = p_id_empleado;

    --? SQL%ROWCOUNT dice cuántas filas afectó el último UPDATE
    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Empleado no encontrado');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Subido el ' || p_porcentaje
                          || '% al empleado ' || p_id_empleado);
        COMMIT;
    END IF;
END;
/

EXEC subir_sueldo(1, 10);


--------------------------------------------------------------------------------
--* EJEMPLO 3.4 - Función que calcula y devuelve un valor
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION calcular_iva(p_precio NUMBER)
RETURN NUMBER                --! En funciones, el tipo de retorno es OBLIGATORIO
IS
    c_iva CONSTANT NUMBER := 0.21;
BEGIN
    --! RETURN es OBLIGATORIO en todas las funciones
    RETURN p_precio * c_iva;
END;
/

--? Las funciones se pueden usar dentro de un SELECT, cosa que un PROC no puede:
SELECT nombre, salario, calcular_iva(salario) AS iva_aplicado
  FROM empleados;

--? Y también dentro de un bloque PL/SQL
DECLARE
    v_total NUMBER;
BEGIN
    v_total := 1000 + calcular_iva(1000);
    DBMS_OUTPUT.PUT_LINE('Total con IVA: ' || v_total);
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 3.5 - Función que consulta la BD (calcula salario medio)
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION salario_medio_depto(p_id_depto NUMBER)
RETURN NUMBER
IS
    v_media NUMBER;
BEGIN
    SELECT AVG(salario) INTO v_media
      FROM empleados
     WHERE id_depto = p_id_depto;

    --? NVL convierte NULL en 0 (por si el depto no tiene empleados)
    RETURN NVL(v_media, 0);
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('Salario medio IT:     ' || salario_medio_depto(10));
    DBMS_OUTPUT.PUT_LINE('Salario medio Ventas: ' || salario_medio_depto(20));
END;
/


--TODO: ALUMNO → Crea una función "es_mayor_edad(p_edad)" que devuelva
--TODO:          'Sí' o 'No' según si la edad es >= 18



--==============================================================================
--==============================================================================
--*                            UT6.4 - CURSORES
--==============================================================================
--==============================================================================


--------------------------------------------------------------------------------
--* EJEMPLO 4.1 - Cursor IMPLÍCITO (Oracle lo crea solo)
--------------------------------------------------------------------------------
BEGIN
    UPDATE empleados
       SET salario = salario + 100
     WHERE id_depto = 10;

    --? Atributos del cursor implícito: SQL%ROWCOUNT, SQL%FOUND, SQL%NOTFOUND
    DBMS_OUTPUT.PUT_LINE('Filas afectadas: ' || SQL%ROWCOUNT);

    IF SQL%FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Sí hubo cambios');
    END IF;

    ROLLBACK;   --? Deshacemos para no alterar los datos de los ejemplos
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 4.2 - Cursor EXPLÍCITO con las 4 fases (DECLARE/OPEN/FETCH/CLOSE)
--------------------------------------------------------------------------------
--! Esta forma "larga" es la que más cae en examen porque demuestra que sabes
--! las 4 fases. Memorízalas: DECLARE → OPEN → FETCH → CLOSE
DECLARE
    --? FASE 1: DECLARACIÓN del cursor (la query no se ejecuta aún)
    CURSOR c_empleados IS
        SELECT id, nombre, salario
          FROM empleados
         WHERE id_depto = 10
         ORDER BY salario DESC;

    --? Variables donde guardaremos cada fila al hacer FETCH
    v_id      empleados.id%TYPE;
    v_nombre  empleados.nombre%TYPE;
    v_salario empleados.salario%TYPE;
BEGIN
    --? FASE 2: APERTURA (ahora sí se ejecuta la consulta)
    OPEN c_empleados;

    --? FASE 3: RECUPERACIÓN (FETCH dentro de un bucle)
    LOOP
        FETCH c_empleados INTO v_id, v_nombre, v_salario;

        --! IMPORTANTÍSIMO: EXIT WHEN va JUSTO DESPUÉS del FETCH
        --! Si lo pones antes, sales antes de procesar la última fila
        EXIT WHEN c_empleados%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(v_id || ' | ' || v_nombre || ' | ' || v_salario);
    END LOOP;

    --? FASE 4: CIERRE (libera recursos)
    CLOSE c_empleados;
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 4.3 - Cursor explícito usando %ROWTYPE (más limpio)
--------------------------------------------------------------------------------
DECLARE
    CURSOR c_empleados IS
        SELECT * FROM empleados WHERE id_depto = 10;

    --? Una sola variable que recoge la fila completa
    v_fila c_empleados%ROWTYPE;
BEGIN
    OPEN c_empleados;
    LOOP
        FETCH c_empleados INTO v_fila;
        EXIT WHEN c_empleados%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(v_fila.nombre || ' - ' || v_fila.salario);
    END LOOP;
    CLOSE c_empleados;
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 4.4 - Cursor FOR (versión SIMPLIFICADA - la más usada)
--------------------------------------------------------------------------------
--* El cursor FOR hace automáticamente:
--*   - DECLARE (de la variable de la fila)
--*   - OPEN
--*   - FETCH (en cada iteración)
--*   - EXIT WHEN NOTFOUND
--*   - CLOSE
BEGIN
    FOR reg IN (SELECT id, nombre, salario
                  FROM empleados
                 WHERE id_depto = 10) LOOP

        --? "reg" es como un %ROWTYPE automático
        DBMS_OUTPUT.PUT_LINE(reg.id || ' - ' || reg.nombre);
    END LOOP;
END;
/

--! TIP DE EXAMEN: Si te dan libertad, usa el FOR. Es más corto y no se te
--! puede olvidar el CLOSE.


--------------------------------------------------------------------------------
--* EJEMPLO 4.5 - Cursor PARAMETRIZADO (recibe parámetros como un proc)
--------------------------------------------------------------------------------
DECLARE
    --? Cursor que acepta un parámetro
    CURSOR c_emp_por_depto(p_depto NUMBER) IS
        SELECT id, nombre FROM empleados WHERE id_depto = p_depto;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Departamento IT ---');
    FOR reg IN c_emp_por_depto(10) LOOP
        DBMS_OUTPUT.PUT_LINE(reg.nombre);
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('--- Departamento Ventas ---');
    FOR reg IN c_emp_por_depto(20) LOOP
        DBMS_OUTPUT.PUT_LINE(reg.nombre);
    END LOOP;
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 4.6 - Cursor para hacer cálculos (subir el sueldo a todos los de IT)
--------------------------------------------------------------------------------
DECLARE
    CURSOR c_empleados IS
        SELECT id, salario FROM empleados WHERE id_depto = 10
        FOR UPDATE;        --! FOR UPDATE bloquea las filas para poder modificarlas

    v_subida NUMBER := 0;
BEGIN
    FOR reg IN c_empleados LOOP
        IF reg.salario < 2000 THEN
            v_subida := 200;
        ELSE
            v_subida := 100;
        END IF;

        UPDATE empleados
           SET salario = salario + v_subida
         WHERE CURRENT OF c_empleados;     --! CURRENT OF actualiza la fila actual

        DBMS_OUTPUT.PUT_LINE('Subido ' || v_subida || '€ a id ' || reg.id);
    END LOOP;

    ROLLBACK;   --? Deshacer cambios para no afectar otros ejemplos
END;
/


--TODO: ALUMNO → Haz un cursor que muestre los empleados ORDENADOS por salario
--TODO:          y diga si están por encima o debajo de la media de la empresa



--==============================================================================
--==============================================================================
--*                          UT6.5 - TRIGGERS (DISPARADORES)
--==============================================================================
--==============================================================================


--------------------------------------------------------------------------------
--* EJEMPLO 5.1 - Trigger BEFORE INSERT - Validar antes de insertar
--------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER tr_validar_salario
    BEFORE INSERT OR UPDATE ON empleados
    FOR EACH ROW                 --! Sin FOR EACH ROW no podríamos usar :NEW/:OLD
BEGIN
    --? :NEW se refiere al valor que se va a insertar/actualizar
    IF :NEW.salario < 1000 THEN
        --! RAISE_APPLICATION_ERROR aborta la operación con un código entre
        --! -20000 y -20999 (rango reservado para errores de usuario)
        RAISE_APPLICATION_ERROR(-20001, 'El salario no puede ser menor a 1000€');
    END IF;

    IF :NEW.salario > 100000 THEN
        RAISE_APPLICATION_ERROR(-20002, 'El salario no puede superar 100000€');
    END IF;
END;
/

--? Prueba: este INSERT debe FALLAR con el error -20001
BEGIN
    INSERT INTO empleados VALUES (99, 'Test', 500, 10, SYSDATE);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error capturado: ' || SQLERRM);
END;
/

--? Este INSERT debe FUNCIONAR
INSERT INTO empleados VALUES (99, 'Test válido', 1500, 10, SYSDATE);
DELETE FROM empleados WHERE id = 99;   --? Limpieza
COMMIT;


--------------------------------------------------------------------------------
--* EJEMPLO 5.2 - Trigger AFTER UPDATE - Auditoría
--------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER tr_auditar_salario
    AFTER UPDATE OF salario ON empleados      --! Solo cuando cambia el salario
    FOR EACH ROW
    WHEN (OLD.salario != NEW.salario)         --! Sin los : aquí (en el WHEN)
BEGIN
    --? Aquí SÍ usamos :OLD y :NEW (CON los dos puntos)
    INSERT INTO auditoria_empleados(id_empleado, accion, salario_old, salario_new, fecha)
    VALUES (:OLD.id, 'UPDATE', :OLD.salario, :NEW.salario, SYSDATE);
END;
/

--? Probamos el trigger
UPDATE empleados SET salario = 2800 WHERE id = 1;
COMMIT;

--? Vemos el registro de auditoría
SELECT * FROM auditoria_empleados;


--------------------------------------------------------------------------------
--* EJEMPLO 5.3 - Trigger con varios eventos (INSERT / UPDATE / DELETE)
--------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER tr_log_empleados
    AFTER INSERT OR UPDATE OR DELETE ON empleados
    FOR EACH ROW
BEGIN
    --? Los predicados INSERTING/UPDATING/DELETING permiten saber qué pasó
    IF INSERTING THEN
        INSERT INTO auditoria_empleados(id_empleado, accion, salario_old, salario_new, fecha)
        VALUES (:NEW.id, 'INSERT', NULL, :NEW.salario, SYSDATE);

    ELSIF UPDATING THEN
        INSERT INTO auditoria_empleados(id_empleado, accion, salario_old, salario_new, fecha)
        VALUES (:NEW.id, 'UPDATE', :OLD.salario, :NEW.salario, SYSDATE);

    ELSIF DELETING THEN
        --! En un DELETE, :NEW es NULL → usamos :OLD
        INSERT INTO auditoria_empleados(id_empleado, accion, salario_old, salario_new, fecha)
        VALUES (:OLD.id, 'DELETE', :OLD.salario, NULL, SYSDATE);
    END IF;
END;
/

--? Probamos los 3 eventos
INSERT INTO empleados VALUES (100, 'Empleado prueba', 2000, 30, SYSDATE);
UPDATE empleados SET salario = 2500 WHERE id = 100;
DELETE FROM empleados WHERE id = 100;
COMMIT;

SELECT * FROM auditoria_empleados ORDER BY id DESC;


--------------------------------------------------------------------------------
--* EJEMPLO 5.4 - Tabla de resumen :NEW vs :OLD según evento
--------------------------------------------------------------------------------
--?  ┌──────────┬───────────────┬───────────────┐
--?  │ Operación│ :OLD          │ :NEW          │
--?  ├──────────┼───────────────┼───────────────┤
--?  │ INSERT   │ NULL          │ Valor a meter │
--?  │ UPDATE   │ Valor antiguo │ Valor nuevo   │
--?  │ DELETE   │ Valor a borrar│ NULL          │
--?  └──────────┴───────────────┴───────────────┘


--------------------------------------------------------------------------------
--* EJEMPLO 5.5 - Activar / desactivar / borrar trigger
--------------------------------------------------------------------------------
--? Desactivar (no lo borra, simplemente no se dispara)
ALTER TRIGGER tr_log_empleados DISABLE;

--? Volver a activarlo
ALTER TRIGGER tr_log_empleados ENABLE;

--? Eliminar por completo
-- DROP TRIGGER tr_log_empleados;


--TODO: ALUMNO → Crea un trigger que NO permita borrar empleados del depto 10 (IT)



--==============================================================================
--==============================================================================
--*                            UT6.6 - PAQUETES
--==============================================================================
--==============================================================================


--------------------------------------------------------------------------------
--* EJEMPLO 6.1 - Especificación del paquete (lo público)
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE pkg_empleados IS

    --? Constante pública (accesible desde fuera con pkg_empleados.c_salario_minimo)
    c_salario_minimo CONSTANT NUMBER := 1000;

    --? Procedimientos públicos - solo la firma, sin cuerpo
    PROCEDURE contratar(p_id NUMBER, p_nombre VARCHAR2, p_salario NUMBER, p_depto NUMBER);
    PROCEDURE despedir(p_id NUMBER);
    PROCEDURE listar_por_depto(p_depto NUMBER);

    --? Funciones públicas
    FUNCTION salario_medio RETURN NUMBER;
    FUNCTION contar_empleados(p_depto NUMBER) RETURN NUMBER;
    FUNCTION existe_empleado(p_id NUMBER) RETURN BOOLEAN;

END pkg_empleados;
/


--------------------------------------------------------------------------------
--* EJEMPLO 6.2 - Cuerpo del paquete (la implementación)
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY pkg_empleados IS

    --! Variable PRIVADA: solo accesible dentro del paquete
    --! No aparece en la especificación
    v_contador_acciones NUMBER := 0;

    --! Procedimiento PRIVADO: tampoco aparece en la especificación
    PROCEDURE registrar_accion(p_accion VARCHAR2) IS
    BEGIN
        v_contador_acciones := v_contador_acciones + 1;
        DBMS_OUTPUT.PUT_LINE('Acción #' || v_contador_acciones || ': ' || p_accion);
    END registrar_accion;

    --------------------------------------------------------------------
    --? Implementación de los procedimientos públicos
    --------------------------------------------------------------------
    PROCEDURE contratar(p_id NUMBER, p_nombre VARCHAR2, p_salario NUMBER, p_depto NUMBER) IS
    BEGIN
        IF p_salario < c_salario_minimo THEN
            RAISE_APPLICATION_ERROR(-20001,
                'Salario por debajo del mínimo (' || c_salario_minimo || ')');
        END IF;

        INSERT INTO empleados(id, nombre, salario, id_depto, fecha_alta)
        VALUES (p_id, p_nombre, p_salario, p_depto, SYSDATE);

        registrar_accion('Contratado ' || p_nombre);   --? Llamada a proc privado
    END contratar;

    PROCEDURE despedir(p_id NUMBER) IS
    BEGIN
        IF NOT existe_empleado(p_id) THEN
            RAISE_APPLICATION_ERROR(-20002, 'El empleado no existe');
        END IF;

        DELETE FROM empleados WHERE id = p_id;
        registrar_accion('Despedido id=' || p_id);
    END despedir;

    PROCEDURE listar_por_depto(p_depto NUMBER) IS
    BEGIN
        FOR reg IN (SELECT id, nombre, salario
                      FROM empleados
                     WHERE id_depto = p_depto) LOOP
            DBMS_OUTPUT.PUT_LINE(reg.id || ' - ' || reg.nombre || ' - ' || reg.salario);
        END LOOP;
    END listar_por_depto;

    --------------------------------------------------------------------
    --? Implementación de las funciones públicas
    --------------------------------------------------------------------
    FUNCTION salario_medio RETURN NUMBER IS
        v_media NUMBER;
    BEGIN
        SELECT AVG(salario) INTO v_media FROM empleados;
        RETURN NVL(v_media, 0);
    END salario_medio;

    FUNCTION contar_empleados(p_depto NUMBER) RETURN NUMBER IS
        v_cont NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_cont
          FROM empleados
         WHERE id_depto = p_depto;
        RETURN v_cont;
    END contar_empleados;

    FUNCTION existe_empleado(p_id NUMBER) RETURN BOOLEAN IS
        v_cont NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_cont FROM empleados WHERE id = p_id;
        RETURN v_cont > 0;
    END existe_empleado;

END pkg_empleados;
/


--------------------------------------------------------------------------------
--* EJEMPLO 6.3 - Cómo usar el paquete
--------------------------------------------------------------------------------
BEGIN
    --? Acceso con notación de punto: paquete.elemento
    pkg_empleados.contratar(200, 'Nuevo Empleado', 1800, 10);

    DBMS_OUTPUT.PUT_LINE('Salario medio: '   || pkg_empleados.salario_medio);
    DBMS_OUTPUT.PUT_LINE('Empleados IT: '    || pkg_empleados.contar_empleados(10));
    DBMS_OUTPUT.PUT_LINE('---');

    pkg_empleados.listar_por_depto(10);

    --? Usar la constante pública
    DBMS_OUTPUT.PUT_LINE('Sal. mínimo: ' || pkg_empleados.c_salario_minimo);

    --? Limpieza
    pkg_empleados.despedir(200);
END;
/


--------------------------------------------------------------------------------
--* EJEMPLO 6.4 - Ver qué hay compilado en la BD
--------------------------------------------------------------------------------
--? Listar todos los paquetes del esquema actual
SELECT object_name, object_type, status
  FROM user_objects
 WHERE object_type IN ('PACKAGE', 'PACKAGE BODY')
 ORDER BY object_name;

--? Si un paquete tiene errores: SHOW ERRORS PACKAGE BODY pkg_empleados;


--------------------------------------------------------------------------------
--* EJEMPLO 6.5 - Eliminar paquete
--------------------------------------------------------------------------------
--? Borra solo el cuerpo (deja la especificación)
-- DROP PACKAGE BODY pkg_empleados;

--? Borra el paquete entero
-- DROP PACKAGE pkg_empleados;


--TODO: ALUMNO → Añade al paquete una función "salario_max_depto(p_depto)"
--TODO:          que devuelva el salario máximo del departamento



--==============================================================================
--==============================================================================
--*                     EJERCICIOS DE REPASO ESTILO EXAMEN
--==============================================================================
--==============================================================================


--------------------------------------------------------------------------------
--TODO: EJERCICIO 1 - Procedimiento "calcular_extra"
--------------------------------------------------------------------------------
--TODO: Crea un procedimiento que reciba el id de un empleado y le aplique
--TODO: una bonificación:
--TODO:   - Si el salario < 2000 → +15%
--TODO:   - Si el salario entre 2000 y 3000 → +10%
--TODO:   - Si el salario > 3000 → +5%
--TODO: Muestra por pantalla el sueldo antes y después.


--------------------------------------------------------------------------------
--TODO: EJERCICIO 2 - Función "categoria_salarial"
--------------------------------------------------------------------------------
--TODO: Crea una función que reciba un salario y devuelva:
--TODO:   - 'BAJO'  si < 1500
--TODO:   - 'MEDIO' si entre 1500 y 2500
--TODO:   - 'ALTO'  si > 2500
--TODO: Pruébala con un SELECT que muestre nombre, salario y categoría.


--------------------------------------------------------------------------------
--TODO: EJERCICIO 3 - Cursor con cálculos
--------------------------------------------------------------------------------
--TODO: Con un cursor explícito (las 4 fases), recorre todos los empleados
--TODO: y calcula:
--TODO:   - Número total de empleados
--TODO:   - Suma total de salarios
--TODO:   - Salario medio
--TODO: Muestra los 3 valores al final.


--------------------------------------------------------------------------------
--TODO: EJERCICIO 4 - Trigger de auditoría completo
--------------------------------------------------------------------------------
--TODO: Crea un trigger AFTER INSERT/UPDATE/DELETE sobre empleados que registre
--TODO: en una tabla "historial" estos campos:
--TODO:   - usuario (USER de Oracle)
--TODO:   - fecha (SYSDATE)
--TODO:   - tabla afectada ('EMPLEADOS')
--TODO:   - operación (INSERT/UPDATE/DELETE)
--TODO:   - id del empleado afectado


--------------------------------------------------------------------------------
--TODO: EJERCICIO 5 - Paquete completo
--------------------------------------------------------------------------------
--TODO: Crea un paquete "pkg_nominas" con:
--TODO:   - Función pago_anual(p_id) → devuelve salario × 14
--TODO:   - Función pago_neto(p_id)  → resta el 20% de IRPF al pago anual
--TODO:   - Procedimiento generar_nominas → recorre todos los empleados e imprime
--TODO:     nombre, pago anual y pago neto



--==============================================================================
--*                              LIMPIEZA FINAL
--==============================================================================
--! Descomenta si quieres limpiar todo al terminar

-- DROP PACKAGE pkg_empleados;
-- DROP TRIGGER tr_validar_salario;
-- DROP TRIGGER tr_auditar_salario;
-- DROP TRIGGER tr_log_empleados;
-- DROP FUNCTION calcular_iva;
-- DROP FUNCTION salario_medio_depto;
-- DROP PROCEDURE saludar;
-- DROP PROCEDURE obtener_salario;
-- DROP PROCEDURE subir_sueldo;
-- DROP TABLE auditoria_empleados;
-- DROP TABLE empleados;
-- DROP TABLE departamentos;


--==============================================================================
--*                              FIN DEL ARCHIVO
--*  ¡Suerte con el examen! Repasa el .md adjunto si tienes dudas teóricas.
--==============================================================================
