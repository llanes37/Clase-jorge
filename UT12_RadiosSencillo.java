/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 12: POO + AGREGACIÓN + COLECCIONES (VERSIÓN SIMPLIFICADA)
 *  📻 EJERCICIO: Gestión de Radios con HashSet - TODO EN UN ARCHIVO
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Objects;

// * ═══════════════════════════════════════════════════════════════════════════════════
// * 📋 CLASE ESPECIFICACIONES - Datos técnicos de la radio
// * ═══════════════════════════════════════════════════════════════════════════════════

class Especificaciones {
    private String marca;
    private String modelo;
    private long numeroSerie;  // 🔑 Identificador único

    // 🔨 Constructor
    public Especificaciones(String marca, String modelo, long numeroSerie) {
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
    }

    // 📤 Getters
    public long getNumeroSerie() { return numeroSerie; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }

    @Override
    public String toString() {
        return "Marca: " + marca + " | Modelo: " + modelo + " | Serie: " + numeroSerie;
    }
}

// * ═══════════════════════════════════════════════════════════════════════════════════
// * 🔋 CLASE BATERIA - Energía de la radio
// * ═══════════════════════════════════════════════════════════════════════════════════

class Bateria {
    private String marca;
    private int porcentaje;  // 0-100

    // 🔨 Constructor con validación
    public Bateria(String marca, int porcentaje) {
        this.marca = marca;
        // Validamos que esté entre 0 y 100
        if (porcentaje < 0) this.porcentaje = 0;
        else if (porcentaje > 100) this.porcentaje = 100;
        else this.porcentaje = porcentaje;
    }

    // 📤 Getters
    public int getPorcentaje() { return porcentaje; }
    public String getMarca() { return marca; }

    // ⚡ Consume batería (nunca baja de 0)
    public void consumir(int cantidad) {
        porcentaje = Math.max(0, porcentaje - cantidad);
    }

    @Override
    public String toString() {
        return "Bateria " + marca + ": " + porcentaje + "%";
    }
}

// * ═══════════════════════════════════════════════════════════════════════════════════
// * 📻 CLASE RADIO - Usa AGREGACIÓN (tiene Especificaciones y Bateria)
// * ═══════════════════════════════════════════════════════════════════════════════════

class Radio {
    // 🔗 AGREGACIÓN: Radio "tiene" estos objetos
    private Especificaciones especificaciones;
    private Bateria bateria;
    
    // Atributos propios
    private double frecuenciaMin;
    private double frecuenciaMax;
    private double frecuenciaActual;
    private boolean encendida;

    // 🔨 Constructor completo
    public Radio(Especificaciones especificaciones, Bateria bateria, 
                 double frecuenciaMin, double frecuenciaMax) {
        this.especificaciones = especificaciones;
        this.bateria = bateria;
        this.frecuenciaMin = frecuenciaMin;
        this.frecuenciaMax = frecuenciaMax;
        this.frecuenciaActual = frecuenciaMin;  // Empieza en la mínima
        this.encendida = false;                  // Empieza apagada
    }

    // 🔨 Constructor simplificado (frecuencias FM estándar)
    public Radio(Especificaciones especificaciones, Bateria bateria) {
        this(especificaciones, bateria, 87.5, 108.0);
    }

    // 📤 Getters
    public Especificaciones getEspecificaciones() { return especificaciones; }
    public Bateria getBateria() { return bateria; }
    public double getFrecuenciaActual() { return frecuenciaActual; }
    public double getFrecuenciaMin() { return frecuenciaMin; }
    public double getFrecuenciaMax() { return frecuenciaMax; }
    public boolean isEncendida() { return encendida; }

    // 🔌 Encender/Apagar
    public String botonEncendido() {
        if (encendida) {
            encendida = false;
            return "Radio APAGADA";
        } else {
            if (bateria.getPorcentaje() > 0) {
                encendida = true;
                return "Radio ENCENDIDA";
            } else {
                return "No hay bateria!";
            }
        }
    }

    // 📶 Subir frecuencia (+0.5)
    public double subirFrecuencia() {
        frecuenciaActual = Math.min(frecuenciaMax, frecuenciaActual + 0.5);
        return frecuenciaActual;
    }

