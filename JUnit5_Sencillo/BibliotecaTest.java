package tests;

// ======================================================================
// EXAMEN PRACTICO: TESTING CAJA NEGRA
// ======================================================================
// TEMA:
//   Biblioteca Municipal "Lectura Viva"
//
// IDEA DE CAJA NEGRA:
//   Probamos metodos por especificacion, sin mirar codigo interno.
//
// TECNICAS:
//   1) Particiones de Equivalencia
//   2) Analisis de Valores Limite (AVL)
// ======================================================================

// ----------------------------------------------------------------------
// IMPORTS - JUnit 5 (org.junit.jupiter)
// ----------------------------------------------------------------------
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;


// ======================================================================
// CLASE DE PRODUCCION: Biblioteca
// En examen real te la suelen dar hecha. Aqui se incluye para practicar.
// ======================================================================
class Biblioteca {

    public double calcularCuotaCarnet(int edad, boolean familiaNumerosa) {
        if (edad < 0) throw new IllegalArgumentException("La edad no puede ser negativa");
        double cuota = (edad < 14) ? 8.0 : (edad < 65) ? 25.0 : 12.0;
        return familiaNumerosa ? cuota * 0.80 : cuota;
    }

    public String clasificarLecturaMensual(int librosLeidos) {
        if (librosLeidos < 0) throw new IllegalArgumentException("No se permiten libros negativos");
        if (librosLeidos < 2) return "Ocasional";
        if (librosLeidos < 5) return "Frecuente";
        return "Intensiva";
    }

    public double calcularPenalizacionRetraso(String tipoMaterial, int diasRetraso) {
        if (diasRetraso < 0) throw new IllegalArgumentException("Los dias de retraso no pueden ser negativos");
        if (tipoMaterial == null) throw new IllegalArgumentException("Tipo desconocido: null");

        double tarifaDia;
        switch (tipoMaterial.toLowerCase()) {
            case "libro": tarifaDia = 0.50; break;
            case "revista": tarifaDia = 0.25; break;
            case "dvd": tarifaDia = 1.50; break;
            default: throw new IllegalArgumentException("Tipo desconocido: " + tipoMaterial);
        }

        return tarifaDia * diasRetraso;
    }

    public boolean validarCodigoPrestamo(String codigo) {
        // Formato valido: LL-DDDDDDL (2 letras + '-' + 6 digitos + 1 letra)
        // Ejemplo: AB-123456Z
        if (codigo == null || codigo.length() != 10) return false;
        if (!Character.isLetter(codigo.charAt(0)) || !Character.isLetter(codigo.charAt(1))) return false;
        if (codigo.charAt(2) != '-') return false;
        for (int i = 3; i < 9; i++) {
            if (!Character.isDigit(codigo.charAt(i))) return false;
        }
        return Character.isLetter(codigo.charAt(9));
    }
}


// ======================================================================
// SOLUCION - TESTS DE CAJA NEGRA
// ======================================================================
class BibliotecaTest {

    private Biblioteca biblioteca;

    @BeforeAll
    static void inicio() {
        System.out.println("====================================");
        System.out.println(" INICIO: Tests Biblioteca ");
        System.out.println("====================================");
    }

    @BeforeEach
    void setUp() {
        biblioteca = new Biblioteca();
    }

    @AfterAll
    static void fin() {
        System.out.println("====================================");
        System.out.println(" FIN: Tests completados ");
        System.out.println("====================================");
    }


    // ==================================================================
    // METODO 1 - calcularCuotaCarnet(int edad, boolean familiaNumerosa)
    // ==================================================================
    //
    // PARTICIONES:
    // P1: edad < 0                  -> IllegalArgumentException
    // P2: 0 <= edad < 14            -> 8.0
    // P3: 14 <= edad <= 64          -> 25.0
    // P4: edad >= 65                -> 12.0
    // P5: familiaNumerosa = true    -> descuento 20%
    //
    // AVL:
    // Frontera 14: 13, 14, 15
    // Frontera 65: 64, 65, 66
    // ==================================================================

