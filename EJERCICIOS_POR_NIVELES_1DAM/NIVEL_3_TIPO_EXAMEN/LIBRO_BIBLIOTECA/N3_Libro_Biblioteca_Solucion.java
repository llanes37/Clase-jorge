/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  BLOQUE DE EJERCICIOS POR NIVELES - 1 DAM
 *  NIVEL 3 - TIPO EXAMEN - SOLUCION
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  SOLUCION | LIBROS + ARRAY DE OBJETOS + METODOS
 * ==========================================================================================
 *  Esta solucion esta pensada para ser explicada en clase.
 *
 *  NO SOLO MUESTRA EL CODIGO FINAL:
 *  Tambien intenta dejar muy claro el orden mental correcto del ejercicio.
 *
 *  PATRON QUE SE ESTA ENTRENANDO:
 *  1. Creo la clase del objeto
 *  2. Creo varios objetos concretos
 *  3. Los guardo en un array
 *  4. Recorro el array para mostrar, contar, calcular y buscar
 *  5. Encuentro un objeto y modifico su estado
 *
 *  Este ultimo punto es importante:
 *  no solo consultamos datos, tambien modificamos un objeto buscado.
 * ==========================================================================================
 */

public class N3_Libro_Biblioteca_Solucion {

    public static void main(String[] args) {

        // * PASO 1: CREAR EL ARRAY DE LIBROS
        // ? Este array sera el contenedor general de la biblioteca.
        // ? Cada posicion podra guardar un objeto LibroBibliotecaSolucion.
        LibroBibliotecaSolucion[] libros = new LibroBibliotecaSolucion[5];

        // * PASO 2: CREAR Y GUARDAR OBJETOS
        // ? Cada objeto representa un libro concreto con sus datos.
        libros[0] = new LibroBibliotecaSolucion("Java Basico", "Marta Gil", 220, false);
        libros[1] = new LibroBibliotecaSolucion("POO desde cero", "Luis Ramos", 310, true);
        libros[2] = new LibroBibliotecaSolucion("Bases de Datos", "Ana Soler", 280, false);
        libros[3] = new LibroBibliotecaSolucion("Entornos de Desarrollo", "Pablo Rey", 190, false);

        // * PASO 3: MOSTRAR TODOS LOS LIBROS
        // ? Este metodo es el primero que conviene probar porque verifica:
        // ? - que el array existe
        // ? - que los objetos estan bien guardados
        // ? - que toString() esta bien construido
        System.out.println("=== LIBROS INICIALES ===");
        mostrarLibros(libros);

        // * PASO 4: CONTAR LIBROS DISPONIBLES
        // ? Aqui el patron es el de siempre:
        // ? contador + recorrido + condicion
        int disponibles = contarDisponibles(libros);
        System.out.println("\nLibros disponibles: " + disponibles);

        // * PASO 5: CALCULAR MEDIA DE PAGINAS
        // ? Otro patron basico:
        // ? suma + contador + division final
        double mediaPaginas = calcularMediaPaginas(libros);
        System.out.println("Media de paginas: " + mediaPaginas);

        // * PASO 6: BUSCAR UN LIBRO POR TITULO
        // ? La idea es recorrer hasta encontrar el libro correcto.
        LibroBibliotecaSolucion buscado = buscarPorTitulo(libros, "Bases de Datos");
        System.out.println("\nLibro buscado:");
        System.out.println(buscado);

        // * PASO 7: OBTENER EL LIBRO MAS LARGO
        // ? Aqui no buscamos una coincidencia exacta.
        // ? Vamos comparando y nos quedamos con el mejor candidato.
        LibroBibliotecaSolucion masLargo = obtenerLibroMasLargo(libros);
        System.out.println("\nLibro con mas paginas:");
        System.out.println(masLargo);

        // * PASO 8: MODIFICAR EL ESTADO DE UN LIBRO BUSCANDOLO POR TITULO
        // ? Este paso es muy didactico:
        // ? primero localizamos el objeto correcto y despues lo modificamos.
        marcarPrestadoPorTitulo(libros, "Java Basico");

        // * PASO 9: VOLVER A MOSTRAR EL ARRAY
        // ? Asi comprobamos visualmente que el cambio se ha aplicado.
        System.out.println("\n=== LIBROS TRAS PRESTAR UNO ===");
        mostrarLibros(libros);
    }

