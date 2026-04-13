# 📘 NIVEL 1 - SENCILLO

## 🧩 Ejercicio guiado: `Producto` básico

Este material no es solo un enunciado.

Su objetivo es ayudarte a **entender muy bien** el patrón más básico de examen
con clases y objetos en 1 DAM.

Aquí no vamos a trabajar con arrays todavía.
Aquí no vamos a trabajar con herencia.
Aquí no vamos a trabajar con menús.

Aquí vamos a centrar todo en una sola idea:

- crear una clase sencilla
- construir objetos a partir de esa clase
- usar esos objetos en `main`
- modificar su estado con métodos simples

---

## 🎯 Qué se pretende entrenar

Aunque el ejercicio parezca corto, aquí se entrenan varias cosas muy
importantes:

- distinguir **clase** y **objeto**
- declarar atributos correctamente
- construir objetos con un constructor
- devolver datos con getters
- crear métodos simples de negocio
- usar `toString()` para comprobar que todo funciona
- trabajar con objetos desde `main`

Este patrón aparece muchísimas veces en 1 DAM.

---

## 🧠 Qué tiene que entender el alumno

Antes de tocar el código, hay que entender esto muy bien:

### 1. La clase es el molde

La clase define cómo será un objeto.

En este caso, la clase se llama `Producto`.

Eso significa que un producto tendrá siempre una estructura fija:

- un nombre
- un precio
- un stock

### 2. El objeto es el elemento concreto

Cuando en `main` haces algo como:

```java
Producto p1 = new Producto("Teclado", 25.99, 8);
```

no estás creando una clase.
Estás creando **un objeto concreto** de tipo `Producto`.

### 3. El constructor sirve para dar valores iniciales

Cuando creas un producto, Java necesita saber con qué datos empieza.

Por eso el constructor recibe:

- el nombre
- el precio
- el stock

### 4. Los getters permiten leer datos del objeto

Si luego quieres saber el nombre, el precio o el stock de un objeto,
normalmente usarás getters.

### 5. Los métodos permiten que el objeto haga cosas

En este ejercicio no solo guardamos información.
También queremos que el objeto pueda responder preguntas o cambiar su estado.

Por ejemplo:

- saber si tiene stock
- añadir stock
- quitar stock

---

## 📝 Qué hay que hacer realmente en el `.java`

En el archivo `N1_Producto_Basico.java` tienes una plantilla con TODOs.

Debes completarla en este orden:

### 🔹 Parte A - Construir la clase

1. Declarar los atributos privados:
   - `nombre`
   - `precio`
   - `stock`
2. Hacer el constructor.
3. Hacer los getters.
4. Hacer `hayStock()`.
5. Hacer `anadirStock(int cantidad)`.
6. Hacer `quitarStock(int cantidad)`.
7. Mejorar `toString()`.

### 🔹 Parte B - Usar la clase en `main`

8. Crear tres objetos `Producto`.
9. Mostrarlos por pantalla.
10. Comprobar si algunos tienen stock.
11. Modificar cantidades.
12. Volver a mostrar los objetos.

---

## 🚦 Orden correcto para resolverlo

Muchos alumnos se bloquean porque intentan empezar por cualquier parte.

El orden bueno es este:

1. atributos
2. constructor
3. getters
4. métodos simples
5. `toString()`
6. `main`

Esto es importante:

> Primero construyes bien la clase.  
> Después usas esa clase para crear objetos.

---

## 🛠️ Cómo pensar cada parte

## 1. Atributos

Tienes que decidir qué tipo de dato necesita cada cosa:

- `nombre` es texto → `String`
- `precio` tiene decimales → `double`
- `stock` es cantidad entera → `int`

Además, en este nivel conviene que sean `private`.

Eso obliga al alumno a usar bien constructor y getters.

---

## 2. Constructor

El constructor recibe los datos del producto y los guarda dentro del objeto.

La idea mental correcta es esta:

- entra un nombre
- entra un precio
- entra un stock
- el objeto guarda esos tres valores

Aquí suele aparecer un error muy típico:

```java
nombre = nombre;
```

Eso está mal.

Lo correcto es usar `this`:

```java
this.nombre = nombre;
```

---

## 3. Getters

Los getters solo devuelven información.

No modifican nada.

Por ejemplo:

- `getNombre()` devuelve el nombre
- `getPrecio()` devuelve el precio
- `getStock()` devuelve el stock

Son una parte sencilla, pero muy importante para ordenar la cabeza del alumno.

---

## 4. Método `hayStock()`

Este método responde a una pregunta:

> ¿El stock es mayor que 0?

Si sí, devuelve `true`.
Si no, devuelve `false`.

Es un buen ejemplo de método corto, claro y típico de examen.

---

## 5. Métodos para modificar stock

Aquí ya se empieza a ver que un objeto no solo guarda datos.
También puede cambiar.

### `anadirStock(int cantidad)`

Debe sumar la cantidad recibida al stock actual.

### `quitarStock(int cantidad)`

Debe restar la cantidad recibida al stock actual.

En este ejercicio no vamos a complicarlo todavía con validaciones avanzadas.
Primero queremos dominar el patrón básico.

---

## 6. `toString()`

`toString()` es muy importante porque permite mostrar el objeto de forma clara.

Si haces esto:

```java
System.out.println(p1);
```

y `toString()` está bien hecho, podrás comprobar rápido si:

- el nombre se ha guardado bien
- el precio está correcto
- el stock ha cambiado cuando tocaba

Es una herramienta muy útil para depurar.

---

## 7. `main`

Cuando la clase ya está construida, `main` se vuelve mucho más sencillo.

Dentro de `main` debes pensar así:

1. crear objetos
2. mostrarlos
3. usar métodos
4. volver a mostrarlos

Ese orden hace que el alumno vea el ejercicio como una secuencia lógica y no
como un bloque caótico.

---

## ❌ Errores típicos

### Error 1: confundir clase con objeto

`Producto` es la clase.

`p1`, `p2`, `p3` son objetos.

### Error 2: olvidar `this` en el constructor

Esto provoca que el objeto no guarde bien los datos.

### Error 3: empezar por `main`

Si la clase no está terminada, `main` se vuelve un foco de errores.

### Error 4: hacer `toString()` al final del todo sin probar antes

Entonces cuesta mucho comprobar si algo funciona.

### Error 5: no ejecutar poco a poco

La mejor forma de trabajar es:

- hacer una parte
- ejecutar
- comprobar
- continuar

---

## ✅ Checklist antes de darlo por terminado

- los atributos están bien declarados
- el constructor usa `this`
- los getters devuelven bien los datos
- `hayStock()` funciona
- `anadirStock()` modifica el stock
- `quitarStock()` modifica el stock
- `toString()` muestra información útil
- los objetos se crean bien en `main`
- el programa muestra resultados claros

---

## 📂 Archivos recomendados para trabajar este nivel

- `N1_Producto_Basico.java`
  Plantilla guiada con TODOs
- `N1_Producto_Basico_Enunciado.md`
  Enunciado puro, estilo examen
- `N1_Producto_Basico_Solucion.java`
  Solución completa, muy comentada

---

## 🚀 Qué debería saber hacer el alumno al terminar

Si este ejercicio sale bien, el alumno debería ser capaz de:

- crear una clase sencilla desde cero
- hacer un constructor correctamente
- hacer getters sin copiar
- crear varios objetos en `main`
- mostrar objetos con `toString()`
- modificar el estado de un objeto con métodos
- entender claramente la diferencia entre clase, objeto, atributo y método

Ese es el verdadero objetivo del nivel 1.
