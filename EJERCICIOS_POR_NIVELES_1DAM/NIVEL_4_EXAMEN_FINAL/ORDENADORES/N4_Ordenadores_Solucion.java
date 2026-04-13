/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  BLOQUE DE EJERCICIOS POR NIVELES - 1 DAM
 *  NIVEL 4 - EXAMEN FINAL - SOLUCION
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  SOLUCION | ORDENADORES
 * ==========================================================================================
 *  Este ejercicio se resuelve mejor pensando en cuatro capas:
 *
 *  1. Periferico
 *  2. Ordenador
 *  3. GestionOrdenadores
 *  4. Secuencia principal
 *
 *  IDEA CLAVE:
 *  - Periferico representa un elemento individual
 *  - Ordenador agrupa perifericos
 *  - GestionOrdenadores agrupa ordenadores
 * ==========================================================================================
 */

import java.util.*;

class PerifericoSolucionN4 {

    // * ATRIBUTOS PRIVADOS
    private String tipo;
    private String marca;
    private boolean averiado;

    // * CONSTRUCTOR
    // ? El estado averiado se genera aleatoriamente.
    public PerifericoSolucionN4(String tipo, String marca) {
        this.tipo = tipo;
        this.marca = marca;
        this.averiado = new Random().nextBoolean();
    }

    // * GETTERS
    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public boolean isAveriado() {
        return averiado;
    }

    // * EQUALS Y HASHCODE
    // ? Dos periféricos son iguales si tienen el mismo tipo y marca.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PerifericoSolucionN4 otro = (PerifericoSolucionN4) obj;
        return Objects.equals(tipo, otro.tipo) && Objects.equals(marca, otro.marca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, marca);
    }

    @Override
    public String toString() {
        return tipo + " (" + marca + ") [" + (averiado ? "Averiado" : "OK") + "]";
    }
}

class OrdenadorSolucionN4 {

    // * ATRIBUTOS PRIVADOS
    private String numeroSerie;
    private String marca;
    private double[] procesador;
    private int memoriaRAM;
    private HashSet<PerifericoSolucionN4> perifericos;
    private final int numeroMaximoPerifericos;
    private boolean encendido;

    // * CONSTRUCTOR
    // ? El ordenador se crea sin periféricos y apagado.
    public OrdenadorSolucionN4(String numeroSerie, String marca, int nucleos,
            double velocidad, int memoriaRAM, int numeroMaximoPerifericos) {
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.procesador = new double[] { nucleos, velocidad };
        this.memoriaRAM = memoriaRAM;
        this.numeroMaximoPerifericos = numeroMaximoPerifericos;
        this.perifericos = new HashSet<>();
        this.encendido = false;
    }

    // * GETTERS
    public String getNumeroSerie() {
        return numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public int getNucleos() {
        return (int) procesador[0];
    }

    public double getVelocidad() {
        return procesador[1];
    }

    public int getMemoriaRAM() {
        return memoriaRAM;
    }

    public int getNumeroMaximoPerifericos() {
        return numeroMaximoPerifericos;
    }

    public boolean isEncendido() {
        return encendido;
    }

    // ? No devolvemos el HashSet directamente para proteger la estructura interna.
    public String getPerifericos() {
        if (perifericos.isEmpty()) {
            return "(Sin periféricos)";
        }
        StringBuilder sb = new StringBuilder();
        for (PerifericoSolucionN4 p : perifericos) {
            sb.append(p).append(" | ");
        }
        return sb.substring(0, sb.length() - 3);
    }

    public HashSet<PerifericoSolucionN4> getPerifericosInternos() {
        return perifericos;
    }

    // * CALCULAR CONSUMO
    public double calcularConsumo() {
        return (procesador[0] * procesador[1]) / 10;
    }

    // * GESTION DE PERIFERICOS
    public boolean addPeriferico(PerifericoSolucionN4 p) {
        if (perifericos.size() >= numeroMaximoPerifericos) {
            return false;
        }
        return perifericos.add(p);
    }

    public boolean removePeriferico(PerifericoSolucionN4 p) {
        return perifericos.remove(p);
    }

    // * ACTUALIZAR NUCLEOS
    public void sumarNucleos(int cantidad) {
        procesador[0] = procesador[0] + cantidad;
    }

    public void encender() {
        encendido = true;
    }

    public void apagar() {
        encendido = false;
    }

    // * EQUALS Y HASHCODE
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrdenadorSolucionN4 otro = (OrdenadorSolucionN4) obj;
        return Objects.equals(numeroSerie, otro.numeroSerie)
                && Objects.equals(marca, otro.marca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroSerie, marca);
    }

    @Override
    public String toString() {
        return "Ordenador{numeroSerie='" + numeroSerie
                + "', marca='" + marca
                + "', nucleos=" + getNucleos()
                + ", velocidad=" + getVelocidad()
                + ", memoriaRAM=" + memoriaRAM
                + ", encendido=" + encendido
                + ", consumo=" + String.format("%.2f", calcularConsumo())
                + ", perifericos=" + getPerifericos()
                + "}";
    }
}

class GestionOrdenadoresSolucionN4 {

    // * HASHSET PRINCIPAL
    private HashSet<OrdenadorSolucionN4> equipos;

    public GestionOrdenadoresSolucionN4() {
        this.equipos = new HashSet<>();
    }

    public boolean addEquipo(OrdenadorSolucionN4 ordenador) {
        return equipos.add(ordenador);
    }

