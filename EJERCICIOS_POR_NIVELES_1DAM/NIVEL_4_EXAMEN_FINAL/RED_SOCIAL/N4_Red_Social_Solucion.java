/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  BLOQUE DE EJERCICIOS POR NIVELES - 1 DAM
 *  NIVEL 4 - EXAMEN FINAL - SOLUCION
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  SOLUCION | RED SOCIAL
 * ==========================================================================================
 *  Este ejercicio es mas largo que los anteriores y obliga a pensar por capas:
 *
 *  1. Clase Usuario
 *  2. Clase Foto
 *  3. Clase GestionFotos
 *  4. Pruebas desde main
 *
 *  IDEA CLAVE:
 *  - Usuario y Foto son clases de dominio
 *  - GestionFotos es la clase gestora
 * ==========================================================================================
 */

import java.util.*;

class UsuarioSolucionRS {

    // * ATRIBUTOS PRIVADOS
    private String nick;
    private String nombre;
    private String apellidos;

    // * CONSTRUCTOR
    public UsuarioSolucionRS(String nick, String nombre, String apellidos) {
        this.nick = nick;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    // * GETTERS
    public String getNick() {
        return nick;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    // * EQUALS Y HASHCODE
    // ? Dos usuarios son iguales si tienen el mismo nick.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UsuarioSolucionRS otro = (UsuarioSolucionRS) obj;
        return Objects.equals(nick, otro.nick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick);
    }

    @Override
    public String toString() {
        return "@" + nick + " (" + nombre + " " + apellidos + ")";
    }
}

class FotoSolucionRS {

    // * ATRIBUTOS PRIVADOS
    private String[] nombre;
    private String formato;
    private double tamanio;
    private HashSet<UsuarioSolucionRS> usuariosEtiquetados;
    private int likes;

    // * CONSTRUCTOR COMPLETO
    // ? La foto siempre se crea sin etiquetados y sin likes.
    public FotoSolucionRS(String[] nombre, String formato, double tamanio) {
        this.nombre = nombre;
        this.formato = formato;
        this.tamanio = tamanio;
        this.usuariosEtiquetados = new HashSet<>();
        this.likes = 0;
    }

    // * CONSTRUCTOR SOBRECARGADO
    // ? Si no llega formato, usamos jpg por defecto.
    public FotoSolucionRS(String[] nombre, double tamanio) {
        this(nombre, "jpg", tamanio);
    }

    // * GETTERS
    public String[] getNombre() {
        return nombre;
    }

    public String getFormato() {
        return formato;
    }

    public double getTamanio() {
        return tamanio;
    }

    public int getLikes() {
        return likes;
    }

    public String getUsuarios() {
        if (usuariosEtiquetados.isEmpty()) {
            return "(ninguno)";
        }

        StringBuilder sb = new StringBuilder();
        for (UsuarioSolucionRS u : usuariosEtiquetados) {
            sb.append(u).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    // * LIKE
    public void like() {
        likes++;
    }

    // * GESTION DE ETIQUETADOS
    // ? HashSet.add() devuelve false si ya existia.
    public boolean addEtiquetado(UsuarioSolucionRS u) {
        return usuariosEtiquetados.add(u);
    }

    public boolean removeEtiquetado(UsuarioSolucionRS u) {
        return usuariosEtiquetados.remove(u);
    }

    // * EQUALS Y HASHCODE
    // ? Como nombre es un array, hay que usar Arrays.equals y Arrays.hashCode.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FotoSolucionRS otra = (FotoSolucionRS) obj;
        return Arrays.equals(nombre, otra.nombre)
                && Objects.equals(formato, otra.formato)
                && Double.compare(tamanio, otra.tamanio) == 0;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(formato, tamanio);
        result = 31 * result + Arrays.hashCode(nombre);
        return result;
    }

    @Override
    public String toString() {
        return "Foto{nombre=" + Arrays.toString(nombre)
                + ", formato='" + formato + '\''
                + ", tamanio=" + tamanio
                + ", likes=" + likes
                + ", usuariosEtiquetados=" + getUsuarios()
                + "}";
    }
}

class GestionFotosSolucionRS {

    // * HASHMAP PRINCIPAL
    // ? La clave es el usuario y el valor es la lista de sus fotos.
    private HashMap<UsuarioSolucionRS, List<FotoSolucionRS>> red;

    public GestionFotosSolucionRS() {
        this.red = new HashMap<>();
    }

    // * ADD USUARIO
    // ? Si ya existe, no lo reemplazamos porque perderiamos sus fotos.
    public boolean addUsuario(UsuarioSolucionRS u) {
        if (red.containsKey(u)) {
            return false;
        }
        red.put(u, new ArrayList<>());
        return true;
    }

    // * ADD FOTO
    public boolean addFoto(UsuarioSolucionRS u, FotoSolucionRS foto) {
        List<FotoSolucionRS> fotos = red.get(u);
        if (fotos == null) {
            return false;
        }
        fotos.add(foto);
        return true;
    }

