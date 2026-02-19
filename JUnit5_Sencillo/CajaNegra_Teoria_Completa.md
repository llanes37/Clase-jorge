# 🧪 Testing de Caja Negra con JUnit 5
## Teoría Completa — Entornos de Desarrollo · 1º DAM

---

## 📑 Índice

1. [¿Qué es el Testing?](#1-qué-es-el-testing)
2. [Tipos de Testing](#2-tipos-de-testing)
3. [Testing de Caja Negra](#3-testing-de-caja-negra-black-box)
4. [Técnica 1: Particiones de Equivalencia](#4-técnica-1-particiones-de-equivalencia)
5. [Técnica 2: Análisis de Valores Límite](#5-técnica-2-análisis-de-valores-límite-avl)
6. [Cómo diseñar casos de prueba paso a paso](#6-cómo-diseñar-casos-de-prueba-paso-a-paso)
7. [JUnit 5: Anotaciones y Asserts](#7-junit-5-anotaciones-y-asserts)
8. [Patrón AAA](#8-patrón-aaa-arrangeactassert)
9. [Ejemplo resuelto completo](#9-ejemplo-resuelto-completo)
10. [Errores típicos de examen](#10-errores-típicos-de-examen)
11. [Checklist examen](#11-checklist-examen)

---

## 1. ¿Qué es el Testing?

El testing es el proceso de **verificar que el software funciona correctamente** ejecutando el programa con entradas conocidas y comprobando que las salidas son las esperadas.

### ¿Por qué es importante?

- Detecta errores antes de que lleguen al usuario
- Documenta el comportamiento esperado del código
- Permite hacer cambios con confianza (si los tests siguen pasando, no hemos roto nada)
- Es **obligatorio** en entornos profesionales

### Vocabulario básico

| Término | Significado |
|---------|-------------|
| **Test / Caso de prueba** | Una ejecución concreta con unos datos de entrada y una salida esperada |
| **Test Suite** | Conjunto de tests agrupados |
| **Fallo (Failure)** | El test detecta que el resultado no es el esperado |
| **Error** | El test no puede ejecutarse (excepción inesperada, etc.) |
| **Cobertura** | % del código que es ejecutado por los tests |

---

## 2. Tipos de Testing

```
TIPOS DE TESTING
│
├── Por NIVEL
│   ├── Unitario ★ ← Este tema
│   ├── Integración
│   └── Sistema / E2E
│
└── Por ENFOQUE
    ├── Caja Negra ★ ← Este tema
    ├── Caja Blanca
    └── Caja Gris
```

### Por nivel

| Nivel | Prueba | Ejemplo |
|-------|--------|---------|
| **Unitario** | Un solo método/función | `sumar(2, 3)` → `5` |
| **Integración** | Varias clases juntas | El servicio de usuarios + base de datos |
| **Sistema (E2E)** | La aplicación entera | El usuario rellena un formulario y se registra |

### Por enfoque

| Enfoque | ¿Se ve el código? | Se basa en... |
|---------|-------------------|---------------|
| **Caja Negra** | ❌ NO | La especificación (lo que debe hacer) |
| **Caja Blanca** | ✅ SÍ | El código fuente (caminos, condiciones) |
| **Caja Gris** | Parcialmente | Combinación de ambas |

---

## 3. Testing de Caja Negra (Black Box)

En el testing de caja negra, el tester **no conoce ni necesita ver el código fuente**. Solo trabaja con:

```
ENTRADA ──→ [ FUNCIÓN DESCONOCIDA ] ──→ SALIDA
              (la "caja negra")
```

Solo sabes:
- **Qué recibe** el método (parámetros)
- **Qué devuelve** o qué efecto tiene (especificación)

### Ventajas

- No hace falta entender la implementación interna
- El tester puede trabajar en paralelo al desarrollador
- Encuentra errores en los "bordes" de las condiciones
- Se puede aplicar a cualquier nivel (unitario, integración, sistema)

### Principales técnicas de caja negra

1. **Particiones de Equivalencia** — Dividir las entradas en grupos con el mismo comportamiento
2. **Análisis de Valores Límite (AVL)** — Probar los extremos de cada partición
3. Tablas de decisión *(no entra en este tema)*
4. Casos de uso *(no entra en este tema)*

---

## 4. Técnica 1: Particiones de Equivalencia

### ¿Qué es?

Consiste en **dividir el dominio de entrada en grupos (particiones)** tal que todos los valores de un mismo grupo se comportan igual. Solo necesitas probar **un valor representativo** de cada partición.

### Tipos de particiones

- **Válidas**: Entradas que el método acepta y procesa normalmente
- **Inválidas**: Entradas que el método rechaza (lanza excepción, devuelve error, etc.)

### Ejemplo: `calcularCuota(int edad)`

```
Especificación:
  - edad < 0   → IllegalArgumentException
  - edad < 18  → 30€
  - 18 ≤ edad ≤ 64 → 60€
  - edad ≥ 65  → 40€
```

**Análisis de particiones:**

| Nº | Partición | Tipo | Valor representativo | Resultado esperado |
|----|-----------|------|---------------------|-------------------|
| P1 | edad < 0 | ❌ Inválida | -1 | IllegalArgumentException |
| P2 | 0 ≤ edad < 18 | ✅ Válida | 10 | 30€ |
| P3 | 18 ≤ edad ≤ 64 | ✅ Válida | 40 | 60€ |
| P4 | edad ≥ 65 | ✅ Válida | 70 | 40€ |

> **Regla de oro:** Con particiones de equivalencia, se necesita **mínimo 1 test por partición**.

### Ejemplo visual

```
         P1 (inválida)  P2 (válida)    P3 (válida)     P4 (válida)
         ──────────────┼──────────────┼───────────────┼────────────────→ edad
                       0              18              65
                    ↑                 ↑               ↑
               Excepción            30€             60€              40€
```

---

## 5. Técnica 2: Análisis de Valores Límite (AVL)

### ¿Qué es?

Los errores de programación **se concentran en los bordes** de las condiciones. Un `<` confundido con `<=`, un `>` con `>=`... La técnica AVL prueba los valores **exactamente en los límites** y sus inmediatamente adyacentes.

### Regla de los 3 valores por frontera

Para cada **frontera** entre dos particiones, probamos:

```
valor_límite - 1   ←── inmediatamente antes
valor_límite       ←── el límite exacto
valor_límite + 1   ←── inmediatamente después
```

### Ejemplo: `calcularCuota(int edad)` con AVL

Las fronteras están en **edad = 0**, **edad = 18** y **edad = 65**:

| Frontera | Valor -1 | Valor límite | Valor +1 |
|----------|----------|-------------|---------|
| edad = 0 | -1 → excepción | 0 → 30€ | 1 → 30€ |
| edad = 18 | 17 → 30€ | 18 → 60€ | 19 → 60€ |
| edad = 65 | 64 → 60€ | 65 → 40€ | 66 → 40€ |

> ⚠️ Estos son los valores que **más frecuentemente fallan** en código mal programado.

### ¿Por qué falla el código en los límites?

```java
// ¿Qué pasa si el programador pone < en vez de <=?
if (edad < 65) { ... }   // ← BUG: el de 65 años no cobra reducción
if (edad <= 65) { ... }  // ← CORRECTO

// El test de valores límite detecta exactamente este error:
assertEquals(40.0, cuota(65, false));  // ← este test FALLARÍA con el bug
```

---

## 6. Cómo diseñar casos de prueba paso a paso

### Proceso sistemático (para el examen)

```
1. LEER la especificación del método
         ↓
2. IDENTIFICAR las variables de entrada
         ↓
3. DEFINIR las PARTICIONES DE EQUIVALENCIA
   (válidas e inválidas)
         ↓
4. IDENTIFICAR las FRONTERAS (valores límite)
         ↓
5. ELEGIR valores representativos para cada partición
   + los valores límite detectados
         ↓
6. ESCRIBIR los tests en JUnit 5
```

### Ejemplo completo: `clasificarEmbarcacion(double eslora)`

**Especificación:**
```
eslora < 0  → IllegalArgumentException
eslora < 6  → "Pequeña"
6 ≤ eslora < 12 → "Mediana"
eslora ≥ 12 → "Grande"
```

**Paso 3 — Particiones:**

| Partición | Rango | Tipo | Representativo |
|-----------|-------|------|----------------|
| P1 | eslora < 0 | Inválida | -1.0 |
| P2 | 0 ≤ eslora < 6 | Válida | 3.0 |
| P3 | 6 ≤ eslora < 12 | Válida | 9.0 |
| P4 | eslora ≥ 12 | Válida | 15.0 |

**Paso 4 — Fronteras:**

| Frontera | Valores a probar |
|----------|-----------------|
| eslora = 0 | -0.01, 0.0, 0.01 |
| eslora = 6 | 5.99, 6.0, 6.01 |
| eslora = 12 | 11.99, 12.0, 12.01 |

**Paso 6 — Tests resultantes:**

```java
// Partición 1 (inválida)
assertThrows(IllegalArgumentException.class, () -> clasificar(-1.0));

// Partición 2 (representativo)
assertEquals("Pequeña", clasificar(3.0));

// Partición 3 (representativo)
assertEquals("Mediana", clasificar(9.0));

// Partición 4 (representativo)
assertEquals("Grande", clasificar(15.0));

// Valores límite de la frontera en 6.0
assertEquals("Pequeña", clasificar(5.99));  // justo antes
assertEquals("Mediana", clasificar(6.0));   // el límite exacto ← más probable que falle
assertEquals("Mediana", clasificar(6.01));  // justo después

// Valores límite de la frontera en 12.0
assertEquals("Mediana", clasificar(11.99));
assertEquals("Grande", clasificar(12.0));   // el límite exacto ← más probable que falle
assertEquals("Grande", clasificar(12.01));
```

---

## 7. JUnit 5: Anotaciones y Asserts

### Anotaciones principales

| Anotación | ¿Qué hace? | Obligatorio |
|-----------|-----------|-------------|
| `@Test` | Marca el método como test | ✅ |
| `@DisplayName("texto")` | Nombre legible en el runner | Recomendado |
| `@BeforeEach` | Se ejecuta antes de cada test | En examen si se pide |
| `@BeforeAll` | Se ejecuta una vez al inicio (static) | En examen si se pide |
| `@AfterEach` | Se ejecuta después de cada test | En examen si se pide |
| `@AfterAll` | Se ejecuta una vez al final (static) | En examen si se pide |
| `@ParameterizedTest` | Test que se repite con varios valores | Recomendado |
| `@ValueSource(...)` | Lista de valores para `@ParameterizedTest` | Con el anterior |
| `@CsvSource({...})` | Tabla de valores para `@ParameterizedTest` | Con el anterior |
| `@Disabled("motivo")` | Desactiva el test temporalmente | Solo si se pide |

### Asserts principales

```java
// Comparar valores iguales
// ! El ESPERADO siempre va PRIMERO
assertEquals(5, calc.sumar(2, 3));

// Comparar doubles (con margen de error)
assertEquals(3.5, calc.dividir(7, 2), 0.001);

// Comprobar que es verdadero
assertTrue(calc.esPrimo(7));

// Comprobar que es falso
assertFalse(calc.esPar(5));

// Comprobar que lanza una excepción
assertThrows(IllegalArgumentException.class,
    () -> calc.dividir(10, 0));

// Comprobar que NO es null
assertNotNull(resultado);

// Agrupar varios asserts (ejecuta todos aunque alguno falle)
assertAll("descripcion",
    () -> assertEquals(5, a),
    () -> assertTrue(b > 0),
    () -> assertFalse(c.isEmpty())
);
```

### Tests parametrizados

```java
// Con un solo valor por ejecución
@ParameterizedTest
@ValueSource(ints = {2, 3, 5, 7, 11})
void testPrimo(int numero) {
    assertTrue(esPrimo(numero));
}

// Con varios valores por ejecución (formato CSV)
@ParameterizedTest
@CsvSource({
    "2, 3, 5",       // sumar(2,3) = 5
    "0, 0, 0",       // sumar(0,0) = 0
    "-1, 1, 0"       // sumar(-1,1) = 0
})
void testSumar(int a, int b, int esperado) {
    assertEquals(esperado, sumar(a, b));
}
```

---

## 8. Patrón AAA (Arrange/Act/Assert)

Todo test bien escrito debe tener estas **tres fases claramente separadas**:

```java
@Test
@DisplayName("Calcular cuota de adulto = 60€")
void cuota_Adulto_Devuelve60() {

    // ARRANGE — Preparar los datos y objetos necesarios
    ClubNautico club = new ClubNautico();
    int edad = 30;
    boolean esVeterano = false;

    // ACT — Ejecutar el método que queremos probar
    double resultado = club.calcularCuotaSocio(edad, esVeterano);

    // ASSERT — Verificar que el resultado es el correcto
    assertEquals(60.0, resultado, 0.01);
}
```

### ¿Por qué es importante el patrón AAA?

- **Legibilidad:** Cualquiera entiende qué hace el test con solo leerlo
- **Mantenimiento:** Si falla, sabemos exactamente en qué fase
- **Estandarización:** Es el estándar de la industria

> ⚠️ En el examen, si te piden "escribe tests siguiendo AAA", **cada test debe tener los 3 comentarios**.

---

## 9. Ejemplo resuelto completo

### Enunciado

```
Método: calcularDescuento(double precio, int unidades)

Especificación:
  - precio <= 0 → IllegalArgumentException
  - unidades <= 0 → IllegalArgumentException
  - unidades < 10 → sin descuento (0%)
  - 10 ≤ unidades < 50 → 5% de descuento
  - unidades ≥ 50 → 10% de descuento
  Devuelve: precio total con descuento aplicado (precio * unidades * factor)
```

### Paso 1: Tabla de particiones

| # | Partición | Tipo | Rep. precio | Rep. unidades | Resultado |
|---|-----------|------|-------------|---------------|-----------|
| P1 | precio ≤ 0 | ❌ | -1.0 | 5 | Excepción |
| P2 | unidades ≤ 0 | ❌ | 10.0 | -1 | Excepción |
| P3 | unidades < 10 | ✅ | 10.0 | 5 | 50.00 (0%) |
| P4 | 10 ≤ unidades < 50 | ✅ | 10.0 | 20 | 190.00 (5%) |
| P5 | unidades ≥ 50 | ✅ | 10.0 | 60 | 540.00 (10%) |

### Paso 2: Valores límite

| Frontera | Valores límite a testear |
|----------|--------------------------|
| unidades = 10 | 9, 10, 11 |
| unidades = 50 | 49, 50, 51 |

### Paso 3: Tests en JUnit 5

```java
@Test
void descuento_PrecioNegativo_LanzaExcepcion() {
    assertThrows(IllegalArgumentException.class,
        () -> tienda.calcularDescuento(-1.0, 5));
}

@Test
void descuento_SinDescuento_5Unidades() {
    // 10.0 * 5 * 1.0 = 50.0
    assertEquals(50.0, tienda.calcularDescuento(10.0, 5), 0.01);
}

@Test
void descuento_5Porciento_20Unidades() {
    // 10.0 * 20 * 0.95 = 190.0
    assertEquals(190.0, tienda.calcularDescuento(10.0, 20), 0.01);
}

// Valores límite
@Test
void descuento_Limite9Unidades_SinDescuento() {
    assertEquals(90.0, tienda.calcularDescuento(10.0, 9), 0.01);  // límite -1
}
@Test
void descuento_Limite10Unidades_ConDescuento5() {
    assertEquals(95.0, tienda.calcularDescuento(10.0, 10), 0.01); // límite exacto
}
@Test
void descuento_Limite11Unidades_ConDescuento5() {
    assertEquals(104.5, tienda.calcularDescuento(10.0, 11), 0.01); // límite +1
}
```

---

## 10. Errores típicos de examen

### ❌ Error 1: Import de JUnit 4 en vez de JUnit 5

```java
import org.junit.Test;         // ❌ MAL — JUnit 4
import org.junit.jupiter.api.Test; // ✅ BIEN — JUnit 5
```

### ❌ Error 2: assertEquals con parámetros al revés

```java
assertEquals(resultado, 5);  // ❌ MAL — (real, esperado)
assertEquals(5, resultado);  // ✅ BIEN — (esperado, real)
// Si falla, el mensaje dice "expected: 5 but was: X"
// Si los pones al revés, dice "expected: X but was: 5" → confuso
```

### ❌ Error 3: @BeforeAll / @AfterAll sin static

```java
@BeforeAll
void setUp() { }          // ❌ MAL — falta static

@BeforeAll
static void setUp() { }   // ✅ BIEN
```

### ❌ Error 4: Test con modificador private

```java
@Test
private void testSumar() { }  // ❌ MAL — JUnit no puede ejecutarlo

@Test
void testSumar() { }          // ✅ BIEN — package-private es suficiente
```

### ❌ Error 5: assertThrows sin lambda

```java
// ❌ MAL — ejecuta el código antes de que assertThrows lo controle
assertThrows(Exception.class, calc.dividir(10, 0));

// ✅ BIEN — usar lambda () -> para envolver el código
assertThrows(Exception.class, () -> calc.dividir(10, 0));
```

### ❌ Error 6: Comparar doubles con assertEquals sin delta

```java
assertEquals(3.5, calc.dividir(7, 2));       // ❌ puede fallar por precisión
assertEquals(3.5, calc.dividir(7, 2), 0.001); // ✅ con margen de error
```

### ❌ Error 7: @ParameterizedTest sin fuente

```java
@ParameterizedTest
void testAlgo(int x) { }   // ❌ MAL — falta @ValueSource o @CsvSource

@ParameterizedTest
@ValueSource(ints = {1, 2, 3})
void testAlgo(int x) { }   // ✅ BIEN
```

### ❌ Error 8: No probar valores límite

Solo probar valores "del centro" de cada partición y olvidar los bordes. **El examen siempre pide valores límite.**

---

## 11. Checklist examen

Antes de entregar, repasa:

### Sobre los imports
- [ ] Todos los imports son de `org.junit.jupiter.api` *(NO `org.junit` a secas)*
- [ ] `import static org.junit.jupiter.api.Assertions.*` para los asserts

### Sobre las anotaciones
- [ ] Cada test tiene `@Test` o `@ParameterizedTest`
- [ ] Los métodos de test son `void` y no son `private`
- [ ] `@BeforeAll` y `@AfterAll` son `static`
- [ ] `@ParameterizedTest` tiene su `@ValueSource` o `@CsvSource`

### Sobre los asserts
- [ ] `assertEquals(ESPERADO, REAL)` — el esperado va **primero**
- [ ] `assertEquals` con doubles usa delta: `assertEquals(x, y, 0.001)`
- [ ] Excepciones con `assertThrows(Ex.class, () -> código)`

### Sobre el diseño de tests
- [ ] He identificado todas las **particiones de equivalencia**
- [ ] He probado los **valores límite** (el límite exacto, el -1 y el +1)
- [ ] He probado casos con **null** y **cadena vacía** si el método recibe String
- [ ] He probado tanto particiones **válidas** como **inválidas** (excepciones)
- [ ] Sigo el patrón **AAA** en cada test (Arrange / Act / Assert)
- [ ] Los nombres de test son descriptivos: `metodo_Escenario_ResultadoEsperado`

---

> 📄 Ver `ClubNauticoTest.java` para el ejemplo completo resuelto con todo lo anterior aplicado.
