# Bloque 99 · Ejercicio Final — Examen Práctico

> Motor: **MySQL 8 obligatorio** · Archivo: `examen_practico_med.sql`
> Tiempo estimado: 90 minutos

Este examen integra todos los conceptos del curso en un caso real de gestión de empleados y clientes. Es el ejercicio de evaluación final.

---

## Antes de empezar

1. Arranca Docker: `docker start repaso-bbdd`
2. Ejecuta `00_reset_completo.sql` para partir del estado limpio
3. Abre `examen_practico_med.sql`
4. Escribe tu nombre y la fecha en el encabezado
5. Resuelve cada sección sin borrar los enunciados

---

## Estructura del examen (100 puntos)

| Sección | Contenido | Puntos |
|---|---|---|
| 1 | SELECT y lógica (CASE, GROUP BY, filtros) | 30 |
| 2 | Procedimientos almacenados con parámetros | 25 |
| 3 | Cursores dentro de procedimientos | 20 |
| 4 | Triggers BEFORE/AFTER con auditoría | 25 |

---

## Criterios de corrección

- **Sintaxis correcta** (sin errores de ejecución): 40%
- **Resultado esperado** (datos correctos): 40%
- **Comentarios y justificaciones**: 20%

---

## Entrega

Guarda el archivo con el nombre `examen_TUNOMBRE.sql` y entrégalo al profesor. Debe ejecutarse de principio a fin sin errores sobre una BD recién inicializada con `00_reset_completo.sql`.