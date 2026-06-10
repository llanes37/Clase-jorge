# P01 — Herencia en Java

> **Archivo de práctica:** `P01_Herencia.java`  
> **PDF de referencia:** `Utilización avanzada de clases I. Herencia.pdf`

---

## ¿Qué es la herencia?

La herencia permite que una clase (**hija / subclase**) reutilice los atributos y métodos de otra clase (**padre / superclase**) sin tener que repetir el código.

```
      Animal
     /      \
  Perro     Gato
```

---

## Palabra clave: `extends`

```java
class Perro extends Animal {
    // Perro hereda todo lo que Animal tiene (excepto private)
}
```

---

## Visibilidad y herencia

| Modificador   | Misma clase | Subclase | Paquete | Fuera |
|---------------|:-----------:|:--------:|:-------:|:-----:|
| `public`      | ✅          | ✅       | ✅      | ✅   |
| `protected`   | ✅          | ✅       | ✅      | ❌   |
| (sin mod.)    | ✅          | ✅*      | ✅      | ❌   |
| `private`     | ✅          | ❌       | ❌      | ❌   |

> (\*mismo paquete). Usa `protected` en atributos del padre si las subclases los necesitan directamente.

---

## Constructor y `super()`

El constructor de la subclase **siempre debe llamar al padre primero** con `super(...)`:

```java
class Perro extends Animal {
    private String raza;

    public Perro(String nombre, int edad, String raza) {
        super(nombre, edad);   // ← PRIMERA línea obligatoria
        this.raza = raza;
    }
}
```

> Si el padre tiene constructor vacío, Java lo llama automáticamente. Si no lo tiene, debes escribir `super(...)` a mano o el compilador da error.

---

## Sobreescritura de métodos: `@Override`

```java
class Animal {
    public void hacerSonido() {
        System.out.println("Sonido genérico.");
    }
}

class Gato extends Animal {
    @Override
    public void hacerSonido() {           // reemplaza el del padre
        System.out.println("MIAU!");
    }
}
```

- `@Override` no es obligatorio, pero **siempre se debe poner**: si escribes mal el nombre del método, el compilador te avisa.

---

## `super` en métodos normales

Puedes reutilizar el método del padre y añadir comportamiento extra:

```java
@Override
public void presentarse() {
    super.presentarse();                   // ejecuta el de Animal
    System.out.println("Raza: " + raza);  // añade info de Perro
}
```

---

## Reglas resumen

| Regla | Detalle |
|-------|---------|
| Herencia simple | En Java, una clase solo puede tener **un** padre |
| `super()` primero | Debe ser la **primera línea** del constructor hijo |
| `private` no se hereda | Usar `protected` o getters para acceso desde subclase |
| `@Override` | Siempre anotarlo al sobreescribir |
| `final class` | Una clase `final` no puede tener subclases |

---

## Resumen visual

```
Animal  ←───────────── extends ───────────── Perro
 - nombre (protected)                         - raza (private)
 - edad   (protected)                         + ladrar()
 + hacerSonido()    ←── @Override ──────────  + hacerSonido()
 + presentarse()    ←── @Override + super ──  + presentarse()
 + cumplirAnio()    ←── @Override + super ──  + cumplirAnio()
```

---

## Ejercicio propuesto

Crea una subclase `Pez extends Animal`:

1. Atributo `boolean aguaDulce`.
2. `@Override` de `hacerSonido()` → imprime `"... (silencio)"`.
3. `@Override` de `presentarse()` usando `super.presentarse()` y añadiendo el tipo de agua.
4. Método propio `nadar()`.

Solución de referencia en `P01_Herencia.java` (sección `// ✅`).
