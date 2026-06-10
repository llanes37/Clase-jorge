import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EXAMEN_FINAL {

    /*
     * =============================================================
     * EXAMEN FINAL - PROGRAMACION 1DAM
     * Engloba todos los temas: P01-P09
     * =============================================================
     * * Contexto:
     *   Sistema de gestion de una ACADEMIA DE MUSICA.
     *   La academia tiene instrumentos de distintos tipos,
     *   alumnos que se matriculan en cursos, y guarda los datos
     *   en un fichero.
     *
     * * Temas cubiertos:
     *   P01 - Herencia:            Instrumento -> Cuerda, Viento, Percusion
     *   P02 - Polimorfismo:        Array de Instrumento, cada uno toca()
     *   P03 - Interfaces:          Afinador, Grabable
     *   P04 - Equals/hashCode:     Alumno comparado por DNI
     *   P05 - Excepciones lanzar:  try-catch-finally, throw, throws
     *   P06 - Jerarquia excepc.:   MatriculaException, StockException (propias)
     *   P07 - Ficheros:            Guardar y leer alumnos en fichero
     *   P08 - Genericos:           Repositorio<T> generico
     *   P09 - Recursividad:        Busqueda recursiva en lista
     * =============================================================
     */

    // * Better Comments:
    // * Teoria.    ! Critico.    ? Aclaracion.    TODO Alumno.    ✅ Solucion.

    static final String FICHERO_ALUMNOS = "alumnos_academia.txt";

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║  ACADEMIA DE MUSICA - SISTEMA        ║");
        System.out.println("╚══════════════════════════════════════╝");

        // ── BLOQUE 1: HERENCIA + POLIMORFISMO ─────────────────────────────
        System.out.println("\n--- BLOQUE 1: HERENCIA + POLIMORFISMO ---");
        Instrumento guitarra = new Cuerda("Guitarra Española", 350.0, 6);
        Instrumento flauta   = new Viento("Flauta Travesera",  280.0, "madera");
        Instrumento bateria  = new Percusion("Bateria Rock",   1200.0, 5);

        // * Array polimorfico: todos son Instrumento.
        Instrumento[] instrumentos = { guitarra, flauta, bateria };
        for (Instrumento i : instrumentos) {
            i.tocar();     // polimorfismo: cada uno toca a su manera
        }

        System.out.println();
        for (Instrumento i : instrumentos) {
            System.out.println(i);   // toString() de cada subclase
        }

        // ── BLOQUE 2: INTERFACES ──────────────────────────────────────────
        System.out.println("\n--- BLOQUE 2: INTERFACES ---");
        // * Guitarra implementa tanto Afinador como Grabable.
        if (guitarra instanceof Afinador) {
            ((Afinador) guitarra).afinar();
        }
        if (guitarra instanceof Grabable) {
            ((Grabable) guitarra).iniciarGrabacion();
            ((Grabable) guitarra).detenerGrabacion();
        }

        // ── BLOQUE 3: EQUALS Y HASHCODE ───────────────────────────────────
        System.out.println("\n--- BLOQUE 3: EQUALS Y HASHCODE ---");
        Alumno a1 = new Alumno("12345678A", "Ana Garcia",  22);
        Alumno a2 = new Alumno("12345678A", "Ana G.",      22);  // mismo DNI
        Alumno a3 = new Alumno("87654321B", "Luis Perez",  20);

        System.out.println("a1.equals(a2): " + a1.equals(a2));   // true (mismo DNI)
        System.out.println("a1.equals(a3): " + a1.equals(a3));   // false
        System.out.println("hashCode iguales: " + (a1.hashCode() == a2.hashCode()));

        // ── BLOQUE 4: EXCEPCIONES ─────────────────────────────────────────
        System.out.println("\n--- BLOQUE 4: EXCEPCIONES ---");
        Academia academia = new Academia("Harmonia");

        // Matricula correcta
        try {
            academia.matricular(a1, "Guitarra basica");
            academia.matricular(a3, "Piano iniciacion");
        } catch (MatriculaException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Proceso de matricula finalizado.");
        }

        // Matricula duplicada (lanza excepcion)
        try {
            academia.matricular(a1, "Guitarra basica");
        } catch (MatriculaException e) {
            System.out.println("Capturada MatriculaException: " + e.getMessage());
        } finally {
            System.out.println("Segundo intento finalizado.");
        }

        // ── BLOQUE 5: REPOSITORIO GENERICO ───────────────────────────────
        System.out.println("\n--- BLOQUE 5: GENERICOS ---");
        Repositorio<Alumno> repoAlumnos = new Repositorio<>();
        repoAlumnos.agregar(a1);
        repoAlumnos.agregar(a3);
        System.out.println("Total alumnos: " + repoAlumnos.tamano());
        System.out.println("Primero: " + repoAlumnos.obtener(0));

        Repositorio<Instrumento> repoInstr = new Repositorio<>();
        for (Instrumento i : instrumentos) repoInstr.agregar(i);
        System.out.println("Total instrumentos: " + repoInstr.tamano());

        // ── BLOQUE 6: RECURSIVIDAD ────────────────────────────────────────
        System.out.println("\n--- BLOQUE 6: RECURSIVIDAD ---");
        // Busqueda recursiva de alumno por DNI
        Alumno encontrado = buscarAlumnoPorDNI(repoAlumnos.getLista(), "87654321B", 0);
        if (encontrado != null) {
            System.out.println("Alumno encontrado: " + encontrado.getNombre());
        } else {
            System.out.println("Alumno no encontrado.");
        }

        // Contar instrumentos de un tipo
        int nCuerdas = contarTipo(repoInstr.getLista(), Cuerda.class, 0);
        System.out.println("Instrumentos de cuerda: " + nCuerdas);

        // ── BLOQUE 7: FICHEROS ────────────────────────────────────────────
        System.out.println("\n--- BLOQUE 7: FICHEROS ---");
        guardarAlumnos(FICHERO_ALUMNOS, repoAlumnos.getLista());
        System.out.println("Alumnos recuperados del fichero:");
        leerAlumnos(FICHERO_ALUMNOS);
        new File(FICHERO_ALUMNOS).delete();  // limpieza

        // ── RESUMEN FINAL ─────────────────────────────────────────────────
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║  FIN DEL EXAMEN FINAL                ║");
        System.out.println("╚══════════════════════════════════════╝");

        // TODO Alumno - Tareas finales:
        // 1) Crea un nuevo tipo de instrumento "Teclado extends Instrumento"
        //    con atributo int numTeclas. Sobreescribe tocar() y toString().
        //    Implementa la interfaz Afinador.
        // 2) Matricula a un AlumnoVIP (subclase de Alumno con atributo descuento).
        //    Sobreescribe equals() usando super.equals().
        // 3) Crea un metodo recursivo "totalPrecio(List<Instrumento>, int)"
        //    que sume los precios de todos los instrumentos del repositorio.
        // 4) Guarda los instrumentos en un fichero "instrumentos.txt"
        //    con formato "Nombre;Precio\n" y leelos de vuelta.

        // ✅ Solucion orientativa (comentada)
        // Instrumento teclado = new Teclado("Yamaha P-45", 599.0, 88);
        // teclado.tocar();
        // ((Afinador) teclado).afinar();
        // double total = totalPrecio(repoInstr.getLista(), 0);
        // System.out.println("Total: " + total);
    }

    // ─── METODOS AUXILIARES ─────────────────────────────────────────────

    // * Busqueda recursiva de Alumno por DNI en una lista.
    static Alumno buscarAlumnoPorDNI(List<Alumno> lista, String dni, int indice) {
        if (indice >= lista.size()) return null;          // caso base: no encontrado
        if (lista.get(indice).getDni().equals(dni)) return lista.get(indice);
        return buscarAlumnoPorDNI(lista, dni, indice + 1); // caso recursivo
    }

    // * Cuenta recursivamente cuantos objetos son de un tipo dado.
    @SuppressWarnings("unchecked")
    static <T> int contarTipo(List<T> lista, Class<?> tipo, int indice) {
        if (indice >= lista.size()) return 0;
        int esDelTipo = tipo.isInstance(lista.get(indice)) ? 1 : 0;
        return esDelTipo + contarTipo(lista, tipo, indice + 1);
    }

    // * Guardar lista de alumnos en fichero CSV.
    static void guardarAlumnos(String ruta, List<Alumno> alumnos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false))) {
            for (Alumno a : alumnos) {
                bw.write(a.getDni() + ";" + a.getNombre() + ";" + a.getEdad());
                bw.newLine();
            }
            System.out.println("Alumnos guardados en '" + ruta + "'.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // * Leer alumnos desde fichero CSV.
    static void leerAlumnos(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    System.out.println("  DNI=" + partes[0] + " Nombre=" + partes[1] + " Edad=" + partes[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }

    // =====================================================================
    // * INTERFACES
    // =====================================================================
    interface Afinador {
        void afinar();
    }

    interface Grabable {
        void iniciarGrabacion();
        void detenerGrabacion();
    }

    // =====================================================================
    // * JERARQUIA DE INSTRUMENTOS (Herencia)
    // =====================================================================
    static abstract class Instrumento {
        protected String nombre;
        protected double precio;

        public Instrumento(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = Math.max(precio, 0);
        }

        // * Metodo abstracto: cada subclase lo implementa a su manera.
        public abstract void tocar();

        public String getNombre() { return nombre; }
        public double getPrecio() { return precio; }

        @Override
        public String toString() {
            return nombre + " [" + getClass().getSimpleName() + "] - " + precio + " EUR";
        }
    }

    // * Instrumento de cuerda: implementa Afinador y Grabable.
    static class Cuerda extends Instrumento implements Afinador, Grabable {
        private int numCuerdas;

        public Cuerda(String nombre, double precio, int numCuerdas) {
            super(nombre, precio);
            this.numCuerdas = numCuerdas;
        }

        @Override public void tocar()              { System.out.println(nombre + ": Tachán tachán! (" + numCuerdas + " cuerdas)"); }
        @Override public void afinar()             { System.out.println(nombre + " afinada."); }
        @Override public void iniciarGrabacion()   { System.out.println(nombre + ": grabando..."); }
        @Override public void detenerGrabacion()   { System.out.println(nombre + ": grabacion detenida."); }
        @Override public String toString()         { return super.toString() + ", cuerdas=" + numCuerdas; }
    }

    static class Viento extends Instrumento {
        private String material;

        public Viento(String nombre, double precio, String material) {
            super(nombre, precio);
            this.material = material;
        }

        @Override public void tocar()      { System.out.println(nombre + ": Fiuuuu! (material=" + material + ")"); }
        @Override public String toString() { return super.toString() + ", material=" + material; }
    }

    static class Percusion extends Instrumento {
        private int numPiezas;

        public Percusion(String nombre, double precio, int numPiezas) {
            super(nombre, precio);
            this.numPiezas = numPiezas;
        }

        @Override public void tocar()      { System.out.println(nombre + ": BUM BUM CRASH! (" + numPiezas + " piezas)"); }
        @Override public String toString() { return super.toString() + ", piezas=" + numPiezas; }
    }

    // =====================================================================
    // * ALUMNO: equals() y hashCode() por DNI
    // =====================================================================
    static class Alumno {
        private String dni;
        private String nombre;
        private int    edad;

        public Alumno(String dni, String nombre, int edad) {
            this.dni    = dni;
            this.nombre = nombre;
            this.edad   = Math.max(edad, 0);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Alumno otro = (Alumno) obj;
            return Objects.equals(dni, otro.dni);
        }

        @Override public int hashCode()     { return Objects.hash(dni); }
        public String getDni()              { return dni; }
        public String getNombre()           { return nombre; }
        public int    getEdad()             { return edad; }
        @Override public String toString()  { return "Alumno{dni='" + dni + "', nombre='" + nombre + "'}"; }
    }

    // =====================================================================
    // * ACADEMIA: gestiona matriculas y lanza excepciones propias
    // =====================================================================
    static class Academia {
        private String nombre;
        private List<String> matriculas = new ArrayList<>();

        public Academia(String nombre) { this.nombre = nombre; }

        // ! throws MatriculaException: el que llame debe manejarlo (checked).
        public void matricular(Alumno alumno, String curso) throws MatriculaException {
            String clave = alumno.getDni() + "-" + curso;
            if (matriculas.contains(clave)) {
                throw new MatriculaException(alumno.getNombre(), curso);
            }
            matriculas.add(clave);
            System.out.println("Matriculado '" + alumno.getNombre() + "' en '" + curso + "'.");
        }
    }

    // =====================================================================
    // * EXCEPCION PROPIA CHECKED: MatriculaException
    // =====================================================================
    static class MatriculaException extends Exception {
        private final String nombreAlumno;
        private final String curso;

        public MatriculaException(String nombreAlumno, String curso) {
            super("El alumno '" + nombreAlumno + "' ya esta matriculado en '" + curso + "'.");
            this.nombreAlumno = nombreAlumno;
            this.curso        = curso;
        }

        public String getNombreAlumno() { return nombreAlumno; }
        public String getCurso()        { return curso; }
    }

    // =====================================================================
    // * REPOSITORIO GENERICO <T>
    // =====================================================================
    static class Repositorio<T> {
        private List<T> lista = new ArrayList<>();

        public void agregar(T elemento) { lista.add(elemento); }
        public T    obtener(int i)      { return lista.get(i); }
        public int  tamano()            { return lista.size(); }
        public List<T> getLista()       { return lista; }

        @Override public String toString() { return "Repositorio" + lista; }
    }
}
