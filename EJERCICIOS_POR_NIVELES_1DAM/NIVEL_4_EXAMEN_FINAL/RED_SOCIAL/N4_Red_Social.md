# 📘 NIVEL 4 - EXAMEN FINAL

## 🧩 Ejercicio guiado: Red Social

Este ejercicio ya no es un ejercicio corto de patrón básico.

Aquí el alumno trabaja con un ejercicio bastante más largo, de final de bloque,
donde aparecen varias clases relacionadas entre sí y varias colecciones de las
que suelen costar más:

- `HashSet`
- `HashMap`
- `List`

---

## 🎯 Qué se pretende entrenar

Este ejercicio obliga a combinar varias ideas a la vez:

- varias clases diferentes
- constructores y sobrecarga
- `equals` y `hashCode`
- una colección dentro de una clase
- una estructura gestora con `HashMap`
- búsqueda y modificación de objetos
- borrado usando iteradores

Es un ejercicio muy bueno de cierre porque ya no se parece a una práctica
aislada, sino a un examen largo.

---

## 🧠 Orden correcto de resolución

Si el alumno intenta hacerlo todo a la vez, se bloquea.

El orden bueno es este:

1. `Usuario`
2. `Foto`
3. `GestionFotos`
4. pruebas en `main`

Dentro de `Foto`, además, el orden bueno es:

1. atributos
2. constructor completo
3. constructor sobrecargado
4. getters
5. `like`
6. `addEtiquetado`
7. `removeEtiquetado`
8. `equals`
9. `hashCode`
10. `toString`

---

## 🔍 Lo que más suele costar aquí

### 1. `equals` y `hashCode`

Son fundamentales porque:

- `Usuario` va dentro de `HashSet`
- `Usuario` es clave del `HashMap`
- `Foto` también se compara por contenido

Si esta parte está mal, casi todo lo demás falla.

### 2. El atributo `nombre` de `Foto`

No es un `String`, sino un array de dos posiciones.

Eso obliga a recordar que para comparar arrays no vale `equals` normal, sino:

- `Arrays.equals(...)`
- `Arrays.hashCode(...)`

### 3. `HashMap<Usuario, List<Foto>>`

Aquí hay que entender muy bien qué significa cada parte:

- la clave es un usuario
- el valor es la lista de sus fotos

No es una lista de usuarios.
No es un mapa de fotos.

Es una estructura que relaciona a cada usuario con sus fotos.

### 4. Borrar mientras se recorre

Cuando se elimina por tamaño, no conviene usar un `for-each` con borrado
directo.

Aquí lo correcto es usar `Iterator`.

---

## ✅ Qué debería aprender el alumno al terminar

- ordenar un ejercicio largo por fases
- identificar qué clase depende de cuál
- usar `HashSet` y `HashMap` con sentido
- recorrer listas y mapas
- entender por qué `equals` y `hashCode` son tan importantes
- distinguir entre buscar, modificar y eliminar

---

## 📂 Archivos de este ejercicio

- `N4_Red_Social.java`
  Plantilla base con TODOs
- `N4_Red_Social_Enunciado.md`
  Enunciado puro, estilo examen
- `N4_Red_Social_Solucion.java`
  Solución comentada
