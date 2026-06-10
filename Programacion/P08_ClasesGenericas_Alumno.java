import java.util.ArrayList;
import java.util.List;

public class P08_ClasesGenericas_Alumno {

    /*
     * =============================================================
     * PLANTILLA ALUMNO - CLASES GENERICAS
     * =============================================================
     * * Instrucciones:
     *   1. Implementa las clases genericas en orden.
     *   2. Ejecuta el main para comprobar que los tipos funcionan.
     *   3. Compara con P08_ClasesGenericas.java al terminar.
     *
     * * Sintaxis clave:
     *   class Caja<T> { private T contenido; }
     *   static <T> void mostrar(T elemento) { ... }
     *   <T extends Number>   -> solo tipos numericos
     *   <T extends Comparable<T>> -> solo tipos comparables
     * =============================================================
     */

    // ! <T> en la clase: T se concreta al crear el objeto: new Caja<String>()
    // ! <T> en el metodo: T se infiere automaticamente segun el argumento pasado.
    // ? El compilador garantiza los tipos -> sin casting manual, sin errores ocultos.

    public static void main(String[] args) {

        System.out.println("=== CAJA GENERICA ===");
        // TODO 1: crea Caja<String> con "Hola mundo" y muestra getContenido()
        // TODO 2: crea Caja<Integer> con 42 y muestra getContenido()
        // TODO 3: crea Caja<Double>  con 3.14 y muestra getContenido()

        System.out.println("\n=== PAR GENERICO <K,V> ===");
        // TODO 4: crea Par<String,Integer> para ("Matematicas", 9) y muestra toString()
        // TODO 5: crea Par<String,String>  para ("Espana","Madrid") y muestra toString()

        System.out.println("\n=== METODO GENERICO ===");
        // TODO 6: llama a mostrar() con String, Integer, Boolean y Double.
        //         Observa como infiere el tipo automaticamente.

        System.out.println("\n=== PILA GENERICA ===");
        Pila<String> pila = new Pila<>();
        // TODO 7: haz push de "Primero", "Segundo", "Tercero"
        //         imprime tamano(), peek() y luego pop() dos veces.
        //         imprime tamano() de nuevo.

        System.out.println("\n=== TIPO ACOTADO <T extends Number> ===");
        // TODO 8: llama a sumar(10, 20)  y muestra el resultado
        // TODO 9: llama a sumar(1.5, 2.5) y muestra el resultado

        System.out.println("\n=== MAXIMO GENERICO ===");
        // TODO 10: llama a maximo(3, 7)     y muestra el resultado
        // TODO 11: llama a maximo("Ana","Zoe") y muestra el resultado
    }

    // =====================================================================
    // TODO Alumno A: implementa el metodo generico mostrar(T elemento)
    //   Imprime: "Tipo: [NombreClase] | Valor: [elemento]"
    //   Pista: elemento.getClass().getSimpleName() para el nombre del tipo.
    // =====================================================================
    static <T> void mostrar(T elemento) {
        // TODO
    }

    // =====================================================================
    // TODO Alumno B: implementa sumar(T a, T b) con T extends Number
    //   Devuelve double con a.doubleValue() + b.doubleValue()
    // =====================================================================
    static <T extends Number> double sumar(T a, T b) {
        return 0; // TODO: reemplaza
    }

    // =====================================================================
    // TODO Alumno C: implementa maximo(T a, T b) con T extends Comparable<T>
    //   Usa a.compareTo(b): si >= 0 devuelve a, si no devuelve b.
    // =====================================================================
    static <T extends Comparable<T>> T maximo(T a, T b) {
        return null; // TODO: reemplaza
    }

    // =====================================================================
    // TODO Alumno D: implementa la clase generica Caja<T>.
    //   Atributo private T contenido.
    //   Constructor Caja(T contenido).
    //   getContenido(), setContenido(T).
    //   toString(): "Caja[[contenido]]"
    // =====================================================================
    static class Caja<T> {
        // TODO D1: atributo
        // TODO D2: constructor
        // TODO D3: getContenido()
        // TODO D4: setContenido(T)
        // TODO D5: toString()
    }

    // =====================================================================
    // TODO Alumno E: implementa la clase generica Par<K, V>.
    //   Atributos: K clave, V valor.
    //   Constructor Par(K clave, V valor).
    //   getClave(), getValor().
    //   toString(): "([clave] -> [valor])"
    // =====================================================================
    static class Par<K, V> {
        // TODO E1: atributos
        // TODO E2: constructor
        // TODO E3: getClave(), getValor()
        // TODO E4: toString()
    }

    // =====================================================================
    // TODO Alumno F: implementa la clase generica Pila<T> (LIFO).
    //   Usa List<T> elementos = new ArrayList<>() internamente.
    //   push(T)   -> anade al final de la lista.
    //   pop()     -> elimina y devuelve el ultimo. Lanza IllegalStateException si vacia.
    //   peek()    -> devuelve el ultimo sin eliminar. Lanza IllegalStateException si vacia.
    //   tamano()  -> devuelve elementos.size()
    //   estaVacia() -> devuelve elementos.isEmpty()
    // =====================================================================
    static class Pila<T> {
        private List<T> elementos = new ArrayList<>();

        // TODO F1: push(T elemento)
        public void push(T elemento) { }

        // TODO F2: pop() -> T
        //   if (estaVacia()) throw new IllegalStateException("La pila esta vacia.");
        //   return elementos.remove(elementos.size() - 1);
        public T pop() { return null; /* TODO */ }

        // TODO F3: peek() -> T
        public T peek() { return null; /* TODO */ }

        // TODO F4: tamano() y estaVacia()
        public int     tamano()    { return 0; /* TODO */ }
        public boolean estaVacia() { return true; /* TODO */ }
    }
}
