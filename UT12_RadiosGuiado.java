/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 12: POO + AGREGACIÓN + COLECCIONES
 *  📻 EJERCICIO GUIADO: GESTIÓN DE RADIOS FM
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Objects;

/**
 * ╔═══════════════════════════════════════════════════════════════════════════════════════╗
 * ║                        📋 ENUNCIADO - CLASE ESPECIFICACIONES                          ║
 * ╠═══════════════════════════════════════════════════════════════════════════════════════╣
 * ║  La clase Especificaciones tiene los atributos:                                       ║
 * ║    • Marca de tipo texto.                                                             ║
 * ║    • Modelo de tipo texto.                                                            ║
 * ║    • Numero de serie de tipo numérico.                                                ║
 * ║                                                                                       ║
 * ║  Tiene también:                                                                       ║
 * ║    • Un constructor para inicializar sus atributos.                                   ║
 * ║    • Un método get para el número de serie.                                           ║
 * ║    • El método toString para mostrar sus valores.                                     ║
 * ╚═══════════════════════════════════════════════════════════════════════════════════════╝
 */
class Especificaciones {
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Marca de tipo texto"
    // ────────────────────────────────────────────────────────────────
    private String marca;
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Modelo de tipo texto"
    // ────────────────────────────────────────────────────────────────
    private String modelo;
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Numero de serie de tipo numérico"
    // ────────────────────────────────────────────────────────────────
    private long numeroSerie;

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Un constructor para inicializar sus atributos"
    // ────────────────────────────────────────────────────────────────
    public Especificaciones(String marca, String modelo, long numeroSerie) {
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
    }

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Un método get para el número de serie"
    // ────────────────────────────────────────────────────────────────
    public long getNumeroSerie() {
        return numeroSerie;
    }

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "El método toString para mostrar sus valores"
    // ────────────────────────────────────────────────────────────────
    public String toString() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", NumSerie: " + numeroSerie;
    }
}


/**
 * ╔═══════════════════════════════════════════════════════════════════════════════════════╗
 * ║                           📋 ENUNCIADO - CLASE BATERIA                                ║
 * ╠═══════════════════════════════════════════════════════════════════════════════════════╣
 * ║  La clase Batería tiene los atributos:                                                ║
 * ║    • Marca de tipo texto.                                                             ║
 * ║    • Porcentaje de carga de tipo entero.                                              ║
 * ║                                                                                       ║
 * ║  Los métodos de la clase son:                                                         ║
 * ║    • Método consumir que recibe la cantidad a restar al porcentaje de carga.          ║
 * ║    • Constructor para inicializar sus atributos.                                      ║
 * ║    • Métodos get.                                                                     ║
 * ║    • Método toString para mostrar sus valores.                                        ║
 * ╚═══════════════════════════════════════════════════════════════════════════════════════╝
 */
class Bateria {
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Marca de tipo texto"
    // ────────────────────────────────────────────────────────────────
    private String marca;
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Porcentaje de carga de tipo entero"
    // ────────────────────────────────────────────────────────────────
    private int porcentajeCarga;

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Constructor para inicializar sus atributos"
    // ────────────────────────────────────────────────────────────────
    public Bateria(String marca, int porcentajeCarga) {
        this.marca = marca;
        this.porcentajeCarga = porcentajeCarga;
    }

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Métodos get"
    // ────────────────────────────────────────────────────────────────
    public String getMarca() {
        return marca;
    }
    
    public int getPorcentajeCarga() {
        return porcentajeCarga;
    }

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Método consumir que recibe la cantidad a restar 
    //             al porcentaje de carga de la batería"
    // ────────────────────────────────────────────────────────────────
    public void consumir(int cantidad) {
        porcentajeCarga = porcentajeCarga - cantidad;
        // Nunca puede bajar de 0
        if (porcentajeCarga < 0) {
            porcentajeCarga = 0;
        }
    }

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Método toString para mostrar sus valores"
    // ────────────────────────────────────────────────────────────────
    public String toString() {
        return "Bateria: " + marca + ", Carga: " + porcentajeCarga + "%";
    }
}


