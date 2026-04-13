# NIVEL 4 - EXAMEN FINAL

Este nivel está reservado para ejercicios largos, de varias capas y más
parecidos a un examen final completo.

Aquí ya no basta con dominar una sola clase y un array. El alumno tiene que ser
capaz de manejar estructuras más serias, relaciones entre clases y una secuencia
larga de operaciones.

---

## Qué se trabaja aquí

En este nivel aparecen de forma más clara:

- varias clases relacionadas
- agregación o composición
- colecciones como `HashSet` y `HashMap`
- clases gestoras
- operaciones globales sobre una estructura completa
- eliminación, búsqueda y actualización de elementos

Este nivel no está pensado para introducir teoría nueva, sino para consolidar y
mezclar todo lo anterior en ejercicios más largos y más cercanos al examen final
real.

---

## Cómo se debe usar este nivel

La forma recomendada de trabajarlo es:

1. leer el enunciado completo
2. identificar todas las clases necesarias
3. resolver primero las clases de dominio
4. construir después la clase gestora
5. dejar el `main` para el final
6. comprobar una a una las operaciones pedidas

Si el alumno intenta arrancar por la parte final o por la secuencia principal,
normalmente se desordena. En este nivel es fundamental resolver por capas.

---

## Ejercicios actuales

- `RED_SOCIAL`
- `ORDENADORES`

### `RED_SOCIAL`

Este ejercicio entrena especialmente:

- varias clases relacionadas entre sí
- `HashMap<Usuario, ListaDeFotos>`
- gestión de likes
- etiquetado con `HashSet`
- eliminación por condiciones
- sobrecarga de métodos

Es muy útil como referencia cuando quieres trabajar búsquedas y operaciones
globales recorriendo valores de un `HashMap`.

### `ORDENADORES`

Este ejercicio entrena especialmente:

- clase principal con agregación de periféricos
- uso de `HashSet` para evitar duplicados
- clase gestora de ordenadores
- eliminación mientras se recorre
- actualización de datos internos del objeto
- secuencia larga de acciones tipo examen

Es una referencia muy buena para ejercicios en los que el alumno necesita
coordinar varias clases y varias operaciones encadenadas.

---

## Qué contiene cada carpeta de ejercicio

Cada ejercicio de este nivel debe mantener el mismo formato:

- archivo base `.java`
  Plantilla de trabajo.
- guía `.md`
  Explica estrategia, orden y puntos conflictivos.
- enunciado `.md`
  Formato de examen real.
- archivo `_Solucion.java`
  Solución completa y comentada.

Mantener esta estructura es importante para que el bloque pueda crecer sin
volverse caótico.

---

## Referencia para futuros ejercicios

Cuando metas nuevos ejercicios en este nivel, lo ideal es que entren aquí solo
si cumplen alguna de estas condiciones:

- varias clases conectadas
- uso de `HashSet` o `HashMap`
- clase gestora separada
- varias operaciones encadenadas
- dificultad claramente superior al patrón de array de objetos simple

Si un ejercicio solo usa una clase y un array de objetos, seguramente pertenece
antes al nivel 2 o al nivel 3.

---

## Criterio de orden dentro del nivel

Para que este nivel siga creciendo bien, conviene mantener siempre esta
organización:

- una carpeta por ejercicio
- cuatro archivos base dentro de cada carpeta
- nombres claros y consistentes

La idea es que, al abrir el nivel, se entienda rápidamente qué ejercicios hay y
para qué sirve cada uno.
