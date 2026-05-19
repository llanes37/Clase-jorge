# Bloque 03 · SELECT y Lógica en SQL

> Motor: SQLite o MySQL · Archivo principal: `select_logica.sql`

En este bloque profundizas en consultas avanzadas e integras lógica de programación directamente en SQL. Es el puente entre saber SELECT básico y entender cómo programar dentro de una base de datos.

---

## Objetivos

1. Escribir SELECTs con alias, columnas calculadas y expresiones
2. Filtrar con `WHERE`, `LIKE`, `IN`, `BETWEEN`, `IS NULL`
3. Aplicar lógica condicional con `CASE` (equivalente al if/else)
4. Agrupar resultados con `GROUP BY` y `HAVING`
5. Unir tablas con `JOIN`

---

## Teoría resumida

### CASE — el if/else de SQL

```sql
SELECT nombre,
  CASE
    WHEN salario < 1000 THEN 'Bajo'
    WHEN salario BETWEEN 1000 AND 2000 THEN 'Medio'
    ELSE 'Alto'
  END AS nivel_salario
FROM empleados;
```

> Siempre termina con `END`. El `ELSE` es opcional pero recomendable.

### GROUP BY + HAVING

```sql
-- Cuántos empleados hay por departamento (solo los que tienen más de 1)
SELECT departamento_id, COUNT(*) AS total
FROM empleados
GROUP BY departamento_id
HAVING COUNT(*) > 1;
```

> `WHERE` filtra filas antes de agrupar. `HAVING` filtra grupos después de agrupar.

### JOIN

```sql
SELECT e.nombre, d.nombre AS departamento
FROM empleados e
JOIN departamentos d ON e.departamento_id = d.id;
```

---

## Ejercicios del archivo select_logica.sql

| Nº | Concepto | Dificultad |
|---|---|---|
| 1 | SELECT básico | Fácil |
| 2 | Alias de columna y tabla | Fácil |
| 3 | WHERE con comparación | Fácil |
| 4 | LIKE para búsqueda de texto | Media |
| 5 | IN y condiciones múltiples | Media |
| 6 | BETWEEN con fechas | Media |
| 7 | CASE para clasificar | Media |
| 8 | GROUP BY + HAVING | Media-Alta |
| 9 | JOIN entre tablas | Alta |

---

## Cómo ejecutar

1. Asegúrate de haber lanzado `00_reset_completo.sql` antes
2. Abre `select_logica.sql`
3. Selecciona la consulta que quieras probar → clic derecho → **Run on active connection**
4. Modifica los valores de los filtros y observa cómo cambia el resultado
