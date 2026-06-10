# Guía Completa de Programación — 1º DAM

Guía de estudio que cubre todos los temas del módulo de Programación. Sigue el orden recomendado: cada tema usa conceptos del anterior.

---

## Índice de temas

| # | Tema | Archivo Java | Archivo MD |
|---|------|-------------|-----------|
| 1 | Herencia | `P01_Herencia.java` | `P01_Herencia.md` |
| 2 | Polimorfismo | `P02_Polimorfismo.java` | `P02_Polimorfismo.md` |
| 3 | Interfaces | `P03_Interfaces.java` | `P03_Interfaces.md` |
| 4 | Equals en Herencia | `P04_EqualsHerencia.java` | `P04_EqualsHerencia.md` |
| 5 | Lanzamiento de Excepciones | `P05_LanzamientoExcepciones.java` | `P05_LanzamientoExcepciones.md` |
| 6 | Jerarquía de Excepciones | `P06_JerarquiaExcepciones.java` | `P06_JerarquiaExcepciones.md` |
| 7 | Ficheros | `P07_Ficheros.java` | `P07_Ficheros.md` |
| 8 | Clases Genéricas | `P08_ClasesGenericas.java` | `P08_ClasesGenericas.md` |
| 9 | Recursividad | `P09_Recursividad.java` | `P09_Recursividad.md` |
| — | **Examen Final** | `EXAMEN_FINAL.java` | — |

---

## Cómo usar estos materiales

1. **Lee el `.md`** del tema para entender la teoría.
2. **Abre el `.java`** correspondiente en el IDE.
3. **Ejecuta** el archivo tal cual y observa la salida.
4. **Haz el `TODO Alumno`** al final del `main`.
5. Comprueba tu solución con la sección `// ✅ Solucion` (comentada).
6. Cuando domines todos los temas, intenta el **`EXAMEN_FINAL.java`**.

---

## Leyenda de comentarios (Better Comments)

| Símbolo | Color | Significado |
|---------|-------|------------|
| `// *` | Verde | Teoría y explicación |
| `// !` | Rojo | Punto crítico o regla importante |
| `// ?` | Azul | Aclaración o pregunta frecuente |
| `// TODO Alumno:` | Naranja | Ejercicio para hacer |
| `// ✅ Solucion` | — | Solución orientativa (comentada) |

> Instala el plugin **Better Comments** en VS Code o IntelliJ para ver los colores.

---

## Tema 1 — Herencia

La **herencia** permite que una clase hija reutilice atributos y métodos de la clase padre.

```java
class Animal {
    protected String nombre;
    public void hacerSonido() { System.out.println("Sonido genérico."); }
}

class Perro extends Animal {
    private String raza;

    public Perro(String nombre, int edad, String raza) {
        super(nombre, edad);   // ← llama al constructor del padre
        this.raza = raza;
    }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + ": GUAU!");
    }
}
```

**Reglas clave:**
- `extends` — herencia simple (solo un padre)
- `super(...)` — primera línea del constructor hijo
- `protected` — visible en subclases
- `@Override` — siempre al sobreescribir

---

## Tema 2 — Polimorfismo

El **polimorfismo** permite tratar objetos de distintas subclases de manera uniforme a través del tipo padre.

```java
Animal[] animales = { new Perro("Rex",3,"Lab"), new Gato("Luna",2,true) };

for (Animal a : animales) {
    a.hacerSonido();   // ← cada uno hace el suyo (enlace dinámico)
}
```

**Downcasting seguro:**
```java
if (a instanceof Perro) {
    Perro p = (Perro) a;
    p.ladrar();
}
```

---

## Tema 3 — Interfaces

Una **interfaz** es un contrato que define qué métodos debe implementar una clase.

```java
interface Figura {
    double calcularArea();
    double calcularPerimetro();

    default void describir() {
        System.out.println("Área: " + calcularArea());
    }
}

class Circulo implements Figura {
    private double radio;
    @Override public double calcularArea()      { return Math.PI * radio * radio; }
    @Override public double calcularPerimetro() { return 2 * Math.PI * radio; }
}
```

Una clase puede implementar **varias interfaces**:
```java
class Drone implements Volador, Fotografiable, Cargable { ... }
```

---

## Tema 4 — Equals en Herencia

