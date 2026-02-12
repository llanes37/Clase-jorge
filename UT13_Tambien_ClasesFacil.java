/******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  FECHA: 2026
 *  UNIDAD 13: CLASES Y OBJETOS (NIVEL INICIAL)
 *  EJERCICIO GUIADO: GESTION BASICA DE ALUMNOS
 *  OBJETIVO: Practicar clases, objetos, encapsulacion y ArrayList
 ******************************************************************************************/

/******************************************************************************************
 *  ENUNCIADO DEL EJERCICIO (PARA CLASE)
 *-----------------------------------------------------------------------------------------
 *  Debes construir una mini aplicacion de consola para gestionar alumnos.
 *
 *  1) Crea la clase AlumnoUT13 con:
 *     - id (autogenerado)
 *     - nombre
 *     - edad
 *     - nota (0..10)
 *     - constructor, getters, setter de nota y toString()
 *
 *  2) Crea la clase AulaUT13 con un ArrayList<AlumnoUT13> y metodos para:
 *     - crearAlumno(nombre, edad, nota)
 *     - buscarPorId(id)
 *     - subirNota(id, puntos)
 *     - calcularMedia()
 *     - mostrarTodos()
 *
 *  3) En la clase principal UT13_Tambien_ClasesFacil implementa un menu:
 *     - Crear alumno
 *     - Mostrar alumnos
 *     - Buscar por ID
 *     - Subir nota
 *     - Ver media
 *     - Resumen teorico
 *
 *  REGLAS IMPORTANTES:
 *  - La nota nunca puede ser menor que 0 ni mayor que 10.
 *  - Si se busca un ID inexistente, informar al usuario.
 *  - Si no hay alumnos, la media debe devolver 0.
 *
 *  PARTE A (RESUELTA):
 *  - Revisa y ejecuta el programa completo.
 *
 *  PARTE B (TAREAS ALUMNO):
 *  - Implementa las tareas marcadas con comentarios // ! TAREA ALUMNO
 *  - Ejemplos: ordenar por nota, buscar por nombre, bajar nota, max/min.
 ******************************************************************************************/

import java.util.ArrayList;   // ? Para guardar muchos objetos AlumnoUT13
import java.util.Scanner;     // ? Para leer datos del teclado

public class UT13_Tambien_ClasesFacil {

    // * Scanner global para simplificar la practica
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // * Creamos el "gestor" del aula
        // ? Este objeto contiene y organiza todos los alumnos
        AulaUT13 aula = new AulaUT13();

        int opcion;

        // * MENU PRINCIPAL
        // ? Estructura muy parecida a UT6 para que el alumno se sienta comodo
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
                case 6:
                    mostrarResumenTeorico();
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

