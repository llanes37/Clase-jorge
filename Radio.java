/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 12: POO + AGREGACIÓN + COLECCIONES
 *  📻 CLASE: Radio - Representa una radio portátil con agregación
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

import java.util.Objects; // ? Para usar Objects.hash() en hashCode()

/**
 * 📖 TEORÍA: ¿Qué es la AGREGACIÓN?
 * ──────────────────────────────────────────────
 * ? La agregación es una relación "tiene-un" entre clases.
 * ? Radio TIENE Especificaciones y TIENE Batería.
 * ? DIFERENCIA con composición: en agregación, los objetos pueden
 *   existir independientemente. Si destruimos la Radio, la Batería
 *   y las Especificaciones podrían seguir existiendo.
 * 
 * ? En código: agregación = atributos que son objetos de otras clases.
 */
public class Radio {

    // * ═══════════════════════════════════════════
    // * 📦 ATRIBUTOS - AGREGACIÓN
    // * ═══════════════════════════════════════════
    
    // 🔗 AGREGACIÓN: Radio "tiene-un" Especificaciones y "tiene-un" Bateria
    private Especificaciones especificaciones;  // 📋 Datos técnicos de la radio
    private Bateria bateria;                    // 🔋 Batería de la radio

    // * ═══════════════════════════════════════════
    // * 📦 ATRIBUTOS PROPIOS
    // * ═══════════════════════════════════════════
    
    private double frecuenciaMin;       // 📉 Frecuencia mínima FM (ej: 87.5)
    private double frecuenciaMax;       // 📈 Frecuencia máxima FM (ej: 108.0)
    private double frecuenciaActual;    // 📻 Frecuencia actualmente sintonizada
    private boolean encendida;          // 🔌 Estado: true = encendida, false = apagada

    // * ═══════════════════════════════════════════
    // * 🔨 CONSTRUCTORES
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Constructor COMPLETO
     * ──────────────────────────────────────────────
     * ? Recibe TODOS los parámetros necesarios.
     * ? Por defecto: radio APAGADA y frecuencia = mínima.
     * 
     * @param especificaciones 📋 Objeto Especificaciones (agregación)
     * @param bateria          🔋 Objeto Bateria (agregación)
     * @param frecuenciaMin    📉 Frecuencia FM mínima
     * @param frecuenciaMax    📈 Frecuencia FM máxima
     */
    public Radio(Especificaciones especificaciones, Bateria bateria, 
                 double frecuenciaMin, double frecuenciaMax) {
        this.especificaciones = especificaciones;   // 🔗 Agregamos especificaciones
        this.bateria = bateria;                     // 🔗 Agregamos batería
        this.frecuenciaMin = frecuenciaMin;         // 📉 Establecemos frecuencia mínima
        this.frecuenciaMax = frecuenciaMax;         // 📈 Establecemos frecuencia máxima
        this.frecuenciaActual = frecuenciaMin;      // 📻 Empezamos en la frecuencia mínima
        this.encendida = false;                     // 🔌 Por defecto: APAGADA
    }

    /**
     * 📖 TEORÍA: Constructor SIMPLIFICADO (sobrecarga)
     * ──────────────────────────────────────────────
     * ? Usa frecuencias FM estándar por defecto: 87.5 - 108.0 MHz
     * ? Llama al constructor completo usando 'this()'
     * ? Esto es SOBRECARGA DE CONSTRUCTORES.
     * 
     * @param especificaciones 📋 Objeto Especificaciones
     * @param bateria          🔋 Objeto Bateria
     */
    public Radio(Especificaciones especificaciones, Bateria bateria) {
        // 🔄 Llamamos al constructor completo con valores por defecto
        this(especificaciones, bateria, 87.5, 108.0);
    }

    // * ═══════════════════════════════════════════
    // * 📤 GETTERS
    // * ═══════════════════════════════════════════

    /** 📋 Devuelve las especificaciones de la radio */
    public Especificaciones getEspecificaciones() {
        return especificaciones;
    }

    /** 🔋 Devuelve la batería de la radio */
    public Bateria getBateria() {
        return bateria;
    }

    /** 📉 Devuelve la frecuencia mínima */
    public double getFrecuenciaMin() {
        return frecuenciaMin;
    }

    /** 📈 Devuelve la frecuencia máxima */
    public double getFrecuenciaMax() {
        return frecuenciaMax;
    }

    /** 📻 Devuelve la frecuencia actualmente sintonizada */
    public double getFrecuenciaActual() {
        return frecuenciaActual;
    }

    /** 🔌 Devuelve true si la radio está encendida */
    public boolean isEncendida() {
        return encendida;
    }

    // * ═══════════════════════════════════════════
    // * ⚡ MÉTODOS DE COMPORTAMIENTO
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Método botonEncendido()
     * ──────────────────────────────────────────────
     * ? Alterna el estado de encendido/apagado.
     * ? IMPORTANTE: Solo puede ENCENDER si la batería tiene carga.
     * ? Siempre puede APAGAR (no necesita batería para eso).
     * 
     * @return String indicando el nuevo estado
     */
    public String botonEncendido() {
        if (encendida) {
            // 🔴 Si está encendida, la apagamos
            encendida = false;
            return "📻 Radio APAGADA";
        } else {
            // 🟢 Intentamos encender: necesitamos batería
            if (bateria.getPorcentajeCarga() > 0) {
                encendida = true;
                return "📻 Radio ENCENDIDA ✅";
            } else {
                return "❌ No se puede encender: BATERÍA AGOTADA";
            }
        }
    }