/**
 * ╔═══════════════════════════════════════════════════════════════════════════════════════╗
 * ║                             📋 ENUNCIADO - CLASE RADIO                                ║
 * ╠═══════════════════════════════════════════════════════════════════════════════════════╣
 * ║  Implementa la clase Radio que permite controlar la sintonización digital de          ║
 * ║  emisoras FM. Tendrá los atributos:                                                   ║
 * ║                                                                                       ║
 * ║    • Datos técnicos de tipo Especificaciones. Implementar como agregación.            ║
 * ║    • Frecuencia Mínima y Frecuencia Máxima es un número con decimales que             ║
 * ║      delimita el rango de frecuencias en MHz que puede sintonizar la radio.           ║
 * ║    • Frecuencia Actual es un número con decimales que indica la frecuencia            ║
 * ║      en MHz en la que se encuentra el sintonizador en un momento dado.                ║
 * ║    • Encendida. Indica si la radio está o no encendida.                               ║
 * ║    • Batería. De tipo batería. Implementar como agregación.                           ║
 * ║                                                                                       ║
 * ║  CONSTRUCTORES - Tener en cuenta:                                                     ║
 * ║    • Es obligatorio que siempre se especifiquen los datos técnicos y la batería.      ║
 * ║    • Siempre que se crea una radio, está apagada y la Frecuencia Actual es la         ║
 * ║      mínima que puede sintonizar esa radio.                                           ║
 * ║    • Si no se indican la Frecuencia Mínima y Frecuencia Máxima de la radio a crear,   ║
 * ║      sus valores son de 87,5 y 108 respectivamente.                                   ║
 * ║                                                                                       ║
 * ║  MÉTODOS:                                                                             ║
 * ║    • Métodos get para todos los atributos.                                            ║
 * ║    • Método toString que devuelve una cadena de texto con el valor de todos los       ║
 * ║      atributos.                                                                       ║
 * ║    • Método equals. Dos radios son iguales si tienen el mismo número de serie.        ║
 * ║    • Método Botón de encendido. Simula el comportamiento del botón de encendido       ║
 * ║      de la radio y la enciende si previamente estaba apagada o la apaga si antes      ║
 * ║      estaba encendida. Devuelve información que indica el estado actual de la radio   ║
 * ║      (encendida o apagada). La radio sólo se puede encender si la batería no se       ║
 * ║      ha agotado.                                                                      ║
 * ║    • Métodos subir frecuencia y bajar frecuencia que sube o baja la frecuencia        ║
 * ║      de la radio en 0,5 MHz respectivamente. Ten en cuenta que una radio no puede     ║
 * ║      sintonizar una frecuencia inferior o superior a los límites establecidos.        ║
 * ║      Los métodos devuelven la frecuencia actual que tiene sintonizada la radio.       ║
 * ║    • Método establecer frecuencia que fija en la radio la frecuencia que el           ║
 * ║      usuario decida (dentro de los límites). Devuelve información indicando si        ║
 * ║      se pudo o no establecer la frecuencia.                                           ║
 * ╚═══════════════════════════════════════════════════════════════════════════════════════╝
 */
class Radio {
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Datos técnicos de tipo Especificaciones. 
    //             Implementar como agregación."
    // 
    // EXPLICACIÓN: Agregación significa que Radio TIENE un objeto
    //              Especificaciones (relación "tiene-un")
    // ────────────────────────────────────────────────────────────────
    private Especificaciones especificaciones;
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Frecuencia Mínima y Frecuencia Máxima es un número 
    //             con decimales que delimita el rango de frecuencias 
    //             en MHz que puede sintonizar la radio."
    // ────────────────────────────────────────────────────────────────
    private double frecuenciaMinima;
    private double frecuenciaMaxima;
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Frecuencia Actual es un número con decimales que 
    //             indica la frecuencia en MHz en la que se encuentra 
    //             el sintonizador en un momento dado."
    // ────────────────────────────────────────────────────────────────
    private double frecuenciaActual;
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Encendida. Indica si la radio está o no encendida."
    // ────────────────────────────────────────────────────────────────
    private boolean encendida;
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Batería. De tipo batería. 
    //             Implementar como agregación."
    // ────────────────────────────────────────────────────────────────
    private Bateria bateria;

