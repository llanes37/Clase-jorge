public class P03_Interfaces {

    /*
     * =============================================================
     * TEORIA: INTERFACES EN JAVA
     * Basado en "Utilizacion avanzada de clases III. Interfaces.pdf"
     * =============================================================
     * * Definicion:
     *   Una INTERFAZ es un contrato: define QUE debe hacer una clase,
     *   pero no COMO lo hace. La clase que la implementa rellena ese como.
     *
     * * Sintaxis:
     *   interface NombreInterfaz {
     *       void metodoAbstracto();          // sin cuerpo por defecto
     *       default void metodoDefault() {}  // con cuerpo (Java 8+)
     *   }
     *
     *   class MiClase implements NombreInterfaz { ... }
     *
     * * Diferencias Interface vs Clase Abstracta:
     *   - Interface: sin estado (atributos de instancia), herencia multiple.
     *   - Clase abstracta: puede tener estado, herencia simple.
     *
     * * Reglas importantes:
     *   - Una clase puede implementar VARIAS interfaces (herencia multiple de tipo).
     *   - Una clase DEBE implementar todos los metodos abstractos de la interfaz.
     *   - Los metodos de una interfaz son public abstract por defecto.
     *   - Los atributos de una interfaz son public static final por defecto.
     *   - Con default puedes dar una implementacion base en la interfaz (Java 8+).
     * =============================================================
     */

    // * Better Comments:
    // * Teoria/explicacion.    ! Critico.    ? Aclaracion.
    // TODO Alumno.             ✅ Solucion.

    public static void main(String[] args) {

        // ── PARTE 1: Implementacion basica ────────────────────────────────
        System.out.println("=== FIGURAS GEOMETRICAS ===");
        Figura circulo    = new Circulo(5.0);
        Figura rectangulo = new Rectangulo(4.0, 6.0);
        Figura triangulo  = new Triangulo(3.0, 4.0, 5.0, 6.0);

        // * Polimorfismo con interfaz: misma variable Figura, distintos objetos.
        Figura[] figuras = { circulo, rectangulo, triangulo };
        for (Figura f : figuras) {
            System.out.printf("%-12s area=%.2f  perimetro=%.2f%n",
                    f.getNombre(), f.calcularArea(), f.calcularPerimetro());
        }
        // TODO mini 1: añade un segundo bucle que llame a f.describir() para cada figura.
        //              Observa cual usa el default y cual usa el @Override de Rectangulo.

        // ── PARTE 2: Multiples interfaces ─────────────────────────────────
        System.out.println("\n=== MULTIPLES INTERFACES ===");
        // * Drone implementa TRES interfaces: Volador, Fotografiable, Cargable.
        Drone drone = new Drone("DJI-Mini", 85);
        drone.volar();
        drone.hacerFoto();
        drone.cargar();
        System.out.println("Bateria: " + drone.getBateria() + "%");
        // TODO mini 2: llama tambien a drone.aterrizar()
        //              Es un metodo default de Volador -> no hace falta implementarlo en Drone.

        // ? Podemos guardar drone en variable de cualquier interfaz que implemente.
        Volador    v = drone;   // upcasting a Volador
        Fotografiable f2 = drone; // upcasting a Fotografiable
        v.volar();
        f2.hacerFoto();

        // ── PARTE 3: Metodo default ────────────────────────────────────────
        // * default: metodo con implementacion en la propia interfaz.
        // * La clase puede usarlo tal cual o sobreescribirlo.
        System.out.println("\n=== METODO DEFAULT ===");
        circulo.describir();    // usa el default de Figura
        rectangulo.describir(); // sobreescrito en Rectangulo

        // TODO Alumno:
        // 1) Crea una interfaz "Imprimible" con metodo imprimir().
        // 2) Haz que Circulo implemente tambien Imprimible.
        //    imprimir() debe mostrar "Circulo de radio X".
        // 3) Crea una interfaz "Escalable" con metodo escalar(double factor).
        //    Implementala en Rectangulo: multiplica base y altura por factor.
        // 4) Prueba ambos metodos en el main.

        // ✅ Solucion orientativa (comentada)
        // Imprimible ci = new Circulo(3.0);
        // ci.imprimir();   -> "Circulo de radio 3.0"
        // Escalable re = new Rectangulo(4.0, 6.0);
        // re.escalar(2.0);  -> base=8.0, altura=12.0
    }

    // =====================================================================
    // * INTERFAZ: Figura
    // * Contrato que deben cumplir todas las figuras geometricas.
    // =====================================================================
    interface Figura {
        // ! Metodos abstractos: sin cuerpo, la clase los implementa.
        double calcularArea();
        double calcularPerimetro();
        String getNombre();

        // * Metodo default: implementacion base que la clase puede sobreescribir.
        default void describir() {
            System.out.printf("Figura '%s': area=%.2f, perimetro=%.2f%n",
                    getNombre(), calcularArea(), calcularPerimetro());
        }
    }

    // =====================================================================
    // * INTERFACES adicionales para el ejemplo de multiples interfaces.
    // =====================================================================
    interface Volador {
        void volar();
        default void aterrizar() { System.out.println("Aterrizando..."); }
    }

    interface Fotografiable {
        void hacerFoto();
    }

    interface Cargable {
        void cargar();
        int getBateria();
    }

    // =====================================================================
    // * CLASE: Circulo implements Figura
    // =====================================================================
    static class Circulo implements Figura {
        private double radio;

        public Circulo(double radio) {
            this.radio = radio;
        }

        @Override public double calcularArea()      { return Math.PI * radio * radio; }
        @Override public double calcularPerimetro() { return 2 * Math.PI * radio; }
        @Override public String getNombre()         { return "Circulo"; }
        // * No sobreescribimos describir() -> usa el default de Figura.
        // TODO mini 3: sobreescribe toString() para que imprima "Circulo(radio=[radio])"
        //              Prueba System.out.println(new Circulo(5.0)) en el main.
    }

    // =====================================================================
    // * CLASE: Rectangulo implements Figura
    // =====================================================================
    static class Rectangulo implements Figura {
        private double base;
        private double altura;

        public Rectangulo(double base, double altura) {
            this.base   = base;
            this.altura = altura;
        }

        @Override public double calcularArea()      { return base * altura; }
        @Override public double calcularPerimetro() { return 2 * (base + altura); }
        @Override public String getNombre()         { return "Rectangulo"; }

        // * Sobreescribimos el default con informacion mas especifica.
        @Override
        public void describir() {
            System.out.printf("Rectangulo %sx%s: area=%.2f, perimetro=%.2f%n",
                    base, altura, calcularArea(), calcularPerimetro());
        }
    }

    // =====================================================================
    // * CLASE: Triangulo implements Figura
    // =====================================================================
    static class Triangulo implements Figura {
        private double base;
        private double ladoA;
        private double ladoB;
        private double altura;

        public Triangulo(double base, double ladoA, double ladoB, double altura) {
            this.base   = base;
            this.ladoA  = ladoA;
            this.ladoB  = ladoB;
            this.altura = altura;
        }

        @Override public double calcularArea()      { return (base * altura) / 2.0; }
        @Override public double calcularPerimetro() { return base + ladoA + ladoB; }
        @Override public String getNombre()         { return "Triangulo"; }
    }

    // =====================================================================
    // * CLASE: Drone implements Volador, Fotografiable, Cargable
    // * Ejemplo de implementacion de MULTIPLES interfaces.
    // =====================================================================
    static class Drone implements Volador, Fotografiable, Cargable {
        private String nombre;
        private int    bateria;

        public Drone(String nombre, int bateria) {
            this.nombre  = nombre;
            this.bateria = Math.min(Math.max(bateria, 0), 100);
        }

        // * Implementamos TODOS los metodos de las tres interfaces.
        @Override public void volar()     { System.out.println(nombre + " vuela!"); }
        @Override public void hacerFoto() { System.out.println(nombre + " hace foto!"); }
        @Override public void cargar()    { bateria = 100; System.out.println(nombre + " cargado al 100%."); }
        @Override public int  getBateria(){ return bateria; }
    }
}
