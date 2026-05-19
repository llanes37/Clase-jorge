# 🧩 Bloque 03: Consultas y Lógica en SQL

En este tercer bloque profundizaremos en **consultas avanzadas** y cómo integrar **lógica de programación** directamente en SQL. Veremos:

- SELECTs básicos y con alias
- Filtrado condicional con WHERE
- Búsquedas de texto con LIKE
- Filtrado múltiple con IN
- Rango de fechas con BETWEEN
- Condicionales con CASE (simulación de IF/ELSE)
- Funciones de agregación y agrupaciones con GROUP BY
- Uniones de tablas (JOIN)

---

## 🎯 Objetivos

1. **Dominar SELECT** en distintos escenarios (todas las columnas, columnas específicas, alias).
2. **Filtrar datos** usando operadores de comparación, patrones y rangos.
3. **Aplicar lógica condicional** dentro de consultas mediante CASE.
4. **Realizar agregaciones** para obtener sumas, promedios y conteos agrupados.
5. **Combinar tablas** para consultar datos relacionados con JOIN.

---

## 📘 Teoría

### SELECT y Alias

- `SELECT * FROM empleados;`  
  Obtiene todos los campos de la tabla.
- `SELECT nombre AS empleado FROM empleados;`  
  Crea un alias de columna para facilitar la lectura.

### Filtrado con WHERE

- Comparaciones básicas: `=, >, <, >=, <=, !=`
- Uso de patrones: `LIKE '%texto%'` para coincidencias de subcadenas.
- Listas de valores: `IN (valor1, valor2, ...)`.
- Rangos: `BETWEEN fecha1 AND fecha2`.

### Lógica con CASE

```sql
SELECT nombre,
  CASE
    WHEN salario < 1000 THEN 'Bajo'
    WHEN salario BETWEEN 1000 AND 2000 THEN 'Medio'
    ELSE 'Alto'
  END AS nivel
FROM empleados;
```

- Simula estructuras IF/ELSE directamente en la consulta.

### Agregación y GROUP BY

- `COUNT(*)` cuenta filas.
- `AVG(salario)` calcula promedio.
- Agrupar resultados por una o varias columnas.

### JOIN entre tablas

- `INNER JOIN`: solo filas que coinciden en ambas tablas.
- `LEFT JOIN`: todas las filas de la tabla izquierda, y las de la derecha que coinciden.
- Sintaxis: `FROM tablaA JOIN tablaB ON tablaA.col = tablaB.col`.

---

## 🧪 Ejercicios Prácticos

1. **Alias y Filtrado**  
   - Lista nombres de clientes que usan correo de Gmail.
2. **CASE**  
   - Añade un nuevo nivel salarial “Muy Alto” para salarios superiores a 2500.
3. **GROUP BY y HAVING**  
   - Filtra departamentos con salario promedio > 1500.
4. **JOIN**  
   - Muestra empleados junto a su departamento solo si tienen salario > 1500.

---

## ✍️ Sugerencias de Práctica

- Combina varias condiciones en WHERE (`AND`, `OR`).
- Usa subconsultas en SELECT o WHERE.
- Experimenta con funciones de fecha (`YEAR`, `MONTH`) si tu motor SQL las soporta.
- Prueba distintos tipos de JOIN (RIGHT, FULL) si tu base lo permite.

---

> **Tip:** Ejecuta cada bloque por separado y revisa el resultado antes de continuar.
