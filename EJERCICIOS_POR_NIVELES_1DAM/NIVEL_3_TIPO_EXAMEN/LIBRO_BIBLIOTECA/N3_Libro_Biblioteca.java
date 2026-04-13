/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  BLOQUE DE EJERCICIOS POR NIVELES - 1 DAM
 *  NIVEL 3 - TIPO EXAMEN
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  EJERCICIO NIVEL 3 | LIBROS + ARRAY DE OBJETOS + METODOS
 * ==========================================================================================
 *  Este nivel se parece mas a un examen real:
 *
 *  - hay menos ayuda paso a paso
 *  - hay que organizar mejor el trabajo
 *  - aparecen metodos de consulta y de modificacion
 *
 *  OBJETIVO:
 *  Construir una clase LibroBiblioteca y trabajar con un array de libros.
 * ==========================================================================================
 */

public class N3_Libro_Biblioteca {

    public static void main(String[] args) {

        // TODO 1: Crea un array de libros con capacidad para 5 posiciones

        // TODO 2: Crea 4 objetos LibroBiblioteca con datos inventados

        // TODO 3: Guarda esos objetos en el array

        // TODO 4: Muestra todos los libros

        // TODO 5: Cuenta cuantos libros estan disponibles

        // TODO 6: Calcula la media de paginas de los libros almacenados

        // TODO 7: Busca un libro por titulo y muestralo

        // TODO 8: Obtiene el libro con mayor numero de paginas

        // TODO 9: Marca como prestado un libro buscando por su titulo

        // TODO 10: Vuelve a mostrar todos los libros para comprobar el cambio
    }

    public static void mostrarLibros(LibroBiblioteca[] libros) {
        // TODO
    }

    public static int contarDisponibles(LibroBiblioteca[] libros) {
        // TODO
        return 0;
    }

    public static double calcularMediaPaginas(LibroBiblioteca[] libros) {
        // TODO
        return 0;
    }

    public static LibroBiblioteca buscarPorTitulo(LibroBiblioteca[] libros, String tituloBuscado) {
        // TODO
        return null;
    }

    public static LibroBiblioteca obtenerLibroMasLargo(LibroBiblioteca[] libros) {
        // TODO
        return null;
    }

    public static void marcarPrestadoPorTitulo(LibroBiblioteca[] libros, String tituloBuscado) {
        // TODO
    }
}

class LibroBiblioteca {

    // TODO 11: Declara los atributos privados:
    // - titulo
    // - autor
    // - paginas
    // - prestado

    // TODO 12: Implementa el constructor
    public LibroBiblioteca(String titulo, String autor, int paginas, boolean prestado) {
        // TODO
    }

    // TODO 13: Crea los getters necesarios

    // TODO 14: Crea estaDisponible() que devuelva true si no esta prestado

    // TODO 15: Crea prestar() para marcar el libro como prestado

    // TODO 16: Mejora toString()
    @Override
    public String toString() {
        return "LibroBiblioteca{}";
    }
}
