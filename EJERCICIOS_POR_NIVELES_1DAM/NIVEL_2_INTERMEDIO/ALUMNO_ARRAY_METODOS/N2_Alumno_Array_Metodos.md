# 📘 NIVEL 2 - INTERMEDIO

## 🧩 Ejercicio guiado: `AlumnoCurso` + array de objetos + métodos

Este nivel ya entra de lleno en el patrón clásico de examen de 1 DAM.

Aquí el alumno ya no trabaja con objetos sueltos.
Ahora tiene que aprender a manejar **varios objetos a la vez** usando un array y
varios métodos de proceso.

---

## 🎯 Qué se pretende entrenar

Con este ejercicio se trabajan de forma conjunta estas ideas:

- crear una clase correctamente
- instanciar varios objetos
- guardarlos en un array
- recorrer el array con bucles
- hacer métodos que procesan ese array
- separar la lógica del objeto de la lógica del programa principal

Este es uno de los ejercicios con más valor para preparar examen.

---

## 🧠 Qué tiene que entender el alumno antes de empezar

Hay dos niveles de trabajo distintos:

### 1. La clase `AlumnoCurso`

La clase representa **un solo alumno**.

Cada objeto tendrá:

- un nombre
- una nota final
- si es repetidor o no

### 2. El array de alumnos

El array representa **el conjunto de alumnos**.

Aquí ya no hablamos de un alumno concreto, sino de varios alumnos guardados
juntos.

Eso obliga a pensar en:

- posiciones del array
- bucles
- comprobaciones de `null`
- métodos que recorren todo el conjunto

---

## 📝 Qué hay que hacer realmente en el `.java`

En el archivo `N2_Alumno_Array_Metodos.java` debes resolver dos partes grandes.

### 🔹 Parte A - Construir la clase

1. Declarar atributos.
2. Hacer el constructor.
3. Hacer los getters.
4. Crear `estaAprobado()`.
5. Crear `tipoAlumno()`.
6. Mejorar `toString()`.

### 🔹 Parte B - Trabajar con el array

7. Crear el array.
8. Crear varios objetos.
9. Guardarlos en posiciones del array.
10. Mostrar todos los alumnos.
11. Contar aprobados.
12. Calcular media.
13. Buscar por nombre.
14. Obtener el alumno con mayor nota.

---

## 🚦 Orden correcto para resolverlo

El orden más útil para el alumno es este:

1. hacer la clase
2. crear los objetos
3. meterlos en el array
4. comprobar que `mostrarAlumnos()` funciona
5. hacer `contarAprobados()`
6. hacer `calcularMediaNotas()`
7. hacer `buscarPorNombre()`
8. hacer `obtenerMayorNota()`

Este orden evita muchísimos bloqueos.

---

## 🛠️ Cómo pensar cada parte

## 1. La clase

La clase debe centrarse en el alumno individual.

No debe preocuparse por arrays ni por recorridos.

Solo debe saber:

- cómo guardar sus datos
- cómo decir si está aprobado
- cómo decir qué tipo de alumno es
- cómo mostrarse por pantalla

---

## 2. El array

El array guarda varios objetos del mismo tipo.

Aquí lo importante es entender que:

- cada posición puede guardar un objeto
- alguna posición puede estar vacía
- para procesar el array hace falta recorrerlo con un bucle

Por eso aparecen comprobaciones de `null`.

---

## 3. `mostrarAlumnos()`

Este es el primer método recomendable porque sirve para verificar muchas cosas
a la vez:

- que el array se ha creado bien
- que los objetos están dentro
- que `toString()` está bien hecho

Si este método funciona, ya tienes una base muy sólida.

---

## 4. `contarAprobados()`

Este método entrena un patrón muy habitual:

- crear contador
- recorrer
- comprobar condición
- incrementar
- devolver resultado

Es un patrón básico que luego sirve para muchísimos ejercicios.

---

## 5. `calcularMediaNotas()`

Aquí aparece otro patrón muy importante:

- crear suma
- crear contador
- recorrer
- acumular datos
- dividir al final

El alumno debe aprender a no dividir dentro del bucle.

---

## 6. `buscarPorNombre()`

Aquí el patrón es:

- recorrer el array
- comprobar cada posición
- comparar nombres
- devolver el alumno cuando se encuentra

Es importante que el alumno entienda que no hace falta seguir buscando una vez
encontrado.

---

## 7. `obtenerMayorNota()`

Este método suele costar más porque obliga a comparar objetos.

La idea correcta es:

- empezar con una referencia `mejorAlumno`
- recorrer el array
- comparar la nota del alumno actual con la del mejor
- actualizar si hace falta

---

## ❌ Errores típicos

### Error 1: mezclar la clase con la lógica del array

La clase representa un alumno.

Los métodos estáticos procesan el conjunto de alumnos.

### Error 2: olvidar posiciones `null`

Antes de usar un elemento del array hay que comprobar que existe.

### Error 3: meter todo dentro de `main`

Eso hace el código más largo, más confuso y menos reutilizable.

### Error 4: empezar por el método más difícil

Siempre conviene empezar por mostrar, luego contar y luego calcular o buscar.

### Error 5: no probar paso a paso

Haz una parte, ejecuta y revisa.

---

## ✅ Checklist antes de darlo por terminado

- la clase está bien construida
- el constructor usa bien `this`
- los getters funcionan
- `estaAprobado()` devuelve bien
- `tipoAlumno()` devuelve el texto correcto
- `toString()` muestra información útil
- el array está bien creado
- los objetos están bien guardados
- `mostrarAlumnos()` funciona
- `contarAprobados()` funciona
- `calcularMediaNotas()` funciona
- `buscarPorNombre()` funciona
- `obtenerMayorNota()` funciona

---

## 📂 Archivos recomendados para trabajar este nivel

- `N2_Alumno_Array_Metodos.java`
  Plantilla guiada con TODOs
- `N2_Alumno_Array_Metodos_Enunciado.md`
  Enunciado puro, estilo examen
- `N2_Alumno_Array_Metodos_Solucion.java`
  Solución completa, muy comentada

---

## 🚀 Qué debería saber hacer el alumno al terminar

Si este ejercicio sale bien, el alumno debería poder:

- trabajar con arrays de objetos
- separar la clase del procesamiento general
- recorrer arrays con seguridad
- contar, buscar y calcular datos
- entender el patrón real de muchos exámenes de 1 DAM

Ese es el verdadero objetivo del nivel 2.
