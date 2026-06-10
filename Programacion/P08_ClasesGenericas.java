import java.util.ArrayList;
import java.util.List;

public class P08_ClasesGenericas {

    /*
     * =============================================================
     * TEORIA: CLASES GENERICAS EN JAVA
     * Basado en "Clases genericas.pdf"
     * =============================================================
     * * ¿Que son los genericos (Generics)?
     *   Permiten escribir clases y metodos que funcionan con CUALQUIER
     *   tipo de dato, manteniendo la seguridad de tipos en compilacion.
     *
     * * Sintaxis:
     *   class MiCaja<T> { private T contenido; }
     *   <T> es un parametro de tipo (puede ser cualquier nombre, por convenio T).
     *
     * * Parametros de tipo convencionales:
     *   T -> Type (tipo generico)
     *   E -> Element (en colecciones)
     *   K -> Key (clave)
     *   V -> Value (valor)
     *   N -> Number
     *
     * * Tipos acotados (bounded):
     *   <T extends Number>   -> T solo puede ser Number o sus subtipos.
     *   <T extends Comparable<T>> -> T debe poder compararse con si mismo.
     *
     * * Ventajas:
     *   - Reutilizacion: una clase funciona con muchos tipos.
     *   - Seguridad: el compilador detecta errores de tipo.
     *   - Sin casting: no necesitas hacer (Tipo) obj al recuperar datos.
     * =============================================================
     */

    // * Better Comments:
    // * Teoria.    ! Critico.    ? Aclaracion.    TODO Alumno.    ✅ Solucion.

    public static void main(String[] args) {

        // ── PARTE 1: Caja generica ────────────────────────────────────────
        System.out.println("=== CAJA GENERICA ===");
        // * La misma clase Caja funciona con String, Integer, Double...
        Caja<String>  cajaTexto  = new Caja<>("Hola mundo");
        Caja<Integer> cajaEntero = new Caja<>(42);
        Caja<Double>  cajaDecimal = new Caja<>(3.14);

        System.out.println("Caja texto  : " + cajaTexto.getContenido());
        System.out.println("Caja entero : " + cajaEntero.getContenido());
        System.out.println("Caja decimal: " + cajaDecimal.getContenido());
        // TODO mini 1: usa cajaTexto.setContenido("Nuevo valor") y muestra de nuevo.
        //              ¿Cambia el contenido?

        // ! Sin genericos tendriamos Object y necesitariamos casting manual.
        // ! Con genericos el compilador garantiza los tipos.

        // ── PARTE 2: Par generico (dos tipos) ─────────────────────────────
        System.out.println("\n=== PAR GENERICO <K,V> ===");
        Par<String, Integer> nota    = new Par<>("Matematicas", 9);
        Par<String, String>  capital = new Par<>("Espana", "Madrid");
        Par<Integer, Boolean> estado = new Par<>(404, false);

        System.out.println("Nota      : " + nota);
        System.out.println("Capital   : " + capital);
        System.out.println("Estado    : " + estado);

        // ── PARTE 3: Metodo generico ──────────────────────────────────────
        System.out.println("\n=== METODO GENERICO ===");
        // * El metodo infiere el tipo automaticamente segun lo que pasamos.
        mostrar("Texto");
        mostrar(123);
        mostrar(true);
        mostrar(3.14);

        // ── PARTE 4: Pila generica (Stack) ────────────────────────────────
        System.out.println("\n=== PILA GENERICA ===");
        Pila<String> pilaTextos = new Pila<>();
        pilaTextos.push("Primero");
        pilaTextos.push("Segundo");
        pilaTextos.push("Tercero");
        System.out.println("Tamano: " + pilaTextos.tamano());
        System.out.println("Tope: " + pilaTextos.peek());
        System.out.println("Pop: "  + pilaTextos.pop());
        System.out.println("Pop: "  + pilaTextos.pop());
        System.out.println("Tamano: " + pilaTextos.tamano());
        // TODO mini 2: vacia la pila con un bucle while (!pilaTextos.estaVacia()).
        //              Luego llama a pop() una vez mas y captura IllegalStateException.

        // ── PARTE 5: Tipo acotado (bounded) ──────────────────────────────
        System.out.println("\n=== TIPO ACOTADO <T extends Number> ===");
        // * Solo acepta tipos que extiendan Number (Integer, Double, Float...).
        System.out.println("Suma enteros: " + sumar(10, 20));
        System.out.println("Suma doubles: " + sumar(1.5, 2.5));
        // ! sumar("hola", "mundo") -> error de compilacion: String no es Number.

        // ── PARTE 6: Comparar con bounded Comparable ──────────────────────
        System.out.println("\n=== MAXIMO GENERICO ===");
        System.out.println("Max(3,7)   : " + maximo(3, 7));
        System.out.println("Max(a,z)   : " + maximo('a', 'z'));
        System.out.println("Max(Ana,Zoe): " + maximo("Ana", "Zoe"));
        // TODO mini 3: crea una Pila<Integer> y prueba push/pop con numeros.
        //              Observa que el mismo codigo de Pila funciona sin cambios.

        // TODO Alumno:
        // 1) Crea una clase generica "Repositorio<T>" que almacene una lista de T.
        //    Metodos: agregar(T elemento), obtener(int indice), tamano().
        // 2) Usa Repositorio<String> para guardar nombres y Repositorio<Integer> para notas.
        // 3) Crea un metodo generico "invertirArray(T[] array)" que invierta el array.
        // 4) Prueba invertirArray con String[] y Integer[].

        // ✅ Solucion orientativa (comentada)
        // Repositorio<String> repo = new Repositorio<>();
        // repo.agregar("Ana"); repo.agregar("Luis");
        // System.out.println(repo.obtener(0));  // Ana
    }