    /**
     * 📖 TEORÍA: Método subirFrecuencia()
     * ──────────────────────────────────────────────
     * ? Incrementa la frecuencia en 0.5 MHz.
     * ? NO puede superar la frecuencia máxima.
     * ? Usamos Math.min() para asegurar el límite.
     * 
     * @return double con la frecuencia actual tras el cambio
     */
    public double subirFrecuencia() {
        // 🔒 Math.min asegura que no superemos el máximo
        // ? Si frecuenciaActual + 0.5 > max, devuelve max
        frecuenciaActual = Math.min(frecuenciaMax, frecuenciaActual + 0.5);
        return frecuenciaActual;
    }

    /**
     * 📖 TEORÍA: Método bajarFrecuencia()
     * ──────────────────────────────────────────────
     * ? Decrementa la frecuencia en 0.5 MHz.
     * ? NO puede bajar de la frecuencia mínima.
     * ? Usamos Math.max() para asegurar el límite.
     * 
     * @return double con la frecuencia actual tras el cambio
     */
    public double bajarFrecuencia() {
        // 🔒 Math.max asegura que no bajemos del mínimo
        // ? Si frecuenciaActual - 0.5 < min, devuelve min
        frecuenciaActual = Math.max(frecuenciaMin, frecuenciaActual - 0.5);
        return frecuenciaActual;
    }

    /**
     * 📖 TEORÍA: Método establecerFrecuencia()
     * ──────────────────────────────────────────────
     * ? Permite establecer una frecuencia específica.
     * ? Valida que esté dentro del rango permitido.
     * ? Devuelve boolean para indicar éxito o fallo.
     * 
     * @param frecuencia 📻 Frecuencia deseada
     * @return true si se pudo establecer, false si está fuera de rango
     */
    public boolean establecerFrecuencia(double frecuencia) {
        // ✅ Validamos que la frecuencia esté en el rango permitido
        if (frecuencia >= frecuenciaMin && frecuencia <= frecuenciaMax) {
            this.frecuenciaActual = frecuencia;
            return true;    // ✅ Frecuencia válida y establecida
        } else {
            return false;   // ❌ Frecuencia fuera de rango
        }
    }

    // * ═══════════════════════════════════════════
    // * 🔄 equals() y hashCode() - CRÍTICO PARA HashSet
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: ¿Por qué sobrescribir equals()?
     * ──────────────────────────────────────────────
     * ? Por defecto, equals() compara REFERENCIAS (direcciones de memoria).
     * ? Nosotros queremos comparar por NÚMERO DE SERIE.
     * ? Dos radios son "iguales" si tienen el mismo numeroSerie.
     * 
     * ? REGLA: Si sobrescribes equals(), DEBES sobrescribir hashCode().
     * 
     * @param obj Objeto a comparar
     * @return true si tienen el mismo número de serie
     */
    @Override
    public boolean equals(Object obj) {
        // 1️⃣ ¿Es el mismo objeto en memoria? (optimización)
        if (this == obj) {
            return true;
        }
        
        // 2️⃣ ¿Es null o de otra clase?
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        // 3️⃣ Hacemos casting seguro (ya sabemos que es Radio)
        Radio otraRadio = (Radio) obj;
        
        // 4️⃣ Comparamos el NÚMERO DE SERIE
        // ? Accedemos a través de especificaciones (agregación)
        return this.especificaciones.getNumeroSerie() == 
               otraRadio.especificaciones.getNumeroSerie();
    }

    /**
     * 📖 TEORÍA: ¿Por qué sobrescribir hashCode()?
     * ──────────────────────────────────────────────
     * ? HashSet usa hashCode() para organizar elementos en "cubos".
     * ? Si dos objetos son equals(), DEBEN tener el mismo hashCode().
     * ? Usamos Objects.hash() que genera un hash a partir de valores.
     * 
     * @return int código hash basado en el número de serie
     */
    @Override
    public int hashCode() {
        // 🔢 Generamos hash basado en numeroSerie
        return Objects.hash(especificaciones.getNumeroSerie());
    }

    // * ═══════════════════════════════════════════
    // * 📝 MÉTODO toString()
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: toString() completo
     * ──────────────────────────────────────────────
     * ? Mostramos TODA la información de la radio.
     * ? Aprovechamos los toString() de las clases agregadas.
     * 
     * @return String con toda la información formateada
     */
    @Override
    public String toString() {
        String estadoRadio = encendida ? "🟢 ENCENDIDA" : "🔴 APAGADA";
        
        return "\n╔══════════════════════════════════════════════╗\n" +
               "║           📻 RADIO                           ║\n" +
               "╠══════════════════════════════════════════════╣\n" +
               "║ " + especificaciones.toString() + "\n" +
               "║ " + bateria.toString() + "\n" +
               "║ 📻 Frecuencia: " + frecuenciaActual + " MHz " +
               "(Rango: " + frecuenciaMin + " - " + frecuenciaMax + ")\n" +
               "║ Estado: " + estadoRadio + "\n" +
               "╚══════════════════════════════════════════════╝";
    }

    // ! ✅ TAREAS ALUMNO:
    // * 1. Crea dos radios con el mismo número de serie y comprueba si son equals()
    // * 2. Añádelas a un HashSet. ¿Cuántas radios hay en el conjunto?
    // * 3. Intenta encender una radio con batería al 0%. ¿Qué ocurre?
    // * 4. Sube la frecuencia varias veces hasta llegar al máximo. ¿Se pasa del límite?
}
