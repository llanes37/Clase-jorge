package tests;

// ======================================================================
// ? PLANTILLA EXAMEN (ALUMNO) - TESTING CAJA NEGRA
// ? TEMA: CLUB NAUTICO "MAR ABIERTO"
// ? Archivo para practicar en clase con huecos vacios
// ======================================================================
//
// * OBJETIVO DEL ALUMNO
// * 1) Leer la especificacion de cada metodo
// * 2) Identificar particiones de equivalencia
// * 3) Identificar valores limite
// * 4) Completar los tests (AAA) en los huecos TODO
//
// ! IMPORTANTE
// ! Esta plantilla esta MUY comentada para guiar el examen.
// ! El codigo de los tests esta incompleto a proposito.
// ! Tu tarea es rellenar los huecos.

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;


// ======================================================================
// * ENUNCIADO (RESUMEN)
// ======================================================================
//
// * Metodo 1: calcularCuotaSocio(int edad, boolean esVeterano)
// *   - edad < 0           -> IllegalArgumentException
// *   - edad < 18          -> 30.0
// *   - 18 <= edad <= 64   -> 60.0
// *   - edad >= 65         -> 40.0
// *   - esVeterano=true    -> descuento 15%
//
// * Metodo 2: clasificarEmbarcacion(double eslora)
// *   - eslora < 0         -> IllegalArgumentException
// *   - eslora < 6.0       -> "Pequena"
// *   - 6.0 <= eslora < 12 -> "Mediana"
// *   - eslora >= 12.0     -> "Grande"
//
// * Metodo 3: calcularAmarreMensual(String tipo, double eslora)
// *   - eslora <= 0        -> IllegalArgumentException
// *   - tipo desconocido   -> IllegalArgumentException
// *   - base = eslora * 10
// *   - "lancha" -> base
// *   - "velero" -> base * 1.20
// *   - "yate"   -> base * 1.50
//
// * Metodo 4: validarMatricula(String matricula)
// *   - null                -> false
// *   - longitud != 8       -> false
// *   - formato: LLDDDDLL   -> true
// *   - ejemplo valido: AB1234CD


// ======================================================================
// ? CLASE TEST (ALUMNO)
// ? NOTA: Se asume que la clase ClubNautico ya existe en el proyecto.
// ======================================================================
class ClubNauticoAlumnoTest {

    // * Instancia a usar en los tests
    private ClubNautico club;

    @BeforeAll
    static void inicio() {
        // ! BeforeAll debe ser static
        System.out.println("====================================");
        System.out.println(" INICIO: Plantilla alumno ClubNautico ");
        System.out.println("====================================");
    }

    @BeforeEach
    void setUp() {
        // * Se crea objeto nuevo antes de cada test (aislamiento)
        club = new ClubNautico();
    }

    @AfterAll
    static void fin() {
        // ! AfterAll debe ser static
        System.out.println("====================================");
        System.out.println(" FIN: Plantilla alumno ");
        System.out.println("====================================");
    }


    // ==================================================================
    // * METODO 1 - calcularCuotaSocio
    // ==================================================================
    //
    // ? PARTICIONES:
    // ? P1: edad < 0 (invalida)              -> excepcion
    // ? P2: 0 <= edad < 18 (valida)          -> 30.0
    // ? P3: 18 <= edad <= 64 (valida)        -> 60.0
    // ? P4: edad >= 65 (valida)              -> 40.0
    // ? P5: esVeterano=true (valida extra)   -> -15%
    //
    // ? AVL:
    // ? frontera en 18 -> 17, 18, 19
    // ? frontera en 65 -> 64, 65, 66
    // ==================================================================

    @Test
    @DisplayName("[M1-P1] Edad negativa -> IllegalArgumentException")
    void cuota_EdadNegativa_LanzaExcepcion() {
        // TODO: completa el assertThrows con la llamada al metodo
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M1-P2] Menor de edad -> cuota 30")
    void cuota_Menor_Devuelve30() {
        // TODO: ARRANGE (edad, esVeterano)
        // TODO: ACT (llamada al metodo)
        // TODO: ASSERT (assertEquals con delta)
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M1-P3] Adulto -> cuota 60")
    void cuota_Adulto_Devuelve60() {
        // TODO: completa este caso de particion valida
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M1-P4] Senior -> cuota 40")
    void cuota_Senior_Devuelve40() {
        // TODO: completa este caso de particion valida
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M1-P5] Adulto veterano -> 51 (60 - 15%)")
    void cuota_AdultoVeterano_Devuelve51() {
        // TODO: completa caso con descuento
        fail("TODO alumno: completar test");
    }

