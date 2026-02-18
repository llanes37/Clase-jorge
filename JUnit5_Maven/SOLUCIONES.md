# 📝 SOLUCIONES — Ejercicios de JUnit 5

> **Aviso:** Intenta resolver los ejercicios por tu cuenta antes de mirar las soluciones.  
> Equivocarse es parte del aprendizaje. Si un test falla, lee el mensaje de error con calma.

---

## 🟢 Ejercicio 1 — Tu primer test (restar)

**Archivo:** `CalculadoraTest.java`

```java
@Test
@DisplayName("Restar dos positivos: 10 - 3 = 7")
void restar_DosPositivos_DevuelveResta() {
    // * ARRANGE
    Calculadora calc = new Calculadora();
    // ? (Si ya tienes @BeforeEach con calculadora, usa esa variable)

    // * ACT
    int resultado = calc.restar(10, 3);

    // * ASSERT
    assertEquals(7, resultado, "10 - 3 debería ser 7");
}
```

> **Nota:** Si usas el `@BeforeEach` que ya hay en la clase, sustituye `calc` por `calculadora`.

---

## 🟢 Ejercicio 2 — Test con assertTrue (esNumeroPrimo)

**Archivo:** `CalculadoraTest.java`

```java
@Test
@DisplayName("13 es un número primo")
void esNumeroPrimo_Trece_DevuelveTrue() {
    // * ARRANGE
    int numero = 13;

    // * ACT
    boolean resultado = calculadora.esNumeroPrimo(numero);

    // * ASSERT
    assertTrue(resultado, "13 debería ser primo");
}
```

---

## 🟢 Ejercicio 3 — Test con assertFalse (validarEmail)

**Archivo:** `ValidadorUsuarioTest.java`

```java
@Test
@DisplayName("Email sin arroba no es válido")
void validarEmail_SinArroba_DevuelveFalse() {
    // * ARRANGE
    String email = "sinArroba.com";

    // * ACT
    boolean resultado = validador.validarEmail(email);

    // * ASSERT
    assertFalse(resultado, "Un email sin @ no debería ser válido");
}
```

---

## 🟢 Ejercicio 4 — Usar @DisplayName

**Archivo:** `CalculadoraTest.java`

Simplemente añade la anotación encima de tests existentes:

```java
@Test
@DisplayName("Multiplicar 4 × 5 debe dar 20")
void multiplicar_DosPositivos_DevuelveProducto() { ... }

@Test
@DisplayName("Dividir entre cero lanza ArithmeticException")
void dividir_EntreCero_LanzaExcepcion() { ... }

@Test
@DisplayName("El número 1 no se considera primo")
void esNumeroPrimo_Uno_DevuelveFalse() {
    assertFalse(calculadora.esNumeroPrimo(1));
}
```

> **Clave:** `@DisplayName` va SIEMPRE entre `@Test` y el nombre del método.

---

## 🟡 Ejercicio 5 — Test parametrizado con @ValueSource (no primos)

**Archivo:** `CalculadoraTest.java`

```java
@ParameterizedTest
@ValueSource(ints = {4, 6, 8, 9, 10, 15})
@DisplayName("Números no primos devuelven false")
void esNumeroPrimo_NumeroCompuesto_DevuelveFalse(int numero) {
    // * ACT & ASSERT
    assertFalse(calculadora.esNumeroPrimo(numero),
        numero + " NO debería ser primo");
}
```

> **Explicación:** `@ValueSource(ints = {...})` pasa cada valor como parámetro al test. El test se ejecuta 6 veces.

---

## 🟡 Ejercicio 6 — Test parametrizado con @CsvSource (validarPassword)

**Archivo:** `ValidadorUsuarioTest.java`

```java
@ParameterizedTest
@CsvSource({
    "'Abcdef1!', true",            // * Válida: tiene todo
    "'MiClave99#', true",          // * Válida: tiene todo
    "'abc', false",                // ! Muy corta
    "'abcdefgh', false",           // ! Solo minúsculas
    "'ABCDEFGH', false",           // ! Solo mayúsculas
    "'12345678', false",           // ! Solo dígitos
    "'Abcdef1', false",            // ! Falta carácter especial
    "'abcdef1!', false"            // ! Falta mayúscula
})
@DisplayName("Validar password con distintos casos parametrizados")
void validarPassword_Parametrizado_ResultadoCorrecto(String password, boolean esperado) {
    // * ACT
    boolean resultado = validador.validarPassword(password);

    // * ASSERT
    assertEquals(esperado, resultado,
        "validarPassword(\"" + password + "\") debería devolver " + esperado);
}
```

> **Truco:** En `@CsvSource`, las cadenas de texto van entre comillas simples (`'texto'`).

---

## 🟡 Ejercicio 7 — Test de excepción (registrarUsuario con null)

