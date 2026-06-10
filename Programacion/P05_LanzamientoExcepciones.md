# P05 — Lanzamiento de Excepciones

> **Archivo de práctica:** `P05_LanzamientoExcepciones.java`  
> **PDF de referencia:** `Lanzamiento de excepciones.pdf`

---

## ¿Qué es una excepción?

Un evento que ocurre durante la ejecución y **altera el flujo normal** del programa. En Java las excepciones son objetos.

---

## Bloque `try-catch-finally`

```java
try {
    // código que puede lanzar excepción
    int r = dividir(10, 0);

} catch (ArithmeticException e) {
    // se ejecuta si ocurre esa excepción
    System.out.println("Error: " + e.getMessage());

} finally {
    // se ejecuta SIEMPRE (haya o no excepción)
    System.out.println("Fin de la operación.");
}
```

---

## `throw` — lanzar una excepción

```java
static void validarEdad(int edad) {
    if (edad < 0 || edad > 150) {
        throw new IllegalArgumentException("Edad inválida: " + edad);
    }
}
```

---

## `throws` — declarar que un método puede lanzar

```java
static void leerArchivo(String ruta) throws IOException {
    // el compilador obliga al que llame a este método a manejar IOException
    throw new IOException("Archivo no encontrado: " + ruta);
}
```

> `throws` va en la **firma** del método. `throw` va **dentro** del cuerpo.

---

## Checked vs Unchecked

| | Checked | Unchecked |
|-|---------|-----------|
| Hereda de | `Exception` | `RuntimeException` |
| El compilador obliga | ✅ (try-catch o throws) | ❌ |
| Ejemplos | `IOException`, `SQLException` | `NullPointerException`, `ArithmeticException` |
| Cuándo usar | Errores esperados y recuperables | Errores de programación |

---

## Múltiples `catch`

```java
try {
    // ...
} catch (NullPointerException e) {
    System.out.println("Null: " + e.getMessage());
} catch (IllegalArgumentException e) {
    System.out.println("Argumento inválido: " + e.getMessage());
}
```

> Los `catch` se comprueban **en orden**. Pon los más específicos primero.

---

## Multi-catch (Java 7+)

```java
catch (NullPointerException | ArithmeticException e) {
    System.out.println("Error: " + e.getClass().getSimpleName());
}
```

---

## Flujo de ejecución

```
try {
    linea A
    linea B  ← lanza excepción aquí
    linea C  ← NO se ejecuta
}
catch { ← salta aquí }
finally { ← siempre }
continúa aquí...
```

---

## Reglas resumen

| Regla | Detalle |
|-------|---------|
| `throw` | Dentro del cuerpo del método |
| `throws` | En la firma del método |
| `finally` | Siempre se ejecuta (incluso con `return`) |
| Checked | Obliga a try-catch o a propagar con `throws` |
| Unchecked | No obliga, pero se puede capturar |

---

## Ejercicio propuesto

1. Método `retirarDinero(double saldo, double cantidad)` que lanza `IllegalArgumentException` si la cantidad es inválida.
2. Llámalo con distintos valores y captura la excepción.
3. Añade `finally` que imprima `"Operación finalizada."`.
4. Crea un método `checked` con `throws IOException` y manejialo con `try-catch`.