    // ════════════════════════════════════════════════════════════════
    //                        CONSTRUCTORES
    // ════════════════════════════════════════════════════════════════

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Es obligatorio que siempre se especifiquen los 
    //             datos técnicos y la batería."
    //            "Siempre que se crea una radio, está apagada y la 
    //             Frecuencia Actual es la mínima que puede sintonizar"
    //
    // Constructor COMPLETO: recibe especificaciones, bateria, 
    //                       frecuenciaMinima y frecuenciaMaxima
    // ────────────────────────────────────────────────────────────────
    public Radio(Especificaciones especificaciones, Bateria bateria, 
                 double frecuenciaMinima, double frecuenciaMaxima) {
        this.especificaciones = especificaciones;
        this.bateria = bateria;
        this.frecuenciaMinima = frecuenciaMinima;
        this.frecuenciaMaxima = frecuenciaMaxima;
        this.frecuenciaActual = frecuenciaMinima;  // Empieza en la minima
        this.encendida = false;                     // Empieza apagada
    }

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Si no se indican la Frecuencia Mínima y Frecuencia 
    //             Máxima de la radio a crear, sus valores son de 
    //             87,5 y 108 respectivamente."
    //
    // Constructor SIMPLIFICADO: solo recibe especificaciones y bateria
    //                           usa frecuencias por defecto 87.5 y 108
    // ────────────────────────────────────────────────────────────────
    public Radio(Especificaciones especificaciones, Bateria bateria) {
        this.especificaciones = especificaciones;
        this.bateria = bateria;
        this.frecuenciaMinima = 87.5;              // Valor por defecto
        this.frecuenciaMaxima = 108.0;             // Valor por defecto
        this.frecuenciaActual = 87.5;              // Empieza en la minima
        this.encendida = false;                     // Empieza apagada
    }

    // ════════════════════════════════════════════════════════════════
    //                      MÉTODOS GET
    // ════════════════════════════════════════════════════════════════

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Métodos get para todos los atributos"
    // ────────────────────────────────────────────────────────────────
    public Especificaciones getEspecificaciones() {
        return especificaciones;
    }
    
    public Bateria getBateria() {
        return bateria;
    }
    
    public double getFrecuenciaMinima() {
        return frecuenciaMinima;
    }
    
    public double getFrecuenciaMaxima() {
        return frecuenciaMaxima;
    }
    
    public double getFrecuenciaActual() {
        return frecuenciaActual;
    }
    
    public boolean isEncendida() {
        return encendida;
    }

    // ════════════════════════════════════════════════════════════════
    //                      MÉTODO toString
    // ════════════════════════════════════════════════════════════════

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Método toString que devuelve una cadena de texto 
    //             con el valor de todos los atributos"
    // ────────────────────────────────────────────────────────────────
    public String toString() {
        String estado;
        if (encendida) {
            estado = "ENCENDIDA";
        } else {
            estado = "APAGADA";
        }
        return especificaciones.toString() + " | " + 
               bateria.toString() + " | " +
               "Freq: " + frecuenciaActual + " MHz (" + 
               frecuenciaMinima + "-" + frecuenciaMaxima + ") | " + 
               estado;
    }

