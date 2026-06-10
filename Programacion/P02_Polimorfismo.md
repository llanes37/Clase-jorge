# P02 — Polimorfismo en Java

> **Archivo de práctica:** `P02_Polimorfismo.java`  
> **PDF de referencia:** `Utilización avanzada de clases II. Polimorfismo.pdf`

---

## ¿Qué es el polimorfismo?

**Polimorfismo** = "muchas formas". El mismo método se comporta de manera diferente según el objeto que lo ejecuta.

Hay dos tipos principales en Java:

| Tipo | Cuándo se resuelve | Mecanismo |
|------|-------------------|-----------|
| **Sobreescritura** (Override) | En ejecución | Subclase redefine método del padre |
| **Sobrecarga** (Overload) | En compilación | Mismo nombre, distintos parámetros |

---

## 1. Polimorfismo por sobreescritura

```java
class Animal {
    public void hacerSonido() {
        System.out.println("Sonido genérico.");
    }
}

class Perro extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("GUAU GUAU!");
    }
}

class Gato extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("MIAU!");
    }
}
```

---

## 2. Upcasting (automático y seguro)

Guardar un objeto hijo en una variable de tipo padre:

```java
Animal a = new Perro("Rex", 3, "Labrador");  // upcasting
a.hacerSonido();  // llama al de Perro -> "GUAU GUAU!"
```

> Java decide en **tiempo de ejecución** qué método `@Override` llamar según el **tipo real** del objeto. Esto se llama **enlace dinámico** (dynamic binding).

---

## 3. Array polimórfico

```java
Animal[] animales = {
    new Perro("Rex", 3, "Labrador"),
    new Gato("Luna", 2, true),
    new Pajaro("Pico", 1, "Canario")
};

for (Animal a : animales) {
    a.hacerSonido();   // polimorfismo: cada uno hace el suyo
}
```

---

## 4. Downcasting e `instanceof`

```java
Animal a = new Perro("Rex", 3, "Labrador");

// Comprobar antes de hacer downcasting:
if (a instanceof Perro) {
    Perro p = (Perro) a;   // downcasting
    p.ladrar();
}
```

> **Regla de oro:** Siempre usa `instanceof` antes de hacer downcasting. Sin la comprobación puede lanzarse `ClassCastException` en tiempo de ejecución.

---

## 5. Sobrecarga (Overload)

```java
class Calculadora {
    public int    sumar(int a, int b)         { return a + b; }
    public double sumar(double a, double b)   { return a + b; }
    public int    sumar(int a, int b, int c)  { return a + b + c; }
}
```

- El compilador elige cuál llamar según el **tipo y número de parámetros**.
- **No es herencia**: puede ocurrir en la misma clase.

---

## Diferencias clave

| | Override | Overload |
|-|----------|---------|
| Se resuelve | Ejecución | Compilación |
| Requiere herencia | Sí | No |
| Firma del método | Idéntica | Diferente |
| Necesita `@Override` | Sí (buena práctica) | No aplica |

---

## Resumen visual

```
Animal[]  ──┬──> Perro  ->  hacerSonido()  ->  "GUAU GUAU!"
            ├──> Gato   ->  hacerSonido()  ->  "MIAU!"
            └──> Pajaro ->  hacerSonido()  ->  "PIIIO PIIIO!"
                             ↑
                    enlace dinámico en ejecución
```

---

## Ejercicio propuesto

1. Crea `Serpiente extends Animal` con `hacerSonido()` → `"SSSSS..."` y método `reptar()`.
2. Añade una `Serpiente` a un `ArrayList<Animal>`.
3. Recorre el array con `instanceof` y llama a `reptar()` cuando corresponda.
4. Prueba un downcasting incorrecto y observa `ClassCastException`.
