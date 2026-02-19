# 📘 Teoria Completa: Testing de Caja Negra con JUnit 5

> Guía de repaso orientada a examen (DAM/DAW)  
> Tema central: **diseñar tests sin mirar el código interno**

---

## 🧭 1. Qué es Testing de Caja Negra

El **testing de caja negra** valida el comportamiento de un método o sistema usando:
- **Entradas**
- **Salidas esperadas**
- **Reglas de negocio (especificación)**

Sin usar:
- implementación interna
- estructura del código
- variables internas

Modelo mental:

`ENTRADA -> [caja negra] -> SALIDA`

Objetivo:
- comprobar que el software cumple lo que promete la especificación.

---

## 🎯 2. Objetivo en examen

Cuando te dan un enunciado de caja negra, tu trabajo es:
1. Identificar reglas y restricciones.
2. Diseñar casos con técnica formal.
3. Escribir tests JUnit 5 claros y trazables.
4. Cubrir casos válidos, inválidos y límites.

---

## 🧪 3. Técnicas clave de Caja Negra

## 3.1 Particiones de Equivalencia

Idea:
- agrupar entradas que deben comportarse igual.

Regla práctica:
- mínimo 1 test por partición relevante.

Tipos:
- ✅ particiones válidas (resultado normal)
- ❌ particiones inválidas (error o excepción)

Ejemplo (edad):
- `edad < 0` -> inválida (excepción)
- `0..17` -> válida
- `18..64` -> válida
- `>=65` -> válida

---

## 3.2 Análisis de Valores Límite (AVL)

Los fallos aparecen mucho en fronteras por errores de `<` vs `<=`.

Para cada frontera, prueba:
- límite - 1
- límite exacto
- límite + 1

Ejemplo frontera `18`:
- `17`, `18`, `19`

Ejemplo frontera `6.0`:
- `5.9`, `6.0`, `6.1`

---

## 3.3 Combinación recomendada

Secuencia sólida:
1. Particiones inválidas.
2. Particiones válidas.
3. Límites de cada frontera.
4. Casos combinados con parametrizados.

---

## 🏗️ 4. Estructura mental para diseñar tests

Para cada método, rellena esta plantilla:

```text
Metodo: nombreMetodo(parametros)

Entradas:
- ...

Reglas:
- ...

Particiones:
- P1 (invalida): ...
- P2 (valida): ...
- P3 (valida): ...

Limites:
- frontera X: X-1, X, X+1

Asserts:
- assertEquals / assertThrows / assertTrue / assertFalse
```

---

## ⚙️ 5. JUnit 5: lo imprescindible

Imports correctos:

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
```

Anotaciones clave:
- `@Test`: marca un test.
- `@DisplayName("...")`: nombre legible.
- `@BeforeEach`: antes de cada test.
- `@BeforeAll`: una vez al inicio (debe ser `static`).
- `@AfterAll`: una vez al final (debe ser `static`).
- `@ParameterizedTest`: mismo test con varios datos.

Asserts más usados:
- `assertEquals(esperado, real)`
- `assertEquals(esperado, real, delta)` para `double`
- `assertTrue(condicion)`
- `assertFalse(condicion)`
- `assertThrows(Ex.class, () -> codigo)`
- `assertAll("grupo", () -> ..., () -> ...)`

---

## 🧩 6. Patrón AAA (obligatorio en la práctica)

AAA = **Arrange - Act - Assert**

```java
// ARRANGE
int edad = 30;

// ACT
double cuota = club.calcularCuotaSocio(edad, false);

