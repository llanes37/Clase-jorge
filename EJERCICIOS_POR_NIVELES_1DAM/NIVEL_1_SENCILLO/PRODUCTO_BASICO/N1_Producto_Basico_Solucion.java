/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  BLOQUE DE EJERCICIOS POR NIVELES - 1 DAM
 *  NIVEL 1 - SENCILLO - SOLUCION
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  SOLUCION | PRODUCTO BASICO
 * ==========================================================================================
 *  Este archivo contiene una posible solucion completa del ejercicio.
 *
 *  NO SOLO RESUELVE EL EJERCICIO:
 *  Tambien intenta mostrar el orden mental correcto para construirlo.
 *
 *  ORDEN SEGUIDO:
 *  1. Crear la clase Producto
 *  2. Declarar atributos
 *  3. Hacer constructor
 *  4. Hacer getters
 *  5. Hacer metodos de negocio
 *  6. Hacer toString()
 *  7. Usar la clase en main
 * ==========================================================================================
 */

public class N1_Producto_Basico_Solucion {

    public static void main(String[] args) {

        // * PASO 1: CREAR OBJETOS
        // ? Ahora ya podemos crear productos porque la clase Producto esta completa.
        ProductoSolucion p1 = new ProductoSolucion("Teclado", 25.99, 8);
        ProductoSolucion p2 = new ProductoSolucion("Raton", 14.50, 0);
        ProductoSolucion p3 = new ProductoSolucion("Monitor", 179.95, 3);

        // * PASO 2: MOSTRAR OBJETOS
        // ? Gracias a toString(), imprimir el objeto ya da informacion clara.
        System.out.println("=== PRODUCTOS INICIALES ===");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // * PASO 3: COMPROBAR STOCK
        // ? hayStock() responde a una pregunta simple sobre el estado del objeto.
        System.out.println("\n=== COMPROBACION DE STOCK ===");
        System.out.println(p1.getNombre() + " tiene stock: " + p1.hayStock());
        System.out.println(p2.getNombre() + " tiene stock: " + p2.hayStock());

        // * PASO 4: MODIFICAR EL STOCK
        // ? Aqui el objeto cambia internamente al llamar a sus metodos.
        p2.anadirStock(5);
        p3.quitarStock(2);

        // * PASO 5: VOLVER A MOSTRAR LOS OBJETOS
        // ? Esto permite comprobar si los cambios se han aplicado bien.
        System.out.println("\n=== PRODUCTOS ACTUALIZADOS ===");
        System.out.println(p2);
        System.out.println(p3);

        // * PASO 6: MENSAJE FINAL
        System.out.println("\nEjercicio probado correctamente.");
    }
}

// * CLASE AUXILIAR DE LA SOLUCION
// ? La dejamos en el mismo archivo para que el alumno vea todo el ejemplo junto.
class ProductoSolucion {

    // * ATRIBUTOS PRIVADOS
    // ? Cada objeto ProductoSolucion tendra su propio nombre, precio y stock.
    private String nombre;
    private double precio;
    private int stock;

    // * CONSTRUCTOR
    // ? El constructor recibe los datos iniciales y los guarda en el objeto.
    // ! Usamos this para diferenciar el atributo del parametro.
    public ProductoSolucion(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // * GETTERS
    // ? Estos metodos permiten leer el valor de cada atributo.
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    // * METODO DE NEGOCIO 1
    // ? Devuelve true si el stock es mayor que 0.
    public boolean hayStock() {
        return stock > 0;
    }

    // * METODO DE NEGOCIO 2
    // ? Suma al stock la cantidad recibida.
    public void anadirStock(int cantidad) {
        stock = stock + cantidad;
    }

    // * METODO DE NEGOCIO 3
    // ? Resta del stock la cantidad recibida.
    public void quitarStock(int cantidad) {
        stock = stock - cantidad;
    }

    // * TO STRING
    // ? Muy util para mostrar el objeto completo de forma legible.
    @Override
    public String toString() {
        return "Producto{nombre='" + nombre + "', precio=" + precio + ", stock=" + stock + "}";
    }
}
