import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class P07_Ficheros {

    /*
     * =============================================================
     * TEORIA: ALMACENANDO DATOS - FICHEROS EN JAVA
     * Basado en "Almacenando datos. Ficheros.pdf"
     * =============================================================
     * * ¿Por que ficheros?
     *   Los datos en memoria (variables, objetos) se pierden al cerrar
     *   el programa. Los ficheros permiten guardar datos de forma
     *   PERSISTENTE en disco.
     *
     * * Clases principales (paquete java.io):
     *   - File          -> representa un archivo o directorio en disco.
     *   - FileWriter    -> escribe texto caracter a caracter.
     *   - BufferedWriter -> escribe texto con buffer (mas eficiente).
     *   - FileReader    -> lee texto caracter a caracter.
     *   - BufferedReader -> lee texto linea a linea (mas comodo).
     *
     * * try-with-resources (Java 7+):
     *   try (BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt"))) {
     *       bw.write("Hola");
     *   }
     *   El recurso se cierra AUTOMATICAMENTE al salir del bloque.
     *   SIEMPRE cerrar los streams para evitar perdida de datos y fugas.
     *
     * * Modos de apertura:
     *   new FileWriter("file.txt")       -> sobreescribe (borra el contenido)
     *   new FileWriter("file.txt", true) -> modo append (anade al final)
     * =============================================================
     */

    // * Better Comments:
    // * Teoria.    ! Critico.    ? Aclaracion.    TODO Alumno.    ✅ Solucion.

    // * Ruta del fichero usado en los ejemplos (en el directorio de trabajo).
    static final String FICHERO = "datos_prueba.txt";

    public static void main(String[] args) {

        // ── PARTE 1: Crear y comprobar fichero con File ───────────────────
        System.out.println("=== FILE: CREAR Y COMPROBAR ===");
        crearFichero(FICHERO);

        // ── PARTE 2: Escribir con FileWriter (sobreescritura) ─────────────
        System.out.println("\n=== ESCRIBIR (sobreescritura) ===");
        escribir(FICHERO, "Primera linea del fichero.\n", false);
        escribir(FICHERO, "Segunda linea del fichero.\n", false);
        // ! false = sobreescritura: la segunda llamada BORRA la primera linea.

        // ── PARTE 3: Escribir con append ──────────────────────────────────
        System.out.println("\n=== ESCRIBIR (append) ===");
        escribir(FICHERO, "Primera linea.\n", false);   // sobreescribe
        escribirAppend(FICHERO, "Segunda linea.\n");    // anade
        escribirAppend(FICHERO, "Tercera linea.\n");    // anade
        // TODO mini 1: añade una cuarta linea con tu nombre usando escribirAppend().
        //              Luego llama a leerFichero() aqui y comprueba que aparece.

        // ── PARTE 4: Leer con BufferedReader ──────────────────────────────
        System.out.println("\n=== LEER FICHERO ===");
        leerFichero(FICHERO);

        // ── PARTE 5: Escribir multiples lineas con BufferedWriter ─────────
        System.out.println("\n=== BUFFEREDWRITER (multiples lineas) ===");
        escribirVariasLineas(FICHERO);
        leerFichero(FICHERO);

        // ── PARTE 6: Informacion del fichero ──────────────────────────────
        System.out.println("\n=== INFO DEL FICHERO ===");
        infoFichero(FICHERO);

        // ── PARTE 7: Borrar fichero ────────────────────────────────────────
        System.out.println("\n=== BORRAR FICHERO ===");
        borrarFichero(FICHERO);
        System.out.println("Existe ahora: " + new File(FICHERO).exists());

        // TODO Alumno:
        // 1) Crea un metodo "escribirAgenda(String fichero)" que guarde
        //    al menos 3 contactos con formato "Nombre;Telefono\n" en un fichero.
        // 2) Crea un metodo "leerAgenda(String fichero)" que lea el fichero
        //    linea a linea y separe nombre y telefono con split(";").
        // 3) Prueba ambos metodos en el main.
        // 4) Anade manejo de IOException en ambos metodos.

        // ✅ Solucion orientativa (comentada)
        // escribirAgenda("agenda.txt");
        // leerAgenda("agenda.txt");
    }

    // =====================================================================
    // * Crear fichero si no existe.
    // =====================================================================
    static void crearFichero(String ruta) {
        File f = new File(ruta);
        try {
            if (f.createNewFile()) {
                System.out.println("Fichero creado: " + f.getName());
            } else {
                System.out.println("Fichero ya existe: " + f.getName());
            }
        } catch (IOException e) {
            System.out.println("Error al crear fichero: " + e.getMessage());
        }
    }

    // =====================================================================
    // * Escribir texto en fichero (sobreescritura o append segun flag).
    // * Usa try-with-resources: el FileWriter se cierra automaticamente.
    // =====================================================================
    static void escribir(String ruta, String contenido, boolean append) {
        // ! try-with-resources: el recurso declarado entre () se cierra solo.
        try (FileWriter fw = new FileWriter(ruta, append)) {
            fw.write(contenido);
            System.out.println("Escrito en '" + ruta + "': " + contenido.trim());
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }

    // =====================================================================
    // * Escribir en modo append (anade al final sin borrar lo anterior).
    // =====================================================================
    static void escribirAppend(String ruta, String linea) {
        try (FileWriter fw = new FileWriter(ruta, true)) {
            fw.write(linea);
            System.out.println("Anadido: " + linea.trim());
        } catch (IOException e) {
            System.out.println("Error al escribir (append): " + e.getMessage());
        }
    }

    // =====================================================================
    // * Escribir multiples lineas con BufferedWriter (mas eficiente).
    // * BufferedWriter.newLine() usa el salto de linea del sistema operativo.
    // =====================================================================
    static void escribirVariasLineas(String ruta) {
        // ? BufferedWriter envuelve a FileWriter para mayor rendimiento.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false))) {
            String[] lineas = {
                "Alumno: Jorge",
                "Modulo: Programacion",
                "Nota: 9",
                "Curso: 1DAM"
            };
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();   // salto de linea portable (Windows/Linux)
            }
            System.out.println("Escritas " + lineas.length + " lineas.");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }

    // =====================================================================
    // * Leer fichero linea a linea con BufferedReader.
    // =====================================================================
    static void leerFichero(String ruta) {
        File f = new File(ruta);
        if (!f.exists()) {
            System.out.println("El fichero '" + ruta + "' no existe.");
            return;
        }
        // * BufferedReader.readLine() devuelve null al llegar al final.
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int numLinea = 1;
            while ((linea = br.readLine()) != null) {
                System.out.println("  [" + numLinea++ + "] " + linea);
            }
            // TODO mini 2: añade despues del while un contador de lineas totales.
            //              Imprime: "Total: [numLinea-1] linea(s) leidas."
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }

    // =====================================================================
    // * Mostrar informacion del fichero con la clase File.
    // =====================================================================
    static void infoFichero(String ruta) {
        File f = new File(ruta);
        System.out.println("Nombre    : " + f.getName());
        System.out.println("Existe    : " + f.exists());
        System.out.println("Es fichero: " + f.isFile());
        System.out.println("Es directorio: " + f.isDirectory());
        System.out.println("Tamanio   : " + f.length() + " bytes");
        System.out.println("Ruta abs  : " + f.getAbsolutePath());
    }

    // =====================================================================
    // * Borrar fichero.
    // =====================================================================
    static void borrarFichero(String ruta) {
        File f = new File(ruta);
        if (f.delete()) {
            System.out.println("Fichero '" + ruta + "' borrado.");
        } else {
            System.out.println("No se pudo borrar '" + ruta + "'.");
        }
        // TODO mini 3: intenta borrar un fichero que no existe y observa que ocurre.
        //              Pista: new File("no_existe.txt").delete() ¿devuelve true o false?
    }
}
