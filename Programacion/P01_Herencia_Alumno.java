public class P01_Herencia_Alumno {

    /*
     * =============================================================
     * PLANTILLA ALUMNO - HERENCIA
     * =============================================================
     * * Instrucciones:
     *   1. Lee los comentarios de teoria antes de cada TODO.
     *   2. Implementa los TODO en orden numerico (1, 2, 3...).
     *   3. Ejecuta el programa despues de cada TODO para ver avance.
     *   4. Cuando funcione todo, compara con P01_Herencia.java.
     *
     * * Palabras clave que vas a usar:
     *   extends, super(), @Override, protected
     * =============================================================
     */

    // * Teoria breve:
    // * class Hija extends Padre  -> Hija hereda todo lo de Padre
    // * super(args)               -> llama al constructor del padre (PRIMERA linea)
    // * @Override                 -> sobreescribe un metodo del padre
    // * protected                 -> visible en la clase Y en sus subclases

    public static void main(String[] args) {
        // * El main esta completo. Implementa las clases para que funcione.

        Animal animal = new Animal("Generico", 1);
        Perro  perro  = new Perro("Rex", 3, "Labrador");
        Gato   gato   = new Gato("Luna", 2, true);

        System.out.println("=== PRESENTACION ===");
        animal.presentarse();
        perro.presentarse();
        gato.presentarse();

        System.out.println("\n=== COMPORTAMIENTOS PROPIOS ===");
        perro.ladrar();
        gato.ronronear();

        System.out.println("\n=== SONIDOS (metodo sobreescrito) ===");
        animal.hacerSonido();
        perro.hacerSonido();
        gato.hacerSonido();

        System.out.println("\n=== CUMPLEANOS ===");
        perro.cumplirAnio();
    }

    // =====================================================================
    // TODO Alumno 1: Implementa la clase Animal con sus atributos y metodos.
    // =====================================================================
    static class Animal {

        // * protected: visible aqui Y en las subclases Perro y Gato.
        // TODO 1a: declara dos atributos protected: nombre (String) y edad (int)

        // TODO 1b: implementa el constructor Animal(String nombre, int edad)
        //          Si edad < 0, asigna 0. Pista: usa operador ternario o if.
        public Animal(String nombre, int edad) {
            // escribe el cuerpo aqui
        }

        // TODO 1c: implementa hacerSonido()
        //          Debe imprimir: "[nombre] emite un sonido generico."
        public void hacerSonido() {
            // escribe el cuerpo aqui
        }

        // TODO 1d: implementa presentarse()
        //          Debe imprimir: "Soy [nombre] y tengo [edad] anio(s)."
        public void presentarse() {
            // escribe el cuerpo aqui
        }

        // TODO 1e: implementa cumplirAnio()
        //          Incrementa edad en 1 e imprime: "[nombre] ahora tiene [edad] anio(s)."
        public void cumplirAnio() {
            // escribe el cuerpo aqui
        }
    }

    // =====================================================================
    // TODO Alumno 2: Implementa Perro que extiende Animal.
    // =====================================================================
    static class Perro extends Animal {

        // TODO 2a: declara el atributo private raza (String)

        // * La primera linea del constructor hijo DEBE ser super(...).
        // TODO 2b: implementa el constructor Perro(String nombre, int edad, String raza)
        //          Primera linea: super(nombre, edad);  luego asigna raza.
        public Perro(String nombre, int edad, String raza) {
            // PRIMERA LINEA: super(nombre, edad);
            // luego asigna this.raza = raza;
        }

        // * @Override indica que sobreescribimos el metodo del padre.
        // TODO 2c: sobreescribe hacerSonido() -> imprime "[nombre]: GUAU GUAU!"
        @Override
        public void hacerSonido() {
            // escribe el cuerpo aqui
        }

        // TODO 2d: sobreescribe presentarse() usando super.presentarse()
        //          Luego imprime: "  -> Raza: [raza]"
        @Override
        public void presentarse() {
            // PRIMERA LINEA: super.presentarse();
            // luego imprime la raza
        }

        // TODO 2e: implementa ladrar() -> imprime "[nombre] ladra: Woof! Woof!"
        public void ladrar() {
            // escribe el cuerpo aqui
        }

        // TODO 2f: sobreescribe cumplirAnio() usando super.cumplirAnio()
        //          Luego imprime: "  (Raza [raza] cumple un anio mas)"
        @Override
        public void cumplirAnio() {
            // PRIMERA LINEA: super.cumplirAnio();
        }
    }

    // =====================================================================
    // TODO Alumno 3: Implementa Gato que extiende Animal.
    // =====================================================================
    static class Gato extends Animal {

        // TODO 3a: declara el atributo private esInterior (boolean)

        // TODO 3b: implementa el constructor Gato(String nombre, int edad, boolean esInterior)
        public Gato(String nombre, int edad, boolean esInterior) {
            // PRIMERA LINEA: super(nombre, edad);
        }

        // TODO 3c: sobreescribe hacerSonido() -> imprime "[nombre]: MIAU!"
        @Override
        public void hacerSonido() {
            // escribe el cuerpo aqui
        }

        // TODO 3d: sobreescribe presentarse() con super.presentarse()
        //          Luego imprime: "  -> Es de interior: [si/no]"
        @Override
        public void presentarse() {
            // PRIMERA LINEA: super.presentarse();
        }

        // TODO 3e: implementa ronronear() -> imprime "[nombre] ronronea: prrrrr..."
        public void ronronear() {
            // escribe el cuerpo aqui
        }
    }

    // =====================================================================
    // TODO Alumno 4 (EXTRA): Crea una subclase Pez extends Animal.
    //   - Atributo boolean aguaDulce.
    //   - Constructor Pez(String nombre, int edad, boolean aguaDulce).
    //   - sobreescribe hacerSonido() -> "... (silencio)"
    //   - sobreescribe presentarse() con super y tipo de agua
    //   - metodo propio nadar()
    //   Luego crea un Pez en el main y pruebalo.
    // =====================================================================
}
