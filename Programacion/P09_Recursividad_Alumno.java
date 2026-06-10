public class P09_Recursividad_Alumno {

    /*
     * =============================================================
     * PLANTILLA ALUMNO - RECURSIVIDAD
     * =============================================================
     * * Instrucciones:
     *   1. Antes de implementar, dibuja la traza a mano en papel.
     *   2. Implementa el CASO BASE primero, luego el CASO RECURSIVO.
     *   3. Ejecuta el main para comprobar los resultados esperados.
     *   4. Compara con P09_Recursividad.java al terminar.
     *
     * * Estructura obligatoria:
     *   static tipo metodo(params) {
     *       if (caso base) return valor_fijo;   // PARADA
     *       return ... metodo(params_reducidos); // RECURSION
     *   }
     * =============================================================
     */

    // ! Sin caso base -> StackOverflowError (recursion infinita).
    // ! El caso recursivo siempre debe acercarse al caso base.
    // ? Traza de factorial(3): factorial(3) -> 3*factorial(2) -> 3*2*factorial(1) -> 3*2*1 = 6

    public static void main(String[] args) {

        System.out.println("=== FACTORIAL ===");
        // TODO 1: imprime factorial(i) para i = 0..7 con un bucle for.
        //         Resultados esperados: 1, 1, 2, 6, 24, 120, 720, 5040

        System.out.println("\n=== FIBONACCI ===");
        // TODO 2: imprime fibonacci(i) para i = 0..10 en una sola linea.
        //         Esperado: 0 1 1 2 3 5 8 13 21 34 55

        System.out.println("\n=== SUMA DE DIGITOS ===");
        // TODO 3: imprime sumaDigitos(1234) -> esperado 10
        //         imprime sumaDigitos(999)  -> esperado 27

        System.out.println("\n=== POTENCIA ===");
        // TODO 4: imprime potencia(2, 10) -> esperado 1024
        //         imprime potencia(5, 0)  -> esperado 1

        System.out.println("\n=== CONTAR EN ARRAY ===");
        int[] numeros = {3, 7, 2, 7, 9, 7, 1};
        // TODO 5: llama a contarApariciones(numeros, 7, 0) -> esperado 3

        System.out.println("\n=== VOLTEAR STRING ===");
        // TODO 6: imprime voltear("hola")    -> "aloh"
        //         imprime voltear("anilina") -> "anilina" (palindromo)

        System.out.println("\n=== SUMA ARRAY ===");
        int[] arr = {1, 2, 3, 4, 5};
        // TODO 7: llama a sumaArray(arr, 0) -> esperado 15
    }

    // =====================================================================
    // TODO Alumno A: implementa factorial(int n)
    //   Caso base:     n <= 1 -> return 1
    //   Caso recursivo: return n * factorial(n - 1)
    // =====================================================================
    static long factorial(int n) {
        // TODO: caso base + caso recursivo
        return 0; // reemplaza
    }

    // =====================================================================
    // TODO Alumno B: implementa fibonacci(int n)
    //   Caso base 1: n == 0 -> return 0
    //   Caso base 2: n == 1 -> return 1
    //   Caso recursivo: return fibonacci(n-1) + fibonacci(n-2)
    // =====================================================================
    static int fibonacci(int n) {
        // TODO
        return 0; // reemplaza
    }

    // =====================================================================
    // TODO Alumno C: implementa sumaDigitos(int n)
    //   Caso base:     n < 10 -> return n    (un solo digito)
    //   Caso recursivo: return (n % 10) + sumaDigitos(n / 10)
    //   Pista: n % 10 = ultimo digito | n / 10 = numero sin el ultimo digito
    // =====================================================================
    static int sumaDigitos(int n) {
        // TODO
        return 0; // reemplaza
    }

    // =====================================================================
    // TODO Alumno D: implementa potencia(int base, int exp)
    //   Caso base:     exp == 0 -> return 1
    //   Caso recursivo: return base * potencia(base, exp - 1)
    //   Lanza IllegalArgumentException si exp < 0.
    // =====================================================================
    static long potencia(int base, int exp) {
        // TODO
        return 0; // reemplaza
    }

    // =====================================================================
    // TODO Alumno E: implementa contarApariciones(int[] arr, int valor, int indice)
    //   Caso base:     indice >= arr.length -> return 0
    //   Caso recursivo: int coincide = (arr[indice] == valor) ? 1 : 0;
    //                   return coincide + contarApariciones(arr, valor, indice + 1);
    // =====================================================================
    static int contarApariciones(int[] arr, int valor, int indice) {
        // TODO
        return 0; // reemplaza
    }

    // =====================================================================
    // TODO Alumno F: implementa voltear(String s)
    //   Caso base:     s == null || s.length() <= 1 -> return s
    //   Caso recursivo: return voltear(s.substring(1)) + s.charAt(0)
    //   Traza voltear("hola"):
    //     voltear("ola") + 'h'
    //     voltear("la") + 'o' + 'h'
    //     voltear("a")  + 'l' + 'o' + 'h'
    //     "a" + 'l' + 'o' + 'h' = "aloh"
    // =====================================================================
    static String voltear(String s) {
        // TODO
        return null; // reemplaza
    }

    // =====================================================================
    // TODO Alumno G: implementa sumaArray(int[] arr, int indice)
    //   Caso base:     indice >= arr.length -> return 0
    //   Caso recursivo: return arr[indice] + sumaArray(arr, indice + 1)
    // =====================================================================
    static int sumaArray(int[] arr, int indice) {
        // TODO
        return 0; // reemplaza
    }

    // =====================================================================
    // TODO Alumno H (EXTRA): implementa esPalindromo(String s)
    //   Un palindromo se lee igual al reves: "ama", "radar", "anilina".
    //   Caso base: s.length() <= 1 -> return true
    //   Recursivo: compara s.charAt(0) y s.charAt(s.length()-1).
    //              Si son distintos -> false.
    //              Si son iguales   -> esPalindromo(s.substring(1, s.length()-1))
    // =====================================================================
    static boolean esPalindromo(String s) {
        // TODO
        return false; // reemplaza
    }
}
