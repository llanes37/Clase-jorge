public class P02_Polimorfismo {

    /*
     * =============================================================
     * TEORIA: POLIMORFISMO EN JAVA
     * Basado en "Utilizacion avanzada de clases II. Polimorfismo.pdf"
     * =============================================================
     * * Definicion:
     *   POLIMORFISMO = "muchas formas".
     *   El mismo metodo se comporta de manera diferente segun el objeto
     *   que lo ejecuta. Se basa en la herencia (P01).
     *
     * * Tipos de polimorfismo en Java:
     *   1) SOBREESCRITURA (Override) -> en tiempo de ejecucion.
     *      La subclase redefine un metodo del padre.
     *   2) SOBRECARGA (Overload) -> en tiempo de compilacion.
     *      Mismo nombre de metodo, distintos parametros.
     *
     * * Conceptos clave:
     *   - UPCASTING: guardar un objeto hijo en variable de tipo padre.
     *     Animal a = new Perro("Rex", 3, "Labrador");
     *   - DOWNCASTING: recuperar el tipo real del objeto.
     *     Perro p = (Perro) a;
     *   - instanceof: comprobar el tipo real antes de hacer casting.
     *     if (a instanceof Perro) { ... }
     *   - Enlace dinamico (dynamic binding): Java decide en ejecucion
     *     que metodo @Override llamar segun el tipo real del objeto.
     * =============================================================
     */

    // * Better Comments - guia visual:
    // * Teoria/explicacion suave.
    // ! Punto critico o regla importante.
    // ? Aclaracion o pregunta frecuente.
    // TODO Alumno -> ejercicio guiado.
    // ✅ Solucion -> referencia comentada.

    public static void main(String[] args) {

        // ── PARTE 1: Upcasting ────────────────────────────────────────────
        // * Guardamos objetos Perro y Gato en variables de tipo Animal.
        // * Esto es upcasting: es seguro y automatico.
        Animal a1 = new Perro("Rex",   3, "Labrador");
        Animal a2 = new Gato("Luna",   2, true);
        Animal a3 = new Pajaro("Pico", 1, "Canario");

        System.out.println("=== POLIMORFISMO CON ARRAY ===");
        // * Podemos meter distintos tipos en un array de Animal.
        Animal[] animales = { a1, a2, a3 };

        // * Enlace dinamico: Java llama al hacerSonido() de cada tipo real.
        // ! Aunque la variable es Animal, Java busca el metodo en la subclase real.
        for (Animal a : animales) {
            a.hacerSonido();   // polimorfismo en accion
            // TODO mini 1: añade aqui a.presentarse() y observa que cada uno
            //              imprime su propio formato (otro ejemplo de enlace dinamico).
        }

        // ── PARTE 2: instanceof y Downcasting ─────────────────────────────
        System.out.println("\n=== INSTANCEOF Y DOWNCASTING ===");
        for (Animal a : animales) {
            // ? instanceof devuelve true si el objeto ES de ese tipo (o lo extiende).
            if (a instanceof Perro) {
                Perro p = (Perro) a;   // downcasting: recuperamos el tipo real
                p.ladrar();
            } else if (a instanceof Pajaro) {
                Pajaro pa = (Pajaro) a;
                pa.volar();
            } else if (a instanceof Gato) {
                Gato g = (Gato) a;
                g.ronronear();
            }
        }

        // TODO mini 2: añade un bloque else al final que imprima:
        //              "Animal de tipo desconocido: " + a.getClass().getSimpleName()
        //              Prueba anadiendo un nuevo subtipo que no este en los if.

        // ! Si haces downcasting sin comprobar con instanceof puedes tener
        //   ClassCastException en tiempo de ejecucion.
        // ! Ejemplo de error:
        // Perro error = (Perro) a2;  // a2 es Gato -> ClassCastException!

        // ── PARTE 3: Sobrecarga (Overload) ────────────────────────────────
        // * La sobrecarga NO depende de la herencia.
        // * El compilador elige el metodo segun los parametros.
        System.out.println("\n=== SOBRECARGA ===");
        Calculadora calc = new Calculadora();
        System.out.println("Suma int:    " + calc.sumar(3, 5));
        System.out.println("Suma double: " + calc.sumar(2.5, 1.5));
        System.out.println("Suma 3 ints: " + calc.sumar(1, 2, 3));
        // TODO mini 3: añade un cuarto metodo sumar(String a, String b) que concatene.
        //              Prueba calc.sumar("Hola ", "mundo") -> "Hola mundo"

        // ── PARTE 4: Metodo polimorfico con logica comun ──────────────────
        System.out.println("\n=== PRESENTACION POLIMORFICAS ===");
        for (Animal a : animales) {
            a.presentarse();   // cada uno usa su @Override
            System.out.println("---");
        }

        // TODO Alumno:
        // 1) Crea una subclase "Serpiente" que extienda Animal.
        //    hacerSonido() -> "SSSSS..."
        //    metodo propio: reptar()
        // 2) Anade un objeto Serpiente al array animales (tendra que ser ArrayList).
        // 3) En el bucle with instanceof, detecta Serpiente y llama reptar().
        // 4) Prueba a hacer downcasting incorrecto y observa ClassCastException.

        // ✅ Solucion orientativa (comentada)
        // Serpiente s = new Serpiente("Sisi", 4);
        // s.hacerSonido();   // SSSSS...
        // s.reptar();
        // Animal aS = s;     // upcasting
        // if (aS instanceof Serpiente) { ((Serpiente) aS).reptar(); }
    }

    // =====================================================================
    // * CLASE PADRE: Animal (misma jerarquia que P01)
    // =====================================================================
    static class Animal {
        protected String nombre;
        protected int    edad;

        public Animal(String nombre, int edad) {
            this.nombre = nombre;
            this.edad   = Math.max(edad, 0);
        }

        public void hacerSonido() {
            System.out.println(nombre + " emite un sonido generico.");
        }

        public void presentarse() {
            System.out.println("Animal: " + nombre + ", " + edad + " anio(s).");
        }
    }

    static class Perro extends Animal {
        private String raza;

        public Perro(String nombre, int edad, String raza) {
            super(nombre, edad);
            this.raza = raza;
        }

        @Override public void hacerSonido()  { System.out.println(nombre + ": GUAU GUAU!"); }
        @Override public void presentarse()  { System.out.println("Perro: " + nombre + " (" + raza + "), " + edad + " anio(s)."); }
        public    void ladrar()              { System.out.println(nombre + " ladra furioso!"); }
    }

    static class Gato extends Animal {
        private boolean esInterior;

        public Gato(String nombre, int edad, boolean esInterior) {
            super(nombre, edad);
            this.esInterior = esInterior;
        }

        @Override public void hacerSonido() { System.out.println(nombre + ": MIAU!"); }
        @Override public void presentarse() { System.out.println("Gato: " + nombre + " (interior=" + esInterior + "), " + edad + " anio(s)."); }
        public    void ronronear()          { System.out.println(nombre + " ronronea: prrrrr..."); }
    }

    static class Pajaro extends Animal {
        private String especie;

        public Pajaro(String nombre, int edad, String especie) {
            super(nombre, edad);
            this.especie = especie;
        }

        @Override public void hacerSonido() { System.out.println(nombre + ": PIIIO PIIIO!"); }
        @Override public void presentarse() { System.out.println("Pajaro: " + nombre + " (" + especie + "), " + edad + " anio(s)."); }
        public    void volar()              { System.out.println(nombre + " despega y vuela!"); }
    }

    // =====================================================================
    // * SOBRECARGA: misma clase, mismo nombre, distintos parametros.
    // * El compilador elige el metodo correcto en tiempo de compilacion.
    // =====================================================================
    static class Calculadora {
        // ? Los tres metodos se llaman igual pero tienen firmas distintas.
        public int    sumar(int a, int b)             { return a + b; }
        public double sumar(double a, double b)       { return a + b; }
        public int    sumar(int a, int b, int c)      { return a + b + c; }
    }
}
