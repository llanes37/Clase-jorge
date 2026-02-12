/******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  FECHA: 2026
 *  UNIDAD 13: CLASES Y OBJETOS (NIVEL INICIAL)
 *  PLANTILLA ALUMNO: GESTION BASICA DE ALUMNOS
 ******************************************************************************************/

/******************************************************************************************
 *  ENUNCIADO PARA EL ALUMNO
 *-----------------------------------------------------------------------------------------
 *  Objetivo:
 *  Completar una mini aplicacion de consola para practicar:
 *  - Clases y objetos
 *  - Constructor
 *  - Encapsulacion (atributos privados + getters/setters)
 *  - ArrayList
 *  - Menus y validacion basica de datos
 *
 *  Debes completar TODAS las zonas marcadas con:
 *  - // TODO ALUMNO:
 *
 *  Reglas del ejercicio:
 *  1) La nota siempre debe estar entre 0 y 10.
 *  2) Si no hay alumnos, la media es 0.
 *  3) Si el ID no existe, avisar al usuario.
 *  4) El programa no debe romperse al leer datos.
 ******************************************************************************************/

import java.util.ArrayList;   // ? Coleccion de objetos
import java.util.Scanner;     // ? Entrada por teclado

public class UT13_Tambien_ClasesFacil_Alumno {

    // * Scanner global para simplificar el ejercicio
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        AulaUT13_Alumno aula = new AulaUT13_Alumno();
        int opcion;

