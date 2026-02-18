# 🧪 JUnit 5 con Maven — Mini-Tema Completo

## Entornos de Desarrollo · 1º DAM

> **Autor:** Material didáctico para clase particular  
> **Fecha:** Febrero 2026  
> **IDE principal:** Eclipse · Compatible con VS Code  
> **Requisitos previos:** Java básico (clases, métodos, excepciones) y haber usado Eclipse

---

## 📑 Índice

1. [¿Qué es el testing?](#1--qué-es-el-testing)
2. [¿Qué es JUnit 5?](#2--qué-es-junit-5)
3. [¿Qué es Maven?](#3--qué-es-maven)
4. [Instalación y preparación](#4--instalación-y-preparación)
5. [Estructura de proyecto Maven](#5--estructura-de-proyecto-maven)
6. [Conceptos clave con ejemplos](#6--conceptos-clave-con-ejemplos)
7. [Buenas prácticas](#7--buenas-prácticas)
8. [Cómo ejecutar tests](#8--cómo-ejecutar-tests)
9. [Errores típicos y cómo solucionarlos](#9--errores-típicos-y-cómo-solucionarlos)
10. [Ejercicios para el alumno](#10--ejercicios-para-el-alumno)
11. [Mini checklist final para examen](#11--mini-checklist-final-para-examen)

---

## 1. 🔍 ¿Qué es el testing?

**Testing** (pruebas de software) es el proceso de verificar que un programa funciona correctamente. En lugar de ejecutar la aplicación entera y probar a mano, escribimos **código que prueba otro código** de forma automática.

### Tipos de pruebas (simplificado)

| Tipo | ¿Qué prueba? | Ejemplo |
|------|--------------|---------|
| **Unitarias** | Un solo método/función aislado | `sumar(2, 3)` devuelve `5` |
| **Integración** | Varias clases trabajando juntas | El servicio de usuarios se conecta a la base de datos |
| **Sistema / E2E** | La aplicación completa | Un usuario puede registrarse desde el formulario web |

> ⚠️ **En este tema nos centramos en pruebas UNITARIAS** — las más básicas y fundamentales.

### ¿Por qué hacer tests?

- **Detectar errores pronto:** antes de que lleguen a producción.
- **Documentar el código:** los tests muestran cómo debe usarse cada método.
- **Refactorizar con confianza:** cambias código interno y los tests confirman que no has roto nada.
- **Ahorro de tiempo:** probar a mano es lento y propenso a errores humanos.

---

## 2. 🧪 ¿Qué es JUnit 5?

**JUnit** es el framework de testing más usado en Java. La versión 5 (también llamada **JUnit Jupiter**) es la más moderna.

### Arquitectura de JUnit 5 (simplificada)

```
JUnit 5
  ├── JUnit Jupiter  → API para escribir tests (@Test, asserts, etc.)
  ├── JUnit Platform → Motor que ejecuta los tests
  └── JUnit Vintage  → Compatibilidad con JUnit 3 y 4 (no lo usamos)
```

> 📌 Cuando decimos "JUnit 5" normalmente nos referimos a **JUnit Jupiter**.

### ¿Qué aporta JUnit 5 respecto a JUnit 4?

- Anotaciones más claras (`@BeforeEach` en vez de `@Before`)
- Tests parametrizados integrados (sin librerías extra)
- `@DisplayName` para nombres legibles
- `assertAll` para agrupar asserts
- `assertThrows` más limpio para excepciones
- Tags, timeouts, tests condicionales...

---

## 3. 📦 ¿Qué es Maven?

**Maven** es una herramienta de **gestión de proyectos y dependencias** para Java. Sirve para:

1. **Organizar** el proyecto con una estructura estándar de carpetas.
2. **Descargar librerías** automáticamente (como JUnit 5) desde internet.
3. **Compilar y ejecutar tests** con un solo comando.

### El archivo `pom.xml`

Es el "corazón" de Maven. Ahí defines:
- **groupId:** identificador del grupo/organización (ej: `com.entornos`)
- **artifactId:** nombre del proyecto (ej: `junit5-clase`)
- **version:** versión del proyecto
- **dependencies:** librerías que necesitas (JUnit 5)
- **plugins:** herramientas adicionales (Surefire para ejecutar tests)

> 💡 Maven descarga las dependencias automáticamente al abrir el proyecto o al ejecutar `mvn test`.

---

## 4. 🔧 Instalación y preparación

### 4.1 En Eclipse (IDE principal) — Paso a paso

#### Paso 1: Verificar que tienes Java instalado

Abre un terminal y ejecuta:
```bash
java -version
```
Debe mostrar Java 11 o superior. Si no, descarga e instala [Eclipse Temurin JDK 17](https://adoptium.net/).

#### Paso 2: Crear proyecto Maven en Eclipse

1. `File → New → Maven Project`
2. Marca ✅ **"Create a simple project (skip archetype selection)"**
3. Rellena:
   - **Group Id:** `com.entornos`
   - **Artifact Id:** `junit5-clase`
   - **Version:** `1.0` (o deja `0.0.1-SNAPSHOT`)
   - **Packaging:** `jar`
4. Click en **Finish**

#### Paso 3: Configurar el `pom.xml`

Eclipse genera un `pom.xml` básico. Debes añadir:
- La dependencia de JUnit Jupiter
- El plugin Surefire (para ejecutar tests)
- La versión de Java

> 📄 Copia el `pom.xml` que te doy en este proyecto (Bloque 2).

#### Paso 4: Actualizar Maven

Después de cambiar el `pom.xml`:
1. Click derecho sobre el proyecto → `Maven → Update Project...`
2. Marca tu proyecto y pulsa **OK**
3. Espera a que descargue las dependencias (esquina inferior derecha)

#### Paso 5: Crear la estructura de carpetas

Si no se crearon automáticamente, crea:
- `src/main/java` → click derecho → `New → Source Folder`
- `src/test/java` → click derecho → `New → Source Folder`

Dentro de cada una, crea el paquete `com.entornos`.

#### Paso 6: Verificar

- En `src/main/java/com/entornos/` → pon tus clases normales
- En `src/test/java/com/entornos/` → pon tus clases de test
- Ejecuta: Click derecho en la clase test → `Run As → JUnit Test`

---

### 4.2 En VS Code — Paso a paso

#### Paso 1: Instalar extensiones necesarias

Abre VS Code y busca en Extensions (`Ctrl+Shift+X`):
1. **Extension Pack for Java** (Microsoft) — incluye soporte Java completo
2. **Maven for Java** (Microsoft) — gestión de Maven
3. **Test Runner for Java** (Microsoft) — ejecución visual de tests

#### Paso 2: Instalar Maven (si no lo tienes)

Opción A — Con `winget` (Windows):
```powershell
winget install Apache.Maven
```

Opción B — Manual:
1. Descarga Maven desde [maven.apache.org](https://maven.apache.org/download.cgi)
2. Descomprime y añade la carpeta `bin` a la variable de entorno `PATH`

Verifica:
```bash
mvn -version
```

#### Paso 3: Abrir el proyecto

1. `File → Open Folder...` → selecciona la carpeta del proyecto (donde está `pom.xml`)
2. VS Code detectará Maven automáticamente y descargará dependencias
3. Si no, abre un terminal y ejecuta: `mvn clean install`

#### Paso 4: Ejecutar tests

- **Visual:** En la barra lateral aparece el icono de Testing (matraz/frasco). Haz click y verás los tests listados. Pulsa ▶ para ejecutar.
- **Terminal:** `mvn test`

> ⚠️ **Nota:** Eclipse es más sencillo para principiantes con Maven. VS Code requiere tener Maven instalado por separado.

---

## 5. 📁 Estructura de proyecto Maven

```
junit5-clase/                  ← Carpeta raíz del proyecto
├── pom.xml                    ← Configuración Maven (dependencias, plugins)
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── entornos/
│   │               ├── Calculadora.java          ← Clase "de producción"
│   │               └── ValidadorUsuario.java     ← Otra clase "de producción"
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── entornos/
│                   ├── CalculadoraTest.java       ← Tests de Calculadora
│                   └── ValidadorUsuarioTest.java  ← Tests de ValidadorUsuario
│
└── target/                    ← Carpeta generada por Maven (compilados, informes)
```

### Reglas importantes

| Regla | Ejemplo |
|-------|---------|
| El código "de verdad" va en `src/main/java` | `Calculadora.java` |
| Los tests van en `src/test/java` | `CalculadoraTest.java` |
| **El paquete DEBE ser el mismo** en main y test | `com.entornos` en ambos |
| Las clases de test se nombran `XxxTest.java` | `CalculadoraTest.java` |
| Maven busca tests en `src/test/java` automáticamente | No hace falta configurar nada extra |

> ❗ **Error frecuente de examen:** poner los tests en `src/main/java`. **MÁL.** Los tests NUNCA van en main.

---

## 6. 🎯 Conceptos clave con ejemplos

### 6.1 La anotación `@Test`

Marca un método como test. JUnit lo ejecutará automáticamente.

```java
import org.junit.jupiter.api.Test;          // ! Siempre de org.junit.jupiter (JUnit 5)
import static org.junit.jupiter.api.Assertions.*; // * Para usar assertEquals, assertTrue, etc.

class CalculadoraTest {

    @Test   // * Esto marca el método como un TEST
    void testSumar() {
        Calculadora calc = new Calculadora();
        int resultado = calc.sumar(2, 3);
        assertEquals(5, resultado);  // * ¿El resultado es 5? Si no, FALLA
    }
}
```

> ⚠️ Los métodos de test **no devuelven nada** (`void`) y **no reciben parámetros** (salvo en tests parametrizados).

---

### 6.2 Asserts principales

Los **asserts** son los métodos que comprueban si algo es correcto. Si fallan, el test falla.

```java
// * assertEquals(esperado, real) → ¿Son iguales?
assertEquals(5, calc.sumar(2, 3));
assertEquals("hola", texto.toLowerCase());

// * assertTrue(condición) → ¿Es verdadero?
assertTrue(calc.esNumeroPrimo(7));
assertTrue(edad >= 18);

// * assertFalse(condición) → ¿Es falso?
assertFalse(calc.esNumeroPrimo(4));

// * assertNotNull(objeto) → ¿No es null?
assertNotNull(usuario.getNombre());

// * assertNull(objeto) → ¿Es null?
assertNull(resultado);

// * assertThrows(Excepcion.class, () -> código) → ¿Lanza excepción?
assertThrows(ArithmeticException.class, () -> calc.dividir(10, 0));

// * assertAll → Ejecuta TODOS los asserts aunque alguno falle
assertAll("Operaciones básicas",
    () -> assertEquals(5, calc.sumar(2, 3)),
    () -> assertEquals(1, calc.restar(3, 2)),
    () -> assertEquals(6, calc.multiplicar(2, 3))
);
```

> 💡 **assertAll** es muy útil: si usas varios `assertEquals` sueltos, al fallar el primero ya no se ejecutan los demás. Con `assertAll`, ves TODOS los fallos a la vez.

---

### 6.3 Ciclo de vida de los tests

JUnit permite ejecutar código **antes y después** de cada test o de toda la clase.

```java
class EjemploCicloVida {

    @BeforeAll       // * Se ejecuta UNA VEZ antes de TODOS los tests
    static void inicioGlobal() {
        System.out.println("Inicio de la batería de tests");
    }

    @BeforeEach      // * Se ejecuta ANTES de CADA test
    void inicioTest() {
        System.out.println("Preparando test...");
    }

    @Test
    void test1() { System.out.println("Ejecutando test 1"); }

    @Test
    void test2() { System.out.println("Ejecutando test 2"); }

    @AfterEach       // * Se ejecuta DESPUÉS de CADA test
    void finTest() {
        System.out.println("Limpiando después del test...");
    }

    @AfterAll        // * Se ejecuta UNA VEZ después de TODOS los tests
    static void finGlobal() {
        System.out.println("Fin de la batería de tests");
    }
}
```

**Salida:**
```
Inicio de la batería de tests
Preparando test...
Ejecutando test 1
Limpiando después del test...
Preparando test...
Ejecutando test 2
Limpiando después del test...
Fin de la batería de tests
```

| Anotación | ¿Cuándo? | ¿Static? | Uso típico |
|-----------|----------|----------|------------|
| `@BeforeAll` | Una vez al inicio | **Sí** | Conexión a BD, cargar datos pesados |
| `@BeforeEach` | Antes de CADA test | No | Crear objetos frescos (ej: `new Calculadora()`) |
| `@AfterEach` | Después de CADA test | No | Limpiar datos temporales |
| `@AfterAll` | Una vez al final | **Sí** | Cerrar conexiones, liberar recursos |

> ❗ **¡Cuidado!** `@BeforeAll` y `@AfterAll` deben ser `static`. Es un error habitual en exámenes.

---

### 6.4 `@DisplayName` — Nombres bonitos

```java
@Test
@DisplayName("Sumar 2 + 3 debe dar 5")  // * Aparece así en el runner de Eclipse
void testSumar() {
    assertEquals(5, new Calculadora().sumar(2, 3));
}
```

En el runner de Eclipse/VS Code se verá:
```
✅ Sumar 2 + 3 debe dar 5
```
En vez del nombre técnico del método.

---

### 6.5 Tests parametrizados

Permiten ejecutar el **mismo test con diferentes datos** sin repetir código.

#### Con `@ValueSource` (un solo parámetro)

```java
@ParameterizedTest                        // * En vez de @Test
@ValueSource(ints = {2, 3, 5, 7, 11})    // * Estos valores se pasan al método
@DisplayName("Debe ser primo")
void testEsPrimo(int numero) {
    assertTrue(new Calculadora().esNumeroPrimo(numero));
}
```

Esto ejecuta el test **5 veces**, una por cada número.

#### Con `@CsvSource` (múltiples parámetros)

```java
@ParameterizedTest
@CsvSource({          // * Cada línea es un "caso de test": param1, param2, resultado
    "2, 3, 5",        // ? sumar(2, 3) → 5
    "0, 0, 0",        // ? sumar(0, 0) → 0
    "-1, 1, 0",       // ? sumar(-1, 1) → 0
    "100, 200, 300"   // ? sumar(100, 200) → 300
})
@DisplayName("Sumar con distintos valores")
void testSumarParametrizado(int a, int b, int esperado) {
    assertEquals(esperado, new Calculadora().sumar(a, b));
}
```

> 💡 `@CsvSource` es como una tabla CSV: valores separados por comas. Cada fila es una ejecución del test.

---

### 6.6 Excepciones con `assertThrows`

```java
@Test
@DisplayName("Dividir entre 0 lanza ArithmeticException")
void testDividirEntreCero() {
    Calculadora calc = new Calculadora();

    // * assertThrows comprueba que se lanza la excepción esperada
    ArithmeticException ex = assertThrows(
        ArithmeticException.class,        // ? Tipo de excepción que esperamos
        () -> calc.dividir(10, 0)         // ? Código que debe lanzarla (lambda)
    );

    // * Opcionalmente, verificamos el mensaje de la excepción
    assertEquals("No se puede dividir entre cero", ex.getMessage());
}
```

> ❗ **Si el código NO lanza la excepción, el test FALLA.** Si lanza una excepción diferente, también falla.

---

### 6.7 `@Tag` — Etiquetar tests

```java
@Test
@Tag("Rapido")
void testSumar() { ... }

@Test
@Tag("Lentos")
void testOperacionCompleja() { ... }
```

Permite ejecutar solo un grupo de tests:
```xml
<!-- En pom.xml, dentro de maven-surefire-plugin -->
<groups>Rapido</groups>       <!-- Solo ejecuta tests con @Tag("Rapido") -->
<excludedGroups>Lentos</excludedGroups>  <!-- Excluye los lentos -->
```

---

### 6.8 `@Timeout` — Límite de tiempo

```java
@Test
@Timeout(value = 2, unit = TimeUnit.SECONDS)  // * Falla si tarda más de 2 segundos
@DisplayName("La operación debe completarse rápido")
void testRendimiento() {
    // ... operación que debe ser rápida ...
}
```

> ⚠️ Úsalo solo para comprobar rendimiento. No abuses de `Thread.sleep()` en tests, salvo para demos.

---

### 6.9 `@Disabled` — Desactivar un test

```java
@Test
@Disabled("Pendiente de implementar el método validarDNI")  // * El test NO se ejecuta
@DisplayName("Validar DNI correcto")
void testValidarDNI() {
    // TODO: Implementar cuando el método esté listo
}
```

**¿Cuándo se usa?**
- Un método aún no está implementado
- Un bug conocido que se arreglará después
- Un test que depende de algo externo no disponible

> 📌 Siempre pon un **motivo** en `@Disabled("razón")`. Nunca dejes tests desactivados sin explicación.

---

## 7. ✅ Buenas prácticas

### 7.1 Patrón AAA (Arrange / Act / Assert)

Todo test debe tener **tres partes claras**:

```java
@Test
void testSumar() {
    // * ARRANGE — Preparar datos y objetos
    Calculadora calc = new Calculadora();
    int a = 2;
    int b = 3;

    // * ACT — Ejecutar el método que queremos probar
    int resultado = calc.sumar(a, b);

    // * ASSERT — Comprobar el resultado
    assertEquals(5, resultado);
}
```

### 7.2 Nombres de tests descriptivos

```java
// ❌ MAL
@Test void test1() { ... }
@Test void testSumar() { ... }

// ✅ BIEN — Se entiende qué prueba y qué espera
@Test void sumar_DosPositivos_DevuelveSuma() { ... }
@Test void dividir_EntreCero_LanzaExcepcion() { ... }
@Test void validarEmail_SinArroba_DevuelveFalse() { ... }
```

> 💡 Un buen nombre de test es: `método_Escenario_ResultadoEsperado`

### 7.3 Tests independientes

Cada test debe poder ejecutarse **solo y en cualquier orden**. Un test NUNCA debe depender del resultado de otro.

```java
// ❌ MAL — test2 depende de test1
int valorCompartido;

@Test void test1() { valorCompartido = calc.sumar(2, 3); }
@Test void test2() { assertEquals(5, valorCompartido); }  // ! PELIGRO: ¿y si test1 no se ejecuta primero?
```

### 7.4 Datos representativos

Prueba con:
- ✅ Valores normales: `sumar(2, 3)`
- ✅ Valores límite: `sumar(0, 0)`, `sumar(Integer.MAX_VALUE, 1)`
- ✅ Valores negativos: `sumar(-5, 3)`
- ✅ Valores nulos (si aplica): `validarEmail(null)`
- ✅ Cadenas vacías: `validarEmail("")`

### 7.5 No usar `Thread.sleep()` (salvo demo)

```java
// ❌ MAL — Hace que los tests sean lentos e impredecibles
@Test void testTimeout() {
    Thread.sleep(3000);
    assertTrue(true);
}

// ✅ BIEN — Usa @Timeout
@Test
@Timeout(2)
void testRapido() {
    // la operación real que quieres medir
}
```

---

## 8. ▶️ Cómo ejecutar tests

### Desde Eclipse

1. **Un solo test:** Click derecho en el método → `Run As → JUnit Test`
2. **Toda la clase:** Click derecho en la clase → `Run As → JUnit Test`
3. **Todo el proyecto:** Click derecho en `src/test/java` → `Run As → JUnit Test`

Se abre la **vista JUnit** con barras verdes (✅ OK) o rojas (❌ fallo).

### Desde VS Code

1. **Visual:** Panel lateral "Testing" → click ▶ en el test que quieras
2. **Terminal:** Ver abajo

### Desde terminal (ambos IDEs)

```bash
# Ejecutar TODOS los tests
mvn test

# Ejecutar solo los tests de una clase
mvn test -Dtest=CalculadoraTest

# Ejecutar un test específico
mvn test -Dtest=CalculadoraTest#testSumar

# Ejecutar tests con un Tag
mvn test -Dgroups=Rapido

# Compilar sin ejecutar tests
mvn install -DskipTests
```

> 💡 Si usas PowerShell y da error con `-D`, pon comillas: `mvn test "-Dtest=CalculadoraTest"`

---

## 9. 🐛 Errores típicos y cómo solucionarlos

### Error 1: `import org.junit.Test` (JUnit 4 en vez de 5)

```java
// ❌ MAL — Esto es JUnit 4
import org.junit.Test;

// ✅ BIEN — Esto es JUnit 5
import org.junit.jupiter.api.Test;
```

**Solución:** Asegúrate de que los imports empiecen por `org.junit.jupiter`.

---

### Error 2: Tests no se detectan / no aparecen

**Causas posibles:**
- La clase de test no está en `src/test/java`
- El nombre de la clase no acaba en `Test` (ej: `CalculadoraPrueba` → Maven no lo detecta)
- El método de test no tiene `@Test`
- El método de test es `private` (debe ser `package-private` o `public`)

**Solución:**
```java
// ❌ MAL
private void testSumar() { ... }

// ✅ BIEN
@Test
void testSumar() { ... }  // ? sin modificador de acceso = package-private, que es válido
```

---

### Error 3: `NoClassDefFoundError` o `ClassNotFoundException`

**Causa:** Maven no descargó las dependencias.

**Solución:**
```bash
mvn clean install
```
En Eclipse: Click derecho → `Maven → Update Project... → Force Update`

---

### Error 4: `maven-surefire-plugin` no ejecuta tests

**Causa:** Falta el plugin o la versión es antigua.

**Solución:** Asegúrate de tener en el `pom.xml`:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
</plugin>
```

---

### Error 5: Paquetes no coinciden

```
// Archivo en: src/main/java/com/entornos/Calculadora.java
package com.entornos;    // ✅ Correcto

// Archivo en: src/test/java/com/entornos/CalculadoraTest.java
package com.entornos;    // ✅ DEBE ser el mismo paquete
```

Si pones distinto paquete en test que en main, no podrás acceder a las clases.

---

### Error 6: `@BeforeAll` no es static

```java
// ❌ MAL — Error de compilación
@BeforeAll
void setup() { }

// ✅ BIEN
@BeforeAll
static void setup() { }
```

---

### Error 7: `@ParameterizedTest` sin fuente de datos

```java
// ❌ MAL — Falta @ValueSource, @CsvSource, etc.
@ParameterizedTest
void testAlgo(int x) { }

// ✅ BIEN
@ParameterizedTest
@ValueSource(ints = {1, 2, 3})
void testAlgo(int x) { }
```

---

### Error 8: Java version mismatch

Si Eclipse dice "Java 5" o similar, revisa el `pom.xml`:
```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

---

## 10. 📝 Ejercicios para el alumno

### 🟢 Nivel Básico

#### Ejercicio 1 — Tu primer test
- **Archivo:** `CalculadoraTest.java`
- **Tarea:** Crea un test llamado `restar_DosPositivos_DevuelveResta` que pruebe `restar(10, 3)` y compruebe que devuelve `7`.
- **Asserts:** `assertEquals`
- **Pista:** Sigue el patrón AAA. Crea una instancia de `Calculadora`, llama al método y compara con `assertEquals`.

#### Ejercicio 2 — Test con assertTrue
- **Archivo:** `CalculadoraTest.java`
- **Tarea:** Crea un test que verifique que `esNumeroPrimo(13)` devuelve `true`.
- **Asserts:** `assertTrue`
- **Pista:** `assertTrue` recibe directamente el resultado del método.

#### Ejercicio 3 — Test con assertFalse
- **Archivo:** `ValidadorUsuarioTest.java`
- **Tarea:** Crea un test que verifique que `validarEmail("sinArroba.com")` devuelve `false`.
- **Asserts:** `assertFalse`
- **Pista:** Un email sin `@` no es válido.

#### Ejercicio 4 — Usar @DisplayName
- **Archivo:** `CalculadoraTest.java`
- **Tarea:** Añade `@DisplayName` a 3 tests existentes con descripciones claras en español.
- **Pista:** Ejemplo: `@DisplayName("Multiplicar 4 × 5 debe dar 20")`

---

### 🟡 Nivel Medio

#### Ejercicio 5 — Test parametrizado con @ValueSource
- **Archivo:** `CalculadoraTest.java`
- **Tarea:** Crea un test parametrizado que compruebe que los números `4, 6, 8, 9, 10, 15` **NO** son primos.
- **Asserts:** `assertFalse`
- **Pista:** Usa `@ParameterizedTest` + `@ValueSource(ints = {...})`.

#### Ejercicio 6 — Test parametrizado con @CsvSource
- **Archivo:** `ValidadorUsuarioTest.java`
- **Tarea:** Crea un test parametrizado que pruebe `validarPassword` con al menos 5 contraseñas diferentes y sus resultados esperados.
- **Asserts:** `assertEquals` (comparando `boolean`)
- **Pista:** `@CsvSource({"'abc', false", "'Abcdef1!', true", ...})`. Las cadenas van entre comillas simples dentro del CSV.

#### Ejercicio 7 — Test de excepción
- **Archivo:** `ValidadorUsuarioTest.java`
- **Tarea:** Crea un test que verifique que `registrarUsuario(null, "pass123")` lanza `IllegalArgumentException`.
- **Asserts:** `assertThrows`
- **Pista:** `assertThrows(IllegalArgumentException.class, () -> validador.registrarUsuario(null, "pass123"))`

#### Ejercicio 8 — assertAll para validación completa
- **Archivo:** `ValidadorUsuarioTest.java`
- **Tarea:** Crea un test con `assertAll` que verifique de una vez que:
  - `validarEmail("test@email.com")` → `true`
  - `validarEmail("")` → `false`
  - `validarEmail(null)` → `false`
  - `validarEmail("test@.com")` → `false`
- **Asserts:** `assertAll` con lambdas internas
- **Pista:** Cada lambda dentro de `assertAll` es un assert independiente.

---

### 🔴 Nivel Alto

#### Ejercicio 9 — Refactorizar para testear
- **Archivo:** `Calculadora.java` + `CalculadoraTest.java`
- **Tarea:** Añade a `Calculadora` un método `calcularFactorial(int n)` que:
  - Para `n < 0`: lance `IllegalArgumentException`
  - Para `n == 0` o `n == 1`: devuelva `1`
  - Para `n > 0`: devuelva el factorial
  - Para `n > 20`: lance `IllegalArgumentException` (overflow de `long`)
- Luego crea tests para TODOS estos casos, incluyendo valores límite como `0`, `1`, `20` y `21`.
- **Asserts:** `assertEquals`, `assertThrows`
- **Pista:** El factorial de 20 es `2432902008176640000L`. Usa `long` como tipo de retorno.

#### Ejercicio 10 — Casos borde de validación
- **Archivo:** `ValidadorUsuario.java` + `ValidadorUsuarioTest.java`
- **Tarea:** Añade al `ValidadorUsuario` un método `validarNombreUsuario(String nombre)` que devuelva `true` si:
  - Tiene entre 3 y 20 caracteres
  - Solo contiene letras, números y guiones bajos
  - No empieza por número
  - No es null
- Crea al menos 8 tests cubriendo: caso válido, muy corto, muy largo, con caracteres especiales, empieza por número, vacío, null, y un caso límite exacto (3 y 20 caracteres).
- **Asserts:** Usa `@ParameterizedTest` con `@CsvSource`
- **Pista:** Usa una expresión regular como `"^[a-zA-Z_][a-zA-Z0-9_]{2,19}$"` para la validación.

---

## 11. ✅ Mini checklist final para examen

Antes de entregar o en el examen, repasa:

- [ ] ¿Tus imports son de `org.junit.jupiter.api`? (NO `org.junit`)
- [ ] ¿Tus clases de test están en `src/test/java`?
- [ ] ¿El paquete del test coincide con el de la clase que pruebas?
- [ ] ¿Cada test tiene `@Test` o `@ParameterizedTest`?
- [ ] ¿Los métodos de test son `void` y no son `private`?
- [ ] ¿`@BeforeAll` y `@AfterAll` son `static`?
- [ ] ¿Has usado `assertEquals(ESPERADO, REAL)` — el esperado va PRIMERO?
- [ ] ¿Has usado `assertThrows` con lambda para excepciones?
- [ ] ¿Los tests parametrizados tienen su fuente (`@ValueSource`, `@CsvSource`)?
- [ ] ¿Has dado `@DisplayName` a los tests para que se lean bien?
- [ ] ¿Sigues el patrón AAA (Arrange/Act/Assert)?
- [ ] ¿Cada test es independiente de los demás?
- [ ] ¿`mvn test` ejecuta todo sin errores?

---

## 📚 Recursos adicionales

- [Documentación oficial JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Maven en 5 minutos](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- [Guía de Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)

---

> 📄 Las **soluciones a los ejercicios** están en [SOLUCIONES.md](SOLUCIONES.md)
