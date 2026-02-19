package tests;

// ════════════════════════════════════════════════════════════════════════
// ?                  EXAMEN PRÁCTICO: TESTING CAJA NEGRA
// ?                  ════════════════════════════════════
// ?                       CLUB NÁUTICO "MAR ABIERTO"
// ════════════════════════════════════════════════════════════════════════
// ?
// ? ¿QUÉ ES CAJA NEGRA?
// ? Probamos el método SIN ver su código interno.
// ? Solo usamos la especificación (descripción de qué debe hacer).
// ?
// ?     ENTRADA ──→ [ ??? código oculto ??? ] ──→ SALIDA esperada
// ?
// ? TÉCNICAS USADAS EN ESTE EJERCICIO:
// ?   1. Particiones de Equivalencia → agrupar entradas con igual comportamiento
// ?   2. Análisis de Valores Límite  → probar los extremos de cada partición
// ?
// ════════════════════════════════════════════════════════════════════════
// ?
// ? ENUNCIADO COMPLETO:
// ? ───────────────────
// ? El Club Náutico "Mar Abierto" necesita software de gestión.
// ? Te proporcionan la clase ClubNautico con 4 métodos.
// ? Tu tarea: diseñar y escribir los tests de caja negra.
// ?
// ? ┌─────────────────────────────────────────────────────────────────┐
// ? │ MÉTODO 1: calcularCuotaSocio(int edad, boolean esVeterano)     │
// ? │   · edad < 0           → IllegalArgumentException             │
// ? │   · edad < 18          → cuota = 30.0€                        │
// ? │   · 18 ≤ edad ≤ 64     → cuota = 60.0€                        │
// ? │   · edad ≥ 65          → cuota = 40.0€                        │
// ? │   · si esVeterano=true → descuento del 15% sobre la cuota     │
// ? └─────────────────────────────────────────────────────────────────┘
// ? ┌─────────────────────────────────────────────────────────────────┐
// ? │ MÉTODO 2: clasificarEmbarcacion(double eslora)                 │
// ? │   · eslora < 0         → IllegalArgumentException             │
// ? │   · eslora < 6.0       → "Pequeña"                            │
// ? │   · 6.0 ≤ eslora < 12  → "Mediana"                            │
// ? │   · eslora ≥ 12.0      → "Grande"                             │
// ? └─────────────────────────────────────────────────────────────────┘
// ? ┌─────────────────────────────────────────────────────────────────┐
// ? │ MÉTODO 3: calcularAmarreMensual(String tipo, double eslora)    │
// ? │   · eslora ≤ 0         → IllegalArgumentException             │
// ? │   · tipo desconocido   → IllegalArgumentException             │
// ? │   · tarifa base        = eslora × 10€                         │
// ? │   · "lancha"           → tarifa base (sin recargo)            │
// ? │   · "velero"           → tarifa base + 20%                    │
// ? │   · "yate"             → tarifa base + 50%                    │
// ? └─────────────────────────────────────────────────────────────────┘
// ? ┌─────────────────────────────────────────────────────────────────┐
// ? │ MÉTODO 4: validarMatricula(String matricula)                   │
// ? │   · null               → false                                │
// ? │   · longitud ≠ 8       → false                                │
// ? │   · Formato válido: 2 letras + 4 dígitos + 2 letras           │
// ? │   · Ejemplo válido: "AB1234CD"                                │
// ? └─────────────────────────────────────────────────────────────────┘
// ?
// ? PUNTUACIÓN:
// ?   · calcularCuotaSocio    : 2.5 puntos
// ?   · clasificarEmbarcacion : 2.5 puntos
// ?   · calcularAmarreMensual : 2.5 puntos
// ?   · validarMatricula      : 2.5 puntos
// ?                            ──────────
// ?                      TOTAL: 10 puntos
// ?
// ════════════════════════════════════════════════════════════════════════

// * ─────────────────────────────────────────────────────────────────────
// * IMPORTS — Siempre de org.junit.jupiter (JUnit 5)
// ! NUNCA uses solo "org.junit" → eso es JUnit 4 y NO funcionará
// * ─────────────────────────────────────────────────────────────────────
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;


