# P03 — Interfaces en Java

> **Archivo de práctica:** `P03_Interfaces.java`  
> **PDF de referencia:** `Utilización avanzada de clases III. Interfaces.pdf`

---

## ¿Qué es una interfaz?

Una **interfaz** es un **contrato**: define *qué* debe hacer una clase, pero no *cómo*. La clase que la implementa (`implements`) rellena el cómo.

```java
interface Figura {
    double calcularArea();       // método abstracto (sin cuerpo)
    double calcularPerimetro();
}
```

---

## Implementar una interfaz

```java
class Circulo implements Figura {
    private double radio;

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }
}
```

> La clase **debe implementar todos los métodos abstractos** de la interfaz o el compilador da error.

---

## Múltiples interfaces

Una clase puede implementar **varias interfaces** a la vez (herencia múltiple de tipo):

```java
class Drone implements Volador, Fotografiable, Cargable {
    @Override public void volar()     { ... }
    @Override public void hacerFoto() { ... }
    @Override public void cargar()    { ... }
}
```

---

## Método `default` (Java 8+)

Puedes dar una implementación base en la propia interfaz. La clase puede usarla tal cual o sobreescribirla:

```java
interface Figura {
    default void describir() {
        System.out.println("Figura: " + getNombre());
    }
}
```

---

## Polimorfismo con interfaces

```java
Figura[] figuras = { new Circulo(5), new Rectangulo(4, 6) };
for (Figura f : figuras) {
    System.out.println(f.calcularArea());  // polimorfismo
}
```

Una variable de tipo interfaz puede apuntar a cualquier clase que la implemente.

---

## Interface vs Clase Abstracta

| | Interface | Clase Abstracta |
|-|-----------|----------------|
| Herencia múltiple | ✅ (varias) | ❌ (solo una) |
| Atributos de instancia | ❌ (solo constantes) | ✅ |
| Constructores | ❌ | ✅ |
| Métodos con cuerpo | `default` / `static` | ✅ |
| Cuándo usar | Contrato de capacidades | Base común con estado |

---

## Reglas resumen

| Regla | Detalle |
|-------|---------|
| `implements` | Palabra clave para implementar interfaces |
| Múltiples | `class A implements B, C, D` |
| Métodos abstractos | Son `public abstract` por defecto |
| Atributos | Son `public static final` por defecto |
| `default` | Método con cuerpo en la interfaz (Java 8+) |

---

## Ejercicio propuesto

1. Crea interfaz `Imprimible` con método `imprimir()`.
2. Haz que `Circulo` también implemente `Imprimible`.
3. Crea interfaz `Escalable` con `escalar(double factor)`.
4. Implementa `Escalable` en `Rectangulo`.
5. Prueba ambas en el `main`.