    @Test
    @DisplayName("[P1-INV] Edad negativa -> IllegalArgumentException")
    void cuota_EdadNegativa_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> biblioteca.calcularCuotaCarnet(-1, false)
        );
    }

    @Test
    @DisplayName("[P2] Nino sin descuento -> 8.0")
    void cuota_NinoSinDescuento_Devuelve8() {
        double cuota = biblioteca.calcularCuotaCarnet(10, false);
        assertEquals(8.0, cuota, 0.01);
    }

    @Test
    @DisplayName("[P3] Adulto sin descuento -> 25.0")
    void cuota_AdultoSinDescuento_Devuelve25() {
        double cuota = biblioteca.calcularCuotaCarnet(30, false);
        assertEquals(25.0, cuota, 0.01);
    }

    @Test
    @DisplayName("[P4] Senior sin descuento -> 12.0")
    void cuota_SeniorSinDescuento_Devuelve12() {
        double cuota = biblioteca.calcularCuotaCarnet(70, false);
        assertEquals(12.0, cuota, 0.01);
    }

    @Test
    @DisplayName("[P5] Adulto familia numerosa -> 20.0")
    void cuota_AdultoFamiliaNumerosa_Devuelve20() {
        double cuota = biblioteca.calcularCuotaCarnet(30, true);
        assertEquals(20.0, cuota, 0.01);
    }

    @ParameterizedTest
    @ValueSource(ints = {13, 14, 15, 64, 65, 66})
    @DisplayName("[AVL] Limites de edad en 14 y 65")
    void cuota_EdadesLimite_CambianTramoCorrecto(int edad) {
        double cuota = biblioteca.calcularCuotaCarnet(edad, false);
        if (edad <= 13) {
            assertEquals(8.0, cuota, 0.01);
        } else if (edad <= 64) {
            assertEquals(25.0, cuota, 0.01);
        } else {
            assertEquals(12.0, cuota, 0.01);
        }
    }

    @ParameterizedTest
    @CsvSource({
        "10, false, 8.0",
        "30, false, 25.0",
        "70, false, 12.0",
        "30, true, 20.0",
        "70, true, 9.6"
    })
    @DisplayName("[PARAM] Cuota de carnet en varios escenarios")
    void cuota_VariosCasos_CalculaCorrectamente(int edad, boolean familiaNumerosa, double esperado) {
        assertEquals(esperado, biblioteca.calcularCuotaCarnet(edad, familiaNumerosa), 0.01);
    }


    // ==================================================================
    // METODO 2 - clasificarLecturaMensual(int librosLeidos)
    // ==================================================================
    //
    // PARTICIONES:
    // P1: librosLeidos < 0        -> IllegalArgumentException
    // P2: librosLeidos < 2        -> "Ocasional"
    // P3: 2 <= librosLeidos < 5   -> "Frecuente"
    // P4: librosLeidos >= 5       -> "Intensiva"
    //
    // AVL:
    // Frontera 2: 1, 2, 3
    // Frontera 5: 4, 5, 6
    // ==================================================================

    @Test
    @DisplayName("[P1-INV] Libros negativos -> IllegalArgumentException")
    void lectura_LibrosNegativos_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> biblioteca.clasificarLecturaMensual(-1)
        );
    }

    @Test
    @DisplayName("[P2] 0 libros -> Ocasional")
    void lectura_CeroLibros_Ocasional() {
        assertEquals("Ocasional", biblioteca.clasificarLecturaMensual(0));
    }

    @Test
    @DisplayName("[AVL] 2 libros exactos -> Frecuente")
    void lectura_DosLibros_Frecuente() {
        assertEquals("Frecuente", biblioteca.clasificarLecturaMensual(2));
    }

    @Test
    @DisplayName("[AVL] 5 libros exactos -> Intensiva")
    void lectura_CincoLibros_Intensiva() {
        assertEquals("Intensiva", biblioteca.clasificarLecturaMensual(5));
    }

    @ParameterizedTest
    @CsvSource({
        "1, Ocasional",
        "2, Frecuente",
        "4, Frecuente",
        "5, Intensiva",
        "8, Intensiva"
    })
    @DisplayName("[PARAM] Clasificacion de lectura por numero de libros")
    void lectura_VariosCasos_ClasificaCorrectamente(int librosLeidos, String esperado) {
        assertEquals(esperado, biblioteca.clasificarLecturaMensual(librosLeidos));
    }


    // ==================================================================
    // METODO 3 - calcularPenalizacionRetraso(String tipoMaterial, int dias)
    // ==================================================================
    //
    // REGLAS:
    // - dias < 0                     -> IllegalArgumentException
    // - tipo desconocido o null      -> IllegalArgumentException
    // - libro   -> 0.50 por dia
    // - revista -> 0.25 por dia
    // - dvd     -> 1.50 por dia
    //
    // AVL PRINCIPAL:
    // frontera de dias en 0: -1 (inv), 0 (exacto), 1 (valido)
    // ==================================================================

    @Test
    @DisplayName("[P1-INV] Dias negativos -> IllegalArgumentException")
    void penalizacion_DiasNegativos_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> biblioteca.calcularPenalizacionRetraso("libro", -1)
        );
    }

    @Test
    @DisplayName("[P2-INV] Tipo desconocido -> IllegalArgumentException")
    void penalizacion_TipoDesconocido_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> biblioteca.calcularPenalizacionRetraso("comic", 3)
        );
    }

    @Test
    @DisplayName("[P2-INV] Tipo null -> IllegalArgumentException")
    void penalizacion_TipoNull_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> biblioteca.calcularPenalizacionRetraso(null, 3)
        );
    }

    @Test
    @DisplayName("[AVL] 0 dias -> 0.0")
    void penalizacion_CeroDias_DevuelveCero() {
        assertEquals(0.0, biblioteca.calcularPenalizacionRetraso("libro", 0), 0.01);
    }

    @Test
    @DisplayName("[P3] Libro 10 dias -> 5.0")
    void penalizacion_Libro10_Devuelve5() {
        assertEquals(5.0, biblioteca.calcularPenalizacionRetraso("libro", 10), 0.01);
    }

    @Test
    @DisplayName("[P4] Revista 10 dias -> 2.5")
    void penalizacion_Revista10_Devuelve2_5() {
        assertEquals(2.5, biblioteca.calcularPenalizacionRetraso("revista", 10), 0.01);
    }

    @Test
    @DisplayName("[P5] DVD 4 dias -> 6.0")
    void penalizacion_Dvd4_Devuelve6() {
        assertEquals(6.0, biblioteca.calcularPenalizacionRetraso("dvd", 4), 0.01);
    }

    @Test
    @DisplayName("[P3] Tipo en mayusculas funciona igual")
    void penalizacion_TipoMayusculas_Funciona() {
        assertEquals(3.0, biblioteca.calcularPenalizacionRetraso("LIBRO", 6), 0.01);
    }

    @ParameterizedTest
    @CsvSource({
        "libro,   1, 0.5",
        "libro,  10, 5.0",
        "revista, 4, 1.0",
        "revista, 8, 2.0",
        "dvd,     2, 3.0",
        "dvd,     6, 9.0"
    })
    @DisplayName("[PARAM] Penalizacion para distintos tipos y dias")
    void penalizacion_VariosCasos_CalculaCorrectamente(String tipoMaterial, int diasRetraso, double esperado) {
        assertEquals(esperado, biblioteca.calcularPenalizacionRetraso(tipoMaterial, diasRetraso), 0.01);
    }


    // ==================================================================
    // METODO 4 - validarCodigoPrestamo(String codigo)
    // ==================================================================
    //
    // FORMATO VALIDO: LL-DDDDDDL
    // Ejemplo valido: AB-123456Z
    //
    // PARTICIONES:
    // P1: null                   -> false
    // P2: longitud != 10         -> false
    // P3: pos 0-1 no letras      -> false
    // P4: pos 2 no '-'           -> false
    // P5: pos 3-8 no digitos     -> false
    // P6: pos 9 no letra         -> false
    // P7: formato correcto       -> true
    //
    // AVL de longitud:
    // 9, 10, 11
    // ==================================================================

    @Test
    @DisplayName("[P7] Codigo valido AB-123456Z -> true")
    void codigo_Valido_DevuelveTrue() {
        assertTrue(biblioteca.validarCodigoPrestamo("AB-123456Z"));
    }

    @Test
    @DisplayName("[P7] Codigo valido en minusculas -> true")
    void codigo_ValidoMinusculas_DevuelveTrue() {
        assertTrue(biblioteca.validarCodigoPrestamo("ab-000111x"));
    }

    @Test
    @DisplayName("[P1-INV] Codigo null -> false")
    void codigo_Null_DevuelveFalse() {
        assertFalse(biblioteca.validarCodigoPrestamo(null));
    }

    @Test
    @DisplayName("[P2-INV / AVL] Longitud 9 -> false")
    void codigo_Longitud9_DevuelveFalse() {
        assertFalse(biblioteca.validarCodigoPrestamo("AB-12345Z"));
    }

    @Test
    @DisplayName("[P2-INV / AVL] Longitud 11 -> false")
    void codigo_Longitud11_DevuelveFalse() {
        assertFalse(biblioteca.validarCodigoPrestamo("AB-1234567Z"));
    }

    @Test
    @DisplayName("[P3-INV] Inicio con digitos -> false")
    void codigo_InicioConDigitos_DevuelveFalse() {
        assertFalse(biblioteca.validarCodigoPrestamo("12-123456Z"));
    }

    @Test
    @DisplayName("[P4-INV] Sin guion en posicion 2 -> false")
    void codigo_SinGuion_DevuelveFalse() {
        assertFalse(biblioteca.validarCodigoPrestamo("ABX123456Z"));
    }

    @Test
    @DisplayName("[P5-INV] Letras en zona numerica -> false")
    void codigo_LetrasEnZonaNumerica_DevuelveFalse() {
        assertFalse(biblioteca.validarCodigoPrestamo("AB-12A456Z"));
    }

    @Test
    @DisplayName("[P6-INV] Ultimo caracter no letra -> false")
    void codigo_UltimoNoLetra_DevuelveFalse() {
        assertFalse(biblioteca.validarCodigoPrestamo("AB-1234567"));
    }

    @ParameterizedTest
    @CsvSource({
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
    @DisplayName("[PARAM] Validacion de codigo de prestamo")
    void codigo_VariosCasos_ValidaCorrectamente(String codigo, boolean esperado) {
        assertEquals(esperado, biblioteca.validarCodigoPrestamo(codigo));
    }


    // ==================================================================
    // TEST GLOBAL - assertAll
    // ==================================================================
    @Test
    @DisplayName("[GLOBAL] Verificacion rapida de los 4 metodos")
    void global_VerificacionCompleta_assertAll() {
        assertAll("Biblioteca - verificacion global",
            // Metodo 1
            () -> assertEquals(8.0,  biblioteca.calcularCuotaCarnet(10, false), 0.01),
            () -> assertEquals(25.0, biblioteca.calcularCuotaCarnet(30, false), 0.01),
            () -> assertEquals(12.0, biblioteca.calcularCuotaCarnet(70, false), 0.01),
            () -> assertEquals(20.0, biblioteca.calcularCuotaCarnet(30, true),  0.01),
            () -> assertThrows(IllegalArgumentException.class,
                    () -> biblioteca.calcularCuotaCarnet(-1, false)),

            // Metodo 2
            () -> assertEquals("Ocasional", biblioteca.clasificarLecturaMensual(1)),
            () -> assertEquals("Frecuente", biblioteca.clasificarLecturaMensual(2)),
            () -> assertEquals("Intensiva", biblioteca.clasificarLecturaMensual(5)),

            // Metodo 3
            () -> assertEquals(5.0, biblioteca.calcularPenalizacionRetraso("libro", 10), 0.01),
            () -> assertEquals(2.5, biblioteca.calcularPenalizacionRetraso("revista", 10), 0.01),
            () -> assertEquals(6.0, biblioteca.calcularPenalizacionRetraso("dvd", 4), 0.01),

            // Metodo 4
            () -> assertTrue(biblioteca.validarCodigoPrestamo("AB-123456Z")),
            () -> assertFalse(biblioteca.validarCodigoPrestamo(null)),
            () -> assertFalse(biblioteca.validarCodigoPrestamo("AB-12345Z"))
        );
    }
}


// ======================================================================
// RESUMEN RAPIDO PARA EXAMEN
// ======================================================================
// 1) Particiones de Equivalencia:
//    - agrupa entradas que se comportan igual
//    - cubre particiones validas e invalidas
//
// 2) AVL:
//    - prueba limite-1, limite exacto, limite+1
//    - el limite exacto suele revelar errores de < y <=
//
// 3) Asserts clave:
//    - assertEquals, assertTrue, assertFalse, assertThrows, assertAll
//
// 4) Anotaciones clave:
//    - @Test, @DisplayName, @BeforeEach, @BeforeAll/@AfterAll
//    - @ParameterizedTest + @ValueSource / @CsvSource
//
// 5) Patron AAA:
//    - Arrange, Act, Assert
// ======================================================================