// ════════════════════════════════════════════════════════════════════════
// * CLASE ClubNautico — La clase "de producción" que vamos a testear
// ! En un examen real te la dan hecha. Tú solo escribes los tests.
// ════════════════════════════════════════════════════════════════════════
class ClubNautico {

    public double calcularCuotaSocio(int edad, boolean esVeterano) {
        if (edad < 0) throw new IllegalArgumentException("La edad no puede ser negativa");
        double cuota = (edad < 18) ? 30.0 : (edad < 65) ? 60.0 : 40.0;
        return esVeterano ? cuota * 0.85 : cuota;
    }

    public String clasificarEmbarcacion(double eslora) {
        if (eslora < 0) throw new IllegalArgumentException("La eslora no puede ser negativa");
        if (eslora < 6)  return "Pequeña";
        if (eslora < 12) return "Mediana";
        return "Grande";
    }

    public double calcularAmarreMensual(String tipo, double eslora) {
        if (eslora <= 0) throw new IllegalArgumentException("La eslora debe ser positiva");
        double base = eslora * 10;
        switch (tipo.toLowerCase()) {
            case "lancha": return base;
            case "velero": return base * 1.20;
            case "yate":   return base * 1.50;
            default: throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        }
    }

    public boolean validarMatricula(String matricula) {
        if (matricula == null || matricula.length() != 8) return false;
        if (!Character.isLetter(matricula.charAt(0)) || !Character.isLetter(matricula.charAt(1))) return false;
        for (int i = 2; i < 6; i++) if (!Character.isDigit(matricula.charAt(i))) return false;
        return Character.isLetter(matricula.charAt(6)) && Character.isLetter(matricula.charAt(7));
    }
}


// ════════════════════════════════════════════════════════════════════════
// *               SOLUCIÓN — TESTS DE CAJA NEGRA
// ════════════════════════════════════════════════════════════════════════
class ClubNauticoTest {

    // * La instancia se crea nueva antes de cada test (ver @BeforeEach)
    private ClubNautico club;

    @BeforeAll
    static void inicio() {
        // ! @BeforeAll DEBE ser static — error muy típico de examen
        System.out.println("══════════════════════════════");
        System.out.println("  INICIO: Tests Club Náutico  ");
        System.out.println("══════════════════════════════");
    }

    @BeforeEach
    void setUp() {
        // ? Creamos objeto nuevo antes de CADA test
        // ? Así ningún test "contamina" el estado del siguiente
        club = new ClubNautico();
    }

    @AfterAll
    static void fin() {
        // ! @AfterAll DEBE ser static
        System.out.println("══════════════════════════════");
        System.out.println("  FIN: Tests completados      ");
        System.out.println("══════════════════════════════");
    }


    // ════════════════════════════════════════════════════════════════════
    //       MÉTODO 1 — calcularCuotaSocio(int edad, boolean esVeterano)
    // ════════════════════════════════════════════════════════════════════
    // ?
    // ? ANÁLISIS DE PARTICIONES DE EQUIVALENCIA:
    // ?
    // ?  Nº  │ Partición           │ Tipo       │ Valor rep. │ Resultado
    // ?  ────┼─────────────────────┼────────────┼────────────┼──────────
    // ?  P1  │ edad < 0            │ ❌ Inválida │    -1      │ Excepción
    // ?  P2  │ 0 ≤ edad < 18       │ ✅ Válida   │    10      │  30.0€
    // ?  P3  │ 18 ≤ edad ≤ 64      │ ✅ Válida   │    40      │  60.0€
    // ?  P4  │ edad ≥ 65           │ ✅ Válida   │    70      │  40.0€
    // ?  P5  │ esVeterano = true   │ ✅ Válida   │  40+true   │  51.0€ (-15%)
    // ?
    // ? ANÁLISIS DE VALORES LÍMITE (AVL):
    // ?
    // ?  Frontera   │ Límite−1 │ Límite exacto  │ Límite+1
    // ?  ───────────┼──────────┼────────────────┼──────────
    // ?  edad = 0   │  -1→exc. │  0 →  30€      │  1 → 30€
    // ?  edad = 18  │ 17→ 30€  │ 18 →  60€   ★  │ 19 → 60€
    // ?  edad = 65  │ 64→ 60€  │ 65 →  40€   ★  │ 66 → 40€
    // ?
    // ?  ★ = caso más probable de tener bug (< vs <=)
    // ?
    // ════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("[P1-INV] Edad negativa → IllegalArgumentException")
    void cuota_EdadNegativa_LanzaExcepcion() {
        // * ARRANGE
        int edad = -1; // ? Pertenece a P1 (inválida)

        // * ACT & ASSERT
        // ? lambda () -> envuelve el código que debe lanzar la excepción
        assertThrows(
            IllegalArgumentException.class,
            () -> club.calcularCuotaSocio(edad, false)
        );
    }

