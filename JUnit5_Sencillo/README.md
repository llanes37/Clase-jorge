# JUnit 5 — Resumen Rápido para Examen

## ¿Qué es JUnit?
Framework para hacer **tests automáticos** en Java. Escribes código que comprueba si tus métodos funcionan bien.

---

## Lo básico en 2 minutos

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MiClaseTest {
    
    @Test
    void testSumar() {
        // ARRANGE - preparar
        int a = 2, b = 3;
        
        // ACT - ejecutar
        int resultado = a + b;
        
        // ASSERT - comprobar
        assertEquals(5, resultado);  // ¡El ESPERADO va primero!
    }
}
```

---

## Asserts principales

| Assert | Para qué | Ejemplo |
|--------|----------|---------|
| `assertEquals(esperado, real)` | Comparar valores | `assertEquals(5, calc.sumar(2,3))` |
| `assertTrue(condición)` | Debe ser true | `assertTrue(edad >= 18)` |
| `assertFalse(condición)` | Debe ser false | `assertFalse(lista.isEmpty())` |
| `assertThrows(Ex.class, lambda)` | Esperar excepción | `assertThrows(Exception.class, () -> dividir(5,0))` |

---

## Ciclo de vida

```java
@BeforeAll  static void antesDeNada() { }   // 1 vez al inicio (STATIC)
@BeforeEach void antesDeCadaTest() { }      // Antes de CADA test
@AfterEach  void despuesDeCadaTest() { }    // Después de CADA test  
@AfterAll   static void alFinal() { }       // 1 vez al final (STATIC)
```

---

## Tests parametrizados

```java
@ParameterizedTest
@ValueSource(ints = {2, 3, 5, 7, 11})
void testPrimos(int num) {
    assertTrue(esPrimo(num));
}

@ParameterizedTest
@CsvSource({"2,3,5", "0,0,0", "-1,1,0"})
void testSumar(int a, int b, int esperado) {
    assertEquals(esperado, sumar(a, b));
}
```

---

## Checklist examen

- [ ] Imports de `org.junit.jupiter` (NO `org.junit` solo)
- [ ] Tests en `src/test/java`
- [ ] Métodos con `@Test` y `void`
- [ ] `assertEquals(ESPERADO, REAL)` — esperado primero
- [ ] `@BeforeAll` y `@AfterAll` son `static`
- [ ] AAA: Arrange, Act, Assert

---

## Ejecutar en Eclipse

1. Click derecho en la clase → **Run As → JUnit Test**
2. Barra verde = OK, roja = fallo

---

> 📄 Ver `CalculadoraConTests.java` para ejemplo completo
