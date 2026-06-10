# P09 — Recursividad

> **Archivo de práctica:** `P09_Recursividad.java`  
> **PDF de referencia:** `Recursividad.pdf`

---

## ¿Qué es la recursividad?

Un método es **recursivo** cuando **se llama a sí mismo** para resolver un problema, dividiéndolo en subproblemas más pequeños.

---

## Estructura obligatoria

Todo método recursivo necesita:

```java
static int metodoRecursivo(int n) {
    // 1. CASO BASE: condición de parada
    if (n <= 0) return 0;

    // 2. CASO RECURSIVO: llamada con datos reducidos
    return n + metodoRecursivo(n - 1);
}
```

> Sin el **caso base** el método se llamaría infinitamente hasta provocar `StackOverflowError`.

---

## Factorial

```
factorial(5) = 5 × factorial(4)
             = 5 × 4 × factorial(3)
             = 5 × 4 × 3 × factorial(2)
             = 5 × 4 × 3 × 2 × factorial(1)
             = 5 × 4 × 3 × 2 × 1 = 120
```

```java
static long factorial(int n) {
    if (n <= 1) return 1;              // caso base
    return n * factorial(n - 1);       // caso recursivo
}
```

---

## Fibonacci

```
fib(0)=0, fib(1)=1
fib(n) = fib(n-1) + fib(n-2)
Secuencia: 0, 1, 1, 2, 3, 5, 8, 13, 21...
```

```java
static int fibonacci(int n) {
    if (n == 0) return 0;   // caso base 1
    if (n == 1) return 1;   // caso base 2
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```

---

## Voltear un String

```
voltear("hola")
  = voltear("ola") + 'h'
  = voltear("la")  + 'o' + 'h'
  = voltear("a")   + 'l' + 'o' + 'h'
  = "a"            + 'l' + 'o' + 'h'
  = "aloh"
```

```java
static String voltear(String s) {
    if (s.length() <= 1) return s;
    return voltear(s.substring(1)) + s.charAt(0);
}
```

---

## Traza de ejecución (pila de llamadas)

```
factorial(4)
  └─ 4 × factorial(3)
           └─ 3 × factorial(2)
                    └─ 2 × factorial(1)
                               └─ 1  ← caso base
                    = 2 × 1 = 2
           = 3 × 2 = 6
  = 4 × 6 = 24
```

---

## Recursividad vs Iteración

| | Recursión | Iteración |
|-|-----------|-----------|
| Legibilidad | ✅ más clara en algunos casos | variable |
| Memoria | Más (apila llamadas) | Menos |
| Riesgo | `StackOverflowError` | sin ese riesgo |
| Cuándo usar | Problemas naturalmente recursivos | Loops simples |

---

## Reglas resumen

| Regla | Detalle |
|-------|---------|
| Siempre hay caso base | Sin él → bucle infinito |
| Los datos se reducen | Cada llamada debe acercarse al caso base |
| Traza antes de programar | Dibuja las llamadas a mano |
| `StackOverflowError` | Señal de que el caso base nunca se alcanza |

---

## Ejercicio propuesto

1. `esPalindromo(String s)` — compara el primer y el último carácter; si son iguales, llama recursivamente con la subcadena interior.
2. `mcd(int a, int b)` — Algoritmo de Euclides: `mcd(a,0)=a`, `mcd(a,b)=mcd(b, a%b)`.
3. Prueba `mcd(48,18)` → debe devolver `6`.