    @ParameterizedTest
    @CsvSource({
        // edad, esVeterano, esperado
        "17, false, 30.0",
        "18, false, 60.0",
        "64, false, 60.0",
        "65, false, 40.0",
        "66, false, 40.0"
    })
    @DisplayName("[M1-AVL] Limites de edad 18 y 65")
    void cuota_Limites_Edad(int edad, boolean esVeterano, double esperado) {
        // TODO: usar assertEquals(esperado, llamada, delta)
        fail("TODO alumno: completar test");
    }


    // ==================================================================
    // * METODO 2 - clasificarEmbarcacion
    // ==================================================================
    //
    // ? PARTICIONES:
    // ? P1: eslora < 0                 -> excepcion
    // ? P2: eslora < 6                 -> "Pequena"
    // ? P3: 6 <= eslora < 12           -> "Mediana"
    // ? P4: eslora >= 12               -> "Grande"
    //
    // ? AVL:
    // ? frontera en 6  -> 5.9, 6.0, 6.1
    // ? frontera en 12 -> 11.9, 12.0, 12.1
    // ==================================================================

    @Test
    @DisplayName("[M2-P1] Eslora negativa -> IllegalArgumentException")
    void clasificar_EsloraNegativa_LanzaExcepcion() {
        // TODO: completar assertThrows
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M2-P2] Eslora 3.0 -> Pequena")
    void clasificar_Pequena() {
        // TODO: completar assertEquals
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M2-AVL] Eslora 6.0 exacto -> Mediana")
    void clasificar_SeisExacto_Mediana() {
        // TODO: completar assertEquals
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M2-AVL] Eslora 12.0 exacto -> Grande")
    void clasificar_DoceExacto_Grande() {
        // TODO: completar assertEquals
        fail("TODO alumno: completar test");
    }

    @ParameterizedTest
    @CsvSource({
        // eslora, esperado
        "5.9, Pequena",
        "6.0, Mediana",
        "11.9, Mediana",
        "12.0, Grande"
    })
    @DisplayName("[M2-PARAM] Clasificacion por eslora")
    void clasificar_VariosCasos(double eslora, String esperado) {
        // TODO: completar assertEquals(esperado, ...)
        fail("TODO alumno: completar test");
    }


    // ==================================================================
    // * METODO 3 - calcularAmarreMensual
    // ==================================================================
    //
    // ? PARTICIONES:
    // ? P1: eslora <= 0 (invalida)   -> excepcion
    // ? P2: tipo desconocido         -> excepcion
    // ? P3: lancha                   -> base
    // ? P4: velero                   -> base * 1.20
    // ? P5: yate                     -> base * 1.50
    //
    // ? FORMULA:
    // ? base = eslora * 10
    // ==================================================================

    @Test
    @DisplayName("[M3-P1] Eslora 0 -> IllegalArgumentException")
    void amarre_EsloraCero_LanzaExcepcion() {
        // TODO: completar assertThrows
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-P2] Tipo desconocido -> IllegalArgumentException")
    void amarre_TipoDesconocido_LanzaExcepcion() {
        // TODO: completar assertThrows
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-P3] Lancha 10m -> 100")
    void amarre_Lancha10_Devuelve100() {
        // TODO: completar caso
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-P4] Velero 10m -> 120")
    void amarre_Velero10_Devuelve120() {
        // TODO: completar caso
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M3-P5] Yate 10m -> 150")
    void amarre_Yate10_Devuelve150() {
        // TODO: completar caso
        fail("TODO alumno: completar test");
    }