    @Test
    @DisplayName("[P2] Menor de edad (10 años) → 30€")
    void cuota_MenorDeEdad_Devuelve30() {
        // * ARRANGE
        int edad = 10;         // ? Representativo de P2
        boolean esVeterano = false;

        // * ACT
        double resultado = club.calcularCuotaSocio(edad, esVeterano);

        // * ASSERT
        // ! assertEquals(ESPERADO, REAL) — el esperado va PRIMERO siempre
        assertEquals(30.0, resultado, 0.01);
        // ? 0.01 = delta (margen de error aceptado para decimales)
    }

    @Test
    @DisplayName("[P3] Adulto (40 años) → 60€")
    void cuota_Adulto_Devuelve60() {
        // * ARRANGE
        int edad = 40;         // ? Representativo de P3
        boolean esVeterano = false;

        // * ACT
        double resultado = club.calcularCuotaSocio(edad, esVeterano);

        // * ASSERT
        assertEquals(60.0, resultado, 0.01);
    }

    @Test
    @DisplayName("[P4] Jubilado (70 años) → 40€")
    void cuota_Jubilado_Devuelve40() {
        // * ARRANGE
        int edad = 70;         // ? Representativo de P4
        boolean esVeterano = false;

        // * ACT
        double resultado = club.calcularCuotaSocio(edad, esVeterano);

        // * ASSERT
        assertEquals(40.0, resultado, 0.01);
    }

    @Test
    @DisplayName("[P5] Adulto veterano → 51€  (60€ × 0.85)")
    void cuota_AdultoVeterano_DevuelveConDescuento() {
        // * ARRANGE
        int edad = 40;
        boolean esVeterano = true;  // ? activa el descuento del 15%

        // * ACT
        double resultado = club.calcularCuotaSocio(edad, esVeterano);

        // * ASSERT
        // ? 60.0 × 0.85 = 51.0
        assertEquals(51.0, resultado, 0.01);
    }

    @Test
    @DisplayName("[P5] Jubilado veterano → 34€  (40€ × 0.85)")
    void cuota_JubiladoVeterano_DevuelveConDescuento() {
        // ? 40.0 × 0.85 = 34.0
        assertEquals(34.0, club.calcularCuotaSocio(70, true), 0.01);
    }

    @Test
    @DisplayName("[P5] Menor veterano → 25.5€  (30€ × 0.85)")
    void cuota_MenorVeterano_DevuelveConDescuento() {
        // ? 30.0 × 0.85 = 25.5
        assertEquals(25.5, club.calcularCuotaSocio(15, true), 0.01);
    }

    // * ── VALORES LÍMITE: frontera edad = 18 ─────────────────────────

    @Test
    @DisplayName("[AVL] 17 años → 30€  (límite−1: aún menor)")
    void cuota_AVL_17_Devuelve30() {
        // ! Si el código tiene <= en vez de <, este test lo detecta
        assertEquals(30.0, club.calcularCuotaSocio(17, false), 0.01);
    }

    @Test
    @DisplayName("[AVL] 18 años → 60€  (límite exacto ★ más probable de fallar)")
    void cuota_AVL_18_Devuelve60() {
        // ! El límite exacto es el caso MÁS probable de tener bug
        assertEquals(60.0, club.calcularCuotaSocio(18, false), 0.01);
    }

    @Test
    @DisplayName("[AVL] 64 años → 60€  (límite−1: último adulto)")
    void cuota_AVL_64_Devuelve60() {
        assertEquals(60.0, club.calcularCuotaSocio(64, false), 0.01);
    }

