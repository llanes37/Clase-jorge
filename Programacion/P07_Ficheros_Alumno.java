import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class P07_Ficheros_Alumno {

    /*
     * =============================================================
     * PLANTILLA ALUMNO - FICHEROS
     * =============================================================
     * * Instrucciones:
     *   1. Implementa cada metodo en orden.
     *   2. Ejecuta el main para ver si crea, escribe y lee correctamente.
     *   3. Compara con P07_Ficheros.java al terminar.
     *
     * * Recuerda siempre usar try-with-resources:
     *   try (BufferedWriter bw = new BufferedWriter(new FileWriter("f.txt"))) {
     *       bw.write("...");
     *   }
     * =============================================================
     */

    // ! Siempre cerrar streams -> usa try-with-resources (el recurso se cierra solo).
    // ! IOException es CHECKED: obligatorio manejarla con try-catch.
    // ! new FileWriter("f", false) -> sobreescribe | new FileWriter("f", true) -> append

    static final String FICHERO = "practica_ficheros.txt";

    public static void main(String[] args) {

        System.out.println("=== CREAR FICHERO ===");
        // TODO 1: llama a crearFichero(FICHERO)

        System.out.println("\n=== ESCRIBIR (sobreescritura) ===");
        // TODO 2: llama a escribir(FICHERO, "Primera linea.\n", false)
        //         luego escribir(FICHERO, "Segunda linea.\n", false)
        //         Observa: la segunda llamada BORRA la primera.

        System.out.println("\n=== ESCRIBIR (append) ===");
        // TODO 3: llama a escribir(FICHERO, "Primera linea.\n", false)   // sobreescribe
        //         luego escribirAppend(FICHERO, "Segunda linea.\n")
        //         luego escribirAppend(FICHERO, "Tercera linea.\n")

        System.out.println("\n=== LEER FICHERO ===");
        // TODO 4: llama a leerFichero(FICHERO)

        System.out.println("\n=== BUFFEREDWRITER (multiples lineas) ===");
        // TODO 5: llama a escribirVariasLineas(FICHERO)
        //         luego leerFichero(FICHERO)

        System.out.println("\n=== INFO DEL FICHERO ===");
        // TODO 6: llama a infoFichero(FICHERO)

        System.out.println("\n=== BORRAR FICHERO ===");
        // TODO 7: llama a borrarFichero(FICHERO)
        //         Luego imprime si el fichero existe: new File(FICHERO).exists()
    }

    // =====================================================================
    // TODO Alumno A: implementa crearFichero(String ruta)
    //   Crea un objeto File(ruta).
    //   Llama a archivo.createNewFile() dentro de un try-catch(IOException).
    //   Si retorna true  -> imprime "Fichero creado: [nombre]"
    //   Si retorna false -> imprime "Fichero ya existe: [nombre]"
    // =====================================================================
    static void crearFichero(String ruta) {
        // TODO
    }

    // =====================================================================
    // TODO Alumno B: implementa escribir(String ruta, String contenido, boolean append)
    //   Usa try-with-resources con FileWriter(ruta, append).
    //   Escribe contenido con fw.write(contenido).
    //   Imprime: "Escrito en '[ruta]': [contenido.trim()]"
    //   Captura IOException.
    // =====================================================================
    static void escribir(String ruta, String contenido, boolean append) {
        // TODO: try (FileWriter fw = new FileWriter(ruta, append)) { ... }
    }

    // =====================================================================
    // TODO Alumno C: implementa escribirAppend(String ruta, String linea)
    //   Igual que escribir() pero con append = true siempre.
    //   Imprime: "Anadido: [linea.trim()]"
    // =====================================================================
    static void escribirAppend(String ruta, String linea) {
        // TODO
    }

    // =====================================================================
    // TODO Alumno D: implementa escribirVariasLineas(String ruta)
    //   Usa try-with-resources con BufferedWriter(new FileWriter(ruta, false)).
    //   Escribe estas 4 lineas con bw.write() y bw.newLine() despues de cada una:
    //     "Alumno: Jorge"
    //     "Modulo: Programacion"
    //     "Nota: 9"
    //     "Curso: 1DAM"
    //   Al final imprime "Escritas 4 lineas."
    // =====================================================================
    static void escribirVariasLineas(String ruta) {
        // TODO: try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false))) { ... }
    }

    // =====================================================================
    // TODO Alumno E: implementa leerFichero(String ruta)
    //   Comprueba primero si el fichero existe con new File(ruta).exists().
    //   Si no existe, imprime "El fichero no existe." y retorna.
    //   Usa try-with-resources con BufferedReader(new FileReader(ruta)).
    //   Lee linea a linea con br.readLine() != null.
    //   Imprime cada linea con su numero: "  [1] linea..."
    // =====================================================================
    static void leerFichero(String ruta) {
        // TODO
    }

    // =====================================================================
    // TODO Alumno F: implementa infoFichero(String ruta)
    //   Crea File f = new File(ruta) y muestra:
    //     Nombre, Existe, Es fichero, Es directorio, Tamanio, Ruta absoluta.
    // =====================================================================
    static void infoFichero(String ruta) {
        // TODO: File f = new File(ruta); luego imprime f.getName(), f.exists(), etc.
    }

    // =====================================================================
    // TODO Alumno G: implementa borrarFichero(String ruta)
    //   Si f.delete() devuelve true  -> imprime "Fichero borrado."
    //   Si devuelve false            -> imprime "No se pudo borrar."
    // =====================================================================
    static void borrarFichero(String ruta) {
        // TODO
    }
}