    // 📶 Bajar frecuencia (-0.5)
    public double bajarFrecuencia() {
        frecuenciaActual = Math.max(frecuenciaMin, frecuenciaActual - 0.5);
        return frecuenciaActual;
    }

    // 🎚️ Establecer frecuencia específica
    public boolean establecerFrecuencia(double f) {
        if (f >= frecuenciaMin && f <= frecuenciaMax) {
            frecuenciaActual = f;
            return true;
        }
        return false;
    }

    // * ═══════════════════════════════════════════
    // * 🔑 equals y hashCode - CLAVE PARA HASHSET
    // * Dos radios son iguales si tienen el mismo numeroSerie
    // * ═══════════════════════════════════════════

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Radio otra = (Radio) obj;
        return especificaciones.getNumeroSerie() == otra.especificaciones.getNumeroSerie();
    }

    @Override
    public int hashCode() {
        return Objects.hash(especificaciones.getNumeroSerie());
    }

    @Override
    public String toString() {
        String estado = encendida ? "ENCENDIDA" : "APAGADA";
        return "[" + especificaciones.toString() + " | " + bateria.toString() + 
               " | Freq: " + frecuenciaActual + " MHz | " + estado + "]";
    }
}

// * ═══════════════════════════════════════════════════════════════════════════════════
// * 🎮 CLASE PRINCIPAL - Menú y gestión
// * ═══════════════════════════════════════════════════════════════════════════════════

public class UT12_RadiosSencillo {