    public List<FotoSolucionRS> getFotosUsuario(UsuarioSolucionRS u) {
        return red.get(u);
    }

    // * DAR LIKE A UNA FOTO DE UN USUARIO
    public int darLike(UsuarioSolucionRS u, FotoSolucionRS foto) {
        List<FotoSolucionRS> fotos = red.get(u);
        if (fotos == null) {
            return -1;
        }

        for (FotoSolucionRS f : fotos) {
            if (f.equals(foto)) {
                f.like();
                return f.getLikes();
            }
        }

        return -1;
    }

    // * ETIQUETAR
    // ? Recorremos todas las listas del mapa porque no sabemos de quien es la foto.
    public boolean etiquetar(FotoSolucionRS foto, UsuarioSolucionRS u) {
        for (List<FotoSolucionRS> listaFotos : red.values()) {
            for (FotoSolucionRS f : listaFotos) {
                if (f.equals(foto)) {
                    return f.addEtiquetado(u);
                }
            }
        }
        return false;
    }

    // * ELIMINAR FOTOS DE UN USUARIO
    // ? Usamos Iterator porque vamos a borrar mientras recorremos.
    public HashSet<FotoSolucionRS> eliminar(UsuarioSolucionRS u, double tamMax) {
        HashSet<FotoSolucionRS> eliminadas = new HashSet<>();
        List<FotoSolucionRS> fotos = red.get(u);

        if (fotos == null) {
            return eliminadas;
        }

        Iterator<FotoSolucionRS> it = fotos.iterator();
        while (it.hasNext()) {
            FotoSolucionRS f = it.next();
            if (f.getTamanio() > tamMax) {
                eliminadas.add(f);
                it.remove();
            }
        }

        return eliminadas;
    }

    // * SOBRECARGA: ELIMINAR DE TODA LA RED
    public HashSet<FotoSolucionRS> eliminar(double tamMax) {
        HashSet<FotoSolucionRS> eliminadas = new HashSet<>();

        for (UsuarioSolucionRS u : red.keySet()) {
            eliminadas.addAll(eliminar(u, tamMax));
        }

        return eliminadas;
    }
}

public class N4_Red_Social_Solucion {

    public static void main(String[] args) {

        // * PASO 1: CREAR USUARIOS
        UsuarioSolucionRS u1 = new UsuarioSolucionRS("ana_g", "Ana", "Garcia");
        UsuarioSolucionRS u2 = new UsuarioSolucionRS("luis_r", "Luis", "Ruiz");
        UsuarioSolucionRS u3 = new UsuarioSolucionRS("marta_s", "Marta", "Soler");

        System.out.println("=== USUARIOS ===");
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);

        // * PASO 2: CREAR FOTOS
        FotoSolucionRS f1 = new FotoSolucionRS(new String[] { "IMG", "001" }, "png", 2.5);
        FotoSolucionRS f2 = new FotoSolucionRS(new String[] { "IMG", "002" }, 3.8);
        FotoSolucionRS f3 = new FotoSolucionRS(new String[] { "VAC", "010" }, "jpg", 5.2);
        FotoSolucionRS f4 = new FotoSolucionRS(new String[] { "PER", "011" }, "gif", 1.1);

        // * PASO 3: CREAR LA RED SOCIAL
        GestionFotosSolucionRS red = new GestionFotosSolucionRS();

        System.out.println("\n=== ALTA DE USUARIOS ===");
        System.out.println(red.addUsuario(u1));
        System.out.println(red.addUsuario(u2));
        System.out.println(red.addUsuario(u1));

        // * PASO 4: AÑADIR FOTOS
        red.addFoto(u1, f1);
        red.addFoto(u1, f2);
        red.addFoto(u2, f3);
        red.addFoto(u2, f4);

        // * PASO 5: DAR LIKES
        red.darLike(u1, f1);
        red.darLike(u1, f1);
        red.darLike(u1, f1);

        // * PASO 6: ETIQUETAR
        red.etiquetar(f1, u2);
        red.etiquetar(f1, u3);

        // * PASO 7: MOSTRAR FOTOS DE UN USUARIO
        System.out.println("\n=== FOTOS DE ANA ===");
        System.out.println(red.getFotosUsuario(u1));

        // * PASO 8: ELIMINAR POR TAMAÑO DE UN USUARIO
        System.out.println("\n=== ELIMINADAS DE ANA (> 3 MB) ===");
        System.out.println(red.eliminar(u1, 3.0));
        System.out.println("Fotos restantes de Ana:");
        System.out.println(red.getFotosUsuario(u1));

        // * PASO 9: ELIMINAR EN TODA LA RED
        System.out.println("\n=== ELIMINADAS EN TODA LA RED (> 4 MB) ===");
        System.out.println(red.eliminar(4.0));
        System.out.println("Fotos restantes de Luis:");
        System.out.println(red.getFotosUsuario(u2));
    }
}
