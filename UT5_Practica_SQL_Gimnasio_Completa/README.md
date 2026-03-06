# Practica SQL Completa - Gimnasio

## 1. Enunciado

Esta practica parte del modelo de base de datos de un gimnasio y esta pensada para trabajar:

- creacion de tablas,
- carga de datos,
- relaciones entre tablas,
- consultas `SELECT`,
- `JOIN`,
- `LEFT JOIN`,
- `GROUP BY`,
- verificaciones finales.

Tambien se incluye una pagina principal `Plataforma_SQL.html` para que el alumno pueda:

- ver los scripts en grande,
- copiar consultas al editor,
- ejecutar consultas de lectura en un simulador local,
- visualizar el esquema de la base de datos.

**Nota sobre el PDF**

- PDF fuente incluido: `UT 5 -- C - Tarea 6 - PS.pdf`
- Texto exacto de todas las consultas del PDF: **No definido / Requiere decision**
- Motivo: el PDF se ha podido copiar a la carpeta, pero su texto no se deja extraer bien de forma automatica.
- Solucion aplicada: se ha creado una bateria completa de consultas guiadas basada en el modelo real entregado.

## 2. Modelado

### Tablas principales

- `talumnos_g`: guarda los alumnos del gimnasio.
- `tprofesores_g`: guarda los profesores.
- `tactividades_g`: guarda las actividades y el profesor responsable.
- `talu_activ`: relaciona alumnos con actividades realizadas en una fecha.
- `talu_activ_demand`: relaciona alumnos con actividades demandadas.
- `trecibos_g`: tabla preparada para pagos, creada pero sin datos de ejemplo.

### Relaciones

- Un profesor puede impartir varias actividades.
- Un alumno puede realizar varias actividades.
- Una actividad puede ser realizada por varios alumnos.
- Un alumno puede demandar varias actividades.
- Una actividad puede ser demandada por varios alumnos.
- Un alumno puede tener varios recibos.

### Idea didactica del modelo

Este modelo es muy bueno para clase porque permite practicar:

- claves primarias simples,
- claves primarias compuestas,
- claves foraneas,
- relaciones `1:N`,
- relaciones `N:M`,
- comparaciones entre actividad realizada y actividad demandada.

## 3. Explicacion de metodos y archivos

### `01_creacion_tablas_gimnasio.sql`

Que hace:
crea todas las tablas y primero limpia el esquema para poder repetir la practica.

Por que se implementa asi:
separa la estructura de los datos, que es justo lo que un alumno debe aprender en SQL.

Caso limite:
si el alumno no tiene permisos o trabaja en otro motor distinto de Oracle, algunas sentencias pueden fallar.

### `02_carga_datos_gimnasio.sql`

Que hace:
inserta los datos del gimnasio en el orden correcto.

Por que se implementa asi:
primero inserta tablas padre y despues tablas hijas para no romper claves foraneas.

Caso limite:
si se lanza dos veces sin recrear las tablas, apareceran claves duplicadas.

### `03_consultas_gimnasio_alumno.sql`

Que hace:
deja ejercicios guiados para que el alumno escriba y pruebe consultas.

Por que se implementa asi:
la practica no debe ser solo “leer soluciones”, sino construir consultas paso a paso.

Caso limite:
si el alumno no distingue bien las tablas intermedias, intentara hacer JOIN directos donde no corresponde.

### `04_consultas_gimnasio_soluciones.sql`

Que hace:
incluye soluciones completas de referencia.

Por que se implementa asi:
sirve para correccion, repaso y comparacion final.

Caso limite:
la solucion 14 depende de recibos; como no hay datos, puede devolver cero filas.

### `05_verificaciones_gimnasio.sql`

Que hace:
comprueba rapidamente conteos y relaciones.

Por que se implementa asi:
permite detectar si algo fallo antes de empezar a consultar.

Caso limite:
si falta un `COMMIT` o un `INSERT`, los conteos ya no cuadraran.

### `Plataforma_SQL.html`

Que hace:
actua como visor docente y simulador rapido en navegador con paneles, editor y resultados.

Por que se implementa asi:
ayuda mucho en clase para proyectar scripts, copiar consultas y practicar sin depender siempre de Oracle abierto, pero manteniendo el orden del temario real.

