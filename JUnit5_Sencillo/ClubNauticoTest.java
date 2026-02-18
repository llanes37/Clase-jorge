package tests;

// ? =====================================================================
// ?                    EXAMEN PRÁCTICO: JUNIT 5 - CAJA NEGRA
// ?                    ════════════════════════════════════
// ?                         CLUB NÁUTICO "MAR ABIERTO"
// ? =====================================================================
// ?
// ? ENUNCIADO:
// ? ----------
// ? Se te proporciona la clase ClubNautico con los siguientes métodos:
// ?
// ? 1. calcularCuotaSocio(int edad, boolean esVeterano)
// ?    - Socios menores de 18 años: 30€
// ?    - Socios de 18 a 64 años: 60€
// ?    - Socios de 65 años o más: 40€
// ?    - Si es veterano (más de 10 años como socio): 15% de descuento
// ?    - Si la edad es negativa: lanza IllegalArgumentException
// ?
// ? 2. clasificarEmbarcacion(double eslora)
// ?    - eslora < 0: lanza IllegalArgumentException
// ?    - eslora < 6 metros: "Pequeña"
// ?    - eslora >= 6 y < 12 metros: "Mediana"
// ?    - eslora >= 12 metros: "Grande"
// ?
// ? 3. calcularAmarreMensual(String tipoEmbarcacion, double eslora)
// ?    - Tarifa base: 10€ por metro de eslora
// ?    - Si es "velero": +20% sobre la tarifa base
// ?    - Si es "yate": +50% sobre la tarifa base
// ?    - Si es "lancha": tarifa base (sin recargo)
// ?    - Tipo desconocido: lanza IllegalArgumentException
// ?    - Eslora <= 0: lanza IllegalArgumentException
// ?
// ? 4. validarMatricula(String matricula)
// ?    - Formato válido: 2 letras + 4 números + 2 letras (ej: "AB1234CD")
// ?    - Debe tener exactamente 8 caracteres
// ?    - Devuelve true si es válida, false si no
// ?    - Si es null: devuelve false
// ?
// ? TAREAS:
// ? -------
// ? 1. Escribe tests de caja negra para TODOS los métodos
// ? 2. Cubre los casos normales, límites y de error
// ? 3. Usa las anotaciones: @Test, @DisplayName, @ParameterizedTest
// ? 4. Para excepciones usa assertThrows
// ? 5. Sigue el patrón AAA (Arrange, Act, Assert)
// ?
// ? PUNTUACIÓN:
// ? -----------
// ? - calcularCuotaSocio: 2.5 puntos
// ? - clasificarEmbarcacion: 2.5 puntos
// ? - calcularAmarreMensual: 2.5 puntos
// ? - validarMatricula: 2.5 puntos
// ?
// ? =====================================================================


// * =====================================================================
// * IMPORTS — Siempre de org.junit.jupiter (JUnit 5)
// * =====================================================================
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;


// * =====================================================================
// * CLASE CLUBNAUTICO — La clase que debemos testear
// * =====================================================================
// ! En un examen real, esta clase te la dan hecha (o solo la especificación)
// ! Tú solo escribes los tests (caja negra = sin ver el código interno)

class ClubNautico {

    // * Método 1: Calcular la cuota de un socio según su edad y antigüedad
    public double calcularCuotaSocio(int edad, boolean esVeterano) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        
        double cuota;
        if (edad < 18) {
            cuota = 30.0;
        } else if (edad < 65) {
            cuota = 60.0;
        } else {
            cuota = 40.0;
        }
        
        // Descuento del 15% para veteranos
        if (esVeterano) {
            cuota = cuota * 0.85;
        }
        
