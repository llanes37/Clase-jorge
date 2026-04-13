# COMO RESOLVER UN EXAMEN - 1 DAM

> Guía práctica para afrontar ejercicios de Programación en 1 DAM sin empezar a escribir código al azar

---

## Para qué sirve este documento

Muchos alumnos no suspenden porque no hayan visto la teoría.

Suspenden porque, cuando leen el examen, no saben:

- por dónde empezar
- en qué orden resolverlo
- qué parte pertenece a la clase y qué parte al `main`
- cuándo deben hacer métodos
- cómo pasar del enunciado al código

Este documento sirve para evitar eso.

La idea es que el alumno aprenda un orden de trabajo estable y lo repita siempre.

Cuando el orden es bueno, el examen se vuelve mucho más controlable.

---

## Idea clave

En 1 DAM, muchos ejercicios cambian de contexto:

- productos
- alumnos
- libros
- coches
- empleados
- fotos
- ordenadores

Pero el patrón suele ser muy parecido.

Normalmente aparece una combinación de estas piezas:

- una clase
- objetos
- métodos
- arrays o colecciones
- búsqueda, conteo, media o modificación

Por tanto, el objetivo no es memorizar un ejercicio concreto.

El objetivo real es reconocer el patrón y aplicar siempre una forma correcta de resolverlo.

---

## Regla de oro

Nunca empieces un examen programando sin pensar.

Lo correcto es:

1. leer el enunciado entero
2. detectar las partes del problema
3. decidir el orden de construcción
4. programar por capas
5. probar al final cada parte

Si haces eso, incluso aunque no termines todo, puedes sacar bastante nota.

Si empiezas sin orden, es muy fácil bloquearse, duplicar trabajo o dejar el ejercicio roto.

---

## Qué debes hacer en los primeros minutos

Los primeros minutos son muy importantes.

No son para escribir código rápido.

Son para entender qué te están pidiendo de verdad.

### Paso 1. Leer todo el enunciado

Hay que leerlo entero una vez, sin programar todavía.

Mientras lees, intenta detectar:

- qué clases aparecen
- qué atributos pide cada una
- qué métodos pide cada una
- si hay array o colección
- si hay una clase gestora
- qué debe ocurrir en el `main`

### Paso 2. Separar el examen en bloques

Casi siempre conviene partir el examen en bloques como estos:

- bloque 1: clase principal
- bloque 2: segunda clase si existe
- bloque 3: estructura de datos
- bloque 4: métodos de proceso
- bloque 5: `main`

Cuando haces esa separación mental, el examen deja de ser una masa de texto y empieza a parecer resoluble.

### Paso 3. Subrayar verbos importantes

En un enunciado de programación hay verbos que mandan.

Por ejemplo:

- implementa
- crea
- añade
- elimina
- busca
- muestra
- calcula
- devuelve
- actualiza

Esos verbos indican qué comportamiento tienes que codificar.

No es lo mismo:

- almacenar un dato
- mostrar un dato
- devolver un dato
- modificar un dato

---

## Cómo detectar qué tipo de ejercicio es

Antes de programar, conviene clasificar el examen.

### Tipo 1. Clase simple + objetos

Suele pedir:

- una clase con atributos
- constructor
- getters
- algún método sencillo
- crear varios objetos en `main`

Esto suele ser nivel básico.

El orden correcto es:

1. atributos
2. constructor
3. getters
4. métodos
5. `toString`
6. objetos en `main`

### Tipo 2. Clase + array de objetos + métodos

Es el patrón más típico de 1 DAM.

Suele pedir:

- una clase
- varios objetos
- un array de objetos
- métodos para mostrar, contar, buscar o calcular

El orden correcto es:

1. hacer la clase
2. crear objetos
3. meterlos en el array
4. hacer métodos de proceso
5. usar esos métodos en `main`

### Tipo 3. Clase + modificación de objetos

Aquí, además de mostrar o buscar, te piden cambiar el estado de un objeto.

Ejemplos:

- prestar libro
- añadir stock
- quitar stock
- encender
- poner disponible

En este tipo, el alumno tiene que distinguir entre:

- consultar información
- modificar información

### Tipo 4. Varias clases relacionadas

Aquí la dificultad sube.

Puede aparecer:

- clase principal
- segunda clase relacionada
- agregación o composición
- clase gestora
- `HashSet`
- `HashMap`

