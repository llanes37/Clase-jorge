public class P06_JerarquiaExcepciones {

    /*
     * =============================================================
     * TEORIA: JERARQUIA DE EXCEPCIONES EN JAVA
     * Basado en "Jerarquia de excepciones.pdf"
     * =============================================================
     * * Jerarquia (arbol de herencia):
     *
     *   Throwable
     *   ├── Error               (errores graves del sistema, NO capturar)
     *   │   ├── OutOfMemoryError
     *   │   └── StackOverflowError
     *   └── Exception           (errores recuperables, SI capturar)
     *       ├── IOException     (checked)
     *       ├── SQLException    (checked)
     *       └── RuntimeException (unchecked)
     *           ├── NullPointerException
     *           ├── ArithmeticException
     *           ├── IllegalArgumentException
     *           ├── ArrayIndexOutOfBoundsException
     *           └── ClassCastException
     *
     * * Crear excepciones personalizadas:
     *   - Extends Exception         -> excepcion CHECKED (checked)
     *   - Extends RuntimeException  -> excepcion UNCHECKED
     *
     * * Buenas practicas:
     *   - Captura siempre el tipo MAS ESPECIFICO posible.
     *   - Nunca captures Exception o Throwable sin razon.
     *   - Nunca dejes el bloque catch vacio (traga el error sin avisar).
     *   - Usar excepciones propias para dar contexto al error.
     * =============================================================
     */

    // * Better Comments:
    // * Teoria.    ! Critico.    ? Aclaracion.    TODO Alumno.    ✅ Solucion.

    public static void main(String[] args) {

        // ── PARTE 1: Capturar tipos especificos vs genericos ──────────────
        System.out.println("=== CAPTURA ESPECIFICA ===");
        try {
            String s = null;
            System.out.println(s.length());   // NullPointerException
        } catch (NullPointerException e) {
            // * Capturamos el tipo mas especifico: NullPointerException.
            System.out.println("Capturado NullPointerException: " + e.getClass().getSimpleName());
        }

        // ! Si usamos catch (Exception e) capturamos TODO pero perdemos detalle.
        System.out.println("--- Con Exception generico ---");
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[10]);  // ArrayIndexOutOfBoundsException
        } catch (Exception e) {
            // ? El tipo real se puede ver con getClass().getSimpleName()
            System.out.println("Exception generico: " + e.getClass().getSimpleName());
        }
        // TODO mini 1: cambia el catch anterior a ArrayIndexOutOfBoundsException especifico.
        //              Luego añade un catch(Exception e) debajo como "red de seguridad".

        // ── PARTE 2: Orden de catch (mas especifico primero) ──────────────
        // ! Si pones catch(Exception e) ANTES que catch(IOException e),
        //   el compilador da error: "Exception already caught".
        System.out.println("\n=== ORDEN DE CATCH ===");
        try {
            lanzarChecked(true);
        } catch (java.io.IOException e) {
            System.out.println("IOException capturada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception generica: " + e.getMessage());
        }

        // ── PARTE 3: Excepciones personalizadas checked ───────────────────
        System.out.println("\n=== EXCEPCION PROPIA CHECKED ===");
        try {
            retirarSaldo(100.0, 200.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error banco: " + e.getMessage());
            System.out.println("  Saldo: " + e.getSaldoActual() + " | Pedido: " + e.getCantidadPedida());
        }
        // TODO mini 2: llama a retirarSaldo(100.0, 50.0) (esta vez SIN error).
        //              ¿Se ejecuta el catch? ¿Que imprime?

        // ── PARTE 4: Excepciones personalizadas unchecked ─────────────────
        System.out.println("\n=== EXCEPCION PROPIA UNCHECKED ===");
        try {
            crearUsuario("", "pass123");
        } catch (DatosInvalidosException e) {
            System.out.println("Datos invalidos: " + e.getMessage());
        }
        try {
            crearUsuario("Ana", "123");   // contrasena muy corta
        } catch (DatosInvalidosException e) {
            System.out.println("Datos invalidos: " + e.getMessage());
        }

        // ── PARTE 5: Relanzar excepcion (wrapping) ─────────────────────────
        System.out.println("\n=== RELANZAR EXCEPCION ===");
        try {
            operacionConLog("test");
        } catch (RuntimeException e) {
            System.out.println("Excepcion relanzada: " + e.getMessage());
            System.out.println("Causa original: " + e.getCause().getClass().getSimpleName());
        }

        // TODO Alumno:
        // 1) Crea una excepcion checked "ProductoNoEncontradoException"
        //    con atributo String codigoProducto.
        //    Lanzala en un metodo "buscarProducto(String codigo)".
        // 2) Crea una excepcion unchecked "StockNegativoException".
        //    Lanzala en un metodo "reducirStock(int cantidad)".
        // 3) Captura ambas en el main con mensajes descriptivos.

        // ✅ Solucion orientativa (comentada)
        // try {
        //     buscarProducto("XXX");
        // } catch (ProductoNoEncontradoException e) {
        //     System.out.println("No encontrado: " + e.getCodigoProducto());
        // }
    }

    static void lanzarChecked(boolean lanzar) throws java.io.IOException {
        if (lanzar) throw new java.io.IOException("Fallo de lectura simulado.");
    }

    // * Metodo que lanza excepcion propia CHECKED.
    static void retirarSaldo(double saldo, double cantidad) throws SaldoInsuficienteException {
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException(saldo, cantidad);
        }
        System.out.println("Retiro OK: " + cantidad);
    }

    // * Metodo que lanza excepcion propia UNCHECKED.
    static void crearUsuario(String nombre, String contrasena) {
        if (nombre == null || nombre.isBlank()) {
            throw new DatosInvalidosException("El nombre no puede estar vacio.");
        }
        if (contrasena == null || contrasena.length() < 6) {
            throw new DatosInvalidosException("La contrasena debe tener al menos 6 caracteres.");
        }
        System.out.println("Usuario '" + nombre + "' creado.");
    }

    // * Metodo que captura una excepcion y la relanza con mas contexto.
    static void operacionConLog(String input) {
        try {
            int resultado = Integer.parseInt(input);  // NumberFormatException
            System.out.println("Resultado: " + resultado);
        } catch (NumberFormatException e) {
            // * Wrapping: envolvemos la original en una RuntimeException con mensaje util.
            throw new RuntimeException("Error procesando input '" + input + "'", e);
        }
    }

    // =====================================================================
    // * EXCEPCION PROPIA CHECKED: extiende Exception
    // * El compilador obliga a manejarla con try-catch o throws.
    // =====================================================================
    static class SaldoInsuficienteException extends Exception {
        private final double saldoActual;
        private final double cantidadPedida;

        public SaldoInsuficienteException(double saldoActual, double cantidadPedida) {
            // * Mensaje descriptivo pasado al padre (Exception).
            super(String.format("Saldo insuficiente. Tiene %.2f, pide %.2f.", saldoActual, cantidadPedida));
            // TODO mini 3: añade al mensaje cuanto falta: "Faltan X EUR."
            //              Pista: cantidadPedida - saldoActual
            this.saldoActual    = saldoActual;
            this.cantidadPedida = cantidadPedida;
        }

        // * Getters para acceder a informacion extra del error.
        public double getSaldoActual()    { return saldoActual; }
        public double getCantidadPedida() { return cantidadPedida; }
    }

    // =====================================================================
    // * EXCEPCION PROPIA UNCHECKED: extiende RuntimeException
    // * No obliga al compilador a manejarla.
    // =====================================================================
    static class DatosInvalidosException extends RuntimeException {
        public DatosInvalidosException(String mensaje) {
            super(mensaje);
        }
    }
}