    // ════════════════════════════════════════════════════════════════
    //                   MÉTODO equals y hashCode
    // ════════════════════════════════════════════════════════════════

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Método equals. Dos radios son iguales si tienen 
    //             el mismo número de serie."
    //
    // IMPORTANTE: Si usamos HashSet, necesitamos equals y hashCode
    //             para que el HashSet sepa cuando dos radios son iguales
    // ────────────────────────────────────────────────────────────────
    public boolean equals(Object obj) {
        // Si es el mismo objeto en memoria, son iguales
        if (this == obj) {
            return true;
        }
        // Si es null o de otra clase, no son iguales
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        // Comparamos el numero de serie
        Radio otraRadio = (Radio) obj;
        long miSerie = this.especificaciones.getNumeroSerie();
        long otraSerie = otraRadio.especificaciones.getNumeroSerie();
        return miSerie == otraSerie;
    }
    
    // hashCode debe coincidir con equals (mismo numero serie = mismo hash)
    public int hashCode() {
        return Objects.hash(especificaciones.getNumeroSerie());
    }

    // ════════════════════════════════════════════════════════════════
    //                   MÉTODO BOTÓN DE ENCENDIDO
    // ════════════════════════════════════════════════════════════════

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Método Botón de encendido. Simula el comportamiento 
    //             del botón de encendido de la radio y la enciende si 
    //             previamente estaba apagada o la apaga si antes estaba 
    //             encendida. Devuelve información que indica el estado 
    //             actual de la radio (encendida o apagada). 
    //             La radio sólo se puede encender si la batería no se 
    //             ha agotado."
    // ────────────────────────────────────────────────────────────────
    public String botonEncendido() {
        // Si esta encendida, la apagamos
        if (encendida == true) {
            encendida = false;
            return "La radio se ha APAGADO";
        } 
        // Si esta apagada, intentamos encenderla
        else {
            // Solo podemos encender si hay bateria
            if (bateria.getPorcentajeCarga() > 0) {
                encendida = true;
                return "La radio se ha ENCENDIDO";
            } else {
                return "No se puede encender: BATERIA AGOTADA";
            }
        }
    }

    // ════════════════════════════════════════════════════════════════
    //               MÉTODOS SUBIR Y BAJAR FRECUENCIA
    // ════════════════════════════════════════════════════════════════

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Métodos subir frecuencia y bajar frecuencia que sube 
    //             o baja la frecuencia de la radio en 0,5 MHz 
    //             respectivamente. Ten en cuenta que una radio no puede 
    //             sintonizar una frecuencia inferior o superior a los 
    //             límites establecidos. Los métodos devuelven la 
    //             frecuencia actual que tiene sintonizada la radio."
    // ────────────────────────────────────────────────────────────────
    public double subirFrecuencia() {
        // Subimos 0.5, pero sin pasar del maximo
        double nuevaFrecuencia = frecuenciaActual + 0.5;
        if (nuevaFrecuencia <= frecuenciaMaxima) {
            frecuenciaActual = nuevaFrecuencia;
        }
        return frecuenciaActual;
    }
    
    public double bajarFrecuencia() {
        // Bajamos 0.5, pero sin bajar del minimo
        double nuevaFrecuencia = frecuenciaActual - 0.5;
        if (nuevaFrecuencia >= frecuenciaMinima) {
            frecuenciaActual = nuevaFrecuencia;
        }
        return frecuenciaActual;
    }

    // ════════════════════════════════════════════════════════════════
    //                 MÉTODO ESTABLECER FRECUENCIA
    // ════════════════════════════════════════════════════════════════

    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Método establecer frecuencia que fija en la radio 
    //             la frecuencia que el usuario decida (dentro de los 
    //             límites). Devuelve información indicando si se pudo 
    //             o no establecer la frecuencia."
    // ────────────────────────────────────────────────────────────────
    public boolean establecerFrecuencia(double nuevaFrecuencia) {
        // Comprobamos que este dentro de los limites
        if (nuevaFrecuencia >= frecuenciaMinima && nuevaFrecuencia <= frecuenciaMaxima) {
            frecuenciaActual = nuevaFrecuencia;
            return true;   // Se pudo establecer
        } else {
            return false;  // Fuera de limites, no se pudo
        }
    }
}