El error típico aquí es empezar por el `main`.

Eso casi siempre sale mal.

### Tipo 5. Clase gestora + colección

Aquí normalmente aparece una estructura como:

- `HashSet`
- `ArrayList`
- `HashMap`

Y una clase que centraliza operaciones.

En estos ejercicios hay que pensar por capas:

1. clases de dominio
2. relaciones entre clases
3. clase gestora
4. operaciones del programa

---

## El orden correcto para programar

Este es, en general, el mejor orden para resolver un examen:

1. atributos
2. constructor
3. getters
4. métodos sencillos de la clase
5. `equals` y `hashCode` si los pide
6. `toString`
7. clase gestora o métodos de proceso
8. `main`

Ese orden funciona muy bien porque va de lo más local a lo más global.

Primero construyes la pieza.

Luego haces lo que se hace con la pieza.

Y al final montas el uso completo.

---

## Cómo construir bien una clase

Cuando el examen te pida una clase, acostúmbrate a pensar siempre lo mismo.

### 1. Qué datos tiene

Estos serán los atributos.

Debes detectar:

- tipo de dato
- nombre correcto
- si es simple o estructura

Ejemplos:

- `String nombre`
- `double precio`
- `int stock`
- `boolean prestado`
- `Alumno[] alumnos`
- `HashSet<Usuario> usuariosEtiquetados`

### 2. Cómo se crea

Esto define el constructor.

Pregúntate:

- qué datos me dan al crear el objeto
- qué datos se inicializan solos

Ejemplo:

- si el enunciado dice que un objeto se crea sin likes, eso no se pasa por parámetro
- si dice que se crea apagado, eso se inicializa dentro del constructor

### 3. Qué puede hacer

Esto define los métodos.

Cada método hay que pensarlo así:

- qué recibe
- qué hace
- qué devuelve

Ese análisis evita muchos errores.

---

## Cómo decidir si un método devuelve algo o no

Esta parte causa muchos fallos.

Hazte estas preguntas:

### Si el método solo cambia estado

Suele ser `void` o `boolean`.

Ejemplos:

- `prestar()`
- `anadirStock(int cantidad)`
- `quitarStock(int cantidad)`

### Si el método calcula o consulta

Suele devolver algo.

Ejemplos:

- `hayStock()` devuelve `boolean`
- `calcularMedia()` devuelve `double`
- `buscarPorNombre()` devuelve un objeto o `null`
- `getNombre()` devuelve `String`

### Si el método intenta hacer algo y quieres saber si salió bien

Suele devolver `boolean`.

Ejemplos:

- añadir a una colección
- eliminar de una colección
- etiquetar usuario

---

## Cómo pensar un array de objetos

Este es uno de los puntos más importantes en 1 DAM.

Muchos alumnos ven un array de objetos y se bloquean porque mezclan:

- el array
- cada objeto individual
- los métodos de proceso

Hay que separarlo.

### Qué es el array

El array no es el alumno, ni el libro, ni el producto.

El array es solo la estructura que guarda varios objetos.

Ejemplo:

```java
Alumno[] alumnos = new Alumno[5];
```

Eso no crea 5 alumnos.

Crea espacio para guardar 5 referencias.

Luego hay que crear cada objeto.

### Orden correcto con arrays de objetos

1. crear la clase
2. crear el array
3. crear objetos
4. meter objetos en el array
5. recorrer el array con métodos

### Qué se suele hacer con el array

Normalmente se usa para:

- mostrar elementos
- contar cuántos cumplen una condición
- calcular una media
- buscar un elemento
- encontrar el mayor o menor
- modificar un objeto encontrado

---

## Cómo recorrer bien un array

Cada vez que recorras un array, piensa dos cosas:

1. qué estoy buscando
2. qué hago cuando encuentro un elemento válido

### Ejemplo: mostrar todos

```java
for (int i = 0; i < alumnos.length; i++) {
    if (alumnos[i] != null) {
        System.out.println(alumnos[i]);
    }
}
```

### Idea importante

Si el array puede tener huecos, hay que comprobar `null`.

Olvidar eso es uno de los errores más frecuentes.

---

## Cómo resolver búsquedas

Las búsquedas aparecen muchísimo.

Patrón mental:

1. recorrer
2. comparar
3. si coincide, devolver
4. si no aparece, devolver `null`

Ejemplo mental:

- busco un alumno por nombre
- recorro el array
- comparo nombre a nombre
- si lo encuentro, lo devuelvo
- si termino el recorrido sin éxito, devuelvo `null`

### Error típico

Comparar `String` con `==`.

Lo correcto es:

```java
nombre.equals(otroNombre)
```

o si quieres evitar `NullPointerException`:

```java
textoBuscado.equals(objeto.getNombre())
```

---

## Cómo resolver conteos

Si te piden contar, piensa así:

1. crear contador en 0
2. recorrer la estructura
3. comprobar condición
4. sumar 1 si se cumple
5. devolver el contador

Ejemplo:

- contar aprobados
- contar libros disponibles
- contar fotos grandes

Este patrón se repite muchísimo.

---

## Cómo resolver medias

Si te piden calcular una media, el patrón suele ser:

1. acumulador para la suma
2. contador de elementos válidos
3. recorrido
4. sumar y contar
5. dividir al final

### Cuidado

No conviene dividir dentro del bucle.

Se divide al final.

Además, si puede no haber elementos válidos, hay que controlar el caso de división entre cero.

---

## Cómo resolver “máximo” o “mínimo”

Este patrón también cae mucho.

### Idea general

1. tomar un primer elemento válido como referencia
2. recorrer el resto
3. comparar
4. actualizar la referencia si aparece uno mejor

Ejemplos:

- alumno con mayor nota
- libro con más páginas
- producto más caro

### Error típico

Empezar con una referencia `null` y luego acceder a sus métodos sin control.

---

## Cómo afrontar colecciones como HashSet o HashMap

Esto suele aparecer en ejercicios más largos.

### HashSet

Sirve para guardar elementos sin duplicados.

Eso significa que:

- el objeto necesita bien `equals`
- el objeto necesita bien `hashCode`

Si esos métodos están mal, el `HashSet` no se comportará como esperas.

### HashMap

Sirve para asociar una clave con un valor.

Ejemplo:

- un usuario y sus fotos
- un alumno y sus notas

Aquí hay que tener clara la estructura:

- clave
- valor
- operaciones sobre claves
- operaciones sobre valores

---

## Cómo saber cuándo usar equals y hashCode

Si el ejercicio usa:

- `HashSet`
- `HashMap`

entonces `equals` y `hashCode` casi nunca son opcionales si el enunciado los pide.

Debes pensar:

- qué hace único al objeto
- qué atributos definen igualdad

Ejemplos:

- un usuario por `nick`
- un ordenador por número de serie y marca
- un periférico por tipo y marca

Si dos objetos son iguales según `equals`, también deben generar un `hashCode` coherente.

---

## Cómo afrontar el main

El `main` no suele ser la parte más difícil, pero muchos alumnos lo usan mal.

El `main` no debería servir para meter toda la lógica.

Su función normal es:

- crear objetos
- guardarlos
- llamar métodos
- mostrar resultados

### Mala costumbre

Hacer todos los cálculos dentro de `main`.

### Buena costumbre

Dejar la lógica en métodos y usar el `main` como secuencia de uso.

---

## Qué hacer si te bloqueas

Bloquearse es normal.

Lo importante es reaccionar bien.

### Si no sabes cómo empezar

Haz solo esto:

1. escribe los atributos
2. haz el constructor
3. haz los getters

Con eso ya empiezas a construir la base.

### Si no sabes hacer un método

Escríbelo en lenguaje natural antes:

- qué recibe
- qué tiene que comprobar
- qué devuelve

Después lo traduces a código.

### Si el ejercicio es largo

Divide en miniobjetivos:

- primero que compile la clase
- luego que funcione un método
- luego otro
- luego el `main`

Nunca intentes resolverlo entero de golpe.

---

## Errores típicos que bajan mucha nota

Estos fallos aparecen constantemente en 1 DAM.

### 1. Hacer todo en `main`

Eso suele indicar que no se ha entendido bien el uso de clases y métodos.

### 2. No respetar el enunciado

Ejemplos:

- meter `set` cuando no se piden
- cambiar tipos
- no usar array si lo exige
- no usar `HashSet` si lo exige

### 3. Confundir constructor con método

El constructor:

- no tiene tipo de retorno
- se llama igual que la clase

### 4. Comparar `String` con `==`

Debe usarse `equals`.

### 5. No controlar `null`

Especialmente en arrays de objetos o búsquedas.

### 6. No inicializar estructuras

Ejemplos:

