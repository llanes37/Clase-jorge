public class P09_Recursividad {

    /*
     * =============================================================
     * TEORIA: RECURSIVIDAD EN JAVA
     * Basado en "Recursividad.pdf"
     * =============================================================
     * * Definicion:
     *   Un metodo es RECURSIVO cuando se llama a SI MISMO para
     *   resolver un problema dividiendolo en subproblemas mas pequenos.
     *
     * * Estructura obligatoria de todo metodo recursivo:
     *   1. CASO BASE:      condicion de parada (sin el, bucle infinito).
     *   2. CASO RECURSIVO: llamada al propio metodo con datos reducidos.
     *
     * * Cada llamada recursiva crea un nuevo "marco" en la pila de llamadas.
     *   Si el caso base nunca se alcanza -> StackOverflowError.
     *
     * * ¿Cuando usar recursividad?
     *   - Problemas naturalmente recursivos: factorial, fibonacci, arboles.
     *   - Divide y venceras: busqueda binaria, mergesort.
     *   - Cuando el codigo iterativo seria mucho mas complicado.
     *
     * * Recursividad vs Iteracion:
     *   - Recursion: mas elegante, mas facil de entender.
     *   - Iteracion: mas eficiente en memoria (no apila llamadas).
     *   En DAM usaremos recursividad para entender el concepto.
     * =============================================================
     */

    // * Better Comments:
    // * Teoria.    ! Critico.    ? Aclaracion.    TODO Alumno.    ✅ Solucion.

    public static void main(String[] args) {

        // ── PARTE 1: Factorial ─────────────────────────────────────────────
        System.out.println("=== FACTORIAL ===");
        // * factorial(5) = 5 * 4 * 3 * 2 * 1 = 120
        for (int i = 0; i <= 7; i++) {
            System.out.println("factorial(" + i + ") = " + factorial(i));
        }

        // ── PARTE 2: Fibonacci ─────────────────────────────────────────────
        System.out.println("\n=== FIBONACCI ===");
        // * Secuencia: 0, 1, 1, 2, 3, 5, 8, 13, 21...
        // * fib(n) = fib(n-1) + fib(n-2)
        System.out.print("Secuencia: ");
        for (int i = 0; i <= 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
        // TODO mini 1: imprime fibonacci(30). ¿Tarda mucho?
        //              (La version recursiva recalcula subproblemas multiples veces.)
        //              Busca en internet que es "memoizacion" para entender la mejora.

        // ── PARTE 3: Suma de digitos ───────────────────────────────────────
        System.out.println("\n=== SUMA DE DIGITOS ===");
        // * sumaDigitos(1234) = 1 + 2 + 3 + 4 = 10
        System.out.println("sumaDigitos(1234) = " + sumaDigitos(1234));
        System.out.println("sumaDigitos(999)  = " + sumaDigitos(999));
        System.out.println("sumaDigitos(0)    = " + sumaDigitos(0));

        // ── PARTE 4: Potencia ─────────────────────────────────────────────
        System.out.println("\n=== POTENCIA ===");
        System.out.println("potencia(2,10) = " + potencia(2, 10));
        System.out.println("potencia(3, 4) = " + potencia(3, 4));
        System.out.println("potencia(5, 0) = " + potencia(5, 0));

        // ── PARTE 5: Contar elementos en array ────────────────────────────
        System.out.println("\n=== CONTAR EN ARRAY ===");
        int[] numeros = {3, 7, 2, 7, 9, 7, 1};
        System.out.println("¿Cuantas veces aparece 7? " + contarApariciones(numeros, 7, 0));

        // ── PARTE 6: Voltear un String ─────────────────────────────────────
        System.out.println("\n=== VOLTEAR STRING ===");
        System.out.println("voltear('hola')    = " + voltear("hola"));
        System.out.println("voltear('java')    = " + voltear("java"));
        System.out.println("voltear('anilina') = " + voltear("anilina"));

        // ── PARTE 7: Suma de array ────────────────────────────────────────
        System.out.println("\n=== SUMA ARRAY ===");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Suma {1,2,3,4,5} = " + sumaArray(arr, 0));

        // TODO Alumno:
        // 1) Implementa "esPalindromo(String s)" de forma recursiva.
        //    Un palindromo se lee igual al reves: "ama", "radar", "anilina".
        //    Pista: compara primer y ultimo caracter, luego llama recursivamente
        //    con la subcadena sin esos dos extremos.
        // 2) Implementa "mcd(int a, int b)" (maximo comun divisor) usando
        //    el algoritmo de Euclides: mcd(a,0)=a, mcd(a,b)=mcd(b,a%b).
        // 3) Prueba mcd(48, 18) = 6.

        // ✅ Solucion orientativa (comentada)
        // System.out.println(esPalindromo("radar"));   // true
        // System.out.println(esPalindromo("hola"));    // false
        // System.out.println(mcd(48, 18));              // 6
    }

    // =====================================================================
    // * FACTORIAL: n! = n * (n-1)!
    // * Caso base: factorial(0) = 1
    // * Caso base: factorial(1) = 1
    // =====================================================================
    static long factorial(int n) {
        // ! Caso base: sin esto el metodo se llamaria infinitamente.
        if (n <= 1) return 1;
        // * Caso recursivo: reducimos el problema.
        // TODO mini 3: añade un System.out.println("calculando factorial(" + n + ")")
        //              antes del return. Observa el orden de las llamadas en consola.
        //              (Recuerda comentarlo despues para no ensuciar la salida normal.)
        return n * factorial(n - 1);
        // ? factorial(4) -> 4 * factorial(3)
        //                -> 4 * (3 * factorial(2))
        //                -> 4 * (3 * (2 * factorial(1)))
        //                -> 4 * 3 * 2 * 1 = 24
    }

    // =====================================================================
    // * FIBONACCI: fib(n) = fib(n-1) + fib(n-2)
    // * Caso base: fib(0) = 0, fib(1) = 1
    // =====================================================================
    static int fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("n debe ser >= 0");
        if (n == 0) return 0;   // caso base 1
        if (n == 1) return 1;   // caso base 2
        return fibonacci(n - 1) + fibonacci(n - 2);  // caso recursivo
    }

    // =====================================================================
    // * SUMA DE DIGITOS: sumaDigitos(1234) = 4 + sumaDigitos(123) = 10
    // * Caso base: numero de un solo digito.
    // =====================================================================
    static int sumaDigitos(int n) {
        if (n < 0) n = -n;           // por si acaso es negativo
        if (n < 10) return n;        // caso base: un solo digito
        return (n % 10) + sumaDigitos(n / 10);  // ultimo digito + resto
    }

    // =====================================================================
    // * POTENCIA: base^exp = base * potencia(base, exp-1)
    // * Caso base: exp == 0 -> resultado = 1
    // =====================================================================
    static long potencia(int base, int exp) {
        if (exp < 0) throw new IllegalArgumentException("Exponente negativo no soportado.");
        if (exp == 0) return 1;        // cualquier numero elevado a 0 es 1
        return base * potencia(base, exp - 1);
    }

    // =====================================================================
    // * CONTAR APARICIONES en array de forma recursiva.
    // * Parametro indice controla la posicion actual.
    // =====================================================================
    static int contarApariciones(int[] arr, int valor, int indice) {
        if (indice >= arr.length) return 0;   // caso base: fin del array
        int coincide = (arr[indice] == valor) ? 1 : 0;
        return coincide + contarApariciones(arr, valor, indice + 1);
    }

    // =====================================================================
    // * VOLTEAR STRING recursivamente.
    // * voltear("hola") = voltear("ola") + "h"
    // * Caso base: string de 0 o 1 caracteres.
    // =====================================================================
    static String voltear(String s) {
        if (s == null || s.length() <= 1) return s;   // caso base
        return voltear(s.substring(1)) + s.charAt(0); // recursivo
        // TODO mini 2: usa voltear() para comprobar si una palabra es palindromo.
        //              Un palindromo es igual a su version volteada.
        //              Prueba con "radar", "hola", "anilina".
    }

    // =====================================================================
    // * SUMA DE ARRAY recursiva usando indice.
    // =====================================================================
    static int sumaArray(int[] arr, int indice) {
        if (indice >= arr.length) return 0;        // caso base: fin del array
        return arr[indice] + sumaArray(arr, indice + 1);
    }
}
