package tests;

// ======================================================================
// ? PLANTILLA EXAMEN (ALUMNO) - TESTING DE CAJA NEGRA CON JUNIT 5
// ? TEMA: BIBLIOTECA MUNICIPAL "LECTURA VIVA"
// ? OBJETIVO: Resolver en clase como simulacro real de examen
// ======================================================================
//
// * COMO USAR ESTE ARCHIVO
// * 1) Lee el enunciado completo de abajo.
// * 2) Revisa particiones y valores limite de cada metodo.
// * 3) Completa TODOS los TODO (uno a uno).
// * 4) Ejecuta tests en Eclipse: Run As -> JUnit Test.
// * 5) Corrige hasta que todo quede en verde.
//
// ! IMPORTANTE
// ! Esta plantilla esta incompleta a proposito.
// ! Los fail(\"TODO...\") indican ejercicios pendientes del alumno.
// ! No borres comentarios: te guian como en examen real.

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;


// ======================================================================
// * ENUNCIADO FUNCIONAL (RESUMEN DE EXAMEN)
// ======================================================================
//
// * Metodo 1: calcularCuotaCarnet(int edad, boolean familiaNumerosa)
// *   - edad < 0              -> IllegalArgumentException
// *   - edad < 14             -> 8.0
// *   - 14 <= edad <= 64      -> 25.0
// *   - edad >= 65            -> 12.0
// *   - familiaNumerosa=true  -> descuento del 20%
//
// * Metodo 2: clasificarLecturaMensual(int librosLeidos)
// *   - librosLeidos < 0      -> IllegalArgumentException
// *   - librosLeidos < 2      -> "Ocasional"
// *   - 2 <= librosLeidos < 5 -> "Frecuente"
// *   - librosLeidos >= 5     -> "Intensiva"
//
// * Metodo 3: calcularPenalizacionRetraso(String tipoMaterial, int diasRetraso)
// *   - diasRetraso < 0       -> IllegalArgumentException
// *   - tipoMaterial null o desconocido -> IllegalArgumentException
// *   - libro   -> 0.50 por dia
// *   - revista -> 0.25 por dia
// *   - dvd     -> 1.50 por dia
// *   - resultado = tarifaDia * diasRetraso
//
// * Metodo 4: validarCodigoPrestamo(String codigo)
// *   - null -> false
// *   - longitud != 10 -> false
// *   - formato valido: LL-DDDDDDL
// *   - ejemplo valido: AB-123456Z


// ======================================================================
// ? CLASE TEST (ALUMNO)
// ? Se asume que la clase Biblioteca ya existe en el proyecto.
// ======================================================================
class BibliotecaAlumnoTest {

    // * Objeto a testear
    private Biblioteca biblioteca;

    @BeforeAll
    static void inicio() {
        // ! @BeforeAll debe ser static
        System.out.println("==============================================");
        System.out.println(" INICIO: Simulacro alumno - Biblioteca ");
        System.out.println("==============================================");
    }

    @BeforeEach
    void setUp() {
        // * Aislamiento: objeto nuevo en cada test
        biblioteca = new Biblioteca();
    }

    @AfterAll
    static void fin() {
        // ! @AfterAll debe ser static
        System.out.println("==============================================");
        System.out.println(" FIN: Simulacro alumno - Biblioteca ");
        System.out.println("==============================================");
    }


    // ==================================================================
    // * METODO 1 - calcularCuotaCarnet
    // ==================================================================
    //
    // ? PARTICIONES:
    // ? P1: edad < 0 (invalida)               -> excepcion
    // ? P2: 0 <= edad < 14 (valida)           -> 8.0
    // ? P3: 14 <= edad <= 64 (valida)         -> 25.0
    // ? P4: edad >= 65 (valida)               -> 12.0
    // ? P5: familiaNumerosa=true (valida)     -> -20%
    //
    // ? AVL:
    // ? frontera en 14 -> 13, 14, 15
    // ? frontera en 65 -> 64, 65, 66
    // ==================================================================

    @Test
    @DisplayName("[M1-P1] Edad negativa -> IllegalArgumentException")
    void cuota_EdadNegativa_LanzaExcepcion() {
        // TODO: assertThrows con edad negativa
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M1-P2] Nino sin descuento -> 8.0")
    void cuota_NinoSinDescuento_Devuelve8() {
        // TODO: AAA completo
        // ARRANGE: edad=10, familiaNumerosa=false
        // ACT: llamar metodo
        // ASSERT: assertEquals(8.0, real, 0.01)
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M1-P3] Adulto sin descuento -> 25.0")
    void cuota_AdultoSinDescuento_Devuelve25() {
        // TODO: completar caso adulto
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M1-P4] Senior sin descuento -> 12.0")
    void cuota_SeniorSinDescuento_Devuelve12() {
        // TODO: completar caso senior
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M1-P5] Adulto familia numerosa -> 20.0")
    void cuota_AdultoFamiliaNumerosa_Devuelve20() {
        // TODO: 25.0 con descuento 20% = 20.0
        fail("TODO alumno: completar test");
    }

