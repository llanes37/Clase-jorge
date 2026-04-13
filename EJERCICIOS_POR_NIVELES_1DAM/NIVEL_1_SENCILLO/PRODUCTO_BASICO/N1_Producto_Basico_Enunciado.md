# 📝 ENUNCIADO TIPO EXAMEN

## Producto básico

Implementa la clase `Producto` que se utilizará para representar productos de
una tienda.

Tiene los atributos:

- `nombre`. Almacena el nombre del producto.
- `precio`. Almacena el precio del producto.
- `stock`. Almacena la cantidad de unidades disponibles.

Para el constructor ten en cuenta lo siguiente:

- Para crear un producto se necesita información sobre su nombre, precio y
  stock.

Los métodos de la clase son:

- métodos `get` para todos los atributos
- no hay métodos `set`
- `hayStock`. Devuelve `true` si el producto tiene unidades disponibles y
  `false` en caso contrario
- `anadirStock`. Añade al stock la cantidad recibida por parámetro
- `quitarStock`. Reduce el stock en la cantidad recibida por parámetro
- `toString`. Devuelve una cadena de texto con el valor de todos los atributos

## Programa principal

En el `main`:

- crea tres objetos `Producto` con datos inventados
- muestra por pantalla la información de los tres productos
- indica si algunos de ellos tienen stock disponible
- modifica el stock de al menos dos productos
- vuelve a mostrar por pantalla los productos actualizados

## Se pide

- implementar correctamente la clase `Producto`
- crear y utilizar objetos en el `main`
- aplicar llamadas a métodos sobre los objetos creados
- mostrar resultados de forma clara por pantalla
