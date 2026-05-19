# 🔁 Bloque 05: Cursores Simulados

En este bloque aprenderás a **recorrer registros uno a uno**, procesando cada fila con lógica condicional como si usaras un cursor real. Aunque SQLite no soporta cursores nativos, entenderás el concepto y cómo se traduce en otros motores.

---

## 🎯 Objetivos

1. Simular el recorrido fila a fila usando `SELECT` con `ORDER BY` y `LIMIT`.
2. Aplicar **lógica condicional** en la consulta para procesar cada registro.
3. Conocer la sintaxis de **cursores reales** en MySQL dentro de procedimientos.
4. Practicar cómo **fetch**, **update** y **controlar** el fin del cursor.
5. Preparar la transición para usar cursores en lenguajes externos (Java, Python).

---

## 📘 Teoría

### ¿Qué es un cursor?
- Un **cursor** es un mecanismo que permite **recorrer** fila a fila los resultados de una consulta.
- Usado cuando necesitas aplicar **procesamiento complejo** por registro (por ejemplo, cálculos, actualizaciones, validaciones).

### Simulación en SQLite
- **Ordena** tu `SELECT` con `ORDER BY`.
- Usa `LIMIT` para simular `FETCH FIRST N`.
- Procesa cada fila externamente (Java: `while(rs.next())`, Python: `for row in cursor`).

### Cursor real en MySQL
- Definido dentro de un **procedimiento** con:
  ```sql
  DECLARE cursor_name CURSOR FOR SELECT ...;
  OPEN cursor_name;
  FETCH cursor_name INTO ...;
  CLOSE cursor_name;
  ```
- Utiliza **handlers** para detectar fin de datos.

---

## 🧪 Ejemplos Prácticos

1. **SELECT ordenado**  
   ```sql
   SELECT id, nombre, salario
   FROM empleados
   ORDER BY salario DESC;
   ```
   - Primer elemento: mayor salario  
   - Simula `FETCH NEXT`  

2. **Top-N (LIMIT)**  
   ```sql
   SELECT id, nombre, salario
   FROM empleados
   ORDER BY salario DESC
   LIMIT 3;
   ```
   - Simula `FETCH FIRST 3 ROWS`  

3. **Estado por fila**  
   ```sql
   SELECT id, nombre,
     CASE
       WHEN salario < 1000 THEN 'BAJO'
       ELSE 'OK'
     END AS estado
   FROM empleados
   ORDER BY salario ASC;
   ```
   - Procesa condición por cada registro  

4. **Procedimiento con cursor (MySQL)**  
   ```sql
   DELIMITER $$
   CREATE PROCEDURE ProcesarEmpleados()
   BEGIN
     DECLARE fin INT DEFAULT FALSE;
     DECLARE emp_id INT;
     DECLARE emp_nom VARCHAR(100);
     DECLARE emp_sal DECIMAL(10,2);
     DECLARE cursor_emp CURSOR FOR
       SELECT id, nombre, salario FROM empleados ORDER BY id;
     DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = TRUE;
     OPEN cursor_emp;
     bucle: LOOP
       FETCH cursor_emp INTO emp_id, emp_nom, emp_sal;
       IF fin THEN LEAVE bucle; END IF;
       -- Ejemplo: aumentar salario un 5%
       UPDATE empleados SET salario = emp_sal * 1.05 WHERE id = emp_id;
     END LOOP;
     CLOSE cursor_emp;
   END $$
   DELIMITER ;
   ```
   - Invoca con `CALL ProcesarEmpleados();`

---

## ✍️ Tareas Sugeridas

- Modifica el procedimiento para **disminuir** salario de empleados con sueldo superior a 2000.
- Crea un cursor que recorra solo clientes con email de Gmail.
- Simula el cursor en Java:
  ```java
  while(rs.next()) {
    System.out.println(rs.getString("nombre"));
  }
  ```
- Experimenta con **subcursos** dentro del bucle (por ejemplo, actualizar otra tabla).

---

> **Tip:** Después de ejecutar el procedimiento de MySQL, verifica los cambios con un `SELECT * FROM empleados;`.

