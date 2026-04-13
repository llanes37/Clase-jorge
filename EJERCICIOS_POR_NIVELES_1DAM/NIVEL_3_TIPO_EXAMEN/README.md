# NIVEL 3 - TIPO EXAMEN

Este nivel mantiene el patrón de 1 DAM, pero con menos apoyo y con una
exigencia más cercana a simulacro real.

Aquí el alumno ya debería manejar con cierta soltura:

- una clase propia
- un array de objetos
- varios métodos de consulta
- algún método que modifica el estado de un objeto encontrado

---

## Qué se trabaja aquí

Este nivel sirve para dar un paso más respecto al intermedio:

- mantener el patrón objeto + array + métodos
- exigir más autonomía
- meter una lógica algo más larga
- obligar a actualizar objetos ya creados

La diferencia con el nivel 2 no está tanto en la teoría, sino en la capacidad
de ordenar el trabajo y llegar al final del ejercicio sin atascarse.

---

## Cómo se debe usar este nivel

La forma buena de trabajarlo es esta:

1. leer el enunciado entero sin programar todavía
2. separar qué parte es clase y qué parte es procesamiento
3. construir primero la clase completa
4. montar después el array en `main`
5. resolver los métodos uno por uno
6. probar la parte de modificación al final

En este nivel ya conviene que el alumno intente resolver primero por su cuenta y
use la guía como apoyo, no como sustituto del trabajo.

---

## Ejercicio actual

- `LIBRO_BIBLIOTECA`

Este ejercicio está pensado como paso natural entre el intermedio y un examen
más largo. Mantiene una estructura reconocible, pero añade un punto importante:
buscar un objeto y cambiar su estado.

Eso lo hace especialmente útil para practicar:

- búsquedas por campo
- modificaciones controladas
- comprobación de cambios tras procesar el array

---

## Qué contiene cada carpeta de ejercicio

Cada ejercicio de este nivel debe incluir:

- archivo base `.java`
- guía `.md`
- enunciado `.md`
- archivo `_Solucion.java`

La diferencia con niveles anteriores no está en los archivos, sino en la
dificultad y en la autonomía esperada.

---

## Referencia para crecer este nivel

Los ejercicios nuevos de este nivel deberían seguir esta idea:

- una clase de dominio clara
- un array de objetos
- varios métodos de proceso
- al menos una operación que cambie el estado de un objeto

Cuando el ejercicio ya exige varias clases conectadas, colecciones como
`HashSet` o `HashMap`, o una clase gestora separada, lo correcto es moverlo al
nivel 4.