        // * MENU PRINCIPAL
        do {
            mostrarMenu();
            opcion = leerInt("Elige una opcion: ");

            switch (opcion) {
                case 1:
                    crearAlumno(aula);
                    break;
                case 2:
                    mostrarAlumnos(aula);
                    break;
                case 3:
                    buscarAlumnoPorId(aula);
                    break;
                case 4:
                    subirNota(aula);
                    break;
                case 5:
                    mostrarMediaClase(aula);
                    break;
                case 0:
                    System.out.println("Fin de la practica.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // * Muestra el menu por pantalla
    static void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println(" MENU UT13 - PLANTILLA ALUMNO ");
        System.out.println("==============================================");
        System.out.println("1. Crear alumno");
        System.out.println("2. Mostrar todos");
        System.out.println("3. Buscar por ID");
        System.out.println("4. Subir nota");
        System.out.println("5. Calcular media");
        System.out.println("0. Salir");
    }

    // * Opcion 1
    static void crearAlumno(AulaUT13_Alumno aula) {
        System.out.println("\n--- CREAR ALUMNO ---");
        String nombre = leerTexto("Nombre: ");
        int edad = leerInt("Edad: ");
        double nota = leerDouble("Nota inicial (0..10): ");

        // TODO ALUMNO:
        // 1) Llamar al metodo crearAlumno del aula.
        // 2) Guardar el alumno devuelto en una variable.
        // 3) Mostrar "Alumno creado -> " + alumno.
        // AlumnoUT13_Alumno nuevo = ...
        // System.out.println("Alumno creado -> " + nuevo);
    }

    // * Opcion 2
    static void mostrarAlumnos(AulaUT13_Alumno aula) {
        System.out.println("\n--- LISTA DE ALUMNOS ---");

        // TODO ALUMNO:
        // Llamar al metodo que muestra todos los alumnos.
        // aula.mostrarTodos();
    }

    // * Opcion 3
    static void buscarAlumnoPorId(AulaUT13_Alumno aula) {
        System.out.println("\n--- BUSCAR POR ID ---");
        int id = leerInt("ID: ");

        // TODO ALUMNO:
        // 1) Buscar el alumno por ID.
        // 2) Si existe, mostrar "Encontrado -> " + alumno.
        // 3) Si no existe, mostrar mensaje de error.
        // AlumnoUT13_Alumno alumno = ...
        // if (...) {
        //     ...
        // } else {
        //     ...
        // }
    }

    // * Opcion 4
    static void subirNota(AulaUT13_Alumno aula) {
        System.out.println("\n--- SUBIR NOTA ---");
        int id = leerInt("ID del alumno: ");
        double puntos = leerDouble("Puntos a sumar: ");

        // TODO ALUMNO:
        // 1) Llamar a subirNota(id, puntos).
        // 2) Si devuelve true, mostrar "Nota actualizada".
        // 3) Si devuelve false, mostrar error.
        // boolean ok = ...
        // if (...) {
        //     ...
        // } else {
        //     ...
        // }
    }

    // * Opcion 5
    static void mostrarMediaClase(AulaUT13_Alumno aula) {
        System.out.println("\n--- MEDIA DE CLASE ---");

        // TODO ALUMNO:
        // 1) Pedir la media al aula.
        // 2) Mostrarla por pantalla.
        // double media = ...
        // System.out.println("Media: " + media);
    }

    // * Lectura segura de texto
    static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    // * Lectura segura de enteros
    static int leerInt(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Valor invalido. Introduce un entero: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    // * Lectura segura de decimales
    static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.print("Valor invalido. Introduce un decimal: ");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }
}

// =========================================================================================
// * CLASE AlumnoUT13_Alumno
// ? Representa un alumno individual
// =========================================================================================
class AlumnoUT13_Alumno {
    private int id;
    private String nombre;
    private int edad;
    private double nota;

    // * Contador estatico para ID autoincremental
    private static int siguienteId = 1;

    public AlumnoUT13_Alumno(String nombre, int edad, double nota) {
        // TODO ALUMNO:
        // 1) Asignar id usando siguienteId.
        // 2) Incrementar siguienteId.
        // 3) Asignar nombre y edad.
        // 4) Asignar nota usando validarNota(nota).
        // this.id = ...
        // siguienteId++;
        // this.nombre = ...
        // this.edad = ...
        // this.nota = ...
    }

    // * Metodo auxiliar para asegurar nota entre 0 y 10
    private double validarNota(double nota) {
        // TODO ALUMNO:
        // Si nota < 0 devolver 0
        // Si nota > 10 devolver 10
        // Si no, devolver nota
        // if (...) return ...;
        // if (...) return ...;
        // return ...;
        return 0; // Placeholder temporal
    }

    // TODO ALUMNO:
    // Completar getters necesarios para usar la clase.
    public int getId() {
        // return ...;
        return 0; // Placeholder temporal
    }

    public String getNombre() {
        // return ...;
        return ""; // Placeholder temporal
    }

    public int getEdad() {
        // return ...;
        return 0; // Placeholder temporal
    }

    public double getNota() {
        // return ...;
        return 0; // Placeholder temporal
    }

    // TODO ALUMNO:
    // Crear setter de nota que use validarNota.
    public void setNota(double nota) {
        // this.nota = ...;
    }

    @Override
    public String toString() {
        // TODO ALUMNO:
        // Devolver una cadena clara con todos los datos.
        // return "...";
        return ""; // Placeholder temporal
    }
}

// =========================================================================================
// * CLASE AulaUT13_Alumno
// ? Gestiona el ArrayList de alumnos
// =========================================================================================
class AulaUT13_Alumno {
    private ArrayList<AlumnoUT13_Alumno> alumnos = new ArrayList<AlumnoUT13_Alumno>();

    // * Crea y guarda un nuevo alumno
    public AlumnoUT13_Alumno crearAlumno(String nombre, int edad, double nota) {
        // TODO ALUMNO:
        // 1) Crear objeto AlumnoUT13_Alumno.
        // 2) Anadirl o a la lista.
        // 3) Devolver el objeto creado.
        // AlumnoUT13_Alumno alumno = ...
        // alumnos.add(alumno);
        // return alumno;
        return null; // Placeholder temporal
    }

    // * Busca por ID (si no existe, devuelve null)
    public AlumnoUT13_Alumno buscarPorId(int id) {
        // TODO ALUMNO:
        // Recorrer la lista y devolver el alumno con ese ID.
        // Si no hay coincidencia, devolver null.
        // for (...) {
        //     if (...) return ...;
        // }
        return null; // Placeholder temporal
    }

    // * Sube nota a un alumno concreto
    public boolean subirNota(int id, double puntos) {
        // TODO ALUMNO:
        // 1) Si puntos < 0, devolver false.
        // 2) Buscar alumno por ID.
        // 3) Si no existe, devolver false.
        // 4) Actualizar nota y devolver true.
        // if (...) return false;
        // AlumnoUT13_Alumno alumno = ...
        // if (...) return false;
        // alumno.setNota(...);
        // return true;
        return false; // Placeholder temporal
    }

    // * Calcula media de la clase
    public double calcularMedia() {
        // TODO ALUMNO:
        // 1) Si la lista esta vacia, devolver 0.
        // 2) Sumar notas de todos.
        // 3) Dividir entre numero de alumnos.
        // if (...) return 0;
        // double suma = 0;
        // for (...) suma += ...;
        // return ...;
        return 0; // Placeholder temporal
    }

    // * Muestra alumnos en consola
    public void mostrarTodos() {
        // TODO ALUMNO:
        // 1) Si esta vacio, mostrar aviso.
        // 2) Si no, recorrer e imprimir cada alumno.
        // if (...) {
        //     ...
        //     return;
        // }
        // for (...) {
        //     ...
        // }
    }
}
