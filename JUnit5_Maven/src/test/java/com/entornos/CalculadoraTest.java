package com.entornos;

// ? =====================================================================
// ? CLASE DE TEST: CalculadoraTest
// ? =====================================================================
// ? Esta clase contiene los tests unitarios para la clase Calculadora.
// ? Cada método de test verifica un comportamiento específico de un
// ? método de Calculadora.
// ?
// ? CONVENCIÓN DE NOMBRES: metodo_Escenario_ResultadoEsperado
// ? Ejemplo: dividir_EntreCero_LanzaExcepcion
// ? =====================================================================

// ! RECUERDA: Esta clase va en src/TEST/java/com/entornos/
// ! El paquete DEBE ser el mismo que Calculadora.java (com.entornos)

// * =====================================================================
// * IMPORTS — Todos de org.junit.jupiter (JUnit 5)
// * =====================================================================
// ! NUNCA importes de org.junit (sin jupiter) → eso es JUnit 4
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

// * Importación estática de los métodos de aserción
// ? Así podemos escribir assertEquals(...) en vez de Assertions.assertEquals(...)
import static org.junit.jupiter.api.Assertions.*;

// ? =====================================================================
// ? ¿Por qué no ponemos 'public' en la clase?
// ? En JUnit 5, las clases de test y los métodos de test pueden ser
// ? "package-private" (sin modificador). No necesitan ser public.
// ? Esto es diferente de JUnit 4 donde SÍ tenían que ser public.
// ? =====================================================================
class CalculadoraTest {

    // * Atributo de la clase que vamos a testear
    // ? Lo declaramos aquí y lo inicializamos en @BeforeEach
    // ? para que cada test tenga una instancia FRESCA
    private Calculadora calculadora;

    // ================================================================
    // * CICLO DE VIDA — Métodos que se ejecutan automáticamente
    // ================================================================

    @BeforeAll // * Se ejecuta UNA VEZ antes de todos los tests de esta clase
    static void inicioGlobal() {
        // ? @BeforeAll DEBE ser static (es obligatorio en JUnit 5)
        // ? Se usa para preparar recursos "pesados" compartidos:
        // ? conexiones a BD, cargar archivos grandes, etc.
        System.out.println("========================================");
        System.out.println("  INICIO: Tests de Calculadora");
        System.out.println("========================================");
    }

    @BeforeEach // * Se ejecuta ANTES de CADA test individual
    void setUp() {
        // * Creamos una nueva instancia ANTES de cada test
        // ? Así cada test empieza "limpio", sin depender de otros tests
        // ! BUENA PRÁCTICA: Usar @BeforeEach para inicializar objetos
        calculadora = new Calculadora();
        System.out.println("→ Preparando test...");
    }

    @AfterEach // * Se ejecuta DESPUÉS de CADA test individual
    void tearDown() {
        // ? Se usa para limpiar recursos temporales después de cada test
        // ? En este caso no necesitamos limpiar nada, pero lo dejamos
        // ? como ejemplo didáctico del ciclo de vida
        System.out.println("← Test finalizado.\n");
    }

    @AfterAll // * Se ejecuta UNA VEZ después de TODOS los tests
    static void finGlobal() {
        // ? @AfterAll también DEBE ser static
        // ? Se usa para cerrar conexiones, liberar recursos pesados, etc.
        System.out.println("========================================");
        System.out.println("  FIN: Tests de Calculadora completados");
        System.out.println("========================================");
    }

    // ================================================================
    //  SECCIÓN 1: TESTS DE SUMAR
    // ================================================================

    @Test // * Marca este método como un test que JUnit ejecutará
    @DisplayName("Sumar dos números positivos: 2 + 3 = 5")
    void sumar_DosPositivos_DevuelveSuma() {
        // * ARRANGE — Preparar los datos de entrada
        int a = 2;
        int b = 3;

        // * ACT — Ejecutar el método que queremos probar
        int resultado = calculadora.sumar(a, b);

        // * ASSERT — Verificar que el resultado es el esperado
        // ? assertEquals(ESPERADO, REAL) — ¡El esperado va PRIMERO!
        // ! Error típico de examen: poner los parámetros al revés
        assertEquals(5, resultado, "La suma de 2 + 3 debe ser 5");
        // ? El tercer parámetro (opcional) es un mensaje que aparece si falla
    }

    @Test
    @DisplayName("Sumar con ceros: 0 + 0 = 0")
    void sumar_CerosConCeros_DevuelveCero() {
        // * ARRANGE
        // ? Probar con ceros es importante → caso límite/borde
        int a = 0;
        int b = 0;

        // * ACT
        int resultado = calculadora.sumar(a, b);

        // * ASSERT
        assertEquals(0, resultado);
    }

