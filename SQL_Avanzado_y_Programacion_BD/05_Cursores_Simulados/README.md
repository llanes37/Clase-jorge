# Bloque 05 · Cursores

> Motor: **MySQL 8 obligatorio** (cursores nativos) · SQLite solo para simulación conceptual
> Archivo principal: `cursores_simulados.sql`

Un **cursor** permite procesar fila a fila los resultados de una consulta dentro de un procedimiento almacenado. Es el equivalente al `while(rs.next())` de Java, pero ejecutándose dentro de la propia base de datos.

---

## Objetivos

1. Entender qué es un cursor y cuándo usarlo
2. Declarar, abrir, recorrer y cerrar un cursor en MySQL
3. Controlar el fin del cursor con un `HANDLER`
4. Relacionar el cursor MySQL con el `ResultSet` de Java
5. Simular el comportamiento en SQLite con SELECT + ORDER BY

---

## Teoría esencial

### Cursor completo en MySQL

```sql
DELIMITER $$
CREATE PROCEDURE RecorrerEmpleados()
BEGIN
    DECLARE done    INT DEFAULT 0;
    DECLARE v_id    INT;
    DECLARE v_nom   VARCHAR(100);
    DECLARE v_sal   DECIMAL(10,2);

    DECLARE cur CURSOR FOR
        SELECT id, nombre, salario FROM empleados ORDER BY id;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cur;

    bucle: LOOP
        FETCH cur INTO v_id, v_nom, v_sal;
        IF done THEN LEAVE bucle; END IF;

        -- Aquí va la lógica por cada fila
        -- Ejemplo: UPDATE, INSERT en log, cálculos...
    END LOOP;

    CLOSE cur;
END $$
DELIMITER ;
```

### Equivalente en Java (para el alumno de Acceso a Datos)

```java
ResultSet rs = stmt.executeQuery("SELECT id, nombre, salario FROM empleados ORDER BY id");
while (rs.next()) {
    int id      = rs.getInt("id");
    String nom  = rs.getString("nombre");
    double sal  = rs.getDouble("salario");
    // lógica por fila
}
rs.close();
```

### Simulación en SQLite (sin cursor nativo)

```sql
-- Procesamos fila a fila mentalmente o desde la aplicación
SELECT id, nombre, salario
FROM empleados
ORDER BY id
LIMIT 1 OFFSET 0;  -- fila 1

LIMIT 1 OFFSET 1;  -- fila 2 ...
```

---

## ¿Cuándo usar un cursor?

- Cuando necesitas lógica distinta por cada fila (actualizar, calcular, insertar en log...)
- Cuando no puedes resolverlo con un UPDATE o INSERT ... SELECT único
- En procesos ETL pequeños dentro de la BD

> En la mayoría de casos, un `UPDATE ... WHERE` o un `INSERT ... SELECT` es más eficiente que un cursor. Usa cursores solo cuando la lógica por fila lo justifique.