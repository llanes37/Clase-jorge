/******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  FECHA: 2026
 *  UNIDAD 12: POO + AGREGACION + COLECCIONES (BASE)
 *  EJERCICIO DIDACTICO: RADIO FM GUIADA PASO A PASO
 ******************************************************************************************/

import java.util.Objects;
import java.util.Scanner;

public class UT12_Radio_EjercicioDidactico {

    // * Recurso compartido para leer teclado en toda la aplicacion
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // * BLOQUE 1: Creamos objetos base del ejercicio
        // ? Especificaciones y Bateria existen por separado.
        // ? Luego se "inyectan" en Radio: esto es agregacion.
        Especificaciones esp = new Especificaciones("Sony", "ICF-P27", 1001L);
        Bateria bat = new Bateria("Duracell", 60);
        Radio radio = new Radio(esp, bat); // Frecuencias por defecto 87.5 - 108.0

        // * BLOQUE 2: Bucle de menu
        // ? TEORIA: practicamos objetos por comportamiento (metodos),
        // ? no accediendo a atributos directamente.
        // ? Idea clave para clase: encapsulacion.
        //
        // ! TAREA ALUMNO:
        // ! 1) Anade opcion para recargar bateria.
        // ! 2) Impide cambiar frecuencia si la radio esta apagada.
        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt("Elige opcion: ");

