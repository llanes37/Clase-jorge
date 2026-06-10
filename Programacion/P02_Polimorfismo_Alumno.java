public class P02_Polimorfismo_Alumno {

    /*
     * =============================================================
     * PLANTILLA ALUMNO - POLIMORFISMO
     * =============================================================
     * * Instrucciones:
     *   1. Implementa los TODO en orden.
     *   2. Ejecuta y observa como el array polimorfico cambia de comportamiento.
     *   3. Compara con P02_Polimorfismo.java al terminar.
     *
     * * Conceptos que vas a usar:
     *   upcasting, instanceof, downcasting, @Override, sobrecarga
     * =============================================================
     */

    // * Animal a = new Perro(...)    -> upcasting (automatico, seguro)
    // * if (a instanceof Perro) { (Perro) a }  -> downcasting (manual, con comprobacion)
    // ! Sin instanceof antes del casting -> ClassCastException en ejecucion

    public static void main(String[] args) {

        // * Upcasting: guardamos subtipos en variables de tipo Animal.
        Animal a1 = new Perro("Rex",   3, "Labrador");
        Animal a2 = new Gato("Luna",   2, true);
        Animal a3 = new Pajaro("Pico", 1, "Canario");

        System.out.println("=== POLIMORFISMO CON ARRAY ===");
        Animal[] animales = { a1, a2, a3 };

        // TODO 1: recorre el array y llama a hacerSonido() de cada Animal.
        //         Pista: for (Animal a : animales) { ... }
        //         Observa como cada uno hace el suyo (enlace dinamico).

        System.out.println("\n=== INSTANCEOF Y DOWNCASTING ===");
        // TODO 2: recorre el array con instanceof.
        //         Si es Perro  -> downcasting y llama ladrar()
        //         Si es Gato   -> downcasting y llama ronronear()
        //         Si es Pajaro -> downcasting y llama volar()

        System.out.println("\n=== PRESENTACION ===");
        // TODO 3: recorre el array y llama a presentarse() de cada Animal.

        System.out.println("\n=== SOBRECARGA ===");
        Calculadora calc = new Calculadora();
        // TODO 4: llama a los tres metodos sumar() con distintos tipos de parametros
        //         y muestra el resultado.
        //         calc.sumar(int, int), calc.sumar(double, double), calc.sumar(int, int, int)
    }

    // =====================================================================
    // TODO Alumno 5: Implementa Animal con hacerSonido() y presentarse().
    // =====================================================================
    static class Animal {
        protected String nombre;
        protected int    edad;

        public Animal(String nombre, int edad) {
            // TODO 5a: constructor
        }

        // TODO 5b: hacerSonido() -> "[nombre] emite un sonido generico."
        public void hacerSonido() { }

        // TODO 5c: presentarse() -> "Animal: [nombre], [edad] anio(s)."
        public void presentarse() { }
    }

    // =====================================================================
    // TODO Alumno 6: Implementa Perro extends Animal.
    // =====================================================================
    static class Perro extends Animal {
        private String raza;

        public Perro(String nombre, int edad, String raza) {
            // TODO 6a: super + asignar raza
        }

        // TODO 6b: @Override hacerSonido() -> "[nombre]: GUAU GUAU!"
        @Override public void hacerSonido() { }

        // TODO 6c: @Override presentarse() -> "Perro: [nombre] ([raza]), [edad] anio(s)."
        @Override public void presentarse() { }

        // TODO 6d: ladrar() -> "[nombre] ladra furioso!"
        public void ladrar() { }
    }

    // =====================================================================
    // TODO Alumno 7: Implementa Gato extends Animal.
    // =====================================================================
    static class Gato extends Animal {
        private boolean esInterior;

        public Gato(String nombre, int edad, boolean esInterior) {
            // TODO 7a: super + asignar esInterior
        }

        // TODO 7b: @Override hacerSonido() -> "[nombre]: MIAU!"
        @Override public void hacerSonido() { }

        // TODO 7c: @Override presentarse() con esInterior
        @Override public void presentarse() { }

        // TODO 7d: ronronear() -> "[nombre] ronronea: prrrrr..."
        public void ronronear() { }
    }

    // =====================================================================
    // TODO Alumno 8: Implementa Pajaro extends Animal.
    // =====================================================================
    static class Pajaro extends Animal {
        private String especie;

        public Pajaro(String nombre, int edad, String especie) {
            // TODO 8a: super + asignar especie
        }

        // TODO 8b: @Override hacerSonido() -> "[nombre]: PIIIO PIIIO!"
        @Override public void hacerSonido() { }

        // TODO 8c: @Override presentarse() con especie
        @Override public void presentarse() { }

        // TODO 8d: volar() -> "[nombre] despega y vuela!"
        public void volar() { }
    }

    // =====================================================================
    // TODO Alumno 9: Implementa Calculadora con tres versiones de sumar().
    // * Sobrecarga: mismo nombre, distintos parametros.
    // =====================================================================
    static class Calculadora {
        // TODO 9a: sumar(int a, int b)           -> devuelve int
        // TODO 9b: sumar(double a, double b)     -> devuelve double
        // TODO 9c: sumar(int a, int b, int c)    -> devuelve int
    }
}