    @Test
    @DisplayName("Sumar números negativos: -3 + -7 = -10")
    void sumar_DosNegativos_DevuelveSumaNegativa() {
        // * ARRANGE
        int a = -3;
        int b = -7;

        // * ACT
        int resultado = calculadora.sumar(a, b);

        // * ASSERT
        assertEquals(-10, resultado);
    }

    // ================================================================
    //  SECCIÓN 2: TESTS PARAMETRIZADOS DE SUMAR
    // ================================================================

    @ParameterizedTest // * En vez de @Test → indica que se ejecutará varias veces
    @CsvSource({
        // ? Formato: primerSumando, segundoSumando, resultadoEsperado
        "2, 3, 5",        // ? Caso normal: positivos
        "0, 0, 0",        // ? Caso límite: ceros
        "-1, 1, 0",       // ? Caso: negativo + positivo
        "-5, -3, -8",     // ? Caso: ambos negativos
        "100, 200, 300",  // ? Caso: números grandes
        "1, -1, 0"        // ? Caso: opuestos
    })
    @DisplayName("Sumar parametrizado con distintos valores")
    void sumar_ConDistintosValores_DevuelveResultadoCorrecto(int a, int b, int esperado) {
        // ? JUnit inyecta automáticamente los valores de cada fila del CsvSource
        // ? Este test se ejecuta 6 veces, una por cada fila

        // * ACT
        int resultado = calculadora.sumar(a, b);

        // * ASSERT
        assertEquals(esperado, resultado,
            String.format("sumar(%d, %d) debería devolver %d", a, b, esperado));
    }

    // ================================================================
    //  SECCIÓN 3: TESTS DE RESTAR
    // ================================================================

    @Test
    @DisplayName("Restar: 10 - 3 = 7")
    void restar_PositivoMenosPositivo_DevuelveResta() {
        // * ARRANGE
        int a = 10;
        int b = 3;

        // * ACT
        int resultado = calculadora.restar(a, b);

        // * ASSERT
        assertEquals(7, resultado);
    }

    @Test
    @DisplayName("Restar que da negativo: 3 - 10 = -7")
    void restar_MenorMenosMayor_DevuelveNegativo() {
        // * ARRANGE & ACT & ASSERT — Para tests simples se puede compactar
        // ? Pero en examen, mejor separar por AAA siempre
        assertEquals(-7, calculadora.restar(3, 10));
    }

    // ================================================================
    //  SECCIÓN 4: TESTS DE MULTIPLICAR
    // ================================================================

    @Test
    @DisplayName("Multiplicar: 4 × 5 = 20")
    void multiplicar_DosPositivos_DevuelveProducto() {
        // * ARRANGE
        int a = 4;
        int b = 5;

        // * ACT
        int resultado = calculadora.multiplicar(a, b);

        // * ASSERT
        assertEquals(20, resultado);
    }

    @Test
    @DisplayName("Multiplicar por cero siempre da cero")
    void multiplicar_PorCero_DevuelveCero() {
        // * ARRANGE & ACT & ASSERT
        // ? Propiedad matemática: cualquier número × 0 = 0
        assertEquals(0, calculadora.multiplicar(999, 0));
        assertEquals(0, calculadora.multiplicar(0, 999));
    }

    @Test
    @DisplayName("Multiplicar negativos: -3 × -4 = 12")
    void multiplicar_DosNegativos_DevuelvePositivo() {
        // * ASSERT
        // ? Regla de signos: negativo × negativo = positivo
        assertEquals(12, calculadora.multiplicar(-3, -4));
    }

    // ================================================================
    //  SECCIÓN 5: TESTS DE DIVIDIR (con assertThrows)
    // ================================================================

