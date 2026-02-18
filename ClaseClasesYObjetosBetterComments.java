public class ClaseClasesYObjetosBetterComments {

    /*
     * =============================================================
     * ENUNCIADO DE CLASE (basado en "Clases y objetos.pdf")
     * =============================================================
     * * Contexto del problema:
     *   Queremos almacenar informacion de un coche en Java.
     *   Si usamos variables sueltas (marca, modelo, matricula, potencia, velocidad, arrancado),
     *   terminamos con datos desorganizados y dificil mantenimiento.
     *
     * * Idea clave de teoria:
     *   Una CLASE es una plantilla (modelo) que agrupa atributos y metodos.
     *   Un OBJETO es una instancia concreta creada a partir de esa plantilla.
     *
     * * Objetivo de esta practica:
     *   1) Definir una clase Coche con atributos.
     *   2) Crear varios objetos Coche con distintos valores.
     *   3) Programar comportamientos: arrancar, acelerar, frenar, detener.
     *   4) Aplicar encapsulacion (private + getters/setters).
     *   5) Validar datos para evitar estados invalidos.
     *
     * * Resultado esperado:
     *   Un programa compilable y ejecutable que muestre el estado inicial/final
     *   de varios coches y permita usar el codigo como base de clase didactica.
     * =============================================================
     */

    // * Better Comments - guia visual para el alumno:
    // * "*" teoria, explicacion suave (verde en muchos temas).
    // ! "!" punto critico o regla importante.
    // ? "?" aclaracion o pregunta frecuente.
    // TODO Alumno -> ejercicio guiado.
    // ✅ Solucion -> referencia comentada.

    // ! MAIN: punto de entrada obligatorio de una aplicacion Java.
    // * Todo empieza aqui cuando ejecutamos: java ClaseClasesYObjetosBetterComments
    public static void main(String[] args) {

        // * PARTE 1 - Crear objetos (instancias) de la clase Coche.
        // * Cada objeto comparte estructura, pero tiene valores propios.
        Coche coche1 = new Coche("Toyota", "Corolla", "1234ABC", 110);
        Coche coche2 = new Coche("Seat", "Ibiza", "5678DEF", 95);
        Coche coche3 = new Coche();

        // * PARTE 2 - Rellenar datos del coche creado con constructor vacio.
        // ? Esto muestra para que sirven los setters.
        coche3.setMarca("Ford");
        coche3.setModelo("Focus");
        coche3.setMatricula("9012GHI");
        coche3.setPotencia(120);

        // * PARTE 3 - Observar estado inicial antes de ejecutar acciones.
        System.out.println("=== ESTADO INICIAL ===");
        System.out.println(coche1);
        System.out.println(coche2);
        System.out.println(coche3);

        // * PARTE 4 - Simular comportamiento real del objeto.
        // * Un metodo cambia el estado interno del objeto.
        coche1.arrancar();
        coche1.acelerar(30);
        coche1.acelerar(25);
        coche1.frenar(10);

        coche2.arrancar();
        coche2.acelerar(50);
        coche2.detener();

        // * PARTE 5 - Provocar un caso invalido para ver validaciones.
        // ! Regla: no se puede acelerar si el coche esta apagado.
        coche3.acelerar(20);
        coche3.arrancar();
        coche3.acelerar(20);

        // * PARTE 6 - Ver como ha cambiado cada objeto tras los metodos.
        System.out.println("\n=== ESTADO FINAL ===");
        System.out.println(coche1);
        System.out.println(coche2);
        System.out.println(coche3);

        // TODO Alumno:
        // 1) Crea un cuarto coche y ejecuta arrancar, acelerar y frenar.
        // 2) Crea un metodo nuevo: tocarClaxon().
        // 3) Intenta asignar potencia negativa y comprueba la validacion.

        // ✅ Solucion orientativa (comentada)
        // Coche coche4 = new Coche("Renault", "Clio", "3456JKL", 90);
        // coche4.arrancar();
        // coche4.acelerar(40);
        // coche4.frenar(15);
        // System.out.println(coche4);
    }

    // * Clase interna para tener todo en un solo archivo compilable.
    // ? En proyectos reales normalmente iria en su propio archivo Coche.java.
    static class Coche {

        // * ATRIBUTOS (estado del objeto).
        // ! Encapsulacion: se declaran private para proteger los datos.
        private String marca;
        private String modelo;
        private String matricula;
        private int potencia;
        private int velocidad;
        private boolean arrancado;

        // * CONSTRUCTOR POR DEFECTO.
        // * Se usa cuando hacemos: new Coche()
        public Coche() {
            this.marca = "SinMarca";
            this.modelo = "SinModelo";
            this.matricula = "0000XXX";
            this.potencia = 70;
            this.velocidad = 0;
            this.arrancado = false;
        }

        // * CONSTRUCTOR CON PARAMETROS.
        // * Permite crear el objeto ya inicializado con datos reales.
        public Coche(String marca, String modelo, String matricula, int potencia) {
            this.marca = marca;
            this.modelo = modelo;
            this.matricula = matricula;
            setPotencia(potencia);
            this.velocidad = 0;
            this.arrancado = false;
        }

        // * METODOS DE COMPORTAMIENTO.
        // * En POO, los metodos representan acciones del objeto.

        public void arrancar() {
            if (arrancado) {
                System.out.println("El coche " + matricula + " ya estaba arrancado.");
                return;
            }
            arrancado = true;
            System.out.println("Coche " + matricula + " arrancado.");
        }

        public void acelerar(int incremento) {
            if (!arrancado) {
                System.out.println("No se puede acelerar: el coche " + matricula + " esta apagado.");
                return;
            }
            if (incremento <= 0) {
                System.out.println("Incremento invalido. Debe ser mayor que 0.");
                return;
            }
            velocidad += incremento;
            System.out.println("Coche " + matricula + " acelera a " + velocidad + " km/h.");
        }

        public void frenar(int decremento) {
            if (decremento <= 0) {
                System.out.println("Decremento invalido. Debe ser mayor que 0.");
                return;
            }
            velocidad -= decremento;
            if (velocidad < 0) {
                velocidad = 0;
            }
            System.out.println("Coche " + matricula + " reduce a " + velocidad + " km/h.");
        }

        public void detener() {
            velocidad = 0;
            arrancado = false;
            System.out.println("Coche " + matricula + " detenido y apagado.");
        }

        // * GETTERS: permiten leer atributos privados sin exponerlos directamente.
        public String getMarca() {
            return marca;
        }

        public String getModelo() {
            return modelo;
        }

        public String getMatricula() {
            return matricula;
        }

        public int getPotencia() {
            return potencia;
        }

        public int getVelocidad() {
            return velocidad;
        }

        public boolean isArrancado() {
            return arrancado;
        }

        // * SETTERS: permiten modificar atributos con control y validaciones.
        // ! Esto evita estados incoherentes en el objeto.
        public void setMarca(String marca) {
            if (marca == null || marca.isBlank()) {
                System.out.println("Marca invalida. Se mantiene valor anterior.");
                return;
            }
            this.marca = marca;
        }

        public void setModelo(String modelo) {
            if (modelo == null || modelo.isBlank()) {
                System.out.println("Modelo invalido. Se mantiene valor anterior.");
                return;
            }
            this.modelo = modelo;
        }

        public void setMatricula(String matricula) {
            if (matricula == null || matricula.isBlank()) {
                System.out.println("Matricula invalida. Se mantiene valor anterior.");
                return;
            }
            this.matricula = matricula;
        }

        public void setPotencia(int potencia) {
            if (potencia <= 0) {
                System.out.println("Potencia invalida. Se usa 70 CV por defecto.");
                this.potencia = 70;
                return;
            }
            this.potencia = potencia;
        }

        // * toString: representacion textual del objeto.
        // ? Muy util para depurar y mostrar informacion en consola.
        @Override
        public String toString() {
            return "Coche{" +
                    "marca='" + marca + '\'' +
                    ", modelo='" + modelo + '\'' +
                    ", matricula='" + matricula + '\'' +
                    ", potencia=" + potencia +
                    ", velocidad=" + velocidad +
                    ", arrancado=" + arrancado +
                    '}';
        }
    }
}