/**
 * ╔═══════════════════════════════════════════════════════════════════════════════════════╗
 * ║                          📋 ENUNCIADO - CLASE PRINCIPAL                               ║
 * ╠═══════════════════════════════════════════════════════════════════════════════════════╣
 * ║  En la clase Principal implementa un menú de opciones para realizar las              ║
 * ║  siguientes tareas:                                                                   ║
 * ║                                                                                       ║
 * ║  1. Crear una radio y añadirla a un HashSet. Pedir los datos necesarios al           ║
 * ║     usuario. Mostrar un mensaje indicando si se pudo añadir o no.                    ║
 * ║  2. Mostrar los datos de todas las radios.                                           ║
 * ║  3. Encender/apagar Radio. Pedir al usuario el número de serie de la radio           ║
 * ║     que quiere encender/apagar. Mostrar un mensaje indicando si se pudo              ║
 * ║     encender o apagar o no existe la radio.                                          ║
 * ║  4. Radios encendidas. Mostrar los datos técnicos y la frecuencia sintonizada        ║
 * ║     de las radios que están encendidas.                                              ║
 * ║  5. Subir/bajar frecuencia de todas las radios. Preguntar al usuario si quiere       ║
 * ║     subir o bajar la frecuencia. Esta operación tiene un consumo de un 10%           ║
 * ║     para las radios encendidas y no tiene consumo para las radios apagadas.          ║
 * ║  6. Establecer frecuencia. Pedir al usuario el número de serie de la radio           ║
 * ║     que quiere establecer la frecuencia. Mostrar si se pudo o no establecer          ║
 * ║     la frecuencia.                                                                    ║
 * ║  7. Eliminar. Se eliminan las radios con un porcentaje de batería inferior           ║
 * ║     al que indique el usuario.                                                       ║
 * ║  8. Consultar batería. Muestra la carga de batería de todas las radios.              ║
 * ║  9. Salir.                                                                           ║
 * ║                                                                                       ║
 * ║  AMPLIACIÓN: Hacer el ejercicio esta vez utilizando una estructura ArrayList         ║
 * ║              para almacenar los objetos Radio.                                        ║
 * ╚═══════════════════════════════════════════════════════════════════════════════════════╝
 */
public class UT12_RadiosGuiado {

    // Coleccion de radios (HashSet no permite duplicados)
    static HashSet<Radio> radios = new HashSet<>();
    
    // Para la AMPLIACION con ArrayList (permite duplicados)
    // static ArrayList<Radio> radios = new ArrayList<>();
    
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        
        int opcion = -1;
        
        // Bucle del menu - se repite hasta que opcion sea 9
        while (opcion != 9) {
            
            // Mostramos el menu
            System.out.println();
            System.out.println("====== MENU RADIOS =======");
            System.out.println("1. Crear radio");
            System.out.println("2. Mostrar todas las radios");
            System.out.println("3. Encender/Apagar radio");
            System.out.println("4. Ver radios encendidas");
            System.out.println("5. Subir/Bajar frecuencia todas");
            System.out.println("6. Establecer frecuencia");
            System.out.println("7. Eliminar por bateria baja");
            System.out.println("8. Consultar baterias");
            System.out.println("9. Salir");
            System.out.println("==========================");
            System.out.print("Elige opcion: ");
            
            // Leemos la opcion
            opcion = leerNumeroEntero();
            
            // Ejecutamos segun la opcion (sin switch, solo if-else)
            if (opcion == 1) {
                opcion1_CrearRadio();
            }
            else if (opcion == 2) {
                opcion2_MostrarTodas();
            }
            else if (opcion == 3) {
                opcion3_EncenderApagar();
            }
            else if (opcion == 4) {
                opcion4_RadiosEncendidas();
            }
            else if (opcion == 5) {
                opcion5_SubirBajarFrecuencia();
            }
            else if (opcion == 6) {
                opcion6_EstablecerFrecuencia();
            }
            else if (opcion == 7) {
                opcion7_EliminarBateriaBaja();
            }
            else if (opcion == 8) {
                opcion8_ConsultarBaterias();
            }
            else if (opcion == 9) {
                System.out.println("Adios!");
            }
            else {
                System.out.println("Opcion no valida");
            }
        }
        
