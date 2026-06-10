public class P03_Interfaces_Alumno {

    /*
     * =============================================================
     * PLANTILLA ALUMNO - INTERFACES
     * =============================================================
     * * Instrucciones:
     *   1. Lee la teoria de cada interfaz antes de implementarla.
     *   2. Implementa los TODO en orden.
     *   3. Compara con P03_Interfaces.java al terminar.
     *
     * * Conceptos que vas a usar:
     *   interface, implements, @Override, default, multiples interfaces
     * =============================================================
     */

    // * interface Nombre { void metodo(); }   -> solo firma, sin cuerpo
    // * class Clase implements Interfaz { }   -> DEBE implementar todos los metodos
    // * default void metodo() { ... }         -> cuerpo opcional en la interfaz

    public static void main(String[] args) {

        System.out.println("=== FIGURAS GEOMETRICAS ===");
        Figura circulo    = new Circulo(5.0);
        Figura rectangulo = new Rectangulo(4.0, 6.0);
        Figura triangulo  = new Triangulo(3.0, 4.0, 5.0, 6.0);

        Figura[] figuras = { circulo, rectangulo, triangulo };

        // TODO 1: recorre el array e imprime nombre, area y perimetro de cada Figura.
        //         Usa calcularArea() y calcularPerimetro().

        System.out.println("\n=== METODO DEFAULT ===");
        // TODO 2: llama a describir() en circulo y rectangulo.
        //         Observa cual usa el default y cual usa el @Override.

        System.out.println("\n=== DRONE CON MULTIPLES INTERFACES ===");
        Drone drone = new Drone("DJI-Mini", 85);

        // TODO 3: llama a drone.volar(), drone.hacerFoto(), drone.cargar()
        //         y muestra drone.getBateria().
        //         Luego guarda drone en una variable Volador y llama volar().
        //         Luego en una variable Fotografiable y llama hacerFoto().
    }

    // =====================================================================
    // * INTERFAZ Figura: contrato para todas las figuras geometricas.
    // =====================================================================
    interface Figura {
        // TODO A: declara metodo abstracto calcularArea() que devuelve double
        // TODO B: declara metodo abstracto calcularPerimetro() que devuelve double
        // TODO C: declara metodo abstracto getNombre() que devuelve String

        // * default: implementacion base que las clases pueden sobreescribir.
        // TODO D: implementa un metodo default describir() que imprima:
        //         "Figura '[nombre]': area=[area], perimetro=[perimetro]"
        //         Usa getNombre(), calcularArea() y calcularPerimetro().
    }

    // =====================================================================
    // * INTERFACES para el Drone.
    // =====================================================================
    interface Volador {
        void volar();
        // * default: aterrizar() ya tiene implementacion, no hay que sobreescribirla.
        default void aterrizar() { System.out.println("Aterrizando..."); }
    }

    interface Fotografiable {
        // TODO E: declara metodo abstracto hacerFoto()
    }

    interface Cargable {
        void cargar();
        int  getBateria();
    }

    // =====================================================================
    // TODO Alumno 4: Implementa Circulo implements Figura.
    // =====================================================================
    static class Circulo implements Figura {
        private double radio;

        public Circulo(double radio) {
            // TODO 4a: asigna radio
        }

        // TODO 4b: calcularArea()      -> Math.PI * radio * radio
        @Override public double calcularArea()      { return 0; /* TODO */ }

        // TODO 4c: calcularPerimetro() -> 2 * Math.PI * radio
        @Override public double calcularPerimetro() { return 0; /* TODO */ }

        // TODO 4d: getNombre()         -> "Circulo"
        @Override public String getNombre()         { return null; /* TODO */ }

        // * Circulo NO sobreescribe describir() -> usa el default de Figura.
    }

    // =====================================================================
    // TODO Alumno 5: Implementa Rectangulo implements Figura.
    // =====================================================================
    static class Rectangulo implements Figura {
        private double base;
        private double altura;

        public Rectangulo(double base, double altura) {
            // TODO 5a: asigna base y altura
        }

        // TODO 5b: calcularArea()      -> base * altura
        @Override public double calcularArea()      { return 0; /* TODO */ }

        // TODO 5c: calcularPerimetro() -> 2 * (base + altura)
        @Override public double calcularPerimetro() { return 0; /* TODO */ }

        // TODO 5d: getNombre()         -> "Rectangulo"
        @Override public String getNombre()         { return null; /* TODO */ }

        // TODO 5e: sobreescribe describir() con formato:
        //          "Rectangulo [base]x[altura]: area=[area], perimetro=[perimetro]"
        @Override
        public void describir() {
            // TODO
        }
    }

    // =====================================================================
    // TODO Alumno 6: Implementa Triangulo implements Figura.
    // =====================================================================
    static class Triangulo implements Figura {
        private double base, ladoA, ladoB, altura;

        public Triangulo(double base, double ladoA, double ladoB, double altura) {
            // TODO 6a: asigna todos los campos
        }

        // TODO 6b: calcularArea()      -> (base * altura) / 2
        @Override public double calcularArea()      { return 0; /* TODO */ }

        // TODO 6c: calcularPerimetro() -> base + ladoA + ladoB
        @Override public double calcularPerimetro() { return 0; /* TODO */ }

        // TODO 6d: getNombre()         -> "Triangulo"
        @Override public String getNombre()         { return null; /* TODO */ }
    }

    // =====================================================================
    // TODO Alumno 7: Implementa Drone implements Volador, Fotografiable, Cargable.
    // * Un Drone implementa TRES interfaces a la vez.
    // =====================================================================
    static class Drone implements Volador, Fotografiable, Cargable {
        private String nombre;
        private int    bateria;

        public Drone(String nombre, int bateria) {
            // TODO 7a: asigna nombre; bateria debe estar entre 0 y 100 (usa Math.min/max)
        }

        // TODO 7b: volar()     -> "[nombre] vuela!"
        @Override public void volar()     { }

        // TODO 7c: hacerFoto() -> "[nombre] hace foto!"
        @Override public void hacerFoto() { }

        // TODO 7d: cargar()    -> asigna bateria = 100 e imprime "[nombre] cargado al 100%."
        @Override public void cargar()    { }

        // TODO 7e: getBateria() -> devuelve bateria
        @Override public int getBateria() { return 0; /* TODO */ }
    }
}