**Archivo:** `ValidadorUsuarioTest.java`

```java
@Test
@DisplayName("Registrar con email null lanza IllegalArgumentException")
void registrarUsuario_EmailNull_LanzaIllegalArgument() {
    // * ACT & ASSERT
    IllegalArgumentException ex = assertThrows(
        IllegalArgumentException.class,
        () -> validador.registrarUsuario(null, "pass123")
    );

    // * Verificar que tiene mensaje
    assertNotNull(ex.getMessage());
    // * Verificar el contenido del mensaje
    assertEquals("El email y la contraseña no pueden ser null", ex.getMessage());
}
```

> **Clave:** `assertThrows` recibe la clase de excepción y una **lambda** con el código que debe fallar. Devuelve la excepción para poder inspeccionarla.

---

## 🟡 Ejercicio 8 — assertAll para validación completa de email

**Archivo:** `ValidadorUsuarioTest.java`

```java
@Test
@DisplayName("Validar múltiples emails de una vez con assertAll")
void validarEmail_MultiplesCasos_assertAll() {
    assertAll("Validación completa de emails",
        () -> assertTrue(validador.validarEmail("test@email.com"),
            "test@email.com debería ser válido"),
        () -> assertFalse(validador.validarEmail(""),
            "Email vacío debería ser inválido"),
        () -> assertFalse(validador.validarEmail(null),
            "Email null debería ser inválido"),
        () -> assertFalse(validador.validarEmail("test@.com"),
            "Email con dominio que empieza por punto debería ser inválido")
    );
}
```

> **Ventaja de `assertAll`:** Si falla el 2º assert, los demás **sí se ejecutan** y ves todos los fallos. Con `assertEquals` sueltos, al fallar uno ya no se ejecutan los siguientes.

---

## 🔴 Ejercicio 9 — Refactorizar: calcularFactorial

### Paso 1: Añadir el método a `Calculadora.java`

```java
// * MÉTODO: calcularFactorial
// ? Calcula el factorial de un número: n! = n × (n-1) × ... × 2 × 1
// ? Ejemplo: 5! = 5 × 4 × 3 × 2 × 1 = 120
// ! Usamos long porque los factoriales crecen muy rápido
// ! 20! = 2.432.902.008.176.640.000 (cabe en long)
// ! 21! = 51.090.942.171.709.440.000 (NO cabe en long → overflow)
public long calcularFactorial(int n) {
    // * Caso error: números negativos
    if (n < 0) {
        throw new IllegalArgumentException("No existe factorial de números negativos");
    }
    
    // * Caso error: overflow (n > 20 no cabe en long)
    if (n > 20) {
        throw new IllegalArgumentException("Factorial de " + n + " causa overflow (máximo n=20)");
    }
    
    // * Caso base: 0! = 1 y 1! = 1
    if (n == 0 || n == 1) {
        return 1;
    }
    
    // * Caso general: n! = n × (n-1)!
    long resultado = 1;
    for (int i = 2; i <= n; i++) {
        resultado *= i;
    }
    return resultado;
}
```

### Paso 2: Añadir los tests a `CalculadoraTest.java`

```java
// ================================================================
//  TESTS DE calcularFactorial
// ================================================================

@Test
@DisplayName("Factorial de 0 es 1")
void calcularFactorial_Cero_DevuelveUno() {
    assertEquals(1, calculadora.calcularFactorial(0));
}

@Test
@DisplayName("Factorial de 1 es 1")
void calcularFactorial_Uno_DevuelveUno() {
    assertEquals(1, calculadora.calcularFactorial(1));
}

@ParameterizedTest
@CsvSource({
    "2, 2",
    "3, 6",
    "5, 120",
    "10, 3628800",
    "20, 2432902008176640000"
})
@DisplayName("Factoriales con distintos valores")
void calcularFactorial_ValoresNormales_DevuelveFactorial(int n, long esperado) {
    assertEquals(esperado, calculadora.calcularFactorial(n));
}

@Test
@DisplayName("Factorial de número negativo lanza excepción")
void calcularFactorial_Negativo_LanzaExcepcion() {
    assertThrows(IllegalArgumentException.class,
        () -> calculadora.calcularFactorial(-1));
}

@Test
@DisplayName("Factorial de 21 lanza excepción por overflow")
void calcularFactorial_MayorQue20_LanzaExcepcion() {
    assertThrows(IllegalArgumentException.class,
        () -> calculadora.calcularFactorial(21));
}

@Test
@DisplayName("Factorial: casos límite con assertAll")
void calcularFactorial_CasosLimite_Correctos() {
    assertAll("Factoriales límite",
        () -> assertEquals(1, calculadora.calcularFactorial(0), "0! = 1"),
        () -> assertEquals(1, calculadora.calcularFactorial(1), "1! = 1"),
        () -> assertEquals(2432902008176640000L, calculadora.calcularFactorial(20), "20! correcto"),
        () -> assertThrows(IllegalArgumentException.class,
            () -> calculadora.calcularFactorial(-5), "Negativo → excepción"),
        () -> assertThrows(IllegalArgumentException.class,
            () -> calculadora.calcularFactorial(21), "21 → excepción por overflow")
    );
}
```