    @ParameterizedTest
    @CsvSource({
        // edad, familiaNumerosa, esperado
        "13, false, 8.0",
        "14, false, 25.0",
        "15, false, 25.0",
        "64, false, 25.0",
        "65, false, 12.0",
        "66, false, 12.0"
    })
    @DisplayName("[M1-AVL] Limites de edad en 14 y 65")
    void cuota_Limites_Edad(int edad, boolean familiaNumerosa, double esperado) {
        // TODO: assertEquals con delta
        fail("TODO alumno: completar test");
    }


    // ==================================================================
    // * METODO 2 - clasificarLecturaMensual
    // ==================================================================
    //
    // ? PARTICIONES:
    // ? P1: librosLeidos < 0              -> excepcion
    // ? P2: librosLeidos < 2              -> "Ocasional"
    // ? P3: 2 <= librosLeidos < 5         -> "Frecuente"
    // ? P4: librosLeidos >= 5             -> "Intensiva"
    //
    // ? AVL:
    // ? frontera en 2 -> 1, 2, 3
    // ? frontera en 5 -> 4, 5, 6
    // ==================================================================

    @Test
    @DisplayName("[M2-P1] Libros negativos -> IllegalArgumentException")
    void lectura_LibrosNegativos_LanzaExcepcion() {
        // TODO: assertThrows con -1
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M2-P2] 0 libros -> Ocasional")
    void lectura_CeroLibros_Ocasional() {
        // TODO: assertEquals(\"Ocasional\", ...)
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M2-AVL] 2 exacto -> Frecuente")
    void lectura_DosExacto_Frecuente() {
        // TODO: assertEquals(\"Frecuente\", ...)
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M2-AVL] 5 exacto -> Intensiva")
    void lectura_CincoExacto_Intensiva() {
        // TODO: assertEquals(\"Intensiva\", ...)
        fail("TODO alumno: completar test");
    }

    @ParameterizedTest
    @CsvSource({
        // librosLeidos, esperado
        "1, Ocasional",
        "2, Frecuente",
        "4, Frecuente",
        "5, Intensiva",
        "8, Intensiva"
    })
    @DisplayName("[M2-PARAM] Clasificacion con varios casos")
    void lectura_VariosCasos(int librosLeidos, String esperado) {
        // TODO: assertEquals(esperado, ...)
        fail("TODO alumno: completar test");
    }


    // ==================================================================
    // * METODO 3 - calcularPenalizacionRetraso
    // ==================================================================
    //
    // ? PARTICIONES:
    // ? P1: diasRetraso < 0 (invalida)      -> excepcion
    // ? P2: tipo null o desconocido         -> excepcion
    // ? P3: tipo libro                       -> dias * 0.50
    // ? P4: tipo revista                     -> dias * 0.25
    // ? P5: tipo dvd                         -> dias * 1.50
    //
    // ? AVL de dias:
    // ? frontera 0 -> -1, 0, 1
    // ==================================================================

    @Test
    @DisplayName("[M3-P1] Dias negativos -> IllegalArgumentException")
    void penalizacion_DiasNegativos_LanzaExcepcion() {
        // TODO: assertThrows
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-P2] Tipo desconocido -> IllegalArgumentException")
    void penalizacion_TipoDesconocido_LanzaExcepcion() {
        // TODO: assertThrows con tipo \"comic\"
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-P2] Tipo null -> IllegalArgumentException")
    void penalizacion_TipoNull_LanzaExcepcion() {
        // TODO: assertThrows con tipo null
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-AVL] 0 dias (libro) -> 0.0")
    void penalizacion_CeroDias_DevuelveCero() {
        // TODO: assertEquals(0.0, ..., 0.01)
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-P3] Libro 10 dias -> 5.0")
    void penalizacion_Libro10_Devuelve5() {
        // TODO: 10 * 0.50 = 5.0
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-P4] Revista 10 dias -> 2.5")
    void penalizacion_Revista10_Devuelve2_5() {
        // TODO: 10 * 0.25 = 2.5
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-P5] DVD 4 dias -> 6.0")
    void penalizacion_Dvd4_Devuelve6() {
        // TODO: 4 * 1.50 = 6.0
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-EXTRA] Tipo en mayusculas (LIBRO) funciona")
    void penalizacion_TipoMayusculas_Funciona() {
        // TODO: assertEquals para tipo \"LIBRO\" y dias 6 -> 3.0
        fail("TODO alumno: completar test");
    }

