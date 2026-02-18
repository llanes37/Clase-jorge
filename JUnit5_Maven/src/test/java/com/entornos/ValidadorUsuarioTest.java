package com.entornos;

// ? =====================================================================
// ? CLASE DE TEST: ValidadorUsuarioTest
// ? =====================================================================
// ? Esta clase contiene los tests unitarios para ValidadorUsuario.
// ? Aquí practicamos validaciones de cadenas (emails, passwords) que
// ? son escenarios muy comunes en aplicaciones reales.
// ? =====================================================================

// ! RECUERDA: Esta clase va en src/TEST/java/com/entornos/
// ! El paquete DEBE ser com.entornos (igual que ValidadorUsuario.java)

// * =====================================================================
// * IMPORTS — Todos de org.junit.jupiter (JUnit 5)
// * =====================================================================
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorUsuarioTest {

    // * Atributo de la clase que vamos a testear
    private ValidadorUsuario validador;

    // ================================================================
    // * CICLO DE VIDA
    // ================================================================

    @BeforeAll
    static void inicioGlobal() {
        // ? Se ejecuta UNA VEZ antes de todos los tests
        System.out.println("========================================");
        System.out.println("  INICIO: Tests de ValidadorUsuario");
        System.out.println("========================================");
    }

    @BeforeEach
    void setUp() {
        // * Creamos una nueva instancia antes de cada test
        // ? Así cada test es INDEPENDIENTE — no comparte estado
        // ! BUENA PRÁCTICA: Nunca reutilices objetos entre tests
        validador = new ValidadorUsuario();
        System.out.println("→ Preparando test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("← Test finalizado.\n");
    }

    @AfterAll
    static void finGlobal() {
        System.out.println("========================================");
        System.out.println("  FIN: Tests de ValidadorUsuario");
        System.out.println("========================================");
    }

    // ================================================================
    //  SECCIÓN 1: TESTS DE validarEmail — Casos válidos
    // ================================================================

    @Test
    @DisplayName("Email válido básico: test@email.com")
    @Tag("Rapido")
    void validarEmail_FormatoBasicoCorrecto_DevuelveTrue() {
        // * ARRANGE
        String email = "test@email.com";

        // * ACT
        boolean resultado = validador.validarEmail(email);

        // * ASSERT
        assertTrue(resultado, "test@email.com debería ser un email válido");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "usuario@dominio.com",
        "nombre.apellido@empresa.es",
        "info@sub.dominio.org",
        "a@b.co",
        "test123@email.net"
    })
    @DisplayName("Emails válidos con distintos formatos")
    void validarEmail_DistintosFormatosValidos_DevuelveTrue(String email) {
        // * ACT & ASSERT
        assertTrue(validador.validarEmail(email),
            "\"" + email + "\" debería ser válido");
    }

    // ================================================================
    //  SECCIÓN 2: TESTS DE validarEmail — Casos inválidos
    // ================================================================

    @Test
    @DisplayName("Email null devuelve false")
    void validarEmail_Null_DevuelveFalse() {
        // * ASSERT
        // ? null es un caso que SIEMPRE hay que probar en validaciones de String
        // ! Error típico en producción: no comprobar null → NullPointerException
        assertFalse(validador.validarEmail(null));
    }

    @Test
    @DisplayName("Email vacío devuelve false")
    void validarEmail_Vacio_DevuelveFalse() {
        assertFalse(validador.validarEmail(""));
    }

    @Test
    @DisplayName("Email solo espacios devuelve false")
    void validarEmail_SoloEspacios_DevuelveFalse() {
        assertFalse(validador.validarEmail("   "));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "sinArroba.com",           // ? Sin @
        "@sinParteLocal.com",      // ? Sin texto antes del @
        "doble@@arroba.com",       // ? Dos @
        "sin.punto@dominio",       // ? Sin punto en el dominio
        "test@.com",               // ? Dominio empieza con punto
        "test@dominio."            // ? Dominio termina con punto
    })
    @DisplayName("Emails inválidos con distintos errores")
    void validarEmail_DistintosFormatosInvalidos_DevuelveFalse(String email) {
        // * ACT & ASSERT
        assertFalse(validador.validarEmail(email),
            "\"" + email + "\" NO debería ser válido");
    }

    // ================================================================
    //  SECCIÓN 3: TESTS DE validarEmail con assertAll
    // ================================================================

    @Test
    @DisplayName("Verificar múltiples validaciones de email de una vez")
    void validarEmail_MultiplesCasos_TodosCorrectos() {
        // ? assertAll nos permite verificar VARIOS casos de una vez
        // ? Si falla alguno, vemos TODOS los fallos (no solo el primero)
        // * Esto es especialmente útil cuando quieres una visión completa

        assertAll("Validación completa de emails",
            // * Casos válidos
            () -> assertTrue(validador.validarEmail("ok@email.com"),
                "ok@email.com debería ser válido"),
            () -> assertTrue(validador.validarEmail("user@domain.es"),
                "user@domain.es debería ser válido"),

            // * Casos inválidos
            () -> assertFalse(validador.validarEmail(""),
                "Email vacío debería ser inválido"),
            () -> assertFalse(validador.validarEmail(null),
                "Email null debería ser inválido"),
            () -> assertFalse(validador.validarEmail("sinArroba"),
                "Email sin @ debería ser inválido"),
            () -> assertFalse(validador.validarEmail("test@.com"),
                "Email con dominio que empieza por punto debería ser inválido")
        );
    }

    // ================================================================
    //  SECCIÓN 4: TESTS DE validarPassword
    // ================================================================

    @Test
    @DisplayName("Password válida: Abcdef1!")
    void validarPassword_CumpleTodosRequisitos_DevuelveTrue() {
        // * ARRANGE
        // ? Una contraseña válida necesita: mayúscula, minúscula, dígito, especial, ≥8 chars
        String password = "Abcdef1!";

        // * ACT
        boolean resultado = validador.validarPassword(password);

        // * ASSERT
        assertTrue(resultado, "Abcdef1! cumple todos los requisitos");
    }

    @ParameterizedTest
    @CsvSource({
        // ? Formato: 'password', resultado esperado
        // * Las cadenas en @CsvSource van entre comillas simples

        // ! Caso válido
        "'Abcdef1!', true",            // ? Cumple todo: mayúscula + minúscula + dígito + especial + ≥8
        "'MiClave123#', true",         // ? Otra contraseña válida
        "'P@ssw0rd!!', true",          // ? Otra más

        // ! Casos inválidos
        "'abc', false",                // ? Muy corta (< 8 caracteres)
        "'abcdefgh', false",           // ? Solo minúsculas, sin mayúsculas/dígitos/especiales
        "'ABCDEFGH', false",           // ? Solo mayúsculas
        "'12345678', false",           // ? Solo dígitos
        "'Abcdefgh', false",           // ? Falta dígito y especial
        "'Abcdefg1', false",           // ? Falta carácter especial
        "'abcdef1!', false"            // ? Falta mayúscula
    })
    @DisplayName("Validar password con distintos casos")
    void validarPassword_DistintosCasos_ResultadoCorrecto(String password, boolean esperado) {
        // * ACT
        boolean resultado = validador.validarPassword(password);

        // * ASSERT
        assertEquals(esperado, resultado,
            "validarPassword(\"" + password + "\") debería devolver " + esperado);
    }

    @Test
    @DisplayName("Password null devuelve false")
    void validarPassword_Null_DevuelveFalse() {
        assertFalse(validador.validarPassword(null));
    }

    @Test
    @DisplayName("Password vacía devuelve false")
    void validarPassword_Vacia_DevuelveFalse() {
        assertFalse(validador.validarPassword(""));
    }

    // ================================================================
    //  SECCIÓN 5: TESTS DE registrarUsuario
    // ================================================================

    @Test
    @DisplayName("Registro exitoso con email y password válidos")
    void registrarUsuario_DatosValidos_DevuelveExito() {
        // * ARRANGE
        String email = "jorge@email.com";
        String password = "MiClave123!";

        // * ACT
        String resultado = validador.registrarUsuario(email, password);

        // * ASSERT
        assertEquals("Usuario registrado correctamente", resultado);
    }

    @Test
    @DisplayName("Registro con email inválido devuelve error")
    void registrarUsuario_EmailInvalido_DevuelveErrorEmail() {
        // * ARRANGE
        String emailMalo = "sinArroba.com";
        String passwordBuena = "MiClave123!";

        // * ACT
        String resultado = validador.registrarUsuario(emailMalo, passwordBuena);

        // * ASSERT
        assertEquals("Email no válido", resultado);
    }

    @Test
    @DisplayName("Registro con password inválida devuelve error")
    void registrarUsuario_PasswordInvalida_DevuelveErrorPassword() {
        // * ARRANGE
        String emailBueno = "jorge@email.com";
        String passwordMala = "1234"; // ? Muy corta

        // * ACT
        String resultado = validador.registrarUsuario(emailBueno, passwordMala);

        // * ASSERT
        assertEquals("Contraseña no válida", resultado);
    }

    // ================================================================
    //  SECCIÓN 6: TESTS DE EXCEPCIÓN con assertThrows
    // ================================================================

    @Test
    @DisplayName("Registrar con email null lanza IllegalArgumentException")
    void registrarUsuario_EmailNull_LanzaExcepcion() {
        // * ACT & ASSERT
        // ? assertThrows verifica que se lanza la excepción correcta
        // ! El método registrarUsuario lanza IllegalArgumentException si email es null
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> validador.registrarUsuario(null, "MiClave123!")
        );

        // * Verificar el mensaje de la excepción
        assertEquals("El email y la contraseña no pueden ser null", ex.getMessage());
    }

    @Test
    @DisplayName("Registrar con password null lanza IllegalArgumentException")
    void registrarUsuario_PasswordNull_LanzaExcepcion() {
        // * ACT & ASSERT
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> validador.registrarUsuario("jorge@email.com", null)
        );

        // * Verificar el mensaje
        assertNotNull(ex.getMessage(), "La excepción debería tener un mensaje");
        // ? assertNotNull comprueba que el objeto NO es null
    }

    @Test
    @DisplayName("Registrar con ambos null lanza IllegalArgumentException")
    void registrarUsuario_AmbosNull_LanzaExcepcion() {
        // * ACT & ASSERT
        assertThrows(
            IllegalArgumentException.class,
            () -> validador.registrarUsuario(null, null)
        );
        // ? Aquí no capturamos el resultado porque no necesitamos verificar el mensaje
    }

    // ================================================================
    //  SECCIÓN 7: TESTS DE normalizarEmail
    // ================================================================

    @Test
    @DisplayName("Normalizar email: quita espacios y pasa a minúsculas")
    void normalizarEmail_ConEspaciosYMayusculas_DevuelveNormalizado() {
        // * ARRANGE
        String emailSucio = "  Jorge@EMAIL.COM  ";

        // * ACT
        String resultado = validador.normalizarEmail(emailSucio);

        // * ASSERT
        assertEquals("jorge@email.com", resultado);
    }

    @Test
    @DisplayName("Normalizar email null devuelve cadena vacía")
    void normalizarEmail_Null_DevuelveCadenaVacia() {
        // * ASSERT
        assertEquals("", validador.normalizarEmail(null));
    }

    @Test
    @DisplayName("Normalizar email ya correcto lo deja igual")
    void normalizarEmail_YaNormalizado_DevuelveIgual() {
        // * ASSERT
        assertEquals("test@email.com", validador.normalizarEmail("test@email.com"));
    }

    // ================================================================
    //  SECCIÓN 8: TESTS DE calcularFortalezaPassword
    // ================================================================

    @ParameterizedTest
    @CsvSource({
        // ? Formato: 'password', 'fortaleza esperada'
        "'', 'Muy débil'",                  // ? Vacío → 0 puntos
        "'abc', 'Muy débil'",               // ? Corta, solo minúsculas → 0 puntos
        "'abcdefgh', 'Débil'",              // ? ≥8 + minúsculas → 2 puntos
        "'Abcdefgh', 'Media'",              // ? ≥8 + minúsculas + mayúsculas → 3 puntos
        "'Abcdefg1', 'Fuerte'",             // ? ≥8 + min + may + dígito → 4 puntos
        "'Abcdefghij1!', 'Muy fuerte'"      // ? ≥12 + min + may + dígito + especial → 5 puntos
    })
    @DisplayName("Fortaleza de contraseña con distintos casos")
    void calcularFortaleza_DistintosCasos_ResultadoCorrecto(
            String password, String esperado) {
        // * ACT
        String resultado = validador.calcularFortalezaPassword(password);

        // * ASSERT
        assertEquals(esperado, resultado,
            "La fortaleza de \"" + password + "\" debería ser \"" + esperado + "\"");
    }

    @Test
    @DisplayName("Fortaleza de password null es 'Muy débil'")
    void calcularFortaleza_Null_DevuelveMuyDebil() {
        assertEquals("Muy débil", validador.calcularFortalezaPassword(null));
    }

    // ================================================================
    //  SECCIÓN 9: TEST INTEGRANDO assertAll CON registrarUsuario
    // ================================================================

    @Test
    @DisplayName("Verificación completa del flujo de registro")
    void registrarUsuario_FlujoCompleto_TodoCoherente() {
        // ? Este test verifica que todo el flujo funciona de forma coherente
        // ? Usamos assertAll para comprobar múltiples escenarios

        assertAll("Flujo completo de registro",
            // * Caso exitoso
            () -> assertEquals("Usuario registrado correctamente",
                validador.registrarUsuario("user@test.com", "Clave123!"),
                "Registro válido debería ser exitoso"),

            // * Email inválido
            () -> assertEquals("Email no válido",
                validador.registrarUsuario("malEmail", "Clave123!"),
                "Email sin @ debería fallar"),

            // * Password inválida
            () -> assertEquals("Contraseña no válida",
                validador.registrarUsuario("user@test.com", "123"),
                "Password corta debería fallar"),

            // * Excepciones
            () -> assertThrows(IllegalArgumentException.class,
                () -> validador.registrarUsuario(null, "pass"),
                "Email null debería lanzar excepción")
        );
    }

    // ================================================================
    // TODO EJERCICIOS PARA EL ALUMNO — Añade más tests aquí
    // ================================================================

    // TODO Ejercicio 3: assertFalse para validarEmail("sinArroba.com")
    // TODO Ejercicio 6: Test parametrizado de validarPassword con @CsvSource
    // TODO Ejercicio 7: assertThrows para registrarUsuario(null, "pass123")
    // TODO Ejercicio 8: assertAll para múltiples validaciones de email
    // TODO Ejercicio 10: Implementar validarNombreUsuario y sus tests
}
