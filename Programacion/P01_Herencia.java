public class P01_Herencia {

    /*
     * =============================================================
     * TEORIA: HERENCIA EN JAVA
     * Basado en "Utilizacion avanzada de clases I. Herencia.pdf"
     * =============================================================
     * * Concepto clave:
     *   La HERENCIA permite que una clase hija reutilice los atributos
     *   y metodos de una clase padre.
     *   Palabra clave: extends
     *
     * * Beneficios:
     *   - Reutilizacion de codigo (no repetimos lo comun).
     *   - Jerarquia de clases (modelo el mundo real).
     *   - Base del polimorfismo (P02).
     *
     * * Sintaxis basica:
     *   class ClaseHija extends ClasePadre { ... }
     *
     * * Reglas importantes:
     *   - Java solo permite herencia SIMPLE (un solo padre).
     *   - El constructor del padre se llama con super(...).
     *   - super() debe ser la PRIMERA linea del constructor hijo.
     *   - Los atributos private del padre NO se heredan directamente
     *     (se accede con getters/setters o usando protected).
     *   - Con @Override sobreescribimos un metodo del padre.
     * =============================================================
     */

    // * Better Comments - guia visual para este archivo:
    // * Teoria y explicacion suave (verde).
    // ! Punto critico o regla importante (rojo).
    // ? Aclaracion o pregunta frecuente (azul).
    // TODO Alumno -> ejercicio guiado (naranja).
    // ✅ Solucion -> referencia comentada.

    public static void main(String[] args) {

        // ── PARTE 1: Crear objetos de distintas subclases ─────────────────
        // * Perro y Gato son Animal: heredan nombre y edad.
        Animal animal  = new Animal("Generico", 1);
        Perro  perro   = new Perro("Rex", 3, "Labrador");
        Gato   gato    = new Gato("Luna", 2, true);

        // ── PARTE 2: Metodos heredados y sobreescritos ────────────────────
        System.out.println("=== PRESENTACION ===");
        animal.presentarse();
        perro.presentarse();    // usa super.presentarse() + aniade raza
        gato.presentarse();

        // TODO mini 1: imprime System.out.println(perro) y System.out.println(gato)
        //              ¿que metodo llama Java internamente? ¿donde esta definido?

        // ── PARTE 3: Metodos exclusivos de cada subclase ──────────────────
        System.out.println("\n=== COMPORTAMIENTOS PROPIOS ===");
        perro.ladrar();
        gato.ronronear();
        // ! animal.ladrar() no compila: Animal no tiene ese metodo.

        // ── PARTE 4: Metodo sobreescrito con @Override ────────────────────
        System.out.println("\n=== SONIDOS (metodo sobreescrito) ===");
        animal.hacerSonido();
        perro.hacerSonido();    // @Override -> GUAU
        gato.hacerSonido();     // @Override -> MIAU

        // ── PARTE 5: super en metodo normal (no solo constructor) ─────────
        System.out.println("\n=== CUMPLEANOS ===");
        perro.cumplirAnio();    // llama a super.cumplirAnio() + imprime raza
        // TODO mini 2: llama tambien a gato.cumplirAnio() y observa la diferencia.
        //              Gato NO sobreescribe cumplirAnio() -> usa el del padre tal cual.

        // TODO Alumno:
        // 1) Crea una subclase "Pez" que extienda Animal.
        //    Agrega atributo boolean aguaDulce.
        //    Sobreescribe hacerSonido() -> "... (silencio)".
        //    Crea un Pez y llama a presentarse() y hacerSonido().
        // 2) Agrega un metodo nadar() exclusivo de Pez.
        // 3) En presentarse() del Pez, usa super.presentarse()
        //    y luego imprime si es de agua dulce o salada.

        // ✅ Solucion orientativa (comentada)
        // Pez pez = new Pez("Nemo", 1, true);
        // pez.presentarse();
        // pez.hacerSonido();
        // pez.nadar();
    }

    // =====================================================================
    // * CLASE PADRE: Animal
    // * Define la estructura comun a todos los animales.
    // =====================================================================
    static class Animal {

        // * protected: visible en esta clase Y en todas sus subclases.
        // ! Si fuera private, las subclases NO podrian acceder directamente.
        protected String nombre;
        protected int    edad;

        // * Constructor del padre.
        public Animal(String nombre, int edad) {
            this.nombre = nombre;
            this.edad   = (edad >= 0) ? edad : 0;
        }

        // * Metodo que las subclases pueden sobreescribir (@Override).
        public void hacerSonido() {
            System.out.println(nombre + " emite un sonido generico.");
            // TODO mini 3: añade aqui una segunda linea que imprima "  (tipo: Animal)"
            //              Las subclases que hagan @Override no veran esta linea extra.
        }

        // * Metodo que se hereda tal cual si la subclase no lo sobreescribe.
        public void presentarse() {
            System.out.println("Soy " + nombre + " y tengo " + edad + " anio(s).");
        }

        // * Metodo que las subclases pueden ampliar con super.cumplirAnio().
        public void cumplirAnio() {
            edad++;
            System.out.println(nombre + " ahora tiene " + edad + " anio(s).");
        }

        @Override
        public String toString() {
            return "Animal{nombre='" + nombre + "', edad=" + edad + "}";
        }
    }

    // =====================================================================
    // * SUBCLASE: Perro extends Animal
    // * Hereda nombre y edad, anade raza y comportamientos propios.
    // =====================================================================
    static class Perro extends Animal {

        private String raza;

        // ! La primera linea del constructor hijo DEBE ser super(...).
        // ! Si no lo escribimos, Java intenta llamar a super() sin argumentos
        //   y falla si el padre no tiene constructor vacio.
        public Perro(String nombre, int edad, String raza) {
            super(nombre, edad);   // inicializa los atributos del padre
            this.raza = raza;
        }

        // * @Override: indicamos que sobreescribimos el metodo del padre.
        // ? Si escribes mal el nombre del metodo sin @Override, no da error
        //   pero creas un metodo nuevo en lugar de sobreescribir. Usa siempre @Override.
        @Override
        public void hacerSonido() {
            System.out.println(nombre + " dice: GUAU GUAU!");
        }

        // * Ampliamos presentarse() reutilizando el del padre con super.
        @Override
        public void presentarse() {
            super.presentarse();                            // ejecuta el de Animal
            System.out.println("  -> Raza: " + raza);      // y anade info de Perro
        }

        // * Metodo exclusivo de Perro (no existe en Animal).
        public void ladrar() {
            System.out.println(nombre + " ladra: Woof! Woof!");
        }

        // * Ampliamos cumplirAnio() con informacion extra.
        @Override
        public void cumplirAnio() {
            super.cumplirAnio();
            System.out.println("  (Raza " + raza + " cumple un anio mas)");
        }

        @Override
        public String toString() {
            return "Perro{nombre='" + nombre + "', edad=" + edad + ", raza='" + raza + "'}";
        }
    }

    // =====================================================================
    // * SUBCLASE: Gato extends Animal
    // =====================================================================
    static class Gato extends Animal {

        private boolean esInterior;

        public Gato(String nombre, int edad, boolean esInterior) {
            super(nombre, edad);
            this.esInterior = esInterior;
        }

        @Override
        public void hacerSonido() {
            System.out.println(nombre + " dice: MIAU!");
        }

        @Override
        public void presentarse() {
            super.presentarse();
            System.out.println("  -> Es de interior: " + (esInterior ? "si" : "no"));
        }

        // * Metodo exclusivo de Gato.
        public void ronronear() {
            System.out.println(nombre + " ronronea: prrrrr...");
        }

        @Override
        public String toString() {
            return "Gato{nombre='" + nombre + "', edad=" + edad + ", interior=" + esInterior + "}";
        }
    }
}
