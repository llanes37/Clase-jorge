# P08 — Clases Genéricas

> **Archivo de práctica:** `P08_ClasesGenericas.java`  
> **PDF de referencia:** `Clases genéricas.pdf`

---

## ¿Qué son los genéricos?

Permiten escribir clases y métodos que funcionan con **cualquier tipo de dato**, manteniendo la seguridad de tipos en tiempo de compilación.

Sin genéricos (problemático):
```java
Object contenido = "Hola";
String s = (String) contenido;  // casting manual → puede fallar en ejecución
```

Con genéricos (seguro):
```java
Caja<String> caja = new Caja<>("Hola");
String s = caja.getContenido();  // no necesita casting, el compilador lo sabe
```

---

## Clase genérica

```java
class Caja<T> {
    private T contenido;

    public Caja(T contenido) {
        this.contenido = contenido;
    }

    public T getContenido() { return contenido; }
}
```

```java
Caja<String>  c1 = new Caja<>("Hola");
Caja<Integer> c2 = new Caja<>(42);
Caja<Double>  c3 = new Caja<>(3.14);
```

---

## Clase genérica con dos tipos

```java
class Par<K, V> {
    private K clave;
    private V valor;

    public Par(K clave, V valor) { this.clave = clave; this.valor = valor; }
    public K getClave() { return clave; }
    public V getValor() { return valor; }
}
```

```java
Par<String, Integer> nota = new Par<>("Matemáticas", 9);
```

---

## Método genérico

```java
static <T> void mostrar(T elemento) {
    System.out.println(elemento.getClass().getSimpleName() + ": " + elemento);
}
```

El tipo `T` se **infiere automáticamente** según lo que se pasa:
```java
mostrar("Texto");   // T = String
mostrar(123);       // T = Integer
mostrar(true);      // T = Boolean
```

---

## Tipos acotados (`extends`)

Solo permite tipos que hereden de una clase o implementen una interfaz:

```java
// Solo acepta Integer, Double, Float, Long...
static <T extends Number> double sumar(T a, T b) {
    return a.doubleValue() + b.doubleValue();
}
```

```java
// Solo acepta tipos que implementen Comparable
static <T extends Comparable<T>> T maximo(T a, T b) {
    return (a.compareTo(b) >= 0) ? a : b;
}
```

---

## Parámetros de tipo convencionales

| Letra | Significado |
|-------|-------------|
| `T` | Type (tipo genérico) |
| `E` | Element (colecciones) |
| `K` | Key (clave en maps) |
| `V` | Value (valor en maps) |
| `N` | Number |

---

## Ventajas de los genéricos

| Ventaja | Detalle |
|---------|---------|
| **Reutilización** | Una clase para muchos tipos |
| **Seguridad** | El compilador detecta errores de tipo |
| **Sin casting** | No necesitas `(Tipo)` al recuperar |

---

## Ejercicio propuesto

1. Crea `Repositorio<T>` con `ArrayList<T>` interno. Métodos: `agregar(T)`, `obtener(int)`, `tamano()`.
2. Usa `Repositorio<String>` para nombres y `Repositorio<Integer>` para notas.
3. Método genérico `invertirArray(T[] array)` que invierte el contenido.
4. Prueba con `String[]` e `Integer[]`.
