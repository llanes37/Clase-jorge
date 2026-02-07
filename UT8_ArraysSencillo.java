/******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  FECHA: 2026
 *  UNIDAD 8 (VERSION SENCILLA): ARRAYS + IF + BUCLES
 *  REPOSITORIO PRIVADO PARA USO EDUCATIVO
 ******************************************************************************************/

import java.util.Arrays;
import java.util.Scanner;

public class UT8_ArraysSencillo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // * MENU PRINCIPAL (EJERCICIOS SENCILLOS)
        int opcion;
        do {
            System.out.println("\nMENU UNIDAD 8 (SENCILLO): ARRAYS");
            System.out.println("1 - Array de palabras (crear y filtrar con if)");
            System.out.println("2 - Array de numeros (pares/impares y maximo)");
            System.out.println("0 - Salir");
            System.out.print("Elige una opcion: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> arrayDePalabras(sc);
                case 2 -> arrayDeNumeros(sc);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // * EJERCICIO 1: ARRAY DE PALABRAS + IF
    public static void arrayDePalabras(Scanner sc) {
        /*
         * TEORIA (MUY BASICA):
         * - Un array es una lista con un tamano fijo.
         * - Podemos recorrerlo con un for y tomar decisiones con if.
         */
        System.out.println("\nEJERCICIO 1: Array de palabras");

        // ? CAMBIA este numero a 3 si quieres hacerlo mas facil, o a 8 si quieres mas practica.
        int tam = 5;
        String[] palabras = new String[tam];

        // ? Pedimos palabras al usuario
        for (int i = 0; i < palabras.length; i++) {
            System.out.print("Introduce la palabra " + (i + 1) + ": ");
            palabras[i] = sc.nextLine().trim();
        }

        System.out.println("Array completo: " + Arrays.toString(palabras));

        // * FILTRAR CON IF (EJEMPLO 1): mostrar solo palabras largas
        System.out.println("\nPalabras con 5 o mas letras:");
        boolean alguna = false;
        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].length() >= 5) {
                System.out.println("- " + palabras[i]);
                alguna = true;
            }
        }
        if (!alguna) {
            System.out.println("(ninguna)");
        }

        // TODO (ALUMNO): muestra tambien las palabras que empiezan por vocal (a,e,i,o,u) ignorando mayusculas.
        // TODO (ALUMNO): cuenta cuantas palabras estan vacias (""), por si el usuario solo pulsa Enter.
    }

    // * EJERCICIO 2: ARRAY DE NUMEROS + IF
    public static void arrayDeNumeros(Scanner sc) {
        /*
         * TEORIA (MUY BASICA):
         * - Un array de int guarda numeros.
         * - Con if podemos saber si es par (n % 2 == 0) o impar.
         */
        System.out.println("\nEJERCICIO 2: Array de numeros");

        int tam = 6;
        int[] numeros = new int[tam];

        // ? Pedimos numeros al usuario
        for (int i = 0; i < numeros.length; i++) {
            System.out.print("Numero " + (i + 1) + ": ");
            numeros[i] = leerEntero(sc);
        }

        System.out.println("Array completo: " + Arrays.toString(numeros));

        int pares = 0;
        int impares = 0;
        int maximo = numeros[0];

        for (int n : numeros) {
            if (n % 2 == 0) {
                pares++;
            } else {
                impares++;
            }

            if (n > maximo) {
                maximo = n;
            }
        }

        System.out.println("Cantidad de pares: " + pares);
        System.out.println("Cantidad de impares: " + impares);
        System.out.println("Maximo: " + maximo);

        // TODO (ALUMNO): calcula tambien el minimo.
        // TODO (ALUMNO): calcula la media (promedio) como double.
        // ! IMPORTANTE: si divides int/int, se pierde la parte decimal. Pista: usa (double) suma / tam.
    }

    // * METODO EXTRA: LEER ENTERO SIN ROMPER EL PROGRAMA
    public static int leerEntero(Scanner sc) {
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

