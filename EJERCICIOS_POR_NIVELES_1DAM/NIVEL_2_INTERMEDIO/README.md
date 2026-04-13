# NIVEL 2 - INTERMEDIO

Este nivel introduce el patrón más típico de Programación en 1 DAM:

- una clase
- varios objetos
- un array de objetos
- métodos para recorrer, contar, buscar y calcular

Aquí el alumno ya no trabaja solo con un objeto aislado. Empieza a pensar en un
conjunto de elementos y en operaciones que se hacen sobre todos ellos.

---

## Qué se trabaja aquí

En este nivel se entrena:

- diseñar una clase con sentido
- crear varios objetos del mismo tipo
- guardarlos dentro de un array
- recorrer el array con métodos
- calcular medias, contar elementos y buscar coincidencias
- separar mejor la lógica del `main`

Este es probablemente el nivel más útil para preparar el examen típico de 1 DAM,
porque aquí aparece el esqueleto que más se repite luego en clase:

- crear clase
- crear objetos
- almacenarlos
- procesarlos con métodos

---

## Cómo se debe usar este nivel

Orden recomendado:

1. leer el enunciado tipo examen
2. identificar qué parte pertenece a la clase y qué parte al `main`
3. construir la clase primero
4. crear después el array y los objetos
5. hacer luego los métodos de proceso
6. comparar al final con la solución comentada

Si el alumno intenta empezar directamente por el `main`, suele perderse. La
mejor costumbre aquí es resolver por capas.

---

## Ejercicio actual

- `ALUMNO_ARRAY_METODOS`

Este ejercicio está bien colocado en este nivel porque obliga a dominar el
patrón completo sin que todavía intervengan varias clases o colecciones más
complejas.

Se trabaja especialmente:

- mostrar todos los elementos
- contar por condición
- calcular una media
- buscar un objeto
- localizar el mejor o el mayor

---

## Qué contiene cada carpeta de ejercicio

Cada ejercicio de este nivel debe tener:

- archivo base `.java`
  Plantilla para resolver.
- guía `.md`
  Explicación de orden, estrategia y puntos conflictivos.
- enunciado `.md`
  Formato más parecido al examen.
- archivo `_Solucion.java`
  Solución final comentada.

---

## Referencia para crecer este nivel

Los nuevos ejercicios que metas aquí deberían seguir esta línea:

- una sola clase principal de dominio
- un array de objetos
- varios métodos de proceso
- dificultad media

Si el ejercicio ya pide varias clases relacionadas, `HashSet`, `HashMap` o una
clase gestora separada, lo normal es subirlo al nivel 3 o al nivel 4.
