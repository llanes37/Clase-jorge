# 📘 NIVEL 3 - TIPO EXAMEN

## 🧩 Ejercicio guiado: `LibroBiblioteca` + array de objetos + modificación

Este nivel ya se acerca bastante a lo que un alumno puede encontrarse en un
examen real.

La estructura sigue siendo la misma que en el nivel 2, pero con una exigencia
un poco mayor:

- hay menos indicaciones dentro del `.java`
- aparecen métodos de consulta
- aparece también un método que modifica el estado de un objeto buscándolo en el array

---

## 🎯 Qué se pretende entrenar

Aquí se quiere consolidar todo esto:

- clase correctamente construida
- array de objetos
- recorridos seguros
- búsqueda
- cálculo
- modificación del estado de un objeto encontrado

Este nivel es muy útil porque obliga al alumno a dejar de pensar en ejercicios
aislados y empezar a pensar en patrones de examen completos.

---

## 🧠 Qué debe hacer bien el alumno

En este punto ya debería ser capaz de:

- crear la clase sin demasiada ayuda
- meter objetos en un array
- recorrer el array sin romper nada
- distinguir entre métodos que devuelven información y métodos que modifican datos

Si todavía falla mucho en eso, conviene volver a trabajar primero el nivel 2.

---

## 📝 Qué hay que hacer en este ejercicio

El ejercicio gira en torno a una clase `LibroBiblioteca`.

Cada libro tendrá:

- título
- autor
- número de páginas
- si está prestado o no

Con esos libros habrá que:

- mostrarlos
- contar cuántos están disponibles
- calcular la media de páginas
- buscar uno por título
- obtener el libro con más páginas
- marcar un libro como prestado buscando por su título

---

## 🚦 Orden correcto para resolverlo

1. hacer la clase
2. hacer constructor y getters
3. hacer `estaDisponible()`
4. hacer `prestar()`
5. mejorar `toString()`
6. crear los objetos en `main`
7. meterlos en el array
8. hacer `mostrarLibros()`
9. hacer `contarDisponibles()`
10. hacer `calcularMediaPaginas()`
11. hacer `buscarPorTitulo()`
12. hacer `obtenerLibroMasLargo()`
13. hacer `marcarPrestadoPorTitulo()`

---

## 🔍 Idea importante: método que modifica tras buscar

Este nivel mete una idea nueva que suele costar:

- primero buscas el objeto dentro del array
- después modificas ese objeto

En este caso:

- buscas un libro por su título
- cuando lo encuentras, llamas a `prestar()`

Eso es muy útil porque se parece bastante a muchos ejercicios reales.

---

## ❌ Errores típicos

- olvidar comprobar `null`
- comparar cadenas con `==` en vez de usar `equals()` o `equalsIgnoreCase()`
- mezclar la lógica del libro con la lógica del array
- modificar el libro equivocado por recorrer mal el array
- no volver a mostrar los datos después de modificar

---

## ✅ Checklist

- la clase está bien definida
- `toString()` muestra bien el libro
- el array se recorre sin errores
- se cuenta bien cuántos están disponibles
- la media se calcula bien
- la búsqueda por título funciona
- el libro con más páginas se obtiene bien
- el cambio a prestado se aplica correctamente

---

## 📂 Archivos recomendados para trabajar este nivel

- `N3_Libro_Biblioteca.java`
  Plantilla base del ejercicio
- `N3_Libro_Biblioteca_Enunciado.md`
  Enunciado puro, estilo examen
- `N3_Libro_Biblioteca_Solucion.java`
  Solución comentada
