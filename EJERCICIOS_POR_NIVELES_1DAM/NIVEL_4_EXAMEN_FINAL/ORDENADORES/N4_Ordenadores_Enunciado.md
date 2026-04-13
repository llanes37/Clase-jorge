# 📝 ENUNCIADO TIPO EXAMEN

## Ordenadores

Implementa la clase `Ordenador`. Tiene los atributos:

- `numeroSerie`. Código de números y letras
- `marca`. Marca del ordenador
- `procesador`. Array de dos posiciones: en la primera se almacena el número de
  núcleos y en la segunda la velocidad del procesador
- `memoriaRAM`. Cantidad en GB. No puede tener decimales
- `perifericos`. Son los periféricos que tiene conectados el ordenador. Se
  implementa como agregación en un `HashSet`
- `numeroMaximoPerifericos`. Es una constante (`final`) con el máximo número de
  periféricos que puede tener un ordenador
- `encendido`. Indica si el ordenador está o no encendido

Para los constructores ten en cuenta lo siguiente:

- para crear un ordenador se necesita información sobre su número de serie,
  marca, velocidad y núcleos del procesador, memoria RAM y máximo de
  periféricos
- un ordenador siempre se crea sin periféricos y apagado

Los métodos de la clase son:

- métodos `get` para todos los atributos
- no se permite que `getPerifericos` devuelva un `HashSet` de periféricos
- no hay métodos `set`
- `toString`. Devuelve una cadena de texto con el valor de todos los atributos y
  el consumo del ordenador, así como los datos de todos sus periféricos
- `equals` y `hashCode`. Dos ordenadores son iguales si tienen el mismo número
  de serie y marca
- `calcularConsumo`. Calcula y devuelve el consumo del ordenador según la
  fórmula `(nucleos del procesador * velocidad del procesador) / 10`
- `addPeriferico`. Añade un periférico al ordenador. Devuelve un `boolean`
  indicando si se pudo añadir o no. Ten en cuenta que no se pueden añadir más
  periféricos que el máximo y que un `HashSet` no permite elementos duplicados
- `removePeriferico`. Recibe un periférico que se eliminará de los conectados al
  ordenador. Devuelve un `boolean` indicando si se pudo eliminar o no

La clase `Periferico` tiene los atributos:

- `tipo`. Ratón, teclado, webcam, etc.
- `marca`. Marca del periférico
- `averiado`. Indica si está o no averiado

También tiene un constructor con los parámetros tipo y marca, métodos `get`,
`equals` y `hashCode` por tipo y marca y un método `toString`.

Cuando se crea un periférico, su estado averiado se genera aleatoriamente según
el código:

```java
Random random = new Random();
random.nextBoolean();
```

## Gestión de ordenadores

Partiendo de la estructura de clases anterior, crea una clase gestora de objetos
`Ordenador` a través de un `HashSet`.

El programa principal debe realizar las siguientes tareas de forma secuencial
(no se pide hacer un menú de opciones):

1. Añadir un equipo.
2. Muestra los datos de todos los equipos de forma que se muestren los datos de
   cada ordenador en una línea diferente.
3. Añadir periféricos. Pide al usuario el número de serie de un ordenador. Si el
   ordenador existe, pide a continuación cuántos periféricos quiere añadir y los
   datos para esos periféricos, intenta añadirlos al ordenador y muestra un
   mensaje fiable indicando cuántos de ellos se pudieron añadir correctamente.
4. Periféricos estropeados. Elimina los periféricos estropeados de todos los
   ordenadores. Muestra el número total de periféricos eliminados.
5. Eliminar periférico. Pide al usuario la marca y el tipo de un periférico.
   Elimina ese periférico de cualquier ordenador en el que esté instalado usando
   el método `removePeriferico` y muestra los números de serie de los
   ordenadores afectados por la eliminación. Utiliza un `ArrayList` para
   almacenarlos.
6. Mostrar periféricos. Muestra todos los periféricos de los ordenadores con más
   de dos núcleos.
7. Actualizar núcleos. Añade 2 núcleos más al procesador de todos los
   ordenadores.
8. Eliminar. Elimina todos los equipos con más de dos periféricos.
