public class P05_LanzamientoExcepciones {

    /*
     * =============================================================
     * TEORIA: LANZAMIENTO DE EXCEPCIONES EN JAVA
     * Basado en "Lanzamiento de excepciones.pdf"
     * =============================================================
     * * ¿Que es una excepcion?
     *   Un evento que ocurre durante la ejecucion y altera el flujo
     *   normal del programa (p.ej. dividir entre 0, archivo no existe).
     *
     * * Bloque try-catch-finally:
     *   try     -> codigo que puede lanzar excepcion.
     *   catch   -> maneja la excepcion si ocurre.
     *   finally -> se ejecuta SIEMPRE (haya o no excepcion). Util para
     *              cerrar recursos.
     *
     * * throw vs throws:
     *   throw  -> lanza una excepcion (dentro de un metodo).
     *   throws -> declara que un metodo PUEDE lanzar una excepcion
     *             (en la firma del metodo).
     *
     * * Excepciones comprobadas (checked) vs no comprobadas (unchecked):
     *   Checked:   el compilador te obliga a manejarlas (catch o throws).
     *              Ejemplo: IOException, SQLException
     *   Unchecked: extienden RuntimeException, no obligan al compilador.
     *              Ejemplo: NullPointerException, ArrayIndexOutOfBoundsException
     * =============================================================
     */

    // * Better Comments:
    // * Teoria.    ! Critico.    ? Aclaracion.    TODO Alumno.    ✅ Solucion.

    public static void main(String[] args) {

        // ── PARTE 1: try-catch basico ─────────────────────────────────────
        System.out.println("=== TRY-CATCH BASICO ===");
        try {
            int resultado = dividir(10, 0);
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            // * Capturamos la excepcion especifica.
            System.out.println("Error aritmetico: " + e.getMessage());
        }
        System.out.println("El programa continua tras el catch.");

        // ── PARTE 2: Multiples catch ──────────────────────────────────────
        System.out.println("\n=== MULTIPLES CATCH ===");
        String[] nombres = {"Ana", "Luis", null};
        for (String nombre : nombres) {
            try {
                System.out.println("Longitud: " + procesarNombre(nombre));
            } catch (NullPointerException e) {
                System.out.println("El nombre era null: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Argumento invalido: " + e.getMessage());
            }
        }
        // TODO mini 1: añade "" (cadena vacia) al array y ejecuta.
        //              ¿Cual de los dos catch lo captura?

        // ── PARTE 3: finally ──────────────────────────────────────────────
        System.out.println("\n=== FINALLY ===");
        try {
            int r = dividir(20, 4);
            System.out.println("Resultado: " + r);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // ! finally se ejecuta SIEMPRE: con excepcion o sin ella.
            System.out.println("Finally: siempre se ejecuta.");
            // TODO mini 2: añade un return dentro del try y comprueba si finally se ejecuta.
            //              (Pista: si, finally se ejecuta incluso con return.)
        }

        // ── PARTE 4: throw - lanzar excepcion manualmente ─────────────────
        System.out.println("\n=== THROW MANUAL ===");
        try {
            validarEdad(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Capturada: " + e.getMessage());
        }
        try {
            validarEdad(25);
            System.out.println("Edad 25 valida.");
        } catch (IllegalArgumentException e) {
            System.out.println("Capturada: " + e.getMessage());
        }

        // ── PARTE 5: throws - metodo que declara excepcion checked ─────────
        System.out.println("\n=== THROWS (CHECKED) ===");
        try {
            leerConfiguracion("config.txt");
        } catch (java.io.IOException e) {
            // * IOException es checked: el compilador nos obliga a manejarla.
            System.out.println("IOException capturada: " + e.getMessage());
        }

        // ── PARTE 6: Multiples excepciones en un solo catch (Java 7+) ─────
        System.out.println("\n=== MULTI-CATCH ===");
        try {
            operacionRiesgosa(null, 0);
        } catch (NullPointerException | ArithmeticException e) {
            // ? Una sola clausula catch para varios tipos.
            System.out.println("Error (multi-catch): " + e.getClass().getSimpleName());
        }

        // TODO Alumno:
        // 1) Crea un metodo "retirarDinero(double saldo, double cantidad)"
        //    que lance IllegalArgumentException si saldo < cantidad
        //    o si cantidad <= 0.
        // 2) Llamalo con distintos valores y captura la excepcion.
        // 3) Anade un bloque finally que imprima "Operacion finalizada.".
        // 4) Crea un metodo que lance IOException (checked) y declaralo
        //    con throws IOException en la firma.

        // ✅ Solucion orientativa (comentada)
        // try {
        //     retirarDinero(100.0, 200.0);
        // } catch (IllegalArgumentException e) {
        //     System.out.println("Error: " + e.getMessage());
        // } finally {
        //     System.out.println("Operacion finalizada.");
        // }
    }

    // =====================================================================
    // * METODO: dividir
    // * Puede lanzar ArithmeticException (unchecked) si divisor == 0.
    // =====================================================================
    static int dividir(int a, int b) {
        // ! No necesita "throws" porque ArithmeticException es unchecked.
        if (b == 0) {
            throw new ArithmeticException("Division por cero no permitida.");
        }
        return a / b;
    }

    // =====================================================================
    // * METODO: procesarNombre
    // * Puede lanzar NullPointerException o IllegalArgumentException.
    // =====================================================================
    static int procesarNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("El nombre no puede ser null.");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        return nombre.length();
    }

    // =====================================================================
    // * METODO: validarEdad
    // * Lanzamos excepcion con throw si el valor no es valido.
    // =====================================================================
    static void validarEdad(int edad) {
        if (edad < 0 || edad > 150) {
            // * throw: lanzamos la excepcion en este punto.
            throw new IllegalArgumentException("Edad invalida: " + edad + ". Debe estar entre 0 y 150.");
        }
        System.out.println("Edad valida: " + edad);
        // TODO mini 3: añade una segunda validacion: si edad < 18 imprime un aviso
        //              "Aviso: menor de edad." sin lanzar excepcion.
    }

    // =====================================================================
    // * METODO: leerConfiguracion
    // * IOException es CHECKED: el compilador obliga a declararlo con throws.
    // =====================================================================
    static void leerConfiguracion(String archivo) throws java.io.IOException {
        // * throws en la firma indica que este metodo PUEDE lanzar IOException.
        // ! Quien llame a este metodo debe manejarla con try-catch o tambien
        //   declararla con throws.
        throw new java.io.IOException("Archivo '" + archivo + "' no encontrado.");
    }

    // =====================================================================
    // * METODO: operacionRiesgosa
    // * Puede lanzar dos tipos distintos de excepcion.
    // =====================================================================
    static void operacionRiesgosa(String texto, int divisor) {
        if (texto == null) {
            throw new NullPointerException("Texto nulo.");
        }
        if (divisor == 0) {
            throw new ArithmeticException("Divisor cero.");
        }
        System.out.println(texto.length() / divisor);
    }
}
