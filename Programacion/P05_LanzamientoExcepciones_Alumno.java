public class P05_LanzamientoExcepciones_Alumno {

    /*
     * =============================================================
     * PLANTILLA ALUMNO - LANZAMIENTO DE EXCEPCIONES
     * =============================================================
     * * Instrucciones:
     *   1. Implementa cada metodo siguiendo los TODO.
     *   2. Ejecuta el main para ver si las excepciones se capturan bien.
     *   3. Compara con P05_LanzamientoExcepciones.java al terminar.
     *
     * * throw  -> lanza la excepcion (dentro del metodo)
     * * throws -> declara que el metodo puede lanzarla (en la firma)
     * * try-catch-finally -> maneja la excepcion
     * =============================================================
     */

    // ! throw va DENTRO del cuerpo del metodo.
    // ! throws va en la FIRMA del metodo (despues de los parentesis).
    // ! finally se ejecuta SIEMPRE, haya o no excepcion.

    public static void main(String[] args) {

        // ── BLOQUE 1: try-catch basico ────────────────────────────────────
        System.out.println("=== TRY-CATCH BASICO ===");
        // TODO 1: llama a dividir(10, 0) dentro de un try-catch.
        //         Captura ArithmeticException e imprime e.getMessage().
        //         Imprime "El programa continua." despues del catch.

        // ── BLOQUE 2: Multiples catch ──────────────────────────────────────
        System.out.println("\n=== MULTIPLES CATCH ===");
        String[] nombres = {"Ana", "Luis", null};
        // TODO 2: recorre el array con un for.
        //         Dentro del for, llama a procesarNombre(nombre) en un try.
        //         Captura NullPointerException e IllegalArgumentException por separado.

        // ── BLOQUE 3: finally ─────────────────────────────────────────────
        System.out.println("\n=== FINALLY ===");
        // TODO 3: llama a dividir(20, 4) en un try-catch-finally.
        //         En finally imprime "Finally: siempre se ejecuta."

        // ── BLOQUE 4: throw manual ────────────────────────────────────────
        System.out.println("\n=== THROW MANUAL ===");
        // TODO 4a: llama a validarEdad(-5) en un try-catch.
        //          Captura IllegalArgumentException e imprime el mensaje.
        // TODO 4b: llama a validarEdad(25) en un try-catch.
        //          Si no lanza excepcion, imprime "Edad 25 valida."

        // ── BLOQUE 5: throws checked ──────────────────────────────────────
        System.out.println("\n=== THROWS (CHECKED) ===");
        // TODO 5: llama a leerConfiguracion("config.txt") en un try-catch.
        //         Captura java.io.IOException e imprime el mensaje.

        // ── BLOQUE 6: multi-catch ─────────────────────────────────────────
        System.out.println("\n=== MULTI-CATCH ===");
        // TODO 6: llama a operacionRiesgosa(null, 0) en un try.
        //         Captura NullPointerException | ArithmeticException en UN solo catch.
        //         Imprime el nombre simple del tipo: e.getClass().getSimpleName()
    }

    // =====================================================================
    // TODO Alumno A: implementa dividir(int a, int b)
    //   Si b == 0, lanza ArithmeticException("Division por cero no permitida.")
    //   Si no, devuelve a / b.
    //   No necesita "throws" (ArithmeticException es unchecked).
    // =====================================================================
    static int dividir(int a, int b) {
        // TODO: if b==0 -> throw new ArithmeticException("...")
        return 0; // reemplaza
    }

    // =====================================================================
    // TODO Alumno B: implementa procesarNombre(String nombre)
    //   Si nombre es null -> lanza NullPointerException("El nombre no puede ser null.")
    //   Si nombre.isBlank() -> lanza IllegalArgumentException("Nombre vacio.")
    //   Si es valido -> devuelve nombre.length()
    // =====================================================================
    static int procesarNombre(String nombre) {
        // TODO
        return 0; // reemplaza
    }

    // =====================================================================
    // TODO Alumno C: implementa validarEdad(int edad)
    //   Si edad < 0 o edad > 150 -> lanza IllegalArgumentException con mensaje descriptivo.
    //   Si es valida -> imprime "Edad valida: [edad]"
    // =====================================================================
    static void validarEdad(int edad) {
        // TODO
    }

    // =====================================================================
    // TODO Alumno D: implementa leerConfiguracion(String archivo)
    //   Declara throws java.io.IOException en la firma.
    //   Lanza siempre: new java.io.IOException("Archivo '" + archivo + "' no encontrado.")
    // =====================================================================
    static void leerConfiguracion(String archivo) /* TODO: añade throws aqui */ {
        // TODO: throw new java.io.IOException(...)
    }

    // =====================================================================
    // TODO Alumno E: implementa operacionRiesgosa(String texto, int divisor)
    //   Si texto es null -> lanza NullPointerException("Texto nulo.")
    //   Si divisor == 0  -> lanza ArithmeticException("Divisor cero.")
    //   Si todo OK       -> imprime texto.length() / divisor
    // =====================================================================
    static void operacionRiesgosa(String texto, int divisor) {
        // TODO
    }
}