    @ParameterizedTest
    @CsvSource({
        // tipo, eslora, esperado
        "lancha, 5.0, 50.0",
        "lancha,10.0,100.0",
        "velero,5.0, 60.0",
        "velero,10.0,120.0",
        "yate,  5.0, 75.0",
        "yate, 20.0,300.0"
    })
    @DisplayName("[M3-PARAM] Tarifas de amarre")
    void amarre_VariosCasos(String tipo, double eslora, double esperado) {
        // TODO: completar assertEquals con delta
        fail("TODO alumno: completar test");
    }


    // ==================================================================
    // * METODO 4 - validarMatricula
    // ==================================================================
    //
    // ? FORMATO VALIDO:
    // ? LLDDDDLL  (2 letras + 4 digitos + 2 letras)
    //
    // ? PARTICIONES:
    // ? P1: null                     -> false
    // ? P2: longitud != 8            -> false
    // ? P3: pos 0-1 no letras        -> false
    // ? P4: pos 2-5 no digitos       -> false
    // ? P5: pos 6-7 no letras        -> false
    // ? P6: formato correcto         -> true
    //
    // ? AVL longitud:
    // ? 7, 8, 9
    // ==================================================================

    @Test
    @DisplayName("[M4-P6] Matricula valida AB1234CD -> true")
    void matricula_Valida_True() {
        // TODO: completar assertTrue
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P1] Matricula null -> false")
    void matricula_Null_False() {
        // TODO: completar assertFalse
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P2/AVL] Longitud 7 -> false")
    void matricula_Longitud7_False() {
        // TODO: completar assertFalse
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P2/AVL] Longitud 9 -> false")
    void matricula_Longitud9_False() {
        // TODO: completar assertFalse
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P3] Empieza con numeros -> false")
    void matricula_InicioNumeros_False() {
        // TODO: completar assertFalse
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P4] Letras donde deben ir digitos -> false")
    void matricula_ZonaDigitosIncorrecta_False() {
        // TODO: completar assertFalse
        fail("TODO alumno: completar test");
    }

    @Test
    @DisplayName("[M4-P5] Termina con numeros -> false")
    void matricula_FinalNumeros_False() {
        // TODO: completar assertFalse
        fail("TODO alumno: completar test");
    }

    @ParameterizedTest
    @CsvSource({
        // matricula, esperado
        "'AB1234CD', true",
        "'ZZ9999ZZ', true",
        "'AB123CD',  false",
        "'AB1234CDE',false",
        "'121234CD', false",
        "'ABABCDCD', false",
        "'AB123456', false",
        "'',         false"
    })
    @DisplayName("[M4-PARAM] Validacion de matriculas")
    void matricula_VariosCasos(String matricula, boolean esperado) {
        // TODO: completar assertEquals(esperado, ...)
        fail("TODO alumno: completar test");
    }


    // ==================================================================
    // * TEST GLOBAL (OPCIONAL DE EXAMEN) - assertAll
    // ==================================================================
    //
    // ? IDEA:
    // ? Reunir verificaciones clave de los 4 metodos.
    // ? assertAll ejecuta todos los asserts aunque uno falle.
    // ==================================================================

    @Test
    @DisplayName("[GLOBAL] Verificacion conjunta de metodos")
    void global_Verificacion_assertAll() {
        // TODO: crear assertAll con al menos:
        // TODO: 2 asserts de cuota
        // TODO: 2 asserts de clasificacion
        // TODO: 2 asserts de amarre
        // TODO: 2 asserts de matricula
        fail("TODO alumno: completar test global");
    }
}


// ======================================================================
// * MINI GUIA DE RESOLUCION PARA EL ALUMNO
// ======================================================================
//
// * PASO 1: empieza por invalidos
// * - suelen dar mas pistas del contrato del metodo
//
// * PASO 2: cubre una prueba por particion valida
// * - no repitas casos equivalentes sin necesidad
//
// * PASO 3: cubre limites exactos
// * - ahi aparecen errores tipicos de < y <=
//
// * PASO 4: usa parametrizados para ahorrar codigo
// * - cuando la logica del test es la misma y cambian datos
//
// * PASO 5: revisa nombres y DisplayName
// * - que se entienda que se prueba y que se espera
//
// ! CONSEJO DE EXAMEN
// ! Si te bloqueas, haz primero:
// ! - 1 test invalido por metodo
// ! - 1 test valido por metodo
// ! Luego anades limites y parametrizados.
// ======================================================================