    static HashSet<Radio> radios = new HashSet<>();  // Colección de radios
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n========== MENU RADIOS ==========");
            System.out.println("1. Crear radio");
            System.out.println("2. Mostrar todas");
            System.out.println("3. Encender/Apagar por serie");
            System.out.println("4. Ver radios encendidas");
            System.out.println("5. Subir/Bajar frecuencia (todas)");
            System.out.println("6. Establecer frecuencia por serie");
            System.out.println("7. Eliminar con bateria baja");
            System.out.println("8. Ver baterias");
            System.out.println("0. Salir");
            System.out.println("=================================");
            System.out.print("Opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> crearRadio();
                case 2 -> mostrarTodas();
                case 3 -> encenderApagar();
                case 4 -> verEncendidas();
                case 5 -> cambiarFrecuenciaTodas();
                case 6 -> establecerFrecuencia();
                case 7 -> eliminarBateriaBaja();
                case 8 -> verBaterias();
                case 0 -> System.out.println("Adios!");
                default -> System.out.println("Opcion invalida");
            }
        } while (opcion != 0);

        sc.close();
    }

    // 🛠️ Leer entero de forma segura
    static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Introduce un numero: ");
            sc.next();
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    // 🛠️ Leer double de forma segura
    static double leerDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Introduce un numero: ");
            sc.next();
        }
        double n = sc.nextDouble();
        sc.nextLine();
        return n;
    }

    // 🛠️ Buscar radio por numero de serie
    static Radio buscarPorSerie(long serie) {
        for (Radio r : radios) {
            if (r.getEspecificaciones().getNumeroSerie() == serie) {
                return r;
            }
        }
        return null;
    }

    // 1️⃣ Crear radio
    static void crearRadio() {
        System.out.print("Marca radio: ");
        String marca = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Numero serie: ");
        long serie = leerEntero();
        System.out.print("Marca bateria: ");
        String marcaBat = sc.nextLine();
        System.out.print("Porcentaje bateria (0-100): ");
        int porcentaje = leerEntero();

        Especificaciones esp = new Especificaciones(marca, modelo, serie);
        Bateria bat = new Bateria(marcaBat, porcentaje);
        Radio nuevaRadio = new Radio(esp, bat);  // Usa frecuencias por defecto

        if (radios.add(nuevaRadio)) {
            System.out.println("Radio añadida!");
        } else {
            System.out.println("Ya existe una radio con ese numero de serie!");
        }
    }

    // 2️⃣ Mostrar todas
    static void mostrarTodas() {
        if (radios.isEmpty()) {
            System.out.println("No hay radios.");
            return;
        }
        System.out.println("\n--- TODAS LAS RADIOS ---");
        for (Radio r : radios) {
            System.out.println(r);
        }
    }

    // 3️⃣ Encender/Apagar
    static void encenderApagar() {
        System.out.print("Numero de serie: ");
        long serie = leerEntero();
        Radio r = buscarPorSerie(serie);
        if (r != null) {
            System.out.println(r.botonEncendido());
        } else {
            System.out.println("No existe esa radio.");
        }
    }

    // 4️⃣ Ver encendidas
    static void verEncendidas() {
        System.out.println("\n--- RADIOS ENCENDIDAS ---");
        boolean hay = false;
        for (Radio r : radios) {
            if (r.isEncendida()) {
                hay = true;
                System.out.println("Serie: " + r.getEspecificaciones().getNumeroSerie() +
                        " | Freq: " + r.getFrecuenciaActual() + " MHz");
            }
        }
        if (!hay) System.out.println("Ninguna encendida.");
    }

    // 5️⃣ Subir/Bajar frecuencia de todas
    static void cambiarFrecuenciaTodas() {
        System.out.print("Subir (S) o Bajar (B)? ");
        String op = sc.nextLine().toUpperCase();

        for (Radio r : radios) {
            if (op.equals("S")) {
                r.subirFrecuencia();
            } else if (op.equals("B")) {
                r.bajarFrecuencia();
            }
            // Si esta encendida, consume bateria
            if (r.isEncendida()) {
                r.getBateria().consumir(10);
            }
            System.out.println("Radio " + r.getEspecificaciones().getNumeroSerie() + 
                    ": " + r.getFrecuenciaActual() + " MHz | Bat: " + 
                    r.getBateria().getPorcentaje() + "%");
        }
    }

    // 6️⃣ Establecer frecuencia
    static void establecerFrecuencia() {
        System.out.print("Numero de serie: ");
        long serie = leerEntero();
        Radio r = buscarPorSerie(serie);
        if (r == null) {
            System.out.println("No existe.");
            return;
        }
        System.out.println("Rango: " + r.getFrecuenciaMin() + " - " + r.getFrecuenciaMax());
        System.out.print("Nueva frecuencia: ");
        double freq = leerDouble();
        if (r.establecerFrecuencia(freq)) {
            System.out.println("Frecuencia establecida!");
        } else {
            System.out.println("Fuera de rango!");
        }
    }

    // 7️⃣ Eliminar con bateria baja
    static void eliminarBateriaBaja() {
        System.out.print("Eliminar radios con bateria menor a: ");
        int umbral = leerEntero();

        // Usamos Iterator para poder eliminar mientras recorremos
        Iterator<Radio> it = radios.iterator();
        int eliminadas = 0;
        while (it.hasNext()) {
            Radio r = it.next();
            if (r.getBateria().getPorcentaje() < umbral) {
                System.out.println("Eliminada: " + r.getEspecificaciones().getNumeroSerie());
                it.remove();
                eliminadas++;
            }
        }
        System.out.println("Total eliminadas: " + eliminadas);
    }

    // 8️⃣ Ver baterias
    static void verBaterias() {
        System.out.println("\n--- ESTADO BATERIAS ---");
        for (Radio r : radios) {
            System.out.println("Serie " + r.getEspecificaciones().getNumeroSerie() + 
                    ": " + r.getBateria().getPorcentaje() + "%");
        }
    }
}

// * ═══════════════════════════════════════════════════════════════════════════════════
// * 📖 CONCEPTOS CLAVE RESUMIDOS
// * ═══════════════════════════════════════════════════════════════════════════════════
// *
// * 1. AGREGACIÓN: Radio "tiene" Especificaciones y Bateria
// *    - Son objetos dentro de otro objeto
// *    - Se pasan por el constructor
// *
// * 2. HASHSET: No permite duplicados
// *    - Usa equals() y hashCode() para comparar
// *    - add() devuelve false si ya existe
// *
// * 3. equals/hashCode: Los sobrescribimos para que dos radios
// *    sean "iguales" si tienen el mismo numeroSerie
// *
// * 4. Iterator: Necesario para eliminar elementos mientras recorremos
// *    - No podemos usar for-each + remove() directamente
// *
// * ═══════════════════════════════════════════════════════════════════════════════════