    // * MENU
    static void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println(" MENU - UT13 CLASES Y OBJETOS (NIVEL FACIL) ");
        System.out.println("==============================================");
        System.out.println("1. Crear alumno");
        System.out.println("2. Mostrar todos los alumnos");
        System.out.println("3. Buscar alumno por ID");
        System.out.println("4. Subir nota a un alumno");
        System.out.println("5. Ver media de la clase");
        System.out.println("6. Ver resumen teorico");
        System.out.println("0. Salir");
    }

    // * OPCION 1: Crear alumno
    // ? Aqui practicamos: constructor + encapsulacion + uso de objetos
    static void crearAlumno(AulaUT13 aula) {
        System.out.println("\n--- CREAR ALUMNO ---");
        String nombre = leerTexto("Nombre: ");
        int edad = leerInt("Edad: ");
        double nota = leerDouble("Nota inicial (0 a 10): ");

        AlumnoUT13 nuevo = aula.crearAlumno(nombre, edad, nota);
        System.out.println("Alumno creado correctamente -> " + nuevo);

        // ! TAREA ALUMNO:
        // ! 1) Validar que el nombre tenga al menos 2 letras.
        // ! 2) Mostrar mensaje especial si nota >= 9.
    }

    // * OPCION 2: Mostrar alumnos
    static void mostrarAlumnos(AulaUT13 aula) {
        System.out.println("\n--- LISTA DE ALUMNOS ---");
        aula.mostrarTodos();

        // ! TAREA ALUMNO:
        // ! Ordena la lista por nota de mayor a menor.
    }

    // * OPCION 3: Buscar por ID
    static void buscarAlumnoPorId(AulaUT13 aula) {
        System.out.println("\n--- BUSCAR ALUMNO ---");
        int id = leerInt("ID del alumno: ");
        AlumnoUT13 a = aula.buscarPorId(id);

        if (a != null) {
            System.out.println("Encontrado -> " + a);
        } else {
            System.out.println("No existe un alumno con ese ID.");
        }

        // ! TAREA ALUMNO:
        // ! Crea otra busqueda por nombre exacto.
    }

    // * OPCION 4: Subir nota
    // ? Reglas: nunca pasar de 10.0
    static void subirNota(AulaUT13 aula) {
        System.out.println("\n--- SUBIR NOTA ---");
        int id = leerInt("ID del alumno: ");
        double puntos = leerDouble("Cuantos puntos subir? ");

        boolean actualizado = aula.subirNota(id, puntos);
        if (actualizado) {
            System.out.println("Nota actualizada.");
            System.out.println("Alumno actualizado -> " + aula.buscarPorId(id));
        } else {
            System.out.println("No se pudo actualizar (ID inexistente o puntos invalidos).");
        }

        // ! TAREA ALUMNO:
        // ! Crear metodo para bajar nota con minimo 0.0
    }

    // * OPCION 5: Media de clase
    static void mostrarMediaClase(AulaUT13 aula) {
        System.out.println("\n--- MEDIA DE CLASE ---");
        double media = aula.calcularMedia();
        System.out.println("Media actual: " + media);

        // ! TAREA ALUMNO:
        // ! Mostrar tambien la nota mas alta y la mas baja.
    }

    // * OPCION 6: Resumen teorico
    static void mostrarResumenTeorico() {
        System.out.println("\n==============================================");
        System.out.println(" RESUMEN TEORICO - CLASES Y OBJETOS ");
        System.out.println("==============================================");
        System.out.println("1) Una CLASE es una plantilla.");
        System.out.println("2) Un OBJETO es una instancia de esa clase.");
        System.out.println("3) Encapsulacion: atributos privados + getters/setters.");
        System.out.println("4) Constructor: inicializa el objeto al crearlo.");
        System.out.println("5) toString: facilita mostrar datos del objeto.");
        System.out.println("6) ArrayList permite guardar muchos objetos.");

        // ! TAREA ALUMNO:
        // ! Explica con tus palabras la diferencia entre clase y objeto.
    }

    // * LECTURA SEGURA DE TEXTO
    static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    // * LECTURA SEGURA DE ENTEROS
    // ? Evita errores de entrada si el usuario escribe letras
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

    // * LECTURA SEGURA DE DECIMALES
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
// * CLASE AlumnoUT13
// ? Representa un alumno individual
// =========================================================================================
class AlumnoUT13 {
    private int id;
    private String nombre;
    private int edad;
    private double nota;

    // * Contador estatico para IDs automaticos (1, 2, 3...)
    private static int siguienteId = 1;

    public AlumnoUT13(String nombre, int edad, double nota) {
        this.id = siguienteId;
        siguienteId++;
        this.nombre = nombre;
        this.edad = edad;
        this.nota = validarNota(nota);
    }

    // ? Garantiza rango de nota entre 0 y 10
    private double validarNota(double nota) {
        if (nota < 0) {
            return 0;
        }
        if (nota > 10) {
            return 10;
        }
        return nota;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = validarNota(nota);
    }

    @Override
    public String toString() {
        return "ID=" + id + " | Nombre=" + nombre + " | Edad=" + edad + " | Nota=" + nota;
    }
}

// =========================================================================================
// * CLASE AulaUT13
// ? Gestiona una coleccion de objetos AlumnoUT13
// =========================================================================================
class AulaUT13 {
    private ArrayList<AlumnoUT13> alumnos = new ArrayList<AlumnoUT13>();

    // * Crea y guarda un alumno en la lista
    public AlumnoUT13 crearAlumno(String nombre, int edad, double nota) {
        AlumnoUT13 a = new AlumnoUT13(nombre, edad, nota);
        alumnos.add(a);
        return a;
    }

    // * Devuelve alumno por ID o null si no existe
    public AlumnoUT13 buscarPorId(int id) {
        for (AlumnoUT13 a : alumnos) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    // * Sube la nota de un alumno
    // ? Si los puntos son negativos no aplica
    public boolean subirNota(int id, double puntos) {
        if (puntos < 0) {
            return false;
        }

        AlumnoUT13 a = buscarPorId(id);
        if (a == null) {
            return false;
        }

        double nueva = a.getNota() + puntos;
        a.setNota(nueva); // ? setNota ya controla limite maximo 10
        return true;
    }

    // * Calcula media de todas las notas
    // ? Si no hay alumnos, devuelve 0
    public double calcularMedia() {
        if (alumnos.isEmpty()) {
            return 0.0;
        }

        double suma = 0.0;
        for (AlumnoUT13 a : alumnos) {
            suma += a.getNota();
        }
        return suma / alumnos.size();
    }

    // * Muestra todos los alumnos
    public void mostrarTodos() {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        for (AlumnoUT13 a : alumnos) {
            System.out.println(a);
        }
    }
}