Caso limite:
el simulador del navegador sirve para `SELECT` y practica de lectura; la validacion real de Oracle se debe hacer en SQL Developer o herramienta equivalente.

## 4. Edge cases

- Ejecutar la carga antes de crear tablas.
- Ejecutar la carga dos veces seguidas.
- Hacer un `JOIN` sin pasar por la tabla intermedia `talu_activ`.
- Filtrar por `NULL` con `=` en vez de `IS NULL`.
- Olvidar columnas en `GROUP BY`.
- Suponer que el orden de salida es automatico sin usar `ORDER BY`.
- Pensar que el simulador web sustituye al motor Oracle.

## 5. Como ejecutar

### Opcion recomendada: Oracle SQL Developer

1. Abrir una conexion Oracle.
2. Ejecutar `01_creacion_tablas_gimnasio.sql`.
3. Ejecutar `02_carga_datos_gimnasio.sql`.
4. Ejecutar `05_verificaciones_gimnasio.sql`.
5. Resolver `03_consultas_gimnasio_alumno.sql`.
6. Comparar con `04_consultas_gimnasio_soluciones.sql`.

### Opcion de apoyo: navegador

1. Abrir esta carpeta con un servidor local.
2. Abrir `Plataforma_SQL.html`.
3. Pulsar `Reiniciar base del navegador`.
4. Elegir un script del lateral.
5. Pasarlo al editor o usar una consulta rapida.
6. Pulsar `Ejecutar consulta del editor`.

### Ejemplo de entrada y salida esperada

Entrada:

```sql
SELECT COUNT(*) AS total_alumnos FROM talumnos_g;
```

Salida esperada:

```text
8
```

Entrada:

```sql
SELECT idactividad, descripcion, activa
FROM tactividades_g
WHERE activa = 'n'
ORDER BY descripcion;
```

Salida esperada:

```text
5 | JUST PUMP | n
6 | JUDO | n
```

## 6. 3 ejercicios extra

### Extra 1

Enunciado:
mostrar los alumnos cuyo telefono empieza por `956`.

Entrada esperada:

```sql
SELECT idalumno, nombre, apellidos, telefono
FROM talumnos_g
WHERE telefono LIKE '956%';
```

Salida esperada:
deben aparecer Luisa, Clara, Rita y Rafael.

### Extra 2

Enunciado:
mostrar las actividades que nadie ha demandado.

Entrada esperada:

```sql
SELECT a.idactividad, a.descripcion
FROM tactividades_g a
LEFT JOIN talu_activ_demand d
  ON d.idactividad = a.idactividad
WHERE d.idactividad IS NULL
ORDER BY a.descripcion;
```

Salida esperada:
deben salir las actividades que no aparecen en la tabla de demandas.

### Extra 3

Enunciado:
mostrar los alumnos que realizan mas de una actividad.

Entrada esperada:

```sql
SELECT al.nombre, al.apellidos, COUNT(*) AS total
FROM talumnos_g al
JOIN talu_activ ta
  ON ta.idalumno = al.idalumno
GROUP BY al.nombre, al.apellidos
HAVING COUNT(*) > 1
ORDER BY total DESC, al.apellidos;
```

Salida esperada:
deben aparecer varios alumnos, porque algunos estan en dos o mas actividades.

## 7. Errores tipicos

- Escribir `WHERE activa = s` en lugar de `WHERE activa = 's'`.
- Unir `talumnos_g` con `tactividades_g` sin pasar por `talu_activ`.
- Olvidar `COMMIT` despues de la carga.
- Escribir una consulta agregada sin `GROUP BY`.
- Usar el HTML como si fuera Oracle real.
- No revisar antes los conteos de `05_verificaciones_gimnasio.sql`.

## 8. Archivos incluidos

- `01_creacion_tablas_gimnasio.sql`
- `02_carga_datos_gimnasio.sql`
- `03_consultas_gimnasio_alumno.sql`
- `04_consultas_gimnasio_soluciones.sql`
- `05_verificaciones_gimnasio.sql`
- `Plataforma_SQL.html`
- `Curso_SQL_Nivel_0_Academia.html`
- `index.html` (redirige a la pagina principal)
- `styles.css`
- `app.js`
- `assets/browser_setup.sql`
- `assets/esquema_gimnasio.svg`
- `UT 5 -- C - Tarea 6 - PS.pdf`