// ASSERT
assertEquals(60.0, cuota, 0.01);
```

Ventajas:
- lectura rápida
- menos errores
- mantenimiento más fácil

---

## 🧮 7. Cómo testear números decimales

Nunca compares `double` sin tolerancia cuando hay operaciones:

```java
assertEquals(120.0, resultado, 0.01);
```

`0.01` = margen de error permitido.

---

## 🚨 8. Excepciones: cuándo y cómo probarlas

Si el enunciado dice “debe lanzar excepción”:

```java
assertThrows(
    IllegalArgumentException.class,
    () -> servicio.metodoInvalido(...)
);
```

Errores típicos:
- usar `try/catch` manual innecesario
- no comprobar el tipo exacto de excepción
- olvidar casos inválidos (`null`, negativos, formato incorrecto)

---

## 🧱 9. Validaciones de formato (strings)

Para formatos tipo matrícula/código:
- `null`
- longitud incorrecta
- caracteres en posiciones clave
- caso válido completo

Ejemplo de idea:
- formato esperado `LLDDDDLL`
- caso válido `AB1234CD`
- caso inválido por longitud `AB123CD`

---

## 📊 10. Diseño mínimo por método (checklist)

- [ ] 1 caso inválido crítico
- [ ] 1 caso válido por partición
- [ ] límites en cada frontera
- [ ] pruebas con mayúsculas/minúsculas si aplica
- [ ] parametrizado para cubrir combinaciones
- [ ] nombres de test claros (`DisplayName`)

---

## 🧠 11. Errores frecuentes en examen

1. Usar imports de JUnit 4 (`org.junit.*`) en lugar de JUnit 5.
2. No probar entradas inválidas.
3. Olvidar el límite exacto.
4. Comparar `double` sin delta.
5. Tests sin relación clara con el enunciado.
6. `@BeforeAll` y `@AfterAll` no `static`.
7. Nombres de test ambiguos.
8. No usar parametrizados cuando hay tabla de casos.

---

## 🗂️ 12. Plantilla rápida para escribir tests en examen

```java
@Test
@DisplayName("[P1-INV] descripcion del caso")
void metodo_CasoEsperado() {
    // ARRANGE
    // datos de entrada

    // ACT
    // llamada al metodo

    // ASSERT
    // comprobacion esperada
}
```

Parametrizado:

```java
@ParameterizedTest
@CsvSource({
    "entrada1, esperado1",
    "entrada2, esperado2"
})
void metodo_VariosCasos(tipo entrada, tipo esperado) {
    assertEquals(esperado, servicio.metodo(entrada));
}
```

---

## 📝 13. Estrategia de resolución en examen (paso a paso)

1. Leer enunciado y subrayar reglas.
2. Listar particiones válidas e inválidas.
3. Marcar fronteras numéricas.
4. Diseñar tabla de casos.
5. Escribir tests base (uno por partición).
6. Añadir límites.
7. Añadir parametrizados.
8. Revisar cobertura de reglas.

---

## 🧾 14. Ejemplo de tabla de diseño (modelo)

| ID | Entrada | Tipo | Esperado |
|---|---|---|---|
| P1 | valor negativo | inválida | excepción |
| P2 | tramo bajo | válida | salida A |
| P3 | tramo medio | válida | salida B |
| P4 | tramo alto | válida | salida C |
| L1 | límite-1 | límite | salida previa |
| L2 | límite exacto | límite | salida nueva |
| L3 | límite+1 | límite | salida nueva |

---

## ✅ 15. Qué debe tener una solución “de 10”

- Cobertura completa de reglas del enunciado.
- Particiones bien definidas.
- AVL correcto en todas las fronteras.
- Código de test limpio y legible.
- Uso correcto de JUnit 5.
- Casos parametrizados donde aporta valor.
- Comentarios útiles (sin ruido).

---

## 🔁 16. Repaso ultra-rápido (1 minuto antes del examen)

- Caja negra = especificación, no implementación.
- Haz inválidos primero.
- Cubre cada partición.
- No olvides límite exacto.
- `double` con delta.
- Excepciones con `assertThrows`.
- Si hay tabla de casos, usa `@ParameterizedTest`.

---

## 📚 17. Práctica recomendada

Repite la misma metodología en dominios distintos:
- club náutico
- biblioteca
- gimnasio
- videoclub

Si sabes diseñar por reglas + particiones + límites, puedes resolver cualquier ejercicio de caja negra de este tipo.