    public OrdenadorSolucionN4 buscarPorSerie(String numeroSerie) {
        for (OrdenadorSolucionN4 o : equipos) {
            if (o.getNumeroSerie().equalsIgnoreCase(numeroSerie)) {
                return o;
            }
        }
        return null;
    }

    public Set<OrdenadorSolucionN4> getEquipos() {
        return equipos;
    }

    // * ELIMINAR PERIFERICOS ESTROPEADOS
    // ? Recorremos todos los ordenadores y luego sus periféricos con Iterator.
    public int eliminarPerifericosEstropeados() {
        int eliminados = 0;

        for (OrdenadorSolucionN4 ordenador : equipos) {
            Iterator<PerifericoSolucionN4> it = ordenador.getPerifericosInternos().iterator();
            while (it.hasNext()) {
                PerifericoSolucionN4 p = it.next();
                if (p.isAveriado()) {
                    it.remove();
                    eliminados++;
                }
            }
        }

        return eliminados;
    }

    // * ELIMINAR UN PERIFERICO DE TODOS LOS ORDENADORES
    public ArrayList<String> eliminarPerifericoGlobal(String tipo, String marca) {
        ArrayList<String> afectados = new ArrayList<>();
        PerifericoSolucionN4 buscado = new PerifericoSolucionN4(tipo, marca);

        for (OrdenadorSolucionN4 ordenador : equipos) {
            if (ordenador.removePeriferico(buscado)) {
                afectados.add(ordenador.getNumeroSerie());
            }
        }

        return afectados;
    }

    // * ACTUALIZAR NUCLEOS EN TODOS LOS ORDENADORES
    public void actualizarNucleos(int cantidad) {
        for (OrdenadorSolucionN4 ordenador : equipos) {
            ordenador.sumarNucleos(cantidad);
        }
    }

    // * ELIMINAR EQUIPOS CON MAS DE DOS PERIFERICOS
    public int eliminarEquiposConMasDeDosPerifericos() {
        int eliminados = 0;
        Iterator<OrdenadorSolucionN4> it = equipos.iterator();

        while (it.hasNext()) {
            OrdenadorSolucionN4 ordenador = it.next();
            if (ordenador.getPerifericosInternos().size() > 2) {
                it.remove();
                eliminados++;
            }
        }

        return eliminados;
    }
}

public class N4_Ordenadores_Solucion {

    public static void main(String[] args) {

        GestionOrdenadoresSolucionN4 gestion = new GestionOrdenadoresSolucionN4();

        // * 1. AÑADIR UN EQUIPO
        OrdenadorSolucionN4 o1 = new OrdenadorSolucionN4("SN-001", "ASUS", 4, 3.2, 16, 4);
        OrdenadorSolucionN4 o2 = new OrdenadorSolucionN4("SN-002", "HP", 2, 2.8, 8, 3);
        gestion.addEquipo(o1);
        gestion.addEquipo(o2);

        // * 2. MOSTRAR EQUIPOS
        System.out.println("=== EQUIPOS ===");
        for (OrdenadorSolucionN4 o : gestion.getEquipos()) {
            System.out.println("Numero de Serie: " + o.getNumeroSerie() + " Datos: " + o);
        }

        // * 3. AÑADIR PERIFERICOS
        OrdenadorSolucionN4 encontrado = gestion.buscarPorSerie("SN-001");
        if (encontrado != null) {
            int añadidos = 0;
            if (encontrado.addPeriferico(new PerifericoSolucionN4("Raton", "Logitech"))) {
                añadidos++;
            }
            if (encontrado.addPeriferico(new PerifericoSolucionN4("Teclado", "Corsair"))) {
                añadidos++;
            }
            if (encontrado.addPeriferico(new PerifericoSolucionN4("Webcam", "Logitech"))) {
                añadidos++;
            }
            System.out.println("\nPeriféricos añadidos correctamente: " + añadidos);
        }

        // * 4. ELIMINAR ESTROPEADOS
        int estropeados = gestion.eliminarPerifericosEstropeados();
        System.out.println("Periféricos estropeados eliminados: " + estropeados);

        // * 5. ELIMINAR UN PERIFERICO CONCRETO
        ArrayList<String> afectados = gestion.eliminarPerifericoGlobal("Teclado", "Corsair");
        System.out.println("Ordenadores afectados por la eliminación: " + afectados);

        // * 6. MOSTRAR PERIFERICOS DE ORDENADORES CON MAS DE DOS NUCLEOS
        System.out.println("\n=== PERIFERICOS DE ORDENADORES CON MAS DE DOS NUCLEOS ===");
        for (OrdenadorSolucionN4 o : gestion.getEquipos()) {
            if (o.getNucleos() > 2) {
                System.out.println("Periféricos del ordenador con número de serie "
                        + o.getNumeroSerie() + ": " + o.getPerifericos());
            }
        }

        // * 7. ACTUALIZAR NUCLEOS
        gestion.actualizarNucleos(2);
        System.out.println("\n=== EQUIPOS TRAS ACTUALIZAR NUCLEOS ===");
        for (OrdenadorSolucionN4 o : gestion.getEquipos()) {
            System.out.println(o);
        }

        // * 8. ELIMINAR EQUIPOS CON MAS DE DOS PERIFERICOS
        int equiposEliminados = gestion.eliminarEquiposConMasDeDosPerifericos();
        System.out.println("\nEquipos eliminados por tener más de dos periféricos: " + equiposEliminados);
    }
}