        return cuota;
    }

    // * Método 2: Clasificar embarcación según su eslora (longitud)
    public String clasificarEmbarcacion(double eslora) {
        if (eslora < 0) {
            throw new IllegalArgumentException("La eslora no puede ser negativa");
        }
        
        if (eslora < 6) {
            return "Pequeña";
        } else if (eslora < 12) {
            return "Mediana";
        } else {
            return "Grande";
        }
    }

    // * Método 3: Calcular tarifa mensual de amarre
    public double calcularAmarreMensual(String tipoEmbarcacion, double eslora) {
        if (eslora <= 0) {
            throw new IllegalArgumentException("La eslora debe ser positiva");
        }
        
        double tarifaBase = eslora * 10; // 10€ por metro
        
        switch (tipoEmbarcacion.toLowerCase()) {
            case "lancha":
                return tarifaBase;
            case "velero":
                return tarifaBase * 1.20; // +20%
            case "yate":
                return tarifaBase * 1.50; // +50%
            default:
                throw new IllegalArgumentException("Tipo de embarcación desconocido: " + tipoEmbarcacion);
        }
    }

    // * Método 4: Validar formato de matrícula
    public boolean validarMatricula(String matricula) {
        if (matricula == null || matricula.length() != 8) {
            return false;
        }
        
        // Primeras 2: letras
        if (!Character.isLetter(matricula.charAt(0)) || 
            !Character.isLetter(matricula.charAt(1))) {
            return false;
        }
        
        // Siguientes 4: números
        for (int i = 2; i < 6; i++) {
            if (!Character.isDigit(matricula.charAt(i))) {
                return false;
            }
        }
        
        // Últimas 2: letras
        if (!Character.isLetter(matricula.charAt(6)) || 
            !Character.isLetter(matricula.charAt(7))) {
            return false;
        }
        
        return true;
    }
}


// * =====================================================================
// *                         SOLUCIÓN DE LOS TESTS
// * =====================================================================
// ! A partir de aquí está la SOLUCIÓN completa
// ! Intenta hacerlo tú primero antes de mirar
// * =====================================================================

class ClubNauticoTest {

    // * Objeto que vamos a testear
    private ClubNautico club;

    @BeforeEach
    void setUp() {
        // ? Creamos una instancia nueva antes de cada test
        club = new ClubNautico();
    }


    // ════════════════════════════════════════════════════════════════════
    //                    TESTS DE calcularCuotaSocio
    // ════════════════════════════════════════════════════════════════════
    // ? Reglas:
    // ?   - edad < 18 → 30€
    // ?   - edad 18-64 → 60€
    // ?   - edad >= 65 → 40€
    // ?   - veterano → 15% descuento
    // ?   - edad < 0 → excepción
    // ════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Cuota menor de edad (17 años) = 30€")
    void cuota_MenorDeEdad_Devuelve30() {
        // * ARRANGE
        int edad = 17;
        boolean esVeterano = false;

        // * ACT
        double cuota = club.calcularCuotaSocio(edad, esVeterano);

        // * ASSERT
        assertEquals(30.0, cuota, 0.01);
        // ? El 0.01 es el margen de error para comparar decimales
    }

    @Test
    @DisplayName("Cuota adulto (30 años) = 60€")
    void cuota_Adulto_Devuelve60() {
        // * ARRANGE
        int edad = 30;
        boolean esVeterano = false;

        // * ACT
        double cuota = club.calcularCuotaSocio(edad, esVeterano);

        // * ASSERT
        assertEquals(60.0, cuota, 0.01);
    }

    @Test
    @DisplayName("Cuota jubilado (65 años) = 40€")
    void cuota_Jubilado_Devuelve40() {
        // * ARRANGE
        int edad = 65;
        boolean esVeterano = false;

        // * ACT
        double cuota = club.calcularCuotaSocio(edad, esVeterano);

        // * ASSERT
        assertEquals(40.0, cuota, 0.01);
    }

    @Test
    @DisplayName("Cuota adulto veterano = 51€ (60€ - 15%)")
    void cuota_AdultoVeterano_DevuelveConDescuento() {
        // * ARRANGE
        int edad = 40;
        boolean esVeterano = true;

        // * ACT
        double cuota = club.calcularCuotaSocio(edad, esVeterano);

        // * ASSERT
        // ? 60€ * 0.85 = 51€
        assertEquals(51.0, cuota, 0.01);
    }

    @Test
    @DisplayName("Cuota jubilado veterano = 34€ (40€ - 15%)")
    void cuota_JubiladoVeterano_DevuelveConDescuento() {
        // * ARRANGE
        int edad = 70;
        boolean esVeterano = true;

        // * ACT
        double cuota = club.calcularCuotaSocio(edad, esVeterano);

        // * ASSERT
        // ? 40€ * 0.85 = 34€
        assertEquals(34.0, cuota, 0.01);
    }

    // * TEST DE VALOR LÍMITE: justo en 18 años
    @Test
    @DisplayName("Límite: 18 años = 60€ (ya no es menor)")
    void cuota_Limite18_Devuelve60() {
        assertEquals(60.0, club.calcularCuotaSocio(18, false), 0.01);
    }