---

## 🔴 Ejercicio 10 — Casos borde: validarNombreUsuario

### Paso 1: Añadir el método a `ValidadorUsuario.java`

```java
// * MÉTODO: validarNombreUsuario
// ? Valida que un nombre de usuario cumpla:
// ?   - No es null
// ?   - Tiene entre 3 y 20 caracteres
// ?   - Solo contiene letras, números y guiones bajos
// ?   - No empieza por número
public boolean validarNombreUsuario(String nombre) {
    if (nombre == null) {
        return false;
    }
    // * Usamos una expresión regular:
    // ? ^          → inicio de la cadena
    // ? [a-zA-Z_]  → primer carácter: letra o guion bajo (NO número)
    // ? [a-zA-Z0-9_]{2,19} → siguientes 2-19 chars: letra, número o guion bajo
    // ? $          → fin de la cadena
    // ? Total: entre 3 (1+2) y 20 (1+19) caracteres
    return nombre.matches("^[a-zA-Z_][a-zA-Z0-9_]{2,19}$");
}
```

### Paso 2: Añadir los tests a `ValidadorUsuarioTest.java`

```java
// ================================================================
//  TESTS DE validarNombreUsuario
// ================================================================

@ParameterizedTest
@CsvSource({
    // * Casos válidos
    "'abc', true",                    // ? Exactamente 3 caracteres (límite inferior)
    "'usuario_normal', true",         // ? Caso normal con guion bajo
    "'Juan123', true",                // ? Letras y números
    "'_privado', true",               // ? Empieza por guion bajo (válido)
    "'a2345678901234567890', true",   // ? Exactamente 20 caracteres (límite superior)

    // * Casos inválidos
    "'ab', false",                    // ? Solo 2 caracteres (muy corto)
    "'a23456789012345678901', false", // ? 21 caracteres (muy largo)
    "'1usuario', false",              // ? Empieza por número
    "'usuario!', false",              // ? Carácter especial no permitido
    "'', false",                      // ? Vacío
    "'user name', false"              // ? Espacio no permitido
})
@DisplayName("Validar nombre de usuario con distintos casos")
void validarNombre_DistintosCasos_ResultadoCorrecto(String nombre, boolean esperado) {
    assertEquals(esperado, validador.validarNombreUsuario(nombre),
        "validarNombreUsuario(\"" + nombre + "\") debería ser " + esperado);
}

@Test
@DisplayName("Nombre de usuario null devuelve false")
void validarNombre_Null_DevuelveFalse() {
    assertFalse(validador.validarNombreUsuario(null));
}

@Test
@DisplayName("Validación completa de nombres con assertAll")
void validarNombre_CasosCompletos_assertAll() {
    assertAll("Validación de nombres de usuario",
        () -> assertTrue(validador.validarNombreUsuario("jorge"),
            "jorge debería ser válido"),
        () -> assertTrue(validador.validarNombreUsuario("abc"),
            "abc (3 chars) debería ser válido — límite inferior"),
        () -> assertFalse(validador.validarNombreUsuario("ab"),
            "ab (2 chars) debería ser inválido — por debajo del límite"),
        () -> assertFalse(validador.validarNombreUsuario("1abc"),
            "Empezar por número debería ser inválido"),
        () -> assertFalse(validador.validarNombreUsuario(null),
            "null debería ser inválido")
    );
}
```

---

## 📌 Resumen de asserts usados

| Assert | Para qué sirve | Ejemplo |
|--------|----------------|---------|
| `assertEquals(esperado, real)` | Comparar valores | `assertEquals(5, calc.sumar(2,3))` |
| `assertTrue(condición)` | Verificar que es true | `assertTrue(calc.esNumeroPrimo(7))` |
| `assertFalse(condición)` | Verificar que es false | `assertFalse(calc.esNumeroPrimo(4))` |
| `assertNotNull(obj)` | Verificar que no es null | `assertNotNull(ex.getMessage())` |
| `assertNull(obj)` | Verificar que es null | `assertNull(resultado)` |
| `assertThrows(Ex.class, lambda)` | Verificar que lanza excepción | `assertThrows(ArithmeticException.class, () -> ...)` |
| `assertAll(nombre, lambdas...)` | Agrupar asserts (ejecuta todos) | `assertAll("grupo", () -> ..., () -> ...)` |

---

> **¡Bien hecho!** Si has llegado hasta aquí y has resuelto todos los ejercicios, estás preparado para el examen. 💪
