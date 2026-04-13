# 📝 ENUNCIADO TIPO EXAMEN

## Gestión de libros de una biblioteca

Implementa la clase `LibroBiblioteca` que se utilizará para representar libros
de una biblioteca.

Tiene los atributos:

- `titulo`. Almacena el título del libro.
- `autor`. Almacena el autor del libro.
- `paginas`. Almacena el número de páginas del libro.
- `prestado`. Indica si el libro está prestado o no.

Para el constructor ten en cuenta lo siguiente:

- Para crear un libro se necesita información sobre su título, autor, número de
  páginas y estado de préstamo.

Los métodos de la clase son:

- métodos `get` para todos los atributos
- no hay métodos `set`
- `estaDisponible`. Devuelve `true` si el libro no está prestado
- `prestar`. Cambia el estado del libro a prestado
- `toString`. Devuelve una cadena de texto con el valor de todos los atributos

## Programa principal

Crea la clase `N3_Libro_Biblioteca` para gestionar una colección de libros a
través de un array de objetos `LibroBiblioteca`.

Debe implementar los métodos:

- `mostrarLibros`. Recorre el array y muestra todos los libros almacenados
- `contarDisponibles`. Devuelve cuántos libros están disponibles
- `calcularMediaPaginas`. Devuelve la media de páginas de los libros
  almacenados
- `buscarPorTitulo`. Recibe un título y devuelve el libro correspondiente o
  `null` si no se encuentra
- `obtenerLibroMasLargo`. Devuelve el libro con mayor número de páginas
- `marcarPrestadoPorTitulo`. Busca un libro por su título y lo marca como
  prestado

En el `main`:

- crea un array de libros
- crea varios objetos `LibroBiblioteca`
- guárdalos en el array
- muestra por pantalla todos los libros
- cuenta cuántos están disponibles
- calcula la media de páginas
- busca un libro por su título
- obtiene el libro con mayor número de páginas
- marca como prestado un libro buscando por su título
- vuelve a mostrar el array para comprobar el cambio

## Se pide

- implementar correctamente la clase `LibroBiblioteca`
- trabajar con arrays de objetos
- usar métodos de consulta y de modificación