- olvidar hacer `new HashSet<>()`
- olvidar crear el array

### 7. Devolver mal el resultado

Ejemplos:

- imprimir cuando debía devolver
- devolver cuando debía modificar

### 8. toString pobre o incorrecto

Si el enunciado pide mostrar todos los datos, el `toString` debe ser útil de verdad.

### 9. equals y hashCode incoherentes

Esto rompe ejercicios con `HashSet` y `HashMap`.

### 10. No terminar nada

Es mejor dejar varias partes pequeñas correctas que mucho código a medias y roto.

---

## Cómo repartir el tiempo en un examen

No existe una fórmula exacta, pero este reparto suele funcionar mejor que improvisar.

### Fase 1. Lectura y esquema

Usa unos minutos al principio para entender:

- clases
- atributos
- métodos
- estructuras

### Fase 2. Construcción base

Haz primero:

- atributos
- constructor
- getters
- `toString`

Esto te da una base rápida y suma puntos.

### Fase 3. Métodos importantes

Después resuelve:

- búsquedas
- conteos
- medias
- modificaciones

### Fase 4. main y repaso

El `main` va al final.

Y deja siempre uno o dos minutos para revisar:

- nombres
- llaves
- tipos
- returns
- `null`

---

## Qué hacer si no te da tiempo

Si ves que no vas a terminar, no entres en pánico.

Prioriza esto:

1. clase bien construida
2. constructor correcto
3. getters
4. `toString`
5. al menos un método importante bien hecho
6. `main` sencillo

Eso es mucho mejor que intentar hacer todo deprisa y dejarlo lleno de errores.

---

## Plantilla mental para casi cualquier examen

Puedes pensar cualquier ejercicio con esta plantilla:

### 1. Qué clases hay

- una
- dos
- una gestora

### 2. Qué atributos tiene cada clase

- tipo de cada atributo
- qué representa

### 3. Cómo se crea cada objeto

- qué entra por constructor
- qué se inicializa solo

### 4. Qué métodos hacen falta

- getters
- consulta
- modificación
- búsqueda
- cálculo

### 5. Qué estructura global hay

- array
- `ArrayList`
- `HashSet`
- `HashMap`

### 6. Qué se hace en main

- crear
- añadir
- mostrar
- buscar
- modificar

Si el alumno se acostumbra a pensar así, muchos exámenes dejan de parecer nuevos.

---

## Ejemplo de orden real de trabajo

Supón que el examen pide:

- clase `Libro`
- array de libros
- mostrar
- buscar por título
- obtener el libro con más páginas
- marcar un libro como prestado

El orden correcto sería:

1. atributos de `Libro`
2. constructor de `Libro`
3. getters de `Libro`
4. método `prestar`
5. método `toString`
6. crear array de libros
7. crear objetos libro
8. guardarlos en el array
9. método `mostrarLibros`
10. método `buscarPorTitulo`
11. método `obtenerLibroMasLargo`
12. método `marcarPrestadoPorTitulo`
13. usar todo desde `main`

Ese es el tipo de pensamiento que hay que automatizar.

---

## Señales de que vas bien

Durante el examen, normalmente vas bien si:

- la clase ya compila
- el constructor está claro
- los getters salen rápidos
- sabes distinguir consulta de modificación
- los métodos tienen una responsabilidad concreta
- el `main` solo organiza y prueba

Si todo eso ocurre, aunque falten detalles, el examen está encarrilado.

---

## Señales de que te estás torciendo

Debes parar y corregir si detectas esto:

- llevas mucho rato en el `main`
- no has terminado todavía la clase principal
- estás improvisando nombres sin orden
- no sabes qué devuelve un método
- estás repitiendo código por todas partes
- el enunciado pide una cosa y tú estás haciendo otra

Cuando eso pase, vuelve al orden base:

1. clase
2. métodos
3. estructura
4. `main`

---

## Consejo final

El examen no se resuelve siendo rápido.

Se resuelve teniendo un orden.

En 1 DAM, muchas veces no gana el alumno que más sabe teoría, sino el que:

- reconoce el patrón
- organiza bien las partes
- evita errores básicos
- escribe código simple y claro

La meta no es hacer un examen perfecto a la primera.

La meta es entrar al examen pensando:

> "Sé por dónde empezar, sé en qué orden hacerlo y sé qué patrón estoy viendo."

Si consigues eso, el nivel sube mucho.
