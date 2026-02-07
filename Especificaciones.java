/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 12: POO + AGREGACIÓN + COLECCIONES
 *  📦 CLASE: Especificaciones - Representa los datos técnicos de una radio
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

/**
 * 📖 TEORÍA: ¿Qué es esta clase?
 * ──────────────────────────────────────────────
 * ? Especificaciones almacena los datos técnicos identificativos de una radio.
 * ? El numeroSerie es ÚNICO y se usará para comparar radios en el HashSet.
 * ? Esta clase será AGREGADA por Radio (relación "tiene-un").
 */
public class Especificaciones {

    // * ═══════════════════════════════════════════
    // * 📦 ATRIBUTOS PRIVADOS
    // * ═══════════════════════════════════════════
    
    private String marca;       // 🏭 Marca del fabricante (ej: Sony, Philips)
    private String modelo;      // 📱 Modelo específico (ej: XR-500)
    private long numeroSerie;   // 🔢 Número de serie ÚNICO (identificador)

    // * ═══════════════════════════════════════════
    // * 🔨 CONSTRUCTOR
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Constructor completo
     * ──────────────────────────────────────────────
     * ? Recibe todos los parámetros necesarios para crear el objeto.
     * ? Se usa la palabra 'this' para diferenciar atributos de parámetros.
     * 
     * @param marca        🏭 Marca de la radio
     * @param modelo       📱 Modelo de la radio
     * @param numeroSerie  🔢 Número de serie único
     */
    public Especificaciones(String marca, String modelo, long numeroSerie) {
        this.marca = marca;             // ✅ Asignamos la marca
        this.modelo = modelo;           // ✅ Asignamos el modelo
        this.numeroSerie = numeroSerie; // ✅ Asignamos el número de serie
    }

    // * ═══════════════════════════════════════════
    // * 📤 GETTERS
    // * ═══════════════════════════════════════════

    /**
     * 🏭 Devuelve la marca del fabricante
     * @return String con la marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * 📱 Devuelve el modelo de la radio
     * @return String con el modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * 🔢 Devuelve el número de serie (CLAVE para comparaciones)
     * @return long con el número de serie
     */
    public long getNumeroSerie() {
        return numeroSerie;
    }

    // * ═══════════════════════════════════════════
    // * 📝 MÉTODO toString()
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Sobrescribir toString()
     * ──────────────────────────────────────────────
     * ? toString() convierte el objeto a String legible para humanos.
     * ? Se llama automáticamente al imprimir el objeto con System.out.println().
     * ? Usamos @Override para indicar que sobrescribimos el método de Object.
     * 
     * @return String formateado con los datos de las especificaciones
     */
    @Override
    public String toString() {
        return "🏭 Marca: " + marca + 
               " | 📱 Modelo: " + modelo + 
               " | 🔢 N° Serie: " + numeroSerie;
    }

    // ! ✅ TAREA ALUMNO:
    // * 1. Añade un getter para la marca
    // * 2. Prueba a crear un objeto Especificaciones y mostrarlo con System.out.println()
    // * 3. Observa cómo se llama automáticamente a toString()
}
