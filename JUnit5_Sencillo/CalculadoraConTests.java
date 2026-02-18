package tests;

// ? =====================================================================
// ? ARCHIVO ÚNICO: Clase Calculadora + Tests de JUnit 5
// ? =====================================================================
// ? Este archivo contiene TODO lo necesario para practicar JUnit 5:
// ? - La clase Calculadora con sus métodos
// ? - Los tests que prueban cada método
// ? 
// ? Para ejecutar en Eclipse:
// ?   1. Click derecho en este archivo
// ?   2. Run As → JUnit Test
// ? =====================================================================

// * =====================================================================
// * IMPORTS — Siempre de org.junit.jupiter (JUnit 5)
// * =====================================================================
// ! NUNCA uses org.junit sin jupiter → eso es JUnit 4
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;


// * =====================================================================
// * CLASE CALCULADORA — La clase "de producción" que vamos a testear
// * =====================================================================
class Calculadora {
    
    // * Suma dos números
    public int sumar(int a, int b) {
        return a + b;
    }
    
    // * Resta dos números
    public int restar(int a, int b) {
        return a - b;
    }
    
    // * Multiplica dos números
    public int multiplicar(int a, int b) {
        return a * b;
    }
    
    // * Divide dos números
    // ! Si divisor es 0, lanza ArithmeticException
    public double dividir(int dividendo, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        return (double) dividendo / divisor;
    }
    
    // * Comprueba si un número es primo
    public boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        if (numero == 2) return true;
        if (numero % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(numero); i += 2) {
            if (numero % i == 0) return false;
        }
        return true;
    }
    
    // * Comprueba si un número es par
    public boolean esPar(int numero) {
        return numero % 2 == 0;
    }
    
    // * Devuelve el mayor de dos números
    public int maximo(int a, int b) {
        return a >= b ? a : b;
    }
    
    // * Valida formato de DNI: 8 dígitos + 1 letra
    public boolean validarDNI(String dni) {
        if (dni == null || dni.length() != 9) return false;
        String numeros = dni.substring(0, 8);
        char letra = dni.charAt(8);
        for (char c : numeros.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return Character.isLetter(letra);
    }
}


// * =====================================================================
// * CLASE DE TESTS — Aquí van todos los tests
// * =====================================================================
// ? El nombre de la clase DEBE acabar en "Test" para que JUnit la detecte
class CalculadoraConTests {

    // * Objeto que vamos a testear
    private Calculadora calc;

    // ================================================================
    // * CICLO DE VIDA — Se ejecutan automáticamente
    // ================================================================
    
    @BeforeAll // ? Se ejecuta UNA VEZ antes de todos los tests
    static void inicioGlobal() {
        // ! @BeforeAll DEBE ser static
        System.out.println("=== INICIO DE TESTS ===");
    }

    @BeforeEach // ? Se ejecuta ANTES de CADA test
    void setUp() {
        // * Creamos una calculadora nueva para cada test
        // ? Así cada test empieza "limpio"
        calc = new Calculadora();
    }

    @AfterEach // ? Se ejecuta DESPUÉS de CADA test
    void tearDown() {
        System.out.println("Test completado");
    }

    @AfterAll // ? Se ejecuta UNA VEZ después de todos los tests
    static void finGlobal() {
        // ! @AfterAll DEBE ser static
        System.out.println("=== FIN DE TESTS ===");
    }

    // ================================================================
    // * TESTS DE SUMAR
    // ================================================================

    @Test // ! Sin @Test el método NO se ejecuta como test
    @DisplayName("Sumar 2 + 3 = 5")
    void testSumar() {
        // * ARRANGE — Preparar datos
        int a = 2;
        int b = 3;

        // * ACT — Ejecutar el método
        int resultado = calc.sumar(a, b);

        // * ASSERT — Comprobar resultado
        // ! assertEquals(ESPERADO, REAL) — el esperado va PRIMERO
        assertEquals(5, resultado, "2 + 3 debe ser 5");
    }

    @Test
    @DisplayName("Sumar con ceros")
    void testSumarCeros() {
        assertEquals(0, calc.sumar(0, 0));
        assertEquals(5, calc.sumar(5, 0));
        assertEquals(5, calc.sumar(0, 5));
    }

    @Test
    @DisplayName("Sumar números negativos")
    void testSumarNegativos() {
        assertEquals(-8, calc.sumar(-3, -5));
        assertEquals(2, calc.sumar(5, -3));
    }

    // ================================================================
    // * TESTS DE RESTAR
    // ================================================================

    @Test
    @DisplayName("Restar: 10 - 3 = 7")
    void testRestar() {
        assertEquals(7, calc.restar(10, 3));
    }

    @Test
    @DisplayName("Restar que da negativo")
    void testRestarNegativo() {
        assertEquals(-5, calc.restar(3, 8));
    }

    // ================================================================
    // * TESTS DE MULTIPLICAR
    // ================================================================

    @Test
    @DisplayName("Multiplicar: 4 x 5 = 20")
    void testMultiplicar() {
        assertEquals(20, calc.multiplicar(4, 5));
    }

    @Test
    @DisplayName("Multiplicar por cero siempre da cero")
    void testMultiplicarPorCero() {
        assertEquals(0, calc.multiplicar(100, 0));
        assertEquals(0, calc.multiplicar(0, 100));
    }

    // ================================================================
    // * TESTS DE DIVIDIR (con assertThrows)
    // ================================================================

