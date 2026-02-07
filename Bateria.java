/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 12: POO + AGREGACIÓN + COLECCIONES
 *  🔋 CLASE: Bateria - Representa la batería de una radio
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

/**
 * 📖 TEORÍA: ¿Qué es esta clase?
 * ──────────────────────────────────────────────
 * ? Bateria representa el componente de energía de una radio.
 * ? El porcentajeCarga va de 0 a 100 (validado en constructor).
 * ? Esta clase será AGREGADA por Radio (relación "tiene-un").
 * ? La batería puede existir independientemente de la radio (agregación).
 */
public class Bateria {

    // * ═══════════════════════════════════════════
    // * 📦 ATRIBUTOS PRIVADOS
    // * ═══════════════════════════════════════════
    
    private String marca;           // 🏭 Marca de la batería (ej: Duracell, Energizer)
    private int porcentajeCarga;    // 🔋 Carga actual (0-100%)

    // * ═══════════════════════════════════════════
    // * 🔨 CONSTRUCTOR
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Constructor con validación
     * ──────────────────────────────────────────────
     * ? Validamos que el porcentaje esté entre 0 y 100.
     * ? Si el usuario pone un valor fuera de rango, se ajusta automáticamente.
     * ? Esto evita estados inválidos del objeto (buena práctica de encapsulación).
     * 
     * @param marca           🏭 Marca de la batería
     * @param porcentajeCarga 🔋 Porcentaje de carga inicial (0-100)
     */
    public Bateria(String marca, int porcentajeCarga) {
        this.marca = marca;
        
        // 🔒 Validación: aseguramos que el porcentaje esté en rango válido
        if (porcentajeCarga < 0) {
            this.porcentajeCarga = 0;           // ⬇️ Mínimo 0%
        } else if (porcentajeCarga > 100) {
            this.porcentajeCarga = 100;         // ⬆️ Máximo 100%
        } else {
            this.porcentajeCarga = porcentajeCarga; // ✅ Valor válido
        }
    }

    // * ═══════════════════════════════════════════
    // * 📤 GETTERS
    // * ═══════════════════════════════════════════

    /**
     * 🏭 Devuelve la marca de la batería
     * @return String con la marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * 🔋 Devuelve el porcentaje de carga actual
     * @return int con el porcentaje (0-100)
     */
    public int getPorcentajeCarga() {
        return porcentajeCarga;
    }

    // * ═══════════════════════════════════════════
    // * ⚡ MÉTODO CONSUMIR BATERÍA
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Modificar estado interno
     * ──────────────────────────────────────────────
     * ? Este método modifica el atributo porcentajeCarga.
     * ? Usamos Math.max() para asegurar que NUNCA baje de 0.
     * ? Math.max(a, b) devuelve el mayor de los dos valores.
     * 
     * @param cantidad ⚡ Cantidad de batería a consumir
     */
    public void consumir(int cantidad) {
        // 🔒 Math.max asegura que el resultado nunca sea menor que 0
        // ? Si porcentajeCarga - cantidad = negativo, devuelve 0
        // ? Si porcentajeCarga - cantidad = positivo, devuelve ese valor
        this.porcentajeCarga = Math.max(0, this.porcentajeCarga - cantidad);
    }

    // * ═══════════════════════════════════════════
    // * 📝 MÉTODO toString()
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Representación visual de la batería
     * ──────────────────────────────────────────────
     * ? Mostramos la información de forma clara y legible.
     * ? Añadimos un indicador visual del nivel de batería.
     * 
     * @return String formateado con los datos de la batería
     */
    @Override
    public String toString() {
        // 🎨 Creamos un indicador visual del nivel
        String indicador;
        if (porcentajeCarga > 70) {
            indicador = "🟢";       // ✅ Batería alta
        } else if (porcentajeCarga > 30) {
            indicador = "🟡";       // ⚠️ Batería media
        } else if (porcentajeCarga > 0) {
            indicador = "🔴";       // ❌ Batería baja
        } else {
            indicador = "⚫";       // 💀 Sin batería
        }
        
        return "🔋 Batería " + marca + ": " + porcentajeCarga + "% " + indicador;
    }

    // ! ✅ TAREA ALUMNO:
    // * 1. Crea una batería con 50% y llama a consumir(30). ¿Cuánto queda?
    // * 2. Luego llama a consumir(50). ¿Qué valor tiene ahora? ¿Puede ser negativo?
    // * 3. Prueba a crear una batería con porcentaje 150. ¿Qué valor se guarda?
}
