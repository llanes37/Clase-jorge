# P06 — Jerarquía de Excepciones

> **Archivo de práctica:** `P06_JerarquiaExcepciones.java`  
> **PDF de referencia:** `Jerarquía de excepciones.pdf`

---

## Árbol de herencia de excepciones

```
Throwable
├── Error                    ← NO capturar (errores graves del sistema)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception                ← SÍ capturar (errores recuperables)
    ├── IOException           (checked)
    ├── SQLException          (checked)
    └── RuntimeException      (unchecked)
        ├── NullPointerException
        ├── ArithmeticException
        ├── IllegalArgumentException
        ├── ArrayIndexOutOfBoundsException
        └── ClassCastException
```

---

## Crear excepciones personalizadas

### Checked (extends Exception)

```java
class SaldoInsuficienteException extends Exception {
    private final double saldoActual;

    public SaldoInsuficienteException(double saldo, double pedido) {
        super("Saldo insuficiente. Tiene " + saldo + ", pide " + pedido);
        this.saldoActual = saldo;
    }

    public double getSaldoActual() { return saldoActual; }
}
```

```java
// Uso:
static void retirar(double saldo, double cantidad) throws SaldoInsuficienteException {
    if (cantidad > saldo) throw new SaldoInsuficienteException(saldo, cantidad);
}
```

### Unchecked (extends RuntimeException)

```java
class DatosInvalidosException extends RuntimeException {
    public DatosInvalidosException(String mensaje) {
        super(mensaje);
    }
}
```

---

## Orden de `catch`: siempre el más específico primero

```java
try {
    // ...
} catch (IOException e) {       // ← primero, más específico
    // ...
} catch (Exception e) {          // ← después, más genérico
    // ...
}
```

> Si pones `Exception` antes que `IOException`, el compilador da error: `"Exception already caught"`.

---

## Relanzar (wrapping)

```java
try {
    int r = Integer.parseInt(input);
} catch (NumberFormatException e) {
    // Envolvemos con más contexto y la causa original
    throw new RuntimeException("Error procesando '" + input + "'", e);
}
```

Acceder a la causa original:
```java
} catch (RuntimeException e) {
    System.out.println(e.getCause().getClass().getSimpleName());
}
```

---

## Buenas prácticas

| Regla | Motivo |
|-------|--------|
| Captura el tipo más específico | Más información del error |
| Nunca catch vacío | El error desaparece silenciosamente |
| No capturar `Error` | Son graves y no recuperables |
| Añadir mensaje descriptivo | El que lo maneje sabrá qué pasó |
| Agregar campos extra | `getSaldoActual()` da más contexto que solo el mensaje |

---

## ¿Cuándo usar checked vs unchecked?

| Situación | Tipo recomendado |
|-----------|-----------------|
| Fallo externo esperado (archivo, red, BD) | Checked (`extends Exception`) |
| Error de programación o datos de entrada | Unchecked (`extends RuntimeException`) |

---

## Ejercicio propuesto

1. Crea `ProductoNoEncontradoException` (checked) con campo `codigoProducto`.
2. Crea `StockNegativoException` (unchecked).
3. Crea métodos `buscarProducto(String codigo)` y `reducirStock(int cantidad)` que las lancen.
4. Captura ambas en `main` con mensajes descriptivos.