    // * METODO 1: MOSTRAR TODOS LOS LIBROS
    // ? Recorre el array y muestra solo las posiciones ocupadas.
    // ! En arrays de objetos siempre hay que pensar en posibles null.
    public static void mostrarLibros(LibroBibliotecaSolucion[] libros) {
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null) {
                System.out.println(libros[i]);
            }
        }
    }

    // * METODO 2: CONTAR DISPONIBLES
    // ? Cuenta cuantos libros no estan prestados.
    // ? Este metodo entrena el patron contador + condicion.
    public static int contarDisponibles(LibroBibliotecaSolucion[] libros) {
        int contador = 0;

        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null && libros[i].estaDisponible()) {
                contador++;
            }
        }

        return contador;
    }

    // * METODO 3: CALCULAR MEDIA DE PAGINAS
    // ? Para calcular una media correctamente:
    // ? - sumamos todas las paginas
    // ? - contamos cuantos libros hay
    // ? - dividimos al final
    public static double calcularMediaPaginas(LibroBibliotecaSolucion[] libros) {
        int suma = 0;
        int contador = 0;

        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null) {
                suma += libros[i].getPaginas();
                contador++;
            }
        }

        // ! Si no hay libros, devolvemos 0 para evitar division entre 0.
        if (contador == 0) {
            return 0;
        }

        return (double) suma / contador;
    }

    // * METODO 4: BUSCAR POR TITULO
    // ? Recorremos el array hasta encontrar el titulo buscado.
    // ? En cuanto aparece coincidencia, devolvemos ese libro.
    public static LibroBibliotecaSolucion buscarPorTitulo(LibroBibliotecaSolucion[] libros, String tituloBuscado) {
        for (int i = 0; i < libros.length; i++) {

            // ? equalsIgnoreCase() evita problemas con mayusculas y minusculas.
            if (libros[i] != null && libros[i].getTitulo().equalsIgnoreCase(tituloBuscado)) {
                return libros[i];
            }
        }

        // ? Si no se encuentra, devolvemos null.
        return null;
    }

    // * METODO 5: OBTENER EL LIBRO CON MAS PAGINAS
    // ? Patron mental:
    // ? 1. Tener una referencia al mejor libro encontrado
    // ? 2. Recorrer el array
    // ? 3. Comparar paginas
    // ? 4. Actualizar cuando el actual sea mejor
    public static LibroBibliotecaSolucion obtenerLibroMasLargo(LibroBibliotecaSolucion[] libros) {
        LibroBibliotecaSolucion mejor = null;

        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null) {

                // ? El primer libro valido pasa a ser el mejor provisional.
                // ? A partir de ahi, comparamos con los siguientes.
                if (mejor == null || libros[i].getPaginas() > mejor.getPaginas()) {
                    mejor = libros[i];
                }
            }
        }

        return mejor;
    }

    // * METODO 6: MARCAR PRESTADO POR TITULO
    // ? Este metodo es muy interesante porque mezcla dos acciones:
    // ? - buscar un objeto dentro del array
    // ? - modificar el estado de ese objeto
    public static void marcarPrestadoPorTitulo(LibroBibliotecaSolucion[] libros, String tituloBuscado) {
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null && libros[i].getTitulo().equalsIgnoreCase(tituloBuscado)) {

                // ? No cambiamos una variable suelta.
                // ? Estamos modificando directamente el objeto encontrado.
                libros[i].prestar();
            }
        }
    }
}

// * CLASE AUXILIAR DE LA SOLUCION
// ? Representa un solo libro de la biblioteca.
class LibroBibliotecaSolucion {

    // * ATRIBUTOS PRIVADOS
    // ? Guardan el estado del libro.
    private String titulo;
    private String autor;
    private int paginas;
    private boolean prestado;

    // * CONSTRUCTOR
    // ? Recibe los datos iniciales del libro y los guarda.
    public LibroBibliotecaSolucion(String titulo, String autor, int paginas, boolean prestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.prestado = prestado;
    }

    // * GETTERS
    // ? Permiten leer los atributos desde fuera sin romper encapsulacion.
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public boolean isPrestado() {
        return prestado;
    }

    // * METODO DE NEGOCIO 1
    // ? Devuelve true cuando el libro no esta prestado.
    public boolean estaDisponible() {
        return !prestado;
    }

    // * METODO DE NEGOCIO 2
    // ? Cambia el estado interno del libro a prestado.
    public void prestar() {
        prestado = true;
    }

    // * TO STRING
    // ? Permite imprimir el libro completo de manera clara.
    @Override
    public String toString() {
        return "LibroBiblioteca{titulo='" + titulo + "', autor='" + autor
                + "', paginas=" + paginas + ", prestado=" + prestado + "}";
    }
}
