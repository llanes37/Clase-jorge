# 📘 NIVEL 4 - EXAMEN FINAL

## 🧩 Ejercicio guiado: Ordenadores

Este ejercicio es de los que ya huelen completamente a examen largo.

No solo hay varias clases.
También hay:

- una clase gestora
- un `HashSet` global
- `HashSet` dentro de otra clase
- recorrido y modificación de estructuras
- operaciones secuenciales sobre todo el conjunto

---

## 🎯 Qué se pretende entrenar

Con este ejercicio se entrena:

- diseño de varias clases relacionadas
- `equals` y `hashCode` en varias clases
- uso correcto de `HashSet`
- gestión de equipos desde una clase central
- eliminación mientras se recorre
- modificación de atributos internos del objeto

Es un ejercicio más largo que `LibroBiblioteca` y más estructural que los
niveles anteriores.

---

## 🧠 Orden correcto de resolución

Si se intenta hacer por el `main`, se vuelve inabarcable.

El orden bueno aquí es:

1. `Periferico`
2. `Ordenador`
3. `GestionOrdenadores`
4. `main`

Dentro de `Ordenador`, conviene ir así:

1. atributos
2. constructor
3. getters
4. `calcularConsumo`
5. `addPeriferico`
6. `removePeriferico`
7. `sumarNucleos`
8. `equals`
9. `hashCode`
10. `toString`

---

## 🔍 Qué suele costar más

### 1. El procesador como array

No es una clase, ni dos atributos sueltos.
Es un array de dos posiciones.

Eso obliga al alumno a recordar bien qué significa cada posición.

### 2. El `HashSet` de periféricos

Aquí `equals` y `hashCode` son fundamentales.

Si están mal, el `HashSet` no detecta duplicados correctamente.

### 3. La clase gestora

Ya no basta con trabajar con un objeto concreto.
Ahora hay que gestionar un conjunto de ordenadores.

### 4. Eliminar mientras se recorre

Para ciertas operaciones conviene usar `Iterator`.
Es una parte que muchos alumnos no dominan bien.

---

## ✅ Qué debería aprender el alumno al terminar

- trabajar con varias clases conectadas
- gestionar conjuntos con `HashSet`
- usar una clase gestora separada
- modificar ordenadores ya existentes
- recorrer y actualizar estructuras de datos
- resolver un ejercicio largo por fases

---

## 📂 Archivos de este ejercicio

- `N4_Ordenadores.java`
  Plantilla base con TODOs
- `N4_Ordenadores_Enunciado.md`
  Enunciado puro, estilo examen
- `N4_Ordenadores_Solucion.java`
  Solución comentada