    @Test
    @DisplayName("[AVL] 65 años → 40€  (límite exacto ★ más probable de fallar)")
    void cuota_AVL_65_Devuelve40() {
        // ! El límite exacto es el caso MÁS probable de tener bug
        assertEquals(40.0, club.calcularCuotaSocio(65, false), 0.01);
    }

    @Test
    @DisplayName("[AVL] 66 años → 40€  (límite+1: claramente jubilado)")
    void cuota_AVL_66_Devuelve40() {
        assertEquals(40.0, club.calcularCuotaSocio(66, false), 0.01);
    }

    // * ── TEST PARAMETRIZADO: varios menores de una vez ──────────────
    @ParameterizedTest
    @ValueSource(ints = {0, 5, 10, 15, 17})  // ? Todos pertenecen a P2
    @DisplayName("[P2-PARAM] Varios menores de edad → 30€ cada uno")
    void cuota_VariosMenores_Devuelven30(int edad) {
        // ? JUnit ejecuta este test 5 veces, una por cada valor
        assertEquals(30.0, club.calcularCuotaSocio(edad, false), 0.01);
    }


    // ════════════════════════════════════════════════════════════════════
    //              MÉTODO 2 — clasificarEmbarcacion(double eslora)
    // ════════════════════════════════════════════════════════════════════
    // ?
    // ? ANÁLISIS DE PARTICIONES DE EQUIVALENCIA:
    // ?
    // ?  Nº  │ Partición           │ Tipo       │ Valor rep. │ Resultado
    // ?  ────┼─────────────────────┼────────────┼────────────┼──────────
    // ?  P1  │ eslora < 0          │ ❌ Inválida │   -1.0     │ Excepción
    // ?  P2  │ 0 ≤ eslora < 6      │ ✅ Válida   │    3.0     │ "Pequeña"
    // ?  P3  │ 6 ≤ eslora < 12     │ ✅ Válida   │    9.0     │ "Mediana"
    // ?  P4  │ eslora ≥ 12         │ ✅ Válida   │   15.0     │ "Grande"
    // ?
    // ? ANÁLISIS DE VALORES LÍMITE (AVL):
    // ?
    // ?  Frontera  │ Límite−0.01 │ Límite exacto   │ Límite+0.01
    // ?  ──────────┼─────────────┼─────────────────┼─────────────
    // ?  6.0 m     │ 5.99→Peq.   │  6.0 → "Mediana" ★ │ 6.01→Med.
    // ?  12.0 m    │ 11.99→Med.  │ 12.0 → "Grande"  ★ │ 12.01→Gran.
    // ?
    // ?  ★ = caso más probable de tener bug (< vs <=)
    // ?
    // ════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("[P1-INV] Eslora negativa → IllegalArgumentException")
    void clasificar_EsloraNegativa_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> club.clasificarEmbarcacion(-1.0)
        );
    }

    @Test
    @DisplayName("[P2] Eslora 3m → Pequeña")
    void clasificar_Eslora3_DevuelvePequena() {
        // * ARRANGE
        double eslora = 3.0;  // ? Representativo de P2

        // * ACT
        String resultado = club.clasificarEmbarcacion(eslora);

        // * ASSERT
        assertEquals("Pequeña", resultado);
    }

    @Test
    @DisplayName("[P3] Eslora 9m → Mediana")
    void clasificar_Eslora9_DevuelveMediana() {
        assertEquals("Mediana", club.clasificarEmbarcacion(9.0));
    }

    @Test
    @DisplayName("[P4] Eslora 15m → Grande")
    void clasificar_Eslora15_DevuelveGrande() {
        assertEquals("Grande", club.clasificarEmbarcacion(15.0));
    }

    // * ── VALORES LÍMITE: frontera 6.0 metros ────────────────────────

    @Test
    @DisplayName("[AVL] 5.99m → Pequeña  (límite−0.01: justo antes de 6m)")
    void clasificar_AVL_599_DevuelvePequena() {
        // ! Sigue siendo Pequeña, la frontera aún no se ha cruzado
        assertEquals("Pequeña", club.clasificarEmbarcacion(5.99));
    }

    @Test
    @DisplayName("[AVL] 6.0m → Mediana  (límite exacto ★ más probable de fallar)")
    void clasificar_AVL_6_DevuelveMediana() {
        // ! Si el código pone > en vez de >= aquí estaría el bug
        assertEquals("Mediana", club.clasificarEmbarcacion(6.0));
    }

    @Test
    @DisplayName("[AVL] 6.01m → Mediana  (límite+0.01: claramente mediana)")
    void clasificar_AVL_601_DevuelveMediana() {
        assertEquals("Mediana", club.clasificarEmbarcacion(6.01));
    }

    // * ── VALORES LÍMITE: frontera 12.0 metros ───────────────────────

    @Test
    @DisplayName("[AVL] 11.99m → Mediana  (límite−0.01: última mediana)")
    void clasificar_AVL_1199_DevuelveMediana() {
        assertEquals("Mediana", club.clasificarEmbarcacion(11.99));
    }

    @Test
    @DisplayName("[AVL] 12.0m → Grande  (límite exacto ★ más probable de fallar)")
    void clasificar_AVL_12_DevuelveGrande() {
        // ! Si el código pone > en vez de >= aquí estaría el bug
        assertEquals("Grande", club.clasificarEmbarcacion(12.0));
    }

    @Test
    @DisplayName("[AVL] 12.01m → Grande  (límite+0.01: claramente grande)")
    void clasificar_AVL_1201_DevuelveGrande() {
        assertEquals("Grande", club.clasificarEmbarcacion(12.01));
    }

    // * ── TEST PARAMETRIZADO: particiones + límites de una vez ───────
    @ParameterizedTest
    @CsvSource({
        // ? representativos de particiones
        "3.0,   Pequeña",
        "9.0,   Mediana",
        "15.0,  Grande",
        // ? valores límite frontera 6m
        "5.99,  Pequeña",
        "6.0,   Mediana",
        "6.01,  Mediana",
        // ? valores límite frontera 12m
        "11.99, Mediana",
        "12.0,  Grande",
        "12.01, Grande"
    })
    @DisplayName("[PARAM] Clasificar con distintas esloras (particiones + límites)")
    void clasificar_VariasEsloras_ClasificaCorrectamente(double eslora, String esperado) {
        assertEquals(esperado, club.clasificarEmbarcacion(eslora));
    }


    // ════════════════════════════════════════════════════════════════════
    //       MÉTODO 3 — calcularAmarreMensual(String tipo, double eslora)
    // ════════════════════════════════════════════════════════════════════
    // ?
    // ? ANÁLISIS DE PARTICIONES DE EQUIVALENCIA:
    // ?
    // ?  Nº  │ Partición             │ Tipo       │ Resultado
    // ?  ────┼───────────────────────┼────────────┼──────────────────────
    // ?  P1  │ eslora ≤ 0            │ ❌ Inválida │ Excepción
    // ?  P2  │ tipo desconocido      │ ❌ Inválida │ Excepción
    // ?  P3  │ tipo = "lancha"       │ ✅ Válida   │ eslora × 10
    // ?  P4  │ tipo = "velero"       │ ✅ Válida   │ eslora × 10 × 1.20
    // ?  P5  │ tipo = "yate"         │ ✅ Válida   │ eslora × 10 × 1.50
    // ?
    // ? CÁLCULOS DE REFERENCIA:
    // ?   lancha, 10m → 10 × 10            = 100.0€
    // ?   velero, 10m → 10 × 10 × 1.20    = 120.0€
    // ?   yate,   10m → 10 × 10 × 1.50    = 150.0€
    // ?   lancha,  5m →  5 × 10            =  50.0€
    // ?   velero,  5m →  5 × 10 × 1.20    =  60.0€
    // ?   yate,    5m →  5 × 10 × 1.50    =  75.0€
    // ?
    // ════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("[P1-INV] Eslora = 0 → IllegalArgumentException")
    void amarre_Eslora0_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> club.calcularAmarreMensual("lancha", 0.0)
        );
    }

    @Test
    @DisplayName("[P1-INV] Eslora negativa → IllegalArgumentException")
    void amarre_EsloraNegativa_LanzaExcepcion() {
        assertThrows(
            IllegalArgumentException.class,
            () -> club.calcularAmarreMensual("lancha", -5.0)
        );
    }

    @Test
    @DisplayName("[P2-INV] Tipo desconocido → IllegalArgumentException")
    void amarre_TipoDesconocido_LanzaExcepcion() {
        // ? Solo acepta "lancha", "velero" y "yate"
        assertThrows(
            IllegalArgumentException.class,
            () -> club.calcularAmarreMensual("submarino", 10.0)
        );
    }

    @Test
    @DisplayName("[P3] Lancha 10m → 100€  (10 × 10, sin recargo)")
    void amarre_Lancha10m_Devuelve100() {
        // * ARRANGE
        String tipo = "lancha";
        double eslora = 10.0;

        // * ACT
        double tarifa = club.calcularAmarreMensual(tipo, eslora);

        // * ASSERT
        // ? 10 × 10 = 100€ (sin recargo)
        assertEquals(100.0, tarifa, 0.01);
    }

    @Test
    @DisplayName("[P4] Velero 10m → 120€  (100€ + 20%)")
    void amarre_Velero10m_Devuelve120() {
        // * ARRANGE
        String tipo = "velero";
        double eslora = 10.0;

        // * ACT
        double tarifa = club.calcularAmarreMensual(tipo, eslora);

        // * ASSERT
        // ? 10 × 10 = 100€, aplicar +20% → 100 × 1.20 = 120€
        assertEquals(120.0, tarifa, 0.01);
    }

    @Test
    @DisplayName("[P5] Yate 10m → 150€  (100€ + 50%)")
    void amarre_Yate10m_Devuelve150() {
        // * ARRANGE
        String tipo = "yate";
        double eslora = 10.0;

        // * ACT
        double tarifa = club.calcularAmarreMensual(tipo, eslora);

        // * ASSERT
        // ? 10 × 10 = 100€, aplicar +50% → 100 × 1.50 = 150€
        assertEquals(150.0, tarifa, 0.01);
    }

    @Test
    @DisplayName("[P4] Tipo en mayúsculas (VELERO) funciona igual → 120€")
    void amarre_TipoMayusculas_FuncionaIgual() {
        // ? El método usa .toLowerCase() internamente → no importa el case
        assertEquals(120.0, club.calcularAmarreMensual("VELERO", 10.0), 0.01);
    }

    // * ── TEST PARAMETRIZADO: 3 tipos con distintas esloras ──────────
    @ParameterizedTest
    @CsvSource({
        // ? Formato: tipo, eslora, tarifa esperada
        "lancha,  5.0,  50.0",   // ?  5 × 10 × 1.00 =  50
        "lancha, 10.0, 100.0",   // ? 10 × 10 × 1.00 = 100
        "velero,  5.0,  60.0",   // ?  5 × 10 × 1.20 =  60
        "velero, 10.0, 120.0",   // ? 10 × 10 × 1.20 = 120
        "yate,    5.0,  75.0",   // ?  5 × 10 × 1.50 =  75
        "yate,   20.0, 300.0"    // ? 20 × 10 × 1.50 = 300
    })
    @DisplayName("[PARAM] Amarre con distintos tipos y esloras")
    void amarre_VariosCasos_CalculaCorrectamente(String tipo, double eslora, double esperado) {
        assertEquals(esperado, club.calcularAmarreMensual(tipo, eslora), 0.01);
    }


    // ════════════════════════════════════════════════════════════════════
    //              MÉTODO 4 — validarMatricula(String matricula)
    // ════════════════════════════════════════════════════════════════════
    // ?
    // ? ANÁLISIS DE PARTICIONES DE EQUIVALENCIA:
    // ?
    // ?  Nº  │ Partición                       │ Tipo       │ Resultado
    // ?  ────┼─────────────────────────────────┼────────────┼──────────
    // ?  P1  │ matricula = null                 │ ❌ Inválida │ false
    // ?  P2  │ longitud ≠ 8                     │ ❌ Inválida │ false
    // ?  P3  │ posiciones 0-1 no son letras     │ ❌ Inválida │ false
    // ?  P4  │ posiciones 2-5 no son dígitos    │ ❌ Inválida │ false
    // ?  P5  │ posiciones 6-7 no son letras     │ ❌ Inválida │ false
    // ?  P6  │ formato correcto LL####LL        │ ✅ Válida   │ true
    // ?
    // ? ANÁLISIS DE VALORES LÍMITE (AVL) — longitud:
    // ?
    // ?  Longitud │ Resultado
    // ?  ─────────┼──────────
    // ?     7     │ false  (límite−1)
    // ?     8     │ true   (límite exacto, si el formato es correcto)
    // ?     9     │ false  (límite+1)
    // ?
    // ════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("[P6] Matrícula válida: AB1234CD → true")
    void matricula_Valida_DevuelveTrue() {
        // * ARRANGE
        String matricula = "AB1234CD";  // ? 2 letras + 4 dígitos + 2 letras

        // * ACT
        boolean resultado = club.validarMatricula(matricula);

        // * ASSERT
        assertTrue(resultado);
    }

    @Test
    @DisplayName("[P6] Matrícula válida en minúsculas: ab1234cd → true")
    void matricula_ValidaMinusculas_DevuelveTrue() {
        // ? Character.isLetter() acepta mayúsculas y minúsculas
        assertTrue(club.validarMatricula("ab1234cd"));
    }

    @Test
    @DisplayName("[P1-INV] Matrícula null → false")
    void matricula_Null_DevuelveFalse() {
        // ! Siempre testear null cuando el parámetro es un objeto / String
        assertFalse(club.validarMatricula(null));
    }

    @Test
    @DisplayName("[P2-INV / AVL] Longitud 7 (límite−1) → false")
    void matricula_Longitud7_DevuelveFalse() {
        // ! Un carácter menos que el correcto
        assertFalse(club.validarMatricula("AB123CD"));   // 7 chars
    }

    @Test
    @DisplayName("[P2-INV / AVL] Longitud 9 (límite+1) → false")
    void matricula_Longitud9_DevuelveFalse() {
        // ! Un carácter más que el correcto
        assertFalse(club.validarMatricula("AB1234CDE")); // 9 chars
    }

    @Test
    @DisplayName("[P2-INV] Cadena vacía → false")
    void matricula_Vacia_DevuelveFalse() {
        assertFalse(club.validarMatricula(""));
    }

    @Test
    @DisplayName("[P3-INV] Empieza con números: 121234CD → false")
    void matricula_EmpiezaConNumeros_DevuelveFalse() {
        // ? Las posiciones 0 y 1 deben ser letras
        assertFalse(club.validarMatricula("121234CD"));
    }

    @Test
    @DisplayName("[P4-INV] Letras en zona de dígitos: ABABCDCD → false")
    void matricula_LetrasEnZonaDigitos_DevuelveFalse() {
        // ? Las posiciones 2, 3, 4, 5 deben ser dígitos
        assertFalse(club.validarMatricula("ABABCDCD"));
    }

    @Test
    @DisplayName("[P5-INV] Termina con números: AB123456 → false")
    void matricula_TerminaConNumeros_DevuelveFalse() {
        // ? Las posiciones 6 y 7 deben ser letras
        assertFalse(club.validarMatricula("AB123456"));
    }

    // * ── TEST PARAMETRIZADO: todos los casos de una vez ─────────────
    @ParameterizedTest
    @CsvSource({
        "'AB1234CD', true",     // ? Válida — representativo P6
        "'ZZ9999ZZ', true",     // ? Válida — otro ejemplo
        "'aa0000bb', true",     // ? Válida — minúsculas
        "'AB123CD',  false",    // ? 7 chars — AVL límite−1 (P2)
        "'AB1234CDE',false",    // ? 9 chars — AVL límite+1 (P2)
        "'121234CD', false",    // ? Empieza con número      (P3)
        "'ABABCDCD', false",    // ? Letras en zona dígitos  (P4)
        "'AB123456', false",    // ? Termina con números     (P5)
        "'',         false",    // ? Vacía                   (P2)
        "'ABCDEFGH', false"     // ? 8 chars pero solo letras (P4)
    })
    @DisplayName("[PARAM] Validar matrícula — todos los casos")
    void matricula_VariosCasos_ValidaCorrectamente(String matricula, boolean esperado) {
        assertEquals(esperado, club.validarMatricula(matricula));
    }


    // ════════════════════════════════════════════════════════════════════
    // *          TEST GLOBAL con assertAll — visión conjunta
    // ════════════════════════════════════════════════════════════════════
    // ? assertAll ejecuta TODOS sus asserts aunque alguno falle
    // ? Así ves de una vez qué métodos fallan y cuáles no
    // ════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("[GLOBAL] Verificación de todos los métodos con assertAll")
    void global_VerificacionCompleta_assertAll() {
        assertAll("Club Náutico — verificación global",

            // * Método 1
            () -> assertEquals(30.0, club.calcularCuotaSocio(10, false), 0.01, "Menor → 30€"),
            () -> assertEquals(60.0, club.calcularCuotaSocio(30, false), 0.01, "Adulto → 60€"),
            () -> assertEquals(40.0, club.calcularCuotaSocio(70, false), 0.01, "Jubilado → 40€"),
            () -> assertEquals(51.0, club.calcularCuotaSocio(30, true),  0.01, "Adulto veterano → 51€"),
            () -> assertThrows(IllegalArgumentException.class,
                    () -> club.calcularCuotaSocio(-1, false), "Edad neg. → excepción"),

            // * Método 2
            () -> assertEquals("Pequeña", club.clasificarEmbarcacion(3.0),  "3m → Pequeña"),
            () -> assertEquals("Mediana", club.clasificarEmbarcacion(6.0),  "6m exacto → Mediana ★"),
            () -> assertEquals("Grande",  club.clasificarEmbarcacion(12.0), "12m exacto → Grande ★"),

            // * Método 3
            () -> assertEquals(100.0, club.calcularAmarreMensual("lancha", 10.0), 0.01, "Lancha 10m"),
            () -> assertEquals(120.0, club.calcularAmarreMensual("velero", 10.0), 0.01, "Velero 10m"),
            () -> assertEquals(150.0, club.calcularAmarreMensual("yate",   10.0), 0.01, "Yate 10m"),

            // * Método 4
            () -> assertTrue( club.validarMatricula("AB1234CD"),  "AB1234CD → válida"),
            () -> assertFalse(club.validarMatricula(null),         "null → inválida"),
            () -> assertFalse(club.validarMatricula("AB123CD"),    "7 chars → inválida (AVL)")
        );
    }
}


