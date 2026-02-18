public class DepuracionObjetosBetterComments {

    /*
     * =============================================================
     * ENUNCIADO DE CLASE (basado en "Depuración de objetos.pdf")
     * =============================================================
     * * Objetivo:
     *   Practicar la depuracion de objetos en Java usando:
     *   - Step Over
     *   - Step Into
     *   - Breakpoints
     *   - this (objeto actual)
     *   - Step Return
     *   - Resume
     *
     * * Idea del ejercicio:
     *   Vamos a crear dos objetos CuentaBancaria y ejecutar operaciones
     *   para poder depurar metodos y observar cambios de estado.
     * =============================================================
     */

    // * Better Comments (recomendado en VS Code / IntelliJ con plugin):
    // * Teoria y explicacion suave.
    // ! Punto importante.
    // ? Aclaracion.
    // TODO Alumno ejercicio.
    // ✅ Solucion referencia.

    public static void main(String[] args) {

        // * Creamos dos objetos para tener interaccion real entre instancias.
        CuentaBancaria cuentaA = new CuentaBancaria("ES11", "Ana", 1000.0);
        CuentaBancaria cuentaB = new CuentaBancaria("ES22", "Luis", 300.0);

        // * Estado inicial.
        System.out.println("=== ESTADO INICIAL ===");
        System.out.println(cuentaA.resumen());
        System.out.println(cuentaB.resumen());

        // ! BREAKPOINT 1 recomendado en esta linea (main):
        // ? Usa Step Over para avanzar linea a linea SIN entrar en metodos.
        cuentaA.ingresar(250.0);

        // ! BREAKPOINT 2 recomendado dentro de retirar(...):
        // ? Usa Step Into para ENTRAR en el metodo y ver variables locales.
        cuentaA.retirar(120.0);

        // ! BREAKPOINT 3 recomendado dentro de transferir(...):
        // ? Observa "this" como objeto actual mientras depuras.
        cuentaA.transferir(cuentaB, 200.0);

        // * Caso invalido para ver validaciones durante depuracion.
        cuentaB.retirar(9999.0);

        // * Estado final.
        System.out.println("\n=== ESTADO FINAL ===");
        System.out.println(cuentaA.resumen());
        System.out.println(cuentaB.resumen());

        // TODO Alumno:
        // 1) Pon breakpoint en aplicarComisionInterna() y usa Step Return.
        // 2) Usa Resume para ir al siguiente breakpoint.
        // 3) Cambia montos para forzar mas casos invalidos.

        // ✅ Solucion orientativa (comentada)
        // cuentaB.ingresar(50);
        // cuentaB.transferir(cuentaA, 20);
    }

    static class CuentaBancaria {

        // * Atributos del objeto (estado interno).
        private final String iban;
        private final String titular;
        private double saldo;

        public CuentaBancaria(String iban, String titular, double saldoInicial) {
            this.iban = iban;
            this.titular = titular;
            this.saldo = Math.max(saldoInicial, 0.0);
        }

        public void ingresar(double cantidad) {
            // ! BREAKPOINT recomendado aqui para Step Into.
            if (cantidad <= 0) {
                System.out.println("Ingreso invalido en " + iban + ".");
                return;
            }

            // * this representa el objeto actual que ejecuta el metodo.
            this.saldo += cantidad;
            System.out.println("Ingreso de " + cantidad + " EUR en " + iban + ".");
        }

        public void retirar(double cantidad) {
            // ! BREAKPOINT recomendado aqui.
            if (cantidad <= 0) {
                System.out.println("Retirada invalida en " + iban + ".");
                return;
            }
            if (cantidad > saldo) {
                System.out.println("Saldo insuficiente en " + iban + ".");
                return;
            }

            saldo -= cantidad;

            // * Llamada interna util para practicar Step Into/Step Return.
            aplicarComisionInterna();

            System.out.println("Retirada de " + cantidad + " EUR en " + iban + ".");
        }

        public void transferir(CuentaBancaria destino, double cantidad) {
            // ! BREAKPOINT recomendado aqui.
            if (destino == null) {
                System.out.println("Transferencia cancelada: destino null.");
                return;
            }
            if (destino == this) {
                System.out.println("Transferencia cancelada: misma cuenta.");
                return;
            }
            if (cantidad <= 0 || cantidad > saldo) {
                System.out.println("Transferencia invalida desde " + iban + ".");
                return;
            }

            // * Flujo interesante para depurar objetos:
            // * 1) this.retirar(cantidad)
            // * 2) destino.ingresar(cantidad)
            // ? Con Step Into veras el cambio de contexto entre objetos.
            this.retirar(cantidad);
            destino.ingresar(cantidad);

            System.out.println("Transferencia de " + cantidad + " EUR desde " + this.iban + " hacia " + destino.iban + ".");
        }

        private void aplicarComisionInterna() {
            // ! Metodo privado ideal para practicar Step Return.
            double comision = 1.0;
            if (saldo >= comision) {
                saldo -= comision;
            }
        }

        public String resumen() {
            return "Cuenta{iban='" + iban + "', titular='" + titular + "', saldo=" + saldo + "}";
        }
    }
}