        teclado.close();
    }

    // ════════════════════════════════════════════════════════════════
    //                  METODOS AUXILIARES PARA LEER
    // ════════════════════════════════════════════════════════════════
    
    // Lee un numero entero de forma segura (no se rompe si meten texto)
    static int leerNumeroEntero() {
        while (teclado.hasNextInt() == false) {
            System.out.print("Introduce un numero entero: ");
            teclado.next(); // Descartamos lo que no es numero
        }
        int numero = teclado.nextInt();
        teclado.nextLine(); // Limpiamos el buffer
        return numero;
    }
    
    // Lee un numero decimal de forma segura
    static double leerNumeroDecimal() {
        while (teclado.hasNextDouble() == false) {
            System.out.print("Introduce un numero: ");
            teclado.next();
        }
        double numero = teclado.nextDouble();
        teclado.nextLine();
        return numero;
    }
    
    // Busca una radio por su numero de serie
    static Radio buscarRadioPorSerie(long numeroSerie) {
        for (Radio r : radios) {
            if (r.getEspecificaciones().getNumeroSerie() == numeroSerie) {
                return r;  // La encontramos
            }
        }
        return null;  // No existe
    }

    // ════════════════════════════════════════════════════════════════
    //                    OPCION 1: CREAR RADIO
    // ════════════════════════════════════════════════════════════════
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Crear una radio y añadirla a un HashSet. 
    //             Pedir los datos necesarios al usuario. 
    //             Mostrar un mensaje indicando si se pudo añadir o no."
    // ────────────────────────────────────────────────────────────────
    static void opcion1_CrearRadio() {
        System.out.println();
        System.out.println("--- CREAR RADIO ---");
        
        // Pedimos los datos de Especificaciones
        System.out.print("Marca de la radio: ");
        String marcaRadio = teclado.nextLine();
        
        System.out.print("Modelo: ");
        String modelo = teclado.nextLine();
        
        System.out.print("Numero de serie: ");
        long numeroSerie = leerNumeroEntero();
        
        // Pedimos los datos de Bateria
        System.out.print("Marca de la bateria: ");
        String marcaBateria = teclado.nextLine();
        
        System.out.print("Porcentaje de carga (0-100): ");
        int porcentaje = leerNumeroEntero();
        
        // Creamos los objetos
        Especificaciones esp = new Especificaciones(marcaRadio, modelo, numeroSerie);
        Bateria bat = new Bateria(marcaBateria, porcentaje);
        Radio nuevaRadio = new Radio(esp, bat);  // Usa frecuencias por defecto
        
        // Intentamos añadir al HashSet
        // add() devuelve true si se añadio, false si ya existia
        boolean seAnadio = radios.add(nuevaRadio);
        
        if (seAnadio == true) {
            System.out.println("Radio creada y anadida correctamente!");
        } else {
            System.out.println("Ya existe una radio con ese numero de serie!");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //                OPCION 2: MOSTRAR TODAS LAS RADIOS
    // ════════════════════════════════════════════════════════════════
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Mostrar los datos de todas las radios."
    // ────────────────────────────────────────────────────────────────
    static void opcion2_MostrarTodas() {
        System.out.println();
        System.out.println("--- TODAS LAS RADIOS ---");
        
        if (radios.isEmpty() == true) {
            System.out.println("No hay radios registradas.");
        } else {
            for (Radio r : radios) {
                System.out.println(r.toString());
            }
        }
    }

    // ════════════════════════════════════════════════════════════════
    //                 OPCION 3: ENCENDER/APAGAR
    // ════════════════════════════════════════════════════════════════
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Encender/apagar Radio. Pedir al usuario el número 
    //             de serie de la radio que quiere encender/apagar. 
    //             Mostrar un mensaje indicando si se pudo encender 
    //             o apagar o no existe la radio."
    // ────────────────────────────────────────────────────────────────
    static void opcion3_EncenderApagar() {
        System.out.println();
        System.out.println("--- ENCENDER/APAGAR ---");
        
        System.out.print("Numero de serie: ");
        long serie = leerNumeroEntero();
        
        Radio radio = buscarRadioPorSerie(serie);
        
        if (radio == null) {
            System.out.println("No existe ninguna radio con ese numero de serie.");
        } else {
            String resultado = radio.botonEncendido();
            System.out.println(resultado);
        }
    }

    // ════════════════════════════════════════════════════════════════
    //                OPCION 4: RADIOS ENCENDIDAS
    // ════════════════════════════════════════════════════════════════
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Radios encendidas. Mostrar los datos técnicos y la 
    //             frecuencia sintonizada de las radios que están 
    //             encendidas."
    // ────────────────────────────────────────────────────────────────
    static void opcion4_RadiosEncendidas() {
        System.out.println();
        System.out.println("--- RADIOS ENCENDIDAS ---");
        
        boolean hayEncendidas = false;
        
        for (Radio r : radios) {
            if (r.isEncendida() == true) {
                hayEncendidas = true;
                System.out.println("Datos tecnicos: " + r.getEspecificaciones().toString());
                System.out.println("Frecuencia sintonizada: " + r.getFrecuenciaActual() + " MHz");
                System.out.println();
            }
        }
        
        if (hayEncendidas == false) {
            System.out.println("No hay ninguna radio encendida.");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //            OPCION 5: SUBIR/BAJAR FRECUENCIA TODAS
    // ════════════════════════════════════════════════════════════════
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Subir/bajar frecuencia de todas las radios. 
    //             Preguntar al usuario si quiere subir o bajar la 
    //             frecuencia. Esta operación tiene un consumo de un 
    //             10% para las radios encendidas y no tiene consumo 
    //             para las radios apagadas."
    // ────────────────────────────────────────────────────────────────
    static void opcion5_SubirBajarFrecuencia() {
        System.out.println();
        System.out.println("--- SUBIR/BAJAR FRECUENCIA ---");
        
        System.out.print("Quieres Subir (S) o Bajar (B)? ");
        String respuesta = teclado.nextLine().toUpperCase();
        
        for (Radio r : radios) {
            double frecuencia;
            
            // Subimos o bajamos segun la respuesta
            if (respuesta.equals("S")) {
                frecuencia = r.subirFrecuencia();
            } else if (respuesta.equals("B")) {
                frecuencia = r.bajarFrecuencia();
            } else {
                System.out.println("Opcion no valida. Usa S o B.");
                return;
            }
            
            // Si la radio esta encendida, consume 10% de bateria
            if (r.isEncendida() == true) {
                r.getBateria().consumir(10);
                System.out.println("Radio " + r.getEspecificaciones().getNumeroSerie() + 
                        " -> Freq: " + frecuencia + " MHz (Bateria: " + 
                        r.getBateria().getPorcentajeCarga() + "%)");
            } else {
                System.out.println("Radio " + r.getEspecificaciones().getNumeroSerie() + 
                        " -> Freq: " + frecuencia + " MHz (apagada, sin consumo)");
            }
        }
    }

    // ════════════════════════════════════════════════════════════════
    //              OPCION 6: ESTABLECER FRECUENCIA
    // ════════════════════════════════════════════════════════════════
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Establecer frecuencia. Pedir al usuario el número 
    //             de serie de la radio que quiere establecer la 
    //             frecuencia. Mostrar si se pudo o no establecer 
    //             la frecuencia."
    // ────────────────────────────────────────────────────────────────
    static void opcion6_EstablecerFrecuencia() {
        System.out.println();
        System.out.println("--- ESTABLECER FRECUENCIA ---");
        
        System.out.print("Numero de serie: ");
        long serie = leerNumeroEntero();
        
        Radio radio = buscarRadioPorSerie(serie);
        
        if (radio == null) {
            System.out.println("No existe ninguna radio con ese numero de serie.");
            return;
        }
        
        System.out.println("Rango permitido: " + radio.getFrecuenciaMinima() + 
                " - " + radio.getFrecuenciaMaxima() + " MHz");
        System.out.print("Nueva frecuencia: ");
        double nuevaFreq = leerNumeroDecimal();
        
        boolean sePudo = radio.establecerFrecuencia(nuevaFreq);
        
        if (sePudo == true) {
            System.out.println("Frecuencia establecida correctamente!");
        } else {
            System.out.println("No se pudo: la frecuencia esta fuera del rango.");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //            OPCION 7: ELIMINAR POR BATERIA BAJA
    // ════════════════════════════════════════════════════════════════
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Eliminar. Se eliminan las radios con un porcentaje 
    //             de batería inferior al que indique el usuario."
    //
    // IMPORTANTE: Para eliminar mientras recorremos usamos Iterator
    //             No podemos usar for-each + remove() directamente
    // ────────────────────────────────────────────────────────────────
    static void opcion7_EliminarBateriaBaja() {
        System.out.println();
        System.out.println("--- ELIMINAR POR BATERIA BAJA ---");
        
        System.out.print("Eliminar radios con bateria menor a: ");
        int umbral = leerNumeroEntero();
        
        // Usamos Iterator para poder eliminar mientras recorremos
        Iterator<Radio> iterador = radios.iterator();
        int eliminadas = 0;
        
        while (iterador.hasNext()) {
            Radio r = iterador.next();
            int carga = r.getBateria().getPorcentajeCarga();
            
            if (carga < umbral) {
                System.out.println("Eliminando radio " + 
                        r.getEspecificaciones().getNumeroSerie() + 
                        " (bateria: " + carga + "%)");
                iterador.remove();  // Elimina de forma segura
                eliminadas++;
            }
        }
        
        System.out.println("Total eliminadas: " + eliminadas);
    }

    // ════════════════════════════════════════════════════════════════
    //               OPCION 8: CONSULTAR BATERIAS
    // ════════════════════════════════════════════════════════════════
    
    // ────────────────────────────────────────────────────────────────
    // ENUNCIADO: "Consultar batería. Muestra la carga de batería 
    //             de todas las radios."
    // ────────────────────────────────────────────────────────────────
    static void opcion8_ConsultarBaterias() {
        System.out.println();
        System.out.println("--- ESTADO DE BATERIAS ---");
        
        if (radios.isEmpty() == true) {
            System.out.println("No hay radios registradas.");
        } else {
            for (Radio r : radios) {
                long serie = r.getEspecificaciones().getNumeroSerie();
                int carga = r.getBateria().getPorcentajeCarga();
                System.out.println("Radio " + serie + ": " + carga + "% de bateria");
            }
        }
    }
}

// * ═══════════════════════════════════════════════════════════════════════════════════
// *                      RESUMEN DE CONCEPTOS CLAVE
// * ═══════════════════════════════════════════════════════════════════════════════════
// *
// *  1. AGREGACION: Una clase "tiene" objetos de otras clases como atributos.
// *     Ejemplo: Radio TIENE Especificaciones y Bateria
// *
// *  2. HASHSET: Coleccion que NO permite duplicados.
// *     - Usa equals() y hashCode() para saber si dos objetos son iguales
// *     - add() devuelve false si el elemento ya existia
// *
// *  3. ARRAYLIST: Coleccion que SI permite duplicados.
// *     - Mantiene el orden de insercion
// *     - Permite acceso por indice con get(i)
// *
// *  4. ITERATOR: Necesario para eliminar elementos mientras recorremos.
// *     - iterator.remove() elimina el elemento actual de forma segura
// *
// *  5. equals/hashCode: Si queremos que HashSet detecte duplicados por
// *     un atributo concreto (numeroSerie), debemos sobrescribir ambos metodos.
// *
// * ═══════════════════════════════════════════════════════════════════════════════════