    // * TEST DE VALOR LÍMITE: justo en 64 años (último adulto)
    @Test
    @DisplayName("Límite: 64 años = 60€ (todavía adulto)")
    void cuota_Limite64_Devuelve60() {
        assertEquals(60.0, club.calcularCuotaSocio(64, false), 0.01);
    }

    // * TEST DE EXCEPCIÓN: edad negativa
    @Test
    @DisplayName("Edad negativa lanza IllegalArgumentException")
    void cuota_EdadNegativa_LanzaExcepcion() {
        // * assertThrows: comprueba que se lanza la excepción correcta
        assertThrows(
            IllegalArgumentException.class,
            () -> club.calcularCuotaSocio(-5, false)
        );
    }

    // * TEST PARAMETRIZADO: varias edades de menores
    @ParameterizedTest
    @ValueSource(ints = {0, 5, 10, 15, 17})
    @DisplayName("Menores de 18 pagan 30€")
    void cuota_VariasMenores_Devuelve30(int edad) {
        assertEquals(30.0, club.calcularCuotaSocio(edad, false), 0.01);
    }


    // ════════════════════════════════════════════════════════════════════
    //                    TESTS DE clasificarEmbarcacion
    // ════════════════════════════════════════════════════════════════════
    // ? Reglas:
    // ?   - eslora < 6 → "Pequeña"
    // ?   - 6 <= eslora < 12 → "Mediana"
    // ?   - eslora >= 12 → "Grande"
    // ?   - eslora < 0 → excepción
    // ════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Eslora 4m = Pequeña")
    void clasificar_Eslora4_DevuelvePequena() {
        // * ARRANGE
        double eslora = 4.0;

        // * ACT
        String clasificacion = club.clasificarEmbarcacion(eslora);

        // * ASSERT
        assertEquals("Pequeña", clasificacion);
    }

    @Test
    @DisplayName("Eslora 8m = Mediana")
    void clasificar_Eslora8_DevuelveMediana() {
        assertEquals("Mediana", club.clasificarEmbarcacion(8.0));
    }

    @Test
    @DisplayName("Eslora 15m = Grande")
    void clasificar_Eslora15_DevuelveGrande() {
        assertEquals("Grande", club.clasificarEmbarcacion(15.0));
    }

    // * TESTS DE VALORES LÍMITE
    @Test
    @DisplayName("Límite: 5.99m = Pequeña")
    void clasificar_Limite599_DevuelvePequena() {
        assertEquals("Pequeña", club.clasificarEmbarcacion(5.99));
    }

    @Test
    @DisplayName("Límite: 6m = Mediana (frontera)")
    void clasificar_Limite6_DevuelveMediana() {
        // ! Caso límite: exactamente 6 metros
        assertEquals("Mediana", club.clasificarEmbarcacion(6.0));
    }

    @Test
    @DisplayName("Límite: 11.99m = Mediana")
    void clasificar_Limite1199_DevuelveMediana() {
        assertEquals("Mediana", club.clasificarEmbarcacion(11.99));
    }

    @Test
    @DisplayName("Límite: 12m = Grande (frontera)")
    void clasificar_Limite12_DevuelveGrande() {
        // ! Caso límite: exactamente 12 metros
        assertEquals("Grande", club.clasificarEmbarcacion(12.0));
    }

    @Test
    @DisplayName("Eslora 0 = Pequeña")
    void clasificar_Eslora0_DevuelvePequena() {
        assertEquals("Pequeña", club.clasificarEmbarcacion(0.0));
    }