    // =====================================================================
    // * METODO GENERICO: el tipo T se deduce en la llamada.
    // =====================================================================
    static <T> void mostrar(T elemento) {
        System.out.println("Tipo: " + elemento.getClass().getSimpleName() + " | Valor: " + elemento);
    }

    // =====================================================================
    // * METODO GENERICO ACOTADO: T extends Number
    // * Solo acepta tipos numericos (Integer, Double, Float, Long...).
    // =====================================================================
    static <T extends Number> double sumar(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    // =====================================================================
    // * METODO GENERICO CON Comparable: encuentra el maximo de dos valores.
    // =====================================================================
    static <T extends Comparable<T>> T maximo(T a, T b) {
        return (a.compareTo(b) >= 0) ? a : b;
    }

    // =====================================================================
    // * CLASE GENERICA: Caja<T>
    // * Almacena un solo objeto de cualquier tipo.
    // =====================================================================
    static class Caja<T> {
        private T contenido;

        public Caja(T contenido) {
            this.contenido = contenido;
        }

        public T getContenido()          { return contenido; }
        public void setContenido(T cont) { this.contenido = cont; }

        @Override
        public String toString() {
            return "Caja[" + contenido + "]";
        }
    }

    // =====================================================================
    // * CLASE GENERICA CON DOS TIPOS: Par<K, V>
    // * Similar a Map.Entry: una clave y un valor de tipos independientes.
    // =====================================================================
    static class Par<K, V> {
        private K clave;
        private V valor;

        public Par(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }

        public K getClave() { return clave; }
        public V getValor() { return valor; }

        @Override
        public String toString() {
            return "(" + clave + " -> " + valor + ")";
        }
    }

    // =====================================================================
    // * CLASE GENERICA: Pila<T> (LIFO - Last In, First Out)
    // * Implementa una pila usando ArrayList internamente.
    // =====================================================================
    static class Pila<T> {
        private List<T> elementos = new ArrayList<>();

        // * push: anade al tope de la pila.
        public void push(T elemento) {
            elementos.add(elemento);
        }

        // * pop: extrae y devuelve el tope. Lanza excepcion si esta vacia.
        public T pop() {
            if (estaVacia()) {
                throw new IllegalStateException("La pila esta vacia.");
            }
            return elementos.remove(elementos.size() - 1);
        }

        // * peek: mira el tope sin extraer.
        public T peek() {
            if (estaVacia()) {
                throw new IllegalStateException("La pila esta vacia.");
            }
            return elementos.get(elementos.size() - 1);
        }

        public int     tamano()   { return elementos.size(); }
        public boolean estaVacia(){ return elementos.isEmpty(); }

        @Override
        public String toString() { return "Pila" + elementos; }
    }
}
