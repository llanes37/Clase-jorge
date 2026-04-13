# 📝 ENUNCIADO TIPO EXAMEN

## Gestión de alumnos de un curso

Implementa la clase `AlumnoCurso` que se utilizará para representar alumnos de
un curso.

Tiene los atributos:

- `nombre`. Almacena el nombre del alumno.
- `notaFinal`. Almacena la nota final del alumno.
- `repetidor`. Indica si el alumno es repetidor o no.

Para el constructor ten en cuenta lo siguiente:

- Para crear un alumno se necesita información sobre su nombre, nota final y si
  es repetidor.

Los métodos de la clase son:

- métodos `get` para todos los atributos
- no hay métodos `set`
- `estaAprobado`. Devuelve `true` si la nota final es mayor o igual que 5
- `tipoAlumno`. Devuelve un `String` indicando si el alumno es repetidor o no
- `toString`. Devuelve una cadena con el valor de todos los atributos

## Programa principal

Crea la clase `N2_Alumno_Array_Metodos` para gestionar un conjunto de alumnos a
través de un array de objetos `AlumnoCurso`.

Debe implementar los métodos:

- `mostrarAlumnos`. Recorre el array y muestra todos los alumnos almacenados
- `contarAprobados`. Devuelve cuántos alumnos están aprobados
- `calcularMediaNotas`. Devuelve la media de las notas de los alumnos almacenados
- `buscarPorNombre`. Recibe el nombre de un alumno y devuelve el objeto
  correspondiente o `null` si no se encuentra
- `obtenerMayorNota`. Devuelve el alumno con mayor nota del array

En el `main`:

- crea un array de alumnos
- crea varios objetos `AlumnoCurso`
- guárdalos en el array
- muestra por pantalla todos los alumnos
- cuenta cuántos están aprobados
- calcula la media de las notas
- busca un alumno por nombre
- obtiene el alumno con mayor nota

## Se pide

- implementar correctamente la clase `AlumnoCurso`
- trabajar con arrays de objetos
- usar métodos para recorrer, contar, calcular y buscar