// ════════════════════════════════════════════════════════════════════════
// *                 RESUMEN RÁPIDO PARA EL EXAMEN
// ════════════════════════════════════════════════════════════════════════
// ?
// ? ── TÉCNICAS DE CAJA NEGRA ────────────────────────────────────────
// ?   Particiones de Equivalencia:
// ?     → Agrupar entradas con igual comportamiento
// ?     → Mínimo 1 test por partición (válida e inválida)
// ?
// ?   Análisis de Valores Límite (AVL):
// ?     → límite−1  /  límite exacto ★  /  límite+1
// ?     → El límite exacto es el más probable de tener bug (< vs <=)
// ?
// ? ── ASSERTS ────────────────────────────────────────────────────────
// ?   assertEquals(ESPERADO, real)            → valores iguales
// ?   assertEquals(ESPERADO, real, 0.001)     → decimales con delta
// ?   assertTrue(condicion)                   → debe ser true
// ?   assertFalse(condicion)                  → debe ser false
// ?   assertThrows(Ex.class, () -> codigo)    → debe lanzar excepción
// ?   assertAll("desc", () -> ..., () -> ...) → varios a la vez
// ?
// ? ── ANOTACIONES ────────────────────────────────────────────────────
// ?   @Test                    → marca el método como test
// ?   @DisplayName("texto")    → nombre legible en el runner
// ?   @BeforeEach              → se ejecuta antes de CADA test
// ?   @BeforeAll / @AfterAll   → una sola vez  ── DEBEN SER STATIC
// ?   @ParameterizedTest       → test que se repite con varios valores
// ?   @ValueSource(ints={...}) → lista de valores   (1 parámetro)
// ?   @CsvSource({"a,b,c"})    → tabla de valores   (n parámetros)
// ?
// ? ── PATRÓN AAA ─────────────────────────────────────────────────────
// ?   // ARRANGE → preparar datos y objetos
// ?   // ACT     → llamar al método que testeas
// ?   // ASSERT  → comprobar que el resultado es el esperado
// ?
// ════════════════════════════════════════════════════════════════════════
