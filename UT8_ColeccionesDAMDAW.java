/******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  FECHA: 2026
 *  UNIDAD 8/9 (PRACTICA): COLECCIONES IMPORTANTES (DAM/DAW)
 *  - ArrayList, HashMap, HashSet, Arrays/Collections
 *  REPOSITORIO PRIVADO PARA USO EDUCATIVO
 ******************************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class UT8_ColeccionesDAMDAW {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*
         * IDEA GENERAL (MUY IMPORTANTE EN 1/2 DAM-DAW):
         *
         * - Array (String[], int[]): tamano fijo. Rapido y simple, pero NO crece solo.
         * - ArrayList<E>: lista dinamica (mantiene orden y permite duplicados).
         * - HashMap<K,V>: diccionario (clave -> valor). Claves unicas.
         * - HashSet<E>: conjunto (sin repetidos). Ideal para eliminar duplicados o comprobar "existe?"
         *
         * Consejos rapidos:
         * - "Tengo una lista que puede cambiar" -> ArrayList
         * - "Quiero buscar por clave (DNI, nombre, id...)" -> HashMap
         * - "No quiero repetidos" o "quiero comprobar existencia rapido" -> HashSet
         */

        // * MENU (COLECCIONES MAS USADAS EN 1 y 2 DAM/DAW)
        int opcion;
        do {
            System.out.println("\nMENU COLECCIONES (DAM/DAW)");
            System.out.println("1 - ArrayList de palabras (cargado + if + bucles)");
            System.out.println("2 - HashMap (notas / inventario) + operaciones basicas");
            System.out.println("3 - HashSet (quitar repetidos) + ejemplo sencillo");
            System.out.println("0 - Salir");
            System.out.print("Elige una opcion: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> demoArrayListPalabras(sc);
                case 2 -> demoHashMapNotas(sc);
                case 3 -> demoHashSetSinRepetidos(sc);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // * OPCION 1: ARRAYLIST (PALABRAS)
    public static void demoArrayListPalabras(Scanner sc) {
        /*
         * TEORIA:
         * - ArrayList<String> es una "lista de Strings" (texto).
         * - Mantiene el ORDEN de insercion y permite REPETIDOS.
         * - Puedes acceder por indice (como un array): get(0), get(1)...
         *
         * Metodos tipicos:
         * - add(x): anade al final
         * - get(i): devuelve el elemento en la posicion i
         * - set(i, x): reemplaza el elemento en la posicion i
         * - remove(i): borra por indice
         * - size(): tamano actual
         * - contains(x): si existe (ojo: distingue mayusculas/minusculas)
         */
        System.out.println("\nOPCION 1: ArrayList de palabras");

        // ? Lista YA CARGADA con valores (para practicar sin pedir todo al usuario)
        ArrayList<String> palabras = new ArrayList<>(
                Arrays.asList("java", "html", "css", "sql", "git", "api", "spring")
        );

        System.out.println("Lista inicial: " + palabras);

        // * Mini-menu dentro del ejercicio (para practicar)
        int opcion;
        do {
            System.out.println("\nSUBMENU ARRAYLIST");
            System.out.println("1 - Anadir palabra");
            System.out.println("2 - Buscar palabra (ignorar mayusculas)");
            System.out.println("3 - Mostrar palabras largas (>= 5)");
            System.out.println("4 - Ordenar alfabeticamente");
            System.out.println("5 - Eliminar por indice");
            System.out.println("0 - Volver");
            System.out.print("Elige: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> {
                    // * add(): anadimos una palabra nueva a la lista
                    System.out.print("Nueva palabra: ");
                    String nueva = sc.nextLine().trim();
                    if (nueva.isEmpty()) {
                        System.out.println("No se anade palabra vacia.");
                    } else {
                        palabras.add(nueva);
                        System.out.println("OK. Lista: " + palabras);
                    }
                }
                case 2 -> {
                    // * Busqueda "sin mayusculas": usamos equalsIgnoreCase()
                    System.out.print("Palabra a buscar: ");
                    String buscar = sc.nextLine().trim();
                    boolean encontrada = contieneIgnoreCase(palabras, buscar);
                    if (encontrada) {
                        System.out.println("Encontrada.");
                    } else {
                        System.out.println("No encontrada.");
                    }
                }
                case 3 -> {
                    // * IF + BUCLE: filtramos por condicion (longitud)
                    System.out.println("Palabras con 5 o mas letras:");
                    boolean alguna = false;
                    for (String p : palabras) {
                        if (p.length() >= 5) {
                            System.out.println("- " + p);
                            alguna = true;
                        }
                    }
                    if (!alguna) {
                        System.out.println("(ninguna)");
                    }
                }
                case 4 -> {
                    // * Ordenar: Collections.sort() ordena alfabeticamente (A-Z) en Strings
                    Collections.sort(palabras);
                    System.out.println("Ordenada: " + palabras);
                }
                case 5 -> {
                    // * remove(indice): borra un elemento por su posicion
                    if (palabras.isEmpty()) {
                        System.out.println("La lista esta vacia.");
                        break;
                    }
                    System.out.println("Lista actual con indices:");
                    for (int i = 0; i < palabras.size(); i++) {
                        System.out.println(i + " -> " + palabras.get(i));
                    }
                    System.out.print("Indice a eliminar: ");
                    int idx = leerEntero(sc);
                    if (idx < 0 || idx >= palabras.size()) {
                        System.out.println("Indice fuera de rango.");
                    } else {
                        String borrada = palabras.remove(idx);
                        System.out.println("Eliminada: " + borrada);
                        System.out.println("Lista: " + palabras);
                    }
                }
                case 0 -> { /* volver */ }
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        // TODO (ALUMNO): crea un ArrayList<Integer> con 5 numeros y calcula suma, maximo y minimo.
        // TODO (ALUMNO): pide al usuario una palabra y muestra en que posiciones aparece (si aparece varias veces).
    }

    // * OPCION 2: HASHMAP (CLAVE -> VALOR)
    public static void demoHashMapNotas(Scanner sc) {
        /*
         * TEORIA:
         * - HashMap<K, V> guarda PARES: clave -> valor
         *   Ejemplo: "Ana" -> 8
         *
         * Reglas importantes:
         * - Las CLAVES NO se repiten. Si haces put() con una clave ya existente, se ACTUALIZA.
         * - El orden NO esta garantizado (no es una lista).
         *
         * Metodos tipicos:
         * - put(clave, valor): anade o actualiza
         * - get(clave): devuelve el valor (o null si no existe)
         * - containsKey(clave): comprueba si existe la clave
         * - remove(clave): elimina
         * - entrySet(): recorrer clave y valor (lo mas tipico)
         */
        System.out.println("\nOPCION 2: HashMap (notas)");

        // ? HashMap ya cargado con valores
        HashMap<String, Integer> notas = new HashMap<>();
        notas.put("Ana", 8);
        notas.put("Luis", 4);
        notas.put("Marta", 10);
        notas.put("Jorge", 6);

        // * Mini-menu dentro del ejercicio
        int opcion;
        do {
            System.out.println("\nSUBMENU HASHMAP");
            System.out.println("1 - Ver todas las notas");
            System.out.println("2 - Buscar nota por nombre");
            System.out.println("3 - Anadir/Actualizar nota");
            System.out.println("4 - Mostrar aprobados (>= 5)");
            System.out.println("5 - Media de la clase");
            System.out.println("0 - Volver");
            System.out.print("Elige: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> {
                    if (notas.isEmpty()) {
                        System.out.println("(sin datos)");
                    } else {
                        for (Map.Entry<String, Integer> entry : notas.entrySet()) {
                            System.out.println(entry.getKey() + " -> " + entry.getValue());
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();
                    if (notas.containsKey(nombre)) {
                        System.out.println("Nota de " + nombre + ": " + notas.get(nombre));
                    } else {
                        System.out.println("No existe ese nombre en el mapa.");
                    }
                }
                case 3 -> {
                    // * put(): si el nombre existe, se sobreescribe la nota anterior
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Nota (0-10): ");
                    int nota = leerEntero(sc);
                    // ! Validacion sencilla
                    if (nota < 0 || nota > 10) {
                        System.out.println("Nota invalida. Debe ser 0..10.");
                    } else {
                        Integer anterior = notas.put(nombre, nota); // ? devuelve la nota anterior (o null si no existia)
                        if (anterior == null) {
                            System.out.println("OK. Alumno nuevo guardado.");
                        } else {
                            System.out.println("OK. Nota actualizada (antes era " + anterior + ").");
                        }
                    }
                }
                case 4 -> {
                    // * Filtrar aprobados: recorremos entrySet() y aplicamos un if
                    System.out.println("Aprobados (>= 5):");
                    boolean alguno = false;
                    for (Map.Entry<String, Integer> entry : notas.entrySet()) {
                        if (entry.getValue() >= 5) {
                            System.out.println("- " + entry.getKey() + " (" + entry.getValue() + ")");
                            alguno = true;
                        }
                    }
                    if (!alguno) {
                        System.out.println("(ninguno)");
                    }
                }
                case 5 -> {
                    // * Calcular media: sumamos valores y dividimos entre el tamano (ojo con int/int)
                    if (notas.isEmpty()) {
                        System.out.println("No se puede calcular la media: mapa vacio.");
                        break;
                    }
                    int suma = 0;
                    for (int n : notas.values()) {
                        suma += n;
                    }
                    double media = (double) suma / notas.size();
                    System.out.println("Media: " + media);
                }
                case 0 -> { /* volver */ }
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        // TODO (ALUMNO): crea un HashMap<Integer, Integer> para contar frecuencias de numeros.
        // ? Pista: si no existe la clave, empieza en 1; si existe, suma 1.
    }

    // * OPCION 3: HASHSET (SIN REPETIDOS)
    public static void demoHashSetSinRepetidos(Scanner sc) {
        /*
         * TEORIA:
         * - HashSet<E> es un conjunto: NO permite repetidos.
         * - Muy tipico para:
         *   1) eliminar duplicados
         *   2) comprobar si algo existe (contains)
         *
         * Importante:
         * - El orden NO esta garantizado.
         */
        System.out.println("\nOPCION 3: HashSet (quitar repetidos)");

        // ? Partimos de un array con repetidos (muy tipico en ejercicios)
        String[] array = {"java", "html", "java", "sql", "css", "css", "git"};
        System.out.println("Array original: " + Arrays.toString(array));

        // * Metemos todo en el set: los repetidos se eliminan automaticamente
        HashSet<String> sinRepetidos = new HashSet<>(Arrays.asList(array));
        System.out.println("HashSet (sin repetidos): " + sinRepetidos);

        // * contains(): comprobar existencia (if)
        System.out.print("Escribe una palabra para comprobar si esta en el set: ");
        String buscar = sc.nextLine().trim();
        if (sinRepetidos.contains(buscar)) {
            System.out.println("SI esta en el set.");
        } else {
            System.out.println("NO esta en el set.");
        }

        // TODO (ALUMNO): pasa el HashSet a un ArrayList y ordenalo.
    }

    // * UTIL: BUSQUEDA SIN DISTINGUIR MAYUSCULAS
    public static boolean contieneIgnoreCase(ArrayList<String> lista, String objetivo) {
        // ? Evita repetir el mismo codigo de busqueda en varios sitios
        // * Devuelve true si "objetivo" esta en la lista, ignorando mayusculas/minusculas.
        if (objetivo == null) {
            return false;
        }
        for (String item : lista) {
            if (item.equalsIgnoreCase(objetivo)) {
                return true;
            }
        }
        return false;
    }

    // * UTIL: LEER ENTERO SIN QUE SE ROMPA EL PROGRAMA
    public static int leerEntero(Scanner sc) {
        // ! Lectura segura de enteros: si el usuario escribe "hola", no se rompe el programa.
        while (true) {
            String texto = sc.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.print("No es un numero valido. Intenta de nuevo: ");
            }
        }
    }
}
