/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  BLOQUE DE EJERCICIOS POR NIVELES - 1 DAM
 *  NIVEL 4 - EXAMEN FINAL
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  EJERCICIO NIVEL 4 | RED SOCIAL
 * ==========================================================================================
 *  Este ejercicio mezcla varias clases y varias colecciones:
 *
 *  - Usuario
 *  - Foto
 *  - GestionFotos
 *
 *  ESTRUCTURAS QUE APARECEN:
 *  - HashSet<Usuario>
 *  - HashMap<Usuario, List<Foto>>
 *
 *  ORDEN RECOMENDADO:
 *  1. Usuario
 *  2. Foto
 *  3. GestionFotos
 *  4. main
 * ==========================================================================================
 */

import java.util.*;

class Usuario {

    // TODO 1: Declara los atributos privados:
    // - nick
    // - nombre
    // - apellidos

    // TODO 2: Implementa el constructor
    public Usuario(String nick, String nombre, String apellidos) {
        // TODO
    }

    // TODO 3: Implementa los getters

    // TODO 4: Implementa equals por nick
    @Override
    public boolean equals(Object obj) {
        // TODO
        return false;
    }

    // TODO 5: Implementa hashCode por nick
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

class Foto {

    // TODO 7: Declara los atributos privados:
    // - nombre (String[])
    // - formato
    // - tamanio
    // - etiquetados (HashSet<Usuario>)
    // - likes

    // TODO 8: Implementa el constructor completo
    public Foto(String[] nombre, String formato, double tamanio) {
        // TODO
    }

    // TODO 9: Implementa el constructor sobrecargado
    public Foto(String[] nombre, double tamanio) {
        // TODO
    }

    // TODO 10: Implementa los getters
    // getUsuarios devuelve String, no el HashSet

    // TODO 11: Implementa like
    public void like() {
        // TODO
    }

    // TODO 12: Implementa addEtiquetado
    public boolean addEtiquetado(Usuario u) {
        // TODO
        return false;
    }

    // TODO 13: Implementa removeEtiquetado
    public boolean removeEtiquetado(Usuario u) {
        // TODO
        return false;
    }

    // TODO 14: Implementa equals
    @Override
    public boolean equals(Object obj) {
        // TODO
        return false;
    }

    // TODO 15: Implementa hashCode
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

class GestionFotos {

    // TODO 17: Declara el HashMap<Usuario, List<Foto>>

    // TODO 18: Implementa el constructor
    public GestionFotos() {
        // TODO
    }

    // TODO 19: Implementa addUsuario
    public boolean addUsuario(Usuario u) {
        // TODO
        return false;
    }

    // TODO 20: Implementa addFoto
    public boolean addFoto(Usuario u, Foto foto) {
        // TODO
        return false;
    }

    // TODO 21: Implementa getFotosUsuario
    public List<Foto> getFotosUsuario(Usuario u) {
        // TODO
        return null;
    }

    // TODO 22: Implementa darLike
    public int darLike(Usuario u, Foto foto) {
        // TODO
        return -1;
    }

    // TODO 23: Implementa etiquetar
    public boolean etiquetar(Foto foto, Usuario u) {
        // TODO
        return false;
    }

    // TODO 24: Implementa eliminar(Usuario u, double tamMax)
    public HashSet<Foto> eliminar(Usuario u, double tamMax) {
        // TODO
        return new HashSet<>();
    }

    // TODO 25: Implementa eliminar(double tamMax)
    public HashSet<Foto> eliminar(double tamMax) {
        // TODO
        return new HashSet<>();
    }
}

public class N4_Red_Social {

    public static void main(String[] args) {

        // TODO 26: Crea varios usuarios y muestralos

        // TODO 27: Crea varias fotos con y sin formato especificado

        // TODO 28: Crea la red social

        // TODO 29: Añade usuarios

        // TODO 30: Añade fotos a usuarios

        // TODO 31: Da likes a una foto

        // TODO 32: Etiqueta usuarios en una foto

        // TODO 33: Muestra las fotos de un usuario

        // TODO 34: Elimina fotos de un usuario por tamaño

        // TODO 35: Elimina fotos de toda la red por tamaño
    }
}
