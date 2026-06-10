public class P06_JerarquiaExcepciones_Alumno {

    /*
     * =============================================================
     * PLANTILLA ALUMNO - JERARQUIA DE EXCEPCIONES
     * =============================================================
     * * Instrucciones:
     *   1. Crea primero las excepciones propias (BLOQUE A y B).
     *   2. Luego implementa los metodos (BLOQUE C y D).
     *   3. Finalmente completa el main (BLOQUE 1-5).
     *
     * * Excepcion checked:   class MiExcepcion extends Exception { }
     * * Excepcion unchecked: class MiExcepcion extends RuntimeException { }
     * =============================================================
     */

    // ! extends Exception       -> el compilador OBLIGA a manejarla (try-catch o throws)
    // ! extends RuntimeException -> el compilador NO obliga (pero se puede capturar)
    // ! Captura siempre el tipo MAS ESPECIFICO primero en los catch.

    public static void main(String[] args) {

        // ── BLOQUE 1: Captura especifica vs generica ──────────────────────
        System.out.println("=== CAPTURA ESPECIFICA ===");
        // TODO 1: provoca un NullPointerException:
        //         String s = null; s.length();
        //         Capturalo con catch (NullPointerException e).

        // ── BLOQUE 2: Orden de catch ──────────────────────────────────────
        System.out.println("\n=== ORDEN DE CATCH ===");
        // TODO 2: llama a lanzarChecked(true).
        //         Pon primero catch(java.io.IOException e) y luego catch(Exception e).
        //         Observa cual se ejecuta.

        // ── BLOQUE 3: Excepcion propia checked ────────────────────────────
        System.out.println("\n=== EXCEPCION PROPIA CHECKED ===");
        // TODO 3: llama a retirarSaldo(100.0, 200.0) en un try-catch.
        //         Captura SaldoInsuficienteException.
        //         Imprime getMessage(), getSaldoActual() y getCantidadPedida().

        // ── BLOQUE 4: Excepcion propia unchecked ─────────────────────────
        System.out.println("\n=== EXCEPCION PROPIA UNCHECKED ===");
        // TODO 4a: llama a crearUsuario("", "pass123") y captura DatosInvalidosException.
        // TODO 4b: llama a crearUsuario("Ana", "123")  y captura DatosInvalidosException.

        // ── BLOQUE 5: Relanzar excepcion ──────────────────────────────────
        System.out.println("\n=== RELANZAR ===");
        // TODO 5: llama a operacionConLog("texto_no_numerico") en un try-catch.
        //         Captura RuntimeException, imprime el mensaje Y el nombre
        //         de la causa: e.getCause().getClass().getSimpleName()
    }

    static void lanzarChecked(boolean lanzar) throws java.io.IOException {
        if (lanzar) throw new java.io.IOException("Fallo simulado.");
    }

    // =====================================================================
    // TODO Alumno C: implementa retirarSaldo(double saldo, double cantidad)
    //   Declara throws SaldoInsuficienteException en la firma.
    //   Si cantidad > saldo -> lanza new SaldoInsuficienteException(saldo, cantidad).
    //   Si es OK -> imprime "Retiro OK: [cantidad]"
    // =====================================================================
    static void retirarSaldo(double saldo, double cantidad) throws SaldoInsuficienteException {
        // TODO
    }

    // =====================================================================
    // TODO Alumno D: implementa crearUsuario(String nombre, String contrasena)
    //   Si nombre es null o blank  -> lanza DatosInvalidosException("El nombre no puede estar vacio.")
    //   Si contrasena < 6 chars    -> lanza DatosInvalidosException("Minimo 6 caracteres.")
    //   Si todo OK                 -> imprime "Usuario '[nombre]' creado."
    // =====================================================================
    static void crearUsuario(String nombre, String contrasena) {
        // TODO
    }

    // =====================================================================
    // TODO Alumno E: implementa operacionConLog(String input)
    //   Dentro de un try, parsea input con Integer.parseInt(input).
    //   En el catch (NumberFormatException e), relanza como:
    //   throw new RuntimeException("Error procesando input '" + input + "'", e);
    // =====================================================================
    static void operacionConLog(String input) {
        // TODO
    }

    // =====================================================================
    // TODO Alumno A: crea SaldoInsuficienteException extends Exception.
    //   Atributos: double saldoActual, double cantidadPedida.
    //   Constructor: recibe ambos y llama super() con un mensaje descriptivo.
    //   Getters: getSaldoActual(), getCantidadPedida().
    // =====================================================================
    static class SaldoInsuficienteException extends Exception {
        // TODO A1: atributos
        // TODO A2: constructor con super(mensaje descriptivo)
        // TODO A3: getters
    }

    // =====================================================================
    // TODO Alumno B: crea DatosInvalidosException extends RuntimeException.
    //   Solo necesita un constructor que reciba String mensaje y llame super(mensaje).
    // =====================================================================
    static class DatosInvalidosException extends RuntimeException {
        // TODO B1: constructor
    }
}