    @Test
    @DisplayName("Dividir: 10 / 2 = 5.0")
    void dividir_DosPositivos_DevuelveCociente() {
        // * ARRANGE
        int dividendo = 10;
        int divisor = 2;

        // * ACT
        double resultado = calculadora.dividir(dividendo, divisor);

        // * ASSERT
        // ? Para comparar doubles usamos delta (margen de error) como 3er param
        // ? 0.001 significa que aceptamos una diferencia de ±0.001
        assertEquals(5.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Dividir: 7 / 2 = 3.5 (división decimal)")
    void dividir_Impares_DevuelveDecimal() {
        // * ACT
        double resultado = calculadora.dividir(7, 2);

        // * ASSERT
        assertEquals(3.5, resultado, 0.001);
    }

    @Test
    @DisplayName("Dividir entre cero lanza ArithmeticException")
    void dividir_EntreCero_LanzaExcepcion() {
        // * ARRANGE
        int dividendo = 10;
        int divisor = 0;

        // * ACT & ASSERT — assertThrows combina ambos
        // ? assertThrows(TipoExcepcion.class, () -> códigoQueLanzaExcepcion)
        // ! El segundo parámetro es una LAMBDA (función anónima)
        // ! que contiene el código que esperamos que lance la excepción
        ArithmeticException excepcion = assertThrows(
            ArithmeticException.class,          // ? Tipo de excepción esperada
            () -> calculadora.dividir(dividendo, divisor)  // ? Código que debe fallar
        );

        // * Opcionalmente, verificamos el mensaje de la excepción
        // ? assertThrows devuelve la excepción, así que podemos inspeccionarla
        assertEquals("No se puede dividir entre cero", excepcion.getMessage());
    }

    // ================================================================
    //  SECCIÓN 6: TESTS DE esNumeroPrimo (con parametrizados)
    // ================================================================

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29})
    // ? @ValueSource proporciona UN solo parámetro al test
    // ? Se usa cuando solo necesitas probar una lista de valores del mismo tipo
    @DisplayName("Debe ser número primo")
    void esNumeroPrimo_NumeroPrimo_DevuelveTrue(int numero) {
        // * ACT & ASSERT
        assertTrue(calculadora.esNumeroPrimo(numero),
            numero + " debería ser primo");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 4, 6, 8, 9, 10, 12, 15, 100})
    @DisplayName("NO debe ser número primo")
    void esNumeroPrimo_NumeroNoPrimo_DevuelveFalse(int numero) {
        // * ACT & ASSERT
        assertFalse(calculadora.esNumeroPrimo(numero),
            numero + " NO debería ser primo");
    }

    @Test
    @DisplayName("Números negativos no son primos")
    void esNumeroPrimo_Negativo_DevuelveFalse() {
        // * ASSERT
        assertFalse(calculadora.esNumeroPrimo(-7),
            "Los números negativos no son primos");
    }

    // ================================================================
    //  SECCIÓN 7: TESTS CON assertAll (agrupación de asserts)
    // ================================================================

    @Test
    @DisplayName("Verificar varias operaciones básicas a la vez")
    void operacionesBasicas_VariasOperaciones_TodasCorrectas() {
        // ? assertAll ejecuta TODOS los asserts aunque alguno falle
        // ? Es mejor que poner varios assertEquals sueltos porque:
        // ? - Con assertEquals sueltos: si falla el 1º, no se ejecutan los demás
        // ? - Con assertAll: se ejecutan TODOS y ves todos los fallos de una vez

        // * ARRANGE
        // (calculadora ya está creada en @BeforeEach)

        // * ACT & ASSERT
        assertAll("Operaciones aritméticas básicas",
            // ? Cada lambda es una comprobación independiente
            () -> assertEquals(10, calculadora.sumar(7, 3), "Suma incorrecta"),
            () -> assertEquals(4, calculadora.restar(7, 3), "Resta incorrecta"),
            () -> assertEquals(21, calculadora.multiplicar(7, 3), "Multiplicación incorrecta"),
            () -> assertEquals(2.333, calculadora.dividir(7, 3), 0.001, "División incorrecta")
        );
    }

    // ================================================================
    //  SECCIÓN 8: TESTS DE validarDNI
    // ================================================================

    @ParameterizedTest
    @CsvSource({
        "'12345678A', true",    // ? DNI válido normal
        "'00000000T', true",    // ? DNI con ceros al inicio
        "'99999999Z', true",    // ? DNI con nueves
        "'1234567A', false",    // ? Solo 7 dígitos (le falta uno)
        "'123456789', false",   // ? 9 dígitos sin letra
        "'ABCDEFGHI', false",   // ? 9 letras sin dígitos
        "'', false",            // ? Cadena vacía
        "'12345678AB', false"   // ? Demasiado largo
    })
    @DisplayName("Validar formato de DNI")
    void validarDNI_DistintosCasos_ResultadoCorrecto(String dni, boolean esperado) {
        // * ACT
        boolean resultado = calculadora.validarDNI(dni);

        // * ASSERT
        assertEquals(esperado, resultado,
            "validarDNI(\"" + dni + "\") debería devolver " + esperado);
    }

    @Test
    @DisplayName("Validar DNI con null devuelve false")
    void validarDNI_Null_DevuelveFalse() {
        // * ASSERT
        // ? null es un caso especial que no se puede pasar fácilmente con @CsvSource
        assertFalse(calculadora.validarDNI(null));
    }

    // ================================================================
    //  SECCIÓN 9: TESTS DE calcularModulo (valor absoluto)
    // ================================================================

    @ParameterizedTest
    @CsvSource({
        "-5, 5",    // ? Negativo → positivo
        "5, 5",     // ? Positivo → se queda igual
        "0, 0",     // ? Cero → cero
        "-100, 100" // ? Negativo grande
    })
    @DisplayName("Valor absoluto de distintos números")
    void calcularModulo_DistintosNumeros_DevuelveModulo(int entrada, int esperado) {
        assertEquals(esperado, calculadora.calcularModulo(entrada));
    }

    // ================================================================
    //  SECCIÓN 10: TEST CON @Tag y @Timeout
    // ================================================================

    @Test
    @Tag("Rapido") // * Etiqueta para filtrar tests por categoría
    @DisplayName("Test rápido: suma simple")
    void sumar_TestRapido() {
        assertEquals(4, calculadora.sumar(2, 2));
    }

    @Test
    @Tag("Lentos") // * Etiqueta para tests "lentos"
    @Timeout(value = 3, unit = TimeUnit.SECONDS)
    // ? @Timeout hace que el test FALLE si tarda más del tiempo indicado
    // ? Útil para detectar bucles infinitos o rendimiento inaceptable
    @DisplayName("Test con timeout: operación que debe completarse en 3s")
    void operacionConTimeout_DebeCompletarseRapido() throws InterruptedException {
        // * ARRANGE
        // ! Este Thread.sleep es SOLO para demostrar @Timeout
        // ! NUNCA uses Thread.sleep en tests reales (ver buenas prácticas)
        Thread.sleep(100); // ? 100ms, muy por debajo del límite de 3s

        // * ACT & ASSERT
        boolean esPrimo = calculadora.esNumeroPrimo(104729);
        // ? 104729 es primo — lo usamos para simular una operación "pesada"
        assertTrue(esPrimo, "104729 es primo");
    }

    // ================================================================
    //  SECCIÓN 11: TEST CON @Disabled
    // ================================================================

    @Test
    @Disabled("Pendiente: implementar validación de letra correcta del DNI")
    // ? @Disabled hace que el test NO se ejecute, pero sigue visible
    // ? en el runner como "saltado" (icono amarillo en Eclipse)
    // 
    // ! ¿Cuándo usar @Disabled?
    // ! - Un método aún no está implementado
    // ! - Un bug conocido que se arreglará luego
    // ! - Un test que depende de un recurso externo no disponible
    // 
    // ! SIEMPRE pon un motivo en @Disabled("razón")
    // ! Los tests desactivados sin motivo son mala práctica
    @DisplayName("Validar que la letra del DNI es correcta (TODO)")
    void validarDNI_LetraCorrecta_DevuelveTrue() {
        // TODO Implementar cuando se amplíe el método validarDNI
        // ? La letra del DNI se calcula: número % 23 → posición en "TRWAGMYFPDXBNJZSQVHLCKE"
        // ? Ejemplo: 12345678 % 23 = 14 → letra 'Z' → "12345678Z" sería correcto
        fail("Test pendiente de implementar");
        // ? fail() hace que el test falle siempre. Es un placeholder.
    }

    // ================================================================
    //  SECCIÓN 12: TEST DE esPar
    // ================================================================

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 10, 100, -2, -4})
    @DisplayName("Números pares devuelven true")
    void esPar_NumeroPar_DevuelveTrue(int numero) {
        assertTrue(calculadora.esPar(numero));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 11, 99, -1, -3})
    @DisplayName("Números impares devuelven false")
    void esPar_NumeroImpar_DevuelveFalse(int numero) {
        assertFalse(calculadora.esPar(numero));
    }

    // ================================================================
    // TODO EJERCICIOS PARA EL ALUMNO — Añade más tests aquí
    // ================================================================

    // TODO Ejercicio 1: Crea un test para calcularMaximo(5, 3) → 5
    // TODO Ejercicio 2: Crea un test assertTrue para esNumeroPrimo(13)
    // TODO Ejercicio 4: Añade @DisplayName a 3 tests existentes
    // TODO Ejercicio 5: Test parametrizado de no-primos con @ValueSource
    // TODO Ejercicio 9: Implementa calcularFactorial y sus tests
}