    // * TEST DE EXCEPCIÓN
    @Test
    @DisplayName("Eslora negativa lanza excepción")
    void clasificar_EsloraNegativa_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> club.clasificarEmbarcacion(-1.0)
        );
    }

    // * TEST PARAMETRIZADO
    @ParameterizedTest
    @CsvSource({
        "1.0, Pequeña",
        "5.5, Pequeña",
        "6.0, Mediana",
        "9.0, Mediana",
        "12.0, Grande",
        "20.0, Grande"
    })
    @DisplayName("Clasificar embarcaciones con varios valores")
    void clasificar_VariasEsloras_ClasificaCorrectamente(double eslora, String esperado) {
        assertEquals(esperado, club.clasificarEmbarcacion(eslora));
    }


    // ════════════════════════════════════════════════════════════════════
    //                    TESTS DE calcularAmarreMensual
    // ════════════════════════════════════════════════════════════════════
    // ? Reglas:
    // ?   - Tarifa base: eslora * 10€
    // ?   - lancha: tarifa base
    // ?   - velero: +20%
    // ?   - yate: +50%
    // ?   - tipo desconocido → excepción
    // ?   - eslora <= 0 → excepción
    // ════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Lancha 10m = 100€ (10m × 10€)")
    void amarre_Lancha10m_Devuelve100() {
        // * ARRANGE
        String tipo = "lancha";
        double eslora = 10.0;

        // * ACT
        double tarifa = club.calcularAmarreMensual(tipo, eslora);

        // * ASSERT
        // ? 10m × 10€ = 100€
        assertEquals(100.0, tarifa, 0.01);
    }

    @Test
    @DisplayName("Velero 10m = 120€ (100€ + 20%)")
    void amarre_Velero10m_Devuelve120() {
        // * ARRANGE
        String tipo = "velero";
        double eslora = 10.0;

        // * ACT
        double tarifa = club.calcularAmarreMensual(tipo, eslora);

        // * ASSERT
        // ? 10m × 10€ = 100€, +20% = 120€
        assertEquals(120.0, tarifa, 0.01);
    }

    @Test
    @DisplayName("Yate 10m = 150€ (100€ + 50%)")
    void amarre_Yate10m_Devuelve150() {
        // * ARRANGE
        String tipo = "yate";
        double eslora = 10.0;

        // * ACT
        double tarifa = club.calcularAmarreMensual(tipo, eslora);

        // * ASSERT
        // ? 10m × 10€ = 100€, +50% = 150€
        assertEquals(150.0, tarifa, 0.01);
    }

    @Test
    @DisplayName("Tipo en mayúsculas funciona igual (VELERO)")
    void amarre_TipoMayusculas_FuncionaIgual() {
        // ? Comprobamos que no distingue mayúsculas/minúsculas
        assertEquals(120.0, club.calcularAmarreMensual("VELERO", 10.0), 0.01);
    }

    // * TEST DE EXCEPCIÓN: tipo desconocido
    @Test
    @DisplayName("Tipo desconocido lanza excepción")
    void amarre_TipoDesconocido_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> club.calcularAmarreMensual("submarino", 10.0)
        );
    }

    // * TEST DE EXCEPCIÓN: eslora <= 0
    @Test
    @DisplayName("Eslora 0 lanza excepción")
    void amarre_Eslora0_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> club.calcularAmarreMensual("lancha", 0.0)
        );
    }

    @Test
    @DisplayName("Eslora negativa lanza excepción")
    void amarre_EsloraNegativa_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> club.calcularAmarreMensual("lancha", -5.0)
        );
    }

    // * TEST PARAMETRIZADO
    @ParameterizedTest
    @CsvSource({
        "lancha, 5.0, 50.0",     // ? 5 × 10 = 50
        "lancha, 8.0, 80.0",     // ? 8 × 10 = 80
        "velero, 5.0, 60.0",     // ? 5 × 10 × 1.20 = 60
        "velero, 10.0, 120.0",   // ? 10 × 10 × 1.20 = 120
        "yate, 5.0, 75.0",       // ? 5 × 10 × 1.50 = 75
        "yate, 20.0, 300.0"      // ? 20 × 10 × 1.50 = 300
    })
    @DisplayName("Calcular amarre con varios tipos y esloras")
    void amarre_VariosCasos_CalculaCorrectamente(String tipo, double eslora, double esperado) {
        assertEquals(esperado, club.calcularAmarreMensual(tipo, eslora), 0.01);
    }


    // ════════════════════════════════════════════════════════════════════
    //                    TESTS DE validarMatricula
    // ════════════════════════════════════════════════════════════════════
    // ? Formato válido: 2 letras + 4 números + 2 letras
    // ? Ejemplo: "AB1234CD"
    // ? null → false
    // ════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Matrícula válida: AB1234CD")
    void matricula_Valida_DevuelveTrue() {
        // * ARRANGE
        String matricula = "AB1234CD";

        // * ACT
        boolean resultado = club.validarMatricula(matricula);

        // * ASSERT
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Matrícula válida con minúsculas: ab1234cd")
    void matricula_ValidaMinusculas_DevuelveTrue() {
        assertTrue(club.validarMatricula("ab1234cd"));
    }

    // * CASOS INVÁLIDOS
    @Test
    @DisplayName("Matrícula null devuelve false")
    void matricula_Null_DevuelveFalse() {
        assertFalse(club.validarMatricula(null));
    }

    @Test
    @DisplayName("Matrícula muy corta devuelve false")
    void matricula_MuyCorta_DevuelveFalse() {
        assertFalse(club.validarMatricula("AB123"));
    }

    @Test
    @DisplayName("Matrícula muy larga devuelve false")
    void matricula_MuyLarga_DevuelveFalse() {
        assertFalse(club.validarMatricula("AB1234CDE"));
    }

    @Test
    @DisplayName("Matrícula sin letras iniciales devuelve false")
    void matricula_SinLetrasIniciales_DevuelveFalse() {
        assertFalse(club.validarMatricula("121234CD"));
    }

    @Test
    @DisplayName("Matrícula sin números centrales devuelve false")
    void matricula_SinNumerosCentrales_DevuelveFalse() {
        assertFalse(club.validarMatricula("ABABCDCD"));
    }

    @Test
    @DisplayName("Matrícula sin letras finales devuelve false")
    void matricula_SinLetrasFinales_DevuelveFalse() {
        assertFalse(club.validarMatricula("AB123456"));
    }

    @Test
    @DisplayName("Matrícula vacía devuelve false")
    void matricula_Vacia_DevuelveFalse() {
        assertFalse(club.validarMatricula(""));
    }

    // * TEST PARAMETRIZADO CON VARIOS CASOS
    @ParameterizedTest
    @CsvSource({
        "'AB1234CD', true",     // ? Válida
        "'ZZ9999ZZ', true",     // ? Válida
        "'aa0000bb', true",     // ? Válida (minúsculas)
        "'AB123CD', false",     // ? Solo 3 números
        "'ABC1234D', false",    // ? 3 letras al inicio
        "'1B1234CD', false",    // ? Empieza con número
        "'AB1234C1', false",    // ? Termina con número
        "'', false",            // ? Vacía
        "'ABCDEFGH', false"     // ? Solo letras
    })
    @DisplayName("Validar matrícula con varios casos")
    void matricula_VariosCasos_ValidaCorrectamente(String matricula, boolean esperado) {
        assertEquals(esperado, club.validarMatricula(matricula));
    }


    // ════════════════════════════════════════════════════════════════════
    // *                    TEST EXTRA: assertAll
    // ════════════════════════════════════════════════════════════════════
    // ? assertAll permite ejecutar múltiples asserts de una vez
    // ? Si falla uno, los demás se ejecutan igual (ves todos los errores)
    // ════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Verificación completa del sistema de cuotas")
    void cuotas_VerificacionCompleta_assertAll() {
        assertAll("Sistema de cuotas",
            () -> assertEquals(30.0, club.calcularCuotaSocio(10, false), 0.01, "Menor"),
            () -> assertEquals(60.0, club.calcularCuotaSocio(30, false), 0.01, "Adulto"),
            () -> assertEquals(40.0, club.calcularCuotaSocio(70, false), 0.01, "Jubilado"),
            () -> assertEquals(51.0, club.calcularCuotaSocio(30, true), 0.01, "Adulto veterano"),
            () -> assertThrows(IllegalArgumentException.class, 
                () -> club.calcularCuotaSocio(-1, false), "Edad negativa")
        );
    }
}


// * =====================================================================
// *                    RESUMEN DE ASSERTS USADOS
// * =====================================================================
// ?
// ? assertEquals(esperado, real)        → Comparar valores
// ? assertEquals(esperado, real, delta) → Comparar decimales con margen
// ? assertTrue(condicion)               → Debe ser true
// ? assertFalse(condicion)              → Debe ser false
// ? assertThrows(Ex.class, lambda)      → Debe lanzar excepción
// ? assertAll("nombre", lambdas...)     → Ejecutar varios asserts juntos
// ?
// * =====================================================================
// *                    ANOTACIONES USADAS
// * =====================================================================
// ?
// ? @Test                  → Marca un método como test
// ? @DisplayName("texto")  → Nombre legible para el test
// ? @BeforeEach            → Se ejecuta antes de cada test
// ? @ParameterizedTest     → Test que se ejecuta con varios valores
// ? @ValueSource           → Lista de valores para test parametrizado
// ? @CsvSource             → Tabla de valores para test parametrizado
// ?
// * =====================================================================