Por defecto `==` compara referencias. Para comparar contenido hay que sobreescribir `equals()` y `hashCode()`.

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Producto otro = (Producto) obj;
    return Objects.equals(codigo, otro.codigo);
}

@Override
public int hashCode() {
    return Objects.hash(codigo);
}
```

**En herencia:** llama a `super.equals(obj)` primero y luego compara los campos propios.

---

## Tema 5 — Lanzamiento de Excepciones

```java
// throw: lanzar excepción dentro del método
static void validarEdad(int edad) {
    if (edad < 0) throw new IllegalArgumentException("Edad inválida: " + edad);
}

// throws: declarar que el método puede lanzar una excepción checked
static void leerArchivo(String ruta) throws IOException {
    throw new IOException("Archivo no encontrado.");
}

// try-catch-finally
try {
    validarEdad(-5);
} catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
} finally {
    System.out.println("Siempre se ejecuta.");
}
```

---

## Tema 6 — Jerarquía de Excepciones

```
Throwable
├── Error         (no capturar: OutOfMemoryError, StackOverflowError)
└── Exception
    ├── IOException       (checked)
    └── RuntimeException  (unchecked)
        ├── NullPointerException
        └── ArithmeticException
```

**Crear excepciones propias:**
```java
class SaldoInsuficienteException extends Exception {       // checked
    public SaldoInsuficienteException(String msg) { super(msg); }
}

class DatosInvalidosException extends RuntimeException {   // unchecked
    public DatosInvalidosException(String msg) { super(msg); }
}
```

---

## Tema 7 — Ficheros

```java
// Escribir
try (BufferedWriter bw = new BufferedWriter(new FileWriter("notas.txt"))) {
    bw.write("Línea 1");
    bw.newLine();
}

// Leer
try (BufferedReader br = new BufferedReader(new FileReader("notas.txt"))) {
    String linea;
    while ((linea = br.readLine()) != null) {
        System.out.println(linea);
    }
}
```

**Modos:**
- `new FileWriter("f.txt")` → sobreescribe
- `new FileWriter("f.txt", true)` → append

---

## Tema 8 — Clases Genéricas

```java
class Caja<T> {
    private T contenido;
    public Caja(T contenido) { this.contenido = contenido; }
    public T getContenido() { return contenido; }
}

Caja<String>  c1 = new Caja<>("Hola");
Caja<Integer> c2 = new Caja<>(42);
```

**Tipo acotado:**
```java
static <T extends Number> double sumar(T a, T b) {
    return a.doubleValue() + b.doubleValue();
}
```

---

## Tema 9 — Recursividad

Un método recursivo tiene **caso base** (parada) y **caso recursivo** (llamada con datos reducidos):

```java
static long factorial(int n) {
    if (n <= 1) return 1;              // caso base
    return n * factorial(n - 1);       // caso recursivo
}
```

```
factorial(4)  =  4 × factorial(3)  =  4 × 3 × 2 × 1  =  24
```

---

## Conexiones entre temas

```
Herencia (P01)
    └── Polimorfismo (P02)     — necesita herencia para el enlace dinámico
    └── Interfaces  (P03)     — contrato sin herencia de implementación
    └── Equals      (P04)     — sobreescribir en jerarquía de herencia
    └── Excepciones (P05/P06) — crear excepciones propias extiende Exception
Ficheros    (P07)             — tema independiente (usa checked exceptions)
Genéricos   (P08)             — tema independiente (mejora reutilización)
Recursividad(P09)             — tema independiente (técnica de diseño)
```

---

## Checklist de preparación para el examen

- [ ] Sé crear una clase hija con `extends` y `super()`
- [ ] Sé sobreescribir métodos con `@Override`
- [ ] Entiendo el polimorfismo con arrays de tipo padre
- [ ] Sé usar `instanceof` antes de un downcasting
- [ ] Sé crear e implementar una interfaz
- [ ] Sé sobreescribir `equals()` y `hashCode()` correctamente
- [ ] Entiendo la diferencia entre `throw` y `throws`
- [ ] Sé la diferencia entre excepciones checked y unchecked
- [ ] Sé crear excepciones propias
- [ ] Sé leer y escribir ficheros con `BufferedReader`/`BufferedWriter`
- [ ] Entiendo qué es una clase genérica `<T>`
- [ ] Sé identificar el caso base y el caso recursivo
