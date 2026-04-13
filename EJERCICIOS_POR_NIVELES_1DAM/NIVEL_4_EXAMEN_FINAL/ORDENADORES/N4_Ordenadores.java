/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  BLOQUE DE EJERCICIOS POR NIVELES - 1 DAM
 *  NIVEL 4 - EXAMEN FINAL
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  EJERCICIO NIVEL 4 | ORDENADORES
 * ==========================================================================================
 *  Este ejercicio mezcla:
 *
 *  - clase Periferico
 *  - clase Ordenador
 *  - clase gestora con HashSet<Ordenador>
 *  - operaciones secuenciales sobre el conjunto
 *
 *  ORDEN RECOMENDADO:
 *  1. Periferico
 *  2. Ordenador
 *  3. GestionOrdenadores
 *  4. main
 * ==========================================================================================
 */

import java.util.*;

class PerifericoN4 {

    // TODO 1: Declara los atributos privados:
    // - tipo
    // - marca
    // - averiado

    // TODO 2: Implementa el constructor (tipo, marca)
    // El estado averiado se genera con Random().nextBoolean()
    public PerifericoN4(String tipo, String marca) {
        // TODO
    }

    // TODO 3: Implementa los getters

    // TODO 4: Implementa equals por tipo y marca
    @Override
    public boolean equals(Object obj) {
        // TODO
        return false;
    }

    // TODO 5: Implementa hashCode por tipo y marca
    @Override
    public int hashCode() {
        // TODO
        return 0;
    }

    // TODO 6: Implementa toString
    @Override
    public String toString() {
        // TODO
        return "";
    }
}

class OrdenadorN4 {

    // TODO 7: Declara los atributos privados:
    // - nSerie
    // - marca
    // - procesador (double[])
    // - ram
    // - perifericos (HashSet<PerifericoN4>)
    // - maxPerifericos (final int)
    // - encendido

    // TODO 8: Implementa el constructor
    public OrdenadorN4(String nSerie, String marca, int nucleos,
            double velocidad, int ram, int maxPerifericos) {
        // TODO
    }

    // TODO 9: Implementa los getters
    // getPerifericos no devuelve el HashSet directamente

    // TODO 10: Implementa calcularConsumo
    public double calcularConsumo() {
        // TODO
        return 0;
    }

    // TODO 11: Implementa addPeriferico
    public boolean addPeriferico(PerifericoN4 p) {
        // TODO
        return false;
    }

    // TODO 12: Implementa removePeriferico
    public boolean removePeriferico(PerifericoN4 p) {
        // TODO
        return false;
    }

    // TODO 13: Implementa un metodo sumarNucleos(int cantidad)

    // TODO 14: Implementa equals por nSerie y marca
    @Override
    public boolean equals(Object obj) {
        // TODO
        return false;
    }

    // TODO 15: Implementa hashCode por nSerie y marca
    @Override
    public int hashCode() {
        // TODO
        return 0;
    }

    // TODO 16: Implementa toString
    @Override
    public String toString() {
        // TODO
        return "";
    }
}

class GestionOrdenadoresN4 {

    // TODO 17: Declara el HashSet<OrdenadorN4>

    // TODO 18: Implementa el constructor
    public GestionOrdenadoresN4() {
        // TODO
    }

    // TODO 19: Implementa addEquipo
    public boolean addEquipo(OrdenadorN4 ordenador) {
        // TODO
        return false;
    }

    // TODO 20: Implementa buscarPorSerie
    public OrdenadorN4 buscarPorSerie(String nSerie) {
        // TODO
        return null;
    }

    // TODO 21: Implementa getEquipos
    public Set<OrdenadorN4> getEquipos() {
        // TODO
        return null;
    }

    // TODO 22: Implementa eliminarPerifericosEstropeados
    public int eliminarPerifericosEstropeados() {
        // TODO
        return 0;
    }

    // TODO 23: Implementa eliminarPerifericoGlobal
    // Devuelve ArrayList<String> con los números de serie afectados
    public ArrayList<String> eliminarPerifericoGlobal(String tipo, String marca) {
        // TODO
        return new ArrayList<>();
    }

    // TODO 24: Implementa actualizarNucleos
    public void actualizarNucleos(int cantidad) {
        // TODO
    }

    // TODO 25: Implementa eliminarEquiposConMasDeDosPerifericos
    public int eliminarEquiposConMasDeDosPerifericos() {
        // TODO
        return 0;
    }
}

public class N4_Ordenadores {

    public static void main(String[] args) {

        // TODO 26: Crea la clase gestora

        // TODO 27: Añade un equipo

        // TODO 28: Muestra los datos de todos los equipos

        // TODO 29: Añade periféricos a un equipo buscando por número de serie

        // TODO 30: Elimina periféricos estropeados de todos los equipos

        // TODO 31: Elimina un periférico concreto de cualquier equipo

        // TODO 32: Muestra periféricos de ordenadores con más de dos núcleos

        // TODO 33: Añade 2 núcleos a todos los ordenadores

        // TODO 34: Elimina equipos con más de dos periféricos
    }
}