            switch (opcion) {
                case 1:
                    // ? El boton alterna: encendida -> apagada, apagada -> encendida
                    System.out.println(radio.botonEncendido());
                    break;
                case 2:
                    // ? Sube en pasos de 0.5 respetando maximo
                    System.out.println("Nueva frecuencia: " + radio.subirFrecuencia());
                    break;
                case 3:
                    // ? Baja en pasos de 0.5 respetando minimo
                    System.out.println("Nueva frecuencia: " + radio.bajarFrecuencia());
                    break;
                case 4:
                    // * Caso de validacion de datos + regla de negocio
                    double f = leerDouble("Frecuencia a establecer: ");
                    boolean ok = radio.establecerFrecuencia(f);
                    System.out.println(ok ? "Frecuencia establecida." : "Frecuencia fuera de rango.");
                    break;
                case 5:
                    // ! Ojo didactico: consumir no permite dejar bateria negativa
                    int consumo = leerInt("Cuanta bateria consumir? ");
                    radio.getBateria().consumir(consumo);
                    System.out.println("Bateria actual: " + radio.getBateria().getPorcentajeCarga() + "%");
                    break;
                case 6:
                    // ? toString muestra estado completo del objeto
                    System.out.println(radio);
                    break;
                case 0:
                    System.out.println("Fin de la practica.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // * MENU PRINCIPAL
    // ? Esta salida sirve para guiar la explicacion en clase paso a paso
    static void mostrarMenu() {
        System.out.println("\n=== RADIO FM - PRACTICA DIDACTICA ===");
        System.out.println("1. Boton encendido/apagado");
        System.out.println("2. Subir frecuencia (+0.5)");
        System.out.println("3. Bajar frecuencia (-0.5)");
        System.out.println("4. Establecer frecuencia");
        System.out.println("5. Consumir bateria");
        System.out.println("6. Mostrar estado completo");
        System.out.println("0. Salir");
    }

    // * LECTURA SEGURA DE ENTEROS
    // ? Mientras no llegue un entero, seguimos pidiendo dato
    // ? Evita que el programa falle por InputMismatchException
    static int leerInt(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Valor invalido. Introduce un entero: ");
            sc.next();
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    // * LECTURA SEGURA DE DECIMALES
    // ? Igual que leerInt, pero para double
    static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.print("Valor invalido. Introduce un decimal: ");
            sc.next();
        }
        double n = sc.nextDouble();
        sc.nextLine();
        return n;
    }

    /******************************************************************************************
     * CLASE ESPECIFICACIONES
     * - Atributos: marca, modelo, numeroSerie
     * - Idea didactica: encapsulacion + toString
     ******************************************************************************************/
    static class Especificaciones {
        // * Atributos privados: encapsulacion
        private String marca;
        private String modelo;
        private long numeroSerie;

        public Especificaciones(String marca, String modelo, long numeroSerie) {
            this.marca = marca;
            this.modelo = modelo;
            this.numeroSerie = numeroSerie;
        }

        public long getNumeroSerie() {
            // ? Lo usamos para igualdad de radios (equals/hashCode)
            return numeroSerie;
        }

        @Override
        public String toString() {
            return "Marca=" + marca + ", Modelo=" + modelo + ", Serie=" + numeroSerie;
        }
    }

    /******************************************************************************************
     * CLASE BATERIA
     * - Atributos: marca, porcentajeCarga
     * - Metodo clave: consumir(cantidad)
     ******************************************************************************************/
    static class Bateria {
        private String marca;
        private int porcentajeCarga;

        public Bateria(String marca, int porcentajeCarga) {
            this.marca = marca;
            // ? Proteccion de rango: carga siempre entre 0 y 100
            this.porcentajeCarga = Math.max(0, Math.min(100, porcentajeCarga));
        }

        public String getMarca() {
            return marca;
        }

        public int getPorcentajeCarga() {
            return porcentajeCarga;
        }

        public void consumir(int cantidad) {
            // ? TEORIA: nunca permitimos estados imposibles (carga negativa).
            if (cantidad < 0) {
                // ! Si llega un valor negativo, ignoramos para no "recargar" por error
                return;
            }
            porcentajeCarga -= cantidad;
            if (porcentajeCarga < 0) {
                porcentajeCarga = 0;
            }
        }

        @Override
        public String toString() {
            return "Bateria{marca=" + marca + ", carga=" + porcentajeCarga + "%}";
        }
    }

    /******************************************************************************************
     * CLASE RADIO (AGREGACION)
     * - Una Radio tiene Especificaciones y Bateria
     * - Reglas del ejercicio:
     *   1) Nace apagada.
     *   2) Frecuencia actual empieza en la minima.
     *   3) Solo enciende si la bateria tiene carga.
     *   4) Equals/hashCode por numero de serie.
     ******************************************************************************************/
    static class Radio {
        // * Agregacion: Radio no "crea" estos objetos, los recibe hechos
        private Especificaciones especificaciones;
        private double frecuenciaMinima;
        private double frecuenciaMaxima;
        private double frecuenciaActual;
        private boolean encendida;
        private Bateria bateria;

        // * Constructor completo
        // ? Regla: siempre arranca apagada y en frecuencia minima
        public Radio(Especificaciones especificaciones, Bateria bateria,
                     double frecuenciaMinima, double frecuenciaMaxima) {
            this.especificaciones = especificaciones;
            this.bateria = bateria;
            this.frecuenciaMinima = frecuenciaMinima;
            this.frecuenciaMaxima = frecuenciaMaxima;
            this.frecuenciaActual = frecuenciaMinima;
            this.encendida = false;
        }

        // * Constructor simplificado (rango FM estandar)
        // ? Sobrecarga de constructores: reutiliza el constructor completo
        public Radio(Especificaciones especificaciones, Bateria bateria) {
            this(especificaciones, bateria, 87.5, 108.0);
        }

        public Especificaciones getEspecificaciones() {
            return especificaciones;
        }

        public Bateria getBateria() {
            return bateria;
        }

        public double getFrecuenciaActual() {
            return frecuenciaActual;
        }

        public boolean isEncendida() {
            return encendida;
        }

        public String botonEncendido() {
            // ? TEORIA: simula un boton real, alternando estado.
            if (encendida) {
                encendida = false;
                return "Radio apagada.";
            }
            if (bateria.getPorcentajeCarga() <= 0) {
                // ! Regla de negocio: no puede encender sin bateria
                return "No se puede encender: bateria agotada.";
            }
            encendida = true;
            return "Radio encendida.";
        }

        public double subirFrecuencia() {
            // ? Math.min evita pasarnos del limite superior
            frecuenciaActual = Math.min(frecuenciaMaxima, frecuenciaActual + 0.5);
            return frecuenciaActual;
        }

        public double bajarFrecuencia() {
            // ? Math.max evita bajar del limite inferior
            frecuenciaActual = Math.max(frecuenciaMinima, frecuenciaActual - 0.5);
            return frecuenciaActual;
        }

        public boolean establecerFrecuencia(double frecuencia) {
            // ? TEORIA: validacion de rango antes de asignar.
            if (frecuencia >= frecuenciaMinima && frecuencia <= frecuenciaMaxima) {
                frecuenciaActual = frecuencia;
                return true;
            }
            return false;
        }

        @Override
        public boolean equals(Object obj) {
            // * ESTRATEGIA DE COMPARACION:
            // ? Dos radios son iguales si su numero de serie coincide
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Radio otra = (Radio) obj;
            return this.especificaciones.getNumeroSerie() == otra.especificaciones.getNumeroSerie();
        }

        @Override
        public int hashCode() {
            // ? Debe ser coherente con equals (mismo criterio: numeroSerie)
            return Objects.hash(especificaciones.getNumeroSerie());
        }

        @Override
        public String toString() {
            return "Radio{"
                    + "esp=" + especificaciones
                    + ", fMin=" + frecuenciaMinima
                    + ", fMax=" + frecuenciaMaxima
                    + ", fActual=" + frecuenciaActual
                    + ", encendida=" + encendida
                    + ", " + bateria
                    + "}";
        }
    }
}