    @ParameterizedTest
    @CsvSource({
        // tipo, dias, esperado
        "libro,   1, 0.5",
        "libro,  10, 5.0",
        "revista, 4, 1.0",
        "revista, 8, 2.0",
        "dvd,     2, 3.0",
        "dvd,     6, 9.0"
    })
    @DisplayName("[M3-PARAM] Penalizacion con varios datos")
    void penalizacion_VariosCasos(String tipoMaterial, int diasRetraso, double esperado) {
        // TODO: assertEquals con delta
        fail("TODO alumno: completar test");
    }


    // ==================================================================
    // * METODO 4 - validarCodigoPrestamo
    // ==================================================================
    //
    // ? FORMATO VALIDO:
    // ? LL-DDDDDDL
    // ? ejemplo: AB-123456Z
    //
    // ? PARTICIONES:
    // ? P1: null                           -> false
    // ? P2: longitud != 10                 -> false
    // ? P3: posiciones 0-1 no letras       -> false
    // ? P4: posicion 2 != '-'              -> false
    // ? P5: posiciones 3-8 no digitos      -> false
    // ? P6: posicion 9 no letra            -> false
    // ? P7: formato correcto               -> true
    //
    // ? AVL longitud:
    // ? 9, 10, 11
    // ==================================================================

    @Test
    @DisplayName("[M4-P7] Codigo valido AB-123456Z -> true")
    void codigo_Valido_True() {
        // TODO: assertTrue(...)
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P7] Codigo valido en minusculas -> true")
    void codigo_ValidoMinusculas_True() {
        // TODO: assertTrue para \"ab-000111x\"
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P1] Codigo null -> false")
    void codigo_Null_False() {
        // TODO: assertFalse(...)
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P2/AVL] Longitud 9 -> false")
    void codigo_Longitud9_False() {
        // TODO: assertFalse con codigo de 9 chars
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P2/AVL] Longitud 11 -> false")
    void codigo_Longitud11_False() {
        // TODO: assertFalse con codigo de 11 chars
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P3] Inicio con digitos -> false")
    void codigo_InicioDigitos_False() {
        // TODO: assertFalse
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P4] Sin guion en posicion 2 -> false")
    void codigo_SinGuion_False() {
        // TODO: assertFalse
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P5] Letras en zona numerica -> false")
    void codigo_LetrasEnZonaNumerica_False() {
        // TODO: assertFalse
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P6] Ultimo caracter no letra -> false")
    void codigo_UltimoNoLetra_False() {
        // TODO: assertFalse
        fail("TODO alumno: completar test");
    }

    @ParameterizedTest
    @CsvSource({
        // codigo, esperado
        "'AB-123456Z', true",
        "'XY-000001Q', true",
        "'ab-999999m', true",
        "'AB-12345Z',  false",
        "'AB-1234567Z',false",
        "'12-123456Z', false",
        "'ABX123456Z', false",
        "'AB-12A456Z', false",
        "'AB-1234567', false",
        "'',           false"
    })
    @DisplayName("[M4-PARAM] Validacion de codigo de prestamo")
    void codigo_VariosCasos(String codigo, boolean esperado) {
        // TODO: assertEquals(esperado, ...)
        fail("TODO alumno: completar test");
    }


    // ==================================================================
    // * TEST GLOBAL (OPCIONAL) - assertAll
    // ==================================================================
    //
    // ? IDEA:
    // ? Reunir comprobaciones clave de los 4 metodos.
    // ? En un examen puede darte puntos extra de claridad.
    // ==================================================================
    @Test
    @DisplayName("[GLOBAL] Verificacion conjunta")
    void global_Verificacion_assertAll() {
        // TODO: crear assertAll con:
        // TODO: 2 asserts de cuota
        // TODO: 2 asserts de clasificacion
        // TODO: 2 asserts de penalizacion
        // TODO: 2 asserts de validacion de codigo
        fail("TODO alumno: completar test global");
    }
}


// ======================================================================
// * GUIA RAPIDA DE RESOLUCION (EN ORDEN)
// ======================================================================
//
// * 1) Completa primero todos los test invalidos (excepcion/false).
// * 2) Luego completa los casos validos principales.
// * 3) Despues rellena los limites (AVL).
// * 4) Finalmente rellena parametrizados y test global.
//
// ! REGLA DE ORO
// ! Si un test falla, revisa:
// ! - entrada usada
// ! - esperado correcto
// ! - assert adecuado
//
// * CONSEJO DE EXAMEN
// * Si te quedas sin tiempo:
// * - Prioriza invalidos + un valido por particion + limites exactos.
// ======================================================================
