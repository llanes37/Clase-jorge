# P04 — Equals en Herencia

> **Archivo de práctica:** `P04_EqualsHerencia.java`  
> **PDF de referencia:** `Equals en herencia.pdf`

---

## El problema: `==` vs `.equals()`

```java
Producto p1 = new Producto("P001", "Teclado", 29.99);
Producto p2 = new Producto("P001", "Teclado", 29.99);

p1 == p2        // false — compara referencias (dirección en memoria)
p1.equals(p2)   // true  — compara contenido (si sobreescribimos equals)
```

> Por defecto `equals()` hace lo mismo que `==`. Hay que sobreescribirlo para comparar por contenido.

---

## Cómo sobreescribir `equals()` correctamente

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;               // 1. reflexividad
    if (obj == null) return false;              // 2. no nulo
    if (getClass() != obj.getClass()) return false; // 3. mismo tipo
    Producto otro = (Producto) obj;             // 4. casting
    return Objects.equals(codigo, otro.codigo); // 5. comparar campos
}
```

---

## El contrato de `equals()`

| Propiedad | Regla |
|-----------|-------|
| **Reflexiva** | `a.equals(a)` → `true` |
| **Simétrica** | `a.equals(b)` == `b.equals(a)` |
| **Transitiva** | si `a=b` y `b=c`, entonces `a=c` |
| **Consistente** | mismo resultado si el estado no cambia |
| **No nula** | `a.equals(null)` → `false` (nunca NPE) |

---

## `hashCode()` — siempre junto a `equals()`

**Regla de oro:** si `a.equals(b) == true` → `a.hashCode() == b.hashCode()`.

```java
@Override
public int hashCode() {
    return Objects.hash(codigo);   // los mismos campos que en equals()
}
```

> Si no sobreescribes `hashCode()`, las colecciones como `HashSet` o `HashMap` **no funcionarán correctamente** con tu clase.

---

## `equals()` en herencia

En una subclase llamas primero al `equals()` del padre y luego comparas los campos propios:

```java
class ProductoDigital extends Producto {
    private String urlDescarga;

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;       // delega al padre
        ProductoDigital otro = (ProductoDigital) obj;
        return Objects.equals(urlDescarga, otro.urlDescarga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), urlDescarga);
    }
}
```

---

## `getClass()` vs `instanceof`

| | `getClass()` | `instanceof` |
|-|-------------|-------------|
| `new Perro().equals(new Animal())` | false | puede ser true |
| Recomendado para igualdad estricta | ✅ | ⚠️ puede romper simetría |

> Usa `getClass()` en `equals()` para garantizar simetría entre padre e hijo.

---

## Uso en colecciones

```java
HashSet<Producto> set = new HashSet<>();
set.add(p1);
set.add(p2);  // equals() == true → no se añade (duplicado)
System.out.println(set.size()); // 1
```

---

## Ejercicio propuesto

1. Crea `Cliente` con atributos `id`, `nombre`, `email`. Compara solo por `id`.
2. Sobreescribe `equals()` y `hashCode()` usando `Objects.hash(id)`.
3. Crea `ClienteVIP extends Cliente` con `nivel`. Sobreescribe `equals()` con `super.equals()` + comparar `nivel`.
4. Prueba que `cliente.equals(null)` no lanza `NullPointerException`.
