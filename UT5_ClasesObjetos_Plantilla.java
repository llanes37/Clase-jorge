/*
 * ==========================================================================================
 *                 UT5 - CLASES Y OBJETOS (PLANTILLA SENCILLA PARA EL ALUMNO)
 * ==========================================================================================
 *
 * Objetivo:
 * - Practicar la creación de clases y objetos.
 * - Trabajar con atributos, constructores, getters/setters y métodos simples.
 *
 * Instrucciones:
 * - Lee los comentarios `TODO`.
 * - Rellena los huecos y completa la lógica.
 * - Prueba todo desde el `main`.
 *
 * Nota:
 * - Esta plantilla compila, pero su comportamiento NO es correcto hasta que completes los TODO.
 */

public class UT5_ClasesObjetos_Plantilla {
    public static void main(String[] args) {
        /*
         * TODO 1) Crea un objeto `Persona` con un nombre y una edad.
         * TODO 2) Llama a `mostrarInformacion()`.
         * TODO 3) Usa setters para cambiar nombre y/o edad y vuelve a mostrar la información.
         *
         * Pista (descomenta y completa):
         * Persona p1 = new Persona("__________", ____ );
         * p1.mostrarInformacion();
         * p1.setNombre("__________");
         * p1.setEdad(____);
         * p1.mostrarInformacion();
         */

        /*
         * TODO 4) Crea un objeto `Coche` con marca, modelo y velocidad inicial.
         * TODO 5) Llama a `acelerar()` y `frenar()` varias veces.
         * TODO 6) Muestra la información del coche.
         *
         * Pista (descomenta y completa):
         * Coche c1 = new Coche("__________", "__________", ____ );
         * c1.mostrarInformacion();
         * c1.acelerar();
         * c1.frenar();
         * c1.mostrarInformacion();
         */
    }
}

// ==========================================================================================
// CLASE 1: Persona
// ==========================================================================================
class Persona {
    // TODO: Declara los atributos (encapsulados) de Persona
    // private String nombre;
    // private int edad;

    // TODO: Crea un constructor con parámetros para inicializar los atributos
    public Persona(String nombre, int edad) {
        // TODO: Inicializa los atributos con `this`
        // this.nombre = nombre;
        // this.edad = edad;
    }

    // TODO: Crea getters y setters
    public String getNombre() {
        // TODO: Devuelve el nombre real
        return null;
    }

    public void setNombre(String nombre) {
        // TODO: Asigna el nombre (puedes validar que no esté vacío)
        // this.nombre = nombre;
    }

    public int getEdad() {
        // TODO: Devuelve la edad real
        return 0;
    }

    public void setEdad(int edad) {
        /*
         * TODO: Asigna la edad con validación:
         * - Si edad es válida, se asigna.
         * - Si no, muestra un mensaje de aviso.
         */
        // if (__________) { this.edad = edad; } else { System.out.println("__________"); }
    }

    public void mostrarInformacion() {
        // TODO: Muestra por pantalla el nombre y la edad con un formato claro
        // System.out.println("Nombre: " + ________ + " | Edad: " + ________);
    }
}

// ==========================================================================================
// CLASE 2: Coche
// ==========================================================================================
class Coche {
    // TODO: Declara los atributos (encapsulados) de Coche
    // private String marca;
    // private String modelo;
    // private int velocidad;

    // TODO: Crea un constructor con parámetros
    public Coche(String marca, String modelo, int velocidadInicial) {
        // TODO: Inicializa los atributos (velocidad = velocidadInicial)
        // this.marca = marca;
        // this.modelo = modelo;
        // this.velocidad = velocidadInicial;
    }

    // TODO: Crea getters y setters (si los necesitas)
    public int getVelocidad() {
        // TODO: Devuelve la velocidad real
        return 0;
    }

    public void setVelocidad(int velocidad) {
        /*
         * TODO: Valida que la velocidad no sea negativa.
         * - Si es negativa, corrige a 0 o muestra un aviso (decide una opción).
         */
        // this.velocidad = ________;
    }

    public void acelerar() {
        /*
         * TODO: Incrementa la velocidad (por ejemplo, +10).
         * - Usa `setVelocidad(...)` si has puesto validación ahí.
         */
        // setVelocidad(getVelocidad() + ____ );
    }

    public void frenar() {
        /*
         * TODO: Disminuye la velocidad (por ejemplo, -5) sin dejarla negativa.
         * - Usa `setVelocidad(...)` si has puesto validación ahí.
         */
        // setVelocidad(getVelocidad() - ____ );
    }

    public void mostrarInformacion() {
        // TODO: Muestra marca, modelo y velocidad
        // System.out.println("Marca: " + ________ + " | Modelo: " + ________ + " | Velocidad: " + ________);
    }
}