    @Test
    @DisplayName("Dividir: 10 / 2 = 5")
    void testDividir() {
        assertEquals(5.0, calc.dividir(10, 2), 0.001);
        // ? El 0.001 es el "delta" para comparar decimales
    }

    @Test
    @DisplayName("Dividir entre cero lanza excepción")
    void testDividirEntreCero() {
        // * assertThrows comprueba que se lanza la excepción
        // ? El segundo parámetro es una LAMBDA con el código que falla
        ArithmeticException ex = assertThrows(
            ArithmeticException.class,
            () -> calc.dividir(10, 0)
        );
        
        // * Podemos comprobar también el mensaje
        assertEquals("No se puede dividir entre cero", ex.getMessage());
    }

    // ================================================================
    // * TESTS DE ESPRIMO (con assertTrue/assertFalse)
    // ================================================================

    @Test
    @DisplayName("7 es primo")
    void testEsPrimo() {
        // * assertTrue comprueba que el resultado es true
        assertTrue(calc.esPrimo(7), "7 debería ser primo");
    }

    @Test
    @DisplayName("4 no es primo")
    void testNoEsPrimo() {
        // * assertFalse comprueba que el resultado es false
        assertFalse(calc.esPrimo(4), "4 NO debería ser primo");
    }

    @Test
    @DisplayName("Casos especiales: 0, 1, 2")
    void testPrimosCasosEspeciales() {
        assertFalse(calc.esPrimo(0), "0 no es primo");
        assertFalse(calc.esPrimo(1), "1 no es primo");
        assertTrue(calc.esPrimo(2), "2 sí es primo");
    }

    // ================================================================
    // * TESTS PARAMETRIZADOS — Un test, muchos valores
    // ================================================================

    @ParameterizedTest // ? En vez de @Test para tests con parámetros
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19})
    @DisplayName("Números primos")
    void testNumerosPrimos(int numero) {
        // ? JUnit ejecuta este test 8 veces, una por cada número
        assertTrue(calc.esPrimo(numero), numero + " debería ser primo");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 4, 6, 8, 9, 10, 12})
    @DisplayName("Números NO primos")
    void testNumerosNoPrimos(int numero) {
        assertFalse(calc.esPrimo(numero), numero + " NO debería ser primo");
    }

    @ParameterizedTest
    @CsvSource({
        // ? Formato: valor1, valor2, resultado esperado
        "2, 3, 5",
        "0, 0, 0",
        "-1, 1, 0",
        "10, 5, 15"
    })
    @DisplayName("Sumar con múltiples valores")
    void testSumarParametrizado(int a, int b, int esperado) {
        assertEquals(esperado, calc.sumar(a, b));
    }

    // ================================================================
    // * TESTS DE VALIDAR DNI
    // ================================================================

    @Test
    @DisplayName("DNI válido: 12345678A")
    void testDNIValido() {
        assertTrue(calc.validarDNI("12345678A"));
    }

    @ParameterizedTest
    @CsvSource({
        "'12345678A', true",   // ? Válido
        "'00000000Z', true",   // ? Válido con ceros
        "'1234567A', false",   // ? Solo 7 dígitos
        "'123456789', false",  // ? Sin letra
        "'ABCDEFGHI', false",  // ? Solo letras
        "'', false"            // ? Vacío
    })
    @DisplayName("Validar DNI con varios casos")
    void testDNIParametrizado(String dni, boolean esperado) {
        assertEquals(esperado, calc.validarDNI(dni));
    }

    @Test
    @DisplayName("DNI null devuelve false")
    void testDNINull() {
        assertFalse(calc.validarDNI(null));
    }

    // ================================================================
    // * TEST CON assertAll — Agrupar varias comprobaciones
    // ================================================================

    @Test
    @DisplayName("Verificar varias operaciones a la vez")
    void testVariasOperaciones() {
        // * assertAll ejecuta TODOS los asserts aunque alguno falle
        // ? Así ves todos los errores de una vez, no solo el primero
        assertAll("Operaciones básicas",
            () -> assertEquals(5, calc.sumar(2, 3)),
            () -> assertEquals(2, calc.restar(5, 3)),
            () -> assertEquals(12, calc.multiplicar(3, 4)),
            () -> assertEquals(2.5, calc.dividir(5, 2), 0.001)
        );
    }

    // ================================================================
    // * TEST DESACTIVADO — @Disabled
    // ================================================================

    @Test
    @Disabled("Pendiente de implementar el método")
    @DisplayName("Test que todavía no está listo")
    void testPendiente() {
        // ? @Disabled hace que el test NO se ejecute
        // ! Siempre pon un motivo: @Disabled("razón")
        fail("Este test está pendiente");
    }

    // ================================================================
    // TODO: EJERCICIOS PARA PRACTICAR
    // ================================================================
    
    // TODO: Ejercicio 1 - Crea un test para calc.esPar(4) → true
    
    // TODO: Ejercicio 2 - Crea un test para calc.maximo(5, 3) → 5
    
    // TODO: Ejercicio 3 - Crea un test parametrizado para números pares
    //       usando @ValueSource(ints = {0, 2, 4, 6, 8, 10})
    
    // TODO: Ejercicio 4 - Crea un test parametrizado con @CsvSource
    //       para calc.maximo con casos: (5,3,5), (3,5,5), (4,4,4)
    
    // TODO: Ejercicio 5 - Añade @DisplayName a los tests que no lo tienen
}
