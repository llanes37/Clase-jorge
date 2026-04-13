# 📝 ENUNCIADO TIPO EXAMEN

## Red social

Implementa la clase `Foto` que se va a utilizar para implementar una red
social. Tiene los atributos:

- `nombre`. Almacena el nombre de la foto en forma de array de `String` de dos
  posiciones. En la primera se almacenan letras y en la segunda un número
  identificativo. Ejemplo: `["IMG", "001"]`
- `formato`. Es el formato de la foto: jpg, png, gif, etc.
- `tamanio`. Almacena el tamaño en MB de la foto.
- `usuariosEtiquetados`. Guarda los usuarios que han sido etiquetados a la
  fotografía. Se implementa a través de un `HashSet`
- `likes`. Número de "me gusta" de la foto

Para los constructores ten en cuenta lo siguiente:

- para crear una foto se necesita información sobre su nombre, formato y tamaño
- una foto siempre se crea sin usuarios etiquetados y sin likes
- en el caso de que no se especifique un formato, este es de tipo `"jpg"`

Los métodos de la clase son:

- métodos `get` para todos los atributos
- `getUsuarios`. Devuelve un `String` con los datos de los usuarios que tiene
  etiquetados esa foto
- no hay métodos `set`
- `equals` y `hashCode`. Dos fotos son iguales si tienen el mismo nombre,
  formato y tamaño
- `toString`. Devuelve una cadena de texto con el valor de todos los atributos y
  los usuarios etiquetados
- `like`. Añade un like a la foto
- `addEtiquetado`. Añade un usuario como etiquetado a la foto y devuelve
  información indicando si se pudo etiquetar o no
- `removeEtiquetado`. Quita un usuario como etiquetado a la foto y devuelve
  información indicando si se pudo realizar la operación o no

La clase `Usuario` tiene los atributos:

- `nick`. Es el identificador del usuario
- `nombre`. Nombre real del usuario
- `apellidos`. Apellidos reales del usuario

También tiene un constructor con parámetros, métodos `get`, `equals` y
`hashCode` por `nick` y un método `toString`.

## Gestión de la red social

Crea la clase `GestionFotos` para que realice todas las acciones relacionadas
con la red social a través de un `HashMap` cuya clave es un `Usuario` y el
valor asociado es una lista de sus fotos.

Debe implementar los métodos:

- `addUsuario`. Añade un nuevo usuario a la red social. Devuelve información
  indicando si se añadió el usuario o si ya existía. Si ya existía el usuario
  no se deben eliminar sus fotos
- `addFoto`. Añade una foto a un usuario. El método devuelve información
  indicando si se pudo añadir la foto al usuario
- `getFotosUsuario`. Dado un usuario, devuelve todas sus fotos
- `darLike`. Recibe un usuario y su foto para darle a like. Da like a la foto y
  devuelve el número de likes de la foto o `-1` si no se encontró la foto
- `etiquetar`. Recibe una foto y el usuario que se quiere etiquetar a esa foto
  y devuelve información indicando si se pudo etiquetar o no. Pista: puedes
  recorrer el conjunto de valores del `HashMap`
- `eliminar`. Elimina las fotos de un usuario especificado cuyo tamaño supere el
  que se recibe por parámetro. Devuelve un `HashSet` con las fotos eliminadas
- `eliminar`. Sobrecarga el método anterior para eliminar todas las fotos de
  todos los usuarios de la red social cuyo tamaño supere el que se recibe por
  parámetro. Devuelve un `HashSet` con las fotos eliminadas. Pista: recorre el
  conjunto de valores del `HashMap